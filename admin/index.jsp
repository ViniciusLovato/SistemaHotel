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
		
		<h2>Administracao do site - Visao Geral</h2>
		<h3>Bem vindo ${usuario.nome}</h3>

		<div class="tableDiv">

			<table>
				<tr>
				    <th>Dados</th>
				    <th>Adicionar</th>
				    <th>Editar</th>
				</tr>

				<tr>
				    <td>Admin</td>
				    <td></td>
				    <td><a href="">Editar</a></td>
				</tr>

				<tr>
				    <td>Usuarios</td>
				    <td><a href="">Add</a></td>
				    <td><a href="usuarios.jsp">Listar</a></td>
				</tr>
				
				<tr>
				    <td>Reservas</td>
				    <td><a href="">Add</a></td>
				    <td><a href="reservas.jsp">Listar</a></td>
				</tr>

				<tr>
				    <td>Mensagens</td>
				    <td><a href="">Add</a></td>
				    <td><a href="mensagens.jsp">Listar</a></td>
				</tr>
			</table>		
		</div>

	</section>

	<aside></aside>

</body>
</html>