<%--
  Created by IntelliJ IDEA.
  User: 22584
  Date: 2023/7/27
  Time: 23:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>班主任工作管理系统 </title>
    <style>
        @import "css/reset.css";
    </style>
</head>
<body>
<iframe height="1000px" width="17%" src="admin_index.jsp"></iframe>
<iframe height="900px" width="79%" name="tar" src="html/headPage/head_admin.html"></iframe>
</body>
<script>
    window.onload = function() {
        // 获取第一个 <iframe> 元素
        var firstIframe = document.querySelector('iframe:first-child');

        // 获取第一个链接的 URL
        var firstLinkUrl = document.querySelector('a:first-of-type').href;

        // 设置第二个 <iframe> 元素的 src 属性为第一个链接的 URL
        if (firstIframe) {
            document.querySelector('iframe[name="tar"]').src = firstLinkUrl;
        }
    };
</script>
</html>
