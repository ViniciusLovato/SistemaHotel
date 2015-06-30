<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>Document</title>

	<link rel="stylesheet" href="/SistemaHotel/css/admin.css">
</head>
<body>

    <jsp:useBean id="hotel" class="hotel.Hotel" scope="session"/>

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
		<h3><a href="/SistemaHotel/admin/index.jsp">Voltar ao inicio</a></h3>

	        <form action="/SistemaHotel/admin/hotel" method="POST">

			<div class="tableDiv">
				<table>
					<tr>
						<th>Número de Quartos</th>
					</tr>
						
		            	<tr  <c:if test="${not mensagem.lida}">class="notLida"</c:if>     >
		            		<td><input type="text" name="numQuartos" value="${hotel.numero_quartos}"/>
</td>
		            	</tr>
				</table>		
			</div>
	            	
	        <input type="submit" value="Salvar">


			</form>



	</section>

	<aside></aside>

</body>
</html>