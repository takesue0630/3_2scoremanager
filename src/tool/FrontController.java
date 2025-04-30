package tool;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns={"*.action"})
public class FrontController extends HttpServlet {
    public void doPost (
        HttpServletRequest request, HttpServletResponse response
    ) throws ServletException, IOException {
        PrintWriter out=response.getWriter();

        try {
        	String path = request.getServletPath().substring(1);
        	String classNamePart = path.replace(".action", "").replace('/', '.');
        	String fullClassName = "main." + classNamePart.substring(classNamePart.lastIndexOf('.') + 1) + "Action";
        	Action action = (Action) Class.forName(fullClassName).getDeclaredConstructor().newInstance();
        	String url = action.execute(request, response);
        	request.getRequestDispatcher(url).forward(request, response);
        	} catch (Exception e) {
            e.printStackTrace(out);
        }
    }

    public void doGet (
        HttpServletRequest request, HttpServletResponse response
    ) throws ServletException, IOException {
        doPost(request,response);
    }
}