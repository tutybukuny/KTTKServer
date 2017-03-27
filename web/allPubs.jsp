<%-- 
    Document   : allBooks
    Created on : Mar 22, 2017, 12:03:14 PM
    Author     : TienDuc
--%>
<%@page import="Model.*"%>
<%@page import="Control.*"%>
<%@page import="DAO.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page language="java" import = "java.util.*, java.awt.*, entity.*, dao.*"%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>All Publishers</title>
    </head>
        <jsp:include page="content/header.jsp"></jsp:include>
        <div class="container">
            <div class="panel panel-danger">
                <div class="panel-heading">
                    <div class="row">
                        <div class="col-md-10" style="padding-top: 5px">
                            <p>List Publishers</p>
                        </div>
                        <div class="col-md-1">
                            <a href="PublisherManagement?action=toAddPub" class="btn btn-success"><span class="glyphicon glyphicon-plus"></span>Add</a>
                        </div>
                    </div>
                </div>
                <div class="panel-body">
                    <div class="table-responsive">
                        <table class="table">
                            <thead>
                                <tr>
                                    <td><b>ID</b></td>
                                    <td><b>Name</b></td>
                                    <td><b>Description</b></td>
                                    <td></td>
                                    <td></td>
                                </tr>
                            </thead>
                            <tbody>
                                <%

                                    ArrayList<Publisher> publishers = (ArrayList<Publisher>) session.getAttribute("publishers");
                                    for (int i = 0; i < publishers.size(); i++) {
                                        Publisher publisher = publishers.get(i);
                                %>
                                <tr>

                                    <td><%=publisher.getID()%></td> 
                                    <td><%=publisher.getName()%></td>
                                    <td><%=publisher.getDescription()%></td>
                                    <td>
                                        <a href="/KTTKServer/PublisherManagement?index=<%=i%>&action=update" class="btn btn-primary"><span class="glyphicon glyphicon-edit"></span></a>
                                    </td>
                                    <td>
                                        <a href="/KTTKServer/PublisherManagement?index=<%=i%>&action=delete" class="btn btn-danger"><span class="glyphicon glyphicon-trash"></span></a>
                                    </td>
                                </tr>
                                <%
                                    }
                                %>
                            </tbody>
                        </table>
                    </div>
                    <div class="panel-footer">

                    </div>  
                </div>
            </div>
        </div>
    </body>
</html>
