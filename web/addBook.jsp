<%-- 
    Document   : addBook
    Created on : Mar 22, 2017, 11:35:19 PM
    Author     : TienDuc
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.ArrayList"%>
<%@page import="Model.Book"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add Book</title>
        <link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">
        <script src="bootstrap/js/jquery-2.2.3.min.js"></script>
        <script src="bootstrap/js/bootstrap.min.js"></script>
    </head>
    <body>
        <ul class="nav nav-tabs" role="tablist">
            <li><a href="Management?action=toAllBook">All Books</a></li>
            <li class="active"><a href="Management?action=toAddBook">Add Book</a></li>
            <li><a href="Management?action=toSearch">Search</a></li>            
        </ul>
        <h3 align="center">Add a new book</h3>
        <form method="POST" name = "AddB" action="/KTTKServer/Management" >
            <input type="text" value="addBook" name="action" hidden="true"/>
            <table align="center">
                <tr>
                    <td width="30%">Name</td>
                    <td width="70%">
                        <input type="text" class="form-control" name="name"/>
                    </td> 
                </tr>
                <tr>
                    <td width="30%">Description</td>
                    <td width="70%">
                        <input type="text" class="form-control" name="description"/>
                    </td> 
                </tr>
                <tr>
                    <td width="30%">Cost</td>
                    <td width="70%">
                        <input type="text" class="form-control" name="cost"/>
                    </td> 
                </tr> 
                <tr>
                    <td width="30%">Publisher</td>
                    <td width="70%">
                        <select class="form-control" name="publisher">
                            <c:forEach items="${publishers}" var="pub">
                                <option value="${pub.ID}">${pub.name}</option>
                            </c:forEach>
                        </select>
                    </td> 
                </tr> 
                <tr>
                    <td width="30%">Author</td>
                    <td width="70%">
                        <select class="form-control" name="author">
                            <c:forEach items="${authors}" var="au">
                                <option value="${au.ID}">${au.name}</option>
                            </c:forEach>
                        </select>
                    </td> 
                </tr> 
                <tr>
                    <td width="30%">Book Type</td>
                    <td width="70%">
                        <select class="form-control" name="bookType">
                            <c:forEach items="${bookTypes}" var="typ">
                                <option value="${typ.ID}">${typ.name}</option>
                            </c:forEach>
                        </select>
                    </td> 
                </tr>
                <tr>
                    <td>
                        <input type="submit" class="btn btn-default" value="Add"/>
                        <input type="reset" class="btn btn-default"/>
                    </td>
                </tr>
            </table>     
        </form>
    </body>
</html>
