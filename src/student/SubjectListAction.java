package student;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Subject;
import dao.SubjectDAO;
import tool.Action;

public class SubjectListAction extends Action {
    public String execute(
        HttpServletRequest request, HttpServletResponse response
    ) throws Exception {

        HttpSession session = request.getSession();



        SubjectDAO dao = new SubjectDAO();
        List<Subject> subject = dao.get(); // ログインIDとパスワードを使って検証する



            session.setAttribute("subject", subject);
            return "subject_list.jsp"; // ログイン成功時のリダイレクト先
        }

        // ログイン失敗時の処理
        // 例: エラーメッセージをセットしてログインページにリダイレクト

    }

