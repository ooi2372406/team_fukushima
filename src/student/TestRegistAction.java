package student;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.ClassNum;
import bean.School;
import bean.Student;
import bean.Subject;
import bean.Teacher;
import dao.StudentDao;
import dao.SubjectDAO;
import tool.Action;
import util.Util;

public class TestRegistAction extends Action {



    public String execute(HttpServletRequest req, HttpServletResponse res) throws IOException {


        try {
        	HttpSession session = req.getSession();
        	boolean isAttend = true;//在学フラグ
        	Teacher teacher=Util.getUser(req);
        	School school=teacher.getSchool();
        	//Subject subject = (Subject) session.getAttribute("subject");
        	ClassNum classnum = (ClassNum) session.getAttribute("classNum");









        	SubjectDAO subject_dao = new SubjectDAO();
        	List<Subject>subjectList=subject_dao.filter(school);
        	System.out.println(subjectList);
        	StudentDao student_dao = new StudentDao();

        	List<Student> studentList = student_dao.filter(school , isAttend);
        	System.out.println(studentList);
        	session.setAttribute("subject", subjectList);
        	session.setAttribute("student", studentList);

        } catch (Exception e) {
            e.printStackTrace();
            res.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error processing request");
        }
        return "test_regist.jsp";

    }
}
/*
private void setTestListStudent(HttpServletRequest req, HttpServletResponse res) throws Exception {
        // リクエストパラメータの取得
        String cd = req.getParameter("cd");
        String subject = req.getParameter("subject");

        // 学生情報の取得
        TestListStudent filterStudent = new TestListStudent();
        filterStudent.setSubjectCd(cd);
        filterStudent.setSubjectName(subject);



        // 結果をリクエスト属性に設定して、JSPに転送
        req.setAttribute("studentList", filterStudent);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/test_list.jsp");
        dispatcher.forward(req, res);
    }

public void setTestListSubject(HttpServletRequest req, HttpServletResponse res) throws Exception {
        // リクエストパラメータの取得
        int entYear = Integer.parseInt(req.getParameter("entYear"));
        String classNum = req.getParameter("classNum");
        String subjectCd = req.getParameter("subjectCd");
        String schoolCd = req.getParameter("schoolCd");

        // 科目情報の取得
        Subject subject = new Subject();
        subject.setCd(subjectCd);
        School school = new School();
        school.setCd(schoolCd);

        List<TestListSubject> subjectList = subjectDao.filter(entYear, classNum, subjectCd, school);

        // 結果をリクエスト属性に設定して、JSPに転送
        req.setAttribute("subjectList", subjectList);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/test_list.jsp");
        dispatcher.forward(req, res);
    }
}*/