package main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Student;
import bean.Teacher;
import dao.StudentDao;
import tool.Action;

public class StudentUpdateExecuteAction extends Action {
	public String execute(
		HttpServletRequest request, HttpServletResponse response
	) throws Exception {

		HttpSession session=request.getSession();

		Student student=new Student();
		student.setNo(request.getParameter("no"));
		student.setName(request.getParameter("name"));
		student.setEntYear(Integer.parseInt(request.getParameter("ent_year")));
		student.setClassNum(request.getParameter("class_num"));
		if (request.getParameter("is_attend")==null) {
			student.setIsAttend(false);
		} else {
			student.setIsAttend(true);
		}

		System.out.println(request.getParameter("is_attend"));

		Teacher teacher=(Teacher)session.getAttribute("teacher");
		student.setSchool(teacher.getSchool());

		try {
			StudentDao dao=new StudentDao();
			dao.save(student);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "student_update_done.jsp";
	}
}
