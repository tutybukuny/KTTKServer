<%-- 
    Document   : _menu
    Created on : May 5, 2016, 8:51:30 PM
    Author     : tutyb_000
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<meta charset="utf-8">
<link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">
<script src="bootstrap/js/jquery-2.2.3.min.js"></script>
<script src="bootstrap/js/bootstrap.min.js"></script>
<style>
    ul {
        list-style-type: none;
        margin: 0;
        padding: 0;
        overflow: hidden;
        background-color: #CC1D1D;
        margin-bottom:50px;
    }

    li {
        float: left;
    }

    li a {
        display: inline-block;
        color: white;
        text-align: center;
        padding: 14px 16px;
        text-decoration: none;
    }

    li a:hover, .dropdown:hover{
        background-color: #E43E3E;
        color: #ffffff;
        text-decoration: none;
    }
</style>

<ul>
    <li><a style="font-size: medium" href="Management?action=toAllBook">BookStore</a></li>
    <li><a href="index.jsp">Trang chủ</a></li>
        <c:choose>
            <c:when test="${sessionScope.human!=null}">

            <li style="float:  right"><a href="Management?action=logout"><span class="glyphicon glyphicon-log-out"></span>Đăng xuất</a></li>
            <li style="float:  right"><a href="userHome.jsp">Chào ${sessionScope.human.name.firstName}</a></li>
            </c:when>
            <c:otherwise>
            <li  style="float:  right"><a href="signup.jsp"><span class="glyphicon glyphicon-user"></span> Đăng ký</a></li>
            <li  style="float:  right"><a href="index.jsp"><span class="glyphicon glyphicon-log-in"></span> Đăng nhập</a></li>
            </c:otherwise>
        </c:choose>
</ul>