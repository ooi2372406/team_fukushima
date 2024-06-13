package student;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.School;
import bean.Student;
import bean.Subject;
import bean.Teacher;
import dao.StudentDao;
import dao.SubjectDAO;
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



            return "/student/test_regist.jsp"; // ログイン成功時のリダイレクト先
    	}catch(Exception e){
    		 // エラーメッセージを設定してエラーページに遷移
            request.setAttribute("message", "エラーが発生しました。");
            return "/student/subject/test_error.jsp";


    	}

    }


}
/*
private void setTestListStudent(HttpServletRequest req, HttpServletResponse res) throws Exception {
        // リクエストパラメータの取得
        String cd = req.getParameter("cd");
        String subject = req.getParameter("subject");

        // 学生情報の取得
        TestListStudent filterStudent = new TestListStudent();
        filterStudent.setSubjectCd(cd);
        filterStudent.setSubjectName(subject);



        // 結果をリクエスト属性に設定して、JSPに転送
        req.setAttribute("studentList", filterStudent);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/test_list.jsp");
        dispatcher.forward(req, res);
    }

public void setTestListSubject(HttpServletRequest req, HttpServletResponse res) throws Exception {
        // リクエストパラメータの取得
        int entYear = Integer.parseInt(req.getParameter("entYear"));
        String classNum = req.getParameter("classNum");
        String subjectCd = req.getParameter("subjectCd");
        String schoolCd = req.getParameter("schoolCd");

        // 科目情報の取得
        Subject subject = new Subject();
        subject.setCd(subjectCd);
        School school = new School();
        school.setCd(schoolCd);

        List<TestListSubject> subjectList = subjectDao.filter(entYear, classNum, subjectCd, school);

        // 結果をリクエスト属性に設定して、JSPに転送
        req.setAttribute("subjectList", subjectList);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/test_list.jsp");
        dispatcher.forward(req, res);
    }
}*/

