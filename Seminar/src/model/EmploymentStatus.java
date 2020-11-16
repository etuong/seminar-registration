package model;

public enum EmploymentStatus {
	EMPLOYEE("JHU Employee"), STUDENT("JHU Student"), SPEAKER("Speaker"), OTHER("Other");

	private final String status;

	EmploymentStatus(String status) {
		this.status = status;
	}

	public String getStatus() {
		return this.status;
	}
}
