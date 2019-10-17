<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>课程表</title>
<style type="text/css">
tr {
	text-align: center;
}
</style>
</head>
<body>
<form action="GetCourseServlet" method="post">
<table align="center"  width="400px"  border="0" cellspacing="0">
  <tr>
    <td><input type="submit" value="课程表"></td>
  </tr>
</table>
</form>
	<table width="1000" height="500" border="1" align="center"
		cellspacing="0">
		<tr bgcolor="#999999">
			<td>节</td>
			<td>星期一</td>
			<td>星期二</td>
			<td>星期三</td>
			<td>星期四</td>
			<td>星期五</td>
		</tr>

	   <tr>
			<td bgcolor="#999999">1-2节</td>
			<td>${sessionScope.session_kebiao.get("1")}</td>
			<td>${sessionScope.session_kebiao.get("6")}</td>
			<td>${sessionScope.session_kebiao.get("11")}</td>
			<td>${sessionScope.session_kebiao.get("16")}</td>
			<td>${sessionScope.session_kebiao.get("21")}</td>
		</tr>
		<tr>
			<td bgcolor="#999999">3-4节</td>
			<td>${sessionScope.session_kebiao.get("2")}</td>
			<td>${sessionScope.session_kebiao.get("7")}</td>
			<td>${sessionScope.session_kebiao.get("12")}</td>
			<td>${sessionScope.session_kebiao.get("17")}</td>
			<td>${sessionScope.session_kebiao.get("22")}</td>
		</tr>
		<tr>
			<td bgcolor="#999999">5-6节</td>
			<td>${sessionScope.session_kebiao.get("3")}</td>
			<td>${sessionScope.session_kebiao.get("8")}</td>
			<td>${sessionScope.session_kebiao.get("13")}</td>
			<td>${sessionScope.session_kebiao.get("18")}</td>
			<td>${sessionScope.session_kebiao.get("23")}</td>
		</tr>
		<tr>
			<td bgcolor="#999999">7-8节</td>
			<td>${sessionScope.session_kebiao.get("4")}</td>
			<td>${sessionScope.session_kebiao.get("9")}</td>
			<td>${sessionScope.session_kebiao.get("14")}</td>
			<td>${sessionScope.session_kebiao.get("19")}</td>
			<td>${sessionScope.session_kebiao.get("24")}</td>
		</tr>
		<tr>
			<td bgcolor="#999999">9-10节</td>
			<td>${sessionScope.session_kebiao.get("5")}</td>
			<td>${sessionScope.session_kebiao.get("10")}</td>
			<td>${sessionScope.session_kebiao.get("15")}</td>
			<td>${sessionScope.session_kebiao.get("20")}</td>
			<td>${sessionScope.session_kebiao.get("25")}</td>
		</tr> 
	</table>
</body>
</html>
