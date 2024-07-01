package subject;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Teacher;
import tool.Action;
import util.Util;

public class SubjectCreateAction extends Action {
    public String execute(
        HttpServletRequest request, HttpServletResponse response
    ) throws Exception {
    	try{

    		// 意図的に例外を発生させる処理（普段はつかわない）
    		 //if (true) {
    	     //       throw new RuntimeException("テスト用の予期せぬエラー");
    	     // }

    		 HttpSession session = request.getSession(false);
             if (session == null) {

                 return "/student/login/login.jsp";
             }

             Teacher teacher = Util.getUser(request);
             if (teacher == null) {
                session.invalidate();
                response.sendRedirect(request.getContextPath() + "/student/login/login.jsp");
                 return null;
            }

			return "/student/subject/subject_create.jsp";
    	}catch(Exception e){
   		 // エラーメッセージを設定してエラーページに遷移
           request.setAttribute("message", "エラーが発生しました。");
           return "/student/subject/subject_error.jsp";

   	}


    }

}
