package controller.command.user;

import constants.ServletPath;
import controller.command.Command;
import controller.utils.HttpWrapper;
import controller.utils.RedirectionManager;
import entity.UserCredentials;
import service.UserDetailsService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class ChangeRatingCommand implements Command {

    private final UserDetailsService userDetailsService;

    public ChangeRatingCommand(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        UserCredentials currentUser = (UserCredentials) session.getAttribute("userCredentials");

        if (currentUser == null || (!currentUser.getRole().equals("admin") && !currentUser.getRole().equals("moderator"))) {
            RedirectionManager.getInstance().redirect(new HttpWrapper(request, response), ServletPath.HOME);
            return RedirectionManager.REDIRECTION;
        }

        Integer userId = Integer.parseInt(request.getParameter("userId"));
        Integer ratingChange = Integer.parseInt(request.getParameter("ratingChange"));
        Short ratingType = Short.parseShort(request.getParameter("ratingType"));

        userDetailsService.updateRating(userId, ratingChange, ratingType);

        RedirectionManager.getInstance().redirect(new HttpWrapper(request, response), "/profile?userId=" + userId);
        return RedirectionManager.REDIRECTION;

    }
}
