<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="model.Student"%>
<%@page import="DB.DB"%>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>学生档案</title>
    <link rel="stylesheet" href="../../css/reset.css" />
    <link rel="stylesheet" href="../../css/content.css" />
    <link rel="stylesheet" href="../../css/search.css" />
    <link rel="stylesheet" href="../../css/youtiy.css" media="all">
    <script src="../../js/jquery.min.js"></script>
    <script>
        jQuery(document).ready(function($) {
            $('.modify').click(function(){
                // 获取当前行数据
                var stu_id = $(this).data('stu_id');
                var name = $(this).data('name');
                var age = $(this).data('age');
                var sex = $(this).data('sex');
                var ethnicity = $(this).data('ethnicity');
                var poli_status = $(this).data('poli_status');

                // 填充表单字段
                $('input[name="stu_id"]').val(stu_id);
                $('input[name="name"]').val(name);
                $('input[name="age"]').val(age);
                $('input[name="sex"]').val(sex);
                $('input[name="ethnicity"]').val(ethnicity);
                $('input[name="poli_status"]').val(poli_status);

                // 打开修改弹窗
                $('.modify-popover').slideDown(200);
                $('.add-popover').slideUp(200);
            })
            $('.modify-popover .close').click(function(){
                // 关闭修改弹窗
                $('.modify-popover').slideUp(200);
            })
        })

        $(document).ready(function() {
            $('.theme-signin input[name="submit"]').click(function() {
                var stu_id = $('input[name="stu_id"]').val();
                var name = $('input[name="name"]').val();
                var age = $('input[name="age"]').val();
                var sex = $('input[name="sex"]').val();
                var ethnicity = $('input[name="ethnicity"]').val();
                var poli_status = $('input[name="poli_status"]').val();

                $('.theme-signin input[name="submit"]').css("color","red");

                $.ajax({
                    type: "GET", // 或者 "GET"，根据实际情况选择
                    url: "ModifyStudent",
                    data: {
                        "stu_id":stu_id,
                        "name":name,
                        "age":age,
                        "sex":sex,
                        "ethnicity":ethnicity,
                        "poli_status":poli_status,
                    },
                    success: function(response) {
                        // 处理成功响应
                        console.log('Ajax 请求成功！');
                        console.log(response);
                        $("#info").val(response);
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
            $("#SearchStuInfo").click(function () {
                $("#SearchStuInfo").css("color", "red");
                var keyword = $("input[name='keyword']").val();
                $.ajax({
                    type: "GET",
                    url: "SearchStuInfo", // 替换为您的Servlet URL
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
        });
    </script>
</head>
<body marginwidth="0" marginheight="0">
<div class="container">
    <div class="public-nav">您当前的位置：<a href="">学生信息</a>><a href="">学生档案</a></div>
    <div class="public-content">
        <div class="public-content-header">
            <h3>修改信息</h3>
            <div class="search">
                <form action="../../SearchStuInfo" method="get">
                    <input type="hidden" name="method" value="SearchStuInfo" />
                    <input placeholder="请输入..." name="keyword" type="text" value="">
                    <button type="button" id="SearchStuInfo"></button>
                </form>
            </div>

        </div>
        <br><br><br><br>
        <div class="public-content-cont">
            <table class="public-cont-table">

                <tr>
                    <th style="width:15%">学号</th>
                    <th style="width:15%">姓名</th>
                    <th style="width:10%">年龄</th>
                    <th style="width:20%">性别</th>
                    <th style="width:20%">民族</th>
                    <th style="width:20%">政治面貌</th>
                    <th style="width:20%">操作</th>
                </tr>
                <%
                    ArrayList stu_al=(ArrayList)session.getAttribute("StuAL");
                    Iterator iter=stu_al.iterator();
                    while(iter.hasNext()){
                        Student stu=(Student) iter.next();
                %>
                <tr><td><%= stu.getId() %></td>
                    <td><%= stu.getName() %></td>
                    <td><%= stu.getAge() %></td>
                    <td><%= stu.getSex() %></td>
                    <td><%= stu.getEthnicity() %></td>
                    <td><%= stu.getPoli_status() %></td>
                    <td>
                        <div class="table-fun">
                            <a class="btn btn-primary btn-large modify" href="javascript:;"
                               data-stu_id="<%= stu.getId() %>"
                               data-name="<%= stu.getName() %>"
                               data-age="<%= stu.getAge() %>"
                               data-sex="<%= stu.getSex() %>"
                               data-ethnicity="<%= stu.getEthnicity() %>"
                               data-poli_status="<%= stu.getPoli_status() %>">
                                修改
                            </a>
                        </div>
                    </td>
                </tr>
                <%}%>
            </table>
        </div>
    </div>
</div>
<div class="theme-popover modify-popover">
    <div class="theme-poptit">
        修改信息
        <a href="javascript:;" title="关闭" class="close">×</a>
    </div>
    <div class="theme-popbod dform">
        <form class="theme-signin" name="modifyform">
            <ol>
                <li><strong>性别：</strong><input  type="radio" name="sex" value="男"  />男
                    <input type="radio" name="sex" value="女"/>女</li>
                <li><strong>学号：</strong><input class="ipt" type="text" name="stu_id" size="20" /></li>
                <li><strong>姓名：</strong><input class="ipt" type="text" name="name" size="20" /></li>
                <li><strong>年龄：</strong><input class="ipt" type="text" name="age" size="20" /></li>
                <li><strong>民族：</strong><input class="ipt" type="text" name="ethnicity" size="20" /></li>
                <li><strong>政治面貌：</strong><input class="ipt" type="text" name="poli_status" size="20" /></li>
                <li><input class="btn btn-primary" type="button" name="submit" value=" 提交 " /></li>
                <input align="center" style="border: none; font-size: 14px;color:#333;" type="text" id="info"/>
            </ol>
        </form>
    </div>
</div>
</body>
</html>