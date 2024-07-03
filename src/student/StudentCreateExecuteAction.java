package student;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
			 // セッションが存在しない場合はログインページにリダイレクト
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
			boolean isAttend = true;
			
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

				request.setAttribute("messageYear", "入学年度を選択してください");
				request.setAttribute("setno", no);
				request.setAttribute("year", entYearSet);
				request.setAttribute("setname", name);
				request.setAttribute("setclass", classNum);
				return "/student/student_create.jsp"; // エラーメッセージを表示するためのJSP
			}

			int entYear2 = Integer.parseInt(entYear);




			// Subjectビーンに設定
			Student student = new Student();
			student.setEntYear(entYear2);
			student.setNo(no);
			student.setName(name);
			student.setClassNum(classNum);
			student.setIsAttend(isAttend);
			student.setSchool(school);



			// SubjectDAOインスタンスを生成
			StudentDao dao= new StudentDao();



			// 重複チェック
			if (dao.get(no) != null) {

				request.setAttribute("messageNo", "学生番号が重複しています");
				request.setAttribute("setyear", entYear2);
				request.setAttribute("setno", no);
				request.setAttribute("setname", name);
				request.setAttribute("setclass", classNum);
				return "/student/student_create.jsp"; // エラーメッセージを表示するためのJSP
			}

			// SubjectDAOのsavaメソッドを実行してデータベースに登録

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
