package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Teacher;


public class TeacherDAO extends DAO{
		public List<Teacher> login(String id , String password) throws Exception {
			List<Teacher> teacher=new ArrayList<>();

			Connection con=getConnection();

			PreparedStatement st=con.prepareStatement(
			"SELECT *  FROM TEACHER WHERE id = ? AND password = ?");
			st.setString(1, id);
			st.setString(2, password);
			ResultSet rs=st.executeQuery();

			while (rs.next()) {
				Teacher p=new Teacher();
				// getのなかの引数はｓｑｌのカラム名をとってきている
				p.setId(rs.getString("ID"));
				p.setPassword(rs.getString("PASSWORD"));
				p.setName(rs.getString("NAME"));

				 School school = new School();
				    school.setCd(rs.getString("SCHOOL_CD"));
				    p.setSchool(school);

				if (password.equals(p.getPassword())) {
				teacher.add(p);

				}}
				st.close();
				con.close();

					return teacher;


			}


			public List<Teacher> get(String id) throws Exception {
				List<Teacher> teacher=new ArrayList<>();

				Connection con=getConnection();


				PreparedStatement st=con.prepareStatement(
					"SELECT * FROM SUBJECT WHERE ID = ?");
					st.setString(1 , id);
				ResultSet rs=st.executeQuery();

				while (rs.next()) {
					Teacher teacherList = new Teacher();
					teacherList.setId(rs.getString("ID"));
					teacherList.setName(rs.getString("NAME"));
					teacherList.setPassword(rs.getString("PASSWORD"));

					School school = new School();
					school.setCd(rs.getString("SCHOOL_CD"));


					teacher.add(teacherList);
				}

				st.close();
				con.close();

				return teacher;
			}




}