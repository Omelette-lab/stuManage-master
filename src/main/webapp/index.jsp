<%--
  Created by IntelliJ IDEA.
  User: DELL
  Date: 2023/7/21
  Time: 18:31
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false"%>
<html>
<head>
    <title>导航栏</title>
    <style>
        @import "css/index.css";
    </style>
</head>
<body id="body-pd">
<div class="l-navbar" id="navbar">
    <nav class="nav">
        <div>
            <div class="nav_brand">
                <ion-icon name="menu-outline" class="nav_toggle" id="nav-toggle"></ion-icon>
                <a href="html/headPage/head.html" class="nav_logo" target="tar">班主任工作管理系统</a>
            </div>
            <div class="nav_list">
                <a href="html/headPage/head.html" class="nav_link active" target="tar">
                    <ion-icon name="home-outline" class="nav_icon" ></ion-icon>
                    <span class="nav_name">主页</span>
                </a>
                <div class="nav_link collapse">
                    <ion-icon name="chatbubbles-outline" class="nav_icon" ></ion-icon>
                    <span class="nav_name">班级日常</span>
                    <ion-icon name="chevron-down-outline" class="collapse__link" ></ion-icon>
                    <ul class="collapse_menu">
                        <a href="html/class_daily/cadre.jsp" class="collapse__sublink" target="tar">班级干部</a>
                        <a href="html/class_daily/dormitry.jsp" class="collapse__sublink" target="tar">宿舍管理</a>
                        <a href="html/class_daily/attendance.jsp" class="collapse__sublink" target="tar">考勤管理</a>
                        <a href="html/class_daily/activity.jsp" class="collapse__sublink" target="tar">班级活动</a>
                        <a href="html/class_daily/award.jsp" class="collapse__sublink" target="tar">奖惩记录</a>
                        <a href="html/class_daily/money.jsp" class="collapse__sublink" target="tar">班费管理</a>
                    </ul>
                </div>
                <div class="nav_link collapse">
                    <ion-icon name="folder-outline" class="nav_icon" ></ion-icon>
                    <span class="nav_name">工作管理</span>
                    <ion-icon name="chevron-down-outline" class="collapse__link" ></ion-icon>
                    <ul class="collapse_menu">
                        <a href="html/work/plan.jsp" class="collapse__sublink" target="tar">计划安排</a>
                        <a href="html/work/summary.jsp" class="collapse__sublink" target="tar">工作总结</a>
                        <a href="html/work/class_meeting.jsp" class="collapse__sublink" target="tar">班会开展</a>
                        <a href="html/work/talking.jsp" class="collapse__sublink" target="tar">谈话记录</a>
                    </ul>
                </div>
                <div class="nav_link collapse">
                    <ion-icon name="pie-chart-outline" class="nav_icon" ></ion-icon>
                    <span class="nav_name">成绩管理</span>
                    <ion-icon name="chevron-down-outline" class="collapse__link" ></ion-icon>
                    <ul class="collapse_menu">
                        <a href="html/grade/edit.jsp" class="collapse__sublink" target="tar">成绩编辑</a>
<%--                        <a href="html/grade/analyse.jsp" class="collapse__sublink" target="tar">数据分析</a>--%>
                    </ul>
                </div>

                <div class="nav_link collapse">
                    <ion-icon name="people-outline" class="nav_icon" ></ion-icon>
                    <span class="nav_name">学生信息</span>
                    <ion-icon name="chevron-down-outline" class="collapse__link" ></ion-icon>
                    <ul class="collapse_menu">
                        <a href="html/stu_info/info.jsp" class="collapse__sublink" target="tar">学生档案</a>
                        <a href="html/stu_info/commet.jsp" class="collapse__sublink" target="tar">学生评语</a>
                        <a href="html/stu_info/visit.jsp" class="collapse__sublink" target="tar">家访记录</a>
                    </ul>
                </div>
                <a href="html/settings/settings.jsp" class="nav_link" target="tar">
                    <ion-icon name="settings-outline" class="nav_icon" ></ion-icon>
                    <span class="nav_name">设置</span>
                </a>
            </div>
        </div>
        <a href="LoginOut" class="nav_link" target="_parent">
<%--            页面内嵌iframe时，在 a 标签中添加 "target="_parent" 属性可实现子页面内点击跳转是整个页面的跳转--%>
            <ion-icon name="log-out-outline" class="nav_icon" ></ion-icon>
            <span class="nav_name">退出登陆</span>
        </a>
    </nav>
</div>

<script src="https://unpkg.com/ionicons@5.1.2/dist/ionicons.js"></script>
<script src="js/index.js"></script>
</body>
</html>