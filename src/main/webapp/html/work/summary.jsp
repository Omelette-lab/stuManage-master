<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="model.Student"%>
<%@page import="DB.DB"%>
<%@ page import="model.Summary" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>工作总结</title>
    <link rel="stylesheet" href="../../css/reset.css" />
    <link rel="stylesheet" href="../../css/content.css" />
    <link rel="stylesheet" href="../../css/search.css" />
    <link rel="stylesheet" href="../../css/youtiy.css" media="all">
    <script src="../../js/jquery.min.js"></script>
    <script type="text/javascript">
        jQuery(document).ready(function($) {
            $('.modify').click(function(){
                // 获取当前行数据
                var date = $(this).data('date');
                var summary = $(this).data('summary');

                // 填充表单字段
                $('input[name="date"]').val(date);
                $('input[name="summary"]').val(summary);

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
                var date = $('input[name="date"]').val();
                var summary = $('input[name="summary"]').val();

                $('.theme-signin input[name="submit"]').css("color","red");

                $.ajax({
                    type: "GET", // 或者 "GET"，根据实际情况选择
                    url: "ModifySummary",
                    data: {
                        "date": date,
                        "summary": summary,
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
            $("#SearchSummary").click(function () {
                $("#SearchSummary").css("color", "red");
                var keyword = $("input[name='keyword']").val();
                $.ajax({
                    type: "GET",
                    url: "SearchSummary", // 替换为您的Servlet URL
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
                $('input[name="add_date"]').val("");
                $('input[name="add_summmary"]').val("");

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
                var date = $('input[name="add_date"]').val();
                var summary = $('input[name="add_summary"]').val();

                $('.theme-signin input[name="submit"]').css("color","red");

                $.ajax({
                    type: "GET", // 或者 "GET"，根据实际情况选择
                    url: "AddSummary",
                    data: {
                        "date": date,
                        "summary": summary,
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
    <div class="public-nav">您当前的位置：<a href="">工作管理</a>><a href="">工作总结</a></div>
    <div class="public-content">
        <div class="public-content-header">
            <h3>修改信息</h3>
            <div class="search">
                <form action="../../SearchSummary" method="get">
                    <input type="hidden" name="method" value="SearchSummary" />
                    <input placeholder="请输入..." name="keyword" type="text" value="">
                    <button type="button" id="SearchSummary"></button>
                </form>
            </div>

        </div>
        <br><br><br><br>
        <div class="public-content-cont">
            <table class="public-cont-table">

                <tr>
                    <th style="width:15%">日期</th>
                    <th style="width:15%">总结</th>
                    <th style="width:20%">操作</th>
                </tr>
                <%
                    ArrayList SummaryAL=(ArrayList)session.getAttribute("SummaryAL");
                    Iterator iter=SummaryAL.iterator();
                    while(iter.hasNext()){
                        Summary summary=(Summary) iter.next();
                %>
                <tr><td><%= summary.getDate() %></td>
                    <td><%= summary.getSummary() %></td>
                    <td>
                        <div class="table-fun">
                            <a class="btn btn-primary btn-large modify" href="javascript:;"
                                data-date="<%= summary.getDate() %>"
                                data-summary="<%= summary.getSummary() %>">
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
                <li><strong>日期：</strong><input class="ipt" type="text" name="date" size="20" /></li>
                <li><strong>总结：</strong><input class="ipt" type="text" name="summary" size="20" /></li>
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
                <li><strong>日期：</strong><input class="ipt" type="text" name="add_date" size="20" /></li>
                <li><strong>总结：</strong><input class="ipt" type="text" name="add_summary" size="20" /></li>
                <li><input class="btn btn-primary" type="button" name="submit" value=" 提交 " /></li>
                <input align="center" style="border: none; font-size: 14px;color:#333;" type="text" id="info2"/>
            </ol>
        </form>
    </div>
</div>
</body>
</html>