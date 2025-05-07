package main;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.TestDao;
import tool.Action;


public class TestRegistExecuteAction extends Action {
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
    String studentCountStr = request.getParameter("studentCount");
    List<String> list = new ArrayList<>();

    // 共通の科目と回数（ループの外で一度だけ取得）
    String subject = request.getParameter("subject");
    String subjectNum = request.getParameter("subjectNum");

    if (studentCountStr != null && !studentCountStr.isEmpty()) {
        int studentCount = Integer.parseInt(studentCountStr);

        for (int i = 0; i < studentCount; i++) {
            String studentNo = request.getParameter("studentNo_" + i);
            String studentName = request.getParameter("studentName_" + i);
            String pointStr = request.getParameter("point_" + i);

            // デバッグ出力
            System.out.println("学生番号: " + studentNo);
            System.out.println("氏名: " + studentName);
            System.out.println("点数: " + pointStr);
            System.out.println("科目: " + subject);
            System.out.println("回数: " + subjectNum);
            System.out.println("----------------------");

            // 必要な順序・構成でデータを追加（この形式はDAOに合わせて調整）
            list.add(studentNo);
            list.add(pointStr);
            list.add(subject);
            list.add(subjectNum);
        }
        System.out.println(list);
        TestDao testdao = new TestDao();
        testdao.save1(list);

    } else {
        System.out.println("studentCount が指定されていません。");
    }

    return "test_regist_done.jsp";
	}
}