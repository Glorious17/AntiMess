package com.antimess.resources;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/search")
public class searchService {
	
	@GET
	@Produces("text/plain")
	public String getNames(){
		return "HelloWorld";
	}
}
