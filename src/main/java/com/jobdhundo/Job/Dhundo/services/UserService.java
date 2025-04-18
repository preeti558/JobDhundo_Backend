package com.jobdhundo.Job.Dhundo.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jobdhundo.Job.Dhundo.entities.User;
import com.jobdhundo.Job.Dhundo.repositories.UserRepo;



@Service
public class UserService {
	
	@Autowired
	private UserRepo userRepo;
	
	public User registerUser(String fullName, String email, String password, String confirmPassword, Boolean isJobSeeker){
		if(!password.equals(confirmPassword)) {
			throw new RuntimeException("Passwords do not match!");
		}
		
		if(userRepo.findByEmail(email).isPresent()) {
			throw new RuntimeException("Email is already registered!");
		}
		
		User user = new User(fullName, email, password, isJobSeeker);
		return userRepo.save(user);
		
	}
	
	public Optional<User> authenticateUser(String email, String password){
		Optional<User> user = userRepo.findByEmail(email);
		
		if(user.isPresent() && user.get().getPassword().equals(password)) {
			return user;
		}
		return Optional.empty();
	}
	
	public Optional<User> findUserByEmail(String email) {
	    return userRepo.findByEmail(email);
	}

	public void saveUser(User user) {
	    userRepo.save(user);
	}

}

