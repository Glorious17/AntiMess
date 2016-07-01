package com.antimess.resources;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class view extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private AntiMessBusiness bus;
	
    public view() {
        super();
        bus = new AntiMessBusiness();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<String> ls = bus.getLagerort(bus.getUserThroughId(request.getSession().getId()));
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE html>");
		out.println("<html>");
		out.println("<head>");
		out.println("<link rel=\"stylesheet\" href=\"style/header.css\"/>");
		out.println("<link rel=\"stylesheet\" href=\"style/lageransicht.css\"/>");
		out.println("<link href='https://fonts.googleapis.com/css?family=Fira+Sans:400,700' rel='stylesheet' type='text/css'>");
		out.println("<link href='https://fonts.googleapis.com/css?family=Lato:400,900,700,300' rel='stylesheet' type='text/css'>");
		out.println("<meta charset=\"utf-8\">");
		out.println("<meta charset=\"ISO-8859-1\">");
		out.println("<title>Lagerverwaltung</title>");
		out.println("</head>");
		out.println("<body>");
		out.println("<header>");
		out.println("<div id=\"header-menu\">");
		out.println("<a class=\"logout-link\" href=\"./logout\"><img class=\"logout-icon\" src=\"img/logout-icon.png\"></a>");
		out.println("</div>");
		out.println("<a href = \"./home?id=" + request.getSession().getId() + "\"><div id=\"logo\">");
		out.println("<h1><span class=\"logo\">anti</span>MESS</h1>");
		out.println("</div></a>");
		out.println("</header>");
		out.println("<div id=\"content\">");
		out.println("<div id=\"menu\">");
		out.println("<label>");
		out.println("<select name=\"top5\" size=\"1\" ng-model=\"lager.Lagerort\">");
		out.println("<option></option>");
		for(String storage : ls){
			if(storage.contains("Berechtigter Zugriff")){
				storage = storage.substring(storage.indexOf(':')+2);
			}
			out.println("<option>" + storage + "</option>");
		}
		out.println("</select>");
		out.println("<button id=\"bearbeiten\">BEA</button>");
		out.println("<button id=\"hinzufügen\">[+]</button>");
		out.println("</label>");
		out.println("</div>");
		out.println("<div>");
		out.println("<ul ng-controller=\"ContentCtrl\">");
		out.println("<li ng-repeat=\"data in person | filter : lager\">");
		out.println("<a href=\"item?picid={{data.ID}}\"><img src=\"{{data.Icon}}\" height=\"200\" width=\"auto\"></a>");
		out.println("</li>");
		out.println("</ul>");
		out.println("</div>");
		out.println("</div>");
		out.println("<script type=\"text/javascript\" src=\"https://ajax.googleapis.com/ajax/libs/angularjs/1.5.6/angular.min.js\"></script>");
		out.println("<script type=\"text/javascript\" src=\"js/MainModule.js\"></script>");
		out.println("<script type=\"text/javascript\" src=\"js/ctrl/CtrlModule.js\"></script>");
		out.println("<script type=\"text/javascript\" src=\"js/main.js\"></script>");
		out.println("</body>");
		out.println("</html>");
		out.close();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
