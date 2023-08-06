package CCVIII.Lab04;

public class Commands {
    public static final String HELO_COMMAND = "^(?i)HELO\\\\s+([a-zA-Z0-9.-]+\\\\.[a-zA-Z]{2,4})$";
    public static final String MAIL_FROM = "^(?i)MAIL\\\\s+FROM:\\\\s+([a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\\\.[a-zA-Z]{2,4})$";
    public static final String RCPT_TO = "^(?i)RCPT\\\\s*to:\\\\s*([a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\\\.[a-zA-Z]{2,4})$";
    public static final String POINT_COMMAND = ".";
    public static final String QUIT_COMMAND = "QUIT";
}
