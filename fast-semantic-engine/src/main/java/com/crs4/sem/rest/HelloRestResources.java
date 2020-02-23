package com.crs4.sem.rest;

import java.io.IOException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.crs4.sem.model.Author;


@Path("/prova")
public class HelloRestResources {
	
	private Author author;
	
	@GET
	@Path("/hello")
	@Produces(MediaType.APPLICATION_JSON)

	public String identify(@QueryParam("text") String text) throws IOException {

		
		
	
	return author.getId();
	}

}
