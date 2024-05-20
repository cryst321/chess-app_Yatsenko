package dao;

import entity.UserCredentials;
import exception.ServerException;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public abstract class DaoFactory {

    private static final Logger LOGGER = LogManager.getLogger(DaoFactory.class);

    public static final String DB_FILE = "/db.properties";
    private static final String DB_FACTORY_CLASS = "factory.class";

    private static DaoFactory daoFactory;

    public abstract DaoConnection getConnection();

    // class level dao
    public abstract UserCredentialsDao createUserCredentialsDao();
    // business level dao
    public abstract UserCredentialsDao createUserCredentialsDao(DaoConnection connection);


    public abstract UserDetailsDao createUserDetailsDao();
    public abstract UserDetailsDao createUserDetailsDao(DaoConnection connection);

    public abstract UserDao createUserDao();
    public abstract UserDao createUserDao(DaoConnection connection);

    public abstract ComplaintDao createComplaintDao();
    public abstract ComplaintDao createComplaintDao(DaoConnection connection);

    public abstract GameRequestDao createGameRequestDao();
    public abstract GameRequestDao createGameRequestDao(DaoConnection connection);

    public abstract ChessGameDao createChessGameDao();
    public abstract ChessGameDao createChessGameDao(DaoConnection connection);

    public abstract ChatMessageDao createChatMessageDao();
    public abstract ChatMessageDao createChatMessageDao(DaoConnection connection);




    public static DaoFactory getDaoFactory() {
        if (daoFactory == null) {
            try {
                InputStream inputStream = DaoFactory.class.getResourceAsStream(DB_FILE);
                Properties dbProps = new Properties();
                dbProps.load(inputStream);
                String factoryClass = dbProps.getProperty(DB_FACTORY_CLASS);
                daoFactory = (DaoFactory) Class.forName(factoryClass).newInstance();

            } catch (IOException | IllegalAccessException | InstantiationException | ClassNotFoundException e) {
                LOGGER.error("Can't load inputStream db config file to properties object", e);
                throw new ServerException(e);
            }
        }
        return daoFactory;
    }
}
