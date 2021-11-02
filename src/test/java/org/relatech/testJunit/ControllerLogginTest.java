package org.relatech.testJunit;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.core.Response;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.relatech.controller.LoginController;
import org.relatech.model.User;
import org.relatech.repository.UserRepository;

import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectMock;

@QuarkusTest
public class ControllerLogginTest {
	
	@InjectMock
	UserRepository userRepository;
	
	@Inject
	LoginController controllerLoggin;
	
	private User user;
	
	@BeforeEach
	void setUp() {
		user = new User();
		user.setEmail("walterdemaio89@gmail.com");
		user.setFiscalCode("DMEWTR89");
		user.setId(1L);
		user.setName("Walter");
		user.setPassword("salutone");
		user.setSurname("De Maio");
		user.setTelephoneNumber("3249008483");
		user.setDateOfBirth(Date.valueOf("1989-04-16").toLocalDate());
	}
	
	@Test
	void insertUserTest() {
		Response response = controllerLoggin.insertUser(user);
		assertNotNull(response);
	}
	
	@Test
	void logginTest() {
		List<User> users = new ArrayList<>();
		users.add(user);
		Mockito.when(userRepository.queryAccess(user.getEmail(), user.getTelephoneNumber(), user.getPassword())).thenReturn(users);
		Response response = controllerLoggin.loggin(user.getEmail(), user.getTelephoneNumber(), user.getPassword());
		assertNotNull(response);
	}
	
	@Test
	void userNullTest() {
		List<User> users = new ArrayList<>();
		Mockito.when(userRepository.queryAccess(null, null, null)).thenReturn(users);
		Response response = controllerLoggin.loggin(null, null, null);
		assertNotNull(response);
	}
	
	@Test
	void uPtest() {
		List<User> users = new ArrayList<>();
		users.add(user);
		Mockito.when(userRepository.update(userRepository.queryForChangePassword(user.getEmail(), user.getPassword()))).thenReturn(1);
		Response response = controllerLoggin.uP(user.getEmail(), user.getPassword());
		assertNotNull(response);
	}

}
