package CCVIII.Lab04.model;

import java.util.ArrayList;
import java.util.List;

public class EmailModel {
    private String mailFrom;
    private ArrayList<String> recipients;
    private String data;
    
    private EmailModel(Builder builder) {
        this.mailFrom = builder.mailFrom;
        this.recipients = builder.recipients;
        this.data = builder.data;
    }
    
    public String getMailFrom() {
        return mailFrom;
    }
    
    public List<String> getRecipients() {
        return recipients;
    }
    
    public String getData() {
        return data;
    }

    public static class Builder {
        private String mailFrom;
        private ArrayList<String> recipients = new ArrayList<>();
        private String data;
        
        public Builder setMailFrom(String mailFrom) {
            this.mailFrom = mailFrom;
            return this;
        }
        
        public Builder setRecipients(ArrayList<String> recipients) {
            this.recipients = recipients;
            return this;
        }
        
        public Builder setData(String data) {
            this.data = data;
            return this;
        }
        
        public Builder addRecipient(String recipient) {
            this.recipients.add(recipient);
            return this;
        }
        
        public EmailModel build() {
            return new EmailModel(this);
        }
    }
}
