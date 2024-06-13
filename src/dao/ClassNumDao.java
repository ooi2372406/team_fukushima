package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.School;

public class ClassNumDao  extends DAO {
	public List<String>Filter (School school) throws Exception {
		List<String> list = new ArrayList<>();
		Connection con=getConnection();

		PreparedStatement st=con.prepareStatement(
			"SELECT * FROM CLASS_NUM WHERE SCHOOL_CD = ? ");
		st.setString(1,school.getCd());
		ResultSet rs=st.executeQuery();

		while (rs.next()) {
			String classNum = rs.getString("CLASS_NUM");
			list.add(classNum);
		}
		return list;
	}

}
