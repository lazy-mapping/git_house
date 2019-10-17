<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>签到</title>
<style type="text/css">
input[type="text"]{
	font-size:24px;
	text-align: center;
	border: 2px solid #3498db;
	padding: 14px 10px;
	width: 300px;
	border-radius: 12px 0 0 12px;
	color: black;
	}
input[type="button"]{
	border: 2px solid #3498db;
	padding: 14px 10px;
	text-align: center;
	background: #3498db;
	border-radius: 0 12px 12px 0;
	width:100px;
	font-size:24px;
	color:white;
	}
</style>
</head>
<body>
<form action="QianDaoServlet" method="post">
<table align="center" width="400px" height="100px" style="margin-top:150px">
  <tr>
   <td width="100px"><input  type="submit" value="生成口令" /></td>
   <td width="300px"><input name="kouling" type="text" placeholder="点名口令" maxlength="6"></td>
  </tr>
</table>
</form>
</body>
</html>