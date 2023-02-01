package com.edgar.sweeter;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.edgar.sweeter.models.Role;
import com.edgar.sweeter.repositories.RoleRepository;
import com.edgar.sweeter.services.UserService;

@SpringBootApplication
public class SweeterBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(SweeterBackendApplication.class, args);
	}

	@Bean
	CommandLineRunner run(RoleRepository roleRepo, UserService userService) {

		return args -> {
			roleRepo.save(new Role((long) 1, "USER"));
/*			
			AppUser u= new AppUser();
			
			u.setFirstName("uko");
			u.setLastName("codes");
			userService.RegisterUser(u);
			*/
			
		};
	}

}
