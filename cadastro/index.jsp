<%@page language="java" contentType="text/html"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="pt-br">

<head>
    <meta charset="UTF-8">
    <title>Novo Cliente</title>

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
                </li>>
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

            <h1>Novo Cliente</h1>
            <form id="registerForm" class="mainForm" action="/SistemaHotel/cadastro" method="POST">
                <div class="input_column">

                    <div class="input_line">
                        <h2>Nome Completo <em>*</em></h2>
                        <input id="nameInput" type="text" placeholder="Ex: João Silva" name="nome" >
                        <span class="error" id="nameInputError"></span>
                    </div>

                    <div class="input_line">
                        <h2>Email <em>*</em></h2>
                        <input id="emailInput" type="email" placeholder="someone@somewhere.com" name="email" >
                        <span  class="error" id="emailInputError"></span>
                    </div>

                    <div class="input_line">
                        <h2>CPF <em>*</em></h2>
                        <input id="cpfInput" type="text" placeholder="XXX.XXX.XXX-XX" name="cpf" >
                        <span  class="error" id="cpfInputError"></span>
                    </div>

                    <div class="input_line">
                        <h2>Data de Nascimento <em>*</em></h2>
                        <input id="birthdayInput" type="text" placeholder="DD/MM/AAAA" name="dataNascimento" >
                        <span  class="error" id="birthdayInputError"></span>
                    </div>

                    <div class="input_line seg">
                        <h2>Sexo <em>*</em></h2>
                        <nav class="segmented-button">
                            <input type="radio" name="sexo" value="male" id="sexo_m" checked="">
                            <label for="sexo_m" class="first">Masculino</label>
                            <input type="radio" name="sexo" value="female" id="sexo_f">
                            <label for="sexo_f" class="last">Feminino</label>
                        </nav>
                    </div>

                    <div class="input_line">
                        <h2>Estado Civil <em>*</em></h2>
                        <div class="select_div">
                            <select id="civilStateInput" name="estadoCivil" >
                                <option value="casado">Casado</option>
                                <option value="solteiro">Solteiro</option>
                                <option value="outros">Outros</option>
                            </select>
                        </div>
                    </div>

                </div>
                <div class="input_column">
                    <div class="input_line">
                        <h2>Cidade <em>*</em></h2>
                        <input id="cityInput" type="text" placeholder="Ex: São Paulo" name="cidade" >
                        <span class="error" id="cityInputError"></span>
                    </div>

                    <div class="input_line">
                        <h2>Estado <em>*</em></h2>
                        <div class="select_div">
                            <select id="stateInput" name="estado" >
                                <option value="AC">Acre</option>
                                <option value="AL">Alagoas</option>
                                <option value="AP">Amapá</option>
                                <option value="AM">Amazonas</option>
                                <option value="BA">Bahia</option>
                                <option value="CE">Ceará</option>
                                <option value="DF">Distrito Federal</option>
                                <option value="ES">Espirito Santo</option>
                                <option value="GO">Goiás</option>
                                <option value="MA">Maranhão</option>
                                <option value="MS">Mato Grosso do Sul</option>
                                <option value="MT">Mato Grosso</option>
                                <option value="MG">Minas Gerais</option>
                                <option value="PA">Pará</option>
                                <option value="PB">Paraía</option>
                                <option value="PR">Paraná</option>
                                <option value="PE">Pernambuco</option>
                                <option value="PI">Piauí</option>
                                <option value="RJ">Rio de Janeiro</option>
                                <option value="RN">Rio Grande do Norte</option>
                                <option value="RS">Rio Grande do Sul</option>
                                <option value="RO">Rondônia</option>
                                <option value="RR">Roraima</option>
                                <option value="SC">Santa Catarina</option>
                                <option value="SP">São Paulo</option>
                                <option value="SE">Sergipe</option>
                                <option value="TO">Tocantins</option>
                            </select>
                        </div>
                    </div>

                    <div class="input_line">
                        <h2>CEP <em>*</em></h2>
                        <input id="cepInput" type="text" placeholder="XXXXX-XXX" name="cep" >
                        <span  class="error" id="cepInputError"></span>
                    </div>

                    <div class="input_line">
                        <h2>Senha <em>*</em></h2>
                        <input id="passwordInput" type="password" placeholder name="senha" >
                        <p id="pwdStrenght"></p>

                        <h2>Confirmar Senha <em>*</em></h2>
                        <input id="confirmPasswordInput" type="password" placeholder="" name="confirmarSenha" >
                        <span class="error" id="confirmPasswordInputError"></span>
                    </div>

                </div>

                <div class="regSubmit">
                    <input class="submit" type="submit" value="Registrar">
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