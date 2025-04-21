package com.jobdhundo.Job.Dhundo.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "users", schema = "jobdhundo")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY )
	private Long id;
	
	private String fullName;
	private String email;
	private String password;
	private Boolean isJobSeeker; //True for job seeker, and false for recruiter
	
	@Transient 
	private String confirmPassword; //This field will not be saved in the database
	

	   
    public User() {}

	public User( String fullName, String email, String password, Boolean isJobSeeker) {
		this.fullName = fullName;
		this.email = email;
		this.password = password;
		this.isJobSeeker = isJobSeeker;
	}
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Boolean getIsJobSeeker() {
		return isJobSeeker;
	}

	public void setIsJobSeeker(Boolean isJobSeeker) {
		this.isJobSeeker = isJobSeeker;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
	
	



}

