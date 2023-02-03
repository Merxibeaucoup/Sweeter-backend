package com.edgar.sweeter.controllers;

import java.util.LinkedHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edgar.sweeter.exceptions.EmailAlreadyTakenException;
import com.edgar.sweeter.exceptions.UserDoesNotExistException;
import com.edgar.sweeter.models.AppUser;
import com.edgar.sweeter.models.RegistrationObject;
import com.edgar.sweeter.services.UserService;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

	@Autowired
	private UserService userSevice;

	@ExceptionHandler({ EmailAlreadyTakenException.class })
	public ResponseEntity<String> handleEmailTaken() {
		return new ResponseEntity<String>("The email provided is already in use", HttpStatus.CONFLICT);

	}

	@PostMapping("/register")
	public AppUser registerUser(@RequestBody RegistrationObject user) {
		return userSevice.RegisterUser(user);

	}
	
	
	@ExceptionHandler({UserDoesNotExistException.class})
	public ResponseEntity<String> handleUserDoesntExist(){
		
		return new ResponseEntity<String>("The User you are looking for doesnt exist",HttpStatus.NOT_FOUND);
		
	}
	/*
	 * 
	 * */
	
	@PutMapping("/update/phone")
	public AppUser updatePhoneNumber(@RequestBody LinkedHashMap<String, String> body) {
		
		String username =  body.get("username");
		String phone = body.get("phone");
		
		
		AppUser user = userSevice.getUserByUsername(username);
		
		user.setPhone(phone);
		
		return userSevice.updateUser(user);
	}
	
	@PostMapping("/email/code")
	public ResponseEntity<String> createEmailVerification(@RequestBody LinkedHashMap<String, String> body ){
		
		userSevice.generateEmailVerification(body.get("username"));
		
		return new ResponseEntity<String>("verification code generated, email sent", HttpStatus.OK);
	}

}
