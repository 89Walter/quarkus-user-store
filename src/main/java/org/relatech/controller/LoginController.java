package org.relatech.controller;

import java.net.URI;
import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.jboss.logging.Logger;
import org.relatech.model.User;
import org.relatech.repository.UserRepository;


@Path("/loggin")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class LoginController implements LoginControllerInterface {

	private Logger logger = Logger.getLogger(LoginController.class);

	@Inject
	UserRepository userRepository;

	@POST
	@Path("/signingUp")
	@Transactional
	@Override
	public Response insertUser(User user) {
		logger.info("Si sta registrando l'utente con nome = " + user.toString());
		userRepository.persist(user);
		if(userRepository.isPersistent(user)) {
			return Response.created(URI.create("/getUser/" + user.getFiscalCode())).build();
		}
		return Response.status(Status.BAD_REQUEST).build();
	}

	@GET
	@Path("/access")
	@Override
	public Response loggin(
			@Parameter(
					description = "give me email for access",
					required = false
					)
			@QueryParam("email") String email,
			@Parameter(
					description = "give me telephoneNumber for access",
					required = false
					)
			@QueryParam("telephoneNumber") String telephoneNumber,
			@Parameter(
					description = "give me password for access",
					required = true
					)
			@QueryParam("password") String password) {
		List<User> users = userRepository.queryAccess(email, telephoneNumber, password);
		if(users.isEmpty()) {
			logger.info("Accesso negato!");
			return Response.status(Status.BAD_REQUEST).build();
		}
		logger.info("Accesso eseguito con successo! Benvenuto sul nostro portale");
		return Response.ok(users).build();	
	}

	@PUT
	@Path("/updatePassword")
	@Transactional
	@Override
	public Response uP(
			@Parameter(
					description = "Email for new password",
					required = true
					)
			@QueryParam("email") String email,
			@Parameter(
					description = "new password",
					required = true
					)
			@QueryParam("nuovaPassword") String nuovaPassword) {
		logger.info("Password modificata correttamente");
		int update = userRepository.update(userRepository.queryForChangePassword(email, nuovaPassword));
		return Response.ok(update).build();
	}




}
