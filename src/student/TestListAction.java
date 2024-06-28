package student;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.School;
import bean.Student;
import bean.Subject;
import bean.Teacher;
import bean.TestListStudent;
import bean.TestListSubject;
import dao.ClassNumDao;
import dao.StudentDao;
import dao.SubjectDAO;
import dao.TestListStudentDAO;
import dao.TestListSubjectDAO;
import tool.Action;
import util.Util;

public class TestListAction extends Action {



    public String execute(HttpServletRequest req, HttpServletResponse res) throws IOException {
        try {
            // 前ページから送られてきたデータを受け取る
            String selectclass = req.getParameter("");
            String selectsubject = req.getParameter("");
            //String selectstsudentNo = req.getParameter("");

            // selected判定のためにセット
            req.setAttribute("selectclass", selectclass);
            req.setAttribute("selectsubject", selectsubject);
            //req.setAttribute("selectstudent", selectstsudentNo);

            HttpSession session = req.getSession();
            Teacher teacher = Util.getUser(req);
            School school = teacher.getSchool();

            SubjectDAO subject_dao = new SubjectDAO();
            List<Subject> subject = subject_dao.filter(school);

            ClassNumDao class_dao = new ClassNumDao();
            List<String> classnum = class_dao.filter(school);
            session.setAttribute("subject", subject);
            session.setAttribute("classnum", classnum);

            // リクエストパラメータ 科目コードと学生コードの値を取得する

            String cd = req.getParameter("f");

            if (cd != null && !cd.isEmpty() && cd.equals("sj")) {
                // 科目識別コード"sj"が送られてきたときはsetTestListSubject を実行
                setTestListSubject(req, res);

            } else if (cd != null && !cd.isEmpty() && cd.equals("st")) {
                // 科目識別コード"sj"が送られてきたときはsetTestListSubject を実行
                setTestListStudent(req, res);

            } else {
                // 上記条件を満たさない場合、通常の処理を実行
                boolean isAttend = true;  // 在籍中の学生だけを取得
                StudentDao studao = new StudentDao();
                List<Student> studentList = studao.filter(school, isAttend);

                // 重複を排除した学生の入学年度リストを取得
                List<Integer> uniqueEnrollYears = studentList.stream()
                                                             .map(Student::getEntYear)
                                                             .distinct()
                                                             .collect(Collectors.toList());

                session.setAttribute("student", uniqueEnrollYears);
            }

        } catch (Exception e) {
            e.printStackTrace();
            res.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error processing request");
        }
        //req.setAttribute("errorMessage",null);
        System.out.println("ここまではきている3");
        return "test_list.jsp";
    }











private void setTestListStudent(HttpServletRequest req, HttpServletResponse res) throws Exception {
        // ユーザー情報を取得

        Teacher teacher = Util.getUser(req);
        School school = teacher.getSchool();

        //getParameterメソッドでデータを受け取る

        String studentCd = req.getParameter("f4");
        Student student = new Student();
        StudentDao studao = new StudentDao();
        student = studao.get(studentCd);


        TestListStudentDAO dao = new TestListStudentDAO();
        List<TestListStudent> studentList = dao.filter(student);

        if (studentList.size() != 0) {
        	System.out.println(studentList);
        	System.out.println("ここまではきている2");
        	req.setAttribute("studentList", studentList);
            req.setAttribute("studentname", student);

		}else{
			req.setAttribute("studentname",student);
			req.setAttribute("studentmessage","成績情報が存在しませんでした");
		}

        if (student == null){
        	req.setAttribute("studentempty", "学生情報が存在しませんでした");
        }

        // 結果をリクエスト属性に設定して、JSPに転送
      //学籍番号保持したまま表示させるための情報入力
        req.setAttribute("f4", studentCd);
    }



private void setTestListSubject(HttpServletRequest req, HttpServletResponse res) throws Exception {

         HttpSession session = req.getSession();

         // ユーザー情報を取得

         Teacher teacher = Util.getUser(req);
         School school = teacher.getSchool();

            //getParameterメソッドでデータを受け取る
        String entYear_str=req.getParameter("f1");
        String classNum=req.getParameter("f2");
        String subjectname=req.getParameter("f3");
      //学籍番号保持したまま表示させるための情報入力
        String studentCd=req.getParameter("f4");
        if("--------".equals(entYear_str) || classNum==null || subjectname==null){
        	// 例: エラーメッセージをセットしてページにリダイレクト
        	req.setAttribute("errorMessage", "入学年度とクラスと科目を選択してください");
        }
        else{
            int entYear= Integer.parseInt(req.getParameter("f1"));

            Subject subject = new Subject();
            subject.setName(subjectname);

            TestListSubjectDAO testdao = new TestListSubjectDAO();
            System.out.println("ここまではきている1");
            List<TestListSubject> testList = testdao.filter(entYear, classNum, subject, school);
            System.out.println(testList.size());

            if (testList.size() == 0) {
            	System.out.println("ここまではきている2");
            	req.setAttribute("errorMessege2", "学生情報が存在しませんでした");

    		}

            // 結果をリクエスト属性に設定して、JSPに転送
            req.setAttribute("testList", testList);
            req.setAttribute("subjectname", subjectname);
          //学籍番号保持したまま表示させるための情報入力
            req.setAttribute("f4", studentCd);

        }


    }


}