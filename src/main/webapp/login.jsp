<%--
  Created by IntelliJ IDEA.
  User: DELL
  Date: 2023/7/21
  Time: 18:31
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false"%>
<html>
<head>
    <title>班主任工作管理系统登录页面</title>
    <style>
        @import "css/login.css";
    </style>
</head>
<body>
<div class="container">
    <div class="form-box">
        <!-- 登录 -->
        <div class="login-box">
            <h1>登录</h1>
            <form class="login-box" action="Login" method="post"> <!-- 添加表单标签和提交地址 -->
                <input type="text" placeholder="用户名" name="id">
                <input type="password" placeholder="密码" name="pwd">
                <div class="radio">
                    身份：<input type="radio" value="teacher" name="identity" checked>班主任<input type="radio" value="admin" name="identity">管理员
                </div>
                <button type="submit">登录</button> <!-- 修改按钮类型为 submit -->
                <button type="reset" >重置</button> <!-- 保留重置按钮 -->
                <div align="center">
                    ${login_Error}
                </div>
            </form>
        </div>
        <!--找回密码 -->
        <div class="getback-box hidden">
            <h1>找回密码</h1>
            <form class="getback-box"> <!-- 添加表单标签和提交地址 -->
                <input type="text" placeholder="用户名" name="id2">
                <input type="email" placeholder="邮箱" name="email">
                <input type="password" placeholder="新的密码" name="newPwd">
                <input type="password" placeholder="确认密码" name="confirmPwd">
                <button type="button" id="ok">确认</button> <!-- 修改按钮类型为 submit -->
                <button type="reset" >重置</button> <!-- 保留重置按钮 -->
                <input align="center" type="text" id="info"/>
            </form>
        </div>
    </div>
    <div class="con-box left">
        <h2>班主任<span>工作管理系统</span></h2>
        <p>您的班级管理<span>好帮手</span></p>
        <img src="res/1.jpg" alt="">
        <p>已找回密码？</p>
        <button id="login">去登录</button>
    </div>
    <div class="con-box right">
        <h2>班主任<span>工作管理系统</span></h2>
        <p>您的班级管理<span>好帮手</span></p>
        <img src="res/2.jpg" alt="">
        <p>忘记密码？</p>
        <button id="getback">去找回</button>
    </div>
</div>
<script src="js/jquery.min.js"></script>
<script>
    // 要操作到的元素
    let login=document.getElementById('login');
    let getback=document.getElementById('getback');
    let form_box=document.getElementsByClassName('form-box')[0];
    let getback_box=document.getElementsByClassName('getback-box')[0];
    let login_box=document.getElementsByClassName('login-box')[0];
    let ok=document.getElementById('ok');
    // 去注册按钮点击事件
    getback.addEventListener('click',()=>{
        form_box.style.transform='translateX(95%)';
        login_box.classList.add('hidden');
        getback_box.classList.remove('hidden');
    })
    // 去登录按钮点击事件
    login.addEventListener('click',()=>{
        form_box.style.transform='translateX(0%)';
        getback_box.classList.add('hidden');
        login_box.classList.remove('hidden');
    })

    $(document).ready(function() {
        $('#ok').click(function() {

            // 创建一个对象来存储表单数据
            var formData = {
                id: $('input[name="id2"]').val(),
                email: $('input[name="email"]').val(),
                newPwd: $('input[name="newPwd"]').val(),
                confirmPwd: $('input[name="confirmPwd"]').val()
            };

            // 发起 Ajax 请求
            $.ajax({
                url: 'Retrieve', // 替换为你的 Servlet 地址
                type: 'GET', // 使用 GET方法发送数据
                dataType:"json",
                data: formData, // 要发送的数据
                success: function(response) {
                    // 处理成功响应
                    console.log('Ajax 请求成功！');
                    $("#info").val(response.info);
                },
                error: function() {
                    // 处理错误响应
                    console.log('Ajax 请求发生错误');
                }
            });
        });
    });

</script>

</body>
</html>
