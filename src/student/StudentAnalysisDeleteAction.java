package student;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.School;
import bean.Teacher;
import dao.StudentAnalysisDao;
import tool.Action;
import util.Util;

public class StudentAnalysisDeleteAction extends Action{
	public String execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

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


            School school = teacher.getSchool();
            String id = req.getParameter("id");
            int setid = Integer.parseInt(id);


            StudentAnalysisDao dao = new StudentAnalysisDao();
            boolean line = dao.delete(setid);

            if(line){
            	session.setAttribute("id", id);
            	return "student_analysis_deletedone.jsp";
            }



	} catch (Exception e) {
        e.printStackTrace();
        res.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error processing request");
    } return "student_analysis_deletedone.jsp";
}

}
