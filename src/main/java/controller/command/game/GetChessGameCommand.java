package controller.command.game;


import constants.Page;
import controller.command.Command;
import entity.ChessGame;
import service.ChessGameService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

public class GetChessGameCommand implements Command {

    private final ChessGameService chessGameService;

    public GetChessGameCommand(ChessGameService chessGameService) {
        this.chessGameService = chessGameService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer gameId = Integer.parseInt(request.getParameter("gameId"));


        Optional<ChessGame> chessGameOpt = chessGameService.getChessGameById(gameId);
        if (chessGameOpt.isPresent()) {
            request.setAttribute("chessGame", chessGameOpt.get());
            return Page.BOARD_VIEW;
        } else {
            return Page.LOBBY_VIEW;
        }
    }
}

