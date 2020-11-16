package controller;

import java.io.IOException;
import java.io.OutputStream;

import javax.mail.MessagingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.xhtmlrenderer.pdf.ITextRenderer;

import model.SeminarModel;
import util.CharArrayWriterResponse;
import util.MailUtil;

@WebServlet("/RegistrationController")
public class RegistrationController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public RegistrationController() {
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
		if ("pdf".equals(action)) {
			// Custom response write: writes the processed JSP to an HTML String
			CharArrayWriterResponse customResponse = new CharArrayWriterResponse(response);
			request.getRequestDispatcher("./pdf.jsp").forward(request, customResponse);
			String html = customResponse.getOutput();
			byte[] data = html.getBytes();

			try {
				Document document = Jsoup.parse(html);
				document.outputSettings().syntax(Document.OutputSettings.Syntax.xml);
				ITextRenderer renderer = new ITextRenderer();
				renderer.setDocumentFromString(document.html());
				renderer.layout();

				// Stream the generated PDF to the client
				OutputStream out = response.getOutputStream();
				response.setContentType("application/pdf");
				response.setHeader("Content-disposition", "attachment; filename=Johns_Hopkins_Seminar.pdf");

				renderer.createPDF(out);
				out.flush();
				out.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if ("excel".equals(action)) {
			// Create workbook and sheet for spreadsheet
			Workbook workbook = new HSSFWorkbook();
			Sheet sheet = workbook.createSheet("Seminar Summary");

			Font font = workbook.createFont();
			font.setBold(true);
			font.setItalic(false);
			CellStyle style = workbook.createCellStyle();
			style.setFont(font);

			int i = 0;
			Row row = sheet.createRow(i++);
			Cell cell1 = row.createCell(0);
			cell1.setCellValue("Your Courses");
			cell1.setCellStyle(style);
			Cell cell2 = row.createCell(1);
			cell2.setCellValue("Cost");
			cell2.setCellStyle(style);

			for (String course : seminar.getCourses().values()) {
				// Create the row and store data in its cells
				row = sheet.createRow(i++);
				row.createCell(0).setCellValue(course);
				row.createCell(1).setCellValue("$" + seminar.getCourseFee());
			}
			if (seminar.getIsHotel()) {
				row = sheet.createRow(i++);
				row.createCell(0).setCellValue("Hotel Accommodation");
				row.createCell(1).setCellValue("$185.00");
			}
			if (seminar.getIsParking()) {
				row = sheet.createRow(i++);
				row.createCell(0).setCellValue("Parking Permit");
				row.createCell(1).setCellValue("$10.00");
			}

			row = sheet.createRow(i++);
			row.createCell(0).setCellValue("Total Fee");
			row.createCell(1).setCellValue("$" + seminar.getTotalFee());

			// Set the response headers to return an attached .xls file
			response.setHeader("content-disposition", "attachment; filename=Johns_Hopkins_Seminar.xls");
			response.setHeader("cache-control", "no-cache");

			// Get the output stream and send the workbook to the browser
			OutputStream out = response.getOutputStream();
			workbook.write(out);
			workbook.close();
			out.close();
		} else {
			String to = seminar.getEmail();
			String subject = "Johns Hopkins Seminar";

			CharArrayWriterResponse customResponse = new CharArrayWriterResponse(response);
			request.getRequestDispatcher("./email.jsp").forward(request, customResponse);

			String body = customResponse.getOutput();

			try {
				MailUtil.sendMail(to, subject, body, true);
			} catch (MessagingException e) {
				String errorMessage = "ERROR MESSAGE: " + e.getMessage();
				request.setAttribute("errorMessage", errorMessage);
			}

			RequestDispatcher dispatcher = this.getServletConfig().getServletContext().getRequestDispatcher(url);
			dispatcher.forward(request, response);
		}
	}
}
