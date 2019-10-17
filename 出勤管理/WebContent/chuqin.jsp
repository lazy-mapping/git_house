<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script type="text/javascript" src="js/chuqin.js"></script>
<title>查看出勤</title>
<style type="text/css">
input[name="chuqin"]{
	text-align:center;
	width:200px;
	height:100px;
	border: 2px solid #3498db;
	background: #3498db;
	border-radius: 12px 0 0 12px;
	color:white;
	font-size:36px;}
input[name="quanqin"]{
	text-align:center;
	width:200px;
	height:100px;
	border: 2px solid #3498db;
	background: #3498db;
	border-radius:0 12px 12px 0;
	color:white;
	font-size:36px;}
</style>
</head>
<body>
${sessionScope.session_type}
<form action="ChuQinServlet" method="post">
<table align="center" style="margin-top:150px" width="400px" height="100px" border="0" cellspacing="0">
  <tr>
    <td><input name="chuqin" type="submit" value="出勤记录"  onClick="chuqin()"/></td>
   <!--  <td><input name="quanqin" type="submit" value="全勤记录"  onClick="quanqin()"/></td> -->
  </tr>
</table>
</form>
</body>
</html>