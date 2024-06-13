package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.Test;

public class TestDao extends DAO {
    private String basesql = "select * from test where school_cd=?;";

    // 学生を全件取得する studentAll メソッド
    /*public List<Subject> get(School school, String cd) throws Exception {
        List<Subject> subjects = new ArrayList<>();

        Connection con = getConnection();
        PreparedStatement st = con.prepareStatement(
            "SELECT CD, NAME FROM SUBJECT WHERE SCHOOL_CD = ? AND SUBJECT_CD = ?"
        );
        st.setString(1, school.getSchoolCd());
        st.setString(2, cd);
        ResultSet rs = st.executeQuery();

        while (rs.next()) {
            Subject subject = new Subject();
            subject.setCd(rs.getString("CD"));
            subject.setName(rs.getString("NAME"));
            subjects.add(subject);
        }

        rs.close();
        st.close();
        con.close();

        return subjects;
    }*/

    public Test get(String student, String subject, String school, int no) throws SQLException {
        Test test = null;
        Connection con = getConnection();
        PreparedStatement st = con.prepareStatement(
            "SELECT * FROM TEST WHERE STUDENT_NO = ? AND SUBJECT_CD = ? AND SCHOOL_CD = ? AND NO = ?"
        );
        st.setString(1, student);
        st.setString(2, subject);
        st.setString(3, school);
        st.setInt(4, no);
        ResultSet rs = st.executeQuery();

        if (rs.next()) {
            test = new Test(
                rs.getString("STUDENT_NO"),
                rs.getString("SUBJECT_CD"),
                rs.getString("SCHOOL_CD"),
                rs.getString("CLASS_NUM"),
                rs.getObject("POINT", Integer.class),
                rs.getInt("NO")
            );
        }

        rs.close();
        st.close();
        con.close();

        return test;
    }

    // postFilterメソッド
    private List<Test> postFilter(ResultSet set, String school) throws SQLException {
        List<Test> tests = new ArrayList<>();
        try {
            while (set.next()) {
                Test test = new Test();
                test.setStudentNo(set.getString("STUDENT_NO"));
                test.setSubjectCd(set.getString("SUBJECT_CD"));
                test.setSchoolCd(set.getString("SCHOOL_CD"));
                test.setPoint(set.getInt("POINT"));
                test.setClassNum(set.getString("CLASS_NUM"));
                tests.add(test);
            }
        } catch (SQLException | NullPointerException e) {
            e.printStackTrace();
        }
        return tests;
    }

    // 修正されたfilterメソッド
    public List<Test> filter(Test test, int entYear, String classNum, String subject, int num, String school, boolean isAttend) throws SQLException {
        List<Test> tests = new ArrayList<>();
        Connection con = getConnection();
        PreparedStatement statement = null;
        ResultSet set = null;
        String condition = " and ent_year=? and class_num=?";
        String order = " order by no asc";
        String conditionIsAttend = "";
        if (isAttend) {
            conditionIsAttend = " and is_attend=true";
        }
        try {
            statement = con.prepareStatement(basesql + conditionIsAttend + condition + order);
            statement.setString(1, test.getSchoolCd());
            statement.setInt(2, entYear);
            statement.setString(3, classNum);
            set = statement.executeQuery();
            tests = postFilter(set, school);
        } catch (Exception e) {
            throw e;
        } finally {
            if (statement != null) {
                try {
                    statement.close();
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
    public boolean save(Test test) throws Exception {
        Connection connection = getConnection();
        PreparedStatement statement = null;
        int count = 0;

        try {
            Test old = get(test.getStudentNo(), test.getSubjectCd(), test.getSchoolCd(), test.getNo());
            if (old == null) {
                statement = connection.prepareStatement(
                    "INSERT INTO TEST (STUDENT_NO, SUBJECT_CD, SCHOOL_CD, NO, POINT, CLASS_NUM) VALUES (?, ?, ?, ?, ?, ?)"
                );
                statement.setString(1, test.getStudentNo());
                statement.setString(2, test.getSubjectCd());
                statement.setString(3, test.getSchoolCd());
                statement.setInt(4, test.getNo());
                statement.setObject(5, test.getPoint(), java.sql.Types.INTEGER);
                statement.setString(6, test.getClassNum());
            } else {
                statement = connection.prepareStatement(
                    "UPDATE TEST SET POINT=?, CLASS_NUM=? WHERE STUDENT_NO=? AND SUBJECT_CD=? AND SCHOOL_CD=? AND NO=?"
                );
                statement.setObject(1, test.getPoint(), java.sql.Types.INTEGER);
                statement.setString(2, test.getClassNum());
                statement.setString(3, test.getStudentNo());
                statement.setString(4, test.getSubjectCd());
                statement.setString(5, test.getSchoolCd());
                statement.setInt(6, test.getNo());
            }
            count = statement.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException sqle) {
                    throw sqle;
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException sqle) {
                    throw sqle;
                }
            }
        }
        return count > 0;
    }

    // プライベートのsaveメソッド
    private boolean save(Test test) throws SQLException {
        Connection con = getConnection();
        PreparedStatement st = con.prepareStatement(
            "INSERT INTO TEST (STUDENT_NO, SUBJECT_CD, SCHOOL_CD, NO, POINT, CLASS_NUM) VALUES (?, ?, ?, ?, ?, ?)"
        );
        st.setString(1, test.getStudentNo());
        st.setString(2, test.getSubjectCd());
        st.setString(3, test.getSchoolCd());
        st.setInt(4, test.getNo());
        st.setObject(5, test.getPoint(), java.sql.Types.INTEGER);
        st.setString(6, test.getClassNum());
        st.executeUpdate();

        st.close();
        con.close();

        return true;
    }

    private boolean delete(List<Test> list) throws SQLException {
        for (Test test : list) {
            delete(test);
        }
        return true;
    }

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
        return DriverManager.getConnection("jdbc:h2:tcp://localhost/~/db_TEAM_FUKUSHIMA", "sa", "");
    }
}
