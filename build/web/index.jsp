<%-- 
    Document   : index
    Created on : 10/04/2023, 21:37:32
    Author     : heube
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>UEG - Gestão de Pessoas</title>
    </head>
    <body>
        <h1>Sistema de Gestão de Pessoas</h1>
        
        <jsp:forward page="/CPessoa?acao=listar" />
        
    </body>
</html>
