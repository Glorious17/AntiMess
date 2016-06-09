package com.antimess.resources;

import javax.ws.rs.*;

@Path("/search")
public class searchService {
	
	@GET
	@Produces("text/plain")
	public String getNames(){
		return "HelloWorld";
	}
}
