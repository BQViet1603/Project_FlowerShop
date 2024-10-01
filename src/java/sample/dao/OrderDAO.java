package sample.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import sample.dto.Order;
import sample.dto.OrderDetail;
import sample.utils.DBUtils;

public class OrderDAO {

    public static ArrayList<Order> getOrders(String email) {
        ArrayList<Order> list = new ArrayList<>();
        String sql = "SELECT OrderID, OrdDate, shipDate, Orders.status AS 'status', Orders.AccID AS 'AccID' "
                + "FROM Orders JOIN Accounts ON Orders.AccID = Accounts.accID "
                + "WHERE Accounts.email = ?";
        try (Connection cn = DBUtils.makeConnection(); PreparedStatement pst = cn.prepareStatement(sql)) {
            pst.setString(1, email);
            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    int orderID = rs.getInt("OrderID");
                    String ordDate = rs.getString("OrdDate");
                    String shipDate = rs.getString("shipDate");
                    int status = rs.getInt("status");
                    int accID = rs.getInt("AccID");
                    list.add(new Order(orderID, ordDate, shipDate, status, accID));
                }
            }
        } catch (SQLException e) {
        }
        return list;
    }

    public static ArrayList<OrderDetail> getOrderDetail(int orderID) {
        ArrayList<OrderDetail> list = new ArrayList<>();
        String sql = "SELECT DetailId, OrderID, PID, PName, price, imgPath, quantity "
                + "FROM OrderDetails JOIN Plants ON OrderDetails.FID = Plants.PID "
                + "WHERE OrderID = ?";

        try (Connection cn = DBUtils.makeConnection(); PreparedStatement pst = cn.prepareStatement(sql)) {

            pst.setInt(1, orderID);
            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    int detailID = rs.getInt("DetailId");
                    int plantID = rs.getInt("PID");
                    String plantName = rs.getString("PName");
                    int price = rs.getInt("price");
                    String imgPath = rs.getString("imgPath");
                    int quantity = rs.getInt("quantity");
                    list.add(new OrderDetail(detailID, orderID, plantID, plantName, price, imgPath, quantity));
                }
            }
        } catch (SQLException e) {
        }
        return list;
    }

    public static boolean updateOrderStatus(String orderID, int status) {
        String sql = "UPDATE dbo.Orders SET status = ? WHERE OrderID = ?";
        try (Connection cn = DBUtils.makeConnection(); PreparedStatement pst = cn.prepareStatement(sql)) {

            pst.setInt(1, status);
            pst.setString(2, orderID);
            return pst.executeUpdate() > 0;
        } catch (SQLException e) {
        }
        return false;
    }

    public static boolean insertOrder(String email, HashMap<String, Integer> cart) {
        String getAccIDSql = "SELECT accID FROM Accounts WHERE email = ?";
        String insertOrderSql = "INSERT INTO Orders(OrdDate, status, AccID) VALUES(?, ?, ?)";
        String getOrderIDSql = "SELECT TOP 1 OrderID FROM Orders ORDER BY OrderID DESC";
        String insertOrderDetailSql = "INSERT INTO OrderDetails VALUES(?, ?, ?)";
        try (Connection cn = DBUtils.makeConnection()) {
            cn.setAutoCommit(false);

            int accID = 0, orderID = 0;
            try (PreparedStatement pst = cn.prepareStatement(getAccIDSql)) {
                pst.setString(1, email);
                try (ResultSet rs = pst.executeQuery()) {
                    if (rs.next()) {
                        accID = rs.getInt("accID");
                    }
                }
            }

            Date orderDate = new Date(System.currentTimeMillis());
            try (PreparedStatement pst = cn.prepareStatement(insertOrderSql)) {
                pst.setDate(1, orderDate);
                pst.setInt(2, 1);
                pst.setInt(3, accID);
                pst.executeUpdate();
            }

            try (PreparedStatement pst = cn.prepareStatement(getOrderIDSql); ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    orderID = rs.getInt("OrderID");
                }
            }

            for (String pid : cart.keySet()) {
                try (PreparedStatement pst = cn.prepareStatement(insertOrderDetailSql)) {
                    pst.setInt(1, orderID);
                    pst.setInt(2, Integer.parseInt(pid.trim()));
                    pst.setInt(3, cart.get(pid));
                    pst.executeUpdate();
                }
            }

            cn.commit();
            return true;
        } catch (SQLException e) {
            try (Connection cn = DBUtils.makeConnection()) {
                cn.rollback();
            } catch (SQLException ex) {
            }
        }
        return false;
    }

    public static ArrayList<Order> getOrdersByDate(Date from, Date to) {
        ArrayList<Order> list = new ArrayList<>();
        String sql = "SELECT OrderID, OrdDate, shipDate, status, AccID FROM Orders WHERE OrdDate BETWEEN ? AND ?";

        try (Connection cn = DBUtils.makeConnection(); PreparedStatement pst = cn.prepareStatement(sql)) {

            pst.setDate(1, from);
            pst.setDate(2, to);
            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    int orderID = rs.getInt("OrderID");
                    String ordDate = rs.getString("OrdDate");
                    String shipDate = rs.getString("shipDate");
                    int status = rs.getInt("status");
                    int accID = rs.getInt("AccID");
                    list.add(new Order(orderID, ordDate, shipDate, status, accID));
                }
            }
        } catch (SQLException e) {
        }
        return list;
    }

    public static ArrayList<Order> getOrders() {
        ArrayList<Order> list = new ArrayList<>();
        String sql = "SELECT OrderID, OrdDate, shipDate, Orders.status AS 'status', Orders.AccID AS 'AccID' "
                + "FROM Orders JOIN Accounts ON Orders.AccID = Accounts.accID";

        try (Connection cn = DBUtils.makeConnection(); Statement st = cn.createStatement(); ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                int orderID = rs.getInt("OrderID");
                String ordDate = rs.getString("OrdDate");
                String shipDate = rs.getString("shipDate");
                int status = rs.getInt("status");
                int accID = rs.getInt("AccID");
                list.add(new Order(orderID, ordDate, shipDate, status, accID));
            }
        } catch (SQLException e) {
        }
        return list;
    }
}
