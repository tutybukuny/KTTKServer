<%-- 
    Document   : index
    Created on : Mar 21, 2017, 10:44:58 PM
    Author     : TienDuc
--%>

<%@page import="Model.Account"%>
<%@page import="Control.HumanControl"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Sign Up</title>
    </head>
    <body>
        <jsp:include page="content/header.jsp"></jsp:include>
            <div class="container">
                <div class="panel panel-danger">
                    <div class="panel-heading">Đăng ký</div>
                    <div class="panel-body">
                        <form id="formsignup" action="/KTTKServer/Management" method="POST" class="form-horizontal" role="form">
                            <input type="text" value="signup" name="action" hidden="true"/>
                            <div class="form-body">
                                <div class="form-group">
                                    <label class="col-md-3 control-label">Họ và tên</label>
                                    <div class="col-md-9 form-inline">
                                        <input id="firstname" name="firstname" type="text" class="form-control" placeholder="Họ" value="${human.name.firstName}"/>
                                        <input name="middlename" type="text" class="form-control" placeholder="Tên Đệm" value="${human.name.middleName}"/>
                                        <input id="lastname" name="lastname" type="text" class="form-control" placeholder="Tên" value="${human.name.lastName}" />
                                        <p class="text-danger" id="fullname_error"></p>
                                    </div>

                                </div>
                                <div class="form-group">
                                    <label class="col-md-3 control-label">Địa chỉ</label>
                                    <div class="col-md-9 form-inline">
                                        <input name="number" type="text" class="form-control" placeholder="Số nhà" value="${human.address.number}"/>
                                        <input name="street" type="text" class="form-control" placeholder="Đường" value="${human.address.street}"/>
                                        <input id="city" name="city" type="text" class="form-control" placeholder="Thành phố" value="${human.address.city}"/>
                                        <p class="text-danger" id="add_error"></p>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-md-3 control-label">Ngày sinh</label>
                                    <div class="col-md-9 form-inline" >
                                        <input id="day" name="day" type="text" class="form-control" placeholder="Ngày" value="${human.birthday.day}"/>
                                        <select class="form-control" name="mounth">
                                            <option value="January">January</option>
                                            <option value="February">February</option>
                                            <option value="January">January</option>
                                            <option value="March">March</option>
                                            <option value="April">April</option>
                                            <option value="May">May</option>
                                            <option value="June">June</option>
                                            <option value="July">July</option>
                                            <option value="August">August</option>
                                            <option value="October">October</option>
                                            <option value="November">November</option>
                                            <option value="December">December</option>
                                        </select>
                                        <input id="year" name="year" type="text" class="form-control" placeholder="Năm" value="${human.birthday.year}"/>
                                        <p class="text-danger" id="date_error"></p>
                                    </div>

                                </div>
                                <div class="form-group">
                                    <label class="col-md-3 control-label">Chức vụ</label>
                                    <div class="col-md-3">
                                        <select name="dis" class="form-control">
                                            <option value="Nhân viên">Nhân viên</option>
                                            <option value="Khách hàng">Khách hàng</option>
                                        </select>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-md-3 control-label">Username</label>
                                    <div class="col-md-3">
                                        <input id="username" name="username" type="text" class="form-control" placeholder="Username" value="${human.acc.username}"/>
                                        <p class="text-danger" id="username_error">${errorusername}</p>
                                </div>

                            </div>
                            <div class="form-group">
                                <label class="col-md-3 control-label">Password</label>
                                <div class="col-md-3">
                                    <input id="password" name="password" type="password" class="form-control" placeholder="Password"/>
                                    <p class="text-danger" id="password_error"></p>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-3 control-label">Re-password</label>
                                <div class="col-md-3">
                                    <input id="repassword" name="repassword" type="password" class="form-control" placeholder="Re-Password"/>
                                    <p class="text-danger" id="repassword_error"></p>
                                </div>
                            </div>
                        </div>
                        <div class="panel-footer">
                            <div class="form-actions right1" style="padding: ">
                                <input type="reset" class="btn btn-default"></button>
                                <button type="submit" class="btn btn-success">Đăng ký</button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </body>
    <script>

        $("#formsignup").submit(function () {
            var username = $.trim($("#username").val());
            var password = $.trim($("#password").val());
            var firstname = $.trim($("#firstname").val());
            var lastname = $.trim($("#lastname").val());
            var password = $.trim($("#password").val());
            var repassword = $.trim($("#repassword").val());
            var day = $.trim($("#day").val());
            var year = $.trim($("#year").val());
            var city = $.trim($("#city").val());
            // Biến cờ hiệu
            var flag = true;
            if (day === '' || year === '') {
                $('#date_error').text('Bạn phải nhập ngày, tháng, năm sinh');
                flag = false;
            } else {
                $('#date_error').text('');
            }
            if (city === '') {
                $('#add_error').text('Bạn phải nhập thành phố');
                flag = false;
            } else {
                $('#add_error').text('');
            }
            // Username
            if (username === '' || username.length < 4) {
                $('#username_error').text('Tên đăng nhập phải lớn hơn 4 ký tự');
                flag = false;
            } else {
                $('#username_error').text('');
            }
            if (firstname === '' || lastname === "") {
                $('#fullname_error').text('Bạn phải nhập họ và tên đệm');
                flag = false;
            } else {
                $('#fullname_error').text('');
            }
            // Password
            if (password.length <= 0) {
                $('#password_error').text('Bạn phải nhập mật khẩu');
                flag = false;
            } else {
                $('#password_error').text('');
            }

            if (repassword.length <= 0) {
                $('#repassword_error').text('Bạn phải nhập lại mật khẩu');
                flag = false;
            } else {
                $('#repassword_error').text('');
            }
            if (repassword !== password) {
                $('#repassword_error').text('Nhập khẩu nhập lại không đúng');
                flag = false;
            } else {
                $('#repassword_error').text('');
            }
            return flag;
        });
    </script>
</html>
