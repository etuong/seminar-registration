package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class SeminarModel implements Serializable {
	private static final long serialVersionUID = 1L;

	private String name;
	private String email;
	private String employmentStatus;
	private List<String> courses;
	private double courseFee;
	private boolean isHotel;
	private boolean isParking;

	public SeminarModel() {
		this.name = "";
		this.email = "";
		this.employmentStatus = "";
		this.courses = new ArrayList<>();
		this.courseFee = 0.0;
		this.isHotel = false;
		this.isParking = false;
	}

	public SeminarModel(String name, String email, String employmentStatus, List<String> courses, double courseFee,
			boolean isHotel, boolean isParking) {
		this.name = name;
		this.email = email;
		this.employmentStatus = employmentStatus;
		this.courses = courses;
		this.courseFee = courseFee;
		this.isHotel = isHotel;
		this.isParking = isParking;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean getIsHotel() {
		return this.isHotel;
	}

	public void setIsHotel(boolean isHotel) {
		this.isHotel = isHotel;
	}

	public boolean getIsParking() {
		return this.isParking;
	}

	public void setIsParking(boolean isParking) {
		this.isParking = isParking;
	}

	public String getEmploymentStatus() {
		return this.employmentStatus;
	}

	public void setEmploymentStatus(String employmentStatus) {
		this.employmentStatus = employmentStatus;
	}

	public String getCourseFee() {
		return String.format("%.2f", this.courseFee);
	}

	public void setCourseFee(double courseFee) {
		this.courseFee = courseFee;
	}

	public String getTotalFee() {
		double totalFee = this.courseFee * this.courses.size();
		if (this.isHotel) {
			totalFee += Constants.HOTEL_FEE;
		} else if (!this.isHotel && this.isParking) {
			totalFee += Constants.PARKING_FEE_ONLY;
		}

		return String.format("%.2f", totalFee);
	}

	public void removeCourse(String courseCode) {
		this.courses.remove(courseCode);
	}

	public void setCourses(String[] courses) {
		this.courses = new ArrayList(Arrays.asList(courses));
	}

	public Map<String, String> getCourses() {
		return this.courses.stream()
				.collect(Collectors.toMap(course -> course, course -> Course.valueOf(course).getCourse()));
	}

	public boolean isCourseSelected(String course) {
		return this.courses.contains(course);
	}
}
