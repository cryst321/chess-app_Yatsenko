package dao.jdbc;

import dao.ComplaintDao;

import dao.ComplaintDao;
import entity.Complaint;
import entity.User;
import exception.ServerException;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JdbcComplaintDao implements ComplaintDao {

    private static final Logger LOGGER = LogManager.getLogger(JdbcComplaintDao.class);

    private static final String INSERT_COMPLAINT = "INSERT INTO complaint (created_at, complainant_id, reported_id, complaint_type, reason, chat_message_id, moderator_id, status) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String GET_ALL = "SELECT * FROM complaint ORDER BY id";
    private static final String GET_BY_ID = "SELECT * FROM complaint WHERE id=?";

    private static final String DELETE_COMPLAINT = "DELETE FROM complaint WHERE id=?";
    private static final String UPDATE_COMPLAINT = "UPDATE complaint SET created_at=?, complainant_id=?, reported_id=?, complaint_type=?, reason=?, chat_message_id=?, moderator_id=?,status=? WHERE id=?";

    private static final String CHANGE_STATUS = "UPDATE complaint SET status=? WHERE id=?";

    private static final String UPDATE_MODERATOR = "UPDATE complaint SET moderator_id=? WHERE id=?";

    private Connection connection;
    private boolean connectionShouldBeClosed;

    public JdbcComplaintDao(Connection connection) {
        this.connection = connection;
        connectionShouldBeClosed = false;
    }

    public JdbcComplaintDao(Connection connection, boolean connectionShouldBeClosed) {
        this.connection = connection;
        this.connectionShouldBeClosed = connectionShouldBeClosed;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Complaint> getAll() {
        List<Complaint> complaints = new ArrayList<>();
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(GET_ALL)) {
            while (resultSet.next()) {
                complaints.add(extractComplaintFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            LOGGER.error("Error while fetching all complaints", e);
            throw new ServerException(e);
        }
        return complaints;
    }

    @Override
    public Optional<Complaint> getById(Integer id) {
        try (PreparedStatement statement = connection.prepareStatement(GET_BY_ID)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return Optional.of(extractComplaintFromResultSet(resultSet));
                }
            }
        } catch (SQLException e) {
            LOGGER.error("Error while fetching complaint by ID: " + id, e);
            throw new ServerException(e);
        }
        return Optional.empty();
    }

    @Override
    public void create(Complaint complaint) {
        try (PreparedStatement statement = connection.prepareStatement(INSERT_COMPLAINT, Statement.RETURN_GENERATED_KEYS)) {
            statement.setTimestamp(1, complaint.getCreatedAt());
            statement.setInt(2, complaint.getComplainant().getId());
            statement.setInt(3, complaint.getReported().getId());
            statement.setString(4, complaint.getComplaintType());
            statement.setString(5, complaint.getReason());

            if (complaint.getChatMessage() != null) {
                statement.setInt(6, complaint.getChatMessage().getMessageId());
            } else {
                statement.setNull(6, java.sql.Types.INTEGER);
            }

            if (complaint.getModerator() != null) {
                statement.setInt(7, complaint.getModerator().getId());
            } else {
                statement.setNull(7, java.sql.Types.INTEGER);
            }

            statement.setString(8, complaint.getStatus());

            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Creating complaint failed, no rows affected.");
            }
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    complaint.setId(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("Creating complaint failed, no ID obtained.");
                }
            }
        } catch (SQLException e) {
            LOGGER.error("Error while creating complaint", e);
            throw new ServerException(e);
        }
    }


    private Complaint extractComplaintFromResultSet(ResultSet resultSet) throws SQLException {
        Integer id = resultSet.getInt("id");
        Timestamp createdAt = resultSet.getTimestamp("created_at");
        String complaintType = resultSet.getString("complaint_type");
        String reason = resultSet.getString("reason");

        Complaint complaint = new Complaint();

        JdbcUserDao userDao = new JdbcUserDao(connection);
        JdbcChatMessageDao chatMessageDao = new JdbcChatMessageDao(connection);


        complaint.setId(id);
        complaint.setCreatedAt(createdAt);
        complaint.setComplainant(userDao.getById(resultSet.getInt("complainant_id")).orElse(null));
        complaint.setReported(userDao.getById(resultSet.getInt("reported_id")).orElse(null));
        complaint.setComplaintType(complaintType);
        complaint.setReason(reason);
        complaint.setChatMessage(chatMessageDao.getById(resultSet.getInt("chat_message_id")).orElse(null));

        complaint.setModerator(userDao.getById(resultSet.getInt("moderator_id")).orElse(null));

        return complaint;
    }

    @Override
    public void updateStatus(String status) {
        try (PreparedStatement statement = connection.prepareStatement(CHANGE_STATUS)) {
            statement.setString(1, status);
            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Setting status complaint failed, no rows affected.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateModerator(Integer moderator_id) {
        try (PreparedStatement statement = connection.prepareStatement(UPDATE_MODERATOR)) {
            statement.setInt(1, moderator_id);
            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Setting moderator complaint failed, no rows affected.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /*"UPDATE complaint SET created_at=?, complainant_id=?, reported_id=?, complaint_type=?, reason=?, chat_message_id=?, moderator_id=?,status=? WHERE id=?"**/
    @Override
    public void update(Complaint complaint) {
        try (PreparedStatement statement = connection.prepareStatement(UPDATE_COMPLAINT)) {
            statement.setTimestamp(1, complaint.getCreatedAt());
            statement.setInt(2, complaint.getComplainant().getId());
            statement.setInt(3, complaint.getReported().getId());
            statement.setString(4, complaint.getComplaintType());
            statement.setString(5, complaint.getReason());
            if (complaint.getChatMessage() != null) {
                statement.setInt(6, complaint.getChatMessage().getMessageId());
            } else {
                statement.setNull(6, java.sql.Types.INTEGER);
            }

            if (complaint.getModerator() != null) {
                statement.setInt(7, complaint.getModerator().getId());
            } else {
                statement.setNull(7, java.sql.Types.INTEGER);
            }
            statement.setString(8, complaint.getStatus());

            statement.setInt(9, complaint.getId());

            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Updating complaint failed, no rows affected.");
            }
        } catch (SQLException e) {
            LOGGER.error("Error while updating complaint", e);
            throw new ServerException(e);
        }
    }

    @Override
    public void delete(Integer id) {
        try (PreparedStatement statement = connection.prepareStatement(DELETE_COMPLAINT)) {
            statement.setInt(1, id);
            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Deleting complaint failed, no rows affected.");
            }
        } catch (SQLException e) {
            LOGGER.error("Error while deleting complaint", e);
            throw new ServerException(e);
        }
    }

    @Override
    public void close() {
        if (connectionShouldBeClosed) {
            try {
                connection.close();
            } catch (SQLException e) {
                LOGGER.error("Error while closing JDBC connection", e);
                throw new ServerException(e);
            }
        }
    }
}
