package student;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.School;
import bean.Teacher;
import bean.Test;
import dao.TestDao;
import tool.Action;
import util.Util;

public class TestRegistAction extends Action {
    public String execute(
        HttpServletRequest request, HttpServletResponse response
    ) throws Exception {
    	try{

    		// 意図的に例外を発生させる処理（普段はつかわない）
    		 //if (true) {
    	     //       throw new RuntimeException("テスト用の予期せぬエラー");
    	     // }

    		// getUserメソッドを呼び出してユーザー情報を取得
    		Teacher teacher = Util.getUser(request);
    		// TeacherオブジェクトからSchoolオブジェクトを取得
    		School school = teacher.getSchool();



    		TestDao dao = new TestDao();
    		List<Test> tests = dao.filter(school);
    		for (Test test : tests) {
                System.out.println("Subject CD: " + test.getClass() + ", Name: " + test.getName());
            }
    		// HttpSessionオブジェクトを取得し、そこにユーザー情報を設定する
    		HttpSession session = request.getSession();
    		// Teacherオブジェクトをセッションに保存する
    		session.setAttribute("subject", tests);

            return "/student/test_regist.jsp"; // ログイン成功時のリダイレクト先
    	}catch(Exception e){
    		 // エラーメッセージを設定してエラーページに遷移
            request.setAttribute("message", "エラーが発生しました。");
            return "/student/subject/test_error.jsp";

    	}
        }



    }

