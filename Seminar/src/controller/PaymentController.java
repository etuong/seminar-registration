package controller;

import java.io.IOException;

import javax.mail.MessagingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.SeminarModel;
import util.CharArrayWriterResponse;
import util.MailUtil;

@WebServlet("/PaymentController")
public class PaymentController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public PaymentController() {
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

		String url = "/registration.jsp";

		String action = request.getParameter("action");
		if ("over".equals(action)) {
			seminar = new SeminarModel();
			url = "/index.jsp";
		} else {

		}

		session.setAttribute("seminar", seminar);
		RequestDispatcher dispatcher = this.getServletConfig().getServletContext().getRequestDispatcher(url);
		dispatcher.forward(request, response);
	}
}
