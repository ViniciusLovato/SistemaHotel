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
		<h2>Bem vindo ${usuario.nome}</h2>
		<h3><a href="index.jsp">Voltar ao inicio</a></h3>

		<div class="mensagensDiv">
			<h3>Mensagens no Sistema</h3>
			<hr/>
            
            <div class="mensagem">
	            <c:forEach items="${mensagens}" var="mensagem">
	            	<p>Enviado por: ${mensagem.nome}</p> <br/>
	                <p>Email : ${mensagem.email} </p> <br/>
	                <p>Telefone : ${mensagem.celular} </p> <br/>
	                <p>Mensagem: ${mensagem.mensagem} </p> <br/>
	                <hr/>
           		</c:forEach>
            </div>



		</div>


	</section>

	<aside></aside>

</body>
</html>