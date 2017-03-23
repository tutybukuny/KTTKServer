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
        <title>All Books</title>
        <link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">
        <script src="bootstrap/js/jquery-2.2.3.min.js"></script>
        <script src="bootstrap/js/bootstrap.min.js"></script>
    </head>
        <jsp:include page="content/header.jsp"></jsp:include>
        <div class="container">
            <div class="panel panel-primary">
                <div class="panel-heading">
                    <div class="row">
                        <div class="col-md-10" style="padding-top: 5px">
                            <p>Danh sách các cuốn sách</p>
                        </div>
                        <div class="col-md-1">
                            <a href='Management?action=toAddBook' class="btn btn-success"><span class="glyphicon glyphicon-plus"></span>Thêm</a>
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
                                    <td><b>Cost</b></td>
                                    <td><b>Publisher</b></td>
                                    <td><b>Author</b></td>
                                    <td><b>Type</b></td>
                                    <td></td>
                                    <td></td>
                                </tr>
                            </thead>
                            <tbody>
                                <%

                                    ArrayList<Book> books = (ArrayList<Book>) session.getAttribute("books");
                                    for (int i = 0; i < books.size(); i++) {
                                        Book book = books.get(i);
                                %>
                                <tr>

                                    <td><%=book.getID()%></td> 
                                    <td><%=book.getName()%></td>
                                    <td><%=book.getDescription()%></td>
                                    <td><%=book.getCost()%></td>
                                    <td><%=book.getPublisher().getName()%></td>
                                    <td><%=book.getAuthor().getName()%></td>
                                    <td><%=book.getBookType().getName()%></td>
                                    <td>
                                        <a href="/KTTKServer/Management?index=<%=i%>&action=update" class="btn btn-primary"><span class="glyphicon glyphicon-edit"></span></a>
                                    </td>
                                    <td>
                                        <a href="/KTTKServer/Management?index=<%=i%>&action=delete" class="btn btn-danger"><span class="glyphicon glyphicon-trash"></span></a>
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
