package dao.jdbc;

import dao.ChatMessageDao;
import entity.ChatMessage;
import entity.User;
import entity.UserDetails;
import exception.ServerException;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JdbcChatMessageDao implements ChatMessageDao {

    private static final Logger LOGGER = LogManager.getLogger(JdbcUserCredentialsDao.class);

    private final static String GET_ALL = "SELECT * FROM chat_message ORDER BY message_id";
    private final static String GET_BY_ID = "SELECT * FROM chat_message WHERE message_id=?";

    private final static String UPDATE_MESSAGE = "UPDATE chat_message SET sender_id=?, game_id=?, content=?, timestamp=? WHERE message_id=?";

    private final static String DELETE_MESSAGE = "DELETE FROM chat_message WHERE message_id=?";

    private static final String INSERT_MESSAGE = "INSERT INTO chat_message (sender_id, game_id, content, timestamp) VALUES (?, ?, ?, ?)";


    /**Всі повідомлення з однієї гри*/
    private static String GET_ALL_FROM_GAME = "SELECT * FROM chat_message WHERE game_id=? ORDER BY timestamp";

    private static String ID = "message_id";
    private static String SENDER_ID = "sender_id";
    private static String GAME_ID = "game_id";
    private static String CONTENT = "content";
    private static String TIMESTAMP = "timestamp";


    private Connection connection;
    private boolean connectionShouldBeClosed;

    public JdbcChatMessageDao(Connection connection) {
        this.connection = connection;
        connectionShouldBeClosed = false;
    }


    public JdbcChatMessageDao(Connection connection, boolean connectionShouldBeClosed) {
        this.connection = connection;
        this.connectionShouldBeClosed = connectionShouldBeClosed;
    }
    public void setConnection(Connection connection) {
        this.connection = connection;
    }



    @Override
    public void close() {
        if (connectionShouldBeClosed) {
            try {
                connection.close();
            } catch (SQLException e) {
                LOGGER.error("JdbcChatMessageDao close SQL exception", e);
                throw new ServerException(e);
            }
        }

    }

    @Override
    public List<ChatMessage> getAll() {
        List<ChatMessage> chatMessages = new ArrayList<>();
        try (Statement query = connection.createStatement(); ResultSet resultSet = query.executeQuery(GET_ALL)) {
            while (resultSet.next()) {
                chatMessages.add(extractChatMessageFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            LOGGER.error("JdbcChatMessageDao getAll SQL exception", e);
            throw new ServerException(e);
        }
        return chatMessages;
    }


    @Override
    public List<ChatMessage> getAllFromGame(Integer gameId) {
        List<ChatMessage> chatMessages = new ArrayList<>();
        try (PreparedStatement query = connection.prepareStatement(GET_ALL_FROM_GAME)) {
            query.setInt(1, gameId);
            ResultSet resultSet = query.executeQuery();
            while (resultSet.next()) {
                chatMessages.add(extractChatMessageFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            LOGGER.error("JdbcChatMessageDao getAllFromGame SQL exception", e);
            throw new ServerException(e);
        }
        return chatMessages;
    }

    @Override
    public Optional<ChatMessage> getById(Integer id) {
        Optional<ChatMessage> chatMessage = Optional.empty();
        try (PreparedStatement query = connection.prepareStatement(GET_BY_ID)) {
            query.setInt(1, id);
            ResultSet resultSet = query.executeQuery();
            if (resultSet.next()) {
                chatMessage = Optional.of(extractChatMessageFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            LOGGER.error("JdbcChatMessageDao getById SQL exception: " + id, e);
            throw new ServerException(e);
        }
        return chatMessage;
    }

    @Override
    public void create(ChatMessage chatMessage) {
        try (PreparedStatement statement = connection.prepareStatement(INSERT_MESSAGE)) {
            statement.setInt(1, chatMessage.getSender().getId());
            statement.setInt(2, chatMessage.getGameId());
            statement.setString(3, chatMessage.getContent());
            statement.setTimestamp(4, chatMessage.getTimestamp());

            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Creating chat message failed, no rows affected.");
            }
        } catch (SQLException e) {
            LOGGER.error("Error while creating chat message", e);
            throw new ServerException(e);
        }
    }

    @Override
    public void update(ChatMessage chatMessage) {
        try (PreparedStatement statement = connection.prepareStatement(UPDATE_MESSAGE)) {
            statement.setInt(1, chatMessage.getSender().getId());
            statement.setInt(2, chatMessage.getGameId());
            statement.setString(3, chatMessage.getContent());
            statement.setTimestamp(4, chatMessage.getTimestamp());
            statement.setInt(5, chatMessage.getMessageId());

            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Updating chat message failed, no rows affected.");
            }
        } catch (SQLException e) {
            LOGGER.error("Error while updating chat message", e);
            throw new ServerException(e);
        }
    }

    @Override
    public void delete(Integer id) {
        try (PreparedStatement statement = connection.prepareStatement(DELETE_MESSAGE)) {
            statement.setInt(1, id);
            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Deleting chat message failed, no rows affected.");
            }
        } catch (SQLException e) {
            LOGGER.error("Error while deleting chat message", e);
            throw new ServerException(e);
        }
    }

    private ChatMessage extractChatMessageFromResultSet(ResultSet resultSet) throws SQLException {
        Integer messageId = resultSet.getInt(ID);
        Integer senderId = resultSet.getInt(SENDER_ID);
        Integer gameId = resultSet.getInt(GAME_ID);
        String content = resultSet.getString(CONTENT);
        Timestamp timestamp = resultSet.getTimestamp(TIMESTAMP);
        ChatMessage chatMessage = new ChatMessage();


        JdbcUserDao userDao = new JdbcUserDao(connection);
        Optional<User> user = userDao.getById(senderId);
        user.ifPresent(chatMessage::setSender);

        chatMessage.setMessageId(messageId);
        chatMessage.setGameId(gameId);
        chatMessage.setContent(content);
        chatMessage.setTimestamp(timestamp);

        return chatMessage;
    }

}
