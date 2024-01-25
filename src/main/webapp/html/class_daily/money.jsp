<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="model.Student"%>
<%@page import="DB.DB"%>
<%@ page import="model.Fund" %>
<%@ page import="java.text.SimpleDateFormat" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>班费管理</title>
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
                var amount = $(this).data('amount');
                var balance = $(this).data('balance');
                var remark = $(this).data('remark');

                // 填充表单字段
                $('input[name="date"]').val(date);
                $('input[name="amount"]').val(amount);
                $('input[name="remark"]').val(remark);
                $('input[name="balance"]').val(balance);

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
                var amount = $('input[name="amount"]').val();
                var balance = $('input[name="balance"]').val();
                var remark = $('input[name="remark"]').val();

                $('.theme-signin input[name="submit"]').css("color","red");

                $.ajax({
                    type: "GET", // 或者 "GET"，根据实际情况选择
                    url: "ModifyFund",
                    data: {
                        "date": date,
                        "amount": amount,
                        "balance": balance,
                        "remark": remark,
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
            $("#SearchFund").click(function () {
                $("#SearchFund").css("color", "red");
                var keyword = $("input[name='keyword']").val();
                $.ajax({
                    type: "GET",
                    url: "SearchFund", // 替换为您的Servlet URL
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
                $('input[name="add_amount"]').val("");
                $('input[name="add_date"]').val("");
                $('input[name="add_balance"]').val("");
                $('input[name="add_remark"]').val("");

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
                var amount = $('input[name="add_amount"]').val();
                var balance = $('input[name="add_balance"]').val();
                var remark = $('input[name="add_remark"]').val();

                $('.theme-signin input[name="submit"]').css("color","red");

                $.ajax({
                    type: "GET", // 或者 "GET"，根据实际情况选择
                    url: "AddFund",
                    data: {
                        "date": date,
                        "amount": amount,
                        "balance": balance,
                        "remark": remark,
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
    <div class="public-nav">您当前的位置：<a href="">班级日常</a>><a href="">班费管理</a></div>
    <div class="public-content">
        <div class="public-content-header">
            <h3>修改信息</h3>
            <div class="search">
                <form action="../../SearchFund" method="get">
                    <input type="hidden" name="method" value="SearchFund" />
                    <input placeholder="请输入..." name="keyword" type="text" value="">
                    <button type="button" id="SearchFund"></button>
                </form>
            </div>

        </div>
        <br><br><br><br>
        <div class="public-content-cont">
            <table class="public-cont-table">

                <tr>
                    <th style="width:15%">日期</th>
                    <th style="width:15%">支出/收入</th>
                    <th style="width:10%">结余</th>
                    <th style="width:20%">事项</th>
                    <th style="width:20%">操作</th>
                </tr>
                <%
                    ArrayList Fund_al=(ArrayList)session.getAttribute("FundAL");
                    Iterator iter=Fund_al.iterator();
                    SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
                    while(iter.hasNext()){
                    Fund fund=(Fund) iter.next();
                %>
                <tr><td><%= ft.format(fund.getDate()) %></td>
                    <td><%= fund.getAmount() %></td>
                    <td><%= fund.getBalance() %></td>
                    <td><%= fund.getRemark() %></td>
                    <td>
                        <div class="table-fun">
                            <a class="btn btn-primary btn-large modify" href="javascript:;"
                               data-date="<%= ft.format(fund.getDate()) %>"
                               data-amount="<%= fund.getAmount() %>"
                               data-balance="<%= fund.getBalance() %>"
                               data-remark="<%= fund.getRemark() %>">
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

<div class="theme-popover  modify-popover">
    <div class="theme-poptit">
        修改信息
        <a href="javascript:;" title="关闭" class="close">×</a>
    </div>
    <div class="theme-popbod dform">
        <form class="theme-signin" name="modifyform">
            <ol>
                <li><strong>日期：</strong><input class="ipt" type="text" name="date" size="20" /></li>
                <li><strong>支出/收入：</strong><input class="ipt" type="text" name="amount" size="20" /></li>
                <li><strong>结余：</strong><input class="ipt" type="text" name="balance" size="20" /></li>
                <li><strong>事项：</strong><input class="ipt" type="text" name="remark" size="20" /></li>
                <li><input class="btn btn-primary" type="button" name="submit" value=" 提交 " /></li>
                <input align="center" style="border: none; font-size: 14px;color:#333;" type="text" id="info"/>
            </ol>
        </form>
    </div>
</div>

<div class="theme-popover  add-popover">
    <div class="theme-poptit">
        添加信息
        <a href="javascript:;" title="关闭" class="close">×</a>
    </div>
    <div class="theme-popbod dform">
        <form class="theme-add" name="addform">
            <ol>
                <li><strong>日期：</strong><input class="ipt" type="text" name="add_date" size="20" /></li>
                <li><strong>支出/收入：</strong><input class="ipt" type="text" name="add_amount" size="20" /></li>
                <li><strong>结余：</strong><input class="ipt" type="text" name="add_balance" size="20" /></li>
                <li><strong>事项：</strong><input class="ipt" type="text" name="add_remark" size="20" /></li>
                <li><input class="btn btn-primary" type="button" name="submit" value=" 提交 " /></li>
                <input align="center" style="border: none; font-size: 14px;color:#333;" type="text" id="info2"/>
            </ol>
        </form>
    </div>
</div>

</body>
</html>