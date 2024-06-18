package student;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.School;
import bean.Student;
import bean.Teacher;
import dao.StudentDao;
import tool.Action;
import util.Util;

public class StudentCreateExecuteAction extends Action {
	public String execute(
		HttpServletRequest request, HttpServletResponse response
	) throws Exception {
		try{

    		// 意図的に例外を発生させる処理（普段はつかわない）
    		 //if (true) {
    	     //       throw new RuntimeException("テスト用の予期せぬエラー");
    	     // }

			// ユーザーからの入力値を受け取る

			// 入学年度
			int entYear = Integer.parseInt(request.getParameter("ent_year"));
			// 学生番号
			String no =request.getParameter("no");
			// 学生名
			String name = request.getParameter("name");
			// クラス番号
			String classNum = request.getParameter("class_num");

			// getUserメソッドを呼び出してユーザー情報を取得
			Teacher teacher = Util.getUser(request);
			// TeacherオブジェクトからSchoolオブジェクトを取得
			School school = teacher.getSchool();

			// Subjectビーンに設定
			Student student = new Student();
			student.setEntYear(entYear);
			student.setNo(no);
			student.setName(name);
			student.setClassNum(classNum);
			student.setSchool(school);
			
			

			// SubjectDAOインスタンスを生成
			StudentDao dao= new StudentDao();


			// 重複チェック
			if (dao.get(no) != null) {
				request.setAttribute("message", "学生番号が重複しています");
				return "/student//student_create.jsp"; // エラーメッセージを表示するためのJSP
			}

			// SubjectDAOのsavaメソッドを実行してデータベースに登録
			System.out.println("ここまではきている");
			boolean line = dao.save(student);

			// lineが0でなければ登録成功
			if (line) {
				request.setAttribute("message", "登録しました");
				return "/student/student_create_done.jsp";

			}

			return "/student/student_create_done.jsp";
		}catch(Exception e){
   		 // エラーメッセージを設定してエラーページに遷移
           request.setAttribute("message", "エラーが発生しました。");
           return "/student/subject/subject_error.jsp";

   	}

	}



}
