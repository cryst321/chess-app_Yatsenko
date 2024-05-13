public class User {
    public int id;
    public String nickname;
    public String email;

    public User(int id, String nickname, String email) {
        this.id = id;
        this.nickname = nickname;
        this.email = email;
    }

    public User() {
        this.id = 4;
        this.nickname = "heh";
        this.email = "lmao";
    }

    // Getters and setters for id, nickname, and email
    // You can generate these automatically in most IDEs
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
