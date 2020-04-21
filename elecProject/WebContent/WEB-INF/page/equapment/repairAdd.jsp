<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<html>
<head>
<title>设备检修记录添加</title>

<link href="${pageContext.request.contextPath }/css/Style.css" type="text/css" rel="stylesheet">
<script language="javascript" src="${pageContext.request.contextPath }/script/function.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/My97DatePicker/WdatePicker.js"></script>
		<script language="javascript" src="${pageContext.request.contextPath }/script/changePageBackUp.js"></script>
<script language="javascript">
function shareOnChange(mySelect)
{
   if (mySelect.selectedIndex == 0)
    {
    PPassword.style.display = ""
    
    }
   else
    PPassword.style.display = "none"
}

</script>

<SCRIPT type="text/javascript">

     function check(){
         var theForm = document.forms[0];
   
	    
	    if(Trim(theForm.startDate.value)==""){
	
	    	alert("请输入校准日期");
			theForm.startDate.focus();
			return false;
	    }
	   if(theForm.comment.value.length>250){
     
        	alert("备注字符长度不能超过250");
			theForm.comment.focus();
			return false; 
        }
        if(theForm.record.value.length>250){
     
        	alert("记录描述字符长度不能超过250");
			theForm.record.focus();
			return false; 
        }
	 if(window.opener)
	{
		document.Form1.action="addDeviceJ.do";
		document.Form1.submit();
		alert("保存成功！");
		
	    window.opener.Pub.submitActionWithForm('Form1','toDeviceJ.do','F1'); 
	    
	}
	window.close();
}
</SCRIPT>
<SCRIPT type="text/javascript">
function submitrequest(action){
     eval("document.location='"+action+"'");
}
function returnMethod(){
	return check();
}

</SCRIPT>
</head>
<body  onunload="submitrequest('clearSession.do?action=J')">

<form name="Form1" method="post" >

    <s:hidden name="SeqID"></s:hidden>
    <s:hidden name="DevID"></s:hidden>
    <s:hidden name="IsHaving" value="1"></s:hidden>
    <s:hidden name="elecDevice.DevID"></s:hidden>
    <s:hidden name="elecDevice.jctID"></s:hidden>
    <s:hidden name="elecDevice.jctUnitID"></s:hidden>
    <s:hidden name="elecDevice.DevType"></s:hidden>
    <s:hidden name="elecDevice.DevState"></s:hidden>
    <s:hidden name="elecDevice.ProduceHome"></s:hidden>
    <s:hidden name="elecDevice.voltage"></s:hidden>
    <s:hidden name="elecDevice.power"></s:hidden>
    <s:hidden name="elecDevice.ProduceArea"></s:hidden>
    <s:hidden name="elecDevice.Uses"></s:hidden>

    <br>
           
	<table  cellSpacing="1" cellPadding="5" width="680" align="center" bgColor="#eeeeee" style="border:1px solid #8ba7e3" border="0">
	<TBODY>			
	<tr>
		<td class="ta_01" align="center" background="${pageContext.request.contextPath }/images/b-info.gif">
		<font face="宋体" size="2"><strong>设备检修记录添加</strong></font></td>
	</tr>
	
	<tr>
		<td>
		<table cellpadding="0" cellspacing="0" border="0" width="100%">
		<tbody>

    <tr>
		<td width="153" class="ta_01" align="center" bgColor="#f5fafe" height="22">所属单位：</td>
		<td class="ta_01" bgColor="#ffffff">	
				<s:select list="#request.jctList" name="jctID" id="jctID"
								  listKey="ddlCode" listValue="ddlName"							
								  cssStyle="width:155px">
				</s:select></td>		
        <td width="100" class="ta_01" align="center" bgColor="#f5fafe" height="22">设备名称：</td>
		<td class="ta_01" bgColor="#ffffff"><font face="宋体" color="red">								
			 <s:textfield name="elecDevice.DevName" id="DevName" size="21"></s:textfield></font></td>
	</tr>
	
	<tr>
		<td width="153" class="ta_01" align="center" bgColor="#f5fafe" height="22">检修周期：</td>
		<td class="ta_01" bgColor="#ffffff" width="224">
			<s:textfield name="elecDevice.AdjustPeriod" maxlength="25" id="AdjustPeriod" size="20"  onblur='checkNumber(this)' ></s:textfield>
        <td width="100" class="ta_01" align="center" bgColor="#f5fafe" height="22">使用日期：</td>
		<td class="ta_01" bgColor="#ffffff"><font face="宋体" color="red">
      		 <s:textfield name="elecDevice.UseDate" maxlength="25" id="UseDate" size="20" onClick="WdatePicker()" ></s:textfield>
         </font></td>
	</tr>
     
    <tr>
	    <td class="ta_01" align="center" bgcolor="#f5fafe" height="22">检修日期：</td>
	    <td class="ta_01" bgcolor="#ffffff"><font face="宋体" color="red">
			<s:textfield name="StartDate" type="text" size="19" id="StartDate" onclick="WdatePicker()"></s:textfield></font>&nbsp;<font color="#FF0000">*</font></td>
		<td class="ta_01" align="center" bgcolor="#ffffff" height="22">检修状态：</td>
					<td class="ta_01" bgcolor="#ffffff">
					<s:select list="#request.repairStateList" name="repairState" id="repairState"
								  listKey="ddlCode" listValue="ddlName"
								  cssStyle="width:155px">
				</s:select></td>
	</tr>
	
	<TR>
		<TD class="ta_01" align="center" bgColor="#f5fafe">检修记录：</TD>
		<TD class="ta_01" bgColor="#ffffff" colSpan="3">
	   <s:textfield  name="isHaving" id="isHaving" class="bg" style="width:50"   onChange="shareOnChange(this)" ></s:textfield> 
            &nbsp;&nbsp;&nbsp;&nbsp;
            <span  id=PPassword style="DISPLAY: none">
             <input style="font-size:12px; color:black; height=22;width=55"  id="BT_Add" type="button" value="详细" name="BT_Add" onClick="openWindowWithName('repairFile.jsp?liu1=1&devId=c51da3d47c814b488d4a4206c7a1703f',800,450,'ECC');" />
           </span>
        </TD>
    </TR>

	<TR>
		<TD class="ta_01" align="center" bgColor="#f5fafe">备&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;注：</TD>
		<TD class="ta_01" bgColor="#ffffff" colSpan="3">
		<s:textarea name="Remrk" cssStyle="WIDTH:95%" rows="4" cols="52"></s:textarea>
		</TD>
	</TR>
	
	<tr>
		<td class="ta_01" align="center" bgColor="#f5fafe" height="22">记录描述：</td>
		<td height="22" colspan="3" bgColor="#FFFFFF" class="ta_01"><font face="宋体" color="red"> </font>							
		<textarea name="record" id="record" style="WIDTH:96%" rows="3"></textarea></td>									
   </tr>

    <tr>
		<td class="ta_01" style="WIDTH: 100%" align="center" bgColor="#f5fafe" colSpan="4"><input type="button" name="BT_Submit" value=" 保存"   onclick="returnMethod()" id="BT_Submit" style="font-size:12px; color:black; height=22;width=55" /><FONT face="宋体">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</FONT><INPUT style="font-size:12px; color:black; height=22;width=55"  type="reset" value=" 关闭" ID="Reset1" NAME="Reset1" onClick="window.close();" />
			<span id="Label1"></span>					</td>
	   </tr >
        </tbody>

		</table>
		</td>
	</tr>
				
	</TBODY>
	</table>
	</form>
</body>
</HTML>