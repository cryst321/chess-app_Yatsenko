package dao;

import entity.Complaint;
import entity.GameRequest;
import entity.User;

public interface ComplaintDao extends GenericDao<Complaint, Integer>, AutoCloseable{
    void close();

    void updateStatus(String status);

    void updateModerator(Integer moderator_id, Integer complaint_id);


}
