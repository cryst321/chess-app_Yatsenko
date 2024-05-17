package entity;

import java.sql.Timestamp;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Objects;

/** Шахова партія */
public class ChessGame {
    /** PK */
    private Integer game_id;
    private Timestamp timeBeginning;
    private Timestamp timeEnd;
    private String gameType; /* varchar(255) */
    private String gameStatus; /* varchar(255) */
    private User blackPlayer;
    private User whitePlayer;
    private User winner;
    private String[] moves;
    private boolean isRating; /* boolean */
    private Integer ratingWhite; /* Integer */
    private Integer ratingBlack; /* Integer */
    private Integer timeRestriction; /* Integer */

    public ChessGame() {
    }

    public ChessGame(Integer game_id, Timestamp timeBeginning, Timestamp timeEnd, String gameType, String gameStatus, User blackPlayer, User whitePlayer, User winner, String[] moves, boolean isRating, Integer ratingWhite, Integer ratingBlack, Integer timeRestriction) {
        this.game_id = game_id;
        this.timeBeginning = timeBeginning;
        this.timeEnd = timeEnd;
        this.gameType = gameType;
        this.gameStatus = gameStatus;
        this.blackPlayer = blackPlayer;
        this.whitePlayer = whitePlayer;
        this.winner = winner;
        this.moves = moves;
        this.isRating = isRating;
        this.ratingWhite = ratingWhite;
        this.ratingBlack = ratingBlack;
        this.timeRestriction = timeRestriction;
    }

    @Override
    public String toString() {
        return "ChessGame{" +
                "game_id=" + game_id +
                ", timeBeginning=" + timeBeginning +
                ", timeEnd=" + timeEnd +
                ", gameType='" + gameType + '\'' +
                ", gameStatus='" + gameStatus + '\'' +
                ", blackPlayer=" + blackPlayer +
                ", whitePlayer=" + whitePlayer +
                ", winner=" + winner +
                ", moves=" + Arrays.toString(moves) +
                ", isRating=" + isRating +
                ", ratingWhite=" + ratingWhite +
                ", ratingBlack=" + ratingBlack +
                ", timeRestriction=" + timeRestriction +
                '}';
    }

    public static class Builder implements IBuilder<ChessGame> {

        private ChessGame chessGame = new ChessGame();

        public Builder setGameId(Integer game_id) {
            chessGame.game_id = game_id;
            return this;
        }

        public Builder setTimeBeginning(Timestamp timeBeginning) {
            chessGame.timeBeginning = timeBeginning;
            return this;
        }

        public Builder setTimeEnd(Timestamp timeEnd) {
            chessGame.timeEnd = timeEnd;
            return this;
        }

        public Builder setGameType(String gameType) {
            chessGame.gameType = gameType;
            return this;
        }

        public Builder setGameStatus(String gameStatus) {
            chessGame.gameStatus = gameStatus;
            return this;
        }

        public Builder setBlackPlayer(User blackPlayer) {
            chessGame.blackPlayer = blackPlayer;
            return this;
        }

        public Builder setWhitePlayer(User whitePlayer) {
            chessGame.whitePlayer = whitePlayer;
            return this;
        }

        public Builder setWinner(User winner) {
            chessGame.winner = winner;
            return this;
        }

        public Builder setMoves(String[] moves) {
            chessGame.moves = moves;
            return this;
        }

        public Builder setIsRating(boolean isRating) {
            chessGame.isRating = isRating;
            return this;
        }

        public Builder setRatingWhite(Integer ratingWhite) {
            chessGame.ratingWhite = ratingWhite;
            return this;
        }

        public Builder setRatingBlack(Integer ratingBlack) {
            chessGame.ratingBlack = ratingBlack;
            return this;
        }

        public Builder setTimeRestriction(Integer timeRestriction) {
            chessGame.timeRestriction = timeRestriction;
            return this;
        }

        @Override
        public ChessGame build() {
            return chessGame;
        }
    }

    public Integer getGameId() {
        return game_id;
    }

    public void setGameId(Integer game_id) {
        this.game_id = game_id;
    }

    public Timestamp getTimeBeginning() {
        return timeBeginning;
    }

    public void setTimeBeginning(Timestamp timeBeginning) {
        this.timeBeginning = timeBeginning;
    }

    public Timestamp getTimeEnd() {
        return timeEnd;
    }

    public void setTimeEnd(Timestamp timeEnd) {
        this.timeEnd = timeEnd;
    }

    public String getGameType() {
        return gameType;
    }

    public void setGameType(String gameType) {
        this.gameType = gameType;
    }

    public String getGameStatus() {
        return gameStatus;
    }

    public void setGameStatus(String gameStatus) {
        this.gameStatus = gameStatus;
    }

    public User getBlackPlayer() {
        return blackPlayer;
    }

    public void setBlackPlayer(User blackPlayer) {
        this.blackPlayer = blackPlayer;
    }

    public User getWhitePlayer() {
        return whitePlayer;
    }

    public void setWhitePlayer(User whitePlayer) {
        this.whitePlayer = whitePlayer;
    }

    public User getWinner() {
        return winner;
    }

    public void setWinner(User winner) {
        this.winner = winner;
    }

    public String[] getMoves() {
        return moves;
    }

    public void setMoves(String[] moves) {
        this.moves = moves;
    }

    public boolean isRating() {
        return isRating;
    }

    public void setRating(boolean isRating) {
        this.isRating = isRating;
    }

    public Integer getRatingWhite() {
        return ratingWhite;
    }

    public void setRatingWhite(Integer ratingWhite) {
        this.ratingWhite = ratingWhite;
    }

    public Integer getRatingBlack() {
        return ratingBlack;
    }

    public void setRatingBlack(Integer ratingBlack) {
        this.ratingBlack = ratingBlack;
    }

    public Integer getTimeRestriction() {
        return timeRestriction;
    }

    public void setTimeRestriction(Integer timeRestriction) {
        this.timeRestriction = timeRestriction;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((game_id == null) ? 0 : game_id.hashCode());
        result = prime * result + ((timeBeginning == null) ? 0 : timeBeginning.hashCode());
        result = prime * result + ((timeEnd == null) ? 0 : timeEnd.hashCode());
        result = prime * result + ((gameType == null) ? 0 : gameType.hashCode());
        result = prime * result + ((gameStatus == null) ? 0 : gameStatus.hashCode());
        result = prime * result + ((blackPlayer == null) ? 0 : blackPlayer.hashCode());
        result = prime * result + ((whitePlayer == null) ? 0 : whitePlayer.hashCode());
        result = prime * result + ((winner == null) ? 0 : winner.hashCode());
        result = prime * result + Arrays.hashCode(moves);
        result = prime * result + (isRating ? 1231 : 1237);
        result = prime * result + ((ratingWhite == null) ? 0 : ratingWhite.hashCode());
        result = prime * result + ((ratingBlack == null) ? 0 : ratingBlack.hashCode());
        result = prime * result + ((timeRestriction == null) ? 0 : timeRestriction.hashCode());
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

        ChessGame other = (ChessGame) obj;

        if (!Objects.equals(game_id, other.game_id)) {
            return false;
        }

        if (!Objects.equals(timeBeginning, other.timeBeginning)) {
            return false;
        }

        return Objects.equals(timeRestriction, other.timeRestriction);
    }
}
