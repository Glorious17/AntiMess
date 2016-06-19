package com.antimess.resources;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path("/search")
public class searchService {
	
	private AntiMessBusiness bus;
	
	public searchService(){
		bus = new AntiMessBusiness();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String getItems(@QueryParam("id") String id){
		String name = bus.getUserThroughId(id);
		System.out.println("name: " + name + "; id: " + id);
		if(bus.checkAcc(name, id)){
			String output = "[";
			ArrayList<String> items = bus.getItems(name);
			for(int i = 0; i < items.size(); i++){
				
				if(i < (items.size()-1))
					output += items.get(i) + ",";
				else
					output += items.get(i);
				
			}
			output += "]";
			System.out.println(output);
			return output;
		}
		return null;
	}
}
