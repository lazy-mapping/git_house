<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
	<meta name="viewport" content="width=device-width, initial-scale=1">
    <title>黄河科技学院出勤系统</title>
	<link rel="stylesheet" type="text/css" href="css/login.css"/>
	<link rel="shortcut icon" href="img/favicon.ico" type="image/x-icon">
	<script type="text/javascript" src="js/login.js">

	</script>
  </head>
  <body>
	  <form action="LoginServlet" method="post" class="box">
			<h1>Login</h1>
			<!-- autocomplete="off" 清除input文本输入框之前提交的表单信息，默认为on。如果整个表单都不想让浏览器自动记录之前输入的值，我们可以给from表单加上 -->
			<!-- 输入框默认值value="${form.id }" -->
			<input id="usename" type="text" name="id" placeholder="Id" pattern="\d{10}$" required/>
			<input id="pwd" type="password" name="password" placeholder="Password" />
			<br><a href="#">
			<input type="radio" name="type" value="teacher">教师
		    <input type="radio" name="type" value="student" checked="ok">学生
		    <input type="radio" name="type" value="admin">管理员</a>
			<input type="submit" name="" id="sub" value="Login" onclick="return isVaildInfo()" />
	  </form>
  </body>
</html>
