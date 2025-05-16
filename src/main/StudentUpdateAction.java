package main;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Student;
import bean.Teacher;
import dao.ClassNumDao;
import dao.StudentDao;
import tool.Action;

public class StudentUpdateAction extends Action {
	public String execute(
		HttpServletRequest request, HttpServletResponse response
	) throws Exception {
		try {
//			セッションの取得
			HttpSession session=request.getSession();

//			受け取った学生番号から変更する学生の情報を持ってくる
			StudentDao s=new StudentDao();
			Student student=s.get(request.getParameter("no"));

			Teacher teacher=(Teacher)session.getAttribute("teacher");
			ClassNumDao dao=new ClassNumDao();
			List<String> class_num=dao.filter(teacher.getSchool());

			request.setAttribute("ent_year", student.getEntYear());
			request.setAttribute("no", student.getNo());
			request.setAttribute("name", student.getName());
			request.setAttribute("is_attend", student.getIsAttend());
			request.setAttribute("class_num", class_num);

			return "student_update.jsp";

		} catch(Exception e) {
			return "../error.jsp";
		}
	}
}
