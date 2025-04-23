package main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Student;
import bean.Teacher;
import dao.StudentDao;
import tool.Action;

public class StudentCreateExecuteAction extends Action {
	public String execute(
		HttpServletRequest request, HttpServletResponse response
	) throws Exception {

//		セッションからユーザーデータを取得
		HttpSession session=request.getSession();
		Teacher teacher=(Teacher)session.getAttribute("teacher");

//		入力値のチェック
//		if (request.getParameter("ent_year")==null) {
//			return "student_create.jsp";
//		}

//		学生を取得
		StudentDao dao=new StudentDao();
//		if (dao.get(request.getParameter("no"))!=null) {
//			return "student_create.jsp";
//		}

//		学生を登録
		Student student=new Student();
		student.setNo(request.getParameter("no"));
		student.setName(request.getParameter("name"));
		student.setEntYear(Integer.parseInt(request.getParameter("ent_year")));
		student.setClassNum(request.getParameter("class_num"));
		student.setIsAttend(true);
		student.setSchool(teacher.getSchool());


//		入力された値をDBに保存
		try {
			dao.save(student);
		} catch (Exception e) {
			e.printStackTrace();
		}

//		表示する
		return "student_create_done.jsp";
	}
}
