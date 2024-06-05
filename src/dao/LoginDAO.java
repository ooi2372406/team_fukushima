package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.Loginbean;

public class LoginDAO extends DAO{
		public List<Loginbean> search(String id , String password) throws Exception {
			List<Loginbean> list=new ArrayList<>();

			Connection con=getConnection();

			PreparedStatement st=con.prepareStatement(
			"SELECT id , password , name FROM TEACHER WHERE id = ? AND password = ?");
			st.setString(1, id);
			st.setString(2, password);
			ResultSet rs=st.executeQuery();

			while (rs.next()) {
				Loginbean p=new Loginbean();
				p.setId(rs.getString("id"));
				p.setPassword(rs.getString("password"));
				p.setName(rs.getString("name"));
				if (password.equals(p.getPassword())) {
				list.add(p);
				}
			}

			st.close();
			con.close();

		return list;
	}

}