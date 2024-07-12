package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import bean.StudentAnalysis;

public class StudentAnalysisDao extends DAO {

    public List<StudentAnalysis> postFilter(ResultSet set) {
        List<StudentAnalysis> studentList = new ArrayList<>();
        try {
            if (!set.isBeforeFirst()) {

                return studentList;
            }

            while (set.next()) {
                String no = set.getString("STUDENT.NO");

                String name = set.getString("STUDENT.NAME");

                String subjectCd = set.getString("TEST.SUBJECT_CD");

                String subjectName = set.getString("SUBJECT.NAME");

                int point = set.getInt("AVG_POINT"); // 変更: エイリアスを使用

                // 同じ学生をリストから探す
                StudentAnalysis analysis = null;
                for (StudentAnalysis s : studentList) {
                    if (s.getName().equals(name)) {
                        analysis = s;
                        break;
                    }
                }

                // 見つからなければ新しいオブジェクトを作成してリストに追加
                if (analysis == null) {
                    analysis = new StudentAnalysis();
                    analysis.setNo(no);
                    analysis.setName(name);
                    analysis.setSubjectCd(subjectCd);
                    analysis.setSubjectName(subjectName);
                    analysis.setPoints(new HashMap<>()); // 初期化
                    studentList.add(analysis);
                }

                // ポイントを追加
                analysis.putPoint(subjectName, point);

                // 例: 特定のキーが存在しなければデフォルト値 -1 を格納
                String specificKey = "someKey"; // チェックしたい特定のキー
                if (!analysis.getPoints().containsKey(specificKey)) {
                    //analysis.putPoint(specificKey, -1);

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return studentList;
    }

    public List<StudentAnalysis> filter(String no) {
        List<StudentAnalysis> studentList = new ArrayList<>();
        try (Connection con = getConnection()) {

            String sql = "SELECT STUDENT.NO, STUDENT.NAME, TEST.SUBJECT_CD, SUBJECT.NAME, AVG(TEST.POINT) AS AVG_POINT FROM TEST " +
                         "JOIN STUDENT ON TEST.STUDENT_NO = STUDENT.NO " +
                         "JOIN SUBJECT ON TEST.SUBJECT_CD = SUBJECT.CD " +
                         "GROUP BY STUDENT.NO, STUDENT.NAME, TEST.SUBJECT_CD, SUBJECT.NAME " +
                         "HAVING STUDENT.NO = ?";
            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setString(1, no);

            ResultSet rs = stmt.executeQuery();


            studentList = postFilter(rs);

            // デバッグ用に取得したリストを出力
            for (StudentAnalysis students : studentList) {

            }
            stmt.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return studentList;
    }

    @SuppressWarnings("unused")
    public List<StudentAnalysis> filter() {
        List<StudentAnalysis> subjectPointList = new ArrayList<>();
        try (Connection con = getConnection()) {

            String sql = "SELECT TEST.SUBJECT_CD, SUBJECT.NAME, AVG(TEST.POINT) AS AVG_POINT FROM TEST " +
                         "JOIN STUDENT ON TEST.STUDENT_NO = STUDENT.NO " +
                         "JOIN SUBJECT ON TEST.SUBJECT_CD = SUBJECT.CD " +
                         "GROUP BY TEST.SUBJECT_CD, SUBJECT.NAME";
            PreparedStatement stmt = con.prepareStatement(sql);

            ResultSet rs = stmt.executeQuery();

            // 一つのStudentAnalysisオブジェクトを使用
            StudentAnalysis analysis = new StudentAnalysis();
            analysis.setPoints(new HashMap<>()); // 初期化

            while (rs.next()) {
                String subjectName = rs.getString("SUBJECT.NAME");
                String subjectCd = rs.getString("TEST.SUBJECT_CD");
                int point = rs.getInt("AVG_POINT");

                // 科目の平均点を追加
                analysis.putPoint(subjectName, point);
                analysis.setSubjectCd(subjectCd); // 必要なら追加（ただし、上書きされる）
                analysis.setSubjectName(subjectName); // 必要なら追加（ただし、上書きされる）
            }

            // リストに1つのStudentAnalysisオブジェクトを追加
            subjectPointList.add(analysis);

            // デバッグ用の出力
            for (StudentAnalysis students : subjectPointList) {

            }

            stmt.close();
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return subjectPointList;
    }


    // 1回目のテストの順位を取得するＤＡＯ
    public int getRank1(String studentCd, String subjectCd) throws Exception {
        int rank = 0;
        try (Connection con = getConnection()) {
            String sql = "SELECT t1.STUDENT_NO, t1.POINT, " +
                         "(SELECT COUNT(*) + 1 " +
                         " FROM TEST t2 " +
                         " WHERE t2.POINT > t1.POINT " +
                         " AND t2.SUBJECT_CD = t1.SUBJECT_CD " +
                         " AND t2.NO = t1.NO) AS RANK " +
                         "FROM TEST t1 " +
                         "WHERE t1.SUBJECT_CD = ? " +
                         "AND t1.NO = 1 " +
                         "AND t1.STUDENT_NO = ?";

            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, subjectCd);

            stmt.setString(2, studentCd);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {  // 必ず有効な行にいることを確認
                rank = rs.getInt("RANK");

            }

            stmt.close();
            con.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rank;
    }


    // 2回目のテストの順位を取得するＤＡＯ
    public int getRank2(String studentCd, String subjectCd) throws Exception {
        int rank = 0;
        try (Connection con = getConnection()) {
            String sql = "SELECT t1.STUDENT_NO, t1.POINT, " +
                         "(SELECT COUNT(*) + 1 " +
                         " FROM TEST t2 " +
                         " WHERE t2.POINT > t1.POINT " +
                         " AND t2.SUBJECT_CD = t1.SUBJECT_CD " +
                         " AND t2.NO = t1.NO) AS RANK " +
                         "FROM TEST t1 " +
                         "WHERE t1.SUBJECT_CD = ? " +
                         "AND t1.NO = 2 " +
                         "AND t1.STUDENT_NO = ?";

            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, subjectCd);

            stmt.setString(2, studentCd);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {  // 必ず有効な行にいることを確認
                rank = rs.getInt("RANK");

            }
            stmt.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rank;
    }

    // 受験した全体の人数を取得する
    public int getStudentCount(String subjectCd, int no) throws Exception {
        int count = 0;
        try (Connection con = getConnection()) {
            String sql = "SELECT COUNT(*) AS COUNT FROM TEST WHERE SUBJECT_CD = ? AND NO = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, subjectCd);
            stmt.setInt(2, no);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                count = rs.getInt("COUNT");
            }

            stmt.close();
            con.close();
        }
        return count;
    }

    public List<StudentAnalysis> getComment(String no) throws Exception{
    	List<StudentAnalysis> comments = new ArrayList<>();
    	 try (Connection con = getConnection()) {
             String sql = "SELECT STUDENT_ID , COMMENTS , COMMENT_DATE FROM COMMENTS WHERE STUDENT_NO = ?";
             PreparedStatement stmt = con.prepareStatement(sql);
             stmt.setString(1, no);

             ResultSet rs = stmt.executeQuery();


             while (rs.next()) {
            	 StudentAnalysis analysis = new StudentAnalysis();
            	 int setid = rs.getInt("STUDENT_ID");
                 String setcomment = rs.getString("COMMENTS");
                 Date setdata = rs.getDate("COMMENT_DATE");
                 analysis.setStudentId(setid);

                 analysis.setComment(setcomment);
                 analysis.setDate(setdata);
                 comments.add(analysis);
             }

             stmt.close();
             con.close();
         }
         return comments;
    }

    // 新規登録するメソッド
    public boolean save(String no , String comments ,  String cd) throws Exception {
        Connection con = getConnection();
        PreparedStatement st = null;
        String insert = "INSERT INTO COMMENTS(STUDENT_NO , COMMENTS , SCHOOL_CE , COMMENT_DATE) VALUES(?, ?, ? , CURRENT_DATE)";
        int rowCount;

        try {
            st = con.prepareStatement(insert);
            st.setString(1, no);
            st.setString(2, comments);
            st.setString(3, cd);
            rowCount = st.executeUpdate();
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

        return rowCount > 0;
    }


    // 削除するメソッド
    public boolean delete(int id) throws Exception {
        Connection con = getConnection();
        PreparedStatement st = null;
        String delete = "DELETE FROM COMMENTS WHERE STUDENT_ID  = ?";
        int rowCount;

        try {
            st = con.prepareStatement(delete);
            st.setInt(1, id);
            rowCount = st.executeUpdate();
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

        return rowCount > 0;
    }


}
