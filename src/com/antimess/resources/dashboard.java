package com.antimess.resources;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class dashboard
 */
@WebServlet("/dashboard")
public class dashboard extends HttpServlet {
	private static final long serialVersionUID = 1L;
	AntiMessBusiness bus;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public dashboard() {
        super();
        bus = new AntiMessBusiness();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(bus.isLogedIn(request.getParameter("id")))
			request.getRequestDispatcher("dashboard.html").forward(request, response);
		else
			response.sendRedirect("index.html");
	}

}
