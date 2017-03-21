<%-- 
    Document   : index
    Created on : Mar 21, 2017, 10:44:58 PM
    Author     : TienDuc
--%>

<%@page import="Model.Account"%>
<%@page import="Control.HumanControl"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form action="Management" method="POST">
            <input type="text" value="login" name="action" hidden="true"/>
            <input type="text" name="username"/><br>
            <input type="password" name="password"><br>
            <input type="submit" value="Login"/>
        </form>
    </body>
</html>
