   <%@page language="java" contentType="text/html"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    <jsp:useBean id="usuario" class="hotel.Usuario" scope="session"/>
      

        <nav class="mainNav">
            <ul class="menu">
                <li>
                    <a href="/SistemaHotel/index.jsp">O Hotel</a>
                </li>
                <li>
                    <a href="/SistemaHotel/suites/index.jsp">Suítes</a>
                </li>
                <li>    
                    <c:choose>
                        <c:when test="${not empty usuario.nome}">
                                <a href="/SistemaHotel/consulta">Reservas</a>                               
                        </c:when>
                    </c:choose>               
                </li>
                <li>
                    <c:choose>
                        <c:when test="${usuario.tipo == 'admin'}">
                                <a href="/SistemaHotel/admin">Admin</a>                               
                        </c:when>
                    </c:choose>   
                </li>
                <li>
                    <a href="/SistemaHotel/contato/index.jsp">Contato</a>
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

        <c:if test="${not empty usuario.nome}">
            <nav class="welcome">
                <h3>Bem vindo, ${usuario.nome}!</h3>
            </nav>
        </c:if>
