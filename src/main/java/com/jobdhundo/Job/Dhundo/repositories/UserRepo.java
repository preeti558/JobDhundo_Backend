package com.jobdhundo.Job.Dhundo.repositories;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.jobdhundo.Job.Dhundo.entities.User;


public interface UserRepo extends JpaRepository<User, Long> {
	
	Optional<User> findByEmail(String email);

}

