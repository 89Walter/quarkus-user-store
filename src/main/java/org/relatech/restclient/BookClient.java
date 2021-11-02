package org.relatech.restclient;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

//Un client REST che serve a fare chiamate REST all'esterno
@Path("/book")
@RegisterRestClient
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public interface BookClient {
	
	@GET
	Response getBooks();
	
	@POST
	@Path("{bookId}")
	Response chooseBook(@PathParam("bookId") Long bookId, BookUserDTO userDTO);
}

