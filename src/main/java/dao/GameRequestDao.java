package dao;

import entity.GameRequest;
import entity.User;

public interface GameRequestDao extends GenericDao<GameRequest, Integer>, AutoCloseable {

    void close();

}

