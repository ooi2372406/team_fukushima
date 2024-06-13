package util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import bean.ClassNum;
import bean.School;
import bean.Subject;
import bean.Teacher;


public class Util {
    public static Teacher getUser(HttpServletRequest request) {
        HttpSession session = request.getSession();
        return (Teacher) session.getAttribute("user");
    }

    // 他のメソッドの例:
    public static void setClassNumSet(HttpServletRequest request) throws Exception {
        HttpSession session = request.getSession();
        School school = ((Teacher) session.getAttribute("user")).getSchool();
        ClassNum classnum = new ClassNum();

        session.setAttribute("classNum", classnum);
    }

    public static void setEntYearSet(HttpServletRequest request) {
        HttpSession session = request.getSession();
        // 入学年を同様に取得する
        // session.setAttribute("entYears", fetchedEntYears);
    }

    public static void setSubjects(HttpServletRequest request) throws Exception {
        HttpSession session = request.getSession();
        School school = ((Teacher) session.getAttribute("user")).getSchool();
     // Subject オブジェクトのインスタンス化
        Subject subject = new Subject();
        subject.setSchool(school);

        // セッションに Subject オブジェクトをセット
        session.setAttribute("subject", subject);
    }

    public static void setNumSet(HttpServletRequest request) {
        HttpSession session = request.getSession();
        // 数値を同様に取得する
        // session.setAttribute("nums", fetchedNums);
    }
}
