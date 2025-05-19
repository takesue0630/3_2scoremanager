package main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Subject;
import bean.Teacher;
import dao.SubjectDao;
import tool.Action;

public class SubjectUpdateExecuteAction extends Action {
	public String execute(
		HttpServletRequest request, HttpServletResponse response
	) throws Exception {
		try {
//			セッションからユーザーデータを取得
			HttpSession session=request.getSession();
			Teacher teacher=(Teacher)session.getAttribute("teacher");

//			変更元のデータがデータベースにあるか問い合わせる
			String cd=request.getParameter("cd");
			SubjectDao subject_dao=new SubjectDao();
			Subject subject=subject_dao.get(cd, teacher.getSchool());
			if (subject.getCd()==null){
				return "subject_update.jsp";
			}

//			科目データを取得
			subject.setCd(cd);
			subject.setName(request.getParameter("name"));
			subject.setSchool(teacher.getSchool());

//			保存する
			subject_dao.save(subject);

			return "subject_update_done.jsp";

		} catch(Exception e) {
			return "../error.jsp";
		}
	}
}
