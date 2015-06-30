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

    <jsp:useBean id="diasOcupados" class="java.util.ArrayList" scope="session"/>

    <script type="text/javascript">
    var diasOcupados = [
    <c:forEach items="${diasOcupados}" var="diaOcupado">
        "${diaOcupado}",
    </c:forEach>
    ];
    </script>

    <script src="/SistemaHotel/js/script.js"></script>
    <script src="/SistemaHotel/js/reserva.js"></script>
    <script src="/SistemaHotel/js/expire.js"></script>

    <link rel="stylesheet" href="/SistemaHotel/css/style.css">
    <link rel="stylesheet" href="/SistemaHotel/css/register.css">

    <style type="text/css">

    .dia_invalido{
        opacity: 1 !important;
        filter: Alpha(Opacity=100) !important;
    }

    .dia_invalido span{
        color: white !important;
        background-color: red !important;
    }

    .dia_valido a{
        color: white !important;
        background-color: green !important;
    }    </style>

    <link rel="stylesheet" href="/SistemaHotel/jquery-ui/jquery-ui.theme.min.css">

</head>

<body>
    <header>

        <jsp:useBean id="usuario" class="hotel.Usuario" scope="session"/>


        <section class="cover">
            <img src="/SistemaHotel/static/cover.png" alt="cover">
        </section>

        <jsp:include page="/menu.jsp" />


    </header>

   <section>
        <div class="content">

        <h1>Nova Reserva</h1>

        <form id="reservationForm" class="mainForm" action="/SistemaHotel/reserva" method="POST">

            <div class="input_column">

                <div class="input_line">
                    <h2>Data de Check-In <em>*</em></h2>
                    <input id="dataEntradaInput" type="text" placeholder="DD/MM/AAAA" name="dataEntrada" >
                    <span  class="error" id="dataEntradaInputError"></span>
                </div>
                <div class="input_line">
                    <h2>Data de Check-Out <em>*</em></h2>
                    <input id="dataSaidaInput" type="text" placeholder="DD/MM/AAAA" name="dataSaida" >
                    <span  class="error" id="dataSaidaInputError"></span>
                </div>
            </div>

            <div class="input_column">

                <div class="input_line">
                    <h2>Quantidade de Adultos <em>*</em></h2>
                    <div class="select_div">
                        <select id="adultsInput" name="adultos" >
                            <option value="1">1</option>
                            <option value="2">2</option>
                            <option value="3">3</option>
                            <option value="4">4</option>
                        </select>
                    </div>
                    <span  class="error" id="adultsInputError"></span>
                </div>

                <div class="input_line">
                    <h2>Crianças de até 3 anos <em>*</em></h2>
                    <div class="select_div">
                        <select id="criancasAte3Input" name="criancasAte3" >
                            <option value="1">0</option>
                            <option value="2">1</option>
                            <option value="3">2</option>
                            <option value="4">3</option>
                        </select>
                    </div>
                    <span  class="error" id="criancasAte3InputError"></span>
                </div>

                <div class="input_line">
                    <h2>Criança de 4 a 12 anos <em>*</em></h2>
                    <div class="select_div">
                        <select id="criancasAte12Input" name="criancasAte12" >
                            <option value="0">0</option>
                            <option value="1">1</option>
                            <option value="2">2</option>
                            <option value="3">3</option>
                            <option value="4">4</option>
                        </select>
                    </div>
                    <span  class="error" id="criancasAte12InputError"></span>
                </div>

            </div>

            <div class="regSubmit resSubmit">
                <input class="submit" type="submit" value="Confirmar Reserva">
            </div>

            </form>
        <p class="nota"><em>*</em> Campos Obrigatórios</p>
        </div>
    </section>

    <footer class="footer">
        <p class="text-center">Desenvolvido por Paulo Moreno e Vinicius Lovato</p>
        <p class="text-center"> Logo <a href="https://thenounproject.com/term/palm-tree/23337/">Palm-tree</a> created by Paul Stevens under the license <a href="
http://creativecommons.org/licenses/by/3.0/legalcode">Creative Common (CC BY 3.0)</a>  and modified by Paulo Moreno </p>
    </footer>

</body>

</html>