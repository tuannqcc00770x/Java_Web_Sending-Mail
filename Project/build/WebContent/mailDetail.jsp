<%@page import="service.InBoxService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="model.Email" %>
<%@ page import="model.IBMail" %>
<%@ page import="service.InBoxService" %>
<!--ArrayList<Email> list = MailService.getInstance().readEmail();-->
<%
int id = 0;
int idx = 0;
if (request.getParameter("id")!=null){
	id = Integer.valueOf(request.getParameter("id"));
}
if (request.getParameter("idx")!=null){
	idx = Integer.valueOf(request.getParameter("idx"));
}
IBMail mail = InBoxService.getInstance().getMailById(id, idx);
%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Mail Detail</title>
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
		<link rel="stylesheet" href="css/index.css">
		<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
		<style type="text/css">
		.heading{
		font-size: 16px;
		font-weight: bold;
		font-style: italic;
		text-decoration: underline;
		color: blue;
		}
		
		</style>
	</head>
	<body>
		<%@include file = "common_jsp/header.jsp" %>
		
		<h1 class="title">Mail detail here</h1>
		<div class="container">
		<p>
		<span class="heading">From: </span>
		<span><%=mail.getFrom()%></span>
		</p>
		<p>
		<span class="heading">To: </span>
		<span><%=mail.getTo()%></span>
		</p>
		<p>
		<span class="heading">Date: </span>
		<span><%=mail.getDate()%></span>
		</p>
		<p>
		<span class="heading">Title: </span>
		<span><%=mail.getTitle()%></span>
		</p>
		<div>
		<%=mail.getContent() %>
		</div>
		<p>
		<a class="btn btn-info" href="./inbox.jsp?idx=<%=idx%>">Go Back</a>
		</p>
		</div>
		
		<%@include file = "common_jsp/footer.jsp" %>
		<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
	</body>
</html>