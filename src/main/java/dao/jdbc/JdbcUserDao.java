package dao.jdbc;

import dao.UserDao;
import entity.User;
import entity.UserCredentials;
import entity.UserDetails;
import exception.ServerException;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JdbcUserDao implements UserDao {

    private static final Logger LOGGER = LogManager.getLogger(JdbcUserDao.class);

    private JdbcUserCredentialsDao userCredentialsDao;
    private JdbcUserDetailsDao userDetailsDao;

    private Connection connection;
    private boolean connectionShouldBeClosed;

    public JdbcUserDao(Connection connection) {
        this.connection = connection;
        this.userCredentialsDao = new JdbcUserCredentialsDao(connection);
        this.userDetailsDao = new JdbcUserDetailsDao(connection);
        connectionShouldBeClosed = false;
    }

    public JdbcUserDao(Connection connection, boolean connectionShouldBeClosed) {
        this.connection = connection;
        this.userCredentialsDao = new JdbcUserCredentialsDao(connection);
        this.userDetailsDao = new JdbcUserDetailsDao(connection);
        this.connectionShouldBeClosed = connectionShouldBeClosed;
    }

    @Override
    public Optional<User> getById(Integer id) {
        try {
            Optional<UserCredentials> userCredentialsOpt = userCredentialsDao.getById(id);
            Optional<UserDetails> userDetailsOpt = userDetailsDao.getById(id);

            if (userCredentialsOpt.isPresent() && userDetailsOpt.isPresent()) {
                User user = new User(userCredentialsOpt.get().getId(), userCredentialsOpt.get(), userDetailsOpt.get());
                return Optional.of(user);
            }
            return Optional.empty();
        } catch (Exception e) {
            LOGGER.error("JdbcUserDao getById SQL exception: " + id, e);
            throw new ServerException(e);
        }
    }
    @Override
    public List<User> getAll() {
        List<User> users = new ArrayList<>();
        try {
            List<UserCredentials> userCredentialsList = userCredentialsDao.getAll();
            for (UserCredentials userCredentials : userCredentialsList) {
                Optional<UserDetails> userDetailsOpt = userDetailsDao.getById(userCredentials.getId());
                if (userDetailsOpt.isPresent()) {
                    User user = new User(userCredentials.getId(),userCredentials, userDetailsOpt.get());
                    users.add(user);
                }
            }
        } catch (Exception e) {
            LOGGER.error("JdbcUserDao getAll SQL exception", e);
            throw new ServerException(e);
        }
        return users;
    }

    @Override
    public void create(User user) {
        JdbcDaoConnection daoConnection = new JdbcDaoConnection(connection);
        Connection conn = daoConnection.getConnection();

        try {
            daoConnection.begin();
            conn.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);

            userCredentialsDao.create(user.getUserCredentials());
            userDetailsDao.create(user.getUserDetails());

            daoConnection.commit();
        } catch (SQLException e) {
            daoConnection.rollback();
            LOGGER.error("JdbcUserDao create SQL exception", e);
            throw new ServerException(e);
        } finally {
            if (connectionShouldBeClosed) {
                daoConnection.close();
            }
        }
    }

    @Override
    public void update(User user) {
        JdbcDaoConnection daoConnection = new JdbcDaoConnection(connection);
        Connection conn = daoConnection.getConnection();

        try {
            daoConnection.begin();
            conn.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);

            userCredentialsDao.update(user.getUserCredentials());
            userDetailsDao.update(user.getUserDetails());

            daoConnection.commit();
        } catch (SQLException e) {
            daoConnection.rollback();
            LOGGER.error("JdbcUserDao update SQL exception", e);
            throw new ServerException(e);
        } finally {
            if (connectionShouldBeClosed) {
                daoConnection.close();
            }
        }
    }

    @Override
    public void delete(Integer id) {
        JdbcDaoConnection daoConnection = new JdbcDaoConnection(connection);
        Connection conn = daoConnection.getConnection();

        try {
            daoConnection.begin();
            conn.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);

            userCredentialsDao.delete(id);
            userDetailsDao.delete(id);

            daoConnection.commit();
        } catch (SQLException e) {
            daoConnection.rollback();
            LOGGER.error("JdbcUserDao delete SQL exception: " + id, e);
            throw new ServerException(e);
        } finally {
            if (connectionShouldBeClosed) {
                daoConnection.close();
            }
        }
    }

    @Override
    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            LOGGER.error("JdbcUserDao close SQL exception", e);
            throw new ServerException(e);
        }
    }
}
