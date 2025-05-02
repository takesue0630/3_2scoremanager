package main;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.School;
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
		int entYear=Integer.parseInt(request.getParameter("f1"));

		//クラスの受け取り
		String classNum=request.getParameter("f2");

		//科目の受け取り
		String subject=request.getParameter("f3");

		//回数の受け取り
		int num=Integer.parseInt(request.getParameter("f4"));


		School school = new School();

		//入力された入学年度,クラス,科目,回数の成績データを取得
		TestDao testdao = new TestDao();
		List<Test> test_filter = testdao.filter(entYear, classNum, subject, num, school);

		//科目名を取得
		List<Subject> subject_filter = subjectdao.filter(school);

		//セッションに格納
		session.setAttribute("test_filter", test_filter);
		session.setAttribute("subject_filter", subject_filter);

		return "test_regist.jsp";
	}
}
