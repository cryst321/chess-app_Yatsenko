package dao.jdbc;

import dao.ChessGameDao;
import entity.ChessGame;
import entity.User;
import exception.ServerException;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JdbcChessGameDao implements ChessGameDao {

    private static final Logger LOGGER = LogManager.getLogger(JdbcChessGameDao.class);

    private static final String GET_ALL = "SELECT * FROM chess_game ORDER BY game_id";
    private static final String GET_BY_ID = "SELECT * FROM chess_game WHERE game_id=?";
    private static final String INSERT_CHESS_GAME = "INSERT INTO chess_game (time_beginning, time_end, game_type, game_status, black_user_id, white_user_id, winner_id, moves, is_rating, rating_white, rating_black, time_restriction) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String UPDATE_CHESS_GAME = "UPDATE chess_game SET time_beginning=?, time_end=?, game_type=?, game_status=?, black_user_id=?, white_user_id=?, winner_id=?, moves=?, is_rating=?, rating_white=?, rating_black=?, time_restriction=? WHERE game_id=?";
    private static final String DELETE_CHESS_GAME = "DELETE FROM chess_game WHERE game_id=?";

    private Connection connection;
    private boolean connectionShouldBeClosed;

    public JdbcChessGameDao(Connection connection) {
        this.connection = connection;
        connectionShouldBeClosed = false;
    }

    public JdbcChessGameDao(Connection connection, boolean connectionShouldBeClosed) {
        this.connection = connection;
        this.connectionShouldBeClosed = connectionShouldBeClosed;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<ChessGame> getAll() {
        List<ChessGame> chessGames = new ArrayList<>();

        try (Statement query = connection.createStatement(); ResultSet resultSet = query.executeQuery(GET_ALL)) {
            while (resultSet.next()) {
                chessGames.add(extractChessGameFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            LOGGER.error("JdbcChessGameDao getAll SQL exception", e);
            throw new ServerException(e);
        }
        return chessGames;
    }

    @Override
    public Optional<ChessGame> getById(Integer id) {
        Optional<ChessGame> chessGame = Optional.empty();
        try (PreparedStatement query = connection.prepareStatement(GET_BY_ID)) {
            query.setInt(1, id);
            ResultSet resultSet = query.executeQuery();
            if (resultSet.next()) {
                chessGame = Optional.of(extractChessGameFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            LOGGER.error("JdbcChessGameDao getById SQL exception: " + id, e);
            throw new ServerException(e);
        }
        return chessGame;
    }

    @Override
    public void create(ChessGame chessGame) {
        try (PreparedStatement query = connection.prepareStatement(INSERT_CHESS_GAME, Statement.RETURN_GENERATED_KEYS)) {
            prepareStatementForChessGame(query, chessGame);
            query.executeUpdate();

            try (ResultSet generatedKeys = query.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    chessGame.setGameId(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("Creating chess game failed, no ID obtained.");
                }
            }
        } catch (SQLException e) {
            LOGGER.error("JdbcChessGameDao create SQL exception", e);
            throw new ServerException(e);
        }
    }

    @Override
    public void update(ChessGame chessGame) {
        try (PreparedStatement query = connection.prepareStatement(UPDATE_CHESS_GAME)) {
            prepareStatementForChessGame(query, chessGame);
            query.setInt(13, chessGame.getGameId());
            query.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("JdbcChessGameDao update SQL exception", e);
            throw new ServerException(e);
        }
    }

    @Override
    public void delete(Integer id) {
        try (PreparedStatement query = connection.prepareStatement(DELETE_CHESS_GAME)) {
            query.setInt(1, id);
            query.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("JdbcChessGameDao delete SQL exception", e);
            throw new ServerException(e);
        }
    }

    @Override
    public void close() {
        if (connectionShouldBeClosed) {
            try {
                connection.close();
            } catch (SQLException e) {
                LOGGER.error("JdbcChessGameDao close SQL exception", e);
                throw new ServerException(e);
            }
        }
    }

    private ChessGame extractChessGameFromResultSet(ResultSet resultSet) throws SQLException {
        ChessGame chessGame = new ChessGame();
        chessGame.setGameId(resultSet.getInt("game_id"));
        chessGame.setTimeBeginning(resultSet.getTimestamp("time_beginning"));
        chessGame.setTimeEnd(resultSet.getTimestamp("time_end"));
        chessGame.setGameType(resultSet.getString("game_type"));
        chessGame.setGameStatus(resultSet.getString("game_status"));
        JdbcUserDao userDao = new JdbcUserDao(connection);
        chessGame.setBlackPlayer(userDao.getById(resultSet.getInt("black_user_id")).orElse(null));
        chessGame.setWhitePlayer(userDao.getById(resultSet.getInt("white_user_id")).orElse(null));
        chessGame.setWinner(userDao.getById(resultSet.getInt("winner_id")).orElse(null));
        chessGame.setMoves((String[]) resultSet.getArray("moves").getArray());
        chessGame.setRating(resultSet.getBoolean("is_rating"));
        chessGame.setRatingWhite(resultSet.getInt("rating_white"));
        chessGame.setRatingBlack(resultSet.getInt("rating_black"));
        chessGame.setTimeRestriction(resultSet.getInt("time_restriction"));
        return chessGame;
    }

    private void prepareStatementForChessGame(PreparedStatement query, ChessGame chessGame) throws SQLException {
        query.setTimestamp(1, chessGame.getTimeBeginning());
        query.setTimestamp(2, chessGame.getTimeEnd());
        query.setString(3, chessGame.getGameType());
        query.setString(4, chessGame.getGameStatus());
        query.setInt(5, chessGame.getBlackPlayer().getId());
        query.setInt(6, chessGame.getWhitePlayer().getId());
        query.setInt(7, chessGame.getWinner() != null ? chessGame.getWinner().getId() : null);
        query.setArray(8, connection.createArrayOf("VARCHAR", chessGame.getMoves()));
        query.setBoolean(9, chessGame.isRating());
        query.setInt(10, chessGame.getRatingWhite());
        query.setInt(11, chessGame.getRatingBlack());
        query.setInt(12, chessGame.getTimeRestriction());
    }
}
