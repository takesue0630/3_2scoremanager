package main;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import tool.Action;

public class LogoutAction extends Action {
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 現在のセッションを取得 (存在しない場合は null を返す)
        HttpSession session = request.getSession(false);

        // セッションが存在する場合、無効化する
        if (session != null) {
            session.invalidate(); // セッションを破棄
        }

        // ログアウト完了画面へリダイレクト
        response.sendRedirect(request.getContextPath() + "/logout_done.jsp");
		return null;

        // または、ログイン画面へリダイレクトする場合
        // response.sendRedirect(request.getContextPath() + "/Login.action?logout=true");
    }
}
