package dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.Student;
import bean.TestListStudent;

public class TestListStudentDAO extends DAO {
    private String baseSql = "SELECT * FROM TEST WHERE ";

    private List<TestListStudent> postFilter(ResultSet set) {
        // データベースから結果セットを受け取り、学生リストを返す
        List<TestListStudent> studentList = new ArrayList<>();
        try {
            while (set.next()) {
                TestListStudent student = new TestListStudent();
                student.setSubjectName(set.getString("SUBJECT.NAME"));
                student.setSubjectCd(set.getString("SUBJECT.CD"));
                student.setNum(set.getInt("TEST.NO"));
                student.setPoint(set.getInt("TEST.POINT"));
                studentList.add(student);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return studentList;
    }

    public List<TestListStudent> filter(Student student) {
        // 学生のフィルタ条件に基づいてリストを取得する
        List<TestListStudent> studentList = new ArrayList<>();
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(
            		 "SELECT SUBJECT.NAME , SUBJECT.CD , TEST.NO , TEST.POINT FROM TEST JOIN SUBJECT ON TEST.SUBJECT_CD = SUBJECT.CD WHERE TEST.STUDENT_NO = ?;"
            		 )) {
            stmt.setString(1, student.getNo());
            ResultSet rs = stmt.executeQuery();
            studentList = postFilter(rs);

            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return studentList;
    }
}
