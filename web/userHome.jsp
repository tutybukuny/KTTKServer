<%-- 
    Document   : index
    Created on : Mar 21, 2017, 10:44:58 PM
    Author     : TienDuc
--%>

<%@page import="Model.Human"%>
<%@page import="Model.Account"%>
<%@page import="Control.HumanControl"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>User</title>
        <link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">
        <script src="bootstrap/js/jquery-2.2.3.min.js"></script>
        <script src="bootstrap/js/bootstrap.min.js"></script>
        <link rel="stylesheet" href="css/style.css">
    </head>
    <body>
        <jsp:include page="content/header.jsp"></jsp:include>
        <div class="login-page">
            <div class="form">
                <table>
                    <tr>
                        <td>First Name: </td>
                        <td><input type="text" value="${sessionScope.human.name.firstName}" readonly="true"></td>
                    </tr>
                    <tr>
                        <td>Last Name: </td>
                        <td><input type="text" value="${sessionScope.human.name.lastName}" readonly="true"></td>
                    </tr>
                    <tr>
                        <td>Middle Name: </td>
                        <td><input type="text" value="${sessionScope.human.name.middleName}" readonly="true"></td>
                    </tr>
                </table>
            </div>
        </div>
    </body>
</html>
