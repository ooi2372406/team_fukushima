package student;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Login;
import dao.LoginDAO;
import tool.Action;

public class LoginExecuteAction extends Action {
    public String execute(
        HttpServletRequest request, HttpServletResponse response
    ) throws Exception {

        HttpSession session = request.getSession();

        String id = request.getParameter("id");
        String password = request.getParameter("password");

        LoginDAO dao = new LoginDAO();
        List<Login> customer = dao.search(id, password); // ログインIDとパスワードを使って検証する

        if (customer != null && customer.size() > 0) {
        	Login user = customer.get(0);
            session.setAttribute("user", user);
            System.out.println(user);
            return "menu.jsp"; // ログイン成功時のリダイレクト先
        }

        // ログイン失敗時の処理
        // 例: エラーメッセージをセットしてログインページにリダイレクト
        request.setAttribute("errorMessage", "IDまたは、パスワードが確認できませんでした。");
        return "login.jsp";
    }
}
