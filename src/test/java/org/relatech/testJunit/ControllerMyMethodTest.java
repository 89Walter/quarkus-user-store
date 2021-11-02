package org.relatech.testJunit;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.core.Response;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.relatech.controller.UserController;
import org.relatech.model.User;
import org.relatech.repository.UserRepository;

import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectMock;

@QuarkusTest
public class ControllerMyMethodTest {
	
	@InjectMock
	UserRepository userRepository;
	
	@Inject
	UserController controllerMyMethod;
	
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
	}
	
	@Test
	void getAllListNoEmptyTest() {
		List<User> users = new ArrayList<>();
		users.add(user);
		Mockito.when(userRepository.listAll()).thenReturn(users);
		Response response = controllerMyMethod.getAllUsers();
		assertNotNull(response);
	}
	
	@Test
	void getAllListEmptyTest() {
		List<User> users = new ArrayList<>();
		Mockito.when(userRepository.listAll()).thenReturn(users);
		Response response = controllerMyMethod.getAllUsers();
		assertNotNull(response);
	}
	
//	@Test
//	void getByFiscalCodeTest() {
//		List<User> users = new ArrayList<>();
//		users.add(user);
//		Mockito.when(userRepository.getUserByFiscalCode(user.getFiscalCode())).thenReturn(users);
//		Response response = controllerMyMethod.getByFiscalCode(user.getFiscalCode());
//		assertNotNull(response);
//	}
//	
//	@Test
//	void fiscalCodeNullTest() {
//		List<User> users = new ArrayList<>();
//		users.add(user);
//		Mockito.when(userRepository.getUserByFiscalCode(null)).thenReturn(users);
//		Response response = controllerMyMethod.getByFiscalCode(null);
//		assertNotNull(response);
//	}
	
	@Test
	void deleteUserByIdTest() {
		List<User> users = new ArrayList<>();
		users.add(user);
		Mockito.when(userRepository.deleteById(user.getId())).thenReturn(true);
		Response response = controllerMyMethod.deleteUserById(user.getId());
		assertNotNull(response);
	}
	
	@Test
	void idNotPresentTest() {
		List<User> users = new ArrayList<>();
		users.add(user);
		Mockito.when(userRepository.deleteById(user.getId())).thenReturn(false);
		Response response = controllerMyMethod.deleteUserById(user.getId());
		assertNotNull(response);
	}
	
	
	
	




}
