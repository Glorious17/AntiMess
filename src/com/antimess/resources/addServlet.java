package com.antimess.resources;

import java.io.File;
import java.io.IOException;
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

public class addServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private AntiMessBusiness bus;
	private String url;

    public addServlet() {
        super();
        bus = new AntiMessBusiness();
    }
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(ServletFileUpload.isMultipartContent(request)){
			url = getFile(request);
			request.getSession().setAttribute("url", url);
			response.sendRedirect("./add1.html");
		}else{
			String name = request.getParameter("gegenstand");
			String lagerort = request.getParameter("lagerort");
			String keyword = request.getParameter("keyword");
			bus.addItem(name, new java.sql.Date(findDate().getTime()), (String) request.getSession().getAttribute("url"), lagerort, bus.getUserThroughId(request.getSession().getId()));
			response.sendRedirect("./home");
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
					path = "C:/tomcat/" + bus.getUserThroughId(request.getSession().getId())
					+ "/" + fileName.substring(fileName.lastIndexOf("\\")+1);
					file = new File( path );
					fi.write( file ) ;
				}
			}
		}catch(Exception ex) {
			ex.printStackTrace();
			return null;
		}
		return "./././././././tomcat/" + bus.getUserThroughId(request.getSession().getId())
					+ "/" + path.substring(path.lastIndexOf("\\")+1);
	}

}
