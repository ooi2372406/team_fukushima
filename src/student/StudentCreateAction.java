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

import bean.School;
import bean.Teacher;
import dao.ClassNumDao;
import tool.Action;
import util.Util;

public class StudentCreateAction extends Action {

	@Override
    public String execute(HttpServletRequest req , HttpServletResponse res) throws Exception {
		HttpSession session = req.getSession();//セッション

		LocalDate todaysDate = LocalDate.now();//LcalDateインスタンスを取得
		int year = todaysDate.getYear();//現在の年を取得
		// getUserメソッドを呼び出してユーザー情報を取得
		Teacher teacher = Util.getUser(req);
		// TeacherオブジェクトからSchoolオブジェクトを取得
		School school = teacher.getSchool();

		ClassNumDao cNumDao = new ClassNumDao();//クラス番号Daoを初期化

		HashMap<String, String> errors = new HashMap<>();//エラーメッセージ

		List<Integer> entYearSet = new ArrayList<>();
		//10年前から1年後まで年をリストに追加
		for (int i = year - 10; i < year + 1; i++) {
			entYearSet.add(i);
		}

		List<String> classnum = cNumDao.filter(school);

		req.setAttribute("year", entYearSet);
		req.setAttribute("classList", classnum);

		return "/student/student_create.jsp";

	}
}



