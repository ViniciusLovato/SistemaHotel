<%@page language="java" contentType="text/html"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="pt-br">

<head>
    <meta charset="UTF-8">
    <title>Contato</title>

    <script src="../jquery/jquery-2.1.3.min.js"></script>
    <script src="../js/script.js"></script>
    <link rel="stylesheet" href="../css/style.css">
    <link rel="stylesheet" href="../css/register.css">

</head>

<body>
    <header>

        <jsp:useBean id="usuario" class="hotel.Usuario" scope="session"/>

        <section class="cover">
            <img src="../static/cover.png" alt="cover">
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

            </ul>
        </nav>
    </header>


   <section>
        <div class="content">
            <h1>Contato</h1>
            <form id="contactForm" class="mainForm" action="/SistemaHotel/contato" method="POST">


                <div class="input_line">
                    <h2>Nome <em>*</em></h2>
                    <input id="nameInput" type="text" placeholder="Ex: Luiza Gonçalves" name="nome" >
                    <span  class="error" id="nameInputError"></span>
                </div>
                <div class="input_line">
                    <h2>E-mail <em>*</em></h2>
                    <input id="emailInput" type="email" placeholder="someone@somewhere.com" name="email" >
                    <span  class="error" id="emailInputError"></span>
                </div>
                <div class="input_line">
                    <h2>Celular</h2>
                    <input id="phoneInput" type="text" placeholder="(XX)XXXXX-XX-XX" name="celular">
                    <span  class="error" id="phoneInputError"></span>
                </div>
                <div class="input_line">
                    <h2>Como Conheceu o Hotel? <em>*</em></h2>
                    <input type="checkbox" name="indicacao[]" value="jornais">Jornais<br/>
                    <input type="checkbox" name="indicacao[]" value="indicacao">Indicação<br/>
                    <input type="checkbox" name="indicacao[]" value="redesSociais">Redes Sociais<br/>
                    <input type="checkbox" name="indicacao[]" value="google">Google<br/>
                    <input type="checkbox" name="indicacao[]" value="revistas">Revistas<br/>
                    <input type="checkbox" name="indicacao[]" value="outros">Outros<br/>
                    <span  class="error" id="howKnowInputError"></span>
                </div>
                <div class="input_line">
                    <h2>Mensagem <em>*</em></h2>
                    <textarea id="messageInput" class="txtMsg" name="mensagem" placeholder="Fale conosco"></textarea>
                    <span  class="error" id="messageInputError"></span>
                </div>

                <div class="regSubmit resSubmit">
                    <input class="submit" type="submit" value="Enviar Mensagem">
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