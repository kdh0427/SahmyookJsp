<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>클라이언트 및 서버 정보</title>
</head>
<body>
	클라이언트 IP =
	<%=request.getRemoteAddr()%>
	<br> 요청정보길이 =
	<%=request.getContentLength()%>
	<br> 요청정보 인코딩 =
	<%=request.getCharacterEncoding()%>
	<br> 요청정보 컨텐츠타입 =
	<%=request.getContentType()%>
	<br> 요청정보 프로토콜 =
	<%=request.getProtocol()%>
	<br> 요청정보 전송방식 =
	<%=request.getMethod()%>
	<br> 요청 URL =
	<%=request.getRequestURL()%>
	<br> 컨텍스트 정보 =
	<%=request.getContextPath()%>
	<br> 서버이름 =
	<%=request.getServerName()%>
	<br> 서버포트 =
	<%=request.getServerPort()%>
	<br>
</body>
</html>