package main;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.School;
import bean.Student;
import bean.Subject;
import bean.Teacher;
import bean.Test;
import dao.ClassNumDao;
import dao.SubjectDao;
import dao.TestDao;
import tool.Action;

//未完成
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


		//検索項目の受け取り(入学年度,クラス,科目,回数)
		//入学年度の受け取り
		int ent_year=Integer.parseInt(request.getParameter("f1"));

		//クラスの受け取り
		String class_num=request.getParameter("f2");

		//科目の受け取り
		String subject=request.getParameter("f3");

		//回数の受け取り
		int no=Integer.parseInt(request.getParameter("f4"));


		TestDao testdao = new TestDao();
		Student student = new Student();
		School school = new School();

		Test test_list = testdao.get(student,subject,school,no);
		return "test_regist.jsp";
	}
}
