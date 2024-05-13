package dao.jdbc;

import dao.UserCredentialsDao;
import entity.UserCredentials;
import exception.ServerException;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JdbcUserCredentialsDao implements UserCredentialsDao {

    private static final Logger LOGGER = LogManager.getLogger(JdbcUserCredentialsDao.class);

    private static String GET_ALL = "SELECT * FROM user_credentials ORDER BY id";
    private static String GET_BY_ID = "SELECT * FROM user_credentials WHERE id=?";


    // table columns names
    private static String ID = "id";
    private static String NICKNAME = "nickname";
    private static String EMAIL = "email";
    private static String PASSWORD = "password";
    private static String ROLE = "role";


    private Connection connection;
    private boolean connectionShouldBeClosed;

    public JdbcUserCredentialsDao(Connection connection) {
        this.connection = connection;
        connectionShouldBeClosed = false;
    }


    public JdbcUserCredentialsDao(Connection connection, boolean connectionShouldBeClosed) {
        this.connection = connection;
        this.connectionShouldBeClosed = connectionShouldBeClosed;
    }
    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<UserCredentials> getAll() {
        List<UserCredentials> users = new ArrayList<>();

        try (Statement query = connection.createStatement(); ResultSet resultSet = query.executeQuery(GET_ALL)) {
            while (resultSet.next()) {
                users.add(extractUserCredentialsFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            LOGGER.error("JdbcUserCredentialsDao getAll SQL exception", e);
            throw new ServerException(e);
        }
        LOGGER.info("users got: " + users.get(0));
        return users;    }

    @Override
    public Optional<UserCredentials> getById(Integer id) {
        Optional<UserCredentials> user = Optional.empty();
        try (PreparedStatement query = connection.prepareStatement(GET_BY_ID)) {
            query.setInt(1, id);
            ResultSet resultSet = query.executeQuery();
            while (resultSet.next()) {
                user = Optional.of(extractUserCredentialsFromResultSet(resultSet));
            }

        } catch (SQLException e) {
            LOGGER.error("JdbcUserCredentialsDao getById SQL exception: " + id, e);
            throw new ServerException(e);
        }
        return user;
    }

    @Override
    public void create(UserCredentials e) {

    }

    @Override
    public void update(UserCredentials e) {

    }

    @Override
    public void delete(Integer id) {

    }

    protected static UserCredentials extractUserCredentialsFromResultSet(ResultSet resultSet) throws SQLException {

        return new UserCredentials.Builder()
                .setId(resultSet.getInt(ID))
                .setNickname(resultSet.getString(NICKNAME))
                .setEmail(resultSet.getString(EMAIL))
                .setRole(resultSet.getString(ROLE)).setPassword(resultSet.getString(PASSWORD)).build();
    }
    @Override
    public void close() {

    }
}
