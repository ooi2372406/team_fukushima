package subject;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Subject;
import bean.Teacher;
import dao.SubjectDAO;
import tool.Action;
import util.Util;

public class SubjectDeleteExecuteAction extends Action {
	public String execute(
		HttpServletRequest request, HttpServletResponse response
	) throws Exception {
		try{

    		// 意図的に例外を発生させる処理（普段はつかわない）
    		 //if (true) {
    	     //       throw new RuntimeException("テスト用の予期せぬエラー");
    	     // }

			// ユーザーからの入力値を受け取る
			String cd=request.getParameter("cd");

			// getUserメソッドを呼び出してユーザー情報を取得
			Teacher teacher = Util.getUser(request);
			// TeacherオブジェクトからSchoolオブジェクトを取得
			//School school = teacher.getSchool();

			// Subjectビーンに設定
			Subject subject=new Subject();
			subject.setCd(cd);
			//subject.setSchool(school);


			// SubjectDAOインスタンスを生成
			SubjectDAO dao=new SubjectDAO();
			// SubjectDAOのsavaメソッドを実行してデータベースに登録
			boolean line = dao.delete(subject);

			// lineが0でなければ登録成功
			if (line) {
				request.setAttribute("message", "登録しました");
				return "/student/subject/subject_delete_done.jsp";
			} else {
				request.setAttribute("message", "登録に失敗しました");
			}
			return "/student/subject/subject_delete.jsp";
		}catch(Exception e){
   		 // エラーメッセージを設定してエラーページに遷移
           request.setAttribute("message", "エラーが発生しました。");
           return "/student/subject/subject_error.jsp";

   	}
	}



}
