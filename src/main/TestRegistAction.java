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
import dao.TestDao;
import tool.Action;

//public class TestRegistAction extends Action {
//    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
//        HttpSession session = request.getSession();
//        Teacher teacher = (Teacher) session.getAttribute("teacher");
//
//        ClassNumDao classdao = new ClassNumDao();
//        SubjectDao subjectdao = new SubjectDao();
//
//        List<String> class_num_list = classdao.filter(teacher.getSchool());
//        List<Subject> subject_list = subjectdao.filter(teacher.getSchool());
//        session.setAttribute("subject_list", subject_list);
//        request.setAttribute("subject_list", subject_list);
//
//
//        System.out.println(class_num_list);
//        System.out.println(subject_list);
//
//        String f1 = request.getParameter("f1");
//        String f2 = request.getParameter("f2");
//        String f3 = request.getParameter("f3");
//        String f4 = request.getParameter("f4");
//
//        request.setAttribute("selectedF1", f1);
//        request.setAttribute("selectedF2", f2);
//        request.setAttribute("selectedF3", f3);
//        request.setAttribute("selectedF4", f4);
//
//
//        System.out.println("=== TestRegistAction に到達 ===");
//        System.out.println("f1: " + f1);
//        System.out.println("f2: " + f2);
//        System.out.println("f3: " + f3);
//        System.out.println("f4: " + f4);
//
//        if (f1 != null && !f1.isEmpty() &&
//            f2 != null && !f2.isEmpty() &&
//            f3 != null && !f3.isEmpty() &&
//            f4 != null && !f4.isEmpty()) {
//
//            try {
//                System.out.println("f4: " + f4);
//                int entYear = Integer.parseInt(f1);
//                String classNum = f2;
//                String subject = f3;
//                int num = Integer.parseInt(f4);
//
//                School school = teacher.getSchool();
//
//                TestDao testdao = new TestDao();
//                System.out.println("f1: " + f1);
//                List<String> test_filter = testdao.filter(entYear, classNum, subject, num, school);
//                System.out.println("f2: " + f2);
//                System.out.println("test_filter.size(): " + test_filter.size());
//                for (String s : test_filter) {
//                    System.out.println(">> " + s);
//                }
//
//                request.setAttribute("test_filter", test_filter); // セッションではなくリクエストに変更
//                session.setAttribute("test_filter", test_filter);
//
//            } catch (NumberFormatException e) {
//                e.printStackTrace();
//                request.setAttribute("error", "検索項目に数値以外が含まれています。");
//            }
//        }
//        System.out.println("f3: " + f3);
//        return "test_regist.jsp";
//    }
//}


//以前のコード
public class TestRegistAction extends Action{
	public String execute(
		HttpServletRequest request, HttpServletResponse response
	) throws Exception {
		try {
//			セッションからユーザーデータを取得
			HttpSession session=request.getSession();
			Teacher teacher=(Teacher)session.getAttribute("teacher");

			String ent_year = request.getParameter("f1"); // 入学年度
            String class_num = request.getParameter("f2"); // クラス
            String subject_cd = request.getParameter("f3"); // 教科
            String subject_num = request.getParameter("f4"); // 回数

            System.out.println("入学年度:" + ent_year);
            System.out.println("クラス　:" + class_num);
            System.out.println("教科　　:" + subject_cd);
            System.out.println("回数　　:" + subject_num);

//			セッションのユーザーデータから、ユーザーが所属している学校のクラスデータを取得
			ClassNumDao class_num_dao = new ClassNumDao();
			List<String> class_num_set = class_num_dao.filter(teacher.getSchool());
			System.out.println("クラスデータ" + class_num_set);
			request.setAttribute("class_num_set", class_num_set);


//			セッションのユーザーデータから、ユーザーが所属している学校の科目データを取得
			SubjectDao subject_dao = new SubjectDao();
			List<Subject> subject_set = subject_dao.filter(teacher.getSchool());
			System.out.println("科目データ" + subject_set);
			request.setAttribute("subject_set", subject_set);

//			カレンダーオブジェクトの生成
			Calendar c = Calendar.getInstance();
//			現在年を格納
		    c.setTime(new Date());
//		    十年前から十年後までのリストを作成
		    List<String> ent_year_set=new ArrayList<>();
		    for (int i = -10; i <= 10; i++) {
		    	ent_year_set.add(""+(c.get(Calendar.YEAR)+i)+"");
		    }
		    request.setAttribute("ent_year_set", ent_year_set);

		 // 入力保持用（再表示のため）
            request.setAttribute("selectedF1", ent_year);
            request.setAttribute("selectedF2", class_num);
            request.setAttribute("selectedF3", subject_cd);
            request.setAttribute("selectedF4", subject_num);

            if (ent_year==null) {
//              初めてこの画面に来た

            	request.setAttribute("serch", false);

            } else if (ent_year=="" || class_num=="" || subject_cd=="" || subject_num=="") {
//            	検索に不十分な選択

            	request.setAttribute("serch", false);

                //リクエスト属性にエラーメッセージを格納
                request.setAttribute("error", "入学年度とクラスと科目と回数を選択してください");

            } else {
//            	検索成功

            	TestDao test_dao=new TestDao();
            	List<String> test_list=test_dao.filter(Integer.parseInt(ent_year), class_num, subject_cd, Integer.parseInt(subject_num), teacher.getSchool());
            	request.setAttribute("test_list", test_list);
            	System.out.println("テストリスト"+test_list);

            	String subject_name=subject_dao.get(subject_cd, teacher.getSchool()).getName();
            	request.setAttribute("subject_name", subject_name);

            	request.setAttribute("serch", true);

            }

            return "test_regist.jsp";

		} catch(Exception e) {
			return "../error.jsp";
		}
	}
}