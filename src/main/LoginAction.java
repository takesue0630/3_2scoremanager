package main;

import java.io.IOException;
import java.sql.SQLException;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Teacher;
import dao.TeacherDao;
import tool.Action;

public class LoginAction extends Action {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        String id = request.getParameter("id");
        String password = request.getParameter("password");

        TeacherDao dao = new TeacherDao();
        Teacher teacher = null;
        String errorMessage = null;
        String forwardTo = "login.jsp"; // 初期値としてログイン画面を設定

        try {
            teacher = dao.login(id, password);

            if (teacher != null) {
                session.setAttribute("teacher", teacher);
                session.setAttribute("teacherName", teacher.getName()); // ユーザー名をセッションに保存 (必要に応じて)
                forwardTo = "main/menu.jsp"; // ログイン成功時はメニュー画面へ
                response.sendRedirect(forwardTo);
                return null; // リダイレクトしたのでここで処理を終える
            } else {
                errorMessage = "IDまたはパスワードが正しくありません。";
            }

        } catch (SQLException e) {
            e.printStackTrace();
            errorMessage = "データベースエラーが発生しました。";
        } catch (NamingException e) {
            e.printStackTrace();
            errorMessage = "データソースの設定に問題があります。";
        } catch (Exception e) {
            e.printStackTrace();
            errorMessage = "予期せぬエラーが発生しました。";
        }

        if (errorMessage != null) {
            request.setAttribute("errorMessage", errorMessage);
            request.setAttribute("previousId", id); // 直前のIDを保持
            request.getRequestDispatcher(forwardTo).forward(request, response);
        }

        return null; // forward または redirect のいずれかを行っているので null を返す
    }
}