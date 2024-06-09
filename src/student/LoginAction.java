package student;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// このURLパターンはhttp://localhost:8080/kouka2で実行される
@WebServlet(urlPatterns={"/student/loginaction"})
public class LoginAction extends HttpServlet {
	public void doGet (
		HttpServletRequest request, HttpServletResponse response
	) throws ServletException, IOException {
		
		PrintWriter out=response.getWriter();
		try {

    		// 意図的に例外を発生させる処理（普段はつかわない）
    		 //if (true) {
    	     //       throw new RuntimeException("テスト用の予期せぬエラー");
    	     // }

			// login.jspへフォワードするだけ
			request.getRequestDispatcher("/student/login.jsp")
				.forward(request, response);
		}catch(Exception e){
   		 // エラーメッセージを設定してエラーページに遷移
           request.setAttribute("message", "エラーが発生しました。");
           request.getRequestDispatcher("/student/subject_error.jsp")
			.forward(request, response);

   	}
	}
}
