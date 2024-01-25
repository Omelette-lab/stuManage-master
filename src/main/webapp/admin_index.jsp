<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false"%>
<html >
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>导航栏_管理员</title>
    <link rel="stylesheet" href="./css/index.css">
</head>
<body id="body-pd">
<div class="l-navbar" id="navbar">
    <nav class="nav">
        <div>
            <div class="nav_brand">
                <ion-icon name="menu-outline" class="nav_toggle" id="nav-toggle"></ion-icon>
                <a href="#" class="nav_logo">班主任工作管理系统</a>
            </div>
            <div class="nav_list">
                <a href="./html/headPage/head_admin.html" class="nav_link active" target="tar">
                    <ion-icon name="home-outline" class="nav_icon" ></ion-icon>
                    <span class="collapse__sublink" href="../html/headPage/head_admin.html" >主页</span>
                </a>
                <div class="nav_link collapse">
                    <ion-icon name="chatbubbles-outline" class="nav_icon" ></ion-icon>
                    <span class="nav_name">学生管理</span>
                    <ion-icon name="chevron-down-outline" class="collapse__link" ></ion-icon>
                    <ul class="collapse_menu">
                        <a href="./html/stumanage/stu_info.jsp" class="collapse__sublink" target="tar">学生档案</a>
                    </ul>
                </div>
                <div class="nav_link collapse">
                    <ion-icon name="folder-outline" class="nav_icon" ></ion-icon>
                    <span class="nav_name">教师管理</span>
                    <ion-icon name="chevron-down-outline" class="collapse__link" ></ion-icon>
                    <ul class="collapse_menu">
                        <a href="./html/teamanage/tea_info.jsp" class="collapse__sublink" target="tar">教师档案</a>
                    </ul>
                </div>

                <a href="./html/settings/settings_admin.jsp" class="nav_link" target="tar">
                    <ion-icon name="settings-outline" class="nav_icon" ></ion-icon>
                    <span class="nav_name">设置</span>
                </a>
            </div>
        </div>
        <a href=" LoginOut" class="nav_link" target="_parent">
            <ion-icon name="log-out-outline" class="nav_icon" ></ion-icon>
            <span class="nav_name">退出登陆</span>
        </a>
    </nav>
</div>

<script src="https://unpkg.com/ionicons@5.1.2/dist/ionicons.js"></script>
<script src="./js/index.js"></script>
</body>
</html>