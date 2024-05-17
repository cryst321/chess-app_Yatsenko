package entity;

import java.util.Objects;

/**
 * Запит на гру.
 */
public class GameRequest {

    /**
     * PK
     */
    private Integer request_id;
    private User user;
    private String gameType; /*varchar(255)*/
    private Integer ratingLess; /*Integer*/
    private Integer ratingMore; /*Integer*/
    private Integer timeRestriction; /*Integer*/
    private String preferredColor; /*varchar(5)*/
    private boolean isRating; /*boolean*/

    public GameRequest() {
    }

    public GameRequest(Integer request_id, User user, String gameType, Integer ratingLess, Integer ratingMore, Integer timeRestriction, String preferredColor, boolean isRating) {
        this.request_id = request_id;
        this.user = user;
        this.gameType = gameType;
        this.ratingLess = ratingLess;
        this.ratingMore = ratingMore;
        this.timeRestriction = timeRestriction;
        this.preferredColor = preferredColor;
        this.isRating = isRating;
    }

    @Override
    public String toString() {
        return "GameRequest{" +
                "request_id=" + request_id +
                ", user=" + user +
                ", gameType='" + gameType + '\'' +
                ", ratingLess=" + ratingLess +
                ", ratingMore=" + ratingMore +
                ", timeRestriction=" + timeRestriction +
                ", preferredColor='" + preferredColor + '\'' +
                ", isRating=" + isRating +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GameRequest that = (GameRequest) o;
        return request_id.equals(that.request_id) && user.equals(that.user) && gameType.equals(that.gameType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(request_id, user, gameType);
    }

    public static class Builder implements IBuilder<GameRequest> {

        private GameRequest gameRequest = new GameRequest();

        public Builder setRequestId(Integer request_id) {
            gameRequest.request_id = request_id;
            return this;
        }

        public Builder setUser(User user) {
            gameRequest.user = user;
            return this;
        }

        public Builder setGameType(String gameType) {
            gameRequest.gameType = gameType;
            return this;
        }

        public Builder setRatingLess(Integer ratingLess) {
            gameRequest.ratingLess = ratingLess;
            return this;
        }

        public Builder setRatingMore(Integer ratingMore) {
            gameRequest.ratingMore = ratingMore;
            return this;
        }

        public Builder setTimeRestriction(Integer timeRestriction) {
            gameRequest.timeRestriction = timeRestriction;
            return this;
        }

        public Builder setPreferredColor(String preferredColor) {
            gameRequest.preferredColor = preferredColor;
            return this;
        }

        public Builder setIsRating(boolean isRating) {
            gameRequest.isRating = isRating;
            return this;
        }

        @Override
        public GameRequest build() {
            return gameRequest;
        }

    }

    public Integer getRequestId() {
        return request_id;
    }

    public void setRequestId(Integer request_id) {
        this.request_id = request_id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getGameType() {
        return gameType;
    }

    public void setGameType(String gameType) {
        this.gameType = gameType;
    }

    public Integer getRatingLess() {
        return ratingLess;
    }

    public void setRatingLess(Integer ratingLess) {
        this.ratingLess = ratingLess;
    }

    public Integer getRatingMore() {
        return ratingMore;
    }

    public void setRatingMore(Integer ratingMore) {
        this.ratingMore = ratingMore;
    }

    public Integer getTimeRestriction() {
        return timeRestriction;
    }

    public void setTimeRestriction(Integer timeRestriction) {
        this.timeRestriction = timeRestriction;
    }

    public String getPreferredColor() {
        return preferredColor;
    }

    public void setPreferredColor(String preferredColor) {
        this.preferredColor = preferredColor;
    }

    public boolean getIsRating() {
        return isRating;
    }

    public void setIsRating(boolean isRating) {
        this.isRating = isRating;
    }


}