package student;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.School;
import bean.Subject;
import bean.Teacher;
import dao.SubjectDAO;
import tool.Action;
import util.Util;

public class SubjectUpdateAction extends Action {
	public String execute(
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		 // getUserメソッドを呼び出してユーザー情報を取得
        Teacher teacher = Util.getUser(request);
     // TeacherオブジェクトからSchoolオブジェクトを取得
        School school = teacher.getSchool();

		// リクエストパラメータからcdを受け取る
		String cd = request.getParameter("cd");

		SubjectDAO dao = new SubjectDAO();
		// 対象の学生を取得

		Subject subject = dao.get(cd , school);
		System.out.println(subject);

		// studentとcourseListを設定してjspにフォワード
		request.setAttribute("subject", subject);

		return "subject_update.jsp";
	}
}
