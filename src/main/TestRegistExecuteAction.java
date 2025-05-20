package main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Subject;
import dao.TestDao;
import tool.Action;

public class TestRegistExecuteAction extends Action {
    public String execute(
    	HttpServletRequest request, HttpServletResponse response
    ) throws Exception {
    	try {
    		String studentCountStr = request.getParameter("studentCount");
            List<String> saveList = new ArrayList<>();
            List<String> deleteList = new ArrayList<>();
            Map<String, String> errorMap = new HashMap<>();

            // 検索条件


            // 科目名と表示番号
            String subjectName = request.getParameter("subject");
            String subjectNum = request.getParameter("subjectNum");

            HttpSession session = request.getSession();
            List<Subject> subjectList = (List<Subject>) session.getAttribute("subject_list");

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
                request.setAttribute("error", "選択された科目のCDが見つかりませんでした。");
                request.setAttribute("test_filter", session.getAttribute("test_filter")); // 再表示用
                return "test_regist.jsp";
            }

            boolean hasError = false;

            if (studentCountStr != null && !studentCountStr.isEmpty()) {
                int studentCount = Integer.parseInt(studentCountStr);

                for (int i = 0; i < studentCount; i++) {
                    String studentNo = request.getParameter("studentNo_" + i);
                    String pointStr = request.getParameter("point_" + i);

                    if (pointStr == null || pointStr.trim().isEmpty()) {
                        // 空欄 → 削除対象
                        deleteList.add(studentNo);
                    } else {
                        try {
                            int point = Integer.parseInt(pointStr);
                            if (point < 0 || point > 100) {
                                errorMap.put("point_" + i, "0～100の範囲で入力してください");
                                hasError = true;
                            } else {
                                // 保存対象
                                saveList.add(studentNo);
                                saveList.add(pointStr);
                                saveList.add(subjectNum);
                            }
                        } catch (NumberFormatException e) {
                            errorMap.put("point_" + i, "数値を入力してください");
                            hasError = true;
                        }
                    }
                }

                if (hasError) {
                    request.setAttribute("errorMap", errorMap);
                    request.setAttribute("test_filter", session.getAttribute("test_filter"));
                    request.setAttribute("studentCount", studentCountStr);
                    return "test_regist.jsp";
                }

                TestDao testdao = new TestDao();

                if (!saveList.isEmpty()) {
                    saveList.add(subjectCd);
                    testdao.save(saveList);
                }

                for (String studentNo : deleteList) {
                    testdao.delete(studentNo);
                    }
            }

            return "test_regist_done.jsp";
    	} catch(Exception e) {
    		return "../error.jsp";
    	}
    }
}
