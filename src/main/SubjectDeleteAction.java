package main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Subject;
import bean.Teacher;
import dao.SubjectDao;
import tool.Action;

public class SubjectDeleteAction extends Action {
	public String execute(
		HttpServletRequest request, HttpServletResponse response
	) throws Exception {

//		科目の詳細データを取得
		HttpSession session=request.getSession();
		Teacher teacher=(Teacher)session.getAttribute("teacher");
//
		String no= request.getParameter("no");

		SubjectDao dao=new SubjectDao();
		Subject subject=dao.get(no,teacher.getSchool());

		Subject p=new Subject();
		p.setCd(no);
		p.setSchool(teacher.getSchool());


		return "subject_delete.jsp";
	}
}
