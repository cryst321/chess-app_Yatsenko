package entity;

import java.util.Objects;

public class User {



    private Integer id;
    private UserCredentials userCredentials;
    private UserDetails userDetails;

    public User() {
    }

    public User(Integer id, UserCredentials userCredentials, UserDetails userDetails) {
        this.id = id;
        this.userCredentials = userCredentials;
        this.userDetails = userDetails;
    }

    @Override
    public String toString() {
        return "User{" + id +
                ", userCredentials=" + userCredentials +
                ", userDetails=" + userDetails +
                '}';
    }

    public static class Builder implements IBuilder<User> {

        private User user = new User();

        public Builder setUserId(Integer id) {
            user.id = id;
            return this;
        }

        public Builder setUserCredentials(UserCredentials userCredentials) {
            user.userCredentials = userCredentials;
            return this;
        }

        public Builder setUserDetails(UserDetails userDetails) {
            user.userDetails = userDetails;
            return this;
        }

        @Override
        public User build() {
            return user;
        }
    }

    public UserCredentials getUserCredentials() {
        return userCredentials;
    }

    public void setUserCredentials(UserCredentials userCredentials) {
        this.userCredentials = userCredentials;
    }

    public UserDetails getUserDetails() {
        return userDetails;
    }

    public void setUserDetails(UserDetails userDetails) {
        this.userDetails = userDetails;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((userCredentials == null) ? 0 : userCredentials.hashCode());
        result = prime * result + ((userDetails == null) ? 0 : userDetails.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if ((obj == null) || (getClass() != obj.getClass())) {
            return false;
        }

        User other = (User) obj;

        if (!Objects.equals(userCredentials, other.userCredentials)) {
            return false;
        }

        return Objects.equals(userDetails, other.userDetails);
    }
}
