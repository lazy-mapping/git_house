<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>回执消息</title>
<style type="text/css">
body{
	background:url(img/iforget_body_bg.jpg);
	text-align:center;
	margin-top:200px;}
</style>
</head>
<body>
<h3>错误页面</h3>
<p>错误原因:
<%=request.getAttribute("msg") %>
</p>
</body>
</html>