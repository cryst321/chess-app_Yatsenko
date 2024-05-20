package service;

import dao.ChessGameDao;
import dao.DaoFactory;
import dao.GameRequestDao;
import entity.ChessGame;
import entity.GameRequest;
import org.apache.log4j.Logger;

import java.util.List;
import java.util.Optional;

public class ChessGameService {

    private static final Logger LOGGER = Logger.getLogger(ChessGameService.class);

    private final DaoFactory daoFactory;

    ChessGameService(DaoFactory daoFactory) {
        this.daoFactory = daoFactory;
    }
    private static class Holder {
        static final ChessGameService INSTANCE = new ChessGameService(DaoFactory.getDaoFactory());
    }

    public static ChessGameService getInstance() {
        return ChessGameService.Holder.INSTANCE;
    }


    public List<ChessGame> getAllChessGames() {
        LOGGER.info("Getting all chess games");
        try (ChessGameDao chessGameDao = daoFactory.createChessGameDao()) {
            return chessGameDao.getAll();
        }
    }

    public Optional<ChessGame> getChessGameById(Integer id) {
        LOGGER.info("Getting chess game by id: " + id);
        try (ChessGameDao chessGameDao = daoFactory.createChessGameDao()) {
            return chessGameDao.getById(id);
        }
    }

    public void createChessGame(ChessGame chessGame) {
        LOGGER.info("Creating chess game for users " + chessGame.getWhitePlayer().getId() + " and " + chessGame.getBlackPlayer().getId());
        try (ChessGameDao chessGameDao = daoFactory.createChessGameDao()) {
            chessGameDao.create(chessGame);
        }
    }

    public void updateChessGame(ChessGame chessGame) {
        LOGGER.info("Updating game  with id: " + chessGame.getGameId());
        try (ChessGameDao chessGameDao = daoFactory.createChessGameDao()) {
            chessGameDao.update(chessGame);
        }
    }

    public void deleteChessGame(Integer id) {
        LOGGER.info("Deleting game with id: " + id);
        try (ChessGameDao chessGameDao = daoFactory.createChessGameDao()) {
            chessGameDao.delete(id);
        }
    }
}
