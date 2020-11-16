package edu.jhu.mod7.sql.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.jhu.mod7.sql.util.SQLUtil;

/**
 * Servlet implementation class SqlGatewayServlet
 */
@WebServlet("/SqlGatewayServlet")
public class SqlGatewayServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String sqlStatement = request.getParameter("sqlStatement");
		String sqlResult = "";
		try {

			// Get a connection
			Class.forName("com.mysql.cj.jdbc.Driver");

//			String dbURL = "jdbc:mysql://localhost:3306/exercise";
//			String username = "user";
//			String password = "password";

			 String dbURL = "jdbc:mysql://sql9.freemysqlhosting.net:3306/sql9372045";
			 String username = "sql9372045";
			 String password = "QkdxdgXSKA";

			Connection connection = DriverManager.getConnection(dbURL, username, password);

			// Create a statement
			Statement statement = connection.createStatement();

			// Parse the SQL string
			sqlStatement = sqlStatement.trim();
			if (sqlStatement.length() >= 6) {
				String sqlType = sqlStatement.substring(0, 6);
				if (sqlType.equalsIgnoreCase("select")) {
					// Create the HTML for the result set
					ResultSet resultSet = statement.executeQuery(sqlStatement);
					sqlResult = SQLUtil.getHtmlTable(resultSet);
					resultSet.close();
				} else {
					int i = statement.executeUpdate(sqlStatement);
					if (i == 0) { // a DDL statement
						sqlResult = "<p>The statement executed successfully.</p>";
					} else { // an INSERT, UPDATE, or DELETE statement
						sqlResult = "<p>The statement executed successfully.<br>" + i + " row(s) affected.</p>";
					}
				}
			}
			statement.close();
			connection.close();
		} catch (ClassNotFoundException e) {
			sqlResult = "<p>Error loading the databse driver: <br>" + e.getMessage() + "</p>";
		} catch (SQLException e) {
			sqlResult = "<p>Error executing the SQL statement: <br>" + e.getMessage() + "</p>";
		}

		HttpSession session = request.getSession();
		session.setAttribute("sqlResult", sqlResult);
		session.setAttribute("sqlStatement", sqlStatement);

		String url = "/index.jsp";
		getServletContext().getRequestDispatcher(url).forward(request, response);
	}
}