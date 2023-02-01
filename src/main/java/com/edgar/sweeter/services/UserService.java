package com.edgar.sweeter.services;

import java.util.Random;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edgar.sweeter.exceptions.EmailAlreadyTakenException;
import com.edgar.sweeter.models.AppUser;
import com.edgar.sweeter.models.RegistrationObject;
import com.edgar.sweeter.models.Role;
import com.edgar.sweeter.repositories.RoleRepository;
import com.edgar.sweeter.repositories.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private  UserRepository userRepo;
	
	
	@Autowired
	private  RoleRepository roleRepo;
	
	public AppUser RegisterUser(RegistrationObject ro) {
		
		AppUser user = new AppUser();
		
		/* set user data and assign them to RO*/
		
		user.setFirstName(ro.getFirstName());
		user.setLastName(ro.getLastName());
		user.setEmail(ro.getEmail());
		user.setDateOfBirth(ro.getDob());
		
		/* concat firstname(1 letter) and lastname , then add random numbers to then end */
		String name = user
						.getFirstName().substring(0,1).concat(user.getLastName());
						
		
				
		boolean nameTaken = true;
		
		String tempName= "";
		
		while(nameTaken) {
			tempName = generateUsername(name);
			
			
			/*if(userRepo.findByUsername(tempName).isEmpty()) ...same as*/
			if(!userRepo.findByUsername(tempName).isPresent()) {
				nameTaken = false;
						
			}
			
		}
		
		user.setUsername(tempName);
		
		
		/* Set default roles as USER then assign to all users during registration*/
		Set<Role> roles = user.getAuthorities();
		roles.add(roleRepo.findByAuthority("USER").get());		
		user.setAuthorities(roles);
		
		try {
			return userRepo.save(user);
		}
		catch(Exception e) {
			throw new EmailAlreadyTakenException();
		}
		
	}
	
	private String generateUsername(String name) {
		
		long generatedNumber = (long)Math.floor(Math.random() * 1_000_000_000);
	return	  name + generatedNumber;
	
	}
	
/*	
 * pickWord() +
 * 
 * Might make these usernames
 * 
 * private String[] words = {"abandoned","able","absolute","adorable","adventurous","academic","acceptable","acclaimed","accomplished",
			"accurate","aching","acidic","acrobatic","active","actual","adept","admirable","admired","adolescent",
			"adorable","adored","advanced","afraid","affectionate","aged","aggravating","aggressive","agile","agitated",
			"agonizing","agreeable","ajar","alarmed","alarming","alert","alienated","alive","all","altruistic","amazing",
			"ambitious","ample","amused","amusing","anchored","ancient","angelic","angry","anguished","animated","annual",
			"another","antique","anxious","any","apprehensive","appropriate","apt","arctic","arid","aromatic","artistic",
			"ashamed","assured","astonishing","athletic","attached","attentive","attractive","austere","authentic",
			"authorized","automatic","avaricious","average","aware","awesome","awful","awkward","babyish","bad","back",
			"baggy","bare","barren","basic","beautiful","belated","beloved","beneficial","better","best","bewitched","big",
			"bighearted","biodegradable","bitesized","bitter","black"};
	
	Random rand = new Random();
	
	private String pickWord() {
		return words[rand.nextInt(words.length)].toLowerCase();
	}
	
	*/

}
