<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.User" %>
<%@ page import="java.util.ArrayList" %>
<%
	User user = (User)request.getAttribute("user");

	@SuppressWarnings("unchecked")
	ArrayList<String> listErr = (ArrayList<String>)request.getAttribute("errors");
	
	String username = "";
	String password = "";
	if (user != null){
		username = user.getUsername();
		password = user.getPassword();
	}
%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Signup</title>
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
		<link rel="stylesheet" href="css/index.css">
		<link rel="stylesheet" href="css/form.css">
	</head>
	<body>
		<%@include file = "common_jsp/header.jsp" %>
		<h1 class="title">Signup</h1>
		<div class="myform">
	     	<form action="<%=request.getContextPath()%>/signup" method="post">
		     	<input class="mg-10" type="text" placeholder="Username" name="username" value="<%=username%>">
		     	<br>
		     	<input class="mg-10" type="password" placeholder="Password" name="password" value="<%=password%>">
		     	<br>
		     	<button class="mg-10" type="submit">Submit</button>
		     	<%if (listErr!=null){%>
		     		<p style="color:red;text-align:left;padding:0 10%;"> <%=String.join("<br />", listErr)%> </p>
		     	<%}%>
	     	</form>
     	</div>
		<%@include file = "common_jsp/footer.jsp" %>
		<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
	</body>
</html>