package dao.jdbc;

import dao.GameRequestDao;
import entity.GameRequest;
import entity.User;
import entity.UserCredentials;
import exception.ServerException;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JdbcGameRequestDao implements GameRequestDao {

    private static final Logger LOGGER = LogManager.getLogger(JdbcGameRequestDao.class);

    private static final String GET_ALL = "SELECT * FROM game_request ORDER BY request_id";
    private static final String GET_BY_ID = "SELECT * FROM game_request WHERE request_id=?";
    private static final String INSERT_GAME_REQUEST = "INSERT INTO game_request (user_id, game_type, rating_less, rating_more, time_restriction, preferred_color, is_rating) VALUES (?, ?, ?, ?, ?, ?, ?)";
    private static final String UPDATE_GAME_REQUEST = "UPDATE game_request SET user_id=?, game_type=?, rating_less=?, rating_more=?, time_restriction=?, preferred_color=?, is_rating=? WHERE request_id=?";
    private static final String DELETE_GAME_REQUEST = "DELETE FROM game_request WHERE request_id=?";

    private Connection connection;
    private boolean connectionShouldBeClosed;

    public JdbcGameRequestDao(Connection connection) {
        this.connection = connection;
        connectionShouldBeClosed = false;
    }

    public JdbcGameRequestDao(Connection connection, boolean connectionShouldBeClosed) {
        this.connection = connection;
        this.connectionShouldBeClosed = connectionShouldBeClosed;
    }

    @Override
    public List<GameRequest> getAll() {
        List<GameRequest> gameRequests = new ArrayList<>();
        try (Statement query = connection.createStatement();
             ResultSet resultSet = query.executeQuery(GET_ALL)) {
            while (resultSet.next()) {
                gameRequests.add(extractGameRequestFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            LOGGER.error("JdbcGameRequestDao getAll SQL exception", e);
            throw new ServerException(e);
        }
        return gameRequests;
    }

    @Override
    public Optional<GameRequest> getById(Integer id) {
        Optional<GameRequest> gameRequest = Optional.empty();
        try (PreparedStatement query = connection.prepareStatement(GET_BY_ID)) {
            query.setInt(1, id);
            ResultSet resultSet = query.executeQuery();
            while (resultSet.next()) {
                gameRequest = Optional.of(extractGameRequestFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            LOGGER.error("JdbcGameRequestDao getById SQL exception: " + id, e);
            throw new ServerException(e);
        }
        return gameRequest;
    }

    @Override
    public void create(GameRequest gameRequest) {
        try (PreparedStatement insertStatement = connection.prepareStatement(INSERT_GAME_REQUEST, Statement.RETURN_GENERATED_KEYS)) {
            insertStatement.setInt(1, gameRequest.getUser().getUserCredentials().getId());
            insertStatement.setString(2, gameRequest.getGameType());
            insertStatement.setInt(3, gameRequest.getRatingLess());
            insertStatement.setInt(4, gameRequest.getRatingMore());
            insertStatement.setInt(5, gameRequest.getTimeRestriction());
            insertStatement.setString(6, gameRequest.getPreferredColor());
            insertStatement.setBoolean(7, gameRequest.getIsRating());

            insertStatement.executeUpdate();

            try (ResultSet generatedKeys = insertStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    gameRequest.setRequestId(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("Creating game request failed, no ID obtained.");
                }
            }
        } catch (SQLException e) {
            LOGGER.error("JdbcGameRequestDao create SQL exception", e);
            throw new ServerException(e);
        }
    }

    @Override
    public void update(GameRequest gameRequest) {
        try (PreparedStatement updateStatement = connection.prepareStatement(UPDATE_GAME_REQUEST)) {
            updateStatement.setInt(1, gameRequest.getUser().getUserCredentials().getId());
            updateStatement.setString(2, gameRequest.getGameType());
            updateStatement.setInt(3, gameRequest.getRatingLess());
            updateStatement.setInt(4, gameRequest.getRatingMore());
            updateStatement.setInt(5, gameRequest.getTimeRestriction());
            updateStatement.setString(6, gameRequest.getPreferredColor());
            updateStatement.setBoolean(7, gameRequest.getIsRating());
            updateStatement.setInt(8, gameRequest.getRequestId());

            updateStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("JdbcGameRequestDao update SQL exception", e);
            throw new ServerException(e);
        }
    }

    @Override
    public void delete(Integer id) {
        try (PreparedStatement deleteStatement = connection.prepareStatement(DELETE_GAME_REQUEST)) {
            deleteStatement.setInt(1, id);
            deleteStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("JdbcGameRequestDao delete SQL exception: " + id, e);
            throw new ServerException(e);
        }
    }

    @Override
    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            LOGGER.error("JdbcGameRequestDao close SQL exception", e);
            throw new ServerException(e);
        }
    }

    private GameRequest extractGameRequestFromResultSet(ResultSet resultSet) throws SQLException {
        GameRequest gameRequest = new GameRequest();
        gameRequest.setRequestId(resultSet.getInt("request_id"));

        JdbcUserDao userDao = new JdbcUserDao(connection);
        Optional<User> user = userDao.getById(resultSet.getInt("user_id"));
        user.ifPresent(gameRequest::setUser);

        gameRequest.setGameType(resultSet.getString("game_type"));
        gameRequest.setRatingLess(resultSet.getInt("rating_less"));
        gameRequest.setRatingMore(resultSet.getInt("rating_more"));
        gameRequest.setTimeRestriction(resultSet.getInt("time_restriction"));
        gameRequest.setPreferredColor(resultSet.getString("preferred_color"));
        gameRequest.setIsRating(resultSet.getBoolean("is_rating"));

        return gameRequest;
    }
}
