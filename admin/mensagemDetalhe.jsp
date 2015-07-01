<%@page language="java" contentType="text/html"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="pt-br">

<head>
	<meta charset="utf-8">
	<title>Administração - Hotel</title>

	<script src="/SistemaHotel/jquery/jquery-2.1.3.min.js"></script>
	<script src="/SistemaHotel/js/script.js"></script>
	<script src="/SistemaHotel/js/expire.js"></script>
	<link rel="stylesheet" href="/SistemaHotel/css/style.css">
	<link rel="stylesheet" href="/SistemaHotel/css/register.css">
	<link rel="stylesheet" href="/SistemaHotel/css/admin_r.css">


</head>

<body>

	<jsp:useBean id="usuario" class="hotel.Usuario" scope="session"/>
	<jsp:useBean id="mensagem" class="hotel.Mensagem" scope="session"/>

	<header>

		<section class="cover">
			<img src="/SistemaHotel/static/cover.png" alt="cover">
		</section>

		<jsp:include page="/menu.jsp" />

	</header>

	<section class="intro">
		<div class="admin_div content">



			<h1>Administracao do site - Hotel</h1>
			<h2>Bem vindo ${usuario.nome}</h2>
			<a class="adReg aReg" href="/SistemaHotel/admin/mensagens.jsp">Voltar ao inicio</a>

			<div class="mensagensDiv">
				<div class="mensagem">
					<p><b>Enviado por:</b> ${mensagem.nome}</p> <br/>
					<p><b>Email :</b> ${mensagem.email} </p> <br/>
					<p><b>Telefone :</b> ${mensagem.celular} </p> <br/>
					<p><b>Mensagem:</b> ${mensagem.mensagem} </p> <br/>
				</div>



			</div>

		</section>

		<footer class="footer">
			<p class="text-center">Desenvolvido por Paulo Moreno e Vinicius Lovato</p>
			<p class="text-center"> Logo <a href="https://thenounproject.com/term/palm-tree/23337/">Palm-tree</a> created by Paul Stevens under the license <a href="
				http://creativecommons.org/licenses/by/3.0/legalcode">Creative Common (CC BY 3.0)</a>  and modified by Paulo Moreno </p>
			</footer>

		</body>

		</html>