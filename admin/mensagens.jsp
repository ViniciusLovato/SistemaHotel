<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>Document</title>

	<link rel="stylesheet" href="/SistemaHotel/css/admin.css">
</head>
<body>

    <jsp:useBean id="mensagens" class="java.util.ArrayList" scope="session"/>

	
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

		<form action="/SistemaHotel/contato" method="GET">
			
			<div class="tableDiv">
				<table>
					<tr>
						<th>Data de Envio</th>
					    <th>Enviado por</th>
					    <th>Email</th>
					    <th>Mensagem</th>
					    <th>Detalhes</th>
					    <th>Remover</th>
					</tr>
						
		            <c:forEach items="${mensagens}" var="mensagem" varStatus="status">
		            	<tr  <c:if test="${not mensagem.lida}">class="notLida"</c:if>     >
		            		<td>${mensagem.dataEnvio}</td>
		            		<td>${mensagem.nome}</td>
			            	<td>${mensagem.email}</td>
							<td> <p class="pMensagem">${mensagem.mensagem}</p></td>		
						    <td><a href="/SistemaHotel/contato?detalhe=${status.index}">Ler</a></td>
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