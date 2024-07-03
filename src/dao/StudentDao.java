package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Student;

public class StudentDao extends DAO {

    private String baseSql = "SELECT * FROM STUDENT WHERE SCHOOL_CD=?";

    public Student get(String no) throws Exception {
        Student student = new Student();
        Connection con = getConnection();
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            st = con.prepareStatement("SELECT * FROM STUDENT WHERE NO = ?");
            st.setString(1, no);
            rs = st.executeQuery();

            SchoolDao schoolDao = new SchoolDao();

            if (rs.next()) {
                student.setNo(rs.getString("no"));
                student.setName(rs.getString("name"));
                student.setEntYear(rs.getInt("ent_year"));
                student.setClassNum(rs.getString("class_num"));
                student.setIsAttend(rs.getBoolean("is_attend"));

                student.setSchool(schoolDao.get(rs.getString("school_cd")));
            } else {
                student = null;
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
        return student;
    }

    private List<Student> postFilter(ResultSet rSet, School school) throws Exception {
        List<Student> list = new ArrayList<>();

        try {
            while (rSet.next()) {
                Student student = new Student();
                student.setNo(rSet.getString("no"));
                student.setName(rSet.getString("name"));
                student.setEntYear(rSet.getInt("ent_year"));
                student.setClassNum(rSet.getString("class_num"));
                student.setIsAttend(rSet.getBoolean("is_attend"));
                student.setSchool(school);
                list.add(student);
            }
        } catch (SQLException | NullPointerException e) {
            e.printStackTrace();
        }
        return list;
    }

    // 萱野追記 学校所属の全学生取得filter()-------------------------------------------
    public List<Student> filter(School school) throws Exception {
        List<Student> list = new ArrayList<>();
        Connection con = getConnection();
        PreparedStatement st = null;
        ResultSet rs = null;

        String order = " ORDER BY NO ASC";

        try {
            st = con.prepareStatement(baseSql + order);
            st.setString(1, school.getCd());
            rs = st.executeQuery();

            list = postFilter(rs, school);
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
        return list;
    }
    // ---------------------------------------------------------
    public List<Student> filter(School school, int entYear, String classNum, boolean isAttend) throws Exception {
        List<Student> list = new ArrayList<>();
        Connection con = getConnection();
        PreparedStatement st = null;
        ResultSet rs = null;

        String condition = "AND ENT_YEAR=? AND CLASS_NUM = ?";
        String order = "ORDER BY NO ASC";
        String conditionIsAttend = "";

        if (isAttend) {
            conditionIsAttend = "AND IS_ATTEND = TRUE";
        }

        try {
            st = con.prepareStatement("SELECT * FROM STUDENT WHERE SCHOOL_CD=? AND IS_ATTEND = TRUE AND ENT_YEAR=? AND CLASS_NUM = ?  ORDER BY NO ASC");
            st.setString(1, school.getCd());
            st.setInt(2, entYear);
            st.setString(3, classNum);
            rs = st.executeQuery();

            list = postFilter(rs, school);
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
        return list;
    }

    public List<Student> filter(School school, int entYear, boolean isAttend) throws Exception {
        List<Student> list = new ArrayList<>();
        Connection con = getConnection();
        PreparedStatement st = null;
        ResultSet rs = null;

        String condition = "AND ENT_YEAR = ?";
        String order = " ORDER BY NO ASC ";
        String conditionIsAttend = "";

        if (isAttend) {
            conditionIsAttend = " AND IS_ATTEND = TRUE";
        } else {
        	conditionIsAttend = "AND IS_ATTEND = FALSE";
        }

        try {
            st = con.prepareStatement(baseSql + condition + conditionIsAttend + order);
            st.setString(1, school.getCd());
            st.setInt(2, entYear);
            rs = st.executeQuery();

            list = postFilter(rs, school);
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
        return list;
    }

    public List<Student> filter(School school, boolean isAttend) throws Exception {
        List<Student> list = new ArrayList<>();
        Connection con = getConnection();
        PreparedStatement st = null;
        ResultSet rs = null;

        String order = " ORDER BY NO ASC";
        String conditionIsAttend = "";

        if (isAttend) {
            conditionIsAttend = " AND IS_ATTEND = TRUE";
        }else{
        	conditionIsAttend = "AND IS_ATTEND = FALSE";
        }

        try {
            st = con.prepareStatement(baseSql + conditionIsAttend + order);
            st.setString(1, school.getCd());
            rs = st.executeQuery();

            list = postFilter(rs, school);
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
        return list;
    }

    public boolean save(Student student) throws Exception {
        Connection con = getConnection();
        PreparedStatement st = null;
        int count = 0;

        try {
            Student old = get(student.getNo());
            if (old == null) {
                st = con.prepareStatement(
                        "INSERT INTO STUDENT(NO, NAME, ENT_YEAR, CLASS_NUM, IS_ATTEND, SCHOOL_CD) VALUES (?, ?, ?, ?, ?, ?)"
                );
                st.setString(1, student.getNo());
                st.setString(2, student.getName());
                st.setInt(3, student.getEntYear());
                st.setString(4, student.getClassNum());
                st.setBoolean(5, student.getIsAttend());
                st.setString(6, student.getSchool().getCd());
            } else {
                st = con.prepareStatement(
                        "UPDATE STUDENT SET NAME = ?, ENT_YEAR = ?, CLASS_NUM = ?, IS_ATTEND = ? WHERE NO = ?"
                );
                st.setString(1, student.getName());
                st.setInt(2, student.getEntYear());
                st.setString(3, student.getClassNum());
                st.setBoolean(4, student.getIsAttend());
                st.setString(5, student.getNo());
            }
            count = st.executeUpdate();
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
        return count > 0;
    }
}
