<%@page language="java" contentType="text/html"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="pt-br">

<head>
    <meta charset="UTF-8">
    <title>Nova Senha</title>

    <script src="/SistemaHotel/jquery/jquery-2.1.3.min.js"></script>
    <script src="/SistemaHotel/js/script.js"></script>
    <link rel="stylesheet" href="/SistemaHotel/css/style.css">
    <link rel="stylesheet" href="/SistemaHotel/css/register.css">
    <link rel="stylesheet" href="/SistemaHotel/css/login.css">

</head>

<body>
    <header>

        <jsp:useBean id="usuario" class="hotel.Usuario" scope="session"/>

        <section class="cover">
            <img src="/SistemaHotel/static/cover.jpg" alt="">
        </section>

        <nav class="mainNav">
            <ul class="menu">

                <li><a href="/SistemaHotel/index.jsp">O Hotel</a>

                </li>
                <li><a href="/SistemaHotel/suites/index.jsp">Suítes</a>

                </li>
                <li>    
                    <c:choose>
                        <c:when test="${not empty usuario.nome}">
                                <a href="reserva/index.jsp">Reservas</a>                               
                        </c:when>
                    </c:choose>               
                </li>
                <li><a href="/SistemaHotel/contato/index.jsp">Contato</a>
                </li>

                <div id="formDiv">
                    <c:choose>
                        <c:when test="${empty usuario.nome}">
                            <a class="aReg" href="/SistemaHotel/login?exit=false">Entrar</a>
                        </c:when>
                        <c:otherwise>
                            <input type="hidden" value="true" id="autenticado"/>
                            <a class="aReg" href="/SistemaHotel/login?exit=true">Sair</a>
                        </c:otherwise>
                    </c:choose>

                    <a class="aReg" href="/SistemaHotel/cadastro/index.jsp">Registrar</a>
                </div>

                <li>
                    
                </li>

            </ul>
        </nav>

    </header>

    <section>
        <div class="content">
            <h1>Erro!</h1>

            <h2 class="hotel_center">${sessionScope.erro}</h2>

            <h2 class="hotel_center">No entanto, a senha foi alterada com sucesso:</h2>

            <h3 class="hotel_center"><b>Email: </b> ${sessionScope.email}</h3>
            <h3 class="hotel_center"><b>Senha: </b> ${sessionScope.senha}</h3>

        </div>
    </section>


    <footer class="footer">
        <p class="text-center">Desenvolvido por Paulo Moreno e Vinicius Lovato</p>
        <p class="text-center"> Logo <a href="https://thenounproject.com/term/palm-tree/23337/">Palm-tree</a> created by Paul Stevensunder the license <a href="
http://creativecommons.org/licenses/by/3.0/legalcode">Creative Common (CC BY 3.0)</a>  and modified by Paulo Moreno </p>
    </footer>


    <c:set var="erro" value="${}" scope="session" />
    <c:set var="senha" value="${}" scope="session" />
    <c:set var="email" value="${}" scope="session" />

</body>

</html>