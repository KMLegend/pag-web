<%-- 
    Document   : listarRevista
    Created on : 10/04/2023, 21:36:57
    Author     : heube
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Gestão de Revistas - Lista</title>
    </head>
    <body>
        <br>
        <br>
        <h1>Lista de Revistas</h1>
        <br>
        <br>
        <form name="listarRevista" action="CRevista" method="GET">
            <table style="width: 70%" border='1'>

                <thead>
                    <td>ID</td>
                    <td>Nome</td>
                    <td>Idade</td>
                    <td colspan="2" >Ação</td>
                </thead>

                <tbody>
                    <c:forEach items="${lista}" var="revista">

                        <tr>
                            <td><c:out value="${revista.id}"/></td>
                            <td><c:out value="${revista.nome}"/></td>
                            <td><c:out value="${revista.idade}"/></td>
                            
                            <td><a href="CRevista?acao=alterar&codigo=<c:out value="${revista.id}"/>"> Alterar</td>
                            <td><a href="CRevista?acao=excluir&codigo=<c:out value="${revista.id}"/>"
                                    onclick="return confirm('Confirma a exclusão?')"> Excluir</td>

                        </tr>

                    </c:forEach>
                </tbody>
                
                
                <tfoot>
                <td align="center" colspan="5"><a href="CRevista?acao=incluir">Incluir Nova Revista</a></td>
                </tfoot>

            </table>
        </form>
    </body>
</html>
