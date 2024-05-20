package controller.command.game;

import constants.Page;
import controller.command.Command;
import entity.GameRequest;
import service.GameRequestService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**Всі запити на гру*/
public class GetLobbyCommand implements Command {

    private final GameRequestService gameRequestService;

    public GetLobbyCommand(GameRequestService gameRequestService) {
        this.gameRequestService = gameRequestService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<GameRequest> gameRequests = gameRequestService.getAllGameRequests();
        request.setAttribute("gameRequests", gameRequests);
        return Page.LOBBY_VIEW;
    }
}

