package main;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.School;
import bean.Student;
import bean.Teacher;
import dao.ClassNumDao;
import dao.StudentDao;
import tool.Action;

public class StudentListAction extends Action {
	public String execute(
		HttpServletRequest request, HttpServletResponse response
	) throws Exception {

//		セッションからユーザーデータを取得
		HttpSession session=request.getSession();
		Teacher teacher=(Teacher)session.getAttribute("teacher");

//		検索項目の取得
//		入学年度
		int ent_year=0;
		if (request.getParameter("f1")!=null) {
			ent_year=Integer.parseInt(request.getParameter("f1"));
		}
//		クラス番号
		String class_num="0";
		if (request.getParameter("f2")!=null) {
			class_num=request.getParameter("f2");
		}
//		在学中
		boolean is_attend;
		if (request.getParameter("f3")!=null) {
			is_attend=true;
		} else {
			is_attend=false;
		}

//		セッションのユーザーデータから、ユーザーが所属している学校の生徒一覧用データを取得
		School school=teacher.getSchool();
		StudentDao dao=new StudentDao();

//		一覧用のリスト作成
		List<Student> list;
		if (class_num.equals("0")) {
			if (ent_year==0) {
				list=dao.filter(school,is_attend);
			} else {
				list=dao.filter(school,ent_year,is_attend);
			}
		} else {
			list=dao.filter(school,ent_year,class_num,is_attend);
		}

//		セッションに生徒のリストとリストサイズを格納
		session.setAttribute("list", list);
		session.setAttribute("size", list.size());

//		カレンダーオブジェクトの生成
		Calendar c = Calendar.getInstance();
//		現在年を格納
	    c.setTime(new Date());
//	    十年前から十年後までのリストを作成
	    List<String> ent_year_set=new ArrayList<>();
	    for (int i = -10; i <= 10; i++) {
	    	ent_year_set.add(""+(c.get(Calendar.YEAR)+i)+"");
	    }
//	    リクエスト属性に格納
	    request.setAttribute("ent_year_set", ent_year_set);

//	    セレクトボックス用のクラスデータを取得
		ClassNumDao cdao=new ClassNumDao();
		List<String> class_num_set=cdao.filter(teacher.getSchool());
//	    リクエスト属性に格納
		request.setAttribute("class_num_set", class_num_set);

		return "student_list.jsp";
	}
}
