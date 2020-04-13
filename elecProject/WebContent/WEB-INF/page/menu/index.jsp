<%@ page language="java"  pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<html>
<head>
<meta http-equiv="Content-Language" content="zh-cn">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<LINK href="${pageContext.request.contextPath}/css/custom.css" type="text/css" rel="stylesheet">
<script type='text/javascript' src='${pageContext.request.contextPath}/script/pub.js'></script>
<script type="text/javascript" src='${pageContext.request.contextPath}/script/validate.js'></script>
<SCRIPT type="text/javascript">
function ini(){
   document.all.name.focus();
}
function check(){
    var theForm = document.forms[0];
	if(Trim(theForm.name.value)==""){
			alert("请输入用户名");
			theForm.name.focus();
			return false;
	    }
	theForm.submit();
	return true; 
}
function checkNumberImage(){
	var imageNumber = document.getElementById("imageNumber");
	imageNumber.src = "${pageContext.request.contextPath}/image.jsp?timestamp="+new Date().getTime();
}
function checkFunction(){
	return check();
}
</SCRIPT>

<STYLE type=text/css>
BODY { margin:0px;background-image:url(${pageContext.request.contextPath}/images/mainBg0.jpg);background-size:1536px 760px;
}	
#button { border:none; height:46px; width:267px; background:url(img134.tif); background-color: #3598db;  color: rgb(255, 255, 255); border-bottom-right-radius:2px;border-bottom-left-radius:2px;}
.u-form .u-input {
    background: none;
    border: none;
    padding: 5px 0;
    color: #555;
    height: 36px;
    line-height: 36px;
    width: 50%;
}
.biaoti{font-size: 40px;
    font-weight: bold; 
    padding-top: 270px;
    padding-left: 270px;
}
</STYLE>

<title>电力设备检修管理系统</title>
</head>
<body onload="ini()">	
	<div class="main" >	  
	    <div class="biaoti">电 力 设 备 检 修 管 理 系 统 </div>
		<div class="aside-blur2 transparent"></div>
		<div class="aside">		
			<div class="loginArea normalForm">      
	        
				<div class="loginType loginTypeSingle">
	              <a logintype="normalForm">用户登录</a><s:fielderror/>
	            </div>
				
				<div class="formLogin">
					 <form action="${pageContext.request.contextPath}/system/elecMenuAction_menuHome.do" method="post" target="_top" class="j-login-form u-form">
					  
					<div class="inputArea j-input-area">
						<div class="common-area">						
							<div class="u-form-item u-form-item-1 forUid">
								<div class="wrapper">
									<label class="label"><img src="${pageContext.request.contextPath}/images/iconhead.png"></label>
									<div class="input-wrapper">
										<div class="sub-input-wrapper">
											<input class="u-input" type="text" name="name" autocomplete="off" id="uid" tabindex="1" placeholder="用户名" value="${requestScope.name }" maxlength="25"/>
										</div>
									</div>
								</div>
							</div>
	
							<div class="u-form-item u-form-item-1 forPassword">							
									<label class="label"><i class="iconfont iconlock"><img src="${pageContext.request.contextPath}/images/iconlock.png"></i></label>
									<div >
										<div class="sub-input-wrapper">										     
											<input class="u-input" type="password" name="password" id="password" placeholder="密　码" tabindex="2" value="${requestScope.password }" />                                           
										</div>
									</div>						
							</div>
					
						
							<div class="u-form-item u-form-item-1 forPassword">							
									<label class="label"><img src="${pageContext.request.contextPath}/images/iconcheck.png"></label>
									<div>
										<div class="sub-input-wrapper" >									
										<input class="u-input" type="text" name="checkNumber" id="" placeholder="验证码"  value="" /> 
										<img src="${pageContext.request.contextPath}/image.jsp"  name="imageNumber" id="imageNumber"  title="点击可更换图片"  onclick="checkNumberImage()"/>  
										</div>								                                           								
									</div>
							</div>
						</div>
	
	
						<div class="u-form-item u-form-item-2">    
							    <div class="u-input" >
	                                <input type="checkbox" name="remeberMe" id="remeberMe" value="yes" ${requestScope.checked }/>
	                                <i class="checkbox checkbox-checked"></i>
	                                <label for="saveUsername" >记住用户名</label>
	                            </div>	
						</div>
						
						<div class="u-form-item u-form-item-2">						
							<input type="button" id="button" class=btn_mouseout onmouseover="this.className='btn_mouseover'" onmouseout="this.className='btn_mouseout'" value="登   录" name="huifubtn" onclick="checkFunction()">
						</div>
						
					</form>
				</div>						
			</div>
		</div>		
	</div>
	
	<div class="copyright">
        <a  target="_blank">
            版权所有：王少彬 @Copyright  2020
        </a>
    </div>
</body>
</html>
