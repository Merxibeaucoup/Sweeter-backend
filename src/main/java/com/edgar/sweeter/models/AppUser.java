package com.edgar.sweeter.models;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="users")
public class AppUser {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="user_id")
	private Long userId;
	
	
	private String firstName;
	
	private String lastName;
	
	@Column(unique = true)
	private String email;
	
	private String phone;
	
	
	@Column(name="dob")
	private LocalDate dateOfBirth;
	
	
	@Column(unique=true)
	private String username;
	
	
	@JsonIgnore
	private String password;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(
			name="user_role_junction",
			joinColumns = {@JoinColumn(name="user_id")},
			inverseJoinColumns= {@JoinColumn(name="role_id")}
			)
	private Set<Role> authorities;
	
	private Boolean enabled;
	
	@Column(nullable = true)
	@JsonIgnore
	private Long verification;
	
	
	public AppUser() {
		
		/*to avoid assigning duplicates*/
		this.authorities = new HashSet<>();
		
		/*user cant use account until email is verified*/
		this.enabled = false;
		
	}


	public Long getUserId() {
		return userId;
	}


	public void setUserId(Long userId) {
		this.userId = userId;
	}


	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getPhone() {
		return phone;
	}


	public void setPhone(String phone) {
		this.phone = phone;
	}


	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}


	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public Set<Role> getAuthorities() {
		return authorities;
	}


	public void setAuthorities(Set<Role> authorities) {
		this.authorities = authorities;
	}


	public Boolean getEnabled() {
		return enabled;
	}


	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}


	public Long getVerification() {
		return verification;
	}


	public void setVerification(Long verification) {
		this.verification = verification;
	}


	@Override
	public String toString() {
		return "AppUser [userId=" + userId + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ ", phone=" + phone + ", dateOfBirth=" + dateOfBirth + ", username=" + username + ", password="
				+ password + ", authorities=" + authorities + ", enabled=" + enabled + ", verification=" + verification
				+ "]";
	}


	@Override
	public int hashCode() {
		return Objects.hash(authorities, dateOfBirth, email, enabled, firstName, lastName, password, phone, userId,
				username, verification);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AppUser other = (AppUser) obj;
		return Objects.equals(authorities, other.authorities) && Objects.equals(dateOfBirth, other.dateOfBirth)
				&& Objects.equals(email, other.email) && Objects.equals(enabled, other.enabled)
				&& Objects.equals(firstName, other.firstName) && Objects.equals(lastName, other.lastName)
				&& Objects.equals(password, other.password) && Objects.equals(phone, other.phone)
				&& Objects.equals(userId, other.userId) && Objects.equals(username, other.username)
				&& Objects.equals(verification, other.verification);
	}


	
	

}
