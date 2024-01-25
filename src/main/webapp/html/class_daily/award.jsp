<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="model.Student"%>
<%@page import="DB.DB"%>
<%@ page import="model.Reward" %>
<%@ page import="java.text.SimpleDateFormat" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>奖惩记录</title>
    <link rel="stylesheet" href="../../css/reset.css" />
    <link rel="stylesheet" href="../../css/content.css" />
    <link rel="stylesheet" href="../../css/search.css" />
    <link rel="stylesheet" href="../../css/youtiy.css" media="all">
    <script src="../../js/jquery.min.js"></script>
    <script type="text/javascript">
        jQuery(document).ready(function($) {
            $('.modify').click(function(){
                // 获取当前行数据
                var type = $(this).data('type');
                var stu_id = $(this).data('stu_id');
                var name = $(this).data('name');
                var date = $(this).data('date');
                var reason = $(this).data('reason');

                // 填充表单字段
                $('input[name="type"]:checked').val(type);
                $('input[name="stu_id"]').val(stu_id);
                $('input[name="date"]').val(date);
                $('input[name="name"]').val(name);
                $('input[name="reason"]').val(reason);

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
                var type = $('input[name="type"]:checked').val();
                var stu_id = $('input[name="stu_id"]').val();
                var name = $('input[name="name"]').val();
                var date = $('input[name="date"]').val();
                var reason = $('input[name="reason"]').val();

                $('.theme-signin input[name="submit"]').css("color","red");

                $.ajax({
                    type: "GET", // 或者 "GET"，根据实际情况选择
                    url: "ModifyReward",
                    data: {
                        "type": type,
                        "stu_id": stu_id,
                        "name": name,
                        "date": date,
                        "reason": reason
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
            $("#SearchReward").click(function () {
                $("#SearchReward").css("color", "red");
                var keyword = $("input[name='keyword']").val();
                $.ajax({
                    type: "GET",
                    url: "SearchReward", // 替换为您的Servlet URL
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
                $('input[name="add_date"]').val("");
                $('input[name="add_name"]').val("");
                $('input[name="add_reason"]').val("");

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
                var type = $('input[name="add_type"]:checked').val();
                var stu_id = $('input[name="add_stu_id"]').val();
                var name = $('input[name="add_name"]').val();
                var date = $('input[name="add_date"]').val();
                var reason = $('input[name="add_reason"]').val();

                $('.theme-signin input[name="submit"]').css("color","red");

                $.ajax({
                    type: "GET", // 或者 "GET"，根据实际情况选择
                    url: "AddReward",
                    data: {
                        "type": type,
                        "stu_id": stu_id,
                        "name": name,
                        "date": date,
                        "reason": reason
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
    <div class="public-nav">您当前的位置：<a href="">班级日常</a>><a href="">奖惩记录</a></div>
    <div class="public-content">
        <div class="public-content-header">
            <h3>修改信息</h3>
            <div class="search">
                <form action="../../SearchReward" method="get">
                    <input type="hidden" name="method" value="SearchReward" />
                    <input placeholder="请输入..." name="keyword" type="text" value="">
                    <button type="button" id="SearchReward"></button>
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
                    <th style="width:20%">奖惩类型</th>
                    <th style="width:20%">原因</th>
                    <th style="width:20%">操作</th>
                </tr>
                <%
                    ArrayList RewardAL=(ArrayList)session.getAttribute("RewardAL");
                    Iterator iter=RewardAL.iterator();
                    DB db = new DB();
                    SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
                    while(iter.hasNext()){
                        Reward reward=(Reward) iter.next();
                %>
                <tr><td><%= reward.getStu_id() %></td>
                    <td><%= db.getName(reward.getStu_id()) %></td>
                    <td><%= ft.format(reward.getDate()) %></td>
                    <td><%= reward.getType() %></td>
                    <td><%= reward.getReason() %></td>
                    <td>
                        <div class="table-fun">
                            <a class="btn btn-primary btn-large modify" href="javascript:;"
                               data-type="<%= reward.getType() %>"
                               data-stu_id="<%= reward.getStu_id() %>"
                               data-name="<%= db.getName(reward.getStu_id()) %>"
                               data-date="<%= ft.format(reward.getDate()) %>"
                               date-reason="<%= reward.getReason() %>">
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
                <li><strong>奖惩类型：</strong><input  type="radio" name="type" value="奖"  />奖
                    <input type="radio" name="type" value="惩"/>惩</li>
                <li><strong>学号：</strong><input class="ipt" type="text" name="stu_id" size="20" /></li>
                <li><strong>姓名：</strong><input class="ipt" type="text" name="name" size="20" /></li>
                <li><strong>日期：</strong><input class="ipt" type="text" name="date" size="20" /></li>
                <li><strong>原因：</strong><input class="ipt" type="text" name="reason" size="20" /></li>
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
        <form class="theme-add" name="addform">
            <ol>
                <li><strong>奖惩类型：</strong><input  type="radio" name="add_type" value="奖"  />奖
                    <input type="radio" name="add_type" value="惩"/>惩</li>
                <li><strong>学号：</strong><input class="ipt" type="text" name="add_stu_id" size="20" /></li>
                <li><strong>姓名：</strong><input class="ipt" type="text" name="add_name" size="20" /></li>
                <li><strong>日期：</strong><input class="ipt" type="text" name="add_date" size="20" /></li>
                <li><strong>原因：</strong><input class="ipt" type="text" name="add_reason" size="20" /></li>
                <li><input class="btn btn-primary" type="button" name="submit" value=" 提交 " /></li>
                <input align="center" style="border: none; font-size: 14px;color:#333;" type="text" id="info2"/>
            </ol>
        </form>
    </div>
</div>
</body>
</html>