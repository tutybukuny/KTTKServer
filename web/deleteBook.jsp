<%-- 
    Document   : deleteBook
    Created on : Mar 23, 2017, 12:01:47 AM
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
        <title>Delete Book</title>
        <link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">
        <script src="bootstrap/js/jquery-2.2.3.min.js"></script>
        <script src="bootstrap/js/bootstrap.min.js"></script>
    </head>
    <body>
        <ul class="nav nav-tabs" role="tablist">
            <li class="active"><a href="Management?action=toAllBook">All Books</a></li>
            <li><a href="Management?action=toAddBook">Add Book</a></li>
            <li><a href="Management?action=toSearch">Search</a></li>           
        </ul>
        <h3 align="center">Are you sure want to delete this book?</h3>
        <form method="POST" name = "Delete" action="/KTTKServer/Management" >
            <input type="text" value="${book.ID}" name="bookID" hidden="true"/>
            <input type="text" value="confirmDelete" name="action" hidden="true"/>
            <table align="center">
                <tr>
                    <td width="30%">ID</td>
                    <td width="70%">
                        <input type="text" class="form-control" name="id" value="${book.ID}" readonly="true"/>
                    </td> 
                </tr> 
                <tr>
                    <td width="30%">Name</td>
                    <td width="70%">
                        <input type="text" class="form-control" name="name" value="${book.name}" readonly="true"/>
                    </td> 
                </tr>
                <tr>
                    <td width="30%">Description</td>
                    <td width="70%">
                        <input type="text" class="form-control" name="description" value="${book.description}" readonly="true"/>
                    </td> 
                </tr> 
                <tr>
                    <td width="30%">Cost</td>
                    <td width="70%">
                        <input type="text" class="form-control" name="cost" value="${book.cost}" readonly="true"/>
                    </td> 
                </tr> 
                <tr>
                    <td width="30%">Cost</td>
                    <td width="70%">
                        <input type="text" class="form-control" name="cost" value="${book.publisher.name}" readonly="true"/>
                    </td> 
                </tr> 
                <tr>
                    <td width="30%">Cost</td>
                    <td width="70%">
                        <input type="text" class="form-control" name="cost" value="${book.author.name}" readonly="true"/>
                    </td> 
                </tr>  
                <tr>
                    <td width="30%">Cost</td>
                    <td width="70%">
                        <input type="text" class="form-control" name="cost" value="${book.bookType.name}" readonly="true"/>
                    </td> 
                </tr> 
                <tr>
                    <td>
                        <input type="submit" class="btn btn-default" value="Delete"/>
                        <a href="Management?action=toAllBook"><input type="button" value="Cancel" class="btn btn-default"></a>
                    </td>
                </tr>
            </table>     
        </form>
    </body>
</html>