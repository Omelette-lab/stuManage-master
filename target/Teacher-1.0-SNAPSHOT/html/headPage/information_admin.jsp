<%@ page import="model.Admin" %><%--
  Created by IntelliJ IDEA.
  User: DELL
  Date: 2023/7/21
  Time: 18:31
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false"%>
<html>
<head>
    <style>
        body {
            background-color: rgba(202,224,232);
            font-size: 20px;
        }
    </style>
    <title>基本信息</title>
</head>
<body>
<%Admin admin= (Admin) session.getAttribute("admin");
    %>
<h3>基本信息</h3>
<p>身份：管理员</p>
<p>账号：admin </p>
<p>电子邮箱：<%=admin.getEmail()%></p>
</body>
</html>
