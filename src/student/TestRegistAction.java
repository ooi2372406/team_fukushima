package student;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TestRegistAction extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // フォームからのデータを取得
        String studentId = request.getParameter("f4");
        String subjectId = request.getParameter("sj");

        // データベースへの登録処理（省略）
        // 登録処理が成功したと仮定

        // リクエスト属性にデータをセット
        request.setAttribute("studentId", studentId);
        request.setAttribute("subjectId", subjectId);

        // 登録完了ページにフォワード
        request.getRequestDispatcher("test_regist_done.jsp").forward(request, response);
    }
}
