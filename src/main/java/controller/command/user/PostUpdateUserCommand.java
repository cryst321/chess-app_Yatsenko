package controller.command.user;

import constants.Attribute;
import constants.Page;
import controller.command.Command;
import controller.utils.HttpWrapper;
import controller.utils.RedirectionManager;
import controller.utils.SessionManager;
import dto.CredentialsDto;
import entity.User;
import entity.UserCredentials;
import entity.UserDetails;
import exception.ServiceException;
import org.apache.log4j.Logger;
import service.UserService;
import validator.entity.UserCredentialsValidator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Date;
import java.util.List;
import java.util.Optional;

public class PostUpdateUserCommand implements Command {
    private static final Logger LOGGER = Logger.getLogger(PostUpdateUserCommand.class);

    private final UserService userService;

    public PostUpdateUserCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();

        UserCredentials currentUserCredentials = (UserCredentials) session.getAttribute("userCredentials");

        if (currentUserCredentials == null) {
            throw new ServletException("User not found in session.");
        }

        User updatedUser = getUserInput(request, currentUserCredentials.getId());

        List<String> errors = validateUserInput(updatedUser);

        if (!errors.isEmpty()) {
            addRequestAttributes(request, updatedUser, errors);
            return Page.UPDATE_PROFILE;
        }

        try {
            userService.updateUser(updatedUser);
        } catch (ServiceException e) {
            LOGGER.error("Error updating user", e);
            errors.add("Error updating user.");
            addRequestAttributes(request, updatedUser, errors);
            return Page.UPDATE_PROFILE;
        }
        request.setAttribute("user", updatedUser);
        return Page.PROFILE_VIEW;
    }

    private User getUserInput(HttpServletRequest request, Integer userId) {

        User existingUser = userService.getUserById(userId).orElseThrow(() -> new IllegalArgumentException("User not found"));

        UserCredentials userCredentials = existingUser.getUserCredentials();
        userCredentials.setNickname(request.getParameter(Attribute.NICKNAME));
        userCredentials.setEmail(request.getParameter(Attribute.EMAIL));
        userCredentials.setPassword(request.getParameter(Attribute.PASSWORD));

        UserDetails userDetails = existingUser.getUserDetails();
        userDetails.setCountry(request.getParameter(Attribute.COUNTRY));
        userDetails.setGender(request.getParameter(Attribute.GENDER));
        userDetails.setDateOfBirth(Date.valueOf(request.getParameter(Attribute.DATE_OF_BIRTH)));
        userDetails.setProfilePicture(request.getParameter(Attribute.PROFILE_PICTURE));
        userDetails.setBio(request.getParameter(Attribute.BIO));

        User user = new User();
        user.setUserCredentials(userCredentials);
        user.setUserDetails(userDetails);
        user.setId(userId);

        return user;

    }

    private List<String> validateUserInput(User user) {
        return UserCredentialsValidator.getInstance().validate(user.getUserCredentials());
    }

    private void addRequestAttributes(HttpServletRequest request, User user, List<String> errors) {
        request.setAttribute(Attribute.USER, user);
        request.setAttribute(Attribute.ERRORS, errors);
    }
}
