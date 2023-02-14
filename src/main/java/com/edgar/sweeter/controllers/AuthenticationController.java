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
import com.edgar.sweeter.exceptions.EmailFailedToSendException;
import com.edgar.sweeter.exceptions.IncorrectVerificationCodeException;
import com.edgar.sweeter.exceptions.UserDoesNotExistException;
import com.edgar.sweeter.models.AppUser;
import com.edgar.sweeter.models.RegistrationObject;
import com.edgar.sweeter.services.UserService;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

	@Autowired
	private UserService userSevice;
	
	
	/*  Register User and Exception handler**/

	@ExceptionHandler({ EmailAlreadyTakenException.class })
	public ResponseEntity<String> handleEmailTaken() {
		return new ResponseEntity<String>("The email provided is already in use", HttpStatus.CONFLICT);

	}

	@PostMapping("/register")
	public AppUser registerUser(@RequestBody RegistrationObject user) {
		return userSevice.RegisterUser(user);

	}
	
	/*  Update Phone Number and Exception handler**/
	
	
	@ExceptionHandler({UserDoesNotExistException.class})
	public ResponseEntity<String> handleUserDoesntExist(){
		
		return new ResponseEntity<String>("The User you are looking for doesnt exist",HttpStatus.NOT_FOUND);
		
	}
	
	
	@PutMapping("/update/phone")
	public AppUser updatePhoneNumber(@RequestBody LinkedHashMap<String, String> body) {
		
		String username =  body.get("username");
		String phone = body.get("phone");
		
		
		AppUser user = userSevice.getUserByUsername(username);
		
		user.setPhone(phone);
		
		return userSevice.updateUser(user);
		
	}
	
	/*  Email verification code creation and Exception handler **/
	
	
	
	@ExceptionHandler({EmailFailedToSendException.class})
	public ResponseEntity<String>handleFailedEmail(){	
		return new ResponseEntity<String>("Email failed to send try again in a moment", HttpStatus.INTERNAL_SERVER_ERROR);	
	}
	
	@PostMapping("/email/code")
	public ResponseEntity<String> createEmailVerification(@RequestBody LinkedHashMap<String, String> body ){
		
		userSevice.generateEmailVerification(body.get("username"));
		
		return new ResponseEntity<String>("verification code generated, email sent", HttpStatus.OK);
	}
	
	
	
	/*  Email verification and Exception handler **/
	
	
	@ExceptionHandler({IncorrectVerificationCodeException.class})
	public ResponseEntity<String> handleIncorrectCode(){		
		return new ResponseEntity<String>("The code provided does not match the users code", HttpStatus.CONFLICT);
		
	}
	 
	
	@PostMapping("/email/verify")
	public AppUser verifyEmail(@RequestBody LinkedHashMap<String, String> body ) {
		
		Long code = Long.parseLong(body.get("code"));
		
		String username = body.get("username");
		
		return userSevice.verifyEmail(username, code);
		
	}
	
	/*  update password and Exception handler **/
	@PutMapping("/update/password")
	public AppUser updatePassword(@RequestBody LinkedHashMap<String, String> body) {
		String username = body.get("username");
		String password = body.get("password");
		
		
		
		return userSevice.setPassword(username, password);
	}

	
	

}
