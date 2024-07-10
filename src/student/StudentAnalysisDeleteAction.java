package student;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tool.Action;

public class StudentAnalysisDeleteAction extends Action{
	public String execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

		return "student_list.jsp";
	}
}
