package org.relatech.controller;

import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.logging.Logger;
import org.relatech.model.User;
import org.relatech.repository.UserRepository;
import org.relatech.restclient.BookClient;
import org.relatech.restclient.BookUserDTO;

@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserController implements UserControllerInterface {

	private Logger logger = Logger.getLogger(UserController.class);

	@Inject
	UserRepository userRepository;

	@Inject
	@RestClient
	BookClient bookClient;

	@GET
	@Path("/bookz")
	@Override
	public Response getBooks() {
		return bookClient.getBooks();
	}

	@POST
	@Path("/chooseBook/{userId}/{bookId}")
	@Override
	public Response chooseBook(@PathParam("userId") Long userId, @PathParam("bookId") Long bookId) {
		return userRepository
				.findByIdOptional(userId)
				.map(user -> bookClient.chooseBook(bookId, new BookUserDTO(user.getFiscalCode())))
				.orElse(Response.status(Status.NOT_FOUND).build());
	}

	@GET
	@Path("/getAll")
	@Override
	public Response getAllUsers() {
		List<User> users = userRepository.listAll();
		if(users.isEmpty()) {
			logger.info("Lista utenti vuota");
		}
		return Response.ok(users).build();
	}

	@GET
	@Path("/getUser")
	@Override
	public Response getByFiscalCode(
			@Parameter(
					description = "give me user with fiscal code",
					required = false
					)
			@QueryParam("fiscalCode") String fiscalCode) {
		if(fiscalCode != null) {
			logger.info("Utente recuperato con successo!");
			return userRepository
					.getUserByFiscalCode(fiscalCode)
					.map(user -> Response.ok(user).build())
					.orElse(Response.status(Status.NOT_FOUND).build());				
		}
		logger.info("Inserire un utente!");
		return Response.status(Status.BAD_REQUEST).build();
	}

	@DELETE
	@Path("/deleteUsers")
	@Transactional
	@Override
	public Response deleteUserById(
			@Parameter(
					description = "id for delete user",
					required = true
					)
			@QueryParam("id") Long id) {
		boolean deleted = userRepository.deleteById(id);
		return deleted ? Response.noContent().build() :
			Response.status(Status.NOT_FOUND).build();			
	}

}
