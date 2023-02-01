package com.edgar.sweeter.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edgar.sweeter.exceptions.EmailAlreadyTakenException;
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
		return new ResponseEntity<String>("The Email provided is already in use", HttpStatus.CONFLICT);

	}

	@PostMapping("/register")
	public AppUser registerUser(@RequestBody RegistrationObject user) {
		return userSevice.RegisterUser(user);

	}

}
