package com.sample;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//**pre init is shown below
//@WebServlet(urlPatterns="/adduserServlet", loadOnStrap=1)

// below is lazy init
@WebServlet("/adduserServlet")
public class addUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	Connection connection;

	//init() is a life cycle method
	@Override
	public void init(ServletConfig config) throws ServletException {

		try {
			ServletContext context = config.getServletContext();
			
			System.out.println("AddUserSevlet.init() method. DB connection created");
			Class.forName("com.mysql.jdbc.Driver"); // loads the driver
			
			// DriverManager.getConnection("jdbc:mysql://localhost/mydb", "root", "admin")
			// above statement connects to the mydb schema created 
			// root is userID and admin is password		
			
			// this method allows easy implementation of changes
			// in case of change in password, all Servlets will be updated automatically
			connection = DriverManager.getConnection(context.getInitParameter("dburl"),
					context.getInitParameter("dbuser"), context.getInitParameter("dbpassword"));
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
		

		try (PreparedStatement statement = connection.prepareStatement("insert into user values (?,?,?,?)");) {

			statement.setString(1, firstname);
			statement.setString(2, lastname);
			statement.setString(3, email);
			statement.setString(4, password);
			
			int rowsInserted = statement.executeUpdate();
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
