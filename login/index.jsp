<%@page language="java" contentType="text/html"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="pt-br">

<head>
    <meta charset="UTF-8">
    <title>Entrar</title>

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
            <h1>Entrar</h1>
            <form id="mainLoginForm" class="mainForm" action="/SistemaHotel/login" method="POST">
                <div class="input_column">
                    <div class="input_line">
                        <h2>Email <em>*</em></h2>
                        <input id="emailInput" type="email" placeholder="someone@somewhere.com" name="email" >
                        <span  class="error" id="emailInputError"></span>
                    </div>
                    <div class="input_line">
                        <h2>Senha <em>*</em></h2>
                        <input id="passwordInput" type="password" placeholder="******" name="senha" >
                        <span  class="error" id="passwordInputError"></span>
                    </div>
                </div>
                <div class="regSubmit">
                    <input class="submit" type="submit" value="Entrar">
                </div>
            </form>
        <p class="nota"><em>*</em> Campos Obrigatórios</p>
        </div>
    </section>


    <footer class="footer">
        <p class="text-center">Desenvolvido por Paulo Moreno e Vinicius Lovato</p>
        <p class="text-center"> Logo <a href="https://thenounproject.com/term/palm-tree/23337/">Palm-tree</a> created by Paul Stevensunder the license <a href="
http://creativecommons.org/licenses/by/3.0/legalcode">Creative Common (CC BY 3.0)</a>  and modified by Paulo Moreno </p>
    </footer>

</body>

</html>