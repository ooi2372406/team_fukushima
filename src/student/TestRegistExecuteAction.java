package student;

import java.util.ArrayList;
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

public class TestRegistExecuteAction extends Action {

    public String execute(
        HttpServletRequest request, HttpServletResponse response) throws Exception {
        try {
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
            School school = teacher.getSchool();

            // リクエストからパラメータを取得
            String no = request.getParameter("gakuban");


            // 学生番号を使用して point フィールドの値を取得
            String pointStr = request.getParameter("point");

            System.out.println(pointStr);
            int point = 0;
            if (pointStr != null && !pointStr.trim().isEmpty()) {
                try {
                    point = Integer.parseInt(pointStr);
                } catch (NumberFormatException e) {
                    // エラーメッセージを設定して、フォームに戻るなどの処理を行う
                    return "error";
                }
            }

            String classnum = request.getParameter("classnum");
            String cd = request.getParameter("kamokucd");


            int num = Integer.parseInt(request.getParameter("num"));



            // DAOを使って学生、科目、テスト情報を取得
            StudentDao studao = new StudentDao();
            Student student = studao.get(no);
            SubjectDAO subdao = new SubjectDAO();
            Subject subject = subdao.get(cd, school);
            TestDao dao = new TestDao();



            // テスト情報を取得して更新
            Test test = dao.get(student, subject, school, num);
            System.out.println(test);

            System.out.println("学番 : " + no );
            System.out.println("科目コード" + cd);
            System.out.println("学校コード" + school.getCd());
            System.out.println("回数 :" + num);
            System.out.println("点数 : " + point);
            System.out.println("クラス番号 : " + classnum);


            if (test == null){
            	boolean createsuccess = dao.save(no , cd , school , num , point , classnum);

            	if (createsuccess){
            		request.setAttribute("message", "登録しました");
                    return "/student/test_regist_done.jsp";
            	}
            }

            if (test != null) {
                test.setPoint(point);
                test.setStudent(student);
                test.setSubject(subject);

                List<Test> testList = new ArrayList<>();
                testList.add(test);

                // テスト情報を保存
                boolean success = dao.save(testList);

                if (success) {
                    request.setAttribute("message", "登録しました");
                    return "/student/test_regist_done.jsp";
                } else {
                    request.setAttribute("message", "登録に失敗しました");
                    return "/student/test_regist_error.jsp";
                }
            } else {
                request.setAttribute("message", "テスト情報が見つかりませんでした");
                return "/student/test_not_found.jsp";
            }
        } catch (Exception e) {
            e.printStackTrace();
            // エラーメッセージを設定してエラーページに遷移
            request.setAttribute("message", "エラーが発生しました: " + e.getMessage());
            return "/student/subject/subject_error.jsp";
        }
    }
}
