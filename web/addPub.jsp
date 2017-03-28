<%-- 
    Document   : addBook
    Created on : Mar 22, 2017, 11:35:19 PM
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
        <title>Add publisher</title>
    </head>
    <body>
        <jsp:include page="content/header.jsp"></jsp:include>
        <div class="container">
            <div class="panel panel-danger">
                <div class="panel-heading">Add new publisher</div>
                <div class="panel-body">
                    <form action="Management" method="POST" class="form-horizontal" role="form">
                        <input type="text" value="addPub" name="action" hidden="true"/>
                        <div class="form-body">
                            <div class="form-group">
                                <label class="col-md-3 control-label">Name</label>
                                <div class="col-md-9">
                                    <input name="name" type="text" class="form-control input-sm"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-3 control-label">Description</label>
                                <div class="col-md-9">
                                    <input name="description" type="text" class="form-control input-sm"/>
                                </div>
                            </div>
                        </div>
                        <div class="panel-footer">
                            <div class="form-actions right1" style="padding: ">
                                <a href="Management?action=toAllPub"><button type="button" class="btn btn-default">Cancel</button></a>
                                <button type="submit" class="btn btn-success">Submit</button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>
