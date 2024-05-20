package controller.command.game;

import constants.Page;
import controller.command.Command;
import controller.utils.HttpWrapper;
import controller.utils.RedirectionManager;
import service.GameRequestService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


public class CheckGameStatusCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Integer gameId = (Integer) session.getAttribute("currentGameId");

        HttpWrapper httpWrapper = new HttpWrapper(request, response);

        if (gameId != null) {
            RedirectionManager.getInstance().redirect(httpWrapper, "/game?gameId=" + gameId);
        } else {
            RedirectionManager.getInstance().redirect(httpWrapper, "/lobby");
        }
        return null;
    }
}

