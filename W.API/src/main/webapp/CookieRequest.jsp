<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css"
	rel="stylesheet">
</head>
<body>

	<div class="container text-center border rounded border-0 bg-secondary bg-opacity-10">
		<div class="row">
			<div>
				<div class="col align-self-start fs-4">Wanna cookies?</div>
			</div>
			<div class="col align-self-center">
				<form action="RequestCookiesServlet" method="post">
					<input type="submit" class="btn btn-danger" name="cookies"
						value="allow"> <input type="submit"
						class="btn btn-success" name="cookies" value="declined">
				</form>
			</div>
		</div>
	</div>


</body>
</html>