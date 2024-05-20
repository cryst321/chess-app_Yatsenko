package entity;

import java.sql.Timestamp;

import java.sql.Timestamp;
import java.util.Objects;

/**Скарга*/
public class Complaint {

    /** PK */
    private Integer id;
    private Timestamp createdAt;
    private User complainant;
    private User reported;
    private String complaintType;
    private String reason;
    private ChatMessage chatMessage;
    private User moderator;
    private String status;
    
    public enum ComplaintType {
        CHEAT, INSULT, RATING_MANIPULATION, TROLLING, OTHER;
    }

    public Complaint() {
    }

    public Complaint(Integer id, Timestamp createdAt, User complainant, User reported, String complaintType, String reason, ChatMessage chatMessage, User moderator, String status) {
        this.id = id;
        this.createdAt = createdAt;
        this.complainant = complainant;
        this.reported = reported;
        this.complaintType = complaintType;
        this.reason = reason;
        this.chatMessage = chatMessage;
        this.moderator = moderator;
        this.status = status;
    }

    @Override
    public String toString() {
        return "Complaint{" +
                "id=" + id +
                ", createdAt=" + createdAt +
                ", complainant=" + complainant +
                ", reported=" + reported +
                ", complaintType='" + complaintType + '\'' +
                ", reason='" + reason + '\'' +
                ", chatMessage=" + chatMessage +
                ", moderator=" + moderator +
                ", status='" + status + '\'' +
                '}';
    }

    public static class Builder implements IBuilder<Complaint> {

        private Complaint complaint = new Complaint();

        public Builder setId(Integer id) {
            complaint.id = id;
            return this;
        }

        public Builder setCreatedAt(Timestamp createdAt) {
            complaint.createdAt = createdAt;
            return this;
        }

        public Builder setComplainant(User complainant) {
            complaint.complainant = complainant;
            return this;
        }

        public Builder setReported(User reported) {
            complaint.reported = reported;
            return this;
        }

        public Builder setComplaintType(String complaintType) {
            complaint.complaintType = complaintType;
            return this;
        }

        public Builder setReason(String reason) {
            complaint.reason = reason;
            return this;
        }

        public Builder setChatMessage(ChatMessage chatMessage) {
            complaint.chatMessage = chatMessage;
            return this;
        }

        public Builder setModerator(User moderator) {
            complaint.moderator = moderator;
            return this;
        }

        public Builder setStatus(String status) {
            complaint.status = status;
            return this;
        }

        @Override
        public Complaint build() {
            return complaint;
        }
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public User getComplainant() {
        return complainant;
    }

    public void setComplainant(User complainant) {
        this.complainant = complainant;
    }

    public User getReported() {
        return reported;
    }

    public void setReported(User reported) {
        this.reported = reported;
    }

    public String getComplaintType() {
        return complaintType;
    }

    public void setComplaintType(String complaintType) {
        this.complaintType = complaintType;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public ChatMessage getChatMessage() {
        return chatMessage;
    }

    public void setChatMessage(ChatMessage chatMessage) {
        this.chatMessage = chatMessage;
    }

    public User getModerator() {
        return moderator;
    }

    public void setModerator(User moderator) {
        this.moderator = moderator;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((createdAt == null) ? 0 : createdAt.hashCode());
        result = prime * result + ((complainant == null) ? 0 : complainant.hashCode());
        result = prime * result + ((reported == null) ? 0 : reported.hashCode());
        result = prime * result + ((complaintType == null) ? 0 : complaintType.hashCode());
        result = prime * result + ((reason == null) ? 0 : reason.hashCode());
        result = prime * result + ((chatMessage == null) ? 0 : chatMessage.hashCode());
        result = prime * result + ((moderator == null) ? 0 : moderator.hashCode());
        result = prime * result + ((status == null) ? 0 : status.hashCode());
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

        Complaint other = (Complaint) obj;

        if (!Objects.equals(id, other.id)) {
            return false;
        }

        if (!Objects.equals(createdAt, other.createdAt)) {
            return false;
        }

        if (!Objects.equals(complainant, other.complainant)) {
            return false;
        }
        if (!Objects.equals(complaintType, other.complaintType)) {
            return false;
        }


        return Objects.equals(status, other.status);
    }
}
