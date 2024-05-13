package controller.command.user_credentials;

import constants.Attribute;
import constants.Page;
import controller.command.Command;
import entity.UserCredentials;
import service.UserCredentialsService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class AllUserCredentialsCommand implements Command {

    private final UserCredentialsService userCredentialsService;

    public AllUserCredentialsCommand(UserCredentialsService userCredentialsService) {
        this.userCredentialsService = userCredentialsService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<UserCredentials> userCredentials = userCredentialsService.getAllUsers();

        request.setAttribute("credentials", userCredentials);

        return Page.ALL_CREDENTIALS_VIEW;
    }
}
