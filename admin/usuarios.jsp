<%@page language="java" contentType="text/html"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="pt-br">

<head>
    <meta charset="utf-8">
    <title>Administração - Usuários</title>

    <script src="/SistemaHotel/jquery/jquery-2.1.3.min.js"></script>
    <script src="/SistemaHotel/js/script.js"></script>
    <script src="/SistemaHotel/js/expire.js"></script>
    <link rel="stylesheet" href="/SistemaHotel/css/style.css">
	<link rel="stylesheet" href="/SistemaHotel/css/admin_r.css">


</head>

<body>

    <jsp:useBean id="usuario" class="hotel.Usuario" scope="session"/>
    <jsp:useBean id="usuariosFiltrados" class="java.util.ArrayList" scope="session"/>

    <header>

        <section class="cover">
            <img src="/SistemaHotel/static/cover.png" alt="cover">
        </section>

        <jsp:include page="/menu.jsp" />

    </header>

    <section class="intro">
        <div class="admin_div content">



			<h1>Administracao do site - Usuários</h1>
				<h2>Bem vindo ${usuario.nome}</h2>
		<a class="adReg aReg" href="/SistemaHotel/admin/index.jsp">Voltar ao inicio</a></br>

		<form action="/SistemaHotel/cadastro" method="GET">
			
			<label for="name">Nome:</label>
			<input type="text" name="nameFilter">

			<input type="submit" value="Filtrar">	
			<button onclick="location.href = '/SistemaHotel/cadastro?nameFilter=all'">Resetar Filtro</button>
		</form>

		<p>Filtrando por nome: ${filtro}</p>
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

		            <c:forEach items="${usuariosFiltrados}" var="userF" varStatus="status">
		            	<tr>
		            		<td>${userF.data_cadastro}</td>
			            	<td>${userF.nome}</td>
			                <td>${userF.email} </td> 
					    	<td><a href="/SistemaHotel/cadastro?detalhe=${status.index}">Detalhes</a></td>
		            		<td><input type="checkbox" name="checkbox${status.index}"></td>
		            	</tr>
	           		</c:forEach>
				</table>		
			</div>
	   		
	   		<input type="submit" value="Remover selecionados">			
		</form>


    </section>

    <footer class="footer">
        <p class="text-center">Desenvolvido por Paulo Moreno e Vinicius Lovato</p>
        <p class="text-center"> Logo <a href="https://thenounproject.com/term/palm-tree/23337/">Palm-tree</a> created by Paul Stevens under the license <a href="
http://creativecommons.org/licenses/by/3.0/legalcode">Creative Common (CC BY 3.0)</a>  and modified by Paulo Moreno </p>
    </footer>

</body>

</html>