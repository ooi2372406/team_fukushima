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

public class SubjectCreateExecuteAction extends Action {
	public String execute(
		HttpServletRequest request, HttpServletResponse response
	) throws Exception {
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

			// ユーザーからの入力値を受け取る
			String cd=request.getParameter("cd");
			String name=request.getParameter("name");

			// getUserメソッドを呼び出してユーザー情報を取得

			// TeacherオブジェクトからSchoolオブジェクトを取得
			School school = teacher.getSchool();

			// Subjectビーンに設定
			Subject subject=new Subject();
			subject.setCd(cd);
			subject.setName(name);
			subject.setSchool(school);


			// SubjectDAOインスタンスを生成
			SubjectDAO dao=new SubjectDAO();


			// 重複チェック
			if (dao.get(cd, school) != null) {
				request.setAttribute("message", "科目コードが重複しています");
				request.setAttribute("cd", cd);
				request.setAttribute("name", name);
				return "/student/subject/subject_create.jsp"; // エラーメッセージを表示するためのJSP
			}

			// SubjectDAOのsavaメソッドを実行してデータベースに登録
			boolean line = dao.save(subject);

			// lineが0でなければ登録成功
			if (line) {
				request.setAttribute("message", "登録しました");
				return "/student/subject/subject_create_done.jsp";

			}

			return "/student/subject/subject_create_done.jsp";
		}catch(Exception e){
   		 // エラーメッセージを設定してエラーページに遷移
           request.setAttribute("message", "エラーが発生しました。");
           return "/student/subject/subject_error.jsp";

   	}

	}



}
