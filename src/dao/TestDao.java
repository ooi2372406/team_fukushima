package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Subject;
import bean.Test;

public class TestDao extends DAO {
String basesql =
    // 学生を全件取得する studentAll メソッド
    public List<Subject> get(School school, String cd) throws Exception {
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
    }

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

    private List<Test> postFilter(ResultSet set, String school) throws SQLException {
       //リストを初期化
    	List<Test> tests = new ArrayList<>();
try{

//リザルトセットを全権走査
        while (set.next()) {
        	//testインスタンスを初期化
            Test test = new Test();
            //testインスタンスに検索結果をセット
                test.setStudentNo(set.getString("studentNo"));
                test.setSubjectCd(set.getString("subjectCd"));
                test.setSchoolCd(set.getString("schoolCd"));
                test.setSchoolCd(set.getString("schoolCd"));
                test.setPoint(set.getString("point"));
                test.setClassNum(set.getString("classNum"));


     //リストに追加
            tests.add(test);
        	}
        }catch (SQLException | NullPointerException e){
        	e.printStackTrace();
        }
        return tests;
    }

    public List<Test> filter(int entYear, String classNum, String subject, int num, String school) throws SQLException {
        List<Test> tests = new ArrayList<>();
        Connection con = getConnection();
        PreparedStatement st = con.prepareStatement(
            "SELECT * FROM TEST WHERE CLASS_NUM = ? AND SUBJECT_CD = ? AND NO = ? AND SCHOOL_CD = ?"
        );
        st.setString(1, classNum);
        st.setString(2, subject);
        st.setInt(3, num);
        st.setString(4, school);
        ResultSet rs = st.executeQuery();

        while (rs.next()) {
            Test test = new Test(
                rs.getString("STUDENT_NO"),
                rs.getString("SUBJECT_CD"),
                rs.getString("SCHOOL_CD"),
                rs.getString("CLASS_NUM"),
                rs.getObject("POINT", Integer.class),
                rs.getInt("NO")
            );
            tests.add(test);
        }

        rs.close();
        st.close();
        con.close();

        return tests;
    }

    public boolean save(List<Test> list) throws SQLException {
        for (Test test : list) {
            save(test);
        }
        return true;
    }

    public boolean save(Test test) throws SQLException {
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

    public boolean delete(List<Test> list) throws SQLException {
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
        return DriverManager.getConnection("jdbc:h2:tcp://localhost/~/db_TEAM_FUKUSHIMA", "sa", null);
    }
}
