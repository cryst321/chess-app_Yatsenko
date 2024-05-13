package entity;



import java.util.Objects;

/**
 * Клас представляє таку інформацію про гравця, як: айді, нік, емейл, пароль, роль
 */
public class UserCredentials {

    /**PK*/ private Integer id;
    private String nickname; /*Varchar(255)*/
    private String email;/*Varchar(255)*/
    private String password;/*Varchar(255)*/
    private String role;/*Varchar(10)*/

    @Override
    public String toString() {
        return "UserCredentials{" +
                "id=" + id +
                ", nickname='" + nickname + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                '}';
    }


    public static class Builder implements IBuilder<UserCredentials> {

        private UserCredentials userCredentials = new UserCredentials();

        public Builder setId(Integer id) {
            userCredentials.id = id;
            return this;
        }

        public Builder setNickname(String nickname) {
            userCredentials.nickname = nickname;
            return this;
        }

        public Builder setEmail(String email) {
            userCredentials.email = email;
            return this;
        }

        public Builder setPassword(String password) {
            userCredentials.password = password;
            return this;
        }

        public Builder setRole(String role) {
            userCredentials.role = role;
            return this;
        }

        @Override
        public UserCredentials build() {
            return userCredentials;
        }

    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((email == null) ? 0 : email.hashCode());
        result = prime * result + ((password == null) ? 0 : password.hashCode());
        result = prime * result + ((nickname == null) ? 0 : nickname.hashCode());
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

        UserCredentials other = (UserCredentials) obj;

        if (!Objects.equals(email, other.email)) {
            return false;
        }

        if (!Objects.equals(password, other.password)) {
            return false;
        }

        return (Objects.equals(nickname, other.nickname));
    }



    }
