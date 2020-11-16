package controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.EmploymentStatus;
import model.SeminarModel;

@WebServlet("/SeminarController")
public class SeminarController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Map<String, Double> courseFee = new HashMap<>();

	static {
		courseFee.put(EmploymentStatus.EMPLOYEE.getStatus(), 850.00);
		courseFee.put(EmploymentStatus.SPEAKER.getStatus(), 0.00);
		courseFee.put(EmploymentStatus.STUDENT.getStatus(), 1000.00);
		courseFee.put(EmploymentStatus.OTHER.getStatus(), 1350.00);
	}

	public SeminarController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		SeminarModel seminar = (SeminarModel) session.getAttribute("seminar");

		String url = "/index.jsp";

		String action = request.getParameter("action");
		if ("edit".equals(action)) {
			url = "/seminar.jsp";
		} else if ("remove".equals(action)) {
			String courseCode = request.getParameter("courseCode");
			seminar.removeCourse(courseCode);
			session.setAttribute("seminar", seminar);
			request.setAttribute("seminar", seminar);
			url = "/results.jsp";
		} else if ("compute".equals(action)) {
			String status = seminar.getEmploymentStatus();
			String[] courses = request.getParameterValues("courses");

			double costPerCourse = courseFee.get(status);

			boolean isHotel = isBoxChecked(request.getParameter("hotel"));
			boolean isParking = isBoxChecked(request.getParameter("parking"));

			seminar.setCourses(courses);
			seminar.setCourseFee(costPerCourse);
			seminar.setIsHotel(isHotel);
			seminar.setIsParking(isParking);

//			User user = new User(seminar.getName(), seminar.getEmail(), seminar.getEmploymentStatus());
//			if (UserDB.emailExists(seminar.getEmail())) {
//				UserDB.update(user);
//			} else {
//				UserDB.insert(user);
//			}
//
//			Integer userId = UserDB.getUserId(seminar.getEmail());
//			Accommodation accommodation = new Accommodation(seminar.getIsHotel(), seminar.getIsParking(), userId);
//			if (AccommodationDB.userIdExists(userId)) {
//				AccommodationDB.update(accommodation);
//			} else {
//				AccommodationDB.insert(accommodation);
//			}

			url = "/results.jsp";
		}

		session.setAttribute("seminar", seminar);
		RequestDispatcher dispatcher = this.getServletConfig().getServletContext().getRequestDispatcher(url);
		dispatcher.forward(request, response);
	}

	private boolean isBoxChecked(String box) {
		return box != null && box.toLowerCase().equals("on");
	}
}
