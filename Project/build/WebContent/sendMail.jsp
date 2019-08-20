<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="model.Email" %>
<%
	Email email = (Email)request.getAttribute("email");
	String success = (String)request.getAttribute("success");
	@SuppressWarnings("unchecked")
	ArrayList<String> listErr = (ArrayList<String>)request.getAttribute("errors");
	
	String to = "";
	String cc = "";
	String title = "";
	String content = "";
	if (email != null){
		to = email.getTo();
		cc = email.getCc();
		title = email.getTitle();
		content = email.getContent();
	}
%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Sending Mail</title>
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
		<link rel="stylesheet" href="css/index.css">
		<link rel="stylesheet" href="css/mail.css">
		<script type="text/javascript" src="ckeditor/ckeditor.js"></script>
	</head>
	<body>
		<%@include file = "common_jsp/header.jsp" %>
		
		<h1 class="title">Sending Mail</h1>
		<div class="container">
			<form action="<%=request.getContextPath()%>/sending" method="post" class="">
				<h3 class="heading">TO:</h3>
				<textarea class="content" rows="" cols="" name="to"><%=to%></textarea>
				<h3 class="heading">CC:</h3>
				<textarea class="content" rows="" cols="" name="cc"><%=cc%></textarea>
				<h3 class="heading">Title:</h3>
				<textarea class="content" rows="" cols="" name="title"><%=title%></textarea>
				<h3 class="heading">Content:</h3>
				<textarea name="editor1"><%=content%></textarea>
				<div class="text-center" style="color:red;padding:10px 10%;">
				<%if (listErr!=null){%>
		     		<%=String.join("<br />", listErr)%>
				<%}%>
				<%if (success != null){
					if (success.equals("true"))%> <h3 style="color:blue"> Your email has been sent!</h3>
				<%}%>
				</div>
				<div class="text-center">
					<button class="btn btn-primary">SEND</button> 
				</div>
			</form>
		</div>
		
		
		<%@include file = "common_jsp/footer.jsp" %>
		<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
		<script>
           CKEDITOR.replace( 'editor1' );
        </script>
	</body>
</html>