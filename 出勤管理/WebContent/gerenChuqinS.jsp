<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="utf-8" import ="domain.Record" import="java.util.ArrayList" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>查看出勤记录表</title>
</head>
<body>
<div class="modal fade" id="kechengList" tabindex="-1"
	 role="dialog" aria-labelledby="myModalLabel">
			<table  border='1' align='center' width='100%' height='450px'>
			<tr style="height: 80px">
			<th>出勤编号</th>
			<th>上课时间</th>
			<th>学号</th>
			<th>教师编号</th>
			<th>课程编号</th>
			<th>学生考勤状态</th>
			<th>周</th>
			<th>学期</th>
			<th>年</th>
			</tr>
			<%  
			String count=request.getParameter("counts");
			int a = Integer.valueOf(count);
			   for(int i=0;i<a;i++){
			%>
		   <tr>
				<td>${sessionScope.session_lists.get(i).getKaoqin_id()}</td>
				<td>${sessionScope.session_lists.get(i).getTime()}</td>
				<td>${sessionScope.session_lists.get(i).getS_id()}</td>
				<td>${sessionScope.session_lists.get(i).getT_id()}</td>
				<td>${sessionScope.session_lists.get(i).getCourse_id()}</td>
				<td>${sessionScope.session_lists.get(i).getStauts()}</td>
				<td>${sessionScope.session_lists.get(i).getWeek()}</td>
				<td>${sessionScope.session_lists.get(i).getTerm()}</td>
			    <td>${sessionScope.session_lists.get(i).getYear()}</td>
			<%
			}
			%>	
			</tr>
			</table>
</div>
</body>
</html>