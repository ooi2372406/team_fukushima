package student;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Teacher;
import dao.TeacherDAO;
import tool.Action;

public class LoginExecuteAction extends Action {
    public String execute(
        HttpServletRequest request, HttpServletResponse response
    ) throws Exception {
    	try{
    		
    		// 意図的に例外を発生させる処理（普段はつかわない）
    		 //if (true) {
    	     //       throw new RuntimeException("テスト用の予期せぬエラー");
    	     // }

    		HttpSession session = request.getSession();

    		String id = request.getParameter("id");
    		String password = request.getParameter("password");

    		TeacherDAO dao = new TeacherDAO();
    		List<Teacher> customer = dao.login(id, password); // ログインIDとパスワードを使って検証する

    		if (customer != null && customer.size() > 0) {
    			Teacher user = customer.get(0);
    			session.setAttribute("user", user);

    			return "menu.jsp"; // ログイン成功時のリダイレクト先
    		}

    		// ログイン失敗時の処理
    		// 例: エラーメッセージをセットしてログインページにリダイレクト
    		request.setAttribute("errorMessage", "IDまたは、パスワードが確認できませんでした。");
    		return "login.jsp";
    	}catch(Exception e){
   		 // エラーメッセージを設定してエラーページに遷移
           request.setAttribute("message", "エラーが発生しました。");
           return "subject_error.jsp";

   	}
    }
}
