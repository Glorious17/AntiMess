package com.antimess.resources;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
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
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
    	view = request.getRequestDispatcher("./");
        view.forward(request, response);  
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(bus.anmelden(request.getParameter("email"), request.getParameter("password"))){
			view = request.getRequestDispatcher("dashboard.html");
		}else{
			view = request.getRequestDispatcher("./");
		}
        view.forward(request, response);  
	}

}
