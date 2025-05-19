package main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Subject;
import bean.Teacher;
import dao.SubjectDao;
import tool.Action;

public class SubjectUpdateAction extends Action {
	public String execute(
		HttpServletRequest request, HttpServletResponse response
	) throws Exception {
		try {
//			セッションの取得
			HttpSession session=request.getSession();
			Teacher teacher=(Teacher)session.getAttribute("teacher");

//			受け取った科目番号から変更する科目の情報を持ってくる
			SubjectDao s=new SubjectDao();
			Subject subject=s.get(request.getParameter("no"),teacher.getSchool());

//			リクエスト属性に格納
			request.setAttribute("cd",subject.getCd());
			request.setAttribute("name",subject.getName());

			return "subject_update.jsp";

		} catch(Exception e) {
			return "../error.jsp";
		}
	}
}
