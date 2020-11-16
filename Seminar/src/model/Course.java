package model;

public enum Course {
	a4("A4 - Web Services"), a1("A1 - J2EE Design Patterns"), a3("A3 - Service Oriented Architectures"), a2(
			"A2 - Enterprise Service Bus"), a6("A6 - Secure Messaging"), a5("A5 - Web Services Security");

	private final String course;

	Course(String course) {
		this.course = course;
	}

	public String getCourse() {
		return this.course;
	}
}
