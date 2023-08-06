package CCVIII.Lab04.data;

public class CreateEmailData {
    private int idMailBox;
    private String subjectMail;
    private String recipients;
    private String emailData;

    // Constructor (private to enforce usage of builder)
    private CreateEmailData(Builder builder) {
        this.idMailBox = builder.idMailBox;
        this.subjectMail = builder.subjectMail;
        this.recipients = builder.recipients;
        this.emailData = builder.emailData;
    }

    // Getters for the attributes (can be omitted if not needed)
    public int getIdMailBox() {
        return idMailBox;
    }

    public String getSubjectMail() {
        return subjectMail;
    }

    public String getRecipients() {
        return recipients;
    }

    public String getEmailData() {
        return emailData;
    }

    // Builder class
    public static class Builder {
        private int idMailBox;
        private String subjectMail;
        private String recipients;
        private String emailData;

        // Setters for the attributes
        public Builder idMailBox(int idMailBox) {
            this.idMailBox = idMailBox;
            return this;
        }

        public Builder subjectMail(String subjectMail) {
            this.subjectMail = subjectMail;
            return this;
        }

        public Builder recipients(String recipients) {
            this.recipients = recipients;
            return this;
        }

        public Builder emailData(String emailData) {
            this.emailData = emailData;
            return this;
        }

        // Build method to create the instance of CreateEmailData
        public CreateEmailData build() {
            return new CreateEmailData(this);
        }
    }
}
