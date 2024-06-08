package student;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Subject;
import bean.Teacher;
import dao.SubjectDAO;
import tool.Action;
import util.Util;

public class SubjectUpdateExecuteAction extends Action {
	public String execute(
		HttpServletRequest request, HttpServletResponse response
	) throws Exception {
		// ユーザーからの入力値を受け取る
		String cd=request.getParameter("cd");
		String name=request.getParameter("name");

		 // getUserメソッドを呼び出してユーザー情報を取得
        Teacher teacher = Util.getUser(request);
     // TeacherオブジェクトからSchoolオブジェクトを取得
        //School school = teacher.getSchool();

		// Subjectビーンに設定
		Subject subject=new Subject();
		subject.setCd(cd);
		subject.setName(name);
		//subject.setSchool(school);


		// SubjectDAOインスタンスを生成
		SubjectDAO dao=new SubjectDAO();
		// SubjectDAOのsavaメソッドを実行してデータベースに登録
		boolean line = dao.update(subject);

		// lineが0でなければ登録成功
		if (line) {
			request.setAttribute("message", "登録しました");
			return "subject_update_done.jsp";
		} else {
			request.setAttribute("message", "登録に失敗しました");
		}
		return "subject_list.jsp";
	}



}
