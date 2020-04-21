<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>

<html>

	<head>
		<title>批量添加设备检修</title>
		<link href="${pageContext.request.contextPath }/css/Style.css" type="text/css" rel="stylesheet" />
		<script language="javascript" src="${pageContext.request.contextPath }/script/public.js" charset="gb2312"></script>
		<script language="javascript" src="${pageContext.request.contextPath }/script/function.js"></script>
		<script language="javascript" src="${pageContext.request.contextPath }/script/page.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/My97DatePicker/WdatePicker.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath }/script/validate.js"></script>
		<script language="javascript" src="${pageContext.request.contextPath }/script/pub.js"></script>
		<script language="javascript" src="${pageContext.request.contextPath }/script/changePageBackUp.js"></script>

<SCRIPT type="text/javascript">

  function jsJumpToBeginDevice(){

     changeformvalue();
	 document.F1.pageNo.value="1";
	 Pub.submitActionWithForm('Form2','changePageDevice.do','Form1'); 
  }

  function jsJumpToEndDevice(){

	 changeformvalue();
	 document.F1.pageNo.value=document.Form1.sumPage.value;
	 Pub.submitActionWithForm('Form2','changePageDevice.do','Form1'); 

  }
  
  function jsNextDevice(){

     changeformvalue();
	 document.F1.direction.value="NEXT";
	 document.F1.goPage.value=""; 
     Pub.submitActionWithForm('Form2','changePageDevice.do','Form1'); 
}

  function jsPreviousDevice(){

     changeformvalue();
	 document.F1.direction.value="PREVIOUS";
	 document.F1.goPage.value="";
     Pub.submitActionWithForm('Form2','changePageDevice.do','Form1'); 
 }
 
  function jsJumptoDevice(){

     changeformvalue();
	 var gopage=parseInt(document.Form1.goPage.value);
	 var sumpage=parseInt(document.Form1.sumPage.value);
	
	 if(gopage<=sumpage&&gopage>=1)
	 {	 	
	 	document.F1.pageNo.value=document.Form1.goPage.value;
        Pub.submitActionWithForm('Form2','changePageDevice.do','Form1');   
	 }else{
	    alert("不存在此页，请重新输入页数"); 
	 }
 }
  
  function  savejilu(){
	  var selectDev = document.getElementsByName("DevID");
		 var flag = false;
	     for(var i=0;i<selectDev.length;i++){
	     	if(selectDev[i].checked){
	     		flag = true;
	     	} 
	     }
	     if(!flag){
	     	alert("没有选择执行操作的记录！不能执行该操作");
	     	return false;
	     }
	     if(window.opener)
	 	{
     document.Form2.action="${pageContext.request.contextPath }/equapment/elecRepairAction_save.do";							  
	 document.Form2.submit();
	 alert("保存成功！"); 
	 	}
	 	self.close();
  }
  
 
	function JctSelectChange(mySelect)
	{
	   if (mySelect.selectedIndex != "" && document.Form1.devType.selectedIndex != "")
	    {
	    	devTuzhi.style.display = ""
	    
	    } else {
	    	devTuzhi.style.display = "none"
	    }
	}
	
	function DevTypeSelectChange(mySelect)
	{
	   if (mySelect.selectedIndex != "" && document.Form1.jctId.selectedIndex != "")
	    {
	    	devTuzhi.style.display = ""
	    
	    } else {
	    	devTuzhi.style.display = "none"
	    }
	}
	
		function upload(fn){
			var jctId=document.Form1.jctId.value;
			if(jctId==0){
          		alert("请先选择所属单位");
          	return;
       		}
			
			var str = "initUpload.do?belongTo=" + fn
						+ "&jctId=" + jctId + "&projid=" + document.F1.jctId.selectedIndex + "-" + document.F1.devType.selectedIndex;
			
			OpenWindow('uploadInit',str,800,450);
		}	

		
		 //设备:全部选中/全部不选中
		   function checkAllUser(device){
			   var selectdev = document.getElementsByName("DevID");
		      for(var i=0;i<selectdev.length;i++){
		    	  selectdev[i].checked = device.checked;
		      }
		   }

</SCRIPT>
</head>

<body>
	
     <form name="Form1" method="post"  id="Form1" style="margin:0px;">		   
		
          
          <!-- 判断是跳转Index.jsp还是跳转List.jsp的标识 -->
		  <s:hidden name="initpage" value="1"></s:hidden>
		  <s:hidden name="pageNO"></s:hidden><!-- 当前第几页 -->

		   		   
			<table cellspacing="1" cellpadding="0" width="90%" align="center" bgcolor="#f5fafe" border="0">
			<tr>
				 <td class="ta_01" align="center" background="${pageContext.request.contextPath }/images/b-info.gif">
							 <font face="宋体" size="2"><strong>仪器设备</strong></font>
				 </td>
		   	</tr>
			
			<tr>
				<td>
				<table cellpadding="0" cellspacing="0" border="0" width="100%">
					<tr>
						
						<td width="100" class="ta_01" align="center" bgcolor="#f5fafe" height="22">设备名称：</td>
						<td class="ta_01" colspan="3">
							<s:textfield name="DevName" id="DevName" size="21"></s:textfield>
						</td>
					</tr>
				</table>
		  		</td>
		   </tr>
		    
	    </table>
  </form>
   	
  <br>	
  	
 <form name="Form2" method="post" id="Form2" style="margin:0px;">

	 <table cellpadding="0" cellspacing="0" border="0" width="90%" align="center">
		<TR>
			<TD align="center" background="${pageContext.request.contextPath }/images/cotNavGround.gif" width="25"><img src="${pageContext.request.contextPath }/images/yin.gif" width="15"></TD>
			<TD class="DropShadow" background="${pageContext.request.contextPath }/images/cotNavGround.gif" width="80">设备信息列表</TD>
			
			<td  align="right" class="ta_01" >
				<INPUT name="BT_Search" type="button"  id="BT_Search" value="查询" style="font-size:12px; color:black; height=20;width=50"  
					onclick="gotoquery('elecRepairAction_moreAddList.do')">
				
				<input  id="BT_Add" type="button" value="添加" name="BT_Add" style="font-size:12px; color:black; height=20;width=50"   
					 onclick="savejilu()">   											
			</td>
		</TR>
     </table>

   <table cellpadding="0" cellspacing="0" border="0" width="90%" align="center">			
		<tr>
			<td class="ta_01" align="center" bgcolor="#f5fafe">
				<table cellspacing="0" cellpadding="1" rules="all" bordercolor="gray" border="1" id="DataGrid1"
							style="BORDER-RIGHT:gray 1px solid; BORDER-TOP:gray 1px solid; BORDER-LEFT:gray 1px solid; WIDTH:100%; WORD-BREAK:break-all; BORDER-BOTTOM:gray 1px solid; BORDER-COLLAPSE:collapse; BACKGROUND-COLOR:#f5fafe; WORD-WRAP:break-word">
				<tr style="FONT-WEIGHT:bold;FONT-SIZE:12pt;HEIGHT:25px;BACKGROUND-COLOR:#afd1f3">
				    <td align="center" width="5%" height=22 background="${pageContext.request.contextPath }/images/tablehead.jpg"><input type="checkbox" name="selectUserAll" onclick="checkAllUser(this)"></td>
					<td width="15%" align="center" height=22 background="${pageContext.request.contextPath }/images/tablehead.jpg">名称</td>
					<td align="center" width="9%" height=22 background="${pageContext.request.contextPath }/images/tablehead.jpg">所属单位</td>			
				</tr>	
							
				<s:if test="#request.deviceList!=null && #request.deviceList.size()>0">
					<s:iterator value="#request.deviceList" var="device">
						<tr onmouseover="this.style.backgroundColor = 'white'" onmouseout="this.style.backgroundColor = '#F5FAFE';">
						
							<td style="HEIGHT:22px" align="center" width="5%">
								<input type="checkbox" name="DevID" id="DevID" value="<s:property value="#device.DevID"/>">
							</td>
							<td style="HEIGHT:22px" align="center" width="15%">
								<s:property value="#device.DevName"/>
							</td>
													
							<td style="HEIGHT:22px" align="center" width="8%">
								<s:property value="#device.jctID"/>
							</td>

						</tr>
					</s:iterator>
				</s:if>		
 																						   														
			</table>
			</td>
		</tr>		

<tr>
      <td width="100%" height="1"  colspan="2">
        <table border="0" width="100%" cellspacing="0" cellpadding="0">
         <s:if test="#request.page!=null">
         		<tr>
		             <td width="15%" align="left">总记录数：<s:property value="%{#request.page.totalResult}"/>条</td> 
		             <td width="14%" align="right"></td>   
		             <s:if test="#request.page.firstPage">
		             	<td width="8%" align="center">首页&nbsp;&nbsp;</td>
		             	<td width="10%" align="center">上一页&nbsp;&nbsp;&nbsp;</td>
		             </s:if>   
		             <s:else>
		             	<td width="8%" align="center"><u><a href="#" onClick="gotopage('elecRepairAction_moreAddList.do','start')">首页&nbsp;&nbsp;|</a></u></td>
		             	<td width="10%" align="center"><u><a href="#" onClick="gotopage('elecRepairAction_moreAddList.do','prev')">上一页&nbsp;&nbsp;&nbsp;|</a></u></td>
		             </s:else>
		             <s:if test="#request.page.lastPage">
					 	<td width="10%" align="center">下一页&nbsp;&nbsp;&nbsp;</td>
		             	<td width="8%" align="center">末页</td>
		             </s:if>
		             <s:else>
		             	<td width="10%" align="center"><u><a href="#" onClick="gotopage('elecRepairAction_moreAddList.do','next')">下一页&nbsp;&nbsp;&nbsp;|</a></u></td>
		             	<td width="8%" align="center"><u><a href="#" onClick="gotopage('elecRepairAction_moreAddList.do','end')">末页</a></u></td>
		             </s:else>
		             <td width="6%" align="center">第<s:property value="%{#request.page.pageNo}"/>页</td>
		             <td width="6%" align="center">共<s:property value="%{#request.page.sumPage}"/>页</td>
		             <td width="21%" align="right">至第<input size="1" type="text" name="goPage" >页
		
		
		             <u><a href="#" onClick="gotopage('elecRepairAction_moreAddList.do','go')">确定</a></u></td>
		             <!-- 当前页 -->
		             <td><input type="hidden" name="pageNO" value="${page.pageNo }" ></td> 
		             <!-- 上页 --> 
		             <td><input type="hidden" name="prevpageNO" value="${page.pageNo-1 }"></td>
		             <!-- 下页 --> 
		             <td><input type="hidden" name="nextpageNO" value="${page.pageNo+1 }"></td>
		             <!-- 总页 -->
		             <td><input type="hidden" name="sumPage" value="${page.sumPage }" ></td>
	           </tr>
         </s:if>
        </table>       
      </td>
    </tr> 
      <tr>  
    <table cellSpacing="1" width=90% cellPadding="5" height="80" align="center" bgColor="#eeeeee" style="border: 1px solid #8ba7e3" border="0" >
				
				<tr>
					<td align="center" bgcolor="#f5fafe" class="ta_01">检修时间：</td>
					<td bgColor="#f5fafe" class="ta_01">
					<input name="startDate" type="text" class="bg" id="startDate" onclick="WdatePicker()" size="10" readonly>&nbsp;<font color="#FF0000">*</font></td>
				</tr>
	
				
				<tr>
					<td align="center" bgcolor="#f5fafe" class="ta_01">检修记录：</td>
					<td bgColor="#f5fafe" class="ta_01">
					<s:textarea name="Record" cssStyle="WIDTH:95%" rows="4" cols="52"></s:textarea></td>
				</tr>
				
			</table>
			</tr> 
 
	
	</table>
		</form>
		
	</body>
</html>

