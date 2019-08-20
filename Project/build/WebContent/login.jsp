<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.User" %>
<%String msg = (String)request.getAttribute("msg"); 
  User user = (User)request.getAttribute("user");
  String username = "";
  String password = "";
  if (user != null){
	  username = user.getUsername();
	  password = user.getPassword();
  }
  String err = (String)request.getAttribute("error");
%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Login</title>
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
		<link rel="stylesheet" href="css/index.css">
		<link rel="stylesheet" href="css/form.css">
	</head>
	<body>
		<%@include file = "common_jsp/header.jsp" %>
		<h1 class="title">Login</h1>
		<%
     	 if (msg != null){%>
     		<h4 style="color:red;text-align:center;padding:0 10%;"> <%=msg%> </h4>
     	 <%}%>
		<div class="myform">
	     	<form action="<%=request.getContextPath()%>/login" method="post">
		     	<input class="mg-10" type="text" placeholder="Username" name="username" value="<%= username%>">
		     	<br>
		     	<input class="mg-10" type="password" placeholder="Password" name="password" value="<%= password%>">
		     	<br>
		     	<button class="mg-10" type="submit">Submit</button>
	     	</form>
	     	<%
		    if (err != null){%>
		     	<p style="color:red;text-align:left;padding:0 10%;"> <%=err%> </p>
		    <%}%>
     	</div>
		<%@include file = "common_jsp/footer.jsp" %>
		<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
	</body>
</html>