package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Subject;


public class SubjectDAO extends DAO {

	// 学生を全件取得するgetメソッド
	public List<Subject> filter(School school) throws Exception {
		List<Subject> subject=new ArrayList<>();

		Connection con=getConnection();

		ResultSet rs = null;

		PreparedStatement st = null;

		String all = "SELECT CD , NAME FROM SUBJECT WHERE SCHOOL_CD = ?";

		try{

			st=con.prepareStatement(all);
			st.setString(1, school.getCd());
			rs=st.executeQuery();

			while (rs.next()) {

				Subject subjectList=new Subject();
				subjectList.setCd(rs.getString("CD"));
				subjectList.setName(rs.getString("NAME"));
				subjectList.setSchool(school);
				subject.add(subjectList);
			}

		}catch (Exception e){
			throw e;
		}finally{
			// プリペアードステートメントを閉じる
			if (st != null){
				try{
					st.close();
				}catch (SQLException sqle){
					throw sqle;
				}
			}

			// コネクションを閉じる
			if (con != null){
				try {
					con.close();
				}catch (SQLException sqle){
					throw sqle;
				}
			}
		}

		return subject;
	}



	public Subject get(String cd , School school) throws Exception {
		Subject subject = null;
		Connection con=getConnection();
		PreparedStatement st = null;
		ResultSet rs = null;
		String select = "SELECT * FROM SUBJECT WHERE CD = ? ";


		try{
			st=con.prepareStatement(select);
			st.setString(1, cd);
			rs=st.executeQuery();

			if (rs.next()) {
				subject=new Subject();
				subject.setCd(rs.getString("CD"));
				subject.setName(rs.getString("NAME"));
				subject.setSchool(school);
			}
		}catch (Exception e){
			throw e;
		}finally{
			// プリペアードステートメントを閉じる
			if (st != null){
				try{
					st.close();
				}catch (SQLException sqle){
					throw sqle;
				}
			}

			// コネクションを閉じる
			if (con != null){
				try {
					con.close();
				}catch (SQLException sqle){
					throw sqle;
				}
			}
		}
		return subject;
	}

	// 新規登録
	public boolean save(Subject subject) throws Exception {
		Connection con=getConnection();

		PreparedStatement st = null;

		String insert = "INSERT INTO SUBJECT VALUES(? , ? , ?)";

		int line;

		try{
			st=con.prepareStatement(insert);
			st.setString(1, subject.getSchool().getCd());
			st.setString(2, subject.getCd());
			st.setString(3, subject.getName());
			// insertしたレコード件数が返ってくる
			line = st.executeUpdate();

		}catch (Exception e){
			throw e;
		}finally{
			// プリペアードステートメントを閉じる
			if (st != null){
				try{
					st.close();
				}catch (SQLException sqle){
					throw sqle;
				}
			}

			// コネクションを閉じる
			if (con != null){
				try {
					con.close();
				}catch (SQLException sqle){
					throw sqle;
				}
			}
		}

		return line > 0;
	}

	//更新
	public boolean update(Subject subject) throws Exception {

		Connection con=getConnection();

		PreparedStatement st = null;

		int line;

		String update = "UPDATE SUBJECT SET NAME = ? WHERE CD = ?";

		try{

			st=con.prepareStatement(update);
			st.setString(1, subject.getName());
			st.setString(2, subject.getCd());


			line = st.executeUpdate();

		}catch (Exception e){
			throw e;
		}finally{
			// プリペアードステートメントを閉じる
			if (st != null){
				try{
					st.close();
				}catch (SQLException sqle){
					throw sqle;
				}
			}

			// コネクションを閉じる
			if (con != null){
				try {
					con.close();
				}catch (SQLException sqle){
					throw sqle;
				}
			}
		}
		return line > 0;
	}

	public boolean delete(Subject subject) throws Exception {
		Connection con=getConnection();

		PreparedStatement st = null;

		String delete = "DELETE FROM SUBJECT WHERE CD = ?";

		int line;

		try{

		st=con.prepareStatement(delete);
		st.setString(1, subject.getCd());
		line = st.executeUpdate();

		}catch (Exception e){
			throw e;
		}finally{
			// プリペアードステートメントを閉じる
			if (st != null){
				try{
					st.close();
				}catch (SQLException sqle){
					throw sqle;
				}
			}

			// コネクションを閉じる
			if (con != null){
				try {
					con.close();
				}catch (SQLException sqle){
					throw sqle;
				}
			}
		}

		return line > 0;
	}

}

