package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.ClassNum;
import bean.School;

public class ClassNumDao  extends DAO {
	public List<String>Filter (School school) throws Exception {
		List<String> list = new ArrayList<>();
		Connection con=getConnection();

		PreparedStatement st=con.prepareStatement(
			"SELECT * FROM CLASS_NUM WHERE SCHOOL_CD = ? ");
		st.setString(1,school.getCd());
		ResultSet rs=st.executeQuery();

		if (rs.next()) {
			ClassNum classnum = new ClassNum();
			classnum.setSchoolCd(rs.getString("SCHOOL_CD"));
			classnum.setClassNum(rs.getString("CLASS_NUM"));
		}
		return list;
	}

}
