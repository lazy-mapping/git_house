<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>


 <form name="Form2" method="post" id="Form2" style="margin:0px;">
	 <table cellpadding="0" cellspacing="0" border="0" width="90%" align="center">
		<TR>
			<TD align="center" background="${pageContext.request.contextPath }/images/cotNavGround.gif" width="25"><img src="${pageContext.request.contextPath }/images/yin.gif" width="15"></TD>
			<TD class="DropShadow" background="${pageContext.request.contextPath }/images/cotNavGround.gif" width="80">设备信息列表</TD>
			
			<td  align="right" class="ta_01" >
				<INPUT name="BT_Search" type="button"  id="BT_Search" value="查询" style="font-size:12px; color:black; height=20;width=50"  
					onclick="gotoquery('elecDeviceAction_home.do')">
					<!-- onclick="gotosearchDevice()">  --> 
				<input  id="BT_Add" type="button" value="添加" name="BT_Add" style="font-size:12px; color:black; height=20;width=50"   
				    onclick="openWindow('${pageContext.request.contextPath }/equapment/elecDeviceAction_add.do',800,400);">
				<!-- <input name="BT_export" type="button" " id="BT_export" value="导出" style="font-size:12px; color:black; height=20;width=50" 
					onClick="openWindow('exportSarDevice.do','600','400')" > -->
			    <!-- <input type="button" name="setexcelExport" style="font-size:12px; color:black; height=20;width=80"  value="导出设置" id="setexcelExport"  onClick="openWindow('equapmentExport.jsp','600','400')"  > -->
				<input style="font-size:12px; color:black; height=20;width=80" id="BT_Delete" type="button" value="批量删除" name="BT_Delete" 
					onclick="return returnMethod()">				
													
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
					<td width="14%" align="center" height=22 background="${pageContext.request.contextPath }/images/tablehead.jpg">规格型号</td>
					<td align="center" width="8%" height=22 background="${pageContext.request.contextPath }/images/tablehead.jpg">设备状态</td>
					<td align="center" width="10%" height=22 background="${pageContext.request.contextPath }/images/tablehead.jpg">使用日期</td>
					<td align="center" width="10%" height=22 background="${pageContext.request.contextPath }/images/tablehead.jpg">检修周期</td>
					<td align="center" width="9%" height=22 background="${pageContext.request.contextPath }/images/tablehead.jpg">所属单位</td>
					
					
					<td width="6%" align="center" height=22 background="${pageContext.request.contextPath }/images/tablehead.jpg">编辑</td>
					
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
							<td style="HEIGHT:22px" align="center" width="15%">
								<s:property value="#device.DevType"/>
							</td>
							<td style="HEIGHT:22px" align="center" width="7%">
								<s:property value="#device.DevState"/>
							</td>
								<td style="HEIGHT:22px" align="center" width="15%">
								<s:date name="#device.UseDate" format="yyyy-MM-dd"/>
							</td>
							<td style="HEIGHT:22px" align="center" width="15%">
								<s:property value="#device.AdjustPeriod"/>
							</td>								
							<td style="HEIGHT:22px" align="center" width="8%">
								<s:property value="#device.jctID"/>
							</td>
							
							<td align="center" style="HEIGHT: 22px" align="center" width="10%">																	
							   <a href="#" onclick="openWindow('${pageContext.request.contextPath }/equapment/elecDeviceAction_edit.do?DevID=<s:property value="#device.DevID"/>','700','400');">
							   <img src="${pageContext.request.contextPath }/images/edit.gif" border="0" style="CURSOR:hand"></a>													
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
		             	<td width="8%" align="center"><u><a href="#" onClick="gotopage('elecDeviceAction_home.do','start')">首页&nbsp;&nbsp;|</a></u></td>
		             	<td width="10%" align="center"><u><a href="#" onClick="gotopage('elecDeviceAction_home.do','prev')">上一页&nbsp;&nbsp;&nbsp;|</a></u></td>
		             </s:else>
		             <s:if test="#request.page.lastPage">
					 	<td width="10%" align="center">下一页&nbsp;&nbsp;&nbsp;</td>
		             	<td width="8%" align="center">末页</td>
		             </s:if>
		             <s:else>
		             	<td width="10%" align="center"><u><a href="#" onClick="gotopage('elecDeviceAction_home.do','next')">下一页&nbsp;&nbsp;&nbsp;|</a></u></td>
		             	<td width="8%" align="center"><u><a href="#" onClick="gotopage('elecDeviceAction_home.do','end')">末页</a></u></td>
		             </s:else>
		             <td width="6%" align="center">第<s:property value="%{#request.page.pageNo}"/>页</td>
		             <td width="6%" align="center">共<s:property value="%{#request.page.sumPage}"/>页</td>
		             <td width="21%" align="right">至第<input size="1" type="text" name="goPage" >页
		
		
		             <u><a href="#" onClick="gotopage('elecDeviceAction_home.do','go')">确定</a></u></td>
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
	</table>
		</form>


