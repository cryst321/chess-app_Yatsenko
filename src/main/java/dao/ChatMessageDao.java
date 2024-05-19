package dao;

import entity.ChatMessage;
import entity.Complaint;

import java.util.List;

public interface ChatMessageDao extends GenericDao<ChatMessage, Integer>, AutoCloseable{

    public List<ChatMessage> getAllFromGame(Integer gameId);

    void close();
}
