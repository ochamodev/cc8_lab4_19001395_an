package CCVIII.Lab04.data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import CCVIII.Lab04.exception.UserNotFoundException;
import CCVIII.Lab04.model.ServerResult;

public class DataRepository {
    private static DataRepository instance;

    private DataRepository() {}

    public static synchronized DataRepository getInstance() {
        if (instance == null) {
            instance = new DataRepository();
        }
        return instance;
    }

    public ServerResult<Integer> insertDomain(String domainName, int isThisDomainServer) {
        try {
            var conn = SQLiteConnection.getInstance().getConnection();
            
            var sql = """
                INSERT INTO Domain(domainName, isThisDomainServer)
                    VALUES(?, ?)
                """
            ;
            try (PreparedStatement stm = conn.prepareStatement(sql)) {
                stm.setString(1, domainName);
                stm.setInt(2, isThisDomainServer);
                var insertId = stm.executeUpdate();

                return ServerResult.success(insertId);

            }
        } catch (SQLException ex) {
            return ServerResult.failure(ex);
        }
    }

    public int insertMailBox(int domain, String userDomainName) {
        try {
            var conn = SQLiteConnection.getInstance().getConnection();
            
            var sql = """
                INSERT INTO MailBox(idDomain, userDomainName)
                    VALUES(?, ?)
                """
            ;
            try (PreparedStatement stm = conn.prepareStatement(sql)) {
                stm.setInt(1, domain);
                stm.setString(2, userDomainName);
                return stm.executeUpdate();
            }
        } catch (SQLException ex) {
            return -1;
        }
    }

    public ServerResult<Boolean> mailBoxExists(String userDomainName) {
        try {
            var conn = SQLiteConnection.getInstance().getConnection();
            
            var sql = """
                SELECT * FROM MailBox
                    WHERE userDomainName = ?
                """
            ;
            try (PreparedStatement stm = conn.prepareStatement(sql)) {
                stm.setString(1, userDomainName);
                ResultSet resultSet = stm.executeQuery();
                if (!resultSet.isBeforeFirst()) {
                    throw new UserNotFoundException("Usuario no encontrado");
                } else {
                    return ServerResult.success(true);
                }
            }
        } catch (Exception ex) {
            return ServerResult.failure(ex);
        }
    }

    public ServerResult<Integer> insertMail(CreateEmailData data) {
         try {
            var conn = SQLiteConnection.getInstance().getConnection();
            
            var sql = """
                INSERT INTO StoredEmail(
                    idMailBox,
                    subjectMail,
                    recipients,
                    emailData
                )
                    VALUES(?,?,?,?,?)
                """
            ;
            try (PreparedStatement stm = conn.prepareStatement(sql)) {
                stm.setInt(1, data.getIdMailBox());
                stm.setString(2, data.getSubjectMail());
                stm.setString(3, data.getRecipients());
                stm.setString(4, data.getEmailData());
                var insertId = stm.executeUpdate();
                return ServerResult.success(insertId);
            }
        } catch (Exception ex) {
            return ServerResult.failure(ex);
        }
    }

}
