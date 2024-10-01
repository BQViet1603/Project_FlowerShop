package sample.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import sample.dto.Account;
import sample.utils.DBUtils;

public class AccountDAO {

    public static Account getAccount(String email, String password) {
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
                            int accID = rs.getInt("accID");
                            String emailDb = rs.getString("email");
                            String passwordDb = rs.getString("password");
                            String fullname = rs.getString("fullname");
                            String phone = rs.getString("phone");
                            int status = rs.getInt("status");
                            int role = rs.getInt("role");
                            acc = new Account(accID, emailDb, passwordDb, fullname, status, phone, role);
                        }
                    }
                }
            }
        } catch (Exception e) {
        }
        return acc;
    }

    public static ArrayList<Account> getAccounts() throws Exception {
        ArrayList<Account> list = new ArrayList<>();
        try (Connection cn = DBUtils.makeConnection()) {
            if (cn != null) {
                String sql = "SELECT accID, email, password, fullname, phone, status, role FROM dbo.Accounts";
                try (Statement st = cn.createStatement(); ResultSet rs = st.executeQuery(sql)) {
                    while (rs.next()) {
                        int accID = rs.getInt("accID");
                        String email = rs.getString("email");
                        String password = rs.getString("password");
                        String fullname = rs.getString("fullname");
                        String phone = rs.getString("phone");
                        int status = rs.getInt("status");
                        int role = rs.getInt("role");
                        Account acc = new Account(accID, email, password, fullname, status, phone, role);
                        list.add(acc);
                    }
                }
            }
        }
        return list;
    }

    public static boolean updateAccountStatus(String email, int status) {
        boolean result = false;
        try (Connection cn = DBUtils.makeConnection()) {
            if (cn != null) {
                String sql = "UPDATE Accounts SET status = ? WHERE email = ?";
                try (PreparedStatement pst = cn.prepareStatement(sql)) {
                    pst.setInt(1, status);
                    pst.setString(2, email);
                    result = pst.executeUpdate() > 0;
                }
            }
        } catch (Exception e) {
        }
        return result;
    }

    public static boolean updateAccount(String email, String newPassword, String newFullname, String newPhone) {
        boolean result = false;
        try (Connection cn = DBUtils.makeConnection()) {
            if (cn != null) {
                String sql = "UPDATE Accounts SET password = ?, fullname = ?, phone = ? WHERE email = ?";
                try (PreparedStatement pst = cn.prepareStatement(sql)) {
                    pst.setString(1, newPassword);
                    pst.setString(2, newFullname);
                    pst.setString(3, newPhone);
                    pst.setString(4, email);
                    result = pst.executeUpdate() > 0;
                }
            }
        } catch (Exception e) {
        }
        return result;
    }

    public static int insertAccount(String newEmail, String newPassword, String newFullname, String newPhone, int newStatus, int newRole) throws Exception {
        int result = 0;
        try (Connection cn = DBUtils.makeConnection()) {
            if (cn != null) {
                String sql = "INSERT INTO dbo.Accounts (email, password, fullname, phone, status, role) VALUES (?, ?, ?, ?, ?, ?)";
                try (PreparedStatement pst = cn.prepareStatement(sql)) {
                    pst.setString(1, newEmail);
                    pst.setString(2, newPassword);
                    pst.setString(3, newFullname);
                    pst.setString(4, newPhone);
                    pst.setInt(5, newStatus);
                    pst.setInt(6, newRole);
                    result = pst.executeUpdate();
                }
            }
        }
        return result;
    }

    public static boolean updateToken(String email, String token) {
        boolean result = false;
        try (Connection cn = DBUtils.makeConnection()) {
            if (cn != null) {
                String sql = "UPDATE Accounts SET token = ? WHERE email = ?";
                try (PreparedStatement pst = cn.prepareStatement(sql)) {
                    pst.setString(1, token);
                    pst.setString(2, email);
                    result = pst.executeUpdate() > 0;
                }
            }
        } catch (Exception e) {
        }
        return result;
    }

    public static Account getAccountByToken(String token) {
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
                            int accID = rs.getInt("accID");
                            String email = rs.getString("email");
                            String password = rs.getString("password");
                            String fullname = rs.getString("fullname");
                            String phone = rs.getString("phone");
                            int status = rs.getInt("status");
                            int role = rs.getInt("role");
                            String tokenDb = rs.getString("token");
                            acc = new Account(accID, email, password, fullname, status, phone, role, tokenDb);
                        }
                    }
                }
            }
        } catch (Exception e) {
        }
        return acc;
    }

    public static ArrayList<Account> getAccountsByEmail(String email) throws Exception {
        ArrayList<Account> list = new ArrayList<>();
        try (Connection cn = DBUtils.makeConnection()) {
            if (cn != null) {
                String sql = "SELECT accID, email, password, fullname, phone, status, role "
                        + "FROM dbo.Accounts "
                        + "WHERE email = ? COLLATE Latin1_General_CS_AS";
                try (PreparedStatement pst = cn.prepareStatement(sql)) {
                    pst.setString(1, email);
                    try (ResultSet rs = pst.executeQuery()) {
                        while (rs.next()) {
                            int accID = rs.getInt("accID");
                            String emailDb = rs.getString("email");
                            String password = rs.getString("password");
                            String fullname = rs.getString("fullname");
                            String phone = rs.getString("phone");
                            int status = rs.getInt("status");
                            int role = rs.getInt("role");
                            Account acc = new Account(accID, emailDb, password, fullname, status, phone, role);
                            list.add(acc);
                        }
                    }
                }
            }
        }
        return list;
    }

    public static ArrayList<Account> getAccountByEmail(String search) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
