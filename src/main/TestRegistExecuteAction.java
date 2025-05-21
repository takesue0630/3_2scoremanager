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
import bean.Test;
import dao.ClassNumDao;
import dao.StudentDao;
import dao.SubjectDao;
import dao.TestDao;
import tool.Action;

public class TestRegistExecuteAction extends Action {
    public String execute(
    	HttpServletRequest request, HttpServletResponse response
    ) throws Exception {
    	try {
//			セッションからユーザーデータを取得
			HttpSession session=request.getSession();
			Teacher teacher=(Teacher)session.getAttribute("teacher");

//			検索項目を取得
			int ent_year=Integer.parseInt(request.getParameter("ent_year"));
			String class_num=request.getParameter("class_num");
			String subject_cd=request.getParameter("subject_cd");
            int subject_num=Integer.parseInt(request.getParameter("subject_num"));

//          更新前のテストリスト
			TestDao test_dao=new TestDao();
			List<Test> test_list=test_dao.filter(ent_year,class_num,subject_cd,subject_num,teacher.getSchool());

//			更新後のテストリスト
			List<Test> new_test_list=new ArrayList<>();

//			リストサイズ分繰り返す
			boolean fraud=false;

			for (Test t:test_list){
				Test test=new Test();

//				値の受け取り
				String path=t.getStudent().getNo() + "_"+t.getSubject().getCd()+"_"+t.getNo();
				System.out.println(path);

//				点数の範囲が正常かチェック
//				空の場合は-1を入れる
				int point=-1;
				if (request.getParameter(path)!="") {
					point=Integer.parseInt(request.getParameter(path));
					if (point<0 || point>100) {
						fraud=true;
					}
				}
				test.setPoint(point);
				StudentDao student_dao=new StudentDao();
				test.setStudent(student_dao.get(t.getStudent().getNo()));
				test.setSubject(t.getSubject());
				test.setNo(t.getNo());
				test.setSchool(teacher.getSchool());
				new_test_list.add(test);
			}

//			不正な値があった場合は入力画面に戻す
			if (fraud) {
//				セッションのユーザーデータから、ユーザーが所属している学校のクラスデータを取得
				ClassNumDao class_num_dao = new ClassNumDao();
				List<String> class_num_set = class_num_dao.filter(teacher.getSchool());
				System.out.println("クラスデータ" + class_num_set);
				request.setAttribute("class_num_set", class_num_set);

//				セッションのユーザーデータから、ユーザーが所属している学校の科目データを取得
				SubjectDao subject_dao = new SubjectDao();
				List<Subject> subject_set = subject_dao.filter(teacher.getSchool());
				System.out.println("科目データ" + subject_set);
				request.setAttribute("subject_set", subject_set);

//				カレンダーオブジェクトの生成
				Calendar c = Calendar.getInstance();
//				現在年を格納
			    c.setTime(new Date());
//			    十年前から十年後までのリストを作成
			    List<String> ent_year_set=new ArrayList<>();
			    for (int i = -10; i <= 10; i++) {
			    	ent_year_set.add(""+(c.get(Calendar.YEAR)+i)+"");
			    }
			    request.setAttribute("ent_year_set", ent_year_set);

//				入力保持用（再表示のため）
	            request.setAttribute("ent_year", ent_year);
	            request.setAttribute("class_num", class_num);
	            request.setAttribute("subject_cd", subject_cd);
	            request.setAttribute("subject_num", subject_num);
            	request.setAttribute("test_list", new_test_list);

            	Subject subject=subject_dao.get(subject_cd, teacher.getSchool());
            	request.setAttribute("subject", subject);

            	request.setAttribute("serch", true);

				return "test_regist.jsp";
			}

			test_dao.save(new_test_list);

			for (Test test:new_test_list) {
				if (test.getPoint()==-1) {
					test_dao.delete(test.getStudent().getNo(),test.getSubject().getCd(),teacher.getSchool().getCd(),test.getNo());
				}
			}

            return "test_regist_done.jsp";
    	} catch(Exception e) {
    		e.printStackTrace();
    		return "../error.jsp";
    	}
    }
}
