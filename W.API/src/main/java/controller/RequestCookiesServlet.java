	package controller;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/RequestCookiesServlet")
public class RequestCookiesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public RequestCookiesServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	
		// Prevent error if the client is going to http://localhost:8080/W.API/RequestCookiesServlet by web browser 
		RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
		rd.forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Identify session
		HttpSession session = request.getSession();
		
		// Send client to another resource on the server by RD
		RequestDispatcher rd;
		
		// Save the "cookies" parameter here
		String submit;
		
		// Allow cookie?
		boolean permission;

		// Get the "cookies" parameter's value from Request parameters and add to string
		submit = request.getParameter("cookies");
		
		// If the "cookies" parameter's value is "allow"
		if (submit.equals("allow")) {

			// Set bool allow cookie? to true
			permission = true;

			// Print in console 
			System.out.println("CookieServlet allow cookie: " + permission);

			// Set the boolean to this session 
			session.setAttribute("session", permission);
			session.setMaxInactiveInterval(60); // "one minute"-cookie - be fast...

			// set RD to index-page
			rd = request.getRequestDispatcher("index.jsp");

		} else {

			// Set allow cookie to false
			permission = false;
			
			// Print in console
			System.out.println("CookieServlet allow cookie: " + permission);

			// Set RD to 404-page
			rd = request.getRequestDispatcher("404.jsp");

		}

		// Send the client to another resource 
		rd.forward(request, response); 

	}

}
