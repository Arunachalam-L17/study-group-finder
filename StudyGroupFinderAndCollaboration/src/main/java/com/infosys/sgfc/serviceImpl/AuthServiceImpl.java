package com.infosys.sgfc.serviceImpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.infosys.sgfc.model.Users;
import com.infosys.sgfc.repository.AuthRepository;
import com.infosys.sgfc.service.AuthService;

@Service
public class AuthServiceImpl implements AuthService {
	
	@Autowired
	private AuthRepository authRepository;
	
    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();


	@Override
	public boolean checkEmail(String email) {
		return authRepository.findByEmail(email).isPresent();
	}

	@Override
	public Users registerUser(Users user) {
		
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        
		return authRepository.save(user);
	}

	@Override
	public Users getUserByEmailAndPassword(String email, String password) {
		
		Optional<Users> optionalUser = authRepository.findByEmail(email);

        if (optionalUser.isPresent()) {

            Users user = optionalUser.get();

            // Compare encrypted password
            if (passwordEncoder.matches(password, user.getPassword())) {
                return user;
            }
        }		return null;
	}

	
}
