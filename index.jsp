<%@page language="java" contentType="text/html"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html lang="pt-br">

<head>
    <meta charset="utf-8">
    <title>Hotel</title>

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

    <section>
            Ola ${usuario.nome} <br/>
            Tempo: <span id="timer"></span> <br/>

    </section>

    <section class="intro">
        <div class="content">

            <h1>O Hotel</h1>

            <div class="column">
                <img src="static/photo1.jpg" width="400px" alt="">
                <br>
                <img src="static/photo2.jpg" width="400px" alt="">
                <br>
                <img src="static/photo3.jpg" width="400px" alt="">
            </div>

            <div class="column">
                <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Harum alias consectetur animi. Ad soluta rerum repellendus alias repellat obcaecati, molestias fuga asperiores ex dolorem corporis dolore non. At similique nihil, magni impedit sed sapiente est accusamus corporis assumenda, incidunt vero.</p>
                
                <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Distinctio culpa, minima delectus facere eos, deserunt aperiam sapiente iure nobis officia.</p>
                
                <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Doloribus deserunt iusto aspernatur quibusdam soluta rerum eaque corporis, nihil adipisci! Aperiam praesentium molestias placeat non quisquam similique vel ullam numquam dignissimos?</p>
                
                <p>Lorem ipsum dolor sit amet!!.</p>
            </div>
        </div>


    </section>

    <footer class="footer">
        <p class="text-center">Desenvolvido por Paulo Moreno e Vinicius Lovato</p>
        <p class="text-center"> Logo <a href="https://thenounproject.com/term/palm-tree/23337/">Palm-tree</a> created by Paul Stevens under the license <a href="
http://creativecommons.org/licenses/by/3.0/legalcode">Creative Common (CC BY 3.0)</a>  and modified by Paulo Moreno </p>
    </footer>

</body>

</html>