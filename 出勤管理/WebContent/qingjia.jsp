<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<title>添加请假信息</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
</head>
<body bgcolor="#FFCC99" text="#000000">

<table width="768" height="25" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td colspan=5 align=center><font size="3" color=red><br>添加新的假条信息</font></td>
  </tr>
</table>  
        <form name="form1" method="post" action="QingjiaServlet">
          <table width="90%" border="0" cellspacing="1" cellpadding="1">
          <tr> 
              <td align="right" width="35%">假条编号：</td>
              <td width="65%"> 
                <input type="text" name="leaveId" maxlength="40" size="30"><font color=red>*必填</font>
              </td>
            </tr>
            <tr> 
              <td align="right" width="35%">姓名：</td>
              <td width="65%"> 
                <input type="text" name="employeeName" maxlength="25" size="20"><font color=red>*必填</font>
              </td>
            </tr>
             <tr> 
             	
              <td align="right" width="35%">开始时间：</td>
              <td width="65%"> 
               <input type=Date name=startTime size=20><font color=red>*必填</font>
		    			</td>
            </tr>
            <tr>
              <td align="right" width="35%">结束时间：</td>
              <td width="65%"> 
               <input type=Date name=endTime size=20><font color=red>*必填</font>
		    			</td>
            </tr>
            <tr> 
              <td align="right" width="35%">天数：</td>
              <td width="65%"> 
         				<input type=text name=day size=20>
              </td>
            </tr>
            
             <tr> 
              <td align="right" width="35%">请假原因：</td>
              <td width="65%"> 
                 <textarea name="reason" cols="40" rows="6"></textarea>
              </td>
            </tr>
            
             <tr> 
              <td align="right" width="35%">批准人：</td>
              <td width="65%"> 
                <input type="text" name="approvePerson" size="20" maxlength="150">
              </td>
            </tr>
            
            <tr> 
              <td align="right" width="35%">&nbsp;</td>
              <td width="65%"> 
                <input type="submit" name="Submit" value="提交">
                <input type="reset" name="reset" value="重置">
              </td>
            </tr>
          </table>
        </form>
</body>
</html>