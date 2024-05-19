package dao;

import entity.User;
import entity.UserCredentials;

public interface UserDao extends GenericDao<User, Integer>, AutoCloseable{

    void close();

}
