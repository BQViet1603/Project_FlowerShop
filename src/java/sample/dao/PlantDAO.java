package sample.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import sample.dto.Plant;
import sample.utils.DBUtils;

public class PlantDAO {

    // Method to get plants based on a search keyword and category/name filter
    public static ArrayList<Plant> getPlants(String keyword, String searchby) {
        ArrayList<Plant> list = new ArrayList<>();

        // Using try-with-resources to handle connection and statement
        try (Connection cn = DBUtils.makeConnection()) {
            if (cn != null) {
                String sql = """
                             select PID, PName, price, imgPath, description, status, Plants.CateID as 'CateID', CateName
                             from Plants join Categories on Plants.CateID=Categories.CateID
                             """;
                if (searchby != null && !searchby.isEmpty()) {
                    if (searchby.equalsIgnoreCase("byname")) {
                        sql += " where Plants.PName like ?";
                    } else if (searchby.equalsIgnoreCase("bycategory")) {
                        sql += " where CateName like ?";
                    }
                }

                try (PreparedStatement pst = cn.prepareStatement(sql)) {
                    if (searchby != null && !searchby.isEmpty()) {
                        pst.setString(1, "%" + keyword + "%");
                    }
                    try (ResultSet rs = pst.executeQuery()) {
                        while (rs.next()) {
                            int id = rs.getInt("PID");
                            String name = rs.getString("PName");
                            int price = rs.getInt("price");
                            String imgpath = rs.getString("imgPath");
                            String description = rs.getString("description");
                            int status = rs.getInt("status");
                            int cateid = rs.getInt("CateID");
                            String catename = rs.getString("CateName");
                            Plant plant = new Plant(id, name, price, imgpath, description, status, cateid, catename);
                            list.add(plant);
                        }
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();  // Log the error for debugging
        }
        return list;
    }

    // Method to get a specific plant by its ID
    public static Plant getPlant(int id) throws Exception {
        Plant p = null;

        // Using try-with-resources for connection and prepared statement
        try (Connection cn = DBUtils.makeConnection()) {
            if (cn != null) {
                String sql = """
                             select PID, PName, price, imgPath, description, status, CateID
                             from dbo.Plants
                             where PID=?""";
                
                try (PreparedStatement pst = cn.prepareStatement(sql)) {
                    pst.setInt(1, id);
                    
                    try (ResultSet rs = pst.executeQuery()) {
                        if (rs != null && rs.next()) {
                            String name = rs.getString("PName");
                            int price = rs.getInt("price");
                            String imgpath = rs.getString("imgPath");
                            String description = rs.getString("description");
                            int status = rs.getInt("status");
                            int cateid = rs.getInt("CateID");
                            p = new Plant(id, name, price, imgpath, description, status, cateid, name);
                        }
                    }
                }
            }
        }
        return p;
    }
}
