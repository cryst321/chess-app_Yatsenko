package controller.command.game;

import constants.Attribute;
import constants.Page;
import controller.command.Command;
import entity.GameRequest;
import entity.User;
import entity.UserCredentials;
import service.GameRequestService;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class PostCreateGameCommand implements Command {

    private final GameRequestService gameRequestService;
    private final UserService userService;

    public PostCreateGameCommand(GameRequestService gameRequestService, UserService userService) {
        this.gameRequestService = gameRequestService;
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        UserCredentials currentUserCredentials = (UserCredentials) session.getAttribute(Attribute.USER_CREDENTIALS);

        if (currentUserCredentials == null) {
            throw new ServletException("User not found in session.");
        }

        GameRequest gameRequest = getGameRequestInput(request, currentUserCredentials);

        gameRequestService.createGameRequest(gameRequest);

        return Page.HOME_VIEW;
    }

    private GameRequest getGameRequestInput(HttpServletRequest request, UserCredentials userCredentials) throws ServletException {
        User user = userService.getUserById(userCredentials.getId())
                .orElseThrow(() -> new ServletException("User not found"));

        return new GameRequest.Builder()
                .setUser(user)
                .setGameType(GameRequest.GameType.valueOf(request.getParameter("gameType").toUpperCase()))
                .setRatingLess(Integer.parseInt(request.getParameter("ratingLess")))
                .setRatingMore(Integer.parseInt(request.getParameter("ratingMore")))
                .setTimeRestriction(Integer.parseInt(request.getParameter("timeRestriction")))
                .setPreferredColor(request.getParameter("preferredColor"))
                .setIsRating(Boolean.parseBoolean(request.getParameter("isRating")))
                .build();
    }
}
