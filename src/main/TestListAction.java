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
import dao.ClassNumDao;
import dao.SubjectDao;
import tool.Action;

public class TestListAction extends Action {
	public String execute(
		HttpServletRequest request, HttpServletResponse response
	) throws Exception {
		try {
//			セッションからユーザーデータを取得
			HttpSession session=request.getSession();
			Teacher teacher=(Teacher)session.getAttribute("teacher");

//			ユーザーが所属している学校のクラスデータを取得
			ClassNumDao class_num_dao=new ClassNumDao();
			List<String> class_num_set=class_num_dao.filter(teacher.getSchool());

//			ユーザーが所属している学校の科目データを取得
			SubjectDao subject_dao=new SubjectDao();
			List<Subject> subject_set=subject_dao.filter(teacher.getSchool());

//			カレンダーオブジェクトの生成
			Calendar c = Calendar.getInstance();
//			現在年を格納
		    c.setTime(new Date());
//		    十年前から十年後までのリストを作成
		    List<String> ent_year_set=new ArrayList<>();
		    for (int i = -10; i <= 10; i++) {
		    	ent_year_set.add(""+(c.get(Calendar.YEAR)+i)+"");
		    }

//		    収集したデータをリクエスト属性に格納
		    request.setAttribute("class_num_set", class_num_set);
		    request.setAttribute("subject_set", subject_set);
		    request.setAttribute("ent_year_set", ent_year_set);

			return "test_list.jsp";

		} catch(Exception e) {
			return "../error.jsp";
		}
	}
}
