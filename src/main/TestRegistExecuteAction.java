package main;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Subject;
import dao.TestDao;
import tool.Action;

public class TestRegistExecuteAction extends Action {
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String studentCountStr = request.getParameter("studentCount");
        List<String> list = new ArrayList<>();

        // 科目名と回数（共通）
        String subjectName = request.getParameter("subject");
        String subjectNum = request.getParameter("subjectNum");

        HttpSession session = request.getSession();
        List<Subject> subjectList = (List<Subject>) session.getAttribute("subject_list");

        // 科目CDを取得
        String subjectCd = null;
        if (subjectList != null) {
            for (Subject s : subjectList) {
                if (s.getName().equals(subjectName)) {
                    subjectCd = s.getCd();
                    break;
                }
            }
        }

        if (subjectCd == null) {
            System.out.println("該当する科目CDが見つかりません。");
            request.setAttribute("error", "選択された科目のCDが見つかりませんでした。");
            return "test_regist.jsp"; // エラーページにリダイレクトしてもOK
        }

        // 学生情報の取得とデータ格納
        if (studentCountStr != null && !studentCountStr.isEmpty()) {
            int studentCount = Integer.parseInt(studentCountStr);

            for (int i = 0; i < studentCount; i++) {
                String studentNo = request.getParameter("studentNo_" + i);
                String studentName = request.getParameter("studentName_" + i);
                String pointStr = request.getParameter("point_" + i);

                System.out.println("学生番号: " + studentNo);
                System.out.println("氏名: " + studentName);
                System.out.println("点数: " + pointStr);
                System.out.println("科目CD: " + subjectCd);
                System.out.println("回数: " + subjectNum);
                System.out.println("----------------------");

                list.add(studentNo);
                list.add(pointStr);    // ← CDを使う！
                list.add(subjectNum);
            }
            list.add(subjectCd);
            // 保存処理
            TestDao testdao = new TestDao();
            testdao.save1(list);

        } else {
            System.out.println("studentCount が指定されていません。");
        }

        return "test_regist_done.jsp";
    }
}
