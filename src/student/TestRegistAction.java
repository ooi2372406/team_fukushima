package student;

import java.util.List;
import java.util.stream.Collectors;

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

    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        try {
            // セッションが存在しない場合はログインページにリダイレクト
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
            // TeacherオブジェクトからSchoolオブジェクトを取得
            School school = teacher.getSchool();

            boolean isAttend = true; // 在学フラグ

            SubjectDAO sdao = new SubjectDAO();
            List<Subject> subjectList = sdao.filter(school);
            StudentDao studao = new StudentDao();
            List<Student> studentList = studao.filter(school, isAttend);
            List<Integer> uniqueEnrollYears = studentList.stream()
                .map(Student::getEntYear)
                .distinct()
                .collect(Collectors.toList());

            List<String> uniqueEnrollClassNum = studentList.stream()
                .map(Student::getClassNum)
                .distinct()
                .collect(Collectors.toList());
            session.setAttribute("studentclassnum", uniqueEnrollClassNum);
            session.setAttribute("studentYear", uniqueEnrollYears);
            session.setAttribute("student", studentList);
            session.setAttribute("subject", subjectList);

            // 前ページから送られてきたデータを受け取る
            String selectclass = request.getParameter("");
            String selectsubject = request.getParameter("");
            // String selectstsudentNo = req.getParameter("");

            // selected判定のためにセット
            request.setAttribute("selectclass", selectclass);
            request.setAttribute("selectsubject", selectsubject);
            // req.setAttribute("selectstudent", selectstsudentNo);

            String paramF1 = request.getParameter("f1");
            String paramF2 = request.getParameter("f2");
            String paramF3 = request.getParameter("f3");
            String paramF4 = request.getParameter("f4");

            // 最初の遷移時またはJSPから戻った際の両方でパラメータをチェック
            if ((paramF1 == null || paramF1.trim().isEmpty()) ||
                (paramF2 == null || paramF2.trim().isEmpty()) ||
                (paramF3 == null || paramF3.trim().isEmpty()) ||
                (paramF4 == null || paramF4.trim().isEmpty())) {

                if (paramF1 != null || paramF2 != null || paramF3 != null || paramF4 != null) {
                	request.setAttribute("setYear", paramF1);
                	request.setAttribute("setClassNum", paramF2);
                    request.setAttribute("yearerrormessage", "入学年度とクラスと科目と回数を選択してください");
                }
                return "/student/test_regist.jsp";
            }

            int entYear = Integer.parseInt(paramF1);
            String classNum = paramF2;
            String name = paramF3;
            int num = Integer.parseInt(paramF4);

            session.setAttribute("year", entYear);

            setTestListStudent(request, response);

            return "/student/test_regist.jsp"; // ログイン成功時のリダイレクト先
        } catch (Exception e) {
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

        if (list.size() == 0) {
            request.setAttribute("emptymessage", "その試験はまだ受験した人がおりません");
        }

        // テストリストをリクエストに設定
        request.setAttribute("testList", list);
        request.setAttribute("setYear", entYear);
        request.setAttribute("setClassNum", classNum);
    }
}
