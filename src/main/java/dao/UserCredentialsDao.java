package dao;

import entity.UserCredentials;

import java.util.Optional;

public interface UserCredentialsDao extends GenericDao<UserCredentials, Integer>, AutoCloseable {

    Optional<UserCredentials> getFullCredentials(String email, String password);

    void updateBulletRating(int id, int rating);

    void close();


}
