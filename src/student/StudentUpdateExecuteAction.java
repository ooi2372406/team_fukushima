package student;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.School;
import bean.Student;
import bean.Teacher;
import dao.StudentDao;
import tool.Action;
import util.Util;

public class StudentUpdateExecuteAction extends Action {
	public String execute(
		HttpServletRequest request, HttpServletResponse response
	) throws Exception {
		try{
    		// 意図的に例外を発生させる処理（普段はつかわない）
   		 //if (true) {
   	     //       throw new RuntimeException("テスト用の予期せぬエラー");
   	     // }
			HttpSession session = request.getSession();//セッション
			// ユーザーからの入力値を受け取る

			String attendance = request.getParameter("si_attend");
			boolean isAttend = "true".equals(attendance);
			// getUserメソッドを呼び出してユーザー情報を取得
			Teacher teacher = Util.getUser(request);
			// TeacherオブジェクトからSchoolオブジェクトを取得
			School school = teacher.getSchool();


			StudentDao dao = new StudentDao();
			Student student = dao.get(request.getParameter("no"));
			student.setName(request.getParameter("name"));
			student.setClassNum(request.getParameter("class_num"));
			student.setIsAttend(isAttend);


			boolean attend = dao.save(student);





			// attendが0でなければ登録成功
			if (!attend) {
				request.setAttribute("message", "登録に失敗しました");
			} request.setAttribute("message", "登録しました");
			return "/student/student_update_done.jsp";

		}catch(Exception e){
   		 // エラーメッセージを設定してエラーページに遷移
           request.setAttribute("message", "エラーが発生しました。");
           return "/student/subject/subject_error.jsp";

   	}
	}

}
