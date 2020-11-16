package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.EmploymentStatus;
import model.SeminarModel;

@WebServlet("/UserController")
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public UserController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String url = "/seminar.jsp";

		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String status = request.getParameter("employment");

		EmploymentStatus employmentStatus = EmploymentStatus.valueOf(status.toUpperCase());

		// User user = new User(name, email, employmentStatus.getStatus());
		//
		// if (UserDB.emailExists(email)) {
		// UserDB.update(user);
		// } else {
		// UserDB.insert(user);
		// }

		SeminarModel seminar = new SeminarModel();
		seminar.setName(name);
		seminar.setEmail(email);
		seminar.setEmploymentStatus(employmentStatus.getStatus());
		HttpSession session = request.getSession();
		session.setAttribute("seminar", seminar);

		RequestDispatcher dispatcher = this.getServletConfig().getServletContext().getRequestDispatcher(url);
		dispatcher.forward(request, response);
	}
}
