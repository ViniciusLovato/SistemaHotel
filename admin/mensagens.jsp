<%@page language="java" contentType="text/html"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="pt-br">

<head>
    <meta charset="utf-8">
    <title>Administração - Mensagens</title>

    <script src="/SistemaHotel/jquery/jquery-2.1.3.min.js"></script>
    <script src="/SistemaHotel/js/script.js"></script>
    <script src="/SistemaHotel/js/expire.js"></script>
    <link rel="stylesheet" href="/SistemaHotel/css/style.css">
	<link rel="stylesheet" href="/SistemaHotel/css/admin_r.css">


</head>

<body>

    <jsp:useBean id="usuario" class="hotel.Usuario" scope="session"/>
    <jsp:useBean id="mensagens" class="java.util.ArrayList" scope="session"/>

    <header>

        <section class="cover">
            <img src="/SistemaHotel/static/cover.png" alt="cover">
        </section>

        <jsp:include page="/menu.jsp" />

    </header>

    <section class="intro">
        <div class="admin_div content">



<h1>Administracao do site - Mensagens</h1>
		<h2>Bem vindo ${usuario.nome}</h2>

		<a class="adReg aReg" href="/SistemaHotel/admin/index.jsp">Voltar ao inicio</a></br>

		<form action="/SistemaHotel/contato" method="GET">
			
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