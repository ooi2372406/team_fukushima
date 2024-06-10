package util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import bean.Teacher;


public class Util{

	public static Teacher getUser(HttpServletRequest request){
		// セッションからユーザー情報を取得
		HttpSession session = request.getSession();
		// セッションからユーザー情報を取得してTEACHERに渡す
		Teacher teacher = (Teacher) session.getAttribute("user");
		//sessionから教師情報を取得して戻す
		return teacher;
	}


	public static void setClassNumSet(HttpServletRequest request){
	}

	public static void setEntYearSet(HttpServletRequest request){

	}

	public static void setSubjects(HttpServletRequest request){

	}

	public static void setNumSet(HttpServletRequest request){

	}


}