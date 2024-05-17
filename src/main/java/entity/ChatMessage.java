package entity;

import java.sql.Time;
import java.sql.Timestamp;

import java.sql.Timestamp;
import java.util.Objects;

/**Повідомлення чату*/
public class ChatMessage {

    /** PK */
    private Integer message_id;
    private User sender; /** FK */
    private Integer game_id; /** FK */ /**лише ID бо ChessGame дуже масивний об'єкт*/
    private String content; /* text */
    private Timestamp timestamp; /* timestampz */

    public ChatMessage() {
    }

    public ChatMessage(Integer message_id, User sender, Integer game_id, String content, Timestamp timestamp) {
        this.message_id = message_id;
        this.sender = sender;
        this.game_id = game_id;
        this.content = content;
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "ChatMessage{" +
                "message_id=" + message_id +
                ", sender=" + sender +
                ", game_id=" + game_id +
                ", content='" + content + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }

    public static class Builder implements IBuilder<ChatMessage> {

        private ChatMessage chatMessage = new ChatMessage();

        public Builder setMessageId(Integer message_id) {
            chatMessage.message_id = message_id;
            return this;
        }

        public Builder setSender(User sender) {
            chatMessage.sender = sender;
            return this;
        }

        public Builder setGameId(Integer game_id) {
            chatMessage.game_id = game_id;
            return this;
        }

        public Builder setContent(String content) {
            chatMessage.content = content;
            return this;
        }

        public Builder setTimestamp(Timestamp timestamp) {
            chatMessage.timestamp = timestamp;
            return this;
        }

        @Override
        public ChatMessage build() {
            return chatMessage;
        }
    }

    public Integer getMessageId() {
        return message_id;
    }

    public void setMessageId(Integer message_id) {
        this.message_id = message_id;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public Integer getGameId() {
        return game_id;
    }

    public void setGameId(Integer game_id) {
        this.game_id = game_id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((message_id == null) ? 0 : message_id.hashCode());
        result = prime * result + ((sender == null) ? 0 : sender.hashCode());
        result = prime * result + ((game_id == null) ? 0 : game_id.hashCode());
        result = prime * result + ((content == null) ? 0 : content.hashCode());
        result = prime * result + ((timestamp == null) ? 0 : timestamp.hashCode());
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

        ChatMessage other = (ChatMessage) obj;

        if (!Objects.equals(message_id, other.message_id)) {
            return false;
        }

        if (!Objects.equals(sender, other.sender)) {
            return false;
        }

        if (!Objects.equals(content, other.content)) {
            return false;
        }

        return Objects.equals(timestamp, other.timestamp);
    }
}
