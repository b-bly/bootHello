<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login form</title>
</head>
<body>
	<h3>Login form</h3>
	<form:form
		action="${pageContext.request.contextPath}/authenticateTheUser"
		method="POST">
		
		<c:if test="${param.error != null }">
			<i>Sorry, you've entered an incorrect username/password</i>
		
		</c:if>
		
		<p>
			Username: <input type="text" name="username" />
		</p>
		<p>
			Password: <input type="password" name="password" />
		</p>
		<input type="submit" value="Login" />
	</form:form>

</body>
</html>