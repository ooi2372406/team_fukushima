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

        Connection con = getConnection();

        PreparedStatement st = con.prepareStatement ("SELECT * FROM TEST WHERE STUDENT_NO = ? AND SUBJECT_CD = ? AND SCHOOL_CD = ? AND NO = ?");
        st.setString(1, student.getNo());
       //System.out.println("学生番号" + student.getNo());


        st.setString(2, subject.getCd());
        //System.out.println("科目コード" + subject.getCd());

        st.setString(3, school.getCd());
       // System.out.println("学校コード" + school.getCd());

        st.setInt(4, no);
        //System.out.println("回数" + no);



        // クエリを実行
        ResultSet rs = st.executeQuery();
            // 結果が存在するかどうかを確認
        Test test = null;

            if (rs.next()) {
                // テスト結果が存在する場合の処理
                //System.out.println("Test record found.");

                StudentDao studao = new StudentDao();
                SubjectDAO  subdao = new SubjectDAO();
                SchoolDao schooldao = new SchoolDao();

                test = new Test();

                test.setStudent(studao.get(rs.getString("STUDENT_NO")));
                test.setSubject(subdao.get(rs.getString("SUBJECT_CD") , school));
                test.setSchool(schooldao.get(rs.getString("SCHOOL_CD")));
                test.setNo(no);
                test.setPoint(rs.getInt("POINT"));
                test.setClassNum(rs.getString("CLASS_NUM"));

                test.setStudent(studao.get(rs.getString("STUDENT_NO")));
                test.setSubject(subdao.get(rs.getString("SUBJECT_CD") , school));
                test.setSchool(schooldao.get(rs.getString("SCHOOL_CD")));
                test.setNo(no);
                test.setPoint(rs.getInt("POINT"));
                test.setClassNum(rs.getString("CLASS_NUM"));
                // ここで rs.getXXX() を使用してデータを取得
            } else {
                // テスト結果が存在しない場合の処理
                //System.out.println("No test record found.");
                return test;
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
                //test.setSubject(subject);
                test.setPoint(Integer.parseInt("POINT"));
                tests.add(test);
            }
        } catch (SQLException | NullPointerException e) {
            e.printStackTrace();
        }
        return tests;
    }

    public List<Test> filter(Test test, Subject subject, int entYear, String classNum) throws Exception {
        List<Test> studentTestList = new ArrayList<>();
        Connection con = getConnection();
        PreparedStatement st = null;
        ResultSet rs = null;

        try {

            st = con.prepareStatement(
            		 "SELECT STUDENT.ENT_YEAR, STUDENT.CLASS_NUM, STUDENT.NO, STUDENT.NAME, TEST.POINT " +
            			        "FROM STUDENT " +
            			        "LEFT JOIN ( " +
            			        "    SELECT * " +
            			        "    FROM TEST " +
            			        "    WHERE SUBJECT_CD = (SELECT CD FROM SUBJECT WHERE NAME = ?) AND NO = ? " +
            			        ") AS TEST ON STUDENT.NO = TEST.STUDENT_NO " +
            			        "WHERE STUDENT.ENT_YEAR = ? AND STUDENT.CLASS_NUM = ? " +
            			        "ORDER BY STUDENT.NO"

            );

            st.setString(1, subject.getName());
            st.setInt(2, test.getNo());
            st.setInt(3, entYear);
            st.setString(4, classNum);
            //st.setInt(5, pageSize);
            //st.setInt(6, offset);

            rs = st.executeQuery();

            while (rs.next()) {
                Test resultTest = new Test();
                // テスト結果のポイントを取得し、NULLの場合はnullを設定する
                Integer point = (Integer) rs.getObject("POINT");
                if (rs.wasNull()) {
                    point = null;
                }
                resultTest.setPoint(point);
                Student resultStudent = new Student();
                resultStudent.setEntYear(rs.getInt("STUDENT.ENT_YEAR"));
                resultStudent.setClassNum(rs.getString("STUDENT.CLASS_NUM"));
                resultStudent.setNo(rs.getString("STUDENT.NO"));
                resultStudent.setName(rs.getString("STUDENT.NAME"));

                resultTest.setStudent(resultStudent);

                studentTestList.add(resultTest);
            }
        } catch (Exception e) {
            e.printStackTrace(); // エラーの詳細を出力
            throw e; // エラーを再スローして上位層で処理
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }
            if (st != null) {
                try {
                    st.close();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }
        }
        return studentTestList;
    }

    // ページネーション対応の全件取得filterメソッド
    public List<Test> filter(int page, int pageSize) throws Exception {
        List<Test> studentTestList = new ArrayList<>();
        Connection con = getConnection();
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            int offset = (page - 1) * pageSize;

            st = con.prepareStatement(
                "SELECT STUDENT.NO, STUDENT.NAME, STUDENT.ENT_YEAR, STUDENT.CLASS_NUM, " +
                "SUBJECT.NAME AS SUBJECT_NAME, TEST.NO, TEST.POINT " +
                "FROM STUDENT " +
                "LEFT JOIN TEST ON STUDENT.NO = TEST.STUDENT_NO " +
                "LEFT JOIN SUBJECT ON TEST.SUBJECT_CD = SUBJECT.CD " +
                "ORDER BY STUDENT.NO " +
                "LIMIT ? OFFSET ?"
            );
            st.setInt(1, pageSize);
            st.setInt(2, offset);

            rs = st.executeQuery();

            while (rs.next()) {
                Test resultTest = new Test();
                resultTest.setNo(rs.getInt("TEST.NO"));
                resultTest.setPoint(rs.getInt("TEST.POINT"));

                Student resultStudent = new Student();
                resultStudent.setNo(rs.getString("STUDENT.NO"));
                resultStudent.setName(rs.getString("STUDENT.NAME"));
                resultStudent.setEntYear(rs.getInt("STUDENT.ENT_YEAR"));
                resultStudent.setClassNum(rs.getString("STUDENT.CLASS_NUM"));

                resultTest.setStudent(resultStudent);

                Subject resultSubject = new Subject();
                resultSubject.setName(rs.getString("SUBJECT_NAME")); // SUBJECT.NAME のエイリアス

                resultTest.setSubject(resultSubject);
                studentTestList.add(resultTest);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException sqle) {
                    throw sqle;
                }
            }
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
        return studentTestList;
    }

    // データの総数を取得するメソッド
    public int getTotalTests() throws Exception {
        Connection con = getConnection();
        PreparedStatement st = null;
        ResultSet rs = null;
        int totalTests = 0;

        try {
            st = con.prepareStatement("SELECT COUNT(*) FROM TEST");
            rs = st.executeQuery();

            if (rs.next()) {
                totalTests = rs.getInt(1);
            }

        } catch (Exception e) {
            throw e;
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException sqle) {
                    throw sqle;
                }
            }
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
        return totalTests;
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


	public boolean save(String no ,String cd ,School school ,int num ,int point ,String classnum) throws Exception {

		Connection con = getConnection();
        PreparedStatement st = con.prepareStatement(
        		"INSERT INTO TEST VALUES(? , ? , ? , ? , ? , ?)");

        st.setString(1, no);


        st.setString(2, cd);


        st.setString(3, school.getCd());


        st.setInt(4, num);


        st.setInt(5, point);

        st.setString(6 ,  classnum);

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