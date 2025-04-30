package main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Teacher;
import dao.ClassNumDao;
import dao.SubjectDao;
import tool.Action;

public class TestRegistAction extends Action{
	public String execute(
			HttpServletRequest request, HttpServletResponse response
		) throws Exception {
		HttpSession session=request.getSession();
		Teacher teacher=(Teacher)session.getAttribute("teacher");

		//学校のクラスデータを取得
		ClassNumDao classdao = new ClassNumDao();
		classdao.filter(teacher.getSchool());

		//学校の科目データを取得
		SubjectDao subjectdao = new SubjectDao();
		subjectdao.filter(teacher.getSchool());

		return "test_regist.jsp";
	}
}
