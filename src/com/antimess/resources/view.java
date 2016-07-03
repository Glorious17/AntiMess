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

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		ArrayList<String> ls = bus.getLagerort(bus.getUserThroughId(request.getSession().getId()));
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE html>");
		out.println("<html>");
			out.println("<head>");
				out.println("<link rel=\"stylesheet\" href=\"style/header.css\"/>");
				out.println("<link rel=\"stylesheet\" href=\"style/form-bearbeiten.css\">");
				out.println("<link rel=\"stylesheet\" href=\"style/form-hinzufügen.css\">");
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
		
		out.println("<div id=\"content\"  ng-controller=\"ContentCtrl\">");
		
			out.println("<div id=\"formular-hinzufuegen\">");
				out.println("<form id=\"form-add\" method=\"post\" action=\"add\">");
				out.println("<h2>Neuer Lagerort</h2>");
				out.println("<img id=\"close-hinzu\" src=\"style/img/clear_black.png\">");
				out.println("<input name=\"new-lagerort\" type=\"text\" placeholder=\"Lagerort\" required>");
				out.println("</br>");
				out.println("<input name=\"permission\" type=\"text\" placeholder=\"Berechtigung\" required>");
				out.println("</br>");
				out.println("<input type=\"submit\" value=\"Hinzufügen\" id=\"ud_send\">");
				out.println("</form>");
			out.println("</div>");
			
			out.println("<div id=\"formular-bearbeiten\">");
				out.println("<form id=\"form-edit\" method=\"post\" action=\"newName\">");
				out.println("<h2>Lagerort bearbeiten</h2>");
				out.println("<img id=\"close-bea\" src=\"style/img/clear_black.png\">");
				out.println("<h4>Lagerort umbenennen</h4>");
				out.println("<select name=\"top5\" size=\"1\" ng-model=\"lager.Lagerort\">");
				for(String storage : ls){
					if(storage.contains("Berechtigter Zugriff")){
						storage = storage.substring(storage.indexOf(':')+2);
					}
					out.println("<option>" + storage + "</option>");
				}
				out.println("<input name=\"Name\" type=\"text\" placeholder=\"Neuer Name\">");
				out.println("<h3>Lagerort Freigabe</h3>");
				out.println("<h4>Person hinzufügen</h4>");
				out.println("<input name=\"Person hinzufügen\" type=\"text\" placeholder=\"E-Mail Adresse\">");
				out.println("<h4>Person entfernen</h4>");
				out.println("<select name=\"permissions\" size=\"1\">");
					out.println("<option value = \"\"><option>");
					out.println("<option ng-repeat=\"data in person | filter : lager\">{{data.Berechtigt}}</option>");
				out.println("</select>");
				out.println("<input type=\"submit\" value=\"Übernehmen\" id=\"ud_send\">");
				out.println("</form>");
			out.println("</div>");
			out.println("<div id=\"menu\">");
			out.println("<label>");
			out.println("<select name=\"top5\" size=\"1\" ng-model=\"lager.Lagerort\">");
			for(String storage : ls){
				if(storage.contains("Berechtigter Zugriff")){
					storage = storage.substring(storage.indexOf(':')+2);
				}
				out.println("<option>" + storage + "</option>");
			}
			out.println("</select>");
			out.println("</label>");
			out.println("<ul class=\"menu-list\">");
			out.println("<li class=\"menu-button\"><img src=\"style/img/edit.png\" id=\"bearbeiten\"></li>");
			out.println("<li class=\"menu-button\"><img src=\"style/img/add.png\" id=\"hinzufuegen\"></li>");
			out.println("</ul>");
		out.println("</div>");
		out.println("<script src=\"https://ajax.googleapis.com/ajax/libs/jquery/2.2.2/jquery.min.js\"></script>");
		out.println("<script src=\"js/hiddenform.js\"></script>");
			out.println("<div>");
				out.println("<ul class=\"item-pic\">");
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
