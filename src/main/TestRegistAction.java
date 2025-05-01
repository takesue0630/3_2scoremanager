package main;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Subject;
import bean.Teacher;
import dao.ClassNumDao;
import dao.SubjectDao;
import dao.TestDao;
import tool.Action;

public class TestRegistAction extends Action{
	public String execute(
			HttpServletRequest request, HttpServletResponse response
		) throws Exception {
		HttpSession session=request.getSession();
		Teacher teacher=(Teacher)session.getAttribute("teacher");

		//学校のクラスデータを取得
		ClassNumDao classdao = new ClassNumDao();
		List<String> class_num_list = classdao.filter(teacher.getSchool());

		//学校の科目データを取得
		SubjectDao subjectdao = new SubjectDao();
		List<Subject> subject_list = subjectdao.filter(teacher.getSchool());

		request.getRequestDispatcher("test_regist.jsp").include(request,response);

		TestDao testdao = new TestDao();
		List<Test> test_list = testdao.get(getstudent(), getsubject(), school, no);
		return "test_regist.jsp";
	}
}
