<%-- 
    Document   : updateBook
    Created on : Mar 22, 2017, 4:17:50 PM
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
        <title>Update Book</title>
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
        <h3 align="center">Update a book</h3>
        <form method="POST" name = "UpdateB" action="/KTTKServer/Management" >
            <input type="text" value="${book.ID}" name="bookID" hidden="true"/>
            <input type="text" value="confirmUpdate" name="action" hidden="true"/>
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
                        <input type="text" class="form-control" name="name" value="${book.name}"/>
                    </td> 
                </tr>
                <br>
                <tr>
                    <td width="30%">Description</td>
                    <td width="70%">
                        <input type="text" class="form-control" name="description" value="${book.description}"/>
                    </td> 
                </tr> 
                <br>
                <tr>
                    <td width="30%">Cost</td>
                    <td width="70%">
                        <input type="text" class="form-control" name="cost" value="${book.cost}"/>
                    </td> 
                </tr> 
                <br>
                <tr>
                    <td width="30%">Publisher</td>
                    <td width="70%">
                        <select class="form-control" name="publisher">
                            <c:forEach items="${publishers}" var="pub">
                                <option value="${pub.ID}" 
                                        <c:choose>
                                            <c:when test="${book.publisher.ID==pub.ID}">
                                                selected="true"
                                            </c:when>
                                        </c:choose>
                                        >${pub.name}</option>
                            </c:forEach>
                        </select>
                    </td> 
                </tr> 
                <tr>
                    <td width="30%">Author</td>
                    <td width="70%">
                        <select class="form-control" name="author">
                            <c:forEach items="${authors}" var="au">
                                <option value="${au.ID}" 
                                        <c:choose>
                                            <c:when test="${book.author.ID==au.ID}">
                                                selected="true"
                                            </c:when>
                                        </c:choose>
                                        >${au.name}</option>
                            </c:forEach>
                        </select>
                    </td> 
                </tr> 
                <tr>
                    <td width="30%">Book Type</td>
                    <td width="70%">
                        <select class="form-control" name="bookType">
                            <c:forEach items="${bookTypes}" var="typ">
                                <option value="${typ.ID}" 
                                        <c:choose>
                                            <c:when test="${book.bookType.ID==typ.ID}">
                                                selected="true"
                                            </c:when>
                                        </c:choose>
                                        >${typ.name}</option>
                            </c:forEach>
                        </select>
                    </td> 
                </tr>
                <tr>
                    <td>
                        <input class="btn btn-default" type="submit" value="Update"/>
                        <a href="Management?action=toAllBook"><input type="button" value="Cancel" class="btn btn-default"></a>
                    </td>
                </tr>
            </table>     
        </form>
    </body>
</html>
