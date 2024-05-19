package dao;

import entity.UserCredentials;
import entity.UserDetails;

public interface UserDetailsDao extends GenericDao<UserDetails, Integer>, AutoCloseable {

    void updateRating(int id, int rating, short ratingType);

    void close();

}
