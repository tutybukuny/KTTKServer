<%-- 
    Document   : deleteBook
    Created on : Mar 23, 2017, 12:01:47 AM
    Author     : TienDuc
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.ArrayList"%>
<%@page import="Model.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Delete Book Type</title>
    </head>
    <body>
        <jsp:include page="content/header.jsp"></jsp:include>
        <div class="container">
            <div class="panel panel-danger">
                <div class="panel-heading">Delete Book Type</div>
                <div class="panel-body">
                    <form action="/KTTKServer/BookTypeManagement" method="POST" class="form-horizontal" role="form">
                        <input type="text" value="${bookType.ID}" name="bookTypeID" hidden="true"/>
                        <input type="text" value="confirmDelete" name="action" hidden="true"/>
                        <div class="form-body">
                            <div class="form-group">
                                <label class="col-md-3 control-label">Name</label>
                                <div class="col-md-9">
                                    <input name="name" type="text" value="${bookType.name}" readonly="true" class="form-control input-sm"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-3 control-label">Description</label>
                                <div class="col-md-9">
                                    <input name="description" type="text" value="${bookType.description}" readonly="true" class="form-control input-sm"/>
                                </div>
                            </div>
                           
                        </div>
                        <div class="panel-footer">
                            <div class="form-actions right1" style="padding: ">                    
                                <button type="submit" class="btn btn-danger">Delete</button>
                                <a href="BookTypeManagement?action=toAllType"><input type="button" value="Cancel" class="btn btn-default"></a>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>
