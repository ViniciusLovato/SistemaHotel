<%@page language="java" contentType="text/html"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="pt-br">

<head>
	<meta charset="utf-8">
	<title>Administração - Usuário</title>

	<script src="/SistemaHotel/jquery/jquery-2.1.3.min.js"></script>
	<script src="/SistemaHotel/js/script.js"></script>
	<script src="/SistemaHotel/js/expire.js"></script>
	<link rel="stylesheet" href="/SistemaHotel/css/style.css">
	<link rel="stylesheet" href="/SistemaHotel/css/admin_r.css">


</head>

<body>

	<jsp:useBean id="usuario" class="hotel.Usuario" scope="session"/>
	<jsp:useBean id="usuarioDetalhado" class="hotel.Usuario" scope="session"/>

	<header>

		<section class="cover">
			<img src="/SistemaHotel/static/cover.png" alt="cover">
		</section>

		<jsp:include page="/menu.jsp" />

	</header>

	<section class="intro">
		<div class="admin_div content">


			<h1>Administracao do site - Detalhes de Usuário</h1>


			<div class="detalhes">
				<p> <b>Nome:</b> ${usuarioDetalhado.nome}</p>
				<p> <b>Email:</b> ${usuarioDetalhado.email}</p>
				<p>	<b>CPF:</b> ${usuarioDetalhado.cpf}	</p>				
				<p> <b>Data de Nascimento:</b> ${usuarioDetalhado.data_nascimento}</p>
				<p> <b>Sexo:</b> ${usuarioDetalhado.sexo}</p>
				<p> <b>Estado Civil:</b> ${usuarioDetalhado.estado_civil}</p>
				<p> <b>Cidade:</b> ${usuarioDetalhado.cidade}</p>
				<p> <b>Estado:</b> ${usuarioDetalhado.estado}</p>
				<p> <b>CEP: </b> ${usuarioDetalhado.cep}</p>
				<p>	<b>Senha: </b> ${usuarioDetalhado.senha}</p>
				
			</div>
			
			<a class="adReg aReg" href="/SistemaHotel/admin/index.jsp">Voltar ao inicio</a>

		</section>

		<footer class="footer">
			<p class="text-center">Desenvolvido por Paulo Moreno e Vinicius Lovato</p>
			<p class="text-center"> Logo <a href="https://thenounproject.com/term/palm-tree/23337/">Palm-tree</a> created by Paul Stevens under the license <a href="
				http://creativecommons.org/licenses/by/3.0/legalcode">Creative Common (CC BY 3.0)</a>  and modified by Paulo Moreno </p>
			</footer>

		</body>

		</html>

