<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="model.Student"%>
<%@page import="DB.DB"%>
<%@ page import="model.Teacher" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>设置</title>
    <link rel="stylesheet" href="../../css/reset.css">
    <style>
        body{
            transform: scale(1.1);
        }
        .container{
            margin: 30px auto 20px;
            background-color: transparent;
            width: 850px;
            height: 780px;
        }
        .column1{
            margin: 20px;
            background-color: rgba(202,224,232);
            width: 800px;
            height: 300px;
            float: left;
        }
        .column2{
            margin-top: 10px;
            background-color:  transparent;
        }
        .column2 .column2-1{
            height: 350px;
            width: 360px;
            background-color: rgba(202,224,232);
            float: left;
            margin: 25px;

        }
        .column2 .column2-2{
            height: 350px;
            width: 360px;
            background-color: rgba(202,224,232);
            float: left;
            margin: 25px;
        }
        .clearfix::after{
            display: block;
            clear: both;
        }
    </style>
    <script src="../../js/jquery.min.js"></script>
    <script>
        $(document).ready(function() {
            $("#modify").click(function() {
                $("#modify").css("color","red");
                var email = $("input[name='email']").val();
                $.ajax({
                    type: "POST",
                    url: "ModifyMes", // 替换为您的Servlet URL
                    data: { "email": email },
                    // dataType:"json",
                    success: function(response) {
                        console.log('Ajax 请求成功！');
                        $("#Mes_info").val(response);
                    },
                    error: function() {
                        // 处理错误响应
                        console.log('Ajax 请求发生错误');

                    }
                });

            });
        });

        $(document).ready(function() {
            $("#ok").click(function() {
                $("#ok").css("color","red");
                var oldpwd = $("input[name='oldpwd']").val();
                var newpwd_1 = $("input[name='newpwd_1']").val();
                var newpwd_2 = $("input[name='newpwd_2']").val();
                $.ajax({
                    type: "POST",
                    url: "ModifyPwd", // 替换为您的Servlet URL
                    data: {
                        "oldpwd":oldpwd,
                        "newpwd_1":newpwd_1,
                        "newpwd_2":newpwd_2
                    },
                    // dataType:"json",
                    success: function(response) {
                        console.log('Ajax 请求成功！');
                        console.log(response);
                        $("#Pwd_info").val(response);
                    },
                    error: function() {
                        // 处理错误响应
                        console.log('Ajax 请求发生错误');

                    }
                });

            });
        });

        $(document).ready(function() {
            $("#cmt").click(function() {
                $("#cmt").css("color","red");
                var comment = document.getElementsByName("adv")[0].value;
                console.log(comment);
                $.ajax({
                    type: "POST",
                    url: "InsertCmt", // 替换为您的Servlet URL
                    data: {
                        "comment":comment
                    },
                    // dataType:"json",
                    success: function(response) {
                        console.log('Ajax 请求成功！');
                        console.log(response);
                        $("#Cmt_info").val(response);
                    },
                    error: function() {
                        // 处理错误响应
                        console.log('Ajax 请求发生错误');

                    }
                });

            });
        });
    </script>
</head>
<body>
<div class="container" align="center">
    <div class="column1 clearfix">
        <h3>修改密码</h3>
        <br><br>
        <form  class="modifyPwd">
            输入原密码:<input type="password" name="oldpwd" /><br><br>
            输入新密码:<input type="password" name="newpwd_1"/><br><br>
            确认新密码:<input type="password" name="newpwd_2"/><br><br>
            <button type="button" id="ok">确认</button>
            <input type="reset" value="重置">
            <input align="center" style="border: none; font-size: 14px;color:#333;background-color:rgb(202, 224, 232);" type="text" id="Pwd_info"/>
        </form>
    </div>
    <div class="column2 clearfix">
        <div class="column2-1">
            <h3>修改信息</h3>
            <br><br>
            <form class="modifyMes">
                <%
                        Teacher teacher=(Teacher)session.getAttribute("teacher");
                        %>
                姓名：<input type="text" readonly name="name" value=<%=teacher.getName()%>><br><br>
                工号：<input type="text" readonly name="id" value=<%=teacher.getId()%>><br><br>
                邮箱：<input type="text" name="email" value=<%=teacher.getEmail()%>><br><br>
                班级：<input type="text" readonly name="cls_id" value=<%=teacher.getCls_id()%>><br><br>
                <button type="button" id="modify">提交</button>
                <input align="center" style="border: none; font-size: 14px;color:#333;background-color:rgb(202, 224, 232);" type="text" id="Mes_info"/>
            </form>
        </div>
        <div class="column2-2">
            <h3>评语库维护</h3>
            <br><br>
            对本系统有什么意见或建议：<br><br><textarea name="adv" rows="5" cols="30" style=" width: 300px;height: 150px;font-family: Arial, sans-serif;font-size: 14px;"></textarea>
            <br><br><button type="button" id="cmt">提交</button>
            <input align="center" style="border: none; font-size: 14px;color:#333;background-color:rgb(202, 224, 232);" type="text" id="Cmt_info"/>

        </div>
    </div>
</div>
</body>
</html>