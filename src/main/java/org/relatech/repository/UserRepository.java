package org.relatech.repository;

import java.util.List;
import java.util.Optional;

import javax.enterprise.context.ApplicationScoped;
import org.relatech.model.User;


import io.quarkus.hibernate.orm.panache.PanacheRepository;

@ApplicationScoped
public class UserRepository implements PanacheRepository<User>{

	public String queryForChangePassword(String email, String nuovaPassword) {
		String query = "UPDATE User SET password = " + "'"+nuovaPassword+"'" + " WHERE email = " + "'"+email+"'";
		return query;
	}

	public List<User> queryAccess(String email, String telephoneNumber, String password) {
		if(email == null) {
			return list("FROM User WHERE telephoneNumber = " + "'"+telephoneNumber+"'" + " AND password = " + "'"+password+"'");
		}else 
			return list("FROM User WHERE email = " + "'"+email+"'" + " AND password = " + "'"+password+"'");
	}

	public Optional<User> getUserByFiscalCode(String fiscalCode) {
		return find("FROM User WHERE fiscalCode = " + "'"+fiscalCode+"'").singleResultOptional();
	}
	

}
