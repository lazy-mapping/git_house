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
			<!-- <th>课程编号</th> -->
			<th>出勤编号</th>
			<th>工号</th>
			<th>出勤时间</th>
			<th>教师考勤状态</th>
			<th>年</th>
			<th>月</th>
			</tr>
			<%  
			String count=request.getParameter("countt");
			int a = Integer.valueOf(count);
			   for(int i=0;i<a;i++){
			%>
		   <tr>
				<td>${sessionScope.session_list.get(i).getKaoqin_id()}</td>
				<td>${sessionScope.session_list.get(i).getT_id()}</td>
				<td>${sessionScope.session_list.get(i).getTime()}</td>
				<td>${sessionScope.session_list.get(i).getStauts()}</td>
			    <td>${sessionScope.session_list.get(i).getYear()}</td>
			    <td>${sessionScope.session_list.get(i).getMonth()}</td>
			<%
			}
			%>	
			</tr>
			</table>
</div>

</body>
</html>