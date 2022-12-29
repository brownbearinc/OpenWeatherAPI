<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="model.weatherBean"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>the weather</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css"
	rel="stylesheet">
</head>
<body>

	<p class="text-light text-capitalize text-end fs-2">

		<%
		// Skriver ut väderrapporten högra sidan av skärmen 
		weatherBean wBean = (weatherBean) request.getAttribute("wBean");
		out.print(wBean.getCityStr() + " " + wBean.getCelsiusTemp() + "°C " + wBean.getWindStr() + 
				" Wind <br>" + wBean.getDate());
		%>

	</p>


	<%
	
	// Läser av cookies 
	Cookie c[] = request.getCookies();

	// Gå igenom varje cookies 
	for (Cookie search : c) {
		
		// Skriver ut alla cookies med länk till tidigare sökningar förutom "sessions id"-cookie
		if (search.getName().equals("JSESSIONID") == false) {

			out.println("<a href=\"http://localhost:8080/W.API/OverWatchServlet?city=" + search.getName() + "&" + "country=" + search.getValue() + "\"" +
					"class=\"text-light text-capitalize\">" + search.getName() + ", " + search.getValue() + "</a>" + "<br>");

		}
	}
	%>
	<br>

	<jsp:include page="index.jsp" />


</body>
</html>