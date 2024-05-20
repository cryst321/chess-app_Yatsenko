package controller.command.complaint;


import constants.Page;
import controller.command.Command;
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

public class GetReportCommand implements Command {

    private final UserService userService;
    private static final Logger LOGGER = LogManager.getLogger(UserService.class);

    public GetReportCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        UserCredentials currentUserCredentials = (UserCredentials) session.getAttribute("userCredentials");

        if (currentUserCredentials == null) {
            throw new ServletException("User not found in session.");
        }

        Integer reportedId = Integer.valueOf(request.getParameter("reportedId"));
        request.setAttribute("reportedId", reportedId);
        LOGGER.info("Filing report for " + reportedId);

        return Page.REPORT_VIEW;
    }
}
