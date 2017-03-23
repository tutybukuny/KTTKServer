<%-- 
    Document   : _menu
    Created on : May 5, 2016, 8:51:30 PM
    Author     : tutyb_000
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<meta charset="utf-8">
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<nav class="navbar navbar-inverse">
    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#">BookStore</a>
        </div>
        <div class="collapse navbar-collapse" id="myNavbar">
            <ul class="nav navbar-nav">
                <li class="active"><a href="">Trang chủ</a></li> 
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <c:choose>
                    <c:when test="${user.fullname!=null}">
                        <li><a href="#">Chào ${user.fullname}</a></li>
                        <li><a href="User?action=logout"><span class="glyphicon glyphicon-log-out"></span>Đăng xuất</a></li>
                        </c:when>
                        <c:otherwise>
                        <li><a href="#"><span class="glyphicon glyphicon-user"></span> Đăng ký</a></li>
                        <li><a href="/BTLLTM"><span class="glyphicon glyphicon-log-in"></span> Đăng nhập</a></li>
                        </c:otherwise>
                    </c:choose>
            </ul>
        </div>
    </div>
</nav>