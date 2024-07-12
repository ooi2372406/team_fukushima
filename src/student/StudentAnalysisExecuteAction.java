package student;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.School;
import bean.Teacher;
import dao.StudentAnalysisDao;
import tool.Action;
import util.Util;


public class StudentAnalysisExecuteAction extends Action{


	public String execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
    HttpSession session = req.getSession();

    Teacher teacher = Util.getUser(req);
    School school = teacher.getSchool();
    String cd = school.getCd();
    System.out.println(cd);

    String comments = req.getParameter("f3");
    System.out.println(comments);
    String studentCd = req.getParameter("f2");
    System.out.println(studentCd);



    StudentAnalysisDao dao = new StudentAnalysisDao();
    boolean line = dao.save(studentCd , comments , cd);

    if (!line) {
        req.setAttribute("commentErrorMessege", "コメントの登録に失敗しました。");
    }

    return "student_analysis_done.jsp";


	}


}