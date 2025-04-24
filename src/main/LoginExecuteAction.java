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
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // ログインフォームから送信された ID とパスワードを取得
        String loginId = request.getParameter("loginId");
        String password = request.getParameter("password");

        // TeacherDao を使用してログイン認証を行う
        TeacherDao teacherDao = new TeacherDao();
        Teacher loggedInTeacher = null;

        try {
            loggedInTeacher = teacherDao.login(loginId, password);
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

        // 認証結果に基づいて処理を分岐
        if (loggedInTeacher != null) {
            // ログイン成功
            HttpSession session = request.getSession();
            session.setAttribute("loggedInUser", loggedInTeacher); // セッションにログイン情報を保存
            response.sendRedirect(request.getContextPath() + "/main/menu.jsp"); // メニュー画面へリダイレクト
        } else {
            // ログイン失敗
            request.setAttribute("errorMessage", "IDまたはパスワードが正しくありません。"); // エラーメッセージをリクエスト属性に設定
            request.getRequestDispatcher("/login.jsp").forward(request, response); // ログイン画面へフォワード (エラーメッセージを表示)
        }
		return null;
    }
}