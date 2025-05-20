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
		try {
			// セッションからユーザーデータを取得
			HttpSession session = request.getSession();
			Teacher teacher = (Teacher) session.getAttribute("teacher");

			// 検索項目の取得
			int ent_year = 0;
			if (request.getParameter("f1") != null) {
				ent_year = Integer.parseInt(request.getParameter("f1"));
			}

			String class_num = "0";
			if (request.getParameter("f2") != null) {
				class_num = request.getParameter("f2");
			}

			boolean is_attend = request.getParameter("f3") != null;

			// 入力値を再表示のためにリクエスト属性に格納
			request.setAttribute("f1", ent_year);
			request.setAttribute("f2", class_num);
			request.setAttribute("f3", is_attend);

			// ★ バリデーション：クラス指定時に入学年度が未指定ならエラー
			if (ent_year == 0 && !class_num.equals("0")) {
				request.setAttribute("errorMsg", "クラスを指定する場合は入学年度も指定してください");

				// 年度リスト作成
				Calendar c = Calendar.getInstance();
				c.setTime(new Date());
				List<String> ent_year_set = new ArrayList<>();
				for (int i = -10; i <= 10; i++) {
					ent_year_set.add("" + (c.get(Calendar.YEAR) + i));
				}
				request.setAttribute("ent_year_set", ent_year_set);

				// クラス番号リスト取得
				ClassNumDao cdao = new ClassNumDao();
				List<String> class_num_set = cdao.filter(teacher.getSchool());
				request.setAttribute("class_num_set", class_num_set);

				// 空リスト返却
				request.setAttribute("list", new ArrayList<Student>());
				return "student_list.jsp";
			}

			// 学校情報取得
			School school = teacher.getSchool();
			StudentDao dao = new StudentDao();

			// 検索処理
			List<Student> list;
			if (class_num.equals("0")) {
				if (ent_year == 0) {
					list = dao.filter(school, is_attend);
				} else {
					list = dao.filter(school, ent_year, is_attend);
				}
			} else {
				list = dao.filter(school, ent_year, class_num, is_attend);
			}

			// リストをセット
			request.setAttribute("list", list);

			// 年度リスト作成
			Calendar c = Calendar.getInstance();
			c.setTime(new Date());
			List<String> ent_year_set = new ArrayList<>();
			for (int i = -10; i <= 10; i++) {
				ent_year_set.add("" + (c.get(Calendar.YEAR) + i));
			}
			request.setAttribute("ent_year_set", ent_year_set);

			// クラス番号リスト取得
			ClassNumDao cdao = new ClassNumDao();
			List<String> class_num_set = cdao.filter(teacher.getSchool());
			request.setAttribute("class_num_set", class_num_set);

			return "student_list.jsp";

		} catch (Exception e) {
			return "../error.jsp";
		}
	}
}
