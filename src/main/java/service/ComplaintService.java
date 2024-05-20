package service;

import dao.ComplaintDao;
import dao.DaoFactory;
import dao.UserCredentialsDao;
import dao.UserDao;
import entity.Complaint;
import entity.UserCredentials;
import exception.ServiceException;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.List;
import java.util.Optional;

public class ComplaintService {

    private static final Logger LOGGER = LogManager.getLogger(ComplaintService.class);

    private final DaoFactory daoFactory;

    private ComplaintService(DaoFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    private static class Holder {
        static final ComplaintService INSTANCE = new ComplaintService(DaoFactory.getDaoFactory());
    }

    public static ComplaintService getInstance() {
        return Holder.INSTANCE;
    }

    public List<Complaint> getAllComplaints() {
        LOGGER.info("Getting all reports");
        try (ComplaintDao complaintDao = daoFactory.createComplaintDao()) {
            return complaintDao.getAll();
        }
    }

    public Optional<Complaint> getComplaintById(Integer id) {
        LOGGER.info("Getting complaint " + id);
        try (ComplaintDao complaintDao = daoFactory.createComplaintDao()) {
            return complaintDao.getById(id);
        }
    }


    public void createComplaint(Complaint complaint) {
        LOGGER.info("Creating complaint: " + complaint);
        try (ComplaintDao complaintDao = daoFactory.createComplaintDao()) {
            complaintDao.create(complaint);
        } catch (Exception e) {
            LOGGER.error("Error creating complaint", e);
            throw new ServiceException("Error creating complaint", e);
        }
    }

    public void updateComplaint(Complaint complaint) {
        LOGGER.info("Updating complaint: " + complaint);
        try (ComplaintDao complaintDao = daoFactory.createComplaintDao()) {
            complaintDao.update(complaint);
        } catch (Exception e) {
            LOGGER.error("Error updating complaint", e);
            throw new ServiceException("Error updating complaint", e);
        }
    }

    public void deleteComplaint(Integer id) {
        LOGGER.info("Deleting complaint: " + id);
        try (ComplaintDao complaintDao = daoFactory.createComplaintDao()){
            complaintDao.delete(id);
        } catch (Exception e) {
            LOGGER.error(String.format("Error deleting complaint by id: %d", id), e);
            throw new ServiceException(String.format("Error deleting complaint by id: %d", id), e);
        }
    }

    public void setModerator(Integer moderatorId, Integer complaintId) {
        LOGGER.info("Updating complaint: " + complaintId + " + setting moder " + moderatorId);
        try (ComplaintDao complaintDao = daoFactory.createComplaintDao()) {
            complaintDao.updateModerator(moderatorId, complaintId);
        } catch (Exception e) {
            LOGGER.error("Error updating complaint", e);
            throw new ServiceException("Error updating complaint", e);
        }
    }


    public void setStatus(Integer complaintId, String status) {
        LOGGER.info("Updating complaint: " + complaintId + " + setting status " + status);
        try (ComplaintDao complaintDao = daoFactory.createComplaintDao()) {
            complaintDao.updateStatus(complaintId,status);
        } catch (Exception e) {
            LOGGER.error("Error updating complaint", e);
            throw new ServiceException("Error updating complaint", e);
        }
    }
}
