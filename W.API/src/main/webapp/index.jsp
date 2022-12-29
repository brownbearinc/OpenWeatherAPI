<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>OpenWeather</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css"
	rel="stylesheet">

<link href="style.css" rel="stylesheet">

</head>
<body>

	<%
	// Om en session inte exiterar, hänvisa till CookieRequest.jsp
	if (session.getAttribute("session") == null) {
	%>

	<jsp:include page="CookieRequest.jsp" />
	<br />

	<%
	} else { 
	System.out.println("index: Du är ansluten till sessionen");
	}
	%>


	<form action="OverWatchServlet" class="text-light text-capitalize" method="get">
		City:<input type="text" name="city" /><br/> 
		Country:<input type="text" name="country"/> 
		<input type="submit" value="Go" />
	</form>


</body>
</html>

