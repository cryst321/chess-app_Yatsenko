package dao.jdbc;

import dao.*;
import exception.ServerException;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class JdbcDaoFactory extends DaoFactory {

    private static final Logger LOGGER = LogManager.getLogger(JdbcDaoFactory.class);

    private DataSource dataSource;

    /**
     * Get DataSource implementation from Initial Context by means of JNDI mechanism
     */
    public JdbcDaoFactory() {
        try {
            InitialContext ic = new InitialContext();
            dataSource = (DataSource) ic.lookup("java:comp/env/jdbc/chess");

        } catch (Exception e) {
            LOGGER.error("Can't load pool connection from Initial Context", e);
            throw new ServerException(e);
        }
    }

    /**
     * Get custom Connection wrapper for providing transaction execution
     *
     * @return a connection to the data source
     * @throws ServerException if a database access error occurs
     */
    @Override
    public DaoConnection getConnection() {
        try {
            return new JdbcDaoConnection(dataSource.getConnection());
        } catch (SQLException e) {
            LOGGER.error("Can't get DB connection to the data source", e);
            throw new ServerException(e);
        }
    }

    @Override
    public UserCredentialsDao createUserCredentialsDao() {
        try {
            return new JdbcUserCredentialsDao(dataSource.getConnection(), true);
        } catch (SQLException e) {
            LOGGER.error("Can't get DB Connection for JdbcUserCredentialsDao creation", e);
            throw new ServerException(e);
        }
    }

    @Override
    public UserCredentialsDao createUserCredentialsDao(DaoConnection connection) {
        JdbcDaoConnection jdbcConnection = (JdbcDaoConnection) connection;
        Connection sqlConnection = jdbcConnection.getConnection();
        return new JdbcUserCredentialsDao(sqlConnection);
    }

    @Override
    public UserDetailsDao createUserDetailsDao() {
        try {
            return new JdbcUserDetailsDao(dataSource.getConnection(), true);
        } catch (SQLException e) {
            LOGGER.error("Can't get DB Connection for JdbcUserDetailsDao creation", e);
            throw new ServerException(e);
        }    }

    @Override
    public UserDetailsDao createUserDetailsDao(DaoConnection connection) {
        JdbcDaoConnection jdbcConnection = (JdbcDaoConnection) connection;
        Connection sqlConnection = jdbcConnection.getConnection();
        return new JdbcUserDetailsDao(sqlConnection);    }

    @Override
    public UserDao createUserDao() {
        try {
            return new JdbcUserDao(dataSource.getConnection(), true);
        } catch (SQLException e) {
            LOGGER.error("Can't get DB Connection for JdbcUserDao creation", e);
            throw new ServerException(e);
        }    }

    @Override
    public UserDao createUserDao(DaoConnection connection) {
        JdbcDaoConnection jdbcConnection = (JdbcDaoConnection) connection;
        Connection sqlConnection = jdbcConnection.getConnection();
        return new JdbcUserDao(sqlConnection);    }

    @Override
    public ComplaintDao createComplaintDao() {
        try {
            return new JdbcComplaintDao(dataSource.getConnection(), true);
        } catch (SQLException e) {
            LOGGER.error("Can't get DB Connection for JdbcComplaintDao creation", e);
            throw new ServerException(e);
        }        }

    @Override
    public ComplaintDao createComplaintDao(DaoConnection connection) {
        JdbcDaoConnection jdbcConnection = (JdbcDaoConnection) connection;
        Connection sqlConnection = jdbcConnection.getConnection();
        return new JdbcComplaintDao(sqlConnection);      }

    @Override
    public GameRequestDao createGameRequestDao() {
        try {
            return new JdbcGameRequestDao(dataSource.getConnection(), true);
        } catch (SQLException e) {
            LOGGER.error("Can't get DB Connection for JdbcGameRequestDao creation", e);
            throw new ServerException(e);
        }        }

    @Override
    public GameRequestDao createGameRequestDao(DaoConnection connection) {
        JdbcDaoConnection jdbcConnection = (JdbcDaoConnection) connection;
        Connection sqlConnection = jdbcConnection.getConnection();
        return new JdbcGameRequestDao(sqlConnection);        }

    @Override
    public ChessGameDao createChessGameDao() {
        try {
            return new JdbcChessGameDao(dataSource.getConnection(), true);
        } catch (SQLException e) {
            LOGGER.error("Can't get DB Connection for JdbcChessGameDao creation", e);
            throw new ServerException(e);
        }        }

    @Override
    public ChessGameDao createChessGameDao(DaoConnection connection) {
        JdbcDaoConnection jdbcConnection = (JdbcDaoConnection) connection;
        Connection sqlConnection = jdbcConnection.getConnection();
        return new JdbcChessGameDao(sqlConnection);        }

    @Override
    public ChatMessageDao createChatMessageDao() {
        try {
            return new JdbcChatMessageDao(dataSource.getConnection(), true);
        } catch (SQLException e) {
            LOGGER.error("Can't get DB Connection for JdbcChatMessageDao creation", e);
            throw new ServerException(e);
        }        }

    @Override
    public ChatMessageDao createChatMessageDao(DaoConnection connection) {
        JdbcDaoConnection jdbcConnection = (JdbcDaoConnection) connection;
        Connection sqlConnection = jdbcConnection.getConnection();
        return new JdbcChatMessageDao(sqlConnection);        }
}
