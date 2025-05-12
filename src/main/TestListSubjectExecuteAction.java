package main;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Subject;
import bean.Teacher;
import bean.TestListSubject;
import dao.ClassNumDao;
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

//		入学年度、クラス、科目のいずれかが未入力の場合
		if (ent_year==0 || class_num.equals("0") || subject.getCd()==null) {
//			エラーのセット
			request.setAttribute("error", "入学年度とクラスと科目を選択してください");
//			成績一覧へ戻す
			request.getRequestDispatcher("TestList.action").forward(request, response);
		}

//		入学年度、クラス、科目に一致する成績データを取得
		TestListSubjectDao test_list_subject_dao=new TestListSubjectDao();
		List<TestListSubject> list=test_list_subject_dao.filter(ent_year, class_num, subject, teacher.getSchool());
//		リクエスト属性に成績のリストを格納
		request.setAttribute("list", list);

//		ユーザーが所属している学校のクラスデータを取得
		ClassNumDao class_num_dao=new ClassNumDao();
		List<String> class_num_set=class_num_dao.filter(teacher.getSchool());

//		ユーザーが所属している学校の科目データを取得
		List<Subject> subject_set=subject_dao.filter(teacher.getSchool());

//		カレンダーオブジェクトの生成
		Calendar c = Calendar.getInstance();
//		現在年を格納
	    c.setTime(new Date());
//	    十年前から十年後までのリストを作成
	    List<String> ent_year_set=new ArrayList<>();
	    for (int i = -10; i <= 10; i++) {
	    	ent_year_set.add(""+(c.get(Calendar.YEAR)+i)+"");
	    }

//	    収集したデータをリクエスト属性に格納
	    request.setAttribute("class_num_set", class_num_set);
	    request.setAttribute("subject_set", subject_set);
	    request.setAttribute("ent_year_set", ent_year_set);

//		入力欄用データ
		request.setAttribute("ent_year", ent_year);
		request.setAttribute("class_num", class_num);
		request.setAttribute("subject_name", subject.getName());

		return "test_list_subject.jsp";
	}
}