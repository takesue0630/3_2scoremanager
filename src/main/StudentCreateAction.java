package main;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Teacher;
import dao.ClassNumDao;
import tool.Action;

public class StudentCreateAction extends Action {
	public String execute(
		HttpServletRequest request, HttpServletResponse response
	) throws Exception {

//		セッションからユーザーデータを取得
		HttpSession session=request.getSession();
		Teacher teacher=(Teacher)session.getAttribute("teacher");

//	    セレクトボックス用のクラスデータを取得
		ClassNumDao dao=new ClassNumDao();
		List<String> class_num=dao.filter(teacher.getSchool());
//	    リクエスト属性に格納
		request.setAttribute("class_num", class_num);

//		カレンダーオブジェクトの生成
		Calendar c = Calendar.getInstance();
//		現在年を格納
	    c.setTime(new Date());
//	    十年前から十年後までのリストを作成
	    List<String> ent_year=new ArrayList<>();
	    for (int i = -10; i <= 10; i++) {
	    	ent_year.add(""+(c.get(Calendar.YEAR)+i)+"");
	    }
//	    リクエスト属性に格納
	    request.setAttribute("ent_year", ent_year);

		return "student_create.jsp";
	}
}
