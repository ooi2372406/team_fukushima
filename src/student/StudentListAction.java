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

public class StudentListAction extends Action {

	@Override
    public String execute(HttpServletRequest req , HttpServletResponse res) throws Exception {
		HttpSession session = req.getSession();//セッション
		Teacher teacher = (Teacher)session.getAttribute("user");

		String entYearStr=req.getParameter("f1");//入力された入学年度
		String classNum =req.getParameter("f2");//入力されたクラス番号
		String isAttendStr=req.getParameter("f3");//入力された在学フラグ


		int entYear = 0;//入学年度

		boolean isAttend = false;//在学フラグ

		List<Student> students = null;//学生リスト

		LocalDate todaysDate = LocalDate.now();//LcalDateインスタンスを取得

		int year = todaysDate.getYear();//現在の年を取得

		StudentDao sDao = new StudentDao();//学生Dao

		ClassNumDao cNumDao = new ClassNumDao();//クラス番号Daoを初期化

		HashMap<String, String> errors = new HashMap<>();//エラーメッセージ

		//DBからデータ取得３
		//ログインユーザーの学校コードをもとにクラス番号の一覧を取得
		List<String> list = cNumDao.Filter(teacher.getSchool());

		if (entYear != 0 && !classNum.equals("0")) {
			//入学年度とクラス番号を指定
			students = sDao.filter(teacher.getSchool(), entYear, classNum, isAttend);
		} else if (entYear != 0 && classNum.equals("0")) {
			//入学年度のみ指定
			students = sDao.filter(teacher.getSchool(), entYear, isAttend);
		}else if (entYear == 0 && classNum == null || entYear == 0 && classNum.equals("0")) {
			//指定なしの場合
			//全学生情報を取得
			students = sDao.filter(teacher.getSchool(), isAttend);
		}
		req.setAttribute("studentList", students);
		for (Student subject : students) {
           System.out.println("入学年度: " + subject.getEntYear() + ", 学生番号 " + subject.getNo()
            + "氏名" + subject.getName() + "在学フラグ" + subject.getIsAttend());
        }
		//ビジネスロジック４
		if (entYearStr != null) {
			//数値に変換
			entYear = Integer.parseInt(entYearStr);
		}
		//リストを初期化
		List<Integer> entYearSet = new ArrayList<>();
		//10年前から1年後まで年をリストに追加
		for (int i = year - 10; i < year + 1; i++) {
			entYearSet.add(i);
		}
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
		System.out.println(list);
		req.setAttribute("ent_year_set", entYearSet); // 入学年度リスト

		//JSPへフォワード7
		return "student_list_sample.jsp";


	}
	}
