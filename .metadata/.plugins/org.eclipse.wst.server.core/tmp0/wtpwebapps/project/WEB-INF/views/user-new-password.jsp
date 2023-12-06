<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<%
	String msg = (String) request.getAttribute("msg");
	%>
	<%
	if (msg != null) {
	%>
	<h3>
		<%
		out.print(msg);
		%>
	</h3>
	<%
	}
	%>
	<form action="UserController" method="post">
		<div class=" agile-wls-contact-mid">
			<div class="form-group contact-forms">
				<%
				String email = (String) request.getAttribute("email");
				%>
				<input type="hidden" name="email" value="<%=email%>"> <input
					type="password" class="form-control" name="np"
					placeholder="New Password"> <input type="password"
					class="form-control" name="cnfpassword" placeholder="Confirm New Password">
			</div>
			<button type="submit" name="action" 
				class="btn btn-block sent-butnn">New Password</button>
		</div>
	</form>
</body>
</html>