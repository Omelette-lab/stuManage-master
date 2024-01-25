<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="model.Student"%>
<%@page import="DB.DB"%>
<%@ page import="model.Grade" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false"%>

<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>成绩编辑</title>
    <link rel="stylesheet" href="../../css/reset.css" />
    <link rel="stylesheet" href="../../css/content.css" />
    <link rel="stylesheet" href="../../css/search.css" />
    <link rel="stylesheet" href="../../css/youtiy.css" media="all">
    <script src="../../js/jquery.min.js"></script>
    <script src="../../js/form.js"></script>
    <script type="text/javascript">
        jQuery(document).ready(function($) {
            $('.modify').click(function(){
                // 获取当前行数据
                var stu_id = $(this).data('stu_id');
                var date = $(this).data('date');
                var chinese = $(this).data('chinese');
                var math = $(this).data('math');
                var english = $(this).data('english');

                // 填充表单字段
                $('input[name="stu_id"]').val(stu_id);
                $('input[name="date"]').val(date);
                $('input[name="chinese"]').val(chinese);
                $('input[name="math"]').val(math);
                $('input[name="english"]').val(english);

                // 打开修改弹窗
                $('.modify-popover').slideDown(200);
                $('.add-popover').slideUp(200);
            })
            $('.modify-popover .close').click(function(){
                // 关闭修改弹窗
                $('.modify-popover').slideUp(200);
            })
        })

        jQuery(document).ready(function($) {
            $('.ave').click(function(){
                var table = $(".public-cont-table");
                table.load(document.URL + " .public-cont-table");//重新加载表格
                // 打开修改弹窗
                $('.ave-popover').slideDown(200);
                $('.modify-popover').slideUp(200);
                $('.add-popover').slideUp(200);
            })
            $('.ave-popover .close').click(function(){
                // 关闭修改弹窗
                $('.ave-popover').slideUp(200);
            })
        })

        $(document).ready(function() {
            $('.theme-signin input[name="submit"]').click(function() {
                var stu_id = $('input[name="stu_id"]').val();
                var date = $('input[name="date"]').val();
                var chinese = $('input[name="chinese"]').val();
                var math = $('input[name="math"]').val();
                var english = $('input[name="english"]').val();

                $('.theme-signin input[name="submit"]').css("color","red");

                $.ajax({
                    type: "GET", // 或者 "GET"，根据实际情况选择
                    url: "ModifyGrade",
                    data: {
                        "stu_id": stu_id,
                        "date": date,
                        "chinese": chinese,
                        "math": math,
                        "english" :english,
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
            $("#SearchGrade").click(function () {
                $("#SearchGrade").css("color", "red");
                var keyword = $("input[name='keyword']").val();
                $.ajax({
                    type: "GET",
                    url: "SearchGrade", // 替换为您的Servlet URL
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
                $('input[name="add_chinese"]').val("");
                $('input[name="add_math"]').val("");
                $('input[name="add_english"]').val("");

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
                var date = $('input[name="add_date"]').val();
                var chinese = $('input[name="add_chinese"]').val();
                var math = $('input[name="add_math"]').val();
                var english = $('input[name="add_english"]').val();

                $('.theme-signin input[name="submit"]').css("color","red");

                $.ajax({
                    type: "GET", // 或者 "GET"，根据实际情况选择
                    url: "AddGrade",
                    data: {
                        "stu_id": stu_id,
                        "date": date,
                        "chinese": chinese,
                        "math": math,
                        "english" :english,
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
    <div class="public-nav">您当前的位置：<a href="">班级日常</a>><a href="">班级干部</a></div>
    <div class="public-content">
        <div class="public-content-header">
            <h3>修改信息</h3>


            <div class="search">
                <a class="btn btn-primary btn-large ave" href="javascript:;">
                    平均分
                </a>&nbsp;&nbsp;&nbsp;
                <a class="btn btn-primary btn-large add" href="javascript:;">
                    添加
                </a>
                <form class="getback-box">
                    <input placeholder="请输入..." name="keyword" type="text" >
                    <button type="button" id="SearchGrade" ></button>
                </form>
            </div>

        </div>
        <br><br><br><br>
        <div class="public-content-cont">
            <table class="public-cont-table">
                <tbody>

                <tr>
                    <th title="点击排序" onclick="sortTable(0)" style="width:15%">学号</th>
                    <th title="点击排序" onclick="sortTable(1)" style="width:15%">姓名</th>
                    <th title="点击排序" onclick="sortTable(2)" style="width:10%">日期</th>
                    <th title="点击排序" onclick="sortTable(3)" style="width:10%">语文</th>
                    <th title="点击排序" onclick="sortTable(4)" style="width:10%">数学</th>
                    <th title="点击排序" onclick="sortTable(5)" tyle="width:10%">英语</th>
                    <th title="点击排序" onclick="sortTable(6)" tyle="width:10%">总分</th>
                    <th style="width:20%">操作</th>
                </tr>

                <%	ArrayList grade_al=(ArrayList)session.getAttribute("GradeAL");
                    Iterator iter=grade_al.iterator();
                    DB db = new DB();
                    float sumCh=0,sumMa=0,sumEn=0,sum=0;
                    int count=0;
                    SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
                    while(iter.hasNext()){
                        Grade grade=(Grade) iter.next();
                        sumCh+=grade.getChinse();
                        sumMa+=grade.getMath();
                        sumEn+=grade.getEnglish();
                        sum+=(grade.getChinse()+grade.getMath()+grade.getEnglish());
                        count++;
                %>
                <tr>
                    <td><%= grade.getStu_id() %></td>
                    <td>"<%= db.getName(grade.getStu_id()) %>"</td>
                    <td><%= grade.getDate() %></td>
                    <td><%= grade.getChinse() %></td>
                    <td><%= grade.getMath() %></td>
                    <td><%= grade.getEnglish() %></td>
                    <td><%= grade.getChinse()+grade.getMath()+grade.getEnglish() %></td>
                    <td>
                        <div class="table-fun">
                            <a class="btn btn-primary btn-large modify" href="javascript:;"
                               data-stu_id="<%= grade.getStu_id() %>"
                               data-date="<%= ft.format(grade.getDate()) %>"
                               data-chinese="<%= grade.getChinse() %>"
                               data-math="<%= grade.getMath() %>"
                               data-english="<%= grade.getEnglish() %>"
                            >
                                修改
                            </a>
                        </div>
                    </td>
                </tr>
                <%}
                %>
                </tbody>
            </table>
            <div class="box"></div>
        </div>
    </div>

</div>


<div class="theme-popover ave-popover">
    <div class="theme-poptit">
        平均分
        <a href="javascript:;" title="关闭" class="close">×</a>
    </div>
    <div class="theme-popbod dform">
        <form class="theme-signin" name="aveform" >
            <ol>
                <li><strong>语文：</strong><input class="ipt" type="text" name="chinese" size="20" value=<%=sumCh/count%>/></li>
                <li><strong>数学：</strong><input class="ipt" type="text" name="math" size="20" value=<%=sumMa/count%>/></li>
                <li><strong>英语：</strong><input class="ipt" type="text" name="english" size="20" value=<%=sumEn/count%>/></li>
                <li><strong>总分：</strong><input class="ipt" type="text" name="sum" size="20" value=<%=sum/count%>/></li>
            </ol>
        </form>
    </div>
</div>


<div class="theme-popover modify-popover">
    <div class="theme-poptit">
        修改信息
        <a href="javascript:;" title="关闭" class="close">×</a>
    </div>
    <div class="theme-popbod dform">
        <form class="theme-signin" name="modifyform" >
            <ol>
                <li><strong>学号：</strong><input class="ipt" type="text" name="stu_id" size="20" /></li>
                <li><strong>日期：</strong><input class="ipt" type="text" name="date" size="20" /></li>
                <li><strong>语文：</strong><input class="ipt" type="text" name="chinese" size="20" /></li>
                <li><strong>数学：</strong><input class="ipt" type="text" name="math" size="20" /></li>
                <li><strong>英语：</strong><input class="ipt" type="text" name="english" size="20" /></li>
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
                <li><strong>学号：</strong><input class="ipt" type="text" name="add_stu_id" size="20" /></li>
                <li><strong>日期：</strong><input class="ipt" type="text" name="add_date" size="20" /></li>
                <li><strong>语文：</strong><input class="ipt" type="text" name="add_chinese" size="20" /></li>
                <li><strong>数学：</strong><input class="ipt" type="text" name="add_math" size="20" /></li>
                <li><strong>英语：</strong><input class="ipt" type="text" name="add_english" size="20" /></li>
                <li><input class="btn btn-primary" type="button" name="submit" value=" 提交 " /></li>
                <input align="center" style="border: none; font-size: 14px;color:#333;" type="text" id="info2"/>
            </ol>
        </form>
    </div>
</div>
</body>
</html>