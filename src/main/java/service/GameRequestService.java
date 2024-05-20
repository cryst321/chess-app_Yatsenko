package service;

import dao.DaoFactory;
import dao.GameRequestDao;
import entity.GameRequest;
import org.apache.log4j.Logger;

import java.util.List;
import java.util.Optional;

public class GameRequestService {

    private static final Logger LOGGER = Logger.getLogger(GameRequestService.class);

    private final DaoFactory daoFactory;

     GameRequestService(DaoFactory daoFactory) {
        this.daoFactory = daoFactory;
    }
    private static class Holder {
        static final GameRequestService INSTANCE = new GameRequestService(DaoFactory.getDaoFactory());
    }

    public static GameRequestService getInstance() {
        return GameRequestService.Holder.INSTANCE;
    }


    public List<GameRequest> getAllGameRequests() {
        LOGGER.info("Getting all game requests");
        try (GameRequestDao gameRequestDao = daoFactory.createGameRequestDao()) {
            return gameRequestDao.getAll();
        }
    }

    public Optional<GameRequest> getGameRequestById(Integer id) {
        LOGGER.info("Getting game request by id: " + id);
        try (GameRequestDao gameRequestDao = daoFactory.createGameRequestDao()) {
            return gameRequestDao.getById(id);
        }
    }

    public void createGameRequest(GameRequest gameRequest) {
        LOGGER.info("Creating game request for user: " + gameRequest.getUser().getUserCredentials().getNickname());
        try (GameRequestDao gameRequestDao = daoFactory.createGameRequestDao()) {
            gameRequestDao.create(gameRequest);
        }
    }

    public void updateGameRequest(GameRequest gameRequest) {
        LOGGER.info("Updating game request with id: " + gameRequest.getRequestId());
        try (GameRequestDao gameRequestDao = daoFactory.createGameRequestDao()) {
            gameRequestDao.update(gameRequest);
        }
    }

    public void deleteGameRequest(Integer id) {
        LOGGER.info("Deleting game request with id: " + id);
        try (GameRequestDao gameRequestDao = daoFactory.createGameRequestDao()) {
            gameRequestDao.delete(id);
        }
    }
}
