package service;

import dao.DaoFactory;
import dao.UserCredentialsDao;
import dao.UserDetailsDao;
import dto.CredentialsDto;
import entity.UserCredentials;
import entity.UserDetails;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.List;
import java.util.Optional;

public class UserDetailsService {

    private static final Logger LOGGER = LogManager.getLogger(UserCredentialsService.class);

    static final String GET_ALL_USERS = "Get all user details";
    static final String GET_USER_BY_ID = "Get user details by id: %d";

    static final String CREATE_USER = "Create user details: %d";
    static final String UPDATE_USER = "Update user details: %d";
    static final String DELETE_USER = "Delete user details: %d";

    static final String UPDATE_USER_RATING = "Update user with id %d rating %d type %d";


    private final DaoFactory daoFactory;

    UserDetailsService(DaoFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    private static class Holder {
        static final UserDetailsService INSTANCE = new UserDetailsService(DaoFactory.getDaoFactory());
    }

    public static UserDetailsService getInstance() {
        return UserDetailsService.Holder.INSTANCE;
    }

    public List<UserDetails> getAllUsers() {
        LOGGER.info(GET_ALL_USERS);
        try (UserDetailsDao userDao = daoFactory.createUserDetailsDao()) {
            return userDao.getAll();
        }
    }

    public Optional<UserDetails> getUserById(Integer userId) {
        LOGGER.info(String.format(GET_USER_BY_ID, userId));
        try (UserDetailsDao userDao = daoFactory.createUserDetailsDao()) {
            return userDao.getById(userId);
        }
    }


    public void createUser(UserDetails userDetails) {
        LOGGER.info(String.format(CREATE_USER, userDetails.getUser_id()));

        try (UserDetailsDao userDao = daoFactory.createUserDetailsDao()) {
            userDao.create(userDetails);
        }
    }

    public void updateUser(UserDetails userDetails) {
        LOGGER.info(String.format(UPDATE_USER, userDetails.getUser_id()));

        try (UserDetailsDao userDao = daoFactory.createUserDetailsDao()) {
            userDao.update(userDetails);
        }
    }

    public void deleteUser(Integer userId) {
        LOGGER.info(String.format(DELETE_USER,userId));
        try (UserDetailsDao userDao = daoFactory.createUserDetailsDao()) {
            userDao.delete(userId);
        }
    }

    public void updateRating(Integer userId, Integer rating, Short ratingType) {
        LOGGER.info(String.format(UPDATE_USER_RATING,userId, rating, ratingType));
        try (UserDetailsDao userDao = daoFactory.createUserDetailsDao()) {
            userDao.updateRating(userId, rating, ratingType);
        }
    }


    }


