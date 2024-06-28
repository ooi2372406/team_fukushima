package student;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.School;
import bean.StudentAnalysis;
import bean.Teacher;
import dao.StudentAnalysisDao;
import tool.Action;
import util.Util;

public class StudentAnalysisAction extends Action {

    public String execute(HttpServletRequest req, HttpServletResponse res) throws IOException {
        try {
            HttpSession session = req.getSession();
            Teacher teacher = Util.getUser(req);
            School school = teacher.getSchool();
            String no = req.getParameter("f2");
            

            // 科目コードをリストに格納
            List<String> subjects = new ArrayList<>();
            subjects.add("A04"); // 社会
            subjects.add("H02"); // 情報科学２
            subjects.add("H12"); // 理科
            subjects.add("A02"); // 国語
            subjects.add("H14"); // 情報IT

            StudentAnalysisDao dao = new StudentAnalysisDao();

            // 学生の分析データを取得
            List<StudentAnalysis> student = dao.filter(no);

            // 科目の分析データを取得
            List<StudentAnalysis> subject = dao.filter();

            // 科目ごとの順位と受験者数をLinkedHashMapに格納（順序を保持するため）
            Map<String, String> subjectRanks1 = new LinkedHashMap<>();
            Map<String, String> subjectRanks2 = new LinkedHashMap<>();

            for (String subjectCode : subjects) {
                int rank1 = dao.getRank1(no, subjectCode);
                int rank2 = dao.getRank2(no, subjectCode);
                int count1 = dao.getStudentCount(subjectCode, 1); // 一回目の受験者数を取得
                int count2 = dao.getStudentCount(subjectCode, 2); // 二回目の受検者数を取得
                subjectRanks1.put(subjectCode, rank1 + "位 / " + count1 + "人中");
                subjectRanks2.put(subjectCode, rank2 + "位 / " + count2 + "人中");
            }

            // セッションにデータを保存
            session.setAttribute("students", student);
            session.setAttribute("subject", subject);
            session.setAttribute("subjectRanks1", subjectRanks1);
            session.setAttribute("subjectRanks2", subjectRanks2);

        } catch (Exception e) {
            e.printStackTrace();
            res.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error processing request");
        }
        return "student_analysis.jsp";
    }
}
