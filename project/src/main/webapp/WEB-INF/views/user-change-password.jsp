<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="com.model.*"%>
<!DOCTYPE html>
<html>
<head>
<style>
.overlay {
	border: 2px solid black;
	/* Set the border style to a 1px solid black line */
	padding: 10px;
	margin-top: 10px;
	/* Add some padding around the box content to make it easier to read */
}

.department .btn-group .btn:first-child {
	margin-right: 10px;
}

/* Style the primary button */
.btn-primary {
	background-color: #007bff;
	color: #fff;
	border: none;
}

/* Style the success button */
.btn-success {
	background-color: #28a745;
	color: #fff;
	border: none;
}

/* Add margin to the buttons */
.btn-group>.btn {
	margin-right: 5px;
}

.social_agileinfo li a {
	display: block;
	width: 30px;
	height: 30px;
	line-height: 30px;
	text-align: center;
	background: #333;
	border-radius: 50%;
	margin: 0 10px;
}

.social_agileinfo li a i {
	font-size: 18px;
}

.social_agileinfo li a:hover {
	background: #555;
}

.social_agileinfo li a:hover i {
	color: #fff;
}

.social_agileinfo li a:focus {
	outline: none;
}
</style>
<meta charset="ISO-8859-1">
<title>Change Password</title>
</head>
<body>
	<%
	User user = null;
	if (session.getAttribute("data") != null) {
		user = (User) session.getAttribute("data");
	} else {
		response.sendRedirect("user-login.jsp");
	}
	%>
	<form action="UserController" method="post">
		<div class=" agile-wls-contact-mid">
			<div class="form-group contact-forms">
				<input type="hidden" class="form-control" name="id"
					value="<%=user.getId()%>">
			</div>
			<div class="form-group contact-forms">
				<input type="password" class="form-control" name="op"
					placeholder="Old Password">
			</div>
			<div class="form-group contact-forms">
				<input type="password" class="form-control" name="np"
					placeholder="New Password">
			</div>
			<div class="form-group contact-forms">
				<input type="password" class="form-control" name="cnfpassword"
					placeholder="Confirm New Password">
			</div>

			<button type="submit" name="action" class="btn btn-block sent-butnn">Update</button>
		</div>
	</form>
</body>
</html>