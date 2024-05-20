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
}
