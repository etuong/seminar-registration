package model;

import java.io.Serializable;

public class User implements Serializable {

	private String email;
	private String name;
	private String employmentStatus;

	public User() {
		email = "";
		name = "";
		employmentStatus = "";
	}

	public User(String name, String email, String employmentStatus) {
		this.name = name;
		this.email = email;
		this.employmentStatus = employmentStatus;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmploymentStatus() {
		return employmentStatus;
	}

	public void setEmploymentStatus(String employmentStatus) {
		this.employmentStatus = employmentStatus;
	}
}