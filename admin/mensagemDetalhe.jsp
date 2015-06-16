<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>Document</title>

	<link rel="stylesheet" href="/SistemaHotel/css/admin.css">
</head>
<body>

    <jsp:useBean id="mensagem" class="hotel.Mensagem" scope="session"/>

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
		<h3><a href="/SistemaHotel/admin/mensagens.jsp">Voltar ao inicio</a></h3>

		<div class="mensagensDiv">
            <div class="mensagem">
            	<p>Enviado por: ${mensagem.nome}</p> <br/>
                <p>Email : ${mensagem.email} </p> <br/>
                <p>Telefone : ${mensagem.celular} </p> <br/>
                <p>Mensagem: ${mensagem.mensagem} </p> <br/>
                <hr/>
            </div>



		</div>


	</section>

	<aside></aside>

</body>
</html>