package student;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Subject;
import dao.SubjectDAO;
import tool.Action;

public class SubjectCreateExecuteAction extends Action {
	public String execute(
		HttpServletRequest request, HttpServletResponse response
	) throws Exception {
		// ユーザーからの入力値を受け取る
		String cd=request.getParameter("cd");
		String name=request.getParameter("name");
		String schoolcd = request.getParameter("schoolcd");

		// Studentビーンに設定
		Subject subject=new Subject();
		subject.setCd(cd);
		subject.setName(name);
		subject.setSchoolCd(schoolcd);

		// StudentDAOインスタンスを生成
		SubjectDAO dao=new SubjectDAO();
		// StudentDAOのstudentInsertメソッドを実行してデータベースに登録
		boolean line = dao.save(subject);

		// lineが0でなければ登録成功
		if (line) {
			request.setAttribute("message", "登録しました");
			return "subject_create_done.jsp";
		} else {
			request.setAttribute("message", "登録に失敗しました");
		}
		return "subject_list.jsp";
	}



}
