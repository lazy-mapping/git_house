<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>

<html>

<head>
<title>新增设备</title>
<script type="text/javascript" src="${pageContext.request.contextPath}/My97DatePicker/WdatePicker.js"></script>
<LINK href="${pageContext.request.contextPath }/css/Style.css" type="text/css" rel="stylesheet">
<script type='text/javascript' src="${pageContext.request.contextPath }/script/validate.js"></script>
<SCRIPT type="text/javascript">
function ini(){
   document.all.devType.focus();
}

function checkNumber(item){
			if(item.value != ""){
				isNumber1(item);
			}
		}
		
function isNumber1(obj){		
	if(obj == null){
				alert("对象为空!");
		return false;
		}

		}
		
function checkDecimal(item){
			if(item.value != "0" && item.value != "")
			{
				isDecimal(item);
			}
		}
		
function isDecimal(obj){
				if(obj == null)
					alert("对象为空");
					
				var v = obj.value;
					
				var pattern = /^[0-9]+\.\d{1,2}$/;
				flag = pattern.test(v);
				
				if(!flag){
					alert("输入格式为: 0.00");			
					obj.select();
					obj.focus();
					return false;
				}else{
					return true;
				}
			}
		
		
function returnMethod(){
	var theForm = document.Form2;
	
	if(Trim(theForm.DevName.value)==""){
	
	    	alert("请输入设备名称");
			theForm.DevName.focus();
			return false;
	    }
	if(theForm.DevState.value==""){
	
	    	alert("请选择设备状态");
			theForm.DevState.focus();
			return false;
	    }    


	   if (theForm.Remrk.value.length >250)
		{
		    alert("备注不能超过250个汉字！");
		    theForm.Remrk.focus();
		    return false;
		}

		if(Trim(theForm.AdjustPeriod.value)=="")
		{
		  alert("请选择检修周期！");
		  theForm.AdjustPeriod.focus();
		   return false;
		} 
	 if(window.opener)
	{
		 document.Form2.action="${pageContext.request.contextPath }/equapment/elecDeviceAction_save.do";							  
		 document.Form2.submit();
		alert("保存成功！"); 
	    window.opener.Pub.submitActionWithForm('Form2','${pageContext.request.contextPath }/equapment/elecDeviceAction_home.do','Form1');
	
	}
	self.close();
	}										
</SCRIPT>
<title>仪器设备信息编辑</title>
</head>

<body>

	<form name="Form2" method="post" >

  	<table cellSpacing="1" cellPadding="5" width="680" align="center" bgColor="#eeeeee" style="border: 1px solid #8ba7e3" border="0">
		
		<tr>
			<td class="ta_01"  colSpan="4" align="center" background="${pageContext.request.contextPath }/images/b-info.gif">
			<font face="宋体" size="2"><strong>设备信息编辑</strong></font>
			</td>
		</tr>
		
		<tr>
			<td width="18%" align="center" bgColor="#f5fafe" class="ta_01">设备名称：</td>
			<td class="ta_01" bgColor="#ffffff">
				<s:textfield name="DevName" maxlength="25" id="DevName" size="20"></s:textfield>
				           &nbsp;<font color="#FF0000">*</font> </td>		
			<td class="ta_01"  align="center" bgColor="#f5fafe">所属单位：</td>
			<td class="ta_01" bgColor="#ffffff">
				<s:select list="#request.jctList" name="jctID" id="jctID" listKey="ddlCode" listValue="ddlName"
				  headerKey="" headerValue="请选择" cssStyle="width:155px">
				</s:select>	<font color="#FF0000">*</font></td>
		</tr>

	
		<tr>
			<td class="ta_01" align="center" bgColor="#f5fafe">规格型号：</td>
			<td class="ta_01" bgColor="#ffffff">
				<s:textfield name="DevType" maxlength="25" id="DevType" size="20"></s:textfield></td>		
			<td class="ta_01" align="center" bgColor="#f5fafe">检修周期：</td>
			<td class="ta_01" bgColor="#ffffff">
				<s:textfield name="AdjustPeriod" maxlength="25" id="AdjustPeriod" size="20"  onblur='checkNumber(this)' ></s:textfield>
				<font color="#FF0000">*</font></td>		
		</tr>
		
		<tr>
			<td class="ta_01" align="center" bgcolor="#f5fafe" height="22">设备状态：</td>
			<td class="ta_01" bgcolor="#ffffff" align="left">
				<s:select list="#request.devStateList" name="DevState" id="DevState" listKey="ddlCode" listValue="ddlName" 
					headerKey="" headerValue="请选择" cssStyle="width:155px">
				</s:select><font color="#FF0000">*</font></td>
			<td class="ta_01" align="center" bgColor="#f5fafe">厂&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;家：</td>
			<td class="ta_01" bgColor="#ffffff">
				<s:textfield name="produceHome" maxlength="25" id="produceHome" size="20" ></s:textfield></td>
		</tr>

		<tr>
			<td class="ta_01" align="center" bgColor="#f5fafe">产&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;地：</td>
			<td class="ta_01" bgColor="#ffffff">
				<s:textfield name="ProduceArea" maxlength="25" id="ProduceArea" size="20" ></s:textfield><font face="宋体" color="red"></font></td>
			<td class="ta_01" align="center" bgColor="#f5fafe">用&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;途：</td>
			<td class="ta_01" bgColor="#ffffff">
				<s:textfield name="Uses" maxlength="25" id="Uses" size="20" ></s:textfield><font face="宋体" color="red"> </font></td>
		</tr>

		<tr>
			<td class="ta_01" align="center" bgColor="#f5fafe">额定电压：</td>
			<td class="ta_01" bgColor="#ffffff">
				<s:textfield name="voltage" maxlength="25" id="voltage" size="20" ></s:textfield></td>
			<td class="ta_01" align="center" bgColor="#f5fafe">额定功率：</td>
			<td class="ta_01" bgColor="#ffffff">
				<s:textfield name="power" maxlength="25" id="power" size="20" ></s:textfield></td>
		</tr>
		
		<tr>	
	        <td class="ta_01" align="center" bgColor="#f5fafe" height="30">使用日期：</td>
			<td class="ta_01" bgColor="#ffffff" height="30">
				<s:textfield name="UseDate" maxlength="25" id="UseDate" size="20" onClick="WdatePicker()" ></s:textfield></td>			
		</tr>		   
			
												
		<TR>
			<TD class="ta_01" align="center" bgColor="#f5fafe">备&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;注：</TD>
			<TD class="ta_01" bgColor="#ffffff" colSpan="3">
			<s:textarea name="Remrk" cssStyle="WIDTH:95%" rows="4" cols="52"></s:textarea></TD>
		</TR>
		
		<tr>
			<td class="ta_01" style="width: 100%" align="center" bgColor="#f5fafe" colSpan="4"> 
			 
			<input type="button" name="BT_Submit" value="保存" id="BT_Submit" style="font-size:12px; color:black;" height=22; width=55; 
				onclick="returnMethod()">
			<font face="宋体">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</font>
			
			
			<input style="font-size:12px; color:black;" height=22;width=55; type="reset" value="关闭" ID="Reset1" NAME="Reset1" onClick="window.close();">
			<span id="Label1"></span></td>
		</tr>
		
	</table>
	
	
	</form>	
</body>
</html>