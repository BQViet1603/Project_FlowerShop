package sample.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import sample.dto.Account;
import sample.utils.DBUtils;

public class AccountDAO {

    // Define constants for repeated literals
    private static final String ACCOUNT_ID = "accID";
    private static final String EMAIL = "email";
    private static final String PASSWORD = "password";
    private static final String FULLNAME = "fullname";
    private static final String PHONE = "phone";
    private static final String STATUS = "status";
    private static final String ROLE = "role";

    // Private constructor to hide implicit public one
    private AccountDAO() {
        throw new UnsupportedOperationException("Utility class should not be instantiated");
    }

    // Custom exception for AccountDAO errors
    public static class AccountDAOException extends Exception {
        public AccountDAOException(String message, Throwable cause) {
            super(message, cause);
        }
    }

    public static Account getAccount(String email, String password) throws AccountDAOException {
        Account acc = null;
        try (Connection cn = DBUtils.makeConnection()) {
            if (cn != null) {
                String sql = "SELECT accID, email, password, fullname, phone, status, role "
                        + "FROM dbo.Accounts "
                        + "WHERE status = 1 AND email = ? AND password = ? COLLATE Latin1_General_CS_AS";
                try (PreparedStatement pst = cn.prepareStatement(sql)) {
                    pst.setString(1, email);
                    pst.setString(2, password);
                    try (ResultSet rs = pst.executeQuery()) {
                        if (rs.next()) {
                            int accID = rs.getInt(ACCOUNT_ID);
                            String emailDb = rs.getString(EMAIL);
                            String passwordDb = rs.getString(PASSWORD);
                            String fullname = rs.getString(FULLNAME);
                            String phone = rs.getString(PHONE);
                            int status = rs.getInt(STATUS);
                            int role = rs.getInt(ROLE);
                            acc = new Account(accID, emailDb, passwordDb, fullname, status, phone, role);
                        }
                    }
                }
            }
        } catch (Exception e) {
            throw new AccountDAOException("Error retrieving account", e);
        }
        return acc;
    }

    public static List<Account> getAccounts() throws AccountDAOException {
        List<Account> list = new ArrayList<>();
        try (Connection cn = DBUtils.makeConnection()) {
            if (cn != null) {
                String sql = "SELECT accID, email, password, fullname, phone, status, role FROM dbo.Accounts";
                try (Statement st = cn.createStatement(); ResultSet rs = st.executeQuery(sql)) {
                    while (rs.next()) {
                        int accID = rs.getInt(ACCOUNT_ID);
                        String email = rs.getString(EMAIL);
                        String password = rs.getString(PASSWORD);
                        String fullname = rs.getString(FULLNAME);
                        String phone = rs.getString(PHONE);
                        int status = rs.getInt(STATUS);
                        int role = rs.getInt(ROLE);
                        Account acc = new Account(accID, email, password, fullname, status, phone, role);
                        list.add(acc);
                    }
                }
            }
        } catch (Exception e) {
            throw new AccountDAOException("Error retrieving accounts", e);
        }
        return list;
    }

    public static Account getAccountByToken(String token) throws AccountDAOException {
        Account acc = null;
        try (Connection cn = DBUtils.makeConnection()) {
            if (cn != null) {
                String sql = "SELECT accID, email, password, fullname, phone, status, role, token "
                        + "FROM dbo.Accounts "
                        + "WHERE token = ? COLLATE Latin1_General_CS_AS";
                try (PreparedStatement pst = cn.prepareStatement(sql)) {
                    pst.setString(1, token);
                    try (ResultSet rs = pst.executeQuery()) {
                        if (rs.next()) {
                            int accID = rs.getInt(ACCOUNT_ID);
                            String email = rs.getString(EMAIL);
                            String password = rs.getString(PASSWORD);
                            String fullname = rs.getString(FULLNAME);
                            String phone = rs.getString(PHONE);
                            int status = rs.getInt(STATUS);
                            int role = rs.getInt(ROLE);
                            String tokenDb = rs.getString("token");
                            acc = new Account(accID, email, password, fullname, status, phone, role, tokenDb);
                        }
                    }
                }
            }
        } catch (Exception e) {
            throw new AccountDAOException("Error retrieving account by token", e);
        }
        return acc;
    }

    public static List<Account> getAccountsByEmail(String email) throws AccountDAOException {
        List<Account> list = new ArrayList<>();
        try (Connection cn = DBUtils.makeConnection()) {
            if (cn != null) {
                String sql = "SELECT accID, email, password, fullname, phone, status, role "
                        + "FROM dbo.Accounts "
                        + "WHERE email = ? COLLATE Latin1_General_CS_AS";
                try (PreparedStatement pst = cn.prepareStatement(sql)) {
                    pst.setString(1, email);
                    try (ResultSet rs = pst.executeQuery()) {
                        while (rs.next()) {
                            int accID = rs.getInt(ACCOUNT_ID);
                            String emailDb = rs.getString(EMAIL);
                            String password = rs.getString(PASSWORD);
                            String fullname = rs.getString(FULLNAME);
                            String phone = rs.getString(PHONE);
                            int status = rs.getInt(STATUS);
                            int role = rs.getInt(ROLE);
                            Account acc = new Account(accID, emailDb, password, fullname, status, phone, role);
                            list.add(acc);
                        }
                    }
                }
            }
        } catch (Exception e) {
            throw new AccountDAOException("Error retrieving accounts by email", e);
        }
        return list;
    }
}
