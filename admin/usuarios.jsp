<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>Document</title>

	<link rel="stylesheet" href="../css/admin.css">
</head>
<body>

    <jsp:useBean id="usuario" class="hotel.Usuario" scope="session"/>
    <jsp:useBean id="usuarios" class="java.util.ArrayList" scope="session"/>

    <jsp:useBean id="mensagens" class="java.util.ArrayList" scope="session"/>

	
	<header>
		<ul>
			<li>
				<h1>Administrador do Hotel</h1>
			</li>
			<li>		
				<p><a href="">Log out</a></p>
			</li>
		</ul>
	</header>


	<section>
		
		<h2>Bem vindo ${usuario.nome}</h2>
		<h3>Lista de Usuarios</h3>
		<h3><a href="index.jsp">Voltar ao inicio</a></h33>

		<div class="tableDiv">

			<table>
				<tr>
				    <th>Username</th>
				    <th>Email</th>
				    <th>Editar</th>
				    <th>Remover</th>
				</tr>

				
	            <c:forEach items="${usuarios}" var="user">
	            	<tr>
		            	<td>${user.nome}</td>
		                <td>${user.email} </td> 
				    	<td><a href="">Editar</a></td>
				    	<td><a href="">Remover</a></td>
	            	</tr>

           		</c:forEach>
			</table>		
		</div>
	</section>

	<aside></aside>

</body>
</html>