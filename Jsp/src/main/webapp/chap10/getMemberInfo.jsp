<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String memberId = (String)session.getAttribute("MEMBERID");
	String name = (String)session.getAttribute("NAME");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>세션 정보 조회</title>
</head>
<body>

아이디: <%= memberId %> <br>
회원명: <%= name %>

</body>
</html>