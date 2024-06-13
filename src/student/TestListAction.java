package student;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.School;
import bean.Subject;
import bean.Teacher;
import dao.ClassNumDao;
import dao.SubjectDAO;
import tool.Action;
import util.Util;

public class TestListAction extends Action {



    public String execute(HttpServletRequest req, HttpServletResponse res) throws IOException {


        try {
        	HttpSession session = req.getSession();
        	Teacher teacher=Util.getUser(req);
        	School school=teacher.getSchool();

        	SubjectDAO subject_dao=new SubjectDAO();
        	List<Subject>subject = subject_dao.filter(school);

        	ClassNumDao class_dao = new ClassNumDao();
        	List<String>classnum=class_dao.filter(school);
        	System.out.println(classnum);
        	session.setAttribute("subject", subject);
        	session.setAttribute("classnum", classnum);



        } catch (Exception e) {
            e.printStackTrace();
            res.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error processing request");
        }
        return "testListstudent_sample.jsp";

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