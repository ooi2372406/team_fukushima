package student;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.InitialContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import tool.Action;

public class TestListSubjectExecuteAction extends Action {
	public String execute(
		HttpServletRequest request, HttpServletResponse response
	) throws Exception {
				//getParameterメソッドでデータを受け取る
				String Ent_year=request.getParameter("f1");
				String class_number=request.getParameter("f2");
				String subject=request.getParameter("f3");

				try {
					InitialContext ic=new InitialContext();
					DataSource ds=(DataSource)ic.lookup(
						"java:/comp/env/jdbc/team\fukushima");
					Connection con=ds.getConnection();

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
					PrintWriter out = response.getWriter();

					while (rs.next()) {
						out.println(rs.getInt("ent_year"));
						out.println("：");
						out.println(rs.getInt("class_num"));
						out.println("：");
						out.println(rs.getString("subject_name"));
						out.println("<br>");
					}

					st.close();
					con.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
		return "test_list_student.jsp";
	}
}
