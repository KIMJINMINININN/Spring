<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" 
    isELIgnored="false"  %>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
 <c:set var="contextPath"  value="${pageContext.request.contextPath}"  />
 
<%
  request.setCharacterEncoding("UTF-8");
%>    

<html>
<head>
	<meta charset="UTF-8">
	<title>결과창</title>
</head>
<body>
	<form method="post" action="${contextPath}/test/login2.do">
	<table width="400">
	<tr>
		<td>아이디 <input type="text" name="userID" size="10"></td>
	</tr>
	<tr>
		<td>이름 <input type="text" name="userName" size="10"></td>
	</tr>
	</table>
	<input type="submit" name="login" value="로그인">
	<input type="reset" name="reset" value="다시입력">
	</form>
</body>
</html>