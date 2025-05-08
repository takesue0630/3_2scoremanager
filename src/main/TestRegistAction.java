package main;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.School;
import bean.Subject;
import bean.Teacher;
import dao.ClassNumDao;
import dao.SubjectDao;
import dao.TestDao;
import tool.Action;

public class TestRegistAction extends Action {
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();
        Teacher teacher = (Teacher) session.getAttribute("teacher");

        ClassNumDao classdao = new ClassNumDao();
        SubjectDao subjectdao = new SubjectDao();

        List<String> class_num_list = classdao.filter(teacher.getSchool());
        List<Subject> subject_list = subjectdao.filter(teacher.getSchool());
        session.setAttribute("subject_list", subject_list);
        request.setAttribute("subject_list", subject_list);


        System.out.println(class_num_list);
        System.out.println(subject_list);

        String f1 = request.getParameter("f1");
        String f2 = request.getParameter("f2");
        String f3 = request.getParameter("f3");
        String f4 = request.getParameter("f4");

        System.out.println("=== TestRegistAction に到達 ===");
        System.out.println("f1: " + f1);
        System.out.println("f2: " + f2);
        System.out.println("f3: " + f3);
        System.out.println("f4: " + f4);

        if (f1 != null && !f1.isEmpty() &&
            f2 != null && !f2.isEmpty() &&
            f3 != null && !f3.isEmpty() &&
            f4 != null && !f4.isEmpty()) {

            try {
                System.out.println("f4: " + f4);
                int entYear = Integer.parseInt(f1);
                String classNum = f2;
                String subject = f3;
                int num = Integer.parseInt(f4);

                School school = teacher.getSchool();

                TestDao testdao = new TestDao();
                System.out.println("f1: " + f1);
                List<String> test_filter = testdao.filter(entYear, classNum, subject, num, school);
                System.out.println("f2: " + f2);
                System.out.println("test_filter.size(): " + test_filter.size());
                for (String s : test_filter) {
                    System.out.println(">> " + s);
                }

                request.setAttribute("test_filter", test_filter); // セッションではなくリクエストに変更

            } catch (NumberFormatException e) {
                e.printStackTrace();
                request.setAttribute("error", "検索項目に数値以外が含まれています。");
            }
        }
        System.out.println("f3: " + f3);
        return "test_regist.jsp";
    }
}


//以前のコード
/*public class TestRegistAction extends Action{
public String execute(
		HttpServletRequest request, HttpServletResponse response
	) throws Exception {
	HttpSession session=request.getSession();
	Teacher teacher=(Teacher)session.getAttribute("teacher");

	//学校のクラスデータを取得
	ClassNumDao classdao = new ClassNumDao();
	List<String> class_num_list = classdao.filter(teacher.getSchool());
	request.setAttribute("class_num_list", class_num_list);


	//学校の科目データを取得
	SubjectDao subjectdao = new SubjectDao();
	List<Subject> subject_list = subjectdao.filter(teacher.getSchool());
	request.setAttribute("subject_list", subject_list);


	System.out.println("ss");
	request.getRequestDispatcher("test_regist.jsp").include(request,response);
	System.out.println("o");


	//検索項目の受け取り(入学年度,クラス,科目,回数)
	//入学年度の受け取り
	int entYear=Integer.parseInt(request.getParameter("f1"));
	System.out.println(entYear);

	//クラスの受け取り
	String classNum=request.getParameter("f2");
	System.out.println(classNum);

	//科目の受け取り
	String subject=request.getParameter("f3");
	System.out.println(subject);


	//回数の受け取り
	int num=Integer.parseInt(request.getParameter("f4"));
	System.out.println(num);


	School school = new School();

	//入力された入学年度,クラス,科目,回数の成績データを取得
	TestDao testdao = new TestDao();
	List<String> test_filter = testdao.filter(entYear, classNum, subject, num, school);
	System.out.println(test_filter);

	//科目名を取得
	List<Subject> subject_filter = subjectdao.filter(school);

	//セッションに格納
	session.setAttribute("test_filter", test_filter);
	session.setAttribute("subject_filter", subject_filter);

	return "test_regist.jsp";
}
}*/