package student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.InitialContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import bean.School;
import bean.Teacher;
import bean.TestListSubject;
import tool.Action;
import util.Util;

public class TestListSubjectExecuteAction extends Action {
	public String execute(
		HttpServletRequest request, HttpServletResponse response
	) throws Exception {
				Teacher teacher=Util.getUser(request);
				School school=teacher.getSchool();
				//getParameterメソッドでデータを受け取る
				TestListSubject testListSubject=null;
				String Ent_year=request.getParameter("f1");
				String class_number=request.getParameter("f2");
				String subject=request.getParameter("f3");

				try {
					InitialContext ic=new InitialContext();
					DataSource ds=(DataSource)ic.lookup(
						"java:/comp/env/jdbc/team\fukushima");
					Connection con=ds.getConnection();

					TestListAction action = new TestListAction();
					action.setTestListSubject(request,response);

					PreparedStatement st = con.prepareStatement(
						    "SELECT test.student_no, test.subject_cd, test.school_cd, test.no, test.point, test.class_num ,subject.name AS subject_name" +
                       "FROM test " +
                       "JOIN student ON test.student_no = student.no " +
                       "JOIN subject ON test.subject_cd = subject.cd " +
                       "WHERE ent_year LIKE ?"
                       + "AND class_num LIKE ?"
                       + "AND subject_name LIKE ?");

					st.setString(1, "%"+Ent_year+"%");
					st.setString(2, "%"+class_number+"%");
					st.setString(3, "%"+subject+"%");

					ResultSet rs=st.executeQuery();

					while (rs.next()) {
						testListSubject=new TestListSubject();
						testListSubject.setEntYear(rs.getInt("ent_year"));
						testListSubject.setClassNum(rs.getString("class_num"));
						testListSubject.setStudentName(rs.getString("subject_name"));
					}

					st.close();
					con.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
		return "test_list_student.jsp";
	}
}
