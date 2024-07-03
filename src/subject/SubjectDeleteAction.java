package subject;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.School;
import bean.Subject;
import bean.Teacher;
import dao.SubjectDAO;
import tool.Action;
import util.Util;

public class SubjectDeleteAction extends Action {
	public String execute(
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		try{
			 HttpSession session = request.getSession(false);
	            if (session == null) {

	                return "/student/login/login.jsp";
	            }

	            Teacher teacher = Util.getUser(request);
	            if (teacher == null) {
	               session.invalidate();
	               response.sendRedirect(request.getContextPath() + "/student/login/login.jsp");
	                return null;
	           }
    		// 意図的に例外を発生させる処理（普段はつかわない）
    		 //if (true) {
    	     //       throw new RuntimeException("テスト用の予期せぬエラー");
    	     // }

			// getUserメソッドを呼び出してユーザー情報を取得
			
			// TeacherオブジェクトからSchoolオブジェクトを取得
			School school = teacher.getSchool();

			// リクエストパラメータからcdを受け取る
			String cd = request.getParameter("cd");

			SubjectDAO dao = new SubjectDAO();
			// 対象の学生を取得

			Subject subject = dao.get(cd , school);
			

			// studentとcourseListを設定してjspにフォワード
			request.setAttribute("subject", subject);

			return "/student/subject/subject_delete.jsp";
		}catch(Exception e){
   		 // エラーメッセージを設定してエラーページに遷移
           request.setAttribute("message", "エラーが発生しました。");
           return "/student/subject/subject_error.jsp";

   	}
	}
}
