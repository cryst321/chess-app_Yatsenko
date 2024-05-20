package dao;

import entity.Complaint;
import entity.GameRequest;
import entity.User;

public interface ComplaintDao extends GenericDao<Complaint, Integer>, AutoCloseable{
    void close();

    void updateStatus(Integer complaint_id,String status);

    void updateModerator(Integer moderator_id, Integer complaint_id);


}
