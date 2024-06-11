package student;

import java.sql.Connection;
import java.sql.PreparedStatement;
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
		List<String> list = cNumDao. filter(teacher.getSchool());

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
		req.setAttribute("class_num_set", list);
		req.setAttribute("ent_year_set", entYearSet);

		//JSPへフォワード7
		req.getRequestDispatcher("student_list.jsp").forward(req,res);

		private String baseSql = "select * from student where school_cd=?";

		private List<Student> postFilter(ResultSet rSet, School school) throws Exception{
			//リストを初期化
		    List<Student> list = new ArrayList<>();
		    try {
		    	//リザルトセットを全権取得
		        while (rSet.next()) {
		        	//学生インスタンスを初期化
		            Student student = new Student();
		            //学生インスタンスに検索結果をセット
		            student.setNo(rSet.getString("no"));
		            student.setName(rSet.getString("name"));
		            student.setEntYear(rSet.getInt("ent_year"));
		            student.setClassNum(rSet.getString("class_num"));
		            student.setAttend(rSet.getBoolean("is_attend"));
		            student.setSchool(school);
		            //リストに追加
		            list.add(student);
		        }
		    } catch (SQLException | NullPointerException e) {
		        e.printStackTrace();
		    }

		    return list;
		}

		public List<Student> filter(School school, int entYear, String classNum, boolean isAttend) throws Exception {
		    // リストを初期化
		    List<Student> list = new ArrayList<>();

		    // コネクションを確立
		    Connection connection = getConnection();

		    // プリペアドステートメント
		    PreparedStatement statement = null;
		    // リザルトセット
		    ResultSet rSet = null;

		    // SQL文の条件
		    String condition = " and ent_year=? and class_num=?";
		    // SQL文のソート
		    String order = " order by no asc";

		    // SQL文の在学フラグ条件
		    String conditionIsAttend = "";
		    // 在学フラグがtrueの場合
		    if (isAttend) {
		        conditionIsAttend = " and is_attend=true";
		    }

		    try {
		        // プリペアドステートメントにSQL文をセット
		        statement = connection.prepareStatement(baseSql + condition + conditionIsAttend + order);
		        // プリペアドステートメントに学校IDをバインド
		        statement.setInt(1, school.getId());
		        // プリペアドステートメントに入学年をバインド
		        statement.setInt(2, entYear);
		        // プリペアドステートメントにクラス番号をバインド
		        statement.setString(3, classNum);
		        // プリペアドステートメントを実行
		        rSet = statement.executeQuery();

		        //リストへの格納処理を実行
		        list = postFilter(rSet, school);
		    } catch (Exception e) {
		    	throw e;
		    } finally {
		    	// プリペアードステートメントを閉じる
		    	if (statement !=null) {
		    		try {
		    			statement.close();
		    		} catch (SQLException sqle){
		    			throw sqle;
		    		}
		    	}
		    	// コネクションを閉じる
		    	if (connection !=null) {
		    		try {
		    			connection.close();
		    		} cathsh (SQLException sqle) {
		    			throw sqle;
		    		}
		    	}
		    }
		    return list;
		}
		public List<Student> filter(School school, boolean isAttend) throws Exception {
		    // リストを初期化
		    List<Student> list = new ArrayList<>();

		    // コネクションを定義
		    Connection connection = getConnection();
		    
		    // プリペアドステートメント
		    PreparedStatement statement = null;
		    
		    // リザルトセット
		    ResultSet rSet = null;

		    // SQLの条件を定義
		    String order = " order by no asc";

		    // SQLの条件を作成
		    String conditionIsAttend = "";
		    if (isAttend) {
		        conditionIsAttend = " and is_attend=true";
		    }

		    try {
		        // プリペアドステートメントにSQL文をセット
		        statement = connection.prepareStatement(baseSql + conditionIsAttend + order);
		        
		        // プリペアドステートメントに値をバインド
		        statement.setString(1, school.getCd());

		        // プリペアドステートメントを実行
		        rSet = statement.executeQuery();

		        // リストへの結果処理を実行
		        list = postFilter(rSet, school);

		    } catch (Exception e) {
		        throw e;
		    } finally {
		        // プリペアードステートメントを閉じる
		        if (statement != null) {
		            try {
		                statement.close();
		            } catch (SQLException sqle) {
		                throw sqle;
		            }
		        }
		        //コネクションを閉じる
		        if (connection != null) {
		            try {
		                connection.close();
		            } catch (SQLException sqle) {
		                throw sqle;
		            }
		        }
		    }

		    return list;
		}

		
		

	}
