package entity;


import java.sql.Date;
import java.sql.Timestamp;
import java.util.Objects;

/**
 * Клас представляє деталі про гравця
 */
public class UserDetails {

    /**PK, FK*/ private Integer user_id;
    private Integer ratingBullet; /*Integer*/
    private Integer ratingBlitz;/*Integer*/
    private Integer ratingRapid;/*Integer*/
    private Integer ratingClassic;/*Integer*/
    private String country; /*varchar(255)*/
    private String gender; /*varchar(10)*/
    private Date dateOfBirth; /*Date*/
    private String profilePicture; /*varchar(255)*/
    private String bio; /*text*/
    private Timestamp accountCreatedAt; /*timestampz*/


    public UserDetails(Integer user_id, Integer ratingBullet, Integer ratingBlitz, Integer ratingRapid, Integer ratingClassic, String country, String gender, Date dateOfBirth, String profilePicture, String bio, Timestamp accountCreatedAt) {
        this.user_id = user_id;
        this.ratingBullet = ratingBullet;
        this.ratingBlitz = ratingBlitz;
        this.ratingRapid = ratingRapid;
        this.ratingClassic = ratingClassic;
        this.country = country;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
        this.profilePicture = profilePicture;
        this.bio = bio;
        this.accountCreatedAt = accountCreatedAt;
    }


    public UserDetails() {
    }

    public static class Builder implements IBuilder<UserDetails> {

        private UserDetails userDetails = new UserDetails();

        public Builder setUserId(Integer user_id) {
            userDetails.user_id = user_id;
            return this;
        }

        public Builder setRatingBullet(Integer ratingBullet) {
            userDetails.ratingBullet = ratingBullet;
            return this;
        }

        public Builder setRatingBlitz(Integer ratingBlitz) {
            userDetails.ratingBlitz = ratingBlitz;
            return this;
        }

        public Builder setRatingRapid(Integer ratingRapid) {
            userDetails.ratingRapid = ratingRapid;
            return this;
        }

        public Builder setRatingClassic(Integer ratingClassic) {
            userDetails.ratingClassic = ratingClassic;
            return this;
        }

        public Builder setCountry(String country) {
            userDetails.country = country;
            return this;
        }

        public Builder setGender(String gender) {
            userDetails.gender = gender;
            return this;
        }

        public Builder setDateOfBirth(Date dateOfBirth) {
            userDetails.dateOfBirth = dateOfBirth;
            return this;
        }

        public Builder setProfilePicture(String profilePicture) {
            userDetails.profilePicture = profilePicture;
            return this;
        }

        public Builder setBio(String bio) {
            userDetails.bio = bio;
            return this;
        }

        public Builder setAccountCreatedAt(Timestamp accountCreatedAt) {
            userDetails.accountCreatedAt = accountCreatedAt;
            return this;
        }

        @Override
        public UserDetails build() {
            return userDetails;
        }
    }


    @Override
    public String toString() {
        return "UserDetails{" +
                "user_id=" + user_id +
                ", ratingBullet=" + ratingBullet +
                ", ratingBlitz=" + ratingBlitz +
                ", ratingRapid=" + ratingRapid +
                ", ratingClassic=" + ratingClassic +
                ", country='" + country + '\'' +
                ", gender='" + gender + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", profilePicture='" + profilePicture + '\'' +
                ", bio='" + bio + '\'' +
                ", accountCreatedAt=" + accountCreatedAt +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDetails that = (UserDetails) o;
        return user_id.equals(that.user_id) && accountCreatedAt.equals(that.accountCreatedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user_id, accountCreatedAt);
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public Integer getRatingBullet() {
        return ratingBullet;
    }

    public void setRatingBullet(Integer ratingBullet) {
        this.ratingBullet = ratingBullet;
    }

    public Integer getRatingBlitz() {
        return ratingBlitz;
    }

    public void setRatingBlitz(Integer ratingBlitz) {
        this.ratingBlitz = ratingBlitz;
    }

    public Integer getRatingRapid() {
        return ratingRapid;
    }

    public void setRatingRapid(Integer ratingRapid) {
        this.ratingRapid = ratingRapid;
    }

    public Integer getRatingClassic() {
        return ratingClassic;
    }

    public void setRatingClassic(Integer ratingClassic) {
        this.ratingClassic = ratingClassic;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public Timestamp getAccountCreatedAt() {
        return accountCreatedAt;
    }

    public void setAccountCreatedAt(Timestamp accountCreatedAt) {
        this.accountCreatedAt = accountCreatedAt;
    }
}
