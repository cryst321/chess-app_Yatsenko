package dao;

import entity.UserCredentials;

public interface UserCredentialsDao extends GenericDao<UserCredentials, Integer>, AutoCloseable {

    void close();


}
