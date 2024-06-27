package student;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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
			LocalDate todaysDate = LocalDate.now();//LcalDateインスタンスを取得
			int year = todaysDate.getYear();//現在の年を取得
			List<Integer> entYearSet = new ArrayList<>();
			//10年前から10年後まで年をリストに追加
			for (int i = year - 10; i < year + 10; i++) {
				entYearSet.add(i);
			}
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


    		// 意図的に例外を発生させる処理（普段はつかわない）
    		 //if (true) {
    	     //       throw new RuntimeException("テスト用の予期せぬエラー");
    	     // }

			// ユーザーからの入力値を受け取る

			// 入学年度
			String entYear =request.getParameter("ent_year");
			if (entYear.equals("--------") || entYear.equals("")){
				System.out.println("入学年度ここまではきている");
				request.setAttribute("messageYear", "入学年度を選択してください");
				request.setAttribute("setno", no);
				request.setAttribute("year", entYearSet);
				request.setAttribute("setname", name);
				request.setAttribute("setclass", classNum);
				return "/student/student_create.jsp"; // エラーメッセージを表示するためのJSP
			}

			System.out.println(entYear);
			int entYear2 = Integer.parseInt(entYear);

			System.out.println("ここまで");
			System.out.println(entYear2);


			// Subjectビーンに設定
			Student student = new Student();
			student.setEntYear(entYear2);
			student.setNo(no);
			student.setName(name);
			student.setClassNum(classNum);
			student.setSchool(school);



			// SubjectDAOインスタンスを生成
			StudentDao dao= new StudentDao();
			System.out.println("ダオここまではきている");


			// 重複チェック
			if (dao.get(no) != null) {
				System.out.println("学生番号ここまではきている");
				request.setAttribute("messageNo", "学生番号が重複しています");
				request.setAttribute("setyear", entYear2);
				request.setAttribute("setno", no);
				request.setAttribute("setname", name);
				request.setAttribute("setclass", classNum);
				return "/student/student_create.jsp"; // エラーメッセージを表示するためのJSP
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
			System.out.println(e);
           request.setAttribute("message", "エラーが発生しました。");
           return "/student/subject/subject_error.jsp";

   	}

	}



}
