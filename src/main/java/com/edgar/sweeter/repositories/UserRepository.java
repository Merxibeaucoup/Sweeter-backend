package com.edgar.sweeter.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.edgar.sweeter.models.AppUser;

@Repository
public interface UserRepository  extends JpaRepository<AppUser, Long>{
	
	Optional<AppUser> findByUsername(String username);

}
