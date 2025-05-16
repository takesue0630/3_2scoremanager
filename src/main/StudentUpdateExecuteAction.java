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
		try {
//			セッションからユーザーデータを取得
			HttpSession session=request.getSession();

//			生徒データを取得
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

//			ユーザーデータから学校情報を格納
			Teacher teacher=(Teacher)session.getAttribute("teacher");
			student.setSchool(teacher.getSchool());

//			保存する
			StudentDao dao=new StudentDao();
			dao.save(student);

			return "student_update_done.jsp";

		} catch(Exception e) {
			return "../error.jsp";
		}
	}
}
