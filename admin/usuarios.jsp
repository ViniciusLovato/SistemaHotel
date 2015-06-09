<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>Document</title>

	<link rel="stylesheet" href="/SistemaHotel/css/admin.css">
</head>
<body>

    <jsp:useBean id="usuarios" class="java.util.ArrayList" scope="session"/>
	
	<header>
		<ul>
			<li>
				<h1>Administrador do Hotel</h1>
			</li>
			<li>	
				<p>
					 <a class="aReg" href="/SistemaHotel/login?exit=true">Log out</a>
				</p>
			</li>
		</ul>
	</header>


	<section>
		
		<h2>Bem vindo ${usuario.nome}</h2>
		<h3>Lista de Usuarios</h3>
		<h3><a href="/SistemaHotel/admin/index.jsp">Voltar ao inicio</a></h33>

		<form action=""></form>


		<form action="/SistemaHotel/cadastro" method="GET">
			<div class="tableDiv">

				<table>
					<tr>
						<th>Data de Cadastro</th>
					    <th>Username</th>
					    <th>Email</th>
					    <th>Detalhes</th>
					    <th>Remover</th>
					</tr>

		            <c:forEach items="${usuarios}" var="user" varStatus="status">
		            	<tr>
		            		<td>${user.dataCadastro}</td>
			            	<td>${user.nome}</td>
			                <td>${user.email} </td> 
					    	<td><a href="/SistemaHotel/cadastro?detalhe=${status.index}">Detalhes</a></td>
		            		<td><input type="checkbox" name="checkbox${status.index}"></td>
		            	</tr>
	           		</c:forEach>
				</table>		
			</div>
	   		
	   		<input type="submit" value="Remover selecionados">			
		</form>


	</section>

	<aside></aside>

</body>
</html>