

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class RegServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private RequestDispatcher view;
	private AntiMessDao dao;

    public RegServlet() {
        super();
        dao = new AntiMessDao();
        // TODO Auto-generated constructor stub
    }
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(dao.registrieren(request.getParameter("email"), request.getParameter("password"))){
			view = request.getRequestDispatcher("././");
		}else{
			view = request.getRequestDispatcher("./");
		}
		view.forward(request, response);  
	}

}
