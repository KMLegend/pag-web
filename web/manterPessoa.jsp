<%-- 
    Document   : manterRevista
    Created on : 10/04/2023, 21:38:41
    Author     : heube
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Gestão de Revistas - Manter</title>
    </head>
    <body>
        <h1>Manter Revista</h1>
        <form method="POST" action="CRevista" name="manterRevista">
            <table>
                <tr>
                    <td>ID</td>
                    <td><input type="text" readonly="readonly" backgroundcolor=''
                               name="id" size="10" maxlength="10"
                               value="<c:out value="${revista.id}" />" />
                    </td>
                </tr>


                <tr>
                    <td>Nome</td>
                    <td><input type="text" 
                               name="nome" size="50" maxlength="250"
                               value="<c:out value="${revista.nome}"/>" />
                    </td>
                </tr>

                <tr>
                    <td>Idade</td>
                    <td><input type="text" 
                               name="idade" size="10" maxlength="10"
                               value="<c:out value="${revista.idade}"/>" />
                    </td>
                </tr>
                <tr>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                </tr>
                <tr>
                    <td><input type="submit" value="Salvar"/></td>
                    <td><input type="button" value="Voltar" onclick="history.go(-1)"/></td>
                </tr>
            </table>
        </form>
    </body>
</html>