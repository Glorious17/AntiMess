package com.antimess.resources;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class AntiMessServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private RequestDispatcher view;
	private AntiMessBusiness bus;
       
    public AntiMessServlet() {
        super();
    	bus = new AntiMessBusiness();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String username = request.getParameter("email");
		if(bus.anmelden(username, request.getParameter("password"))){
			if(bus.forgotToLogoff(username)){
				bus.logoutUser(username);
				bus.setSession(request.getParameter("email"), request.getSession().getId());
				request.getRequestDispatcher("forgotToLogoff.html").forward(request, response);
			}else{
				bus.setSession(request.getParameter("email"), request.getSession().getId());
				response.sendRedirect("dash?id=" + request.getSession().getId());
			}
			//bus.close();
		}else{
			response.sendRedirect("index.html");
		}
	}

}
