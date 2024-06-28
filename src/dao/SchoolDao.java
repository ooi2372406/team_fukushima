package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import bean.School;

public class SchoolDao extends DAO {
    public School get(String no) throws Exception {
        School school = null;

        String query = "SELECT * FROM SCHOOL WHERE CD = ?";

        // try-with-resources構文を使用してリソースを自動的にクローズする
        try (Connection con = getConnection();
             PreparedStatement st = con.prepareStatement(query)) {

            st.setString(1, no);

            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    school = new School();
                    school.setCd(rs.getString("CD"));
                    school.setName(rs.getString("NAME"));
                }
            }
        }

        return school;
    }
}
