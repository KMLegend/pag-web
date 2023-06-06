<%-- 
    Document   : listarPessoa
    Created on : 10/04/2023, 21:36:57
    Author     : heube
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Gestão de Pessoas - Lista</title>
    </head>
    <body>
        <br>
        <br>
        <h1>Lista de Pessoas</h1>
        <br>
        <br>
        <form name="listarPessoa" action="CPessoa" method="GET">
            <table style="width: 70%" border='1'>

                <thead>
                    <td>ID</td>
                    <td>Nome</td>
                    <td>Idade</td>
                    <td colspan="2" >Ação</td>
                </thead>

                <tbody>
                    <c:forEach items="${lista}" var="pessoa">

                        <tr>
                            <td><c:out value="${pessoa.id}"/></td>
                            <td><c:out value="${pessoa.nome}"/></td>
                            <td><c:out value="${pessoa.idade}"/></td>
                            
                            <td><a href="CPessoa?acao=alterar&codigo=<c:out value="${pessoa.id}"/>"> Alterar</td>
                            <td><a href="CPessoa?acao=excluir&codigo=<c:out value="${pessoa.id}"/>" 
                                    onclick="return confirm('Confirma a exclusão?')"> Excluir</td>

                        </tr>

                    </c:forEach>
                </tbody>
                
                
                <tfoot>
                <td align="center" colspan="5"><a href="CPessoa?acao=incluir">Incluir Nova Pessoa</a></td>
                </tfoot>

            </table>
        </form>
    </body>
</html>
