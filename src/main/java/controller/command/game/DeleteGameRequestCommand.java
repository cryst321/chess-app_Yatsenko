package controller.command.game;

import constants.Attribute;
import constants.Page;
import controller.command.Command;
import service.GameRequestService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteGameRequestCommand implements Command {

    private final GameRequestService gameRequestService;

    public DeleteGameRequestCommand(GameRequestService gameRequestService) {
        this.gameRequestService = gameRequestService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String gameIdParam = request.getParameter("gameRequestId");
        if (gameIdParam != null) {
            try {
                Integer gameRequestId = Integer.valueOf(gameIdParam);
                gameRequestService.deleteGameRequest(gameRequestId);
            } catch (NumberFormatException e) {
                throw new ServletException("Invalid game request ID format", e);
            }
        }

        request.setAttribute("gameRequests", gameRequestService.getAllGameRequests());
        return Page.LOBBY_VIEW;
    }
}
