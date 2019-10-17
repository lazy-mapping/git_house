<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>欢迎！</title>
    <link rel="stylesheet" href="css/index-base.css">
</head>
<body>
    <header>    
        <div class="logo"><img src="img/logo_school.png" alt=""></div>
        <h3>考 勤 系 统</h3>
        <div class="info">
            <ul>
                <li>
                    <span class="label">欢迎你：${sessionScope.session_user.name} </span>
                    <em>
                        <span class="xhxm"></span>
                    </em>
                </li>
                <li>
                    <a href="login.jsp">退出</a>
                </li>
            </ul>
        </div>
    </header>
    <div id="container">
        <nav>
            <ul>
                <li><a href="checkKouling.jsp" target="zzz">签到</a></li>
                <li><a href="kebiao.jsp" target="zzz">课表</a></li>
                <li><a href="qingjia.jsp" target="zzz">请假</a></li>
                <li><a href="chuqin.jsp" target="zzz">出勤</a></li>
                <li><a href="modifypwd.jsp" target="zzz">修改密码</a></li>
            </ul>
        </nav>
        <article>
            <div id="apdiv1"><iframe name="zzz" src="checkKouling.jsp"></div>
          	<div id="apDiv2"><iframe name="zzz" src="kebiao.jsp"></div>
     		<div id="apDiv3"><iframe name="zzz" src="qingjia.jsp"></div>
     		<div id="apDiv4"><iframe name="zzz" src="chuqin.jsp"></div>
      	    <div id="apDiv5"><iframe name="zzz" src="xiugaimima.jsp"></div>
    </article>
    </div>
    <!-- <footer></footer> -->
</body>
</html>