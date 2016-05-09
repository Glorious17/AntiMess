package com.antimess.resources;

import java.io.InputStream;

import javax.servlet.ServletContext;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/register")
public class RegisterService {
	
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String sayPlainTextStart(){
		return "<html> <title> Test </title> <body><form method='post'><h3>Registrieren</h3><input name='email' type='email' placeholder='E-Mail' required><input name='password' type='password' placeholder='Passwort' required><input type='submit' value='Senden' id='ud_send'></form></body> </html>";
	}
	
	/*@GET
	@Produces(MediaType.TEXT_HTML)
	public InputStream getRegisterHTML(){
		InputStream is = getClass().getClassLoader().getResourceAsStream("../../WebContent/reg.html");
		System.out.println("" + is);
		return is;
	}*/
	
	@POST
	@Produces(MediaType.TEXT_HTML)
	public String register(@FormParam("email") String email, @FormParam("password") String password){
		AntiMessBusiness bus = new AntiMessBusiness();
		String success;
		if(bus.registrieren(email, password)){
			success = "erfolgreich";
		}else{
			success = "gescheitert";
		}
		return "<html> <title> Test </title> <body> <h1>" + success + "</h1> </body> </html>";
	}
}
