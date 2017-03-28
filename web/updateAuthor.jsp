<%-- 
    Document   : updateBook
    Created on : Mar 22, 2017, 4:17:50 PM
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
        <title>Update Author</title>
    </head>
    <body>
        <jsp:include page="content/header.jsp"></jsp:include>
            <div class="container">
                <div class="panel panel-danger">
                    <div class="panel-heading">Update Author</div>
                    <div class="panel-body">
                        <form action="/KTTKServer/Management" method="POST" class="form-horizontal" role="form">
                            <input type="text" value="${author.ID}" name="authorID" hidden="true"/>
                        <input type="text" value="confirmUpdateAuthor" name="action" hidden="true"/>
                        <div class="form-body">
                            <div class="form-group">
                                <label class="col-md-3 control-label">Name</label>
                                <div class="col-md-9">
                                    <input name="name" type="text" value="${author.name}" readonly="true" class="form-control input-sm"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-3 control-label">Description</label>
                                <div class="col-md-9">
                                    <input name="description" type="text" value="${author.description}" class="form-control input-sm"/>
                                </div>
                            </div>
                            <div class="panel-footer">
                                <div class="form-actions right1" style="padding: ">
                                    <button type="submit" class="btn btn-success">Update</button>
                                    <a href="Management?action=toAllAuthor"><input type="button" value="Cancel" class="btn btn-default"></a>
                                    
                                </div>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>
