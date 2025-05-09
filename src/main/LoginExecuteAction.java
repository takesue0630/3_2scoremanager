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

            return "/login.jsp"; // フォワード先を返す

        }

        if (password == null || password.trim().isEmpty()) {

            request.setAttribute("passwordError", "パスワードを入力してください。");

            return "/login.jsp"; // フォワード先を返す

        }

        TeacherDao dao = new TeacherDao();

        Teacher teacher = null;

        try {

            teacher = dao.login(id, password);

        } catch (SQLException e) {

            e.printStackTrace();

            request.setAttribute("errorMessage", "データベースエラーが発生しました。");

            return "/login.jsp"; // フォワード先を返す

        } catch (NamingException e) {

            e.printStackTrace();

            request.setAttribute("errorMessage", "データソースの設定エラーが発生しました。");

            return "/login.jsp"; // フォワード先を返す

        } catch (Exception e) {

            e.printStackTrace();

            request.setAttribute("errorMessage", "予期せぬエラーが発生しました。");

            return "/login.jsp"; // フォワード先を返す

        }

        if (teacher != null) {

            // ログイン成功

            session.setAttribute("userId", teacher.getId()); // 例: ユーザーIDをセッションに保存

            // 必要に応じて他のユーザー情報をセッションに保存

            return "main/menu.jsp"; // 遷移先のURLを返す (FrontControllerでフォワード)

        } else {

            // ログイン失敗

            request.setAttribute("errorMessage", "IDまたはパスワードが正しくありません。");

            return "/login.jsp"; // フォワード先を返す

        }

	}

}
