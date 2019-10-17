<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>修改密码</title>
<style type="text/css">
#a{
	padding-top:100px;
	padding-left:450px;}
</style>
</head>

<body>
<div id="a">
<form action="updatePwdServlet" method="post" name="form" >
       旧密码<input type="text" name="pwd" size="20"><br><br>
       新密码<input type="text" name="newpassword" size="20"><br><br>
    确认新密码<input type="text" name="ensurepassword" size="20"><br><br>
    <input type="submit" value="修改">
</form>
</div>
</body>
</html>
