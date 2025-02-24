<%@page import="java.sql.SQLException"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 목록</title>
</head>
<body>

MEMBER 테이블의 내용
<table width="100%" border="1">
<tr>
	<td>이름</td>
	<td>아이디</td>
	<td>이메일</td>
</tr>
<%
	Class.forName("oracle.jdbc.OracleDriver");

	Connection conn = null;
	Statement stmt = null;
	ResultSet rs = null;
	
	try {
		String jdbcDriver = "jdbc:oracle:thin:@localhost:1521:orcl";
		String dbUser = "c##jspexam";
		String dbPass = "qwer1234";
		
		String query = "SELECT * FROM MEMBER ORDER BY MEMBERID";
		
		conn = DriverManager.getConnection(jdbcDriver, dbUser, dbPass);
		stmt = conn.createStatement();
		rs = stmt.executeQuery(query);
		
		while(rs.next()) {
%>
<tr>
	<td><%= rs.getString("NAME") %></td>
	<td><%= rs.getString("MEMBERID") %></td>
	<td><%= rs.getString("EMAIL") %></td>
</tr>
<%
		}
	} catch(SQLException ex) {
		out.println(ex.getMessage());
		ex.printStackTrace();
	} finally {
		if (rs != null) try{ rs.close(); } catch(SQLException ex) {}
		if (stmt != null) try{ stmt.close(); } catch(SQLException ex) {}
		if (conn != null) try{ conn.close(); } catch(SQLException ex) {}
	}
%>
</table>

</body>
</html>
