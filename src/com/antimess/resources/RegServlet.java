package com.antimess.resources;

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
	private AntiMessBusiness bus;

    public RegServlet() {
        super();
        bus = new AntiMessBusiness();
        // TODO Auto-generated constructor stub
    }
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(bus.registrieren(request.getParameter("email"), request.getParameter("password"))){
			response.getWriter().write("<!doctype html><html><head><meta charset='utf-8'><title>antiMESS - Anmelden</title><link rel='stylesheet' type='text/css' href='style/style.css'><script src='https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js'></script><script src='form.js'></script><link href='https://fonts.googleapis.com/css?family=Fira+Sans:400,700' rel='stylesheet' type='text/css'><link href='https://fonts.googleapis.com/css?family=Lato:400,900,700,300' rel='stylesheet' type='text/css'></head><body><div class='message'>Du hast dich erfolgreich mit der Email: " + request.getParameter("email") + " angemeldet.</div><header><h1><span class='logo'>anti</span>MESS</h1><h2>Bring dein Chaos in Ordnung</h2>       </header><form method='post' action='AntiMessServlet'><h3>Anmeldung</h3><input name='email' type='email' placeholder='E-Mail' required><input name='password' type='password' placeholder='Passwort' required><input type='submit' value='Senden' id='ud_send'></form><footer><a href='reg.html' class='rg'>Registrieren </a>|<a href='#' class='pw'>Passwort vergessen?</a></footer></body></html>");
			bus.close();
			response.flushBuffer();
			//view = request.getRequestDispatcher("afterReg.html");
		}else{
			view = request.getRequestDispatcher("reg.html");
			bus.close();
			view.forward(request, response); 

		}
	}

}
