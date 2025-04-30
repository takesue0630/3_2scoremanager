package main;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.School;
import bean.Subject;
import bean.Teacher;
import dao.SubjectDao;
import tool.Action;

public class SubjectListAction extends Action {
	public String execute(
		HttpServletRequest request, HttpServletResponse response
	) throws Exception {

//		セッションからユーザーデータを取得
		HttpSession session=request.getSession();
		Teacher teacher=(Teacher)session.getAttribute("teacher");

//		セッションのユーザーデータから、ユーザーが所属している学校の科目一覧用データを取得
		School school=teacher.getSchool();
		SubjectDao dao=new SubjectDao();

//		一覧用のリスト作成
		List<Subject> list=dao.filter(school);

//		セッションに科目のリストを格納
		session.setAttribute("list", list);

		return "subject_list.jsp";
	}
}
