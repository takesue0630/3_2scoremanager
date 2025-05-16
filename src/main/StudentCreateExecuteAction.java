package main;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Student;
import bean.Teacher;
import dao.ClassNumDao;
import dao.StudentDao;
import tool.Action;

public class StudentCreateExecuteAction extends Action {
	public String execute(
		HttpServletRequest request, HttpServletResponse response
	) throws Exception {
		try{
//			セッションからユーザーデータを取得
			HttpSession session=request.getSession();
			Teacher teacher=(Teacher)session.getAttribute("teacher");

//			入学年度用リストの作成
			Calendar c = Calendar.getInstance();
		    c.setTime(new Date());
		    List<String> ent_year=new ArrayList<>();
		    for (int i = -10; i <= 10; i++) {
		    	ent_year.add(""+(c.get(Calendar.YEAR)+i)+"");
		    }
		    ClassNumDao cdao=new ClassNumDao();
			List<String> class_num=cdao.filter(teacher.getSchool());

//			入学年度が未入力
			if (request.getParameter("ent_year").equals("0")) {
//				入学年度のセット
			    request.setAttribute("ent_year", ent_year);
//			    学生番号のセット
				request.setAttribute("no",request.getParameter("no"));
//				氏名のセット
				request.setAttribute("name",request.getParameter("name"));
//			    セレクトボックス用のクラスデータを取得
				request.setAttribute("class_num", class_num);
//				エラーのセット
				request.setAttribute("error1", "入学年度を選択してください");
//				学生登録画面へ戻す
				return "student_create.jsp";
			}

//			学生を取得
			StudentDao dao=new StudentDao();
//			すでに登録済み
			if (dao.get(request.getParameter("no")).getNo()!=null) {
//				入学年度のセット
			    request.setAttribute("ent_year", ent_year);
//			    学生番号のセット
				request.setAttribute("no",request.getParameter("no"));
//				氏名のセット
				request.setAttribute("name",request.getParameter("name"));
//			    セレクトボックス用のクラスデータを取得
				request.setAttribute("class_num", class_num);
//				エラーのセット
				request.setAttribute("error2", "学生番号が重複しています");
//				学生登録画面へ戻す
				return "student_create.jsp";
			}

//			学生を登録
			Student student=new Student();
			student.setNo(request.getParameter("no"));
			student.setName(request.getParameter("name"));
			student.setEntYear(Integer.parseInt(request.getParameter("ent_year")));
			student.setClassNum(request.getParameter("class_num"));
			student.setIsAttend(true);
			student.setSchool(teacher.getSchool());

//			入力された値をDBに保存
			dao.save(student);

//			表示する
			return "student_create_done.jsp";
		} catch(Exception e) {
			return "../error.jsp";
		}
	}
}
