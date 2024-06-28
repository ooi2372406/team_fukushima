package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Subject;

public class SubjectDAO extends DAO {

    // 学校コードに紐づく全ての科目を取得するメソッド
    public List<Subject> filter(School school) throws Exception {
        List<Subject> subjects = new ArrayList<>();
        Connection con = getConnection();
        ResultSet rs = null;
        PreparedStatement st = null;
        String query = "SELECT CD, NAME FROM SUBJECT WHERE SCHOOL_CD = ?";

        try {
            st = con.prepareStatement(query);
            st.setString(1, school.getCd());
            rs = st.executeQuery();

            while (rs.next()) {
                Subject subject = new Subject();
                subject.setCd(rs.getString("CD"));
                subject.setName(rs.getString("NAME"));
                subject.setSchool(school);
                subjects.add(subject);
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

        return subjects;
    }

    // 科目コードと学校コードに紐づく科目を取得するメソッド
    public Subject get(String cd, School school) throws Exception {
        Subject subject = null;
        Connection con = getConnection();
        PreparedStatement st = null;
        ResultSet rs = null;
        String query = "SELECT * FROM SUBJECT WHERE CD = ? ";

        try {
            st = con.prepareStatement(query);
            st.setString(1, cd);
            rs = st.executeQuery();

            if (rs.next()) {
                subject = new Subject();
                subject.setCd(rs.getString("CD"));
                subject.setName(rs.getString("NAME"));
                subject.setSchool(school);
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
        return subject;
    }

    // 新規登録するメソッド
    public boolean save(Subject subject) throws Exception {
        Connection con = getConnection();
        PreparedStatement st = null;
        String insert = "INSERT INTO SUBJECT VALUES(?, ?, ?)";
        int rowCount;

        try {
            st = con.prepareStatement(insert);
            st.setString(1, subject.getSchool().getCd());
            st.setString(2, subject.getCd());
            st.setString(3, subject.getName());
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

    // 更新するメソッド
    public boolean update(Subject subject) throws Exception {
        Connection con = getConnection();
        PreparedStatement st = null;
        int rowCount;
        String update = "UPDATE SUBJECT SET NAME = ? WHERE CD = ?";

        try {
            st = con.prepareStatement(update);
            st.setString(1, subject.getName());
            st.setString(2, subject.getCd());
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
    public boolean delete(Subject subject) throws Exception {
        Connection con = getConnection();
        PreparedStatement st = null;
        String delete = "DELETE FROM SUBJECT WHERE CD = ?";
        int rowCount;

        try {
            st = con.prepareStatement(delete);
            st.setString(1, subject.getCd());
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
