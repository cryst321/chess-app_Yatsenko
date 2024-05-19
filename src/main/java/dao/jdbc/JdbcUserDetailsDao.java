package dao.jdbc;

import dao.UserDetailsDao;
import entity.UserDetails;
import exception.ServerException;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JdbcUserDetailsDao implements UserDetailsDao {

    private static final Logger LOGGER = LogManager.getLogger(JdbcUserCredentialsDao.class);

    private static String GET_ALL = "SELECT * FROM user_details ORDER BY user_id";
    private static String GET_BY_ID = "SELECT * FROM user_details WHERE user_id=?";

    private static String UPDATE_RATING_BULLET = "UPDATE user_details SET rating_bullet=rating_bullet+? WHERE user_id=?";
    private static String UPDATE_RATING_BLITZ = "UPDATE user_details SET rating_blitz=rating_blitz+? WHERE user_id=?";
    private static String UPDATE_RATING_RAPID = "UPDATE user_details SET rating_rapid=rating_rapid+? WHERE user_id=?";
    private static String UPDATE_RATING_CLASSIC = "UPDATE user_details SET rating_classic=rating_classic+? WHERE user_id=?";

    private static String UPDATE_USER = "UPDATE user_details SET rating_bullet=?, rating_blitz=?, rating_rapid=?, rating_classic=?, country=?, gender=?, date_of_birth=?, profile_picture=?, bio=?, account_created_at=? WHERE user_id=?";

    private static String DELETE_USER = "DELETE FROM user_details WHERE user_id=?";

    private static final String INSERT_USER_DETAILS = "INSERT INTO user_details (user_id) VALUES (?)";

    private static String ID = "user_id";
    private static String RATING_BULLET = "rating_bullet";
    private static String RATING_BLITZ = "rating_blitz";
    private static String RATING_RAPID = "rating_rapid";
    private static String RATING_CLASSIC = "rating_classic";
    private static String COUNTRY = "country";
    private static String GENDER = "gender";
    private static String DATE_OF_BIRTH = "date_of_birth";
    private static String PROFILE_PICTURE = "profile_picture";
    private static String BIO = "bio";
    private static String ACCOUNT_CREATED_AT = "account_created_at";



    private Connection connection;
    private boolean connectionShouldBeClosed;

    public JdbcUserDetailsDao(Connection connection) {
        this.connection = connection;
        connectionShouldBeClosed = false;
    }


    public JdbcUserDetailsDao(Connection connection, boolean connectionShouldBeClosed) {
        this.connection = connection;
        this.connectionShouldBeClosed = connectionShouldBeClosed;
    }
    public void setConnection(Connection connection) {
        this.connection = connection;
    }


    @Override
    public void updateRating(int userId, int ratingChange, short ratingType) {
        JdbcDaoConnection daoConnection = new JdbcDaoConnection(connection);
        Connection conn = daoConnection.getConnection();

        try {
            daoConnection.begin();
            /** Встановлення рівню ізольованості Serializable для операції з рейтингом **/
            conn.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);

            String updateQuery = null;

            switch (ratingType) {
                case 1: // Bullet
                    updateQuery = UPDATE_RATING_BULLET;
                    break;
                case 2: // Blitz
                    updateQuery = UPDATE_RATING_BLITZ;
                    break;
                case 3: // Rapid
                    updateQuery = UPDATE_RATING_RAPID;
                    break;
                case 4: // Classic
                    updateQuery = UPDATE_RATING_CLASSIC;
                    break;
                default:
                    throw new IllegalArgumentException("Invalid rating type: " + ratingType);
            }

            try (PreparedStatement updateStatement = conn.prepareStatement(updateQuery)) {
                updateStatement.setInt(1, ratingChange);
                updateStatement.setInt(2, userId);
                updateStatement.executeUpdate();
            }

            daoConnection.commit();
        } catch (SQLException e) {
            daoConnection.rollback();
            LOGGER.error("Error while updating rating for user ID: " + userId, e);
            throw new ServerException(e);
        } finally {
            if (connectionShouldBeClosed) {
                daoConnection.close();
            }
        }

    }

    @Override
    public void close() {

    }

    @Override
    public List<UserDetails> getAll() {
        List<UserDetails> userDetailsList = new ArrayList<>();
        try (Statement query = connection.createStatement(); ResultSet resultSet = query.executeQuery(GET_ALL)) {
            while (resultSet.next()) {
                userDetailsList.add(extractUserDetailsFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            LOGGER.error("JdbcUserDetailsDao getAll SQL exception", e);
            throw new ServerException(e);
        }
        return userDetailsList;
    }

    @Override
    public Optional<UserDetails> getById(Integer id) {
        Optional<UserDetails> userDetails = Optional.empty();
        try (PreparedStatement query = connection.prepareStatement(GET_BY_ID)) {
            query.setInt(1, id);
            ResultSet resultSet = query.executeQuery();
            while (resultSet.next()) {
                userDetails = Optional.of(extractUserDetailsFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            LOGGER.error("JdbcUserDetailsDao getById SQL exception: " + id, e);
            throw new ServerException(e);
        }
        return userDetails;
    }

    @Override
    public void create(UserDetails userDetails) {
        try (PreparedStatement insertStatement = connection.prepareStatement(INSERT_USER_DETAILS)) {
            insertStatement.setInt(1, userDetails.getUser_id());
            insertStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("JdbcUserDetailsDao create SQL exception", e);
            throw new ServerException(e);
        }
    }

    @Override
    public void update(UserDetails userDetails) {
        try (PreparedStatement updateStatement = connection.prepareStatement(UPDATE_USER)) {
            updateStatement.setInt(1, userDetails.getRatingBullet());
            updateStatement.setInt(2, userDetails.getRatingBlitz());
            updateStatement.setInt(3, userDetails.getRatingRapid());
            updateStatement.setInt(4, userDetails.getRatingClassic());
            updateStatement.setString(5, userDetails.getCountry());
            updateStatement.setString(6, userDetails.getGender());
            updateStatement.setDate(7, new Date(userDetails.getDateOfBirth().getTime()));
            updateStatement.setString(8, userDetails.getProfilePicture());
            updateStatement.setString(9, userDetails.getBio());
            updateStatement.setTimestamp(10, userDetails.getAccountCreatedAt());
            updateStatement.setInt(11, userDetails.getUser_id());
            updateStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("JdbcUserDetailsDao update SQL exception: " + userDetails.getUser_id(), e);
            throw new ServerException(e);
        }
    }

    @Override
    public void delete(Integer id) {
        try (PreparedStatement deleteStatement = connection.prepareStatement(DELETE_USER)) {
            deleteStatement.setInt(1, id);
            deleteStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("JdbcUserDetailsDao delete SQL exception: " + id, e);
            throw new ServerException(e);
        }
    }

    private static UserDetails extractUserDetailsFromResultSet(ResultSet resultSet) throws SQLException {
        UserDetails userDetails = new UserDetails();
        userDetails.setUser_id(resultSet.getInt(ID));
        userDetails.setRatingBullet(resultSet.getInt(RATING_BULLET));
        userDetails.setRatingBlitz(resultSet.getInt(RATING_BLITZ));
        userDetails.setRatingRapid(resultSet.getInt(RATING_RAPID));
        userDetails.setRatingClassic(resultSet.getInt(RATING_CLASSIC));
        userDetails.setCountry(resultSet.getString(COUNTRY));
        userDetails.setGender(resultSet.getString(GENDER));
        userDetails.setDateOfBirth(resultSet.getDate(DATE_OF_BIRTH));
        userDetails.setProfilePicture(resultSet.getString(PROFILE_PICTURE));
        userDetails.setBio(resultSet.getString(BIO));
        userDetails.setAccountCreatedAt(resultSet.getTimestamp(ACCOUNT_CREATED_AT));
        return userDetails;
    }

}
