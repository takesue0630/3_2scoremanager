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

public class LoginExecuteAction extends Action {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        String id = request.getParameter("id");
        String password = request.getParameter("password");

        // 必須項目未入力チェック
        if (id == null || id.trim().isEmpty()) {
            request.setAttribute("idError", "IDを入力してください。");
            request.getRequestDispatcher("/login.jsp").forward(request, response);
            return null;
        }
        if (password == null || password.trim().isEmpty()) {
            request.setAttribute("passwordError", "パスワードを入力してください。");
            request.getRequestDispatcher("/login.jsp").forward(request, response);
            return null;
        }

        TeacherDao dao = new TeacherDao();
        Teacher teacher = null;

        try {
            teacher = dao.login(id, password);
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "データベースエラーが発生しました。");
            request.getRequestDispatcher("/login.jsp").forward(request, response);
            return null;
        } catch (NamingException e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "データソースの設定エラーが発生しました。");
            request.getRequestDispatcher("/login.jsp").forward(request, response);
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "予期せぬエラーが発生しました。");
            request.getRequestDispatcher("/login.jsp").forward(request, response);
            return null;
        }

        if (teacher != null) {
            // ログイン成功
            // ...
        } else {
            // ログイン失敗
            request.setAttribute("errorMessage", "IDまたはパスワードが正しくありません。"); // エラーメッセージをリクエスト属性に設定
            request.getRequestDispatcher("/login.jsp").forward(request, response); // ログイン画面へフォワード (エラーメッセージを表示)
        }
		return "main/menu.jsp";
    }
}