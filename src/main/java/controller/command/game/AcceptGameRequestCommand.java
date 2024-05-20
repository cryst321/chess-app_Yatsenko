package controller.command.game;

import constants.Page;
import constants.ServletPath;
import controller.command.Command;
import controller.utils.HttpWrapper;
import controller.utils.RedirectionManager;
import controller.utils.SessionManager;
import entity.ChessGame;
import entity.GameRequest;
import entity.User;
import entity.UserCredentials;
import service.ChessGameService;
import service.GameRequestService;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Optional;

public class AcceptGameRequestCommand implements Command {

    private final GameRequestService gameRequestService;
    private final ChessGameService chessGameService;
    private final UserService userService;

    public AcceptGameRequestCommand(GameRequestService gameRequestService, ChessGameService chessGameService, UserService userService) {
        this.gameRequestService = gameRequestService;
        this.chessGameService = chessGameService;
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer gameRequestId = Integer.parseInt(request.getParameter("gameRequestId"));
        HttpSession session = request.getSession();
        UserCredentials currentUserCreds = (UserCredentials) session.getAttribute("userCredentials");
        Optional<User> currentUser = userService.getUserById(currentUserCreds.getId());
        if (!currentUser.isPresent()) {
            throw new ServletException("User not found in session.");
        }
         User currentUserObj = currentUser.get();
        Optional<GameRequest> optionalGameRequest = gameRequestService.getGameRequestById(gameRequestId);

        if (!optionalGameRequest.isPresent()) {
            throw new ServletException("Game request not found.");
        }

        GameRequest gameRequest = optionalGameRequest.get();
        User whitePlayer;
        User blackPlayer;

        if (gameRequest.getPreferredColor().equals("white")) {
            whitePlayer = gameRequest.getUser();
            blackPlayer = currentUserObj;
        } else if (gameRequest.getPreferredColor().equals("black")) {
            blackPlayer = gameRequest.getUser();
            whitePlayer = currentUserObj;
        } else {
            if (Math.random() < 0.5) {
                whitePlayer = gameRequest.getUser();
                blackPlayer = currentUserObj;
            } else {
                blackPlayer = gameRequest.getUser();
                whitePlayer = currentUserObj;
            }
        }

        ChessGame chessGame = new ChessGame();
        chessGame.setTimeBeginning(new Timestamp(System.currentTimeMillis()));
        chessGame.setGameType(gameRequest.getGameType());
        chessGame.setGameStatus("starting");
        chessGame.setBlackPlayer(blackPlayer);
        chessGame.setWhitePlayer(whitePlayer);
        chessGame.setRating(gameRequest.getIsRating());
        chessGame.setRatingWhite(whitePlayer.getUserDetails().getRatingRapid());
        chessGame.setRatingBlack(blackPlayer.getUserDetails().getRatingRapid());
        chessGame.setTimeRestriction(gameRequest.getTimeRestriction());

        chessGameService.createChessGame(chessGame);

        session.setAttribute("currentGameId", chessGame.getGameId());

        HttpSession requestorSession = SessionManager.getInstance().getSessionByUserId(gameRequest.getUser().getUserCredentials().getId());
        if (requestorSession != null) {
            requestorSession.setAttribute("currentGameId", chessGame.getGameId());
        }

        // Redirect to the game page
        RedirectionManager.getInstance().redirect(new HttpWrapper(request, response), "/game?gameId=" + chessGame.getGameId());
        return RedirectionManager.REDIRECTION;

    }
}

