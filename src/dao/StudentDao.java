package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Student;

public class StudentDao extends DAO {

	private String baseSql = "SELECT * FROM STUDENT WHERE SCHOOL_CD=?";

	public Student get(String no) throws Exception {

		// 学生インスタンスを初期化
		Student student = new Student();
		// データベースへのコネクションを確立
		Connection con = getConnection();
		// プリペアードステートメント
		PreparedStatement st = null;
		ResultSet rs = null;

		try{
			// プリペアードステートメントにSQL分をセット
			st = con.prepareStatement("SELECT * FROM STUDENT WHERE NO = ?");
			// プリペアードステートメントに学生番号をバインド
			st.setString(1 , no);
			// プリペアードステートメントを実行
			rs = st.executeQuery();

			// 学校DAOを初期化
			SchoolDao schooldao = new SchoolDao();

			if (rs.next()){
				// リザルトセットが存在する場合
				// 学生インスタンスに検索結果をセット
				student.setNo(rs.getString("no"));
				student.setName(rs.getString("name"));
				student.setEntYear(rs.getInt("ent_year"));
				student.setClassNum(rs.getString("class_num"));
				student.setIsAttend(rs.getBoolean("is_attend"));
				// 学校フィールドには学校コードで検索した学校インスタンスをセット

				student.setSchool(schooldao.get(rs.getString("school_cd")));

			} else {
				// リザルトセットが存在しない場合
				// 学生インスタンスにnullをセット
				student = null;
			}
		}catch (Exception e){
			throw e;
		}finally{
			// プリペアードステートメントを閉じる
			if (st != null){
				try{
					st.close();
				}catch (SQLException sqle){
					throw sqle;
				}
			}

			// コネクションを閉じる
			if (con != null){
				try {
					con.close();
				}catch (SQLException sqle){
					throw sqle;
				}
			}
		}
		return student;
	}

	private List<Student> postFilter(ResultSet rSet, School school) throws Exception {
		List<Student> list = new ArrayList<>();

		try{
			//リザルトセットを全件走査
			while (rSet.next()){
				// 学生インスタンスを初期化
				Student student = new Student();
				// 学生インスタンスに検索結果をセット
				student.setNo(rSet.getString("no"));
				student.setName(rSet.getString("name"));
				student.setEntYear(rSet.getInt("ent_year"));
				student.setClassNum(rSet.getString("class_num"));
				student.setIsAttend(rSet.getBoolean("is_attend"));
				student.setSchool(school);
				//リストに追加
				list.add(student);

			}
		}catch (SQLException | NullPointerException e){
			e.printStackTrace();
		}
		return list;
	}

	public List<Student> filter(School school, int entYear, String classNum, boolean isAttend) throws Exception {
		//リストの初期化
		List<Student> list = new ArrayList<>();
		// データベースへ接続
		Connection con = getConnection();
		// プリペアードステートメント
		PreparedStatement st = null;
		// リザルトセット
		ResultSet rs = null;


		// SQL分の作成
		// SQL分　条件
		String condition = "AND ENT_YEAR=? AND CLASS_NUM = ?";
		// SQL分　昇順ソート
		String order = "ORDER BY NO ASC";
		// SQL分の在学フラグの条件
		String conditionIsAttend = "";
		// 在学フラグがtureの場合
		if (isAttend) {
			conditionIsAttend = "AND IS_ATTEND = TRUE";
		}

		try {
			// プリペアードステートメントにSQL分をセット
			st = con.prepareStatement(baseSql + condition + conditionIsAttend + order);
			//プリペアードステートメントに学校コードをバインド
			st.setString(1 ,  school.getCd());
			// プリペアードステートメントに入学年度をバインド
			st.setInt(2 ,  entYear);
			// プリペアードステートメントにクラス番号をバインド
			st.setString(3, classNum);
			// プリペアードステートメントを実行
			rs = st.executeQuery();

			// リストへの格納処理を実行
			list = postFilter(rs , school);
		}catch (Exception e){
			throw e;
		}finally{
			// プリペアードステートメントを閉じる
			if (st != null){
				try{
					st.close();
				}catch (SQLException sqle){
					throw sqle;
				}
			}

			// コネクションを閉じる
			if (con != null){
				try {
					con.close();
				}catch (SQLException sqle){
					throw sqle;
				}
			}
		}
		 return list;

	}

	public List<Student> filter(School school, int entYear, boolean isAttend) throws Exception {
		// リストを初期化
		List<Student> list = new ArrayList<>();
		// コネクションを確立
		Connection con = getConnection();
		// プリペアードステートメント
		PreparedStatement st = null;
		// リザルトセット
		ResultSet rs = null;

		// SQL分
		// 条件指定
		String condition = "AND ENT_YEAR = ?";
		// 昇順ソート
		String order = " ORDER BY NO ASC ";
		// SQL分の在学フラグ
		String conditionIsAttend = "";
		// 在学フラグがtrueだった場合
		if (isAttend) {
			conditionIsAttend = " AND IS_ATTEND = TRUE";
		}

		try{
			// プリペアードステートメントにSQL分をセット
			 String sql = baseSql + condition + conditionIsAttend + order;
			 st = con.prepareStatement("SELECT * FROM STUDENT WHERE SCHOOL_CD=? AND ENT_YEAR = ? AND IS_ATTEND = TRUE ORDER BY NO ASC");
			// プリペアードステートメントに学校コードをバインド
			st.setString(1, school.getCd());
			// プリペアードステートメントに入学年度をバインド
			st.setInt(2 , entYear);
			// プリペアードステートメントを実行
			rs = st.executeQuery();

			// リストへの格納処理を実行
			list = postFilter(rs , school);

		}catch (Exception e){
			throw e;
		}finally{
			// プリペアードステートメントを閉じる
			if (st != null){
				try{
					st.close();
				}catch (SQLException sqle){
					throw sqle;
				}
			}

			// コネクションを閉じる
			if (con != null){
				try {
					con.close();
				}catch (SQLException sqle){
					throw sqle;
				}
			}
		}

		return list;
	}



	public List<Student> filter(School school, boolean isAttend) throws Exception {

			// リストを初期化
			List<Student> list = new ArrayList<>();
			// コネクションを確立
			Connection con = getConnection();
			// プリペアードステートメント
			PreparedStatement st = null;
			// リザルトセット
			ResultSet rs = null;

			String order = " ORDER BY NO ASC";

			String conditionIsAttend = "";
			// 在学フラグがtrueだった場合
			if (isAttend) {
				conditionIsAttend = " AND IS_ATTEND = TRUE";
			}

			try{
				// プリペアードステートメントにSQL分をセット
				st = con.prepareStatement(baseSql + conditionIsAttend + order);
				// プリペアードステートメントに学校コードをバインド
				st.setString(1, school.getCd());
				// プリペアードステートメントを実行
				rs = st.executeQuery();

				// リストへの格納処理を実行
				list = postFilter(rs , school);

			}catch (Exception e){
				throw e;
			}finally{
				// プリペアードステートメントを閉じる
				if (st != null){
					try{
						st.close();
					}catch (SQLException sqle){
						throw sqle;
					}
				}

				// コネクションを閉じる
				if (con != null){
					try {
						con.close();
					}catch (SQLException sqle){
						throw sqle;
					}
				}
			}
			return list;

	}

	public boolean save(Student student) throws Exception {

		// コネクションを確立
		Connection con = getConnection();
		// プリペアードステートメント
		PreparedStatement st = null;
		// 実行件数
		int count = 0;

		try{
			// データベースから学生を取得
			Student old = get(student.getNo());
			if(old == null) {
				// 学生が存在しなかった場合
				// プリペアードステートメントにINSERT分をセット
				st = con.prepareStatement(
						"INSERT INTO STUDENT(NO , NAME , ENT_YERA , CLASS_NUM , IS_ATTEND , SCHOOL_CD) VALUES(? , ? , ? , ? , ? , ?)"
						);
				// プリペアードステートメントに値をバインド
				st.setString(1 ,  student.getNo());
				st.setString(2 , student.getName());
				st.setInt(3 ,  student.getEntYear());
				st.setString(4 , student.getClassNum());
				st.setBoolean(5 , student.getIsAttend());
				st.setString(6 ,  student.getSchool().getCd());
			}else {
				System.out.println("存在している");
				// 学生が存在した場合
				// プリペアードステートメントにUPDATE分をセット
				st = con.prepareStatement(
						"UPDATE STUDENT SET NAME = ? , ENT_YEAR = ? , CLASS_NUM = ? , IS_ATTEND = ? WHERE NO = ?"
						);
				// プリペアードステートメントに値をバインド
				st.setString(1 ,  student.getName());
				st.setInt(2 ,  student.getEntYear());
				st.setString(3 ,  student.getClassNum());
				st.setBoolean(4 ,  student.getIsAttend());
				st.setString(5 ,  student.getNo());
			}
			// プリペアードステートメントを実行
			count = st.executeUpdate();
		}catch (Exception e){
			throw e;
		}finally{
			// プリペアードステートメントを閉じる
			if (st != null){
				try{
					st.close();
				}catch (SQLException sqle){
					throw sqle;
				}
			}

			// コネクションを閉じる
			if (con != null){
				try {
					con.close();
				}catch (SQLException sqle){
					throw sqle;
				}
			}
		}
		if (count > 0) {
			// 実行件数が１件以上ある場合
			return true;
		}else {
			 // 実行件数が０件の場合
			return false;
		}

	}

}