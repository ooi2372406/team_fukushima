package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.School;

public class ClassNumDao extends DAO {

    public List<String> filter(School school) throws Exception {
        List<String> list = new ArrayList<>();

        String query = "SELECT DISTINCT CLASS_NUM FROM STUDENT WHERE SCHOOL_CD = ? ORDER BY CLASS_NUM";

        // try-with-resources構文を使用してリソースを自動的にクローズする
        try (Connection con = getConnection();
             PreparedStatement st = con.prepareStatement(query)) {

            st.setString(1, school.getCd());
            try (ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    String classNum = rs.getString("CLASS_NUM");
                    list.add(classNum);
                }
            }
        }

        return list;
    }
}
