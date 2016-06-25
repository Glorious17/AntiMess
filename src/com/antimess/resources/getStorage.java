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

@Path("/getStorage")
public class getStorage {
	
	private AntiMessBusiness bus;
	
	public getStorage(){
		bus = new AntiMessBusiness();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String getItems(@QueryParam("id") String id){
		String output = "[{";
		ArrayList <String> data = bus.getLagerort(bus.getUserThroughId(id));
		for(int i = 0; i < data.size(); i++){
			if(i < (data.size()-1)){
				output += "\"Lagerort\": \"" + data.get(i) + "\"}, {";
			}else{
				output += "\"Lagerort\": \"" + data.get(i) + "\"}]";
			}
		}
		return output;
	}
}
