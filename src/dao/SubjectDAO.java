package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Subject;


public class SubjectDAO extends DAO {

	// 学生を全件取得するgetメソッド
	public List<Subject> filter(School school) throws Exception {
		List<Subject> subject=new ArrayList<>();

		Connection con=getConnection();


		PreparedStatement st=con.prepareStatement(
			"SELECT CD , NAME FROM SUBJECT");
		ResultSet rs=st.executeQuery();

		while (rs.next()) {

			Subject subjectList=new Subject();
			subjectList.setCd(rs.getString("CD"));
			subjectList.setName(rs.getString("NAME"));

			subjectList.setSchool(school);
			subject.add(subjectList);
		}

		st.close();
		con.close();

		return subject;
	}


	public Subject get(String cd , School school) throws Exception {
		Subject subject = null;
		Connection con=getConnection();

		PreparedStatement st=con.prepareStatement(
			"SELECT * FROM SUBJECT WHERE CD = ? ");
		st.setString(1, cd);
		ResultSet rs=st.executeQuery();

		if (rs.next()) {
			subject=new Subject();
			subject.setCd(rs.getString("CD"));
			subject.setName(rs.getString("NAME"));
			subject.setSchool(school);
		}
		return subject;
	}

	// 新規登録
	public boolean save(Subject subject) throws Exception {
		Connection con=getConnection();

		PreparedStatement st=con.prepareStatement(
			"INSERT INTO SUBJECT VALUES(?,?,?)");
		st.setString(1, subject.getSchool().getCd());
		st.setString(2, subject.getCd());
		st.setString(3, subject.getName());
		// insertしたレコード件数が返ってくる
		int line = st.executeUpdate();

		st.close();
		con.close();

		return line > 0;
	}

	//更新
	public boolean update(Subject subject) throws Exception {

		Connection con=getConnection();


		PreparedStatement st=con.prepareStatement(
			"UPDATE SUBJECT SET NAME = ? WHERE CD = ?");
		st.setString(1, subject.getName());
		st.setString(2, subject.getCd());


		int line = st.executeUpdate();


		return line > 0;
	}

	public boolean delete(Subject subject) throws Exception {
		Connection con=getConnection();

		PreparedStatement st=con.prepareStatement(
			"DELETE FROM SUBJECT WHERE CD = ?");
		st.setString(1, subject.getCd());
		int line = st.executeUpdate();

		st.close();
		con.close();

		return line > 0;
	}

}