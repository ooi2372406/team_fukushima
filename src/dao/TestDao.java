package dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Student;
import bean.Subject;
import bean.Test;

public class TestDao extends DAO {
    private String basesql = "SELECT * FROM TEST WHERE SCHOOL_CD=?;";



    public Test get(Student student, Subject subject, School school, int no) throws Exception {
    	Test test = new Test();
        Connection con = getConnection();

        PreparedStatement st = con.prepareStatement ("SELECT * FROM TEST WHERE STUDENT_NO = ? AND SUBJECT_CD = ? AND SCHOOL_CD = ? AND NO = ?");
        st.setString(1, student.getNo());
       // System.out.println("学生番号" + student.getNo());


        st.setString(2, subject.getCd());
       // System.out.println("科目コード" + subject.getCd());

        st.setString(3, school.getCd());
        //System.out.println("学校コード" + school.getCd());

        st.setInt(4, no);
       // System.out.println("回数" + no);

        ResultSet rs = st.executeQuery();

        StudentDao studao = new StudentDao();
        SubjectDAO  subdao = new SubjectDAO();
        SchoolDao schooldao = new SchoolDao();

        if (rs.next()) {

               test.setStudent(studao.get(rs.getString("STUDENT_NO")));
               test.setSubject(subdao.get(rs.getString("SUBJECT_CD") , school));
               test.setSchool(schooldao.get(rs.getString("SCHOOL_CD")));
               test.setNo(no);
               test.setPoint(rs.getInt("POINT"));
               test.setClassNum(rs.getString("CLASS_NUM"));



        }

        rs.close();
        st.close();
        con.close();

        return test;
    }

    // postFilterメソッド
    private List<Test> postFilter(ResultSet set, School school) throws SQLException {
        List<Test> tests = new ArrayList<>();
        try {
            while (set.next()) {
                Test test = new Test();

                test.setClassNum("CLASS_NUM");
                test.setNo(Integer.parseInt("NO"));
                test.setSchool(school);
               // test.setSubject(subject);
                test.setPoint(Integer.parseInt("POINT"));
                tests.add(test);
            }
        } catch (SQLException | NullPointerException e) {
            e.printStackTrace();
        }
        return tests;
    }

    // 修正されたfilterメソッド
    public List<Test> filter(Test test, int entYear, String classNum, Subject subject, int num, Student student) throws Exception {
        List<Test> tests = new ArrayList<>();

        Connection con = getConnection();
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            st = con.prepareStatement("SELECT TEST.*, STUDENT.* , SUBJECT.* " +
                     "FROM TEST " +
                     "JOIN STUDENT ON TEST.STUDENT_NO = STUDENT.NO " +
                     "JOIN SUBJECT ON TEST.SUBJECT_CD = SUBJECT.CD " +
                     "WHERE STUDENT.ENT_YEAR = ? " +
                     "AND STUDENT.CLASS_NUM = ? " +
                     "AND SUBJECT.NAME = ? " +
                     "AND TEST.NO = ? " +
                     "ORDER BY TEST.NO ASC");
            st.setInt(1, entYear);

            st.setString(2, classNum);

            st.setString(3, subject.getName());

            st.setInt(4, test.getNo());

            rs = st.executeQuery();

            while (rs.next()){
            	 Test resultTest = new Test();

                 resultTest.setNo(rs.getInt("NO"));
                 resultTest.setClassNum(rs.getString("CLASS_NUM"));
                 resultTest.setPoint(rs.getInt("POINT"));

                 // Create a new Student object for each test result
                 Student resultStudent = new Student();
                 resultStudent.setNo(rs.getString("STUDENT.NO"));
                 resultStudent.setName(rs.getString("STUDENT.NAME"));
                 resultStudent.setEntYear(rs.getInt("STUDENT.ENT_YEAR"));

                 resultTest.setStudent(resultStudent);

                 // Set the subject for the test result
                 Subject resultSubject = new Subject();
                 resultSubject.setName(rs.getString("SUBJECT.NAME"));
                 resultSubject.setCd(rs.getString("SUBJECT.CD"));
                 resultTest.setSubject(resultSubject);
                 tests.add(resultTest);

            }
        } catch (Exception e) {
            throw e;
        } finally {
            if (st != null) {
                try {
                    st.close();
                } catch (SQLException sqle) {
                    throw sqle;
                }
            }
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException sqle) {
                    throw sqle;
                }
            }
        }
        return tests;
    }



    // パブリックのsaveメソッド

    	public boolean save(List<Test> tests) throws Exception {
    	    Connection con = null;
    	    boolean allSuccess = true; // すべての操作が成功したかどうかを示すフラグ

    	    try {
    	        con = getConnection();
    	        con.setAutoCommit(false); // トランザクションの開始

    	        for (Test test : tests) {
    	            boolean success = save(test, con);
    	            if (!success) {
    	                allSuccess = false;
    	                break; // 1つでも失敗したらループを抜ける
    	            }
    	        }

    	        if (allSuccess) {
    	            con.commit(); // すべて成功した場合はコミット
    	        } else {
    	            con.rollback(); // 1つでも失敗した場合はロールバック
    	        }

    	        return allSuccess;
    	    } catch (Exception e) {
    	        if (con != null) {
    	            con.rollback(); // 例外が発生した場合もロールバック
    	        }
    	        throw e; // 例外を再スロー
    	    } finally {
    	        if (con != null) {
    	            con.close(); // コネクションを閉じる
    	        }
    	    }
    	}


    // プライベートのsaveメソッド
    public boolean save(Test test , Connection con) throws Exception {

    	con = getConnection();
        PreparedStatement st = con.prepareStatement(
            "UPDATE TEST SET POINT = ? WHERE STUDENT_NO = ? AND SUBJECT_CD = ? AND SCHOOL_CD = ? AND NO = ?"
        );
        st.setInt(1, test.getPoint());


        st.setString(2, test.getStudent().getNo());


        st.setString(3, test.getSubject().getCd());


        st.setString(4, test.getStudent().getSchool().getCd());


        st.setInt(5, test.getNo());


        int line = st.executeUpdate();



        st.close();
        con.close();

        return line  > 0;



    	}
    }

/*
    private boolean delete(Test test, Connection connection) throws SQLException {
        for (Test test : list) {
            delete(test);
        }
        return true;
    }
}
/*
    public boolean delete(Test test) throws SQLException {
        Connection con = getConnection();
        PreparedStatement st = con.prepareStatement(
            "DELETE FROM TEST WHERE STUDENT_NO = ? AND SUBJECT_CD = ? AND SCHOOL_CD = ? AND NO = ?"
        );
        st.setString(1, test.getStudentNo());
        st.setString(2, test.getSubjectCd());
        st.setString(3, test.getSchoolCd());
        st.setInt(4, test.getNo());
        st.executeUpdate();

        st.close();
        con.close();

        return true;
    }

    public Connection getConnection() throws SQLException {
        // 以下にあなたのデータベース接続情報を入力してください
  DriverManager.getConnection("jdbc:h2:tcp://localhost/~/db_TEAM_FUKUSHIMA", "sa", "");

    }



}
*/