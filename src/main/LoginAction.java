package main;

import java.io.IOException;
import java.sql.SQLException;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Teacher;
import dao.TeacherDao;

public class LoginAction extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();

        String id = request.getParameter("id");
        String password = request.getParameter("password");

        TeacherDao dao = new TeacherDao();
        Teacher teacher = null;

        try {
            teacher = dao.login(id, password);
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "データベースエラーが発生しました。");
            request.getRequestDispatcher("login.jsp").forward(request, response);
            return;
        } catch (NamingException e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "データソースの設定エラーが発生しました。");
            request.getRequestDispatcher("login.jsp").forward(request, response);
            return;
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "予期せぬエラーが発生しました。");
            request.getRequestDispatcher("login.jsp").forward(request, response);
            return;
        }
        if (teacher != null) {
            session.setAttribute("teacher", teacher);
            response.sendRedirect("main/menu.jsp");
        } else {
            request.setAttribute("errorMessage", "IDまたはパスワードが正しくありません。");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
}