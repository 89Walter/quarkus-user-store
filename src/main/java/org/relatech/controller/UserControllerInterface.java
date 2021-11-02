package org.relatech.controller;

import javax.transaction.Transactional;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

public interface UserControllerInterface {
	
	@GET
	@Path("/bookz")
	public Response getBooks();
	
	@POST
	@Path("/chooseBook/{userId}/{bookId}")
	public Response chooseBook(@PathParam("userId") Long userId, @PathParam("bookId") Long bookId);
	
	@GET
	@Path("/getAll")
	public Response getAllUsers();
	
	@GET
	@Path("/getUser")
	public Response getByFiscalCode(@QueryParam("fiscalCode") String fiscalCode);
	
	@DELETE
	@Path("/deleteUsers")
	@Transactional
	public Response deleteUserById(@QueryParam("id") Long id);

}
