<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="model.Student"%>
<%@page import="DB.DB"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>

<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>班级干部</title>
	<link rel="stylesheet" href="../../css/reset.css" />
	<link rel="stylesheet" href="../../css/content.css" />
	<link rel="stylesheet" href="../../css/search.css" />
	<link rel="stylesheet" href="../../css/youtiy.css" media="all">
	<script src="../../js/jquery.min.js"></script>
	<script type="text/javascript">
		$(document).ready(function () {
			$("#searchCadre").click(function () {
				$("#searchCadre").css("color", "red");
				var keyword = $("input[name='keyword']").val();
				$.ajax({
					type: "GET",
					url: "SearchCadre", // 替换为您的Servlet URL
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

		$(document).ready(function() {
			$('.theme-signin input[name="submit"]').click(function() {
				var stu_id = $('input[name="stu_id"]').val();
				var stu_name = $('input[name="name"]').val();
				var position = $('input[name="position"]').val();
				$('.theme-signin input[name="submit"]').css("color","red");

				$.ajax({
					type: "GET", // 或者 "GET"，根据实际情况选择
					url: "ModifyCadre",
					data: {
						"stu_id": stu_id,
						"stu_name":stu_name,
						"position": position
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

		jQuery(document).ready(function ($) {
			$('.theme-login').click(function () {
				// 获取当前行数据
				var stuId = $(this).data('stu-id');
				var name = $(this).data('name');
				var position = $(this).data('position');

				// 填充表单字段
				$('input[name="stu_id"]').val(stuId);
				$('input[name="name"]').val(name);
				$('input[name="position"]').val(position);

				// 显示弹出表单
				$('.theme-popover-mask').fadeIn(100);
				$('.theme-popover').slideDown(200);
			})
			$('.theme-poptit .close').click(function () {
				$('.theme-popover-mask').fadeOut(100);
				$('.theme-popover').slideUp(200);
			})

		})
	</script>
</head>
<body marginwidth="0" marginheight="0">
<div class="container">
	<div class="public-nav">您当前的位置：<a href="">班级日常</a>><a href="">班级干部</a></div>
	<div class="public-content">
		<div class="public-content-header">
			<h3>修改信息</h3>
			<div class="search">
				<form action="../../SearchCadre" method="get">
					<input type="hidden" name="method" value="SearchCadre" />
					<input placeholder="请输入..." name="keyword" type="text" value="">
					<button type="button" id="searchCadre"></button>
				</form>
			</div>

		</div>
		<br><br><br><br>
		<div class="public-content-cont">
			<table class="public-cont-table">

				<tr>
					<th style="width:15%">学号</th>
					<th style="width:15%">姓名</th>
					<th style="width:10%">职务</th>
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
					<td><%= stu.getPosition() %></td>
					<td>
						<div class="table-fun">
							<a class="btn btn-primary btn-large theme-login" href="javascript:;"
							   	data-stu-id="<%= stu.getId() %>"
							   	data-name="<%= stu.getName() %>"
							   	data-position="<%= stu.getPosition() %>">
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
<div class="theme-popover">
	<div class="theme-poptit">
		修改信息
		<a href="javascript:;" title="关闭" class="close">×</a>
	</div>
	<div class="theme-popbod dform">
		<form class="theme-signin" name="loginform">
			<ol>

				<li align="center"><strong>学号：</strong><input class="ipt" type="text" name="stu_id" size="20" /></li>
				<li align="center"><strong>姓名：</strong><input class="ipt" type="text" name="name" size="20" /></li>
				<li align="center"><strong>职务：</strong><input class="ipt" type="text" name="position" size="20" /></li>
				<li align="center"><input class="btn btn-primary" type="button" name="submit" value=" 提交 " /></li>
				<input align="center" style="..." type="text" id="info"/>
			</ol>
		</form>
	</div>
</div>
</body>
</html>