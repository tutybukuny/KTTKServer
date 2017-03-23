<%-- 
    Document   : index
    Created on : Mar 21, 2017, 10:44:58 PM
    Author     : TienDuc
--%>

<%@page import="Model.Account"%>
<%@page import="Control.HumanControl"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Log In</title>
        <link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">
        <script src="bootstrap/js/jquery-2.2.3.min.js"></script>
        <script src="bootstrap/js/bootstrap.min.js"></script>
        <link rel="stylesheet" href="css/style.css">
    </head>
    <body>
        <jsp:include page="content/header.jsp"></jsp:include>
        <div class="login-page">
            <div class="form">
                <form action="Management" method="POST" class="login-form">
                    <input type="text" name="username" placeholder="username"/>
                    <input type="password" name="password" placeholder="password"/>
                    <input type="hidden" name="action" value="login"/>
                    <button type="submit">login</button>
                    <p class="message">Not registered? <a href="#">Create an account</a></p>
                </form>
            </div>
        </div>
    </body>
</html>

