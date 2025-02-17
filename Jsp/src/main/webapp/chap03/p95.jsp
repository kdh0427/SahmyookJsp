<%@page import="java.net.URLEncoder"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String value = "자바";
	String encodedValue = URLEncoder.encode(value, "utf-8");
	response.sendRedirect("index.jsp?name" + encodedValue);
%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>실패</title>
	</head>
	<body>
		코드를 고쳐주세요.
	</body>
</html>