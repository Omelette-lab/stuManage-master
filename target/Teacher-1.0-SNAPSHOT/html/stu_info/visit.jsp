<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="model.Student"%>
<%@page import="DB.DB"%>
<%@ page import="model.Visit" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>家访记录</title>
    <link rel="stylesheet" href="../../css/reset.css" />
    <link rel="stylesheet" href="../../css/content.css" />
    <link rel="stylesheet" href="../../css/search.css" />
    <link rel="stylesheet" href="../../css/youtiy.css" media="all">
    <script src="../../js/jquery.min.js"></script>
    <script type="text/javascript">
        jQuery(document).ready(function($) {
            $('.modify').click(function(){
                // 获取当前行数据
                var stu_id = $(this).data('stu_id');
                var name = $(this).data('name');
                var date = $(this).data('date');
                var content = $(this).data('content');

                // 填充表单字段
                $('input[name="stu_id"]').val(stu_id);
                $('input[name="name"]').val(name);
                $('input[name="date"]').val(date);
                $('input[name="content"]').val(content);

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
                var date = $('input[name="date"]').val();
                var content = $('input[name="content"]').val();

                $('.theme-signin input[name="submit"]').css("color","red");

                $.ajax({
                    type: "GET", // 或者 "GET"，根据实际情况选择
                    url: "ModifyVisit",
                    data: {
                        "stu_id":stu_id,
                        "name":name,
                        "date": date,
                        "content" : content,
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
            $("#SearchVisit").click(function () {
                $("#SearchVisit").css("color", "red");
                var keyword = $("input[name='keyword']").val();
                $.ajax({
                    type: "GET",
                    url: "SearchVisit", // 替换为您的Servlet URL
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

        jQuery(document).ready(function($) {
            $('.add').click(function(){

                // 初始化表单字段
                $('input[name="add_stu_id"]').val("");
                $('input[name="add_name"]').val("");
                $('input[name="add_date"]').val("");
                $('input[name="add_content"]').val("");

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
                var stu_id = $('input[name="add_stu_id"]').val();
                var name = $('input[name="add_name"]').val();
                var date = $('input[name="add_date"]').val();
                var content = $('input[name="add_content"]').val();

                $('.theme-add input[name="submit"]').css("color","red");

                $.ajax({
                    type: "GET", // 或者 "GET"，根据实际情况选择
                    url: "AddVisit",
                    data: {
                        "stu_id":stu_id,
                        "name":name,
                        "date": date,
                        "content":content,
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

    </script>
</head>
<body marginwidth="0" marginheight="0">
<div class="container">
    <div class="public-nav">您当前的位置：<a href="">学生信息</a>><a href="">家访记录</a></div>
    <div class="public-content">
        <div class="public-content-header">
            <h3>修改信息</h3>
            <div class="search">
                <form action="../../SearchVisit" method="get">
                    <input type="hidden" name="method" value="SearchVisit" />
                    <input placeholder="请输入..." name="keyword" type="text" value="">
                    <button type="button" id="SearchVisit"></button>
                </form>
            </div>

        </div>
        <br><br><br><br>
        <div class="public-content-cont">
            <table class="public-cont-table">

                <tr>
                    <th style="width:15%">学号</th>
                    <th style="width:15%">姓名</th>
                    <th style="width:10%">日期</th>
                    <th style="width:20%">内容</th>
                    <th style="width:20%">操作</th>
                </tr>
                <%
                    ArrayList VisitAL=(ArrayList)session.getAttribute("VisitAL");
                    Iterator iter=VisitAL.iterator();
                    DB db = new DB();
                    while(iter.hasNext()){
                        Visit visit=(Visit) iter.next();
                %>
                <tr><td><%= visit.getStu_id() %></td>
                    <td><%= db.getName(visit.getStu_id()) %></td>
                    <td><%= visit.getDate() %></td>
                    <td><%= visit.getContent() %></td>
                    <td>
                        <div class="table-fun">
                            <a class="btn btn-primary btn-large modify" href="javascript:;"
                               data-stu_id="<%= visit.getStu_id() %>"
                               data-name="<%= db.getName(visit.getStu_id()) %>"
                               data-date="<%= visit.getDate() %>"
                               data-content="<%= visit.getContent() %>">
                                修改
                            </a>
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
<div class="theme-popover modify-popover">
    <div class="theme-poptit">
        修改信息
        <a href="javascript:;" title="关闭" class="close">×</a>
    </div>
    <div class="theme-popbod dform">
        <form class="theme-signin" name="modifyform">
            <ol>
                <li><strong>学号:</strong><input class="ipt" type="text" name="stu_id" size="20" /></li>
                <li><strong>姓名:</strong><input class="ipt" type="text" name="name" size="20" /></li>
                <li><strong>日期:</strong><input class="ipt" type="text" name="date" size="20" /></li>
                <li><strong>内容:</strong><input class="ipt" type="text" name="content" size="20" /></li>
                <li><input class="btn btn-primary" type="button" name="submit" value=" 提交 " /></li>
                <input align="center" style="border: none; font-size: 14px;color:#333;" type="text" id="info"/>
            </ol>
        </form>
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
                <li><strong>学号:</strong><input class="ipt" type="text" name="add_stu_id" size="20" /></li>
                <li><strong>姓名:</strong><input class="ipt" type="text" name="add_name" size="20" /></li>
                <li><strong>日期:</strong><input class="ipt" type="text" name="add_date" size="20" /></li>
                <li><strong>内容:</strong><input class="ipt" type="text" name="add_content" size="20" /></li>
                <li><input class="btn btn-primary" type="button" name="submit" value=" 提交 " /></li>
                <input align="center" style="border: none; font-size: 14px;color:#333;" type="text" id="info2"/>
            </ol>
        </form>
    </div>
</div>
</body>
</html>