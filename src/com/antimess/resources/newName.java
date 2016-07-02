package com.antimess.resources;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class newName
 */
public class newName extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private AntiMessBusiness bus;

    public newName() {
        super();
        bus = new AntiMessBusiness();
    }
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String lagerortOld = request.getParameter("top5");
		String lagerortNew = request.getParameter("Name");
		String newPermission = request.getParameter("Person hinzufügen");
		String delpermission = request.getParameter("permissions");
		int lagerortID = bus.getLagerortID(bus.getUserThroughId(request.getSession().getId()), lagerortOld);
		if(lagerortID != -1){
			if(lagerortNew != null && !lagerortNew.equals("")){
				bus.updateLagerortName(lagerortID, lagerortNew);
			}
			if(newPermission != null){
				bus.addPermission(lagerortID, newPermission, bus.getUserThroughId(request.getSession().getId()));
			}
			if(delpermission != null){
				bus.deletePermission(lagerortID, delpermission);
			}
		}
		response.sendRedirect("storageView?id="+request.getSession().getId());
	}

}
