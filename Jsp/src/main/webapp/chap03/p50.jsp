<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
	<title>HTML 문서의 제목</title>
</head>
<body>
<%
	String bookTitle = "JSP 프로그래밍";
	String author = "최범균";
%>
<b><%=bookTitle %></b>(<%= author %>)입니다.
</body>
</html>