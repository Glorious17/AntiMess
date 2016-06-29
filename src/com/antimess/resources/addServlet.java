package com.antimess.resources;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URISyntaxException;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class addServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private AntiMessBusiness bus;
	private String url;

    public addServlet() {
        super();
        bus = new AntiMessBusiness();
    }
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		if(request.getParameter("new-lagerort") == null){
			if(ServletFileUpload.isMultipartContent(request)){
				url = getFile(request);
				request.getSession().setAttribute("pic-url", url);
				response.sendRedirect("add.html?id="+ request.getSession().getId() +"&pic="+url.substring(url.lastIndexOf("/")+1));
			}else if(request.getSession().getAttribute("pic-url") != null){
				String name = request.getParameter("gegenstand");
				String lagerort = request.getParameter("top5");
				String keyword = request.getParameter("attribut");
				String url = (String) request.getSession().getAttribute("pic-url");
				System.out.println(url);
				int id = bus.addItem(name, new java.sql.Date(findDate().getTime()), url, lagerort, bus.getUserThroughId(request.getSession().getId()), keyword);
				File file = file// <--Hier weiter arbeiten Jens
				url = "C:/tomcat/" + id + url.substring(url.lastIndexOf('_'));
				File newFile = new File(url);
				file.renameTo(newFile);
				bus.updateItemPic(id, url);
				request.getSession().setAttribute("pic-url", null);
				response.sendRedirect("./home");
			}else{
				response.sendRedirect("add.html?id="+request.getSession().getId());
			}
		}else{
			if(bus.addLagerort(request.getParameter("new-lagerort"), null, bus.getUserThroughId(request.getSession().getId()))){
				String url = (String) request.getSession().getAttribute("pic-url");
				if(url == null){
					response.sendRedirect("add.html?id="+request.getSession().getId());
				}else{
					response.sendRedirect("add.html?id="+ request.getSession().getId() +"&pic="+url.substring(url.lastIndexOf("/")+1));
				}
				
			}else{
				response.setContentType("text/html");
				PrintWriter out = response.getWriter();
				out.println("Dein Lagerort existiert bereits! Trage einen neuen ein:");
				out.println("<form id=\"lagerort\" method=\"post\" action=\"add\">");
				out.println("<input name=\"new-lagerort\" type=\"text\" placeholder=\"Lagerort\" required>");
				out.println("</br>");
				out.println("<input type=\"submit\" value=\"upload\" id=\"lo_add\">");
				out.println("</form>");
				out.close();
			}
		}
		
	}
	
	private Date findDate(){
		GregorianCalendar now = new GregorianCalendar();
		DateFormat format = DateFormat.getDateInstance(DateFormat.MEDIUM);
		String date = format.format(now.getTime());
		Date datum = null;
		try {
			datum = format.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return datum;
	}

	private String getFile(HttpServletRequest request) {
		File file = null;
		String path = "";
		
		DiskFileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
		upload.setSizeMax(20000*1024);

		try{ 
			// Parse the request to get file items.
			List fileItems = upload.parseRequest(request);
			
			// Process the uploaded file items
			Iterator i = fileItems.iterator();
			while ( i.hasNext () ) 
			{
				FileItem fi = (FileItem)i.next();
				if ( !fi.isFormField () )
				{
					String fileName = fi.getName();
					path = bus.getUserThroughId(request.getSession().getId()) + "_" + fileName.substring(fileName.lastIndexOf("\\")+1);
					file = new File("C:/tomcat/" +  path );
					fi.write( file ) ;
				}
			}
		}catch(Exception ex) {
			ex.printStackTrace();
			return null;
		}
		return "/images/" + path;
	}

}
