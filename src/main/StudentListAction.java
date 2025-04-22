package main;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.School;
import bean.Student;
import bean.Teacher;
import dao.StudentDao;
import tool.Action;

public class StudentListAction extends Action {
	public String execute(
		HttpServletRequest request, HttpServletResponse response
	) throws Exception {

//		セッションからユーザーデータを取得
		HttpSession session=request.getSession();
		Teacher teacher=(Teacher)session.getAttribute("teacher");

//		セッションのユーザーデータから、ユーザーが所属している学校の生徒一覧用データを取得
		School school=teacher.getSchool();
		StudentDao dao=new StudentDao();


//		String entYearString=request.getParameter("entYear");
//
//		if (entYearString!=null) {
//			int entYear=Integer.parseInt(entYearString);
//			if (request.getParameter("classNum")!=null) {
//				String classNum=request.getParameter("classNum");
//				list=dao.filter(school,entYear,classNum,true);
//			} else {
//				list=dao.filter(school,entYear,true);
//			}
//		} else {
//			list=dao.filter(school,true);
//		}

		List<Student> list=dao.filter(school,true);

//		boolean isAttend=Boolean.parseBoolean(request.getParameter("isAttend"));

		session.setAttribute("list", list);

		session.setAttribute("size", list.size());

		return "student_list.jsp";
	}
}
