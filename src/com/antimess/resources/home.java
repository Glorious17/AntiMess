package com.antimess.resources;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class home
 */
@WebServlet("/home")
public class home extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private AntiMessBusiness bus;
       
    public home() {
        super();
        bus = new AntiMessBusiness();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(bus.isLogedIn(request.getSession().getId()))
			response.sendRedirect("dashboard.html");
		else
			response.sendRedirect("index.html");
	}

}
