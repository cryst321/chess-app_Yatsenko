package controller.command.user;

import constants.Page;
import controller.command.Command;
import entity.User;
import entity.UserCredentials;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class GetUpdateUserCommand implements Command {

    private final UserService userService;

    public GetUpdateUserCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        UserCredentials currentUserCredentials = (UserCredentials) session.getAttribute("userCredentials");

        if (currentUserCredentials == null) {
            throw new ServletException("User not found in session.");
        }

        Integer userId = currentUserCredentials.getId();
        User user = userService.getUserById(userId).orElseThrow(() -> new ServletException("User not found"));

        request.setAttribute("user", user);

        return Page.UPDATE_PROFILE;
    }
}
