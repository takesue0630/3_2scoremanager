package main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Subject;
import bean.Teacher;
import dao.SubjectDao;
import tool.Action;

public class SubjectDeleteExecuteAction extends Action {
	public String execute(
		HttpServletRequest request, HttpServletResponse response
	) throws Exception {

		HttpSession session=request.getSession();
		Teacher teacher=(Teacher)session.getAttribute("teacher");

		String subject_name=request.getParameter("subject_name");
		String subject_cd=request.getParameter("subject_cd");

		Subject subject=new Subject();
		subject.setName(subject_name);
		subject.setCd(subject_cd);
		subject.setSchool(teacher.getSchool());

		SubjectDao dao=new SubjectDao();
		 boolean result=dao.delete(subject);

		return "subject_delete_done.jsp";
	}
}