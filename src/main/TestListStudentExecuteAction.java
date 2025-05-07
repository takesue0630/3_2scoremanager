package main;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Student;
import bean.Teacher;
import bean.TestListStudent;
import dao.StudentDao;
import dao.TestListStudentDao;
import tool.Action;

public class TestListStudentExecuteAction extends Action {
	public String execute(
		HttpServletRequest request, HttpServletResponse response
	) throws Exception {
		//セッションからユーザーデータを取得
		HttpSession session=request.getSession();
		Teacher teacher=(Teacher)session.getAttribute("teacher");

//		学生番号の取得
		String no=request.getParameter("f1");

//		学生番号に一致する学生の取得
		StudentDao student_dao=new StudentDao();
		Student student=student_dao.get(no);
		request.setAttribute("student", student);

//		学生の成績データを取得
		TestListStudentDao test_list_student_dao=new TestListStudentDao();
		List<TestListStudent> list=test_list_student_dao.filter(student);

//		リクエスト属性に成績のリストを格納
		request.setAttribute("list", list);

//		入力欄用データ
		request.setAttribute("no", no);

		return "test_list_subject.jsp";
	}
}