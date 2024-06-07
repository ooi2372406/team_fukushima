package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.Student;
import bean.Subject;


public class SubjectDAO extends DAO {

	// 学生を全件取得するstudentAllメソッド
	public List<Subject> studentAll() throws Exception {
		List<Subject> studentList=new ArrayList<>();

		Connection con=getConnection();

		// コース情報も必要なのでcourseテーブルからも取得する
		PreparedStatement st=con.prepareStatement(
			"SELECT * FROM subject WHERE ");
		ResultSet rs=st.executeQuery();

		while (rs.next()) {
			//学生ビーンをインスタンス化して情報をセット
			Student student=new Student();
			student.setStudent_id(rs.getInt("student_id"));
			student.setStudent_name(rs.getString("student_name"));
			student.setCourse_id(rs.getInt("course_id"));
			// コースビーンをインスタンス化して情報をセット
			Course course=new Course();
			course.setCourse_id(rs.getInt("course_id"));
			course.setCourse_name(rs.getString("course_name"));
			// コースビーンを学生ビーンにセット
			student.setCourse(course);
			studentList.add(student);
		}

		st.close();
		con.close();

		return studentList;
	}
}