<%@page language="java" contentType="text/html"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html lang="pt-br">

<head>
    <meta charset="utf-8">
    <title>Erro</title>

    <script src="jquery/jquery-2.1.3.min.js"></script>
    <script src="js/script.js"></script>
    <script src="js/expire.js"></script>
    <link rel="stylesheet" href="css/style.css">

</head>

<body>

    <jsp:useBean id="usuario" class="hotel.Usuario" scope="session"/>
    <jsp:useBean id="mensagens" class="java.util.ArrayList" scope="session"/>

    <header>

        <section class="cover">
            <img src="static/cover.png" alt="cover">
        </section>

        <jsp:include page="/menu.jsp" />

    </header>

    <section class="intro">
        <div class="content">

            	<h1>Erro!</h1>

				<h3><c:out value="${sessionScope.erro}" /></h3>
				<h3>Log: <c:out value="${sessionScope.debug}" /></h3>

				<c:set var="erro" value="${}" scope="session" />
				<c:set var="debug" value="${}" scope="session" />


    </section>

    <footer class="footer">
        <p class="text-center">Desenvolvido por Paulo Moreno e Vinicius Lovato</p>
        <p class="text-center"> Logo <a href="https://thenounproject.com/term/palm-tree/23337/">Palm-tree</a> created by Paul Stevens under the license <a href="
http://creativecommons.org/licenses/by/3.0/legalcode">Creative Common (CC BY 3.0)</a>  and modified by Paulo Moreno </p>
    </footer>

</body>

</html>

