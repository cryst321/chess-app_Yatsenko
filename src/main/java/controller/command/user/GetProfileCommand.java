package controller.command.user;

import constants.Page;
import controller.command.Command;
import dao.UserDao;
import entity.User;
import entity.UserCredentials;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import service.UserCredentialsService;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Optional;

public class GetProfileCommand implements Command {

    private final UserService userService;
    private static final Logger LOGGER = LogManager.getLogger(UserService.class);

    public GetProfileCommand(UserService userService) {
        this.userService = userService;
    }


    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        UserCredentials currentUserCredentials = (UserCredentials) session.getAttribute("userCredentials");

        String userIdParam = request.getParameter("userId");
        User user;

        if (userIdParam != null) {
            Integer userId = Integer.valueOf(userIdParam);
            Optional<User> userOpt = userService.getUserById(userId);
            if (userOpt.isPresent()) {
                user = userOpt.get();
            } else {
                request.setAttribute("errorMessage", "User not found");
                return Page.PAGE_NOT_FOUND;

            }
        } else if (currentUserCredentials != null) {
            Optional<User> userOpt = userService.getUserById(currentUserCredentials.getId());
            if (userOpt.isPresent()) {
                user = userOpt.get();
            } else {
                request.setAttribute("errorMessage", "User not found in session");
                return Page.PAGE_NOT_FOUND;
            }
        } else {
            return Page.LOGIN_VIEW;
        }

        request.setAttribute("user", user);
        return Page.PROFILE_VIEW;
    }
}