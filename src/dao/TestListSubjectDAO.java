package dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import bean.School;
import bean.TestListSubject;

public class TestListSubjectDAO extends DAO {
    private String baseSql = "SELECT * FROM student,subject WHERE ";

    public List<TestListSubject> postFilter(ResultSet set) {
        // データベースから結果セットを受け取り、科目リストを返す
        List<TestListSubject> subjectList = new ArrayList<>();
        try {
            while (set.next()) {
                TestListSubject subject = new TestListSubject();
                subject.setEntYear(set.getInt("entYear"));
                subject.setStudentName(set.getString("studentName"));
                subject.setPoints(new HashMap<>());
                // Assuming points are stored as key-value pairs
                // Iterate and populate pointsMap
                subjectList.add(subject);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return subjectList;
    }

    public List<TestListSubject> filter(int entYear, String classNum, String subject, School school) {
        // 年度、クラス番号、科目、学校に基づいてリストを取得する
        List<TestListSubject> subjectList = new ArrayList<>();
        try (
        	Connection conn = getConnection();
            PreparedStatement stmt = conn.prepareStatement(baseSql + "")) {
            stmt.setInt(1, entYear);
            stmt.setString(2, classNum);
            stmt.setString(3, subject);
            stmt.setString(4, school.getCd());
            ResultSet rs = stmt.executeQuery();
            subjectList = postFilter(rs);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return subjectList;
    }
}
