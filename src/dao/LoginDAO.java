package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.Login;

public class LoginDAO extends DAO{
		public List<Login> search(String id , String password) throws Exception {
			List<Login> teacher=new ArrayList<>();

			Connection con=getConnection();

			PreparedStatement st=con.prepareStatement(
			"SELECT *  FROM TEACHER WHERE id = ? AND password = ?");
			st.setString(1, id);
			st.setString(2, password);
			ResultSet rs=st.executeQuery();

			while (rs.next()) {
				Login p=new Login();
				// getのなかの引数はｓｑｌのカラム名をとってきている
				p.setId(rs.getString("ID"));
				p.setPassword(rs.getString("PASSWORD"));
				p.setName(rs.getString("NAME"));
				p.setSchoolCd(rs.getString("SCHOOL_CD"));
				if (password.equals(p.getPassword())) {
				teacher.add(p);
				}
			}

			st.close();
			con.close();

		return teacher;
	}

}