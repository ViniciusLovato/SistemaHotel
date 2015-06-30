<%@page language="java" contentType="text/html"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="pt-br">

<head>
    <meta charset="UTF-8">
    <title>Erro!</title>

    <script src="/SistemaHotel/jquery/jquery-2.1.3.min.js"></script>
    <script src="/SistemaHotel/js/script.js"></script>
    <script src="/SistemaHotel/js/expire.js"></script>
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

        <jsp:include page="/menu.jsp" />


    </header>

    <section>
        <div class="content">
            <h1>Erro!</h1>

            <h2 class="hotel_center">${sessionScope.erro}</h2>

            <div class="regSubmit">
                <a class="aReg" href="/SistemaHotel/login?exit=false">TENTAR NOVAMENTE</a>
            </div>

            <form id="forgotPasswordForm" class="mainForm" action="/SistemaHotel/esqueciSenha" method="POST">
                <div class="input_column">
                    <div class="input_line">
                        <h2>Email <em>*</em></h2>
                        <input id="emailInput" type="email" placeholder="someone@somewhere.com" name="email" >
                        <span  class="error" id="emailInputError"></span>
                    </div>
                </div>
                <div class="regSubmit">
                    <input class="submit" type="submit" value="ESQUECI MINHA SENHA">
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


    <c:set var="erro" value="${}" scope="session" />

</body>

</html>