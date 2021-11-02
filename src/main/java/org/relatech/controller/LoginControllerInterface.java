package org.relatech.controller;

import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import org.relatech.model.User;

public interface LoginControllerInterface {

	public Response insertUser(
			User user);

	public Response loggin(
			@QueryParam("email") String email, 
			@QueryParam("telephoneNumber") String telephoneNumber, 
			@QueryParam("password") String password);

	Response uP(
			@QueryParam("email") String email, 
			@QueryParam("nuovaPassword") String nuovaPassword);

}
