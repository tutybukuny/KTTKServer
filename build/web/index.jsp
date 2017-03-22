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
        <title>Log In</title>
        <link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">
        <script src="bootstrap/js/jquery-2.2.3.min.js"></script>
        <script src="bootstrap/js/bootstrap.min.js"></script>
    </head>
    <body>
        <h2 align="center">Log In</h2><br>
        <table align="center">
            <tr>
                <td width="100%">
                    <form method="POST" action="Management">
                        <input type="text" value="login" name="action" hidden="true">
                        <div class="input-group">
                            <input type="text" class="form-control" placeholder="Name...." name="username">
                        </div><br>
                        <div class="input-group">
                            <input type="password" class="form-control" placeholder="Password" name="password">
                        </div><br>
                        <input type="submit" class="btn btn-default" value="Log In">
                        <input type="reset" class="btn btn-default" value="Reset">
                    </form>
                    <p></td> </tr> </table>
    </body>
</html>

