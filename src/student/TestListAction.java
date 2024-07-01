package student;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.School;
import bean.Student;
import bean.Subject;
import bean.Teacher;
import bean.TestListStudent;
import bean.TestListSubject;
import dao.ClassNumDao;
import dao.StudentDao;
import dao.SubjectDAO;
import dao.TestListStudentDAO;
import dao.TestListSubjectDAO;
import tool.Action;
import util.Util;

public class TestListAction extends Action {

    public String execute(HttpServletRequest req, HttpServletResponse res) throws IOException {
        try {
            // セッションが存在しない場合はログインページにリダイレクト
            HttpSession session = req.getSession(false);
            if (session == null) {

                return "/student/login/login.jsp";
            }

            Teacher teacher = Util.getUser(req);
            if (teacher == null) {
               session.invalidate();
               res.sendRedirect(req.getContextPath() + "/student/login/login.jsp");
                return null;
           }

            // 前ページから送られてきたデータを受け取る
            String selectclass = req.getParameter("");
            String selectsubject = req.getParameter("");
            //String selectstsudentNo = req.getParameter("");

            // selected判定のためにセット
            req.setAttribute("selectclass", selectclass);
            req.setAttribute("selectsubject", selectsubject);
            //req.setAttribute("selectstudent", selectstsudentNo);

            School school = teacher.getSchool();

            SubjectDAO subject_dao = new SubjectDAO();
            List<Subject> subject = subject_dao.filter(school);

            ClassNumDao class_dao = new ClassNumDao();
            List<String> classnum = class_dao.filter(school);
            session.setAttribute("subject", subject);
            session.setAttribute("classnum", classnum);

            // リクエストパラメータ 科目コードと学生コードの値を取得する
            String cd = req.getParameter("f");

            if (cd != null && !cd.isEmpty() && cd.equals("sj")) {
                // 科目識別コード"sj"が送られてきたときはsetTestListSubject を実行
                setTestListSubject(req, res);
            } else if (cd != null && !cd.isEmpty() && cd.equals("st")) {
                // 科目識別コード"sj"が送られてきたときはsetTestListStudent を実行
                setTestListStudent(req, res);
            } else {
                // 上記条件を満たさない場合、通常の処理を実行
                boolean isAttend = true;  // 在籍中の学生だけを取得
                StudentDao studao = new StudentDao();
                List<Student> studentList = studao.filter(school, isAttend);

                // 重複を排除した学生の入学年度リストを取得
                List<Integer> uniqueEnrollYears = studentList.stream()
                                                             .map(Student::getEntYear)
                                                             .distinct()
                                                             .collect(Collectors.toList());

                session.setAttribute("student", uniqueEnrollYears);
            }

        } catch (Exception e) {
            e.printStackTrace();
            res.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error processing request");
        }

        return "test_list.jsp";
    }

    private void setTestListStudent(HttpServletRequest req, HttpServletResponse res) throws Exception {
        Teacher teacher = Util.getUser(req);
        School school = teacher.getSchool();

        String studentCd = req.getParameter("f4");
        StudentDao studao = new StudentDao();
        Student student = studao.get(studentCd);

        TestListStudentDAO dao = new TestListStudentDAO();
        List<TestListStudent> studentList = dao.filter(student);

        if (studentList.size() != 0) {
            req.setAttribute("studentList", studentList);
            req.setAttribute("studentname", student);
        } else {
            req.setAttribute("studentname", student);
            req.setAttribute("studentmessage", "成績情報が存在しませんでした");
        }

        if (student == null) {
            req.setAttribute("studentempty", "学生情報が存在しませんでした");
        }

        req.setAttribute("f4", studentCd);
    }

    private void setTestListSubject(HttpServletRequest req, HttpServletResponse res) throws Exception {
        HttpSession session = req.getSession();

        Teacher teacher = Util.getUser(req);
        School school = teacher.getSchool();

        String entYear_str = req.getParameter("f1");
        String classNum = req.getParameter("f2");
        String subjectname = req.getParameter("f3");
        String studentCd = req.getParameter("f4");

        if ("--------".equals(entYear_str) || classNum == null || subjectname == null) {
            req.setAttribute("errorMessage", "入学年度とクラスと科目を選択してください");
        } else {
            int entYear = Integer.parseInt(entYear_str);

            Subject subject = new Subject();
            subject.setName(subjectname);

            TestListSubjectDAO testdao = new TestListSubjectDAO();
            List<TestListSubject> testList = testdao.filter(entYear, classNum, subject, school);

            if (testList.size() == 0) {
                req.setAttribute("errorMessege2", "学生情報が存在しませんでした");
            }

            req.setAttribute("testList", testList);
            req.setAttribute("subjectname", subjectname);
            req.setAttribute("f4", studentCd);
        }
    }
}
