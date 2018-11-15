package home.am.maintogmail.model;

public class SendEmailModel  {
    private String email;
    private String subject;
    private String message;

    public SendEmailModel(String email, String subject, String message) {
        this.email = email;
        this.subject = subject;
        this.message = message;
    }

    public String getEmail() {
        return email;
    }

    public String getSubject() {
        return subject;
    }

    public String getMessage() {
        return message;
    }
}
