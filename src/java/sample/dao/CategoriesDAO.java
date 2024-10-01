package sample.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import sample.dto.Categories;
import sample.utils.DBUtils;

public class CategoriesDAO {

    public static ArrayList<Categories> getCategories() throws Exception {
        ArrayList<Categories> list = new ArrayList<>();
        try (Connection cn = DBUtils.makeConnection()) {
            if (cn != null) {
                String sql = "SELECT CateID, CateName FROM Categories";
                try (Statement st = cn.createStatement(); ResultSet rs = st.executeQuery(sql)) {
                    while (rs.next()) {
                        int cateID = rs.getInt("CateID");
                        String cateName = rs.getString("CateName");
                        Categories cate = new Categories(cateID, cateName);
                        list.add(cate);
                    }
                }
            }
        } catch (Exception e) {
            throw new Exception("Error retrieving categories", e);
        }
        return list;
    }
}
