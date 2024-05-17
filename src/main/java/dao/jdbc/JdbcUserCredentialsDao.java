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
    private static String GET_FULL_CREDENTIALS = "SELECT * FROM user_credentials WHERE email=? AND password=?";

    private static String UPDATE_RATING_BULLET = "UPDATE user_details SET rating_bullet=rating_bullet+? WHERE user_id=?";


    private static final String INSERT_USER_CREDENTIALS = "INSERT INTO user_credentials (nickname, email, password, role) VALUES (?, ?, ?, 'user')";
    private static final String INSERT_USER_DETAILS = "INSERT INTO user_details (user_id) VALUES (?)";

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
    public void create(UserCredentials user) {
        JdbcDaoConnection daoConnection = new JdbcDaoConnection(connection);
        Connection conn = daoConnection.getConnection();

        try {
            /** Виконуємо операції як одну транзакцію, щоб забезпечити атомарність**/
            daoConnection.begin();

            conn.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);

            /** Створюємо рядок в таблиці user_credentials**/
            try (PreparedStatement insertUserCredentialsStmt = conn.prepareStatement(INSERT_USER_CREDENTIALS, Statement.RETURN_GENERATED_KEYS)) {
                insertUserCredentialsStmt.setString(1, user.getNickname());
                insertUserCredentialsStmt.setString(2, user.getEmail());
                insertUserCredentialsStmt.setString(3, user.getPassword());

                insertUserCredentialsStmt.executeUpdate();

                try (ResultSet generatedKeys = insertUserCredentialsStmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        int userId = generatedKeys.getInt(1);

                        /** Створюємо рядок в таблиці user_details**/
                        try (PreparedStatement insertUserDetailsStmt = conn.prepareStatement(INSERT_USER_DETAILS)) {
                            insertUserDetailsStmt.setInt(1, userId);
                            insertUserDetailsStmt.executeUpdate();
                        }
                    } else {
                        throw new SQLException("Creating user failed, no ID obtained.");
                    }
                }
            }

            daoConnection.commit();
        } catch (SQLException e) {
            daoConnection.rollback();
            LOGGER.error("JdbcUserCredentialsDao create SQL exception", e);
            throw new ServerException(e);
        } finally {
            if(connectionShouldBeClosed)daoConnection.close();
        }

    }

    @Override
    public void update(UserCredentials e) {

    }

    @Override
    public void delete(Integer id) {

    }

    @Override
    public void updateBulletRating(int userId, int ratingChange) {
        JdbcDaoConnection daoConnection = new JdbcDaoConnection(connection);
        Connection conn = daoConnection.getConnection();

        try {
            daoConnection.begin();
            /**Встановлення рівню ізольованості Serializable для операції з рейтингом**/
            conn.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);

            try (PreparedStatement updateStatement = conn.prepareStatement(UPDATE_RATING_BULLET)) {
                updateStatement.setInt(1, ratingChange);
                updateStatement.setInt(2, userId);
                updateStatement.executeUpdate();
            }

            daoConnection.commit();
        } catch (SQLException e) {
            daoConnection.rollback();
            LOGGER.error("Error while updating bullet rating for user ID: " + userId, e);
            throw new ServerException(e);
        } finally {
            if (connectionShouldBeClosed) {
                daoConnection.close();
            }
        }
    }


    protected static UserCredentials extractUserCredentialsFromResultSet(ResultSet resultSet) throws SQLException {

        return new UserCredentials.Builder()
                .setId(resultSet.getInt(ID))
                .setNickname(resultSet.getString(NICKNAME))
                .setEmail(resultSet.getString(EMAIL))
                .setRole(resultSet.getString(ROLE)).setPassword(resultSet.getString(PASSWORD)).build();
    }

    @Override
    public Optional<UserCredentials> getFullCredentials(String email, String password) {
        Optional<UserCredentials> user = Optional.empty();
        try (PreparedStatement query = connection.prepareStatement(GET_FULL_CREDENTIALS)) {
            query.setString(1, email);
            query.setString(2, password);
            ResultSet resultSet = query.executeQuery();
            while (resultSet.next()) {
                user = Optional.of(extractUserCredentialsFromResultSet(resultSet));
            }

        } catch (SQLException e) {
            LOGGER.error("JdbcUserDao getFullCredentials SQL exception: " + email, e);
            throw new ServerException(e);
        }
        return user;    }

    @Override
    public void close() {

    }
}
