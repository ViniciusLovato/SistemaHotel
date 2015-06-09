<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>Document</title>

	<link rel="stylesheet" href="/SistemaHotel/css/admin.css">	
</head>
<body>

    <jsp:useBean id="usuarioDetalhado" class="hotel.Usuario" scope="session"/>
	
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
		<h3>Detalhes de Usuarios</h3>
		
		<div class="detalhes">
			<p> Nome: ${usuarioDetalhado.nome}</p>
			<p> Email: ${usuarioDetalhado.email}</p>
			<p>	CPF: ${usuarioDetalhado.cpf}	</p>				
			<p> Data de Nascimento: ${usuarioDetalhado.data_nascimento}</p>
			<p> Sexo: ${usuarioDetalhado.sexo}</p>
			<p> Estado Civil: ${usuarioDetalhado.estado_civil}</p>
			<p> Cidade: ${usuarioDetalhado.cidade}</p>
			<p> Estado: ${usuarioDetalhado.estado}</p>
			<p> CEP: ${usuarioDetalhado.cep}</p>
			<p>	Senha: ${usuarioDetalhado.senha}</p>
			
		</div>
	
		<h3><a href="/SistemaHotel/admin/index.jsp">Voltar ao inicio</a></h3>
		
	</section>

	<aside></aside>

</body>
</html>