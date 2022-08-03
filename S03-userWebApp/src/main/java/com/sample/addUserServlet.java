package com.sample;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/adduserServlet")
public class addUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	Connection connection;

	//init() is a life cycle method
	@Override
	public void init() throws ServletException {

		try {
			System.out.println("AddUserSevlet.init() method. DB connection created");
			Class.forName("com.mysql.jdbc.Driver"); // loads the driver
			// DriverManager.getConnection("jdbc:mysql://localhost/mydb", "root", "admin")
			// above statement connects to the mydb schema created 
			// root is userID and admin is password
			connection = DriverManager.getConnection("jdbc:mysql://localhost/mydb", "root", "admin");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// allows the response statements to recognize the html tags
		
		response.setContentType("text/html");
		String firstname = request.getParameter("firstname");
		String lastname = request.getParameter("lastname");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		

		try (Statement statement = connection.createStatement();) {

			String query = "insert into user values('" + firstname + "', '" + lastname + "', '" + email + "', '" + password  + "')";
			System.out.println("Query being executed: " + query);
			int rowsInserted = statement.executeUpdate(query);
			System.out.println("Number of rows inserted: " + rowsInserted);
			
			
			// creating an object for PrintWriter to display a message after adding user
			PrintWriter out = response.getWriter();
			out.println("<h2>User is added successfully!</h2>");
			
			// link to go back to home page
			out.write("<p><a href=\"userhome.html\">Home</a></p>");
		} 
		catch (SQLException e) {
			e.printStackTrace();
		} 
		
	}
	
	
	// closes the connection when server stops
	// destroy() is a life cycle method
	@Override
	public void destroy() {
		try {
			System.out.println("AddUserSevlet.destroy() method. DB connection closed");
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
