package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.GettheWeather;
import model.weatherBean;

@WebServlet("/OverWatchServlet")
public class OverWatchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public OverWatchServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Identify session
		HttpSession session = request.getSession();
		
		// Send client to 404-page on the server by RD
		RequestDispatcher rd = request.getRequestDispatcher("404.jsp");

		// If we found HttpSession
		if (session.getAttribute("session") != null) {

			try {

				// Print in console
				System.out.println("OWservlet session: Online");

				// Create wBean instance
				weatherBean wBean = new weatherBean();

				// Add the parameters' name & value to wBean
				wBean.setCityStr(request.getParameter("city"));
				wBean.setCountryStr(request.getParameter("country"));

				// Send wBean instance to GettheWeather
				GettheWeather.getWeather(wBean);

				// Store an attribute in this Request
				request.setAttribute("wBean", wBean);
				
				// Create a new cookie
				Cookie c = new Cookie(wBean.getCityStr(), wBean.getCountryStr());
				c.setMaxAge(120); // 2 minutes 
				response.addCookie(c);

				// Set RD to showWeather-page
				rd = request.getRequestDispatcher("showWeather.jsp");

				// Print in console
				System.out.println("OWservlet cookies: En kaka har skapats");

			}

			catch (Exception error) {

				// Print error in console
				System.out.println(error);
				
				// Set RD to index-page
				rd = request.getRequestDispatcher("index.jsp");

			}

		}

		else { System.out.println("OWservlet session: false or null"); }

		// Send client to another resource on the server by RD
		rd.forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Send the client to index-page
		response.sendRedirect("index.jsp");

	}

}
