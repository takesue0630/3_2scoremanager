package main;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Subject;
import bean.Teacher;
import dao.SubjectDao;
import tool.Action;

public class SubjectCreateExecuteAction extends Action {
	public String execute(
		HttpServletRequest request, HttpServletResponse response
	) throws Exception {
		//入力された値の受け取り
		String cd = request.getParameter("cd");
	    System.out.println(cd);

		String name = request.getParameter("name");
	    System.out.println(name);

	    System.out.println(cd);
	    System.out.println(name);

	    //科目コード、科目名一覧の受け取り
		HttpSession session = request.getSession();
		List<Subject> list = (List<Subject>) session.getAttribute("list");
		Teacher teacher=(Teacher)session.getAttribute("teacher");

		//科目コードが重複してた場合の例外処理
		for (Subject subject : list) {
		    System.out.println(subject.getCd());
		    System.out.println(subject.getSchool().getCd());

		    if (cd.equals(subject.getCd()) && teacher.getSchool().getCd().equals(subject.getSchool().getCd())) {
		        System.out.println("エラー: 科目コードが重複しています。");

		        // エラーメッセージをリクエスト属性にセット（JSPで表示できる）
		        request.setAttribute("error", "科目コードが重複しています。");

		        // 入力値の再表示のためにリクエストに設定
		        request.setAttribute("cd", cd);
		        request.setAttribute("name", name);

	         return "subject_create.jsp";
		    }
		}

		// バリデーション: 科目コードが3文字であることを確認
		if (cd == null || cd.length() != 3) {
		    request.setAttribute("error", "科目コードは3文字で入力してください。");
		    request.setAttribute("cd", cd);
		    request.setAttribute("name", name);
		    return "subject_create.jsp";
		}

		//beanに3つの値格納
		Subject subjectbean = new Subject();
		subjectbean.setSchool(teacher.getSchool());
		subjectbean.setCd(cd);
		subjectbean.setName(name);

		//saveメソッドで値を登録
		SubjectDao subjectdao = new SubjectDao();
		boolean s = subjectdao.save(subjectbean);

		return "subject_create_done.jsp";
	}
}
