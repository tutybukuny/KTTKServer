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
    </head>
    <body>
        <jsp:include page="content/header.jsp"></jsp:include>
        <div class="container">
            <div class="panel panel-danger">
                <div class="panel-heading">Update a book</div>
                <div class="panel-body">
                    <form action="/KTTKServer/Management" method="POST" class="form-horizontal" role="form">
                        <input type="text" value="${book.ID}" name="bookID" hidden="true"/>
                        <input type="text" value="confirmUpdateBook" name="action" hidden="true"/>
                        <div class="form-body">
                            <div class="form-group">
                                <label class="col-md-3 control-label">Name</label>
                                <div class="col-md-9">
                                    <input name="name" type="text" value="${book.name}" readonly="true" class="form-control input-sm"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-3 control-label">Description</label>
                                <div class="col-md-9">
                                    <input name="description" type="text" value="${book.description}" class="form-control input-sm"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-3 control-label">Cost</label>
                                <div class="col-md-9">
                                    <input name="cost" type="text" value="${book.cost}" class="form-control input-sm"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-3 control-label">Publisher</label>
                                <div class="col-md-9">
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
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-3 control-label">Author</label>
                                <div class="col-md-9">
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
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-3 control-label">Book Type</label>
                                <div class="col-md-9">
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
                                </div>
                            </div>    
                        </div>
                        <div class="panel-footer">
                            <div class="form-actions right1" style="padding: ">
                                <a href="Management?action=toAllBook"><input type="button" value="Cancel" class="btn btn-default"></a>
                                <button type="submit" class="btn btn-success">Update</button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>
