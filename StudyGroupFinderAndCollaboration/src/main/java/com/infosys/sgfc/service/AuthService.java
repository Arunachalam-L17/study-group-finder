package com.infosys.sgfc.service;

import com.infosys.sgfc.model.Users;

public interface AuthService {

	boolean checkEmail(String email);

	Users registerUser(Users user);

	Users getUserByEmailAndPassword(String email, String password);
	
	
	
	


	

}
