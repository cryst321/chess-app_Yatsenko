package service;

import dao.DaoFactory;
import dao.UserDao;
import dao.UserDetailsDao;
import dto.CredentialsDto;
import entity.User;
import entity.UserCredentials;
import entity.UserDetails;
import exception.ServiceException;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.apache.log4j.Logger;
import java.util.List;
import java.util.Optional;

public class UserService {

    private static final Logger LOGGER = Logger.getLogger(UserService.class);

    public UserService(DaoFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    private final DaoFactory daoFactory;


    private static class Holder {
        static final UserService INSTANCE = new UserService(DaoFactory.getDaoFactory());
    }

    public static UserService getInstance() {
        return UserService.Holder.INSTANCE;
    }


    public List<User> getAllUsers() {
        LOGGER.info("Get all users");
        try (UserDao userDao = daoFactory.createUserDao()){
            return userDao.getAll();
        } catch (Exception e) {
            LOGGER.error("Error getting all users", e);
            throw new ServiceException("Error getting all users", e);
        }
    }

    public Optional<User> getUserById(Integer userId) {
        LOGGER.info(String.format("Get user by id: %d", userId));
        try (UserDao userDao = daoFactory.createUserDao()){
            return userDao.getById(userId);
        } catch (Exception e) {
            LOGGER.error(String.format("Error getting user by id: %d", userId), e);
            throw new ServiceException(String.format("Error getting user by id: %d", userId), e);
        }
    }

    public void createUser(User user) {
        LOGGER.info(String.format("Create user: %s", user.getUserCredentials().getEmail()));
        try (UserDao userDao = daoFactory.createUserDao()){
            userDao.create(user);
        } catch (Exception e) {
            LOGGER.error("Error creating user", e);
            throw new ServiceException("Error creating user", e);
        }
    }

    public void updateUser(User user) {
        LOGGER.info(String.format("Update user: %s", user.getUserCredentials().getEmail()));
        try (UserDao userDao = daoFactory.createUserDao()){
            userDao.update(user);
        } catch (Exception e) {
            LOGGER.error("Error updating user", e);
            throw new ServiceException("Error updating user", e);
        }
    }

    public void deleteUser(Integer userId) {
        LOGGER.info(String.format("Delete user by id: %d", userId));
        try (UserDao userDao = daoFactory.createUserDao()){
            userDao.delete(userId);
        } catch (Exception e) {
            LOGGER.error(String.format("Error deleting user by id: %d", userId), e);
            throw new ServiceException(String.format("Error deleting user by id: %d", userId), e);
        }
    }
}
