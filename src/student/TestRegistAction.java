package student;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.School;
import bean.Student;
import bean.Subject;
import bean.Teacher;
import bean.Test;
import dao.StudentDao;
import dao.SubjectDAO;
import dao.TestDao;
import tool.Action;
import util.Util;

public class TestRegistAction extends Action {


    public String execute(
        HttpServletRequest request, HttpServletResponse response) throws Exception {
    	try{
    		HttpSession session = request.getSession();
    		// 意図的に例外を発生させる処理（普段はつかわない）
    		 //if (true) {
    	     //       throw new RuntimeException("テスト用の予期せぬエラー");
    	     // }
    		// getUserメソッドを呼び出してユーザー情報を取得
    		Teacher teacher = Util.getUser(request);
    		// TeacherオブジェクトからSchoolオブジェクトを取得
    		School school = teacher.getSchool();


    		boolean isAttend = true;//在学フラグ

    	;	SubjectDAO sdao = new SubjectDAO();
    		List<Subject> subjectList = sdao.filter(school);
    		StudentDao studao = new StudentDao();
    		List<Student> studentList = studao.filter(school, isAttend);
    		session.setAttribute("student", studentList);
    		session.setAttribute("subject", subjectList);


    		   String paramF1 = request.getParameter("f1");
               String paramF2 = request.getParameter("f2");
               String paramF3 = request.getParameter("f3");
               String paramF4 = request.getParameter("f4");

               if (paramF1 != null && !paramF1.trim().isEmpty() &&
                   paramF2 != null && !paramF2.trim().isEmpty() &&
                   paramF3 != null && !paramF3.trim().isEmpty() &&
                   paramF4 != null && !paramF4.trim().isEmpty()) {

                   int entYear = Integer.parseInt(paramF1);
                   String classNum = paramF2;
                   String name = paramF3;
                   int num = Integer.parseInt(paramF4);

                   session.setAttribute("year", entYear);


                   setTestListStudent(request, response);
               } else {

               }


            return "/student/test_regist.jsp"; // ログイン成功時のリダイレクト先
    	}catch(Exception e){
    		 // エラーメッセージを設定してエラーページに遷移
            request.setAttribute("message", "エラーが発生しました。");
            return "/student/subject/subject_error.jsp";


    	}

    }




private void setTestListStudent(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // リクエストパラメータの取得
		Teacher teacher = Util.getUser(request);
	// TeacherオブジェクトからSchoolオブジェクトを取得
		School school = teacher.getSchool();
        int entYear = Integer.parseInt(request.getParameter("f1"));
        String classNum = request.getParameter("f2");
        String name = request.getParameter("f3");
        int num = Integer.parseInt(request.getParameter("f4"));
        Test test = new Test();
        test.setNo(num);
        Subject subject = new Subject();
        subject.setName(name);
        Student student = new Student();



        // 学生情報の取得
        TestDao dao = new TestDao();
        List<Test> list = dao.filter(test, entYear, classNum, subject, num, student);


     // テストリストをリクエストに設定
        request.setAttribute("testList", list);

/*
        // 結果をリクエスト属性に設定して、JSPに転送
        req.setAttribute("studentList", filterStudent);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/test_list.jsp");
        dispatcher.forward(req, res);
    }
*/
}
}

