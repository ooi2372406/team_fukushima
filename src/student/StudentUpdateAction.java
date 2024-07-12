package student;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.School;
import bean.Student;
import bean.Teacher;
import dao.ClassNumDao;
import dao.StudentDao;
import tool.Action;
import util.Util;

public class StudentUpdateAction extends Action {



    public String execute(HttpServletRequest req, HttpServletResponse res) throws IOException {

        // actionに応じた処理の実行
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
        	School school=teacher.getSchool();

        	String no = req.getParameter("f1");

        	StudentDao student_dao=new StudentDao();
        	Student student = student_dao.get(no);
        	boolean isattend = student.getIsAttend();

        	ClassNumDao class_dao = new ClassNumDao();
        	List<String>classnum=class_dao.filter(school);

        	session.setAttribute("student", student);
        	session.setAttribute("classnum", classnum);
        	session.setAttribute("isAttend", isattend);



        } catch (Exception e) {
            e.printStackTrace();
            res.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error processing request");
        }
        return "student_update_sample.jsp";

    }
}
