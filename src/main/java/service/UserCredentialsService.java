package service;

import dao.DaoFactory;
import dao.UserCredentialsDao;
import dto.CredentialsDto;
import entity.UserCredentials;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.List;
import java.util.Optional;

public class UserCredentialsService {

    private static final Logger LOGGER = LogManager.getLogger(UserCredentialsService.class);

    static final String GET_ALL_USERS = "Get all user credentials";
    static final String GET_USER_BY_ID = "Get user credentials by id: %d";
    static final String GET_USER_BY_EMAIL = "Get user credentials by email: %d";

    static final String GET_FULL_CREDENTIALS = "Get full user credentials: %s %s";

    static final String CREATE_USER = "Create user credentials and details: %s";
    static final String UPDATE_USER = "Update user credentials: %d";
    static final String DELETE_USER = "Delete user credentials: %d";

    private final DaoFactory daoFactory;

    UserCredentialsService(DaoFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    private static class Holder {
        static final UserCredentialsService INSTANCE = new UserCredentialsService(DaoFactory.getDaoFactory());
    }

    public static UserCredentialsService getInstance() {
        return Holder.INSTANCE;
    }

    public List<UserCredentials> getAllUsers() {
        LOGGER.info(GET_ALL_USERS);
        try (UserCredentialsDao userDao = daoFactory.createUserCredentialsDao()) {
            return userDao.getAll();
        }
    }

    public Optional<UserCredentials> getUserById(Integer userId) {
        LOGGER.info(String.format(GET_USER_BY_ID, userId));
        try (UserCredentialsDao userDao = daoFactory.createUserCredentialsDao()) {
            return userDao.getById(userId);
        }
    }

    /**public Optional<UserCredentials> getUserByEmail(String email) {
        LOGGER.info(String.format(GET_USER_BY_EMAIL, email));
        try (UserCredentialsDao userDao = daoFactory.createUserCredentialsDao()) {
          //  return userDao.getById(email);
        }
    }**/

    public void createUser(UserCredentials userCredentials) {
        LOGGER.info(String.format(CREATE_USER, userCredentials.getEmail()));

        try (UserCredentialsDao userDao = daoFactory.createUserCredentialsDao()) {
            userDao.create(userCredentials);
        }
    }

    public Optional<UserCredentials> getFullCredentials(CredentialsDto credentials) {
        LOGGER.info(String.format(GET_FULL_CREDENTIALS, credentials.getEmail(),credentials.getPassword()));
        try (UserCredentialsDao userDao = daoFactory.createUserCredentialsDao()) {
            return userDao.getFullCredentials(credentials.getEmail(), credentials.getPassword());
        }
    }


}
