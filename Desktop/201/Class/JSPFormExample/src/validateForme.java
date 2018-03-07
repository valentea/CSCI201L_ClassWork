package CSCI201ClassServlit;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class validateForme
 */
@WebServlet("/validateForme")
public class validateForme extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse responce) 
			throws ServletException, IOException  {
		String email = request.getParameter("email");
		String college = request.getParameter("college");
		String pageToForward = "/form.jsp";
		if(college != null && college.equals("USC")) {
			pageToForward = "/success.jsp";
		}else {
			request.setAttribute("collegeError", college + " is wrong");
		}
		RequestDispatcher dispatch = getServletContext().getRequestDispatcher(pageToForward);
		dispatch.forward(request, responce);
	}

}
