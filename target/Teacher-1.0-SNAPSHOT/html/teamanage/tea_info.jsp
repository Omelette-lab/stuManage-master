<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="model.Student"%>
<%@page import="DB.DB"%>
<%@ page import="model.Teacher" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>教师管理</title>
    <link rel="stylesheet" href="../../css/reset.css" />
    <link rel="stylesheet" href="../../css/content.css" />
    <link rel="stylesheet" href="../../css/search.css" />
    <link rel="stylesheet" href="../../css/youtiy.css" media="all">
    <script src="../../js/jquery.min.js"></script>
    <script>
        jQuery(document).ready(function($) {
            $('.add').click(function(){

                // 打开添加弹窗
                $('.add-popover').slideDown(200);
                $('.modify-popover').slideUp(200);
            })

            $('.add-popover .close').click(function(){
                // 关闭添加弹窗
                $('.add-popover').slideUp(200);
            })
        })

        $(document).ready(function() {
            $('.theme-add input[name="submit"]').click(function() {
                var tea_id = $('input[name="tea_id"]').val();
                var name = $('input[name="name"]').val();
                var pwd = $('input[name="pwd"]').val();
                var mail = $('input[name="mail"]').val();
                var cls_id = $('input[name="cls_id"]').val();

                $('.theme-add input[name="submit"]').css("color","red");

                $.ajax({
                    type: "GET", // 或者 "GET"，根据实际情况选择
                    url: "AddTeacher",
                    data: {
                        "tea_id":tea_id ,
                        "name": name,
                        "pwd" : pwd,
                        "mail" : mail,
                        "cls_id":cls_id,
                    },
                    success: function(response) {
                        // 处理成功响应
                        console.log('Ajax 请求成功！');
                        console.log(response);
                        $("#info2").val(response);
                        var table = $(".public-cont-table");
                        table.load(document.URL + " .public-cont-table");//重新加载表格
                        // 在这里可以更新页面数据或做其他操作
                    },
                    error: function() {
                        // 处理错误响应
                        console.log('Ajax 请求发生错误');
                    }
                });
            });
        });

        $(document).ready(function () {
            $("#SearchTeacher").click(function () {
                $("#SearchTeacher").css("color", "red");
                var keyword = $("input[name='keyword']").val();
                $.ajax({
                    type: "GET",
                    url: "SearchTeacher", // 替换为您的Servlet URL
                    data: { "keyword": keyword },
                    // dataType:"json",
                    success: function () {
                        console.log('Ajax 请求成功！');
                        var table = $(".public-cont-table");
                        table.load(document.URL + " .public-cont-table");//重新加载表格
                    },
                    error: function () {
                        // 处理错误响应
                        console.log('Ajax 请求发生错误');

                    }
                });
            });

            jQuery(document).ready(function($) {
                $('.delete').click(function(){
                    var tea_id = $(this).data('delete_tea_id');
                    $.ajax({
                        type: "GET", // 或者 "GET"，根据实际情况选择
                        url: "DeleteTeacher",
                        data: {
                            "tea_id": tea_id,
                        },
                        success: function(response) {
                            // 处理成功响应
                            console.log('Ajax 请求成功！');
                            console.log(response);
                            alert(response);
                            var table = $(".public-cont-table");
                            table.load(document.URL + " .public-cont-table");//重新加载表格
                            // 在这里可以更新页面数据或做其他操作
                        },
                        error: function() {
                            // 处理错误响应
                            console.log('Ajax 请求发生错误');
                        }
                    });
                })
            })
        });
    </script>
</head>
<body marginwidth="0" marginheight="0">
<div class="container">
    <div class="public-nav">您当前的位置：<a href="">教师管理</a>><a href="">教师档案</a></div>
    <div class="public-content">
        <div class="public-content-header">
            <h3>修改信息</h3>
            <div class="search">
                <form action="../../SearchTeacher" method="get">
                    <input type="hidden" name="method" value="SearchTeacher" />
                    <input placeholder="请输入..." name="keyword" type="text" value="">
                    <button type="button" id="SearchTeacher"></button>
                </form>
            </div>

        </div>
        <br><br><br><br>
        <div class="public-content-cont">
            <table class="public-cont-table">

                <tr>
                    <th style="width:15%">工号</th>
                    <th style="width:15%">姓名</th>
                    <th style="width:20%">邮箱</th>
                    <th style="width:20%">班级</th>
                    <th style="width:20%">操作</th>
                </tr>
                <%
                    ArrayList TeacherAL=(ArrayList)session.getAttribute("Teacher");
                    Iterator iter=TeacherAL.iterator();
                    while(iter.hasNext()){
                        Teacher teacher=(Teacher) iter.next();
                %>
                <tr><td><%= teacher.getId() %></td>
                    <td><%= teacher.getName() %></td>
                    <td><%= teacher.getEmail() %></td>
                    <td><%= teacher.getCls_id() %></td>
                    <td>
                        <div class="table-fun">
                            <a class="btn btn-primary btn-large delete" href="javascript:;"
                               data-delete_tea_id="<%= teacher.getId() %>">删除</a>
                        </div>
                    </td>
                </tr>
                <%}%>
            </table>
        </div>
    </div>
    <div class="table-fun">
        <a class="btn btn-primary btn-large add" href="javascript:;">
            添加
        </a>
    </div>
</div>
<div class="theme-popover add-popover">
    <div class="theme-poptit">
        添加信息
        <a href="javascript:;" title="关闭" class="close">×</a>
    </div>
    <div class="theme-popbod dform">
        <form class="theme-add" name="addform" >
            <ol>
                <li><strong>工号：</strong><input class="ipt" type="text" name="tea_id" size="20" /></li>
                <li><strong>姓名：</strong><input class="ipt" type="text" name="name" size="20" /></li>
                <li><strong>登录密码：</strong><input class="ipt" type="text" name="pwd" size="20" /></li>
                <li><strong>邮箱：</strong><input class="ipt" type="text" name="mail" size="20" /></li>
                <li><strong>班级：</strong><input class="ipt" type="text" name="cls_id" size="20" /></li>
                <li><input class="btn btn-primary" type="button" name="submit" value=" 提交 " /></li>
                <input align="center" style="border: none; font-size: 14px;color:#333;" type="text" id="info2"/>
            </ol>
        </form>
    </div>
</div>
</body>
</html>