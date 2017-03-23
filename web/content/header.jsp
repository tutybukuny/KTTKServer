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
<nav class="navbar navbar-inverse">
    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="Management?action=toAllBook">BookStore</a>
        </div>
        <div class="collapse navbar-collapse" id="myNavbar">
            <ul class="nav navbar-nav">
                <li class="active"><a href="index.jsp">Trang chủ</a></li> 
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <c:choose>
                    <c:when test="${sessionScope.human!=null}">
                        <li><a href="userHome.jsp">Chào ${sessionScope.human.name.firstName}</a></li>
                        <li><a href="Management?action=logout"><span class="glyphicon glyphicon-log-out"></span>Đăng xuất</a></li>
                        </c:when>
                        <c:otherwise>
                        <li><a href="signup.jsp"><span class="glyphicon glyphicon-user"></span> Đăng ký</a></li>
                        <li><a href="index.jsp"><span class="glyphicon glyphicon-log-in"></span> Đăng nhập</a></li>
                        </c:otherwise>
                    </c:choose>
            </ul>
        </div>
    </div>
</nav>