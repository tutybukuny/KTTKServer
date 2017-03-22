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
    <body>
        <ul class="nav nav-tabs" role="tablist">
            <li class="active"><a href="index.jsp">All Books</a></li>
            <li><a href="addBook.jsp">Add Book</a></li>
            <li><a href="search.jsp">Search</a></li>           

        </ul>
        <h3 align="center">All Books</h3>
        <table  class="table table-hover">
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
            <%

                ArrayList<Book> books = (ArrayList<Book>) request.getAttribute("books");
<<<<<<< HEAD
                for (Book book : books) {
=======
                session.setAttribute("books", books);
                for (int i = 0; i < books.size(); i++) {
                    Book book = books.get(i);
>>>>>>> remotes/origin/TienDuc
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
<<<<<<< HEAD
                    <a href="updateBook.jsp?id=<%=book.getID()%>"><span class="glyphicon glyphicon-pencil"></span></a>
                </td>
                <td>
                    <a href="confirmDelete.jsp?id=<%=book.getID()%>"><span class="glyphicon glyphicon-remove"></span></a>
=======
                    <a href="/KTTKServer/Management?index=<%=i%>&action=update"><span class="glyphicon glyphicon-pencil"></span></a>
                </td>
                <td>
                    <a href="/KTTKServer/Management?index=<%=i%>&action=delete"><span class="glyphicon glyphicon-remove"></span></a>
>>>>>>> remotes/origin/TienDuc
                </td>
            </tr>
            <%
                }
            %>
        </table>
    </body>
</html>
