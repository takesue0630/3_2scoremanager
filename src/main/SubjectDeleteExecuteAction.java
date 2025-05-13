package main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tool.Action;

public class SubjectDeleteExecuteAction extends Action {
	public String execute(
		HttpServletRequest request, HttpServletResponse response
	) throws Exception {

		String subject_cd = request.getParameter("subject_cd");
		String cd = request.getParameter("cd");
		String name = request.getParameter("name");


		return "subject_delete.jsp";
	}
}
