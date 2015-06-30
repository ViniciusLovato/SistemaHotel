<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>Document</title>

	<link rel="stylesheet" href="/SistemaHotel/css/admin.css">
</head>
<body>

    <jsp:useBean id="reservasFiltradas" class="java.util.ArrayList" scope="session"/>

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
		<h3>Lista de Reservas</h3>
		<h3><a href="/SistemaHotel/admin/index.jsp">Voltar ao inicio</a></h3>


		<form action="/SistemaHotel/consulta" method="GET">
			
			<label for="name">Email:</label>
			<input type="text" name="nameFilter">

			<input type="submit" value="Filtrar">	
			<button onclick="location.href = '/SistemaHotel/consulta?nameFilter='">Resetar Filtro</button>
		</form>

		<p>Filtrando por Email: ${filter}</p>
		<form action="/SistemaHotel/consulta" method="GET">
			<div class="tableDiv">

				<table>
				<tr>
					<th>Autor da Reserva</th>
				    <th>Data de Checkin</th>
				    <th>Data de Checkout</th>
				    <th>Remover</th>
				</tr>

	            <c:forEach items="${reservasFiltradas}" var="reserva" varStatus="status">
	            	<tr>
	            		<td>${reserva.email}</td>
		            	<td>${reserva.checkin}</td>
		                <td>${reserva.checkout} </td> 
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