package com.jobdhundo.Job.Dhundo.controllers;

import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jobdhundo.Job.Dhundo.entities.User;
import com.jobdhundo.Job.Dhundo.services.UserService;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = {"http://localhost:5173", "https://job-dhundo.netlify.app"}, allowCredentials = "true")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/signup")
	public ResponseEntity<?> signup(@RequestBody User user) {
		try {
			User registeredUser = userService.registerUser(
					user.getFullName(),
					user.getEmail(),
					user.getPassword(),
					user.getConfirmPassword(),
					user.getIsJobSeeker()
					
					);
			return ResponseEntity.ok("User Registered successfully! \n You can Login.");
		}catch(RuntimeException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody User user, HttpSession session) {
		Optional<User> loggedInUser = userService.authenticateUser(user.getEmail(), user.getPassword());
		
		if(loggedInUser.isPresent()) {
			User authenticatedUser = loggedInUser.get();
			session.setAttribute("user", authenticatedUser);
			
			return ResponseEntity.ok(authenticatedUser.getIsJobSeeker());
		}
		
		return ResponseEntity.badRequest().body("Invalid email or password!");
	}
	
	
	
	@PostMapping("/logout")
	public ResponseEntity<String> logout(HttpSession session) {
		session.invalidate();
		return ResponseEntity.ok("Logout successful!");
	}
	
	@PostMapping("/check-email")
	public ResponseEntity<?> checkEmail(@RequestBody Map<String, String> payload) {
	    String email = payload.get("email");

	    Optional<User> userOpt = userService.findUserByEmail(email);
	    if (userOpt.isEmpty()) {
	        return ResponseEntity.badRequest().body("Email not found.");
	    }

	    return ResponseEntity.ok("Email verified.");
	}
	
	
	@PostMapping("/reset-password")
	public ResponseEntity<?> resetPassword(@RequestBody Map<String, String> payload) {
	    String email = payload.get("email");
	    String newPassword = payload.get("newPassword");

	    Optional<User> userOpt = userService.findUserByEmail(email);
	    if (userOpt.isEmpty()) {
	        return ResponseEntity.badRequest().body("Email not found.");
	    }

	    User user = userOpt.get();
	    user.setPassword(newPassword);
	    userService.saveUser(user);

	    return ResponseEntity.ok("Password updated successfully.");
	}


}

