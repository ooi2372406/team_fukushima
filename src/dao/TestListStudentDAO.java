package dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Subject;
import bean.TestListStudent;

public class TestListStudentDAO extends DAO {
    private String baseSql = "SELECT * FROM TEST WHERE ";

    private List<TestListStudent> postFilter(ResultSet set) {
        // データベースから結果セットを受け取り、学生リストを返す
        List<TestListStudent> studentList = new ArrayList<>();
        try {
            while (set.next()) {
                TestListStudent student = new TestListStudent();
                student.setSubjectName(set.getString("subjectName"));
                student.setSubjectCd(set.getString("subjectCd"));
                student.setNum(set.getInt("num"));
                student.setPoint(set.getInt("point"));
                studentList.add(student);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return studentList;
    }

    public List<TestListStudent> filter(int entYear, String classNum, Subject subject, School school) {
        // 学生のフィルタ条件に基づいてリストを取得する
        List<TestListStudent> studentList = new ArrayList<>();
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(baseSql + "entYear = ? AND class_Num = ? AND subject_cd = ? AND school_cd = ?")) {
            stmt.setInt(1, entYear);
            stmt.setString(2, classNum);
            stmt.setString(3, subject.getCd());
            stmt.setString(4, school.getCd());
            ResultSet rs = stmt.executeQuery();
            studentList = postFilter(rs);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return studentList;
    }
}
