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
        <title>JSP Page</title>
    </head>
    <body>
        <form method="POST" name = "UpdateB" action="/Management" >
            <input type="text" value="doUpdate" hidden="true"/>
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
                        <input type="text" class="form-control" name="id" value="${book.name}"/>
                    </td> 
                </tr> 
                <tr>
                    <td width="30%">Description</td>
                    <td width="70%">
                        <input type="text" class="form-control" name="id" value="${book.description}"/>
                    </td> 
                </tr> 
                <tr>
                    <td width="30%">Cost</td>
                    <td width="70%">
                        <input type="text" class="form-control" name="id" value="${book.cost}"/>
                    </td> 
                </tr> 
                <tr>
                    <td width="30%">Publisher</td>
                    <td width="70%">
                        <select>
                            <c:forEach items="${publishers}" var="pub">
                                <option value="${pub.ID}">${pub.name}</option>
                            </c:forEach>
                        </select>
                    </td> 
                </tr> 
            </table>     
        </form>
    </body>
</html>
