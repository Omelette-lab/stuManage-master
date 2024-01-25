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
                var stu_id = $('input[name="stu_id"]').val();
                var name = $('input[name="name"]').val();
                var age = $('input[name="age"]').val();
                var sex = $('input[name="sex"]:checked').val();
                var ethnicity = $('input[name="ethnicity"]').val();
                var poli_status = $('input[name="poli_status"]').val();
                var position = $('input[name="position"]').val();
                var cls_id = $('input[name="cls_id"]').val();
                var dor_id = $('input[name="dor_id"]').val();

                $('.theme-signin input[name="submit"]').css("color","red");

                $.ajax({
                    type: "GET", // 或者 "GET"，根据实际情况选择
                    url: "AddStudent",
                    data: {
                        "stu_id":stu_id ,
                        "name": name,
                        "age" : age,
                        "sex":sex,
                        "ethnicity":ethnicity,
                        "poli_status":poli_status,
                        "position":position,
                        "cls_id":cls_id,
                        "dor_id":dor_id,
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
            $("#SearchStuInfo").click(function () {
                $("#SearchStuInfo").css("color", "red");
                var keyword = $("input[name='keyword']").val();
                $.ajax({
                    type: "GET",
                    url: "SearchAllStuInfo", // 替换为您的Servlet URL
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
                    var stu_id = $(this).data('delete_stu_id');
                    $.ajax({
                        type: "GET", // 或者 "GET"，根据实际情况选择
                        url: "DeleteStudent",
                        data: {
                            "stu_id": stu_id,
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
    <div class="public-nav">您当前的位置：<a href="">学生信息</a>><a href="">学生档案</a></div>
    <div class="public-content">
        <div class="public-content-header">
            <h3>修改信息</h3>
            <div class="search">
                <form action="../../SearchAllStuInfo" method="get">
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
                    ArrayList allStuAL=(ArrayList)session.getAttribute("Student");
                    Iterator iter=allStuAL.iterator();
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
                            <a class="btn btn-primary btn-large delete" href="javascript:;"
                               data-delete_stu_id="<%= stu.getId() %>">删除</a>
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
                <li><strong>学号：</strong><input class="ipt" type="text" name="stu_id" size="20" /></li>
                <li><strong>姓名：</strong><input class="ipt" type="text" name="name" size="20" /></li>
                <li><strong>年龄：</strong><input class="ipt" type="text" name="age" size="20" /></li>
                <li><div class="radio">
                    <strong>性别：</strong><input type="radio" value="男" name="sex">男
                    <input type="radio" value="女" name="sex">女
                </div></li>
                <li><strong>民族：</strong><input class="ipt" type="text" name="ethnicity" size="20" /></li>
                <li><strong>政治面貌：</strong><input class="ipt" type="text" name="poli_status" size="20" /></li>
                <li><strong>职位：</strong><input class="ipt" type="text" name="position" size="20" /></li>
                <li><strong>班级：</strong><input class="ipt" type="text" name="cls_id" size="20" /></li>
                <li><strong>宿舍号：</strong><input class="ipt" type="text" name="dor_id" size="20" /></li>
                <li><input class="btn btn-primary" type="button" name="submit" value=" 提交 " /></li>
                <input align="center" style="border: none; font-size: 14px;color:#333;" type="text" id="info2"/>
            </ol>
        </form>
    </div>
</div>
</body>
</html>