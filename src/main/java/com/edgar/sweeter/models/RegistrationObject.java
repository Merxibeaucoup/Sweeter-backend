package com.edgar.sweeter.models;

import java.time.LocalDate;
import java.util.Objects;

public class RegistrationObject {

	private String firstName;

	private String lastName;

	private String email;

	private LocalDate dob;

	public RegistrationObject() {

	}

	public RegistrationObject(String firstName, String lastName, String email, LocalDate dob) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.dob = dob;
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

	public LocalDate getDob() {
		return dob;
	}

	public void setDob(LocalDate dob) {
		this.dob = dob;
	}

	@Override
	public String toString() {
		return "RegistrationObject [firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + ", dob="
				+ dob + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(dob, email, firstName, lastName);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RegistrationObject other = (RegistrationObject) obj;
		return Objects.equals(dob, other.dob) && Objects.equals(email, other.email)
				&& Objects.equals(firstName, other.firstName) && Objects.equals(lastName, other.lastName);
	}

}
