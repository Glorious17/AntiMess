

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class AntiMessServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private AntiMessDao dao;
	private RequestDispatcher view;
       
    public AntiMessServlet() {
        super();
        dao = new AntiMessDao();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(dao.anmelden(request.getParameter("email"), request.getParameter("password"))){
			response.getWriter().println("<h1>Angemeldet</h1>");
		}else{
			view = request.getRequestDispatcher("./");
	        view.forward(request, response);  
		}
	}

}
