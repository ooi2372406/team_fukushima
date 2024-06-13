package student;

<<<<<<< HEAD
import java.io.IOException;
=======
>>>>>>> 01d94b0e1d538a44f6f1ae8728a03dd685aa8104
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

<<<<<<< HEAD
import bean.ClassNum;
import bean.School;
import bean.Student;
import bean.Subject;
import bean.Teacher;
import dao.StudentDao;
import dao.SubjectDAO;
=======
import bean.School;
import bean.Teacher;
import bean.Test;
import dao.TestDao;
>>>>>>> 01d94b0e1d538a44f6f1ae8728a03dd685aa8104
import tool.Action;
import util.Util;

public class TestRegistAction extends Action {
<<<<<<< HEAD



    public String execute(HttpServletRequest req, HttpServletResponse res) throws IOException {
=======
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


>>>>>>> 01d94b0e1d538a44f6f1ae8728a03dd685aa8104

    		TestDao dao = new TestDao();
    		List<Test> tests = dao.filter(school);
    		for (Test test : tests) {
                System.out.println("Subject CD: " + test.getClass() + ", Name: " + test.getName());
            }
    		// HttpSessionオブジェクトを取得し、そこにユーザー情報を設定する
    		HttpSession session = request.getSession();
    		// Teacherオブジェクトをセッションに保存する
    		session.setAttribute("subject", tests);

<<<<<<< HEAD
        try {
        	HttpSession session = req.getSession();
        	boolean isAttend = true;//在学フラグ
        	Teacher teacher=Util.getUser(req);
        	School school=teacher.getSchool();
        	//Subject subject = (Subject) session.getAttribute("subject");
        	ClassNum classnum = (ClassNum) session.getAttribute("classNum");





=======
            return "/student/test_regist.jsp"; // ログイン成功時のリダイレクト先
    	}catch(Exception e){
    		 // エラーメッセージを設定してエラーページに遷移
            request.setAttribute("message", "エラーが発生しました。");
            return "/student/subject/test_error.jsp";

    	}
        }

>>>>>>> 01d94b0e1d538a44f6f1ae8728a03dd685aa8104




        	SubjectDAO subject_dao = new SubjectDAO();
        	List<Subject>subjectList=subject_dao.filter(school);
        	System.out.println(subjectList);
        	StudentDao student_dao = new StudentDao();

        	List<Student> studentList = student_dao.filter(school , isAttend);
        	System.out.println(studentList);
        	session.setAttribute("subject", subjectList);
        	session.setAttribute("student", studentList);

        } catch (Exception e) {
            e.printStackTrace();
            res.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error processing request");
        }
        return "test_regist.jsp";

    }
<<<<<<< HEAD
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
=======

>>>>>>> 01d94b0e1d538a44f6f1ae8728a03dd685aa8104
