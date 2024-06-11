package student;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.School;
import bean.Subject;
import bean.TestListStudent;
import bean.TestListSubject;
import dao.TestListStudentDAO;
import dao.TestListSubjectDAO;
import tool.Action;
public class TestListAction extends Action {

    // DAOインスタンスの作成
    private TestListStudentDAO studentDao = new TestListStudentDAO();
    private TestListSubjectDAO subjectDao = new TestListSubjectDAO();

    public String execute(HttpServletRequest req, HttpServletResponse res) throws IOException {
        // リクエストパラメータの取得
        String action = req.getParameter("action");

        // actionに応じた処理の実行
        try {
            if ("getStudents".equals(action)) {
                setTestListStudent(req, res);
            } else if ("getSubjects".equals(action)) {
                setTestListSubject(req, res);
            } else {
                res.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid action");
            }
        } catch (Exception e) {
            e.printStackTrace();
            res.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error processing request");
        }
        return "test_list.jsp";
    }

    private void setTestListStudent(HttpServletRequest req, HttpServletResponse res) throws Exception {
        // リクエストパラメータの取得
        String cid = req.getParameter("cid");
        String subject = req.getParameter("subject");

        // 学生情報の取得
        TestListStudent filterStudent = new TestListStudent();
        filterStudent.setSubjectCd(cid);
        filterStudent.setSubjectName(subject);

        List<TestListStudent> studentList = studentDao.postFilter(filterStudent);

        // 結果をリクエスト属性に設定して、JSPに転送
        req.setAttribute("studentList", studentList);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/test_list.jsp");
        dispatcher.forward(req, res);
    }

    private void setTestListSubject(HttpServletRequest req, HttpServletResponse res) throws Exception {
        // リクエストパラメータの取得
        int entYear = Integer.parseInt(req.getParameter("entYear"));
        String classNum = req.getParameter("classNum");
        String subjectCid = req.getParameter("subjectCid");
        String schoolCid = req.getParameter("schoolCid");

        // 科目情報の取得
        Subject subject = new Subject();
        subject.setCd(subjectCid);
        School school = new School();
        school.setCd(schoolCid);

        List<TestListSubject> subjectList = subjectDao.filter(entYear, classNum, subject, school);

        // 結果をリクエスト属性に設定して、JSPに転送
        req.setAttribute("subjectList", subjectList);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/test_list.jsp");
        dispatcher.forward(req, res);
    }
}