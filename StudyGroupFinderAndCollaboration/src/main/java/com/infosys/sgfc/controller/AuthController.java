package com.infosys.sgfc.controller;

import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.infosys.sgfc.model.Users;
import com.infosys.sgfc.security.JwtUtil;
import com.infosys.sgfc.service.AuthService;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:3000")
public class AuthController {
	
	@Autowired
	private AuthService authService;
	
	@Autowired
	private JwtUtil jwtUtil; 

	
	
	@PostMapping("/register")
	public ResponseEntity<?> registerUser(@RequestBody Users user) {

        boolean exists = authService.checkEmail(user.getEmail());

        if (exists) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of(
                            "status", "warning",
                            "message", "⚠️ Email already exists!"
                    ));
        }

        Users savedUser = authService.registerUser(user);

        return ResponseEntity.ok(Map.of(
                "status", "success",
                "message", "✅ Registration successful! Please login."
        ));
    }
	
	// LOGIN 
	
	@PostMapping("/login")
	public ResponseEntity<?> loginUser(@RequestBody Map<String, String> request) {

	    String email = request.get("email");
	    String password = request.get("password");

	    Users user = authService.getUserByEmailAndPassword(email, password);

	    if (user != null) {

	        String token = jwtUtil.generateToken(user.getEmail());

	        return ResponseEntity.ok(Map.of(
	                "status", "success",
	                "message", "✅ Login successful! Welcome " + user.getFullName(),
	                "email", user.getEmail(),
	                "token", token
	        ));
	    }

	    return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
	            .body(Map.of(
	                    "status", "error",
	                    "message", "❌ Invalid email or password!"
	            ));
	}

    }


