<%@page language="java" contentType="text/html"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <title>Reserva</title>

    <link rel="stylesheet" href="/SistemaHotel/jquery-ui/jquery-ui.min.css">
    <script src="/SistemaHotel/jquery/jquery-2.1.3.min.js"></script>
    <script src="/SistemaHotel/jquery-ui/jquery-ui.min.js"></script>

    <script src="/SistemaHotel/js/script.js"></script>

    <link rel="stylesheet" href="/SistemaHotel/css/style.css">
    <link rel="stylesheet" href="/SistemaHotel/css/register.css">

    <link rel="stylesheet" href="/SistemaHotel/jquery-ui/jquery-ui.theme.min.css">

</head>

<body>
    <header>

        <jsp:useBean id="usuario" class="hotel.Usuario" scope="session"/>


        <section class="cover">
            <img src="../static/cover.png" alt="cover">
        </section>

        <jsp:include page="/menu.jsp" />

    </header>

   <section>
        <div class="content">
 
            <form action="/SistemaHotel/hotel/ConsultaReservaServlet" method="GET">
                    <input id="dataEntradaInput" type="text" placeholder="DD/MM/AAAA" name="dataEntrada" >
                    <input id="dataSaidaInput" type="text" placeholder="DD/MM/AAAA" name="dataSaida" >

                    <input type="submit" value="buscar">
            </form>
            
        </div>
   </section>

    <footer class="footer">
        <p class="text-center">Desenvolvido por Paulo Moreno e Vinicius Lovato</p>
        <p class="text-center"> Logo <a href="https://thenounproject.com/term/palm-tree/23337/">Palm-tree</a> created by Paul Stevens under the license <a href="
http://creativecommons.org/licenses/by/3.0/legalcode">Creative Common (CC BY 3.0)</a>  and modified by Paulo Moreno </p>
    </footer>

</body>

</html>