<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>

<html>
  <head>
    
    <title></title>
    
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
    <link href="${pageContext.request.contextPath }/css/Style.css" type="text/css" rel="stylesheet" />
<script language="javascript" src="${pageContext.request.contextPath }/script/function.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/My97DatePicker/WdatePicker.js"></script>
<script language="javascript" src="${pageContext.request.contextPath }/script/changePageBackUp.js"></script>
  
  </head>
  
  <body>
  <form name="Form2" method="post" language="javascript" action="#">
    <table width="95%" border="0" cellpadding="0" cellspacing="1" bordercolor="#f5fafe" bgcolor="#AFD1F3" id="table8">
            
              <tr>
                <td width="47" align="center" bgColor="#F5FAFE" class="ta_01">选择</td>
                <td width="147" bgColor="#F5FAFE" class="ta_01">设备名称</td>
                <td width="50" bgColor="#F5FAFE" class="ta_01">所属单位</td>
              </tr>
				
              <tr>
                <td width="47" align="center" bgColor="#F5FAFE" class="ta_01"><span style="CURSOR:hand; HEIGHT:22px">
                  <input type="checkbox" name="devId" value="" /></span></td>
                <td class="ta_01" bgColor="#F5FAFE">
               		 <s:property value="#record.elecDevice.DevName"/></td>
                <td class="ta_01" bgColor="#F5FAFE">
                	<s:property value="#record.elecDevice.jctID"/></td>
              </tr>
		
     </table>
  </form> 
  </body>
</html>
