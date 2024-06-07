package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Subject;


public class SubjectDAO extends DAO {

	// 学生を全件取得するstudentAllメソッド
	public List<Subject> get(School school , String cd) throws Exception {
		List<Subject> subject=new ArrayList<>();

		Connection con=getConnection();

		// コース情報も必要なのでcourseテーブルからも取得する
		PreparedStatement st=con.prepareStatement(
			"SELECT CD , NAME FROM SUBJECT");
		ResultSet rs=st.executeQuery();

		while (rs.next()) {
			//学生ビーンをインスタンス化して情報をセット
			Subject subjectList=new Subject();
			subjectList.setCd(rs.getString("CD"));
			subjectList.setName(rs.getString("NAME"));


			subject.add(subjectList);
		}

		st.close();
		con.close();

		return subject;
	}

	public boolean save(Subject subject) throws Exception {
		Connection con=getConnection();

		PreparedStatement st=con.prepareStatement(
			"INSERT INTO SUBJECT VALUES(?,?,?)");
		st.setString(1, subject.getSchoolCd());
		st.setString(2, subject.getCd());
		st.setString(3, subject.getName());
		// insertしたレコード件数が返ってくる
		int line = st.executeUpdate();

		st.close();
		con.close();

		return line > 0;
	}

}