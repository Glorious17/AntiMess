package com.antimess.resources;

import java.io.File;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class delete
 */
public class delete extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private AntiMessBusiness bus; 
    
    public delete() {
        super();
        bus = new AntiMessBusiness();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("picid") != null){
			int picid = Integer.parseInt(request.getParameter("picid"));
			String url = bus.getItems(picid).get(3);
			bus.deleteItem(picid);
			File file = new File("C:/tomcat/" + url.substring(url.lastIndexOf("/")));
			file.delete();
		}
		response.sendRedirect("./desk?id=" + request.getSession().getId());
	}

}
