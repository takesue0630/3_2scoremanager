package main;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Subject;
import bean.Teacher;
import bean.TestListSubject;
import dao.SubjectDao;
import dao.TestListSubjectDao;
import tool.Action;

public class TestListSubjectExecuteAction extends Action {
	public String execute(
		HttpServletRequest request, HttpServletResponse response
	) throws Exception {
//		セッションからユーザーデータを取得
		HttpSession session=request.getSession();
		Teacher teacher=(Teacher)session.getAttribute("teacher");

//		検索項目の取得
//		入学年度
		int ent_year=Integer.parseInt(request.getParameter("f1"));
//		クラス番号
		String class_num=request.getParameter("f2");
//		科目
		SubjectDao subject_dao=new SubjectDao();
		Subject subject=subject_dao.get(request.getParameter("f3"), teacher.getSchool());
		System.out.println(subject.getName());

//		入学年度、クラス、科目のいずれかが未入力の場合
		if (ent_year==0 || class_num=="0" || subject.getCd()=="0") {
//			エラーのセット
			request.setAttribute("error", "入学年度とクラスと科目を選択してください");
//			成績一覧画面へ戻す
			return "test_list.jsp";
		}

//		入学年度、クラス、科目に一致する成績データを取得
		TestListSubjectDao test_list_subject_dao=new TestListSubjectDao();
		List<TestListSubject> list=test_list_subject_dao.filter(ent_year, class_num, subject, teacher.getSchool());
//		リクエスト属性に成績のリストを格納
		request.setAttribute("list", list);

//		入力欄用データ
		request.setAttribute("ent_year", ent_year);
		request.setAttribute("class_num", class_num);
		request.setAttribute("subject", subject);

		return "test_list_subject.jsp";
	}
}