package dao;

import entity.ChessGame;
import entity.GameRequest;

public interface ChessGameDao extends GenericDao<ChessGame, Integer>, AutoCloseable{
    void close();

}
