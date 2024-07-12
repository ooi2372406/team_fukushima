package student;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

//import java.io.IOException;　いらない？
//import javax.servlet.ServletException;　いらない？
//import javax.servlet.annotation.WebServlet;　いらない？
//import javax.servlet.http.HttpServlet;　いらない？
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
//import javax.servlet.http.HttpSなんちゃら１

import bean.Student;
import bean.Teacher;
import dao.ClassNumDao;
import dao.StudentDao;
import tool.Action;
import util.Util;

public class StudentListAction extends Action {

	@SuppressWarnings("unused")
	@Override
    public String execute(HttpServletRequest req , HttpServletResponse res) throws Exception {
		try{
			 // セッションが存在しない場合はログインページにリダイレクト
            HttpSession session = req.getSession(false);
            if (session == null) {

                return "/student/login/login.jsp";
            }

            Teacher teacher = Util.getUser(req);
            if (teacher == null) {
               session.invalidate();
               res.sendRedirect(req.getContextPath() + "/student/login/login.jsp");
                return null;
           }

			int entYear = 0;

			String entYearStr=req.getParameter("f1");//入力された入学年度
			String classNum =req.getParameter("f2");//入力されたクラス番号
			if (classNum != null && classNum.equals(""))  classNum = "0";
			if (entYearStr != null && entYearStr.equals("")) entYearStr = null;
			String isAttendStr=req.getParameter("f3");//入力された在学フラグ


			if (entYearStr != null) {
				//数値に変換
					entYear = Integer.parseInt(entYearStr);
			}

			//入学年度


			boolean isAttend = "true".equals(isAttendStr);




			List<Student> students = null;//学生リスト

			LocalDate todaysDate = LocalDate.now();//LcalDateインスタンスを取得

			int year = todaysDate.getYear();//現在の年を取得

			StudentDao sDao = new StudentDao();//学生Dao

			ClassNumDao cNumDao = new ClassNumDao();//クラス番号Daoを初期化

			HashMap<String, String> errors = new HashMap<>();//エラーメッセージ

			//DBからデータ取得３
			//ログインユーザーの学校コードをもとにクラス番号の一覧を取得
			List<String> list = cNumDao.filter(teacher.getSchool());

			//リストを初期化
			List<Integer> entYearSet = new ArrayList<>();
			//10年前から1年後まで年をリストに追加
			for (int i = year - 10; i < year + 10; i++) {
				entYearSet.add(i);
			}

			if (entYearStr == null && classNum == null) {


			    students = sDao.filter(teacher.getSchool());
				req.setAttribute("studentList", students);
				req.setAttribute("class_num_set", list);
				req.setAttribute("ent_year_set", entYearSet);
				req.setAttribute("setClassNum", classNum);

			    return "student_list.jsp";
			}


			if (entYearStr == null && classNum.equals("0")) {
				System.out.println("こっちにとんでる2");

			    students = sDao.filter(teacher.getSchool());
				req.setAttribute("studentList", students);
				req.setAttribute("class_num_set", list);
				req.setAttribute("ent_year_set", entYearSet);


			    return "student_list.jsp";
			}

			if (entYearStr == null && classNum != null) {
				System.out.println("こっちにとんでる");
			    req.setAttribute("yearerrormessage", "クラスを指定する場合は入学年度も指定してください");
			    students = sDao.filter(teacher.getSchool());
				req.setAttribute("studentList", students);
				req.setAttribute("class_num_set", list);
				req.setAttribute("ent_year_set", entYearSet);
				req.setAttribute("setClassNum", classNum);

			    return "student_list.jsp";
			}
/*

*/
            // 萱野テスト 全学生取得してフォワード
			if (isAttendStr == null) {
				students = sDao.filter(teacher.getSchool());
				req.setAttribute("studentList", students);
				req.setAttribute("class_num_set", list);
				req.setAttribute("ent_year_set", entYearSet);

				// null だけではなく、空のリストの場合も考える
				if (students == null || students.isEmpty()){

					req.setAttribute("message" , "学生情報が存在しませんでした。");
				}else{

					return "student_list.jsp";
				}
			}


			if (entYear != 0 && !classNum.equals("0")) {
				//入学年度とクラス番号を指定
				students = sDao.filter(teacher.getSchool(), entYear, classNum, isAttend);
				req.setAttribute("studentList", students);
				req.setAttribute("class_num_set", list);
				req.setAttribute("ent_year_set", entYearSet);
				req.setAttribute("setYear" , entYear);
				req.setAttribute("setClassNum", classNum);
				req.setAttribute("attend", isAttend);

				// null だけではなく、空のリストの場合も考える
				if (students == null || students.isEmpty()){
					req.setAttribute("message" , "学生情報が存在しませんでした。");
				}else{
					return "student_list.jsp";
				}

			} else if (entYear != 0 && classNum.equals("0")) {

				//入学年度のみ指定
				students = sDao.filter(teacher.getSchool(), entYear, isAttend);
				req.setAttribute("studentList", students);
				req.setAttribute("class_num_set", list);

				req.setAttribute("attend", isAttend);
				// null だけではなく、空のリストの場合も考える
				if (students == null || students.isEmpty()){
					req.setAttribute("message" , "学生情報が存在しませんでした。");
					req.setAttribute("setYear" , entYear);

					req.setAttribute("attend", isAttend);
				}else{
					req.setAttribute("ent_year_set", entYearSet);
					req.setAttribute("setYear" , entYear);

					return "student_list.jsp";
				}

			}else if (entYear == 0 && classNum == null || entYear == 0 && classNum.equals("0")) {
				//指定なしの場合
				//全学生情報を取得
				students = sDao.filter(teacher.getSchool(), isAttend);
				req.setAttribute("studentList", students);
				req.setAttribute("class_num_set", list);
				req.setAttribute("ent_year_set", entYearSet);
			}
		/*
		for (Student subject : students) {
           System.out.println("入学年度: " + subject.getEntYear() + ", 学生番号 " + subject.getNo()
            + "氏名" + subject.getName() + "在学フラグ" + subject.getIsAttend());
        }
        */



		//レスポンス値をセット6
		//リクエストに入学年度をセット
			req.setAttribute("f1",entYear);
			//リクエストにクラス番号をセット
			req.setAttribute("f2",classNum);
			//在学フラグが送信されていた場合
			if(isAttendStr != null) {
				//在学フラグを立てる
				isAttend = true;
				//リクエストに在学フラグをセット
				req.setAttribute("f3",isAttendStr);
			}
			//リクエストに学生リストをセット
			req.setAttribute("f3",isAttendStr);

			//リクエストにデータをセット
			req.setAttribute("class_num_set", list);  //クラス

			req.setAttribute("ent_year_set", entYearSet); // 入学年度リスト

			//JSPへフォワード7
			return "student_list.jsp";


		}catch(Exception e){
  		 // エラーメッセージを設定してエラーページに遷移
			req.setAttribute("message", "エラーが発生しました。");
			return "/student/subject/subject_error.jsp";
		}
	}
}
