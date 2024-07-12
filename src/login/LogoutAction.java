package login;

import javax.servlet.http.Cookie; // 正しいインポート
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import tool.Action;

public class LogoutAction extends Action {
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        try {
            HttpSession session = request.getSession();
            session.invalidate(); // セッションの無効化

            // クッキーの削除
            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if (cookie.getName().equals("JSESSIONID")) {
                        cookie.setMaxAge(0); // クッキーの有効期限を0に設定して削除
                        cookie.setPath("/"); // クッキーのパスを設定
                        response.addCookie(cookie);
                        break;
                    }
                }
            }

            // キャッシュを無効にするヘッダーの設定
            response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
            response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
            response.setDateHeader("Expires", 0); // Proxies.

            return "/student/login/logout.jsp"; // ログアウト画面に遷移
        } catch (Exception e) {
            // エラーメッセージを設定してエラーページに遷移
            request.setAttribute("message", "エラーが発生しました。");
            return "subject_error.jsp";
        }
    }
}
