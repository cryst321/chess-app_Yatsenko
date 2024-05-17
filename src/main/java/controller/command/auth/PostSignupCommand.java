package controller.command.auth;

import constants.Attribute;
import constants.Page;
import constants.ServletPath;
import controller.command.Command;
import controller.utils.HttpWrapper;
import controller.utils.RedirectionManager;
import controller.utils.SessionManager;
import dto.CredentialsDto;
import entity.UserCredentials;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import service.UserCredentialsService;
import validator.entity.CredentialsDtoValidator;
import validator.entity.UserCredentialsValidator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class PostSignupCommand implements Command {
    private final UserCredentialsService userService;
    private static final Logger LOGGER = LogManager.getLogger(UserCredentialsService.class);

    public PostSignupCommand(UserCredentialsService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();

        if (SessionManager.getInstance().isUserLoggedIn(session)) {
            RedirectionManager.getInstance().redirect(new HttpWrapper(request, response), ServletPath.HOME);
            return RedirectionManager.REDIRECTION;
        }

        UserCredentials userCredentialsInput = getUserInput(request);

        List<String> errors = validateUserInput(userCredentialsInput);

        if (!errors.isEmpty()) {
            addRequestAttributes(request, userCredentialsInput, errors);
            return Page.SIGNUP_VIEW;
        }

        userService.createUser(userCredentialsInput);

        Optional<UserCredentials> userCredentialsAfterReg = userService.getFullCredentials(new CredentialsDto(userCredentialsInput.getEmail(),userCredentialsInput.getPassword()));

        if (userCredentialsAfterReg.isPresent()) {
            LOGGER.info("user registered " + userCredentialsAfterReg.get().getNickname() + " " + userCredentialsAfterReg.get().getRole());
            SessionManager.getInstance().addUserToSession(session, userCredentialsAfterReg.get());
            RedirectionManager.getInstance().redirect(new HttpWrapper(request, response), ServletPath.HOME);
            return RedirectionManager.REDIRECTION;
        }
        else {
            LOGGER.info("user not registered");
        }
        errors.add("Invalid credentials.");

        addRequestAttributes(request, userCredentialsInput, errors);
        return Page.SIGNUP_VIEW;
    }

    private UserCredentials getUserInput(HttpServletRequest request) {
        return new UserCredentials(request.getParameter(Attribute.NICKNAME),request.getParameter(Attribute.EMAIL), request.getParameter(Attribute.PASSWORD));
    }

    private List<String> validateUserInput(UserCredentials userCredentials) {
        return UserCredentialsValidator.getInstance().validate(userCredentials);
    }

    private void addRequestAttributes(HttpServletRequest request, UserCredentials userCredentials, List<String> errors) {
        request.setAttribute(Attribute.LOGIN_USER, userCredentials);
        request.setAttribute(Attribute.ERRORS, errors);
    }
}
