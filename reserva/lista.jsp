<%@page language="java" contentType="text/html"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <title>Reservas</title>

    <link rel="stylesheet" href="/SistemaHotel/jquery-ui/jquery-ui.min.css">
    <script src="/SistemaHotel/jquery/jquery-2.1.3.min.js"></script>
    <script src="/SistemaHotel/jquery-ui/jquery-ui.min.js"></script>

    <script src="/SistemaHotel/js/script.js"></script>
    <script src="/SistemaHotel/js/reserva.js"></script>
    <script src="/SistemaHotel/js/expire.js"></script>

    <link rel="stylesheet" href="/SistemaHotel/css/style.css">
    <link rel="stylesheet" href="/SistemaHotel/css/register.css">

    <link rel="stylesheet" href="/SistemaHotel/jquery-ui/jquery-ui.theme.min.css">

</head>

<body>
    <header>

        <jsp:useBean id="usuario" class="hotel.Usuario" scope="session"/>
        <jsp:useBean id="reservas" class="java.util.ArrayList" scope="session"/>


        <section class="cover">
            <img src="/SistemaHotel/static/cover.png" alt="cover">
        </section>

        <jsp:include page="/menu.jsp" />

    </header>

   <section>
        <div class="content">
            <h1>Lista de Reservas</h1>

                <div class="regSubmit">

            <a class="aReg" href="/SistemaHotel/reserva">Nova Reserva</a>
</div>
        </div>



    <c:forEach items="${reservas}" var="reserva" varStatus="status">

        <div class="content">

            <div class="input_column">
                <div class="input_line">
                    <h2>Data de Check-In</h2>
                    <input type="text" disabled="true" value="<fmt:formatDate pattern='dd/MM/yy' 
            value='${reserva.checkin}' />">
                </div>
                <div class="input_line">
                    <h2>Data de Check-Out</h2>
                    <input type="text" disabled="true" value="<fmt:formatDate pattern='dd/MM/yy' 
            value='${reserva.checkout}' />">
                </div>
            </div>

            <div class="input_column">

                <div class="input_line">
                    <h2>Quantidade de Adultos</h2>
                    <input type="text" disabled="true" value="${reserva.numeroAdultos}">
                </div>

                <div class="input_line">
                    <h2>Crianças de até 3 anos</h2>
                    <input type="text" disabled="true" value="${reserva.numeroBebes}">

                </div>

                <div class="input_line">
                    <h2>Criança de 4 a 12 anos</h2>
                    <input type="text" disabled="true" value="${reserva.numeroCriancas}">
                </div>


            <form class="mainForm" action="/SistemaHotel/consulta" method="POST">

                <div class="regSubmit">
                    <input type="hidden" name="id" value="${reserva.id}">
                    <input type="hidden" name="acao" value="remover">
                    <input class="" type="submit" value="Remover Reserva">
                </div>

            </div>

        <div class="clear_fix"></div>

        </div>

            </c:forEach>                    


    </section>

    <footer class="footer">
        <p class="text-center">Desenvolvido por Paulo Moreno e Vinicius Lovato</p>
        <p class="text-center"> Logo <a href="https://thenounproject.com/term/palm-tree/23337/">Palm-tree</a> created by Paul Stevens under the license <a href="
http://creativecommons.org/licenses/by/3.0/legalcode">Creative Common (CC BY 3.0)</a>  and modified by Paulo Moreno </p>
    </footer>

</body>

</html>