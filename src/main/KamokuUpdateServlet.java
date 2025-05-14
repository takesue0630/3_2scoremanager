package main;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/KamokuUpdateServlet")
public class KamokuUpdateServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        String cd = request.getParameter("cd");
        String name = request.getParameter("name");

        if (name == null || name.trim().isEmpty()) {
            request.setAttribute("error", "科目名を入力してください。");
            request.setAttribute("cd", cd);
            request.setAttribute("name", name);
            request.getRequestDispatcher("kamoku_update.jsp").forward(request, response);
            return;
        }

        if (name.length() > 20) {
            request.setAttribute("error", "科目名は20文字以内で入力してください。");
            request.setAttribute("cd", cd);
            request.setAttribute("name", name);
            request.getRequestDispatcher("kamoku_update.jsp").forward(request, response);
            return;
        }

        HttpSession session = request.getSession();
        List<Kamoku> kamokuList = (List<Kamoku>) session.getAttribute("kamokuList");

        if (kamokuList != null) {
            for (Kamoku k : kamokuList) {
                if (k.getCd().equals(cd)) {
                    k.setName(name);
                    break;
                }
            }
        }

        // 変更完了後、フラグをクエリパラメータに付加してリダイレクト
        response.sendRedirect("kamoku_update.jsp?cd=" + cd + "&success=true");
    }
}
