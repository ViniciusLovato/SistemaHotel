<%@page language="java" contentType="text/html;charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="pt-br">
<head>
	<meta charset="UTF-8"/>
	<title>Erro!</title>
</head>
<body>
	<h1>Erro!</h1>

	<h3><c:out value="${sessionScope.erro}" /></h3>
	<h3>Log: <c:out value="${sessionScope.debug}" /></h3>

	<c:set var="erro" value="${}" scope="session" />
	<c:set var="debug" value="${}" scope="session" />

</body>
</html>


