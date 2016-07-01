package com.antimess.resources;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ItemView
 */
public class ItemView extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private AntiMessBusiness bus;
       
    public ItemView() {
        super();
        bus = new AntiMessBusiness();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		int picid = Integer.parseInt(request.getParameter("picid"));
		ArrayList<String> ls = bus.getItems(picid);
		PrintWriter out = response.getWriter();
		out.println("<link rel=\"stylesheet\" href=\"style/dashboard-style.css\"/>");
		out.println("<link rel=\"stylesheet\" href=\"style/item-style.css\"/>");
		out.println("<link href='https://fonts.googleapis.com/css?family=Fira+Sans:400,700' rel='stylesheet' type='text/css'>");
		out.println("<link href='https://fonts.googleapis.com/css?family=Lato:400,900,700,300' rel='stylesheet' type='text/css'>");
		out.println("<meta charset=\"UTF-8\">");
		out.println("<title>Gegenstand</title>");
		out.println("</head>");
		out.println("<body>");
		out.println("<header>");
		out.println("<div id=\"header-menu\">");
		out.println("<a class=\"logout-link\" href=\"./logout\"><img class=\"logout-icon\" src=\"img/logout-icon.png\"></a>");
		out.println("</div>");
		out.println("<div id=\"logo\">");
		out.println("<a class=\"logo-link\" href=\"./home\"><h1><span class=\"logo\">anti</span>MESS</h1> </a>");
		out.println("</div>");
		out.println("<img src=\"" + ls.get(3) + "\" alt=\"" + ls.get(0) + "\" id=\"item_pic\">");
		out.println("<div id=\"content\">");
		out.println("<div id=\"title\">");
		out.println("<p>Bezeichnung:</p>");
		out.println("<p>Schlagwörter:</p>");
		out.println("<p>Lagerort:</p>");
		out.println("<p>Lagerdatum:</p>");
		out.println("</div>");
		out.println("<div id=\"gegenstand\">");
		out.println("<p>" + ls.get(0) + "</p>");
		out.println("<p>" + ls.get(4) + "</p>");
		out.println("<p>" + ls.get(1) + "</p>");
		out.println("<p>" + ls.get(2) + "</p>");
		out.println("</div>");
		out.println("</div>");
		out.println("<div id=\"menu\">");
		out.println("<ul>");
		out.println("<li><a class=\"menu-auslagern\" href=\"#\">Auslagern</a></li>");
		out.println("<li><a class=\"menu-einalgern\" href=\"#\">Einlagern</a></li>");
		out.println("<li><a class=\"menu-löschen\" href=\"#\">Löschen</a></li>");
		out.println("</ul>");
		out.println("</div>");
		out.println("</body>");
		out.println("</html>");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
