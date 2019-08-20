<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="model.Email" %>
<%@ page import="model.IBMail" %>
<%@ page import="service.InBoxService" %>
<!--ArrayList<Email> list = MailService.getInstance().readEmail();-->
<%
int total = InBoxService.getInstance().getNumPage();
int idx = 1;
if (request.getParameter("idx") != null){
	idx = Integer.valueOf((String)request.getParameter("idx"));
}
ArrayList<IBMail> list = InBoxService.getInstance().getMailByPage(idx);
%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Sending Mail</title>
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
		
		<h1 class="title">All inbox here</h1>
		<div class="container">
		<% for (IBMail mail:list){%>
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
		<p>
		<a class="btn btn-info" href="./mailDetail.jsp?id=<%=mail.getId()%>&idx=<%=idx%>"><i class="fa fa-eye" aria-hidden="true"></i>Detail...</a>
		<hr>
		</p>
		<%}%>
		<div class="text-center">
		<span><a class="btn btn-success" href="./inbox.jsp?idx=<%=idx - 1 < 1 ? 1 : idx - 1%>"><</a></span>
		<span><%=idx%></span>
		<span>/</span>
		<span><%=total%></span>
		<span><a class="btn btn-success" href="./inbox.jsp?idx=<%=idx + 1 > total ? total : idx + 1%>">></a></span>
		</div>
		</div>
		
		<%@include file = "common_jsp/footer.jsp" %>
		<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
	</body>
</html>