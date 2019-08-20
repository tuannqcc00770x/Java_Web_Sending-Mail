<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="model.Email" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Sending Mail</title>
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
		<link rel="stylesheet" href="css/index.css">
	</head>
	<body>
		<%@include file = "common_jsp/header.jsp" %>
		
		<h1 class="title">Welcome to Sending Mail Project</h1>
		<div class="container">
		<h6>Account to test</h6>
		<p>username = cc00770xprj321x@gmail.com</p>
		<p>password = funixcc00770x</p>
		</div>
		
		<%@include file = "common_jsp/footer.jsp" %>
		<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
	</body>
</html>