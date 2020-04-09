function Pub(){ } 

Pub.submit=function(formindex) {
			
  				document.forms[formindex].submit();
  				
}

Pub.formsubmitAction=function(formindex,action) {
            
			document.forms[formindex].action=action;
  				document.forms[formindex].submit();
  				
}


Pub.submitform=function(formname){
   

   var theform =document.forms[formname];
   theform.submit();
}

Pub.reset=function(formindex){
				document.forms[formindex].reset();
}


////////////////////////////////////////////////////////////////////////////////
Pub.checkAll=function(parent, itemName)
{
  
  var parentbox = document.getElementsByName(itemName);
  
  for (var i=0; i<parentbox.length; i++){
  
  
   parentbox[i].checked = parent.checked;
   
   }
}
Pub.checkItem=function (child, allName)
{
  var all = document.getElementsByName(allName)[0];
  if(!child.checked) all.checked = false;
  else
  {
    var childbox = document.getElementsByName(child.name);
    for (var i=0; i<childbox.length; i++)
     if(!childbox[i].checked) return;
    all.checked = true;
  }
}

Pub.getAllCheckItemValue=function(storename,removevalue){


var obj_all = document.getElementsByTagName("INPUT");
var all_value="";
for(i=0;i<obj_all.length;i++){
if(obj_all[i].type=="checkbox")
   if(obj_all[i].checked ){
      if(obj_all[i].value!=removevalue)
      all_value=all_value+obj_all[i].value+",";
   }
      
   
}
var obj = document.getElementById(storename);
obj.value=all_value;

}

Pub.getAllCheckItemValueWithRemove=function(storename,removevalue){


var obj_all = document.getElementsByTagName("INPUT");
var all_value="";
for(i=0;i<obj_all.length;i++){
if(obj_all[i].type=="checkbox")
   if(obj_all[i].checked ){
      if(obj_all[i].value.indexOf("NULL")==-1){ 
      if(obj_all[i].value!=removevalue)
      all_value=all_value+obj_all[i].value+",";
   }
      }
   
}
var obj = document.getElementById(storename);
obj.value=all_value;

}

///////////////////////////////////////////////////////////////////////////////////////////////////////
Pub.copyRole=function(paramname,action){

var obj_all = document.getElementsByTagName("INPUT");
var value="";
for(i=0;i<obj_all.length;i++){
if(obj_all[i].type=="radio")
   if(obj_all[i].checked ){
       
        value = obj_all[i].value;
      }
   
}

 if(value==""){
 return false;}
  document.forms[0].action=action+"?"+paramname+"="+value;
  document.forms[0].submit();

}
///////////////////////////////////////////////////////////////////////////////////////////////////////

////////////////////////////////////////////////////////////////////////////////////////////////////////////////


/**创建ajax引擎对象*/
Pub.newXMLHttpRequest=function newXMLHttpRequest() {
  var xmlreq = false;
  if (window.XMLHttpRequest) {
    xmlreq = new XMLHttpRequest();
  }
  else if (window.ActiveXObject) {
	
		  try {
		    xmlreq = new ActiveXObject("Msxml2.XMLHTTP");
		  }
		  catch (e1) {
			      try {	      
			        xmlreq = new ActiveXObject("Microsoft.XMLHTTP");
			      } 
			      catch (e2) {
			    	  alert(e2);
		          }
          }
  }
  return xmlreq;
}


/**   处理Ajax的返回结果
 * 传递的参数req, domId,Pub.handleResponse
 * @param req：ajax引擎对象
 * @param eleid：表单Form2的名称
 * @param responseXmlHandler，表示Pub.handleResponse函数
 * @returns {Function}
 */
Pub.getReadyStateHandler =function getReadyStateHandler(req, eleid,responseXmlHandler) {
  
  return function () {
    /**
     * 1表示open方法调用，send方法没有调用
     * 2表示open方法调用，send方法被调用
     * 3表示服务器正在接收信息和响应数据
     * 4表示连接成功，只有4才能获取服务器返回给客户端的值
     */
    if (req.readyState == 4) {
       /**
        * 200服务器响应没有异常，只有200才能获取服务器返回给客户端的值
        */
    	if (req.status == 200) {
	        /**
	         * 服务器响应客户端的值：存放到2个对象中
	         * 1：req.responseText（包括文本、字符串、json数据）
	         * 2：req.responseXML（包括XML数据格式：例如<response><name>张三</name></response>），页面中使用document.getElementByTagName("name")
	         */
    		responseXmlHandler(req.responseText,eleid); 
         } else {
              alert("HTTP error: "+req.status);
        
              return false;
              }
        }
  }
}


Pub.getStatisticReadyStateHandler =function (req, eleid,responseXmlHandler,noteId) {
  alert(req.readyState);
  return function () {
    if (req.readyState == 4) {
       
      if (req.status == 200) {
          
        responseXmlHandler(req.responseText,eleid);
 
      } else {
        
        alert("HTTP error: "+req.status);
        return false;
      }
    }else{
   
     var ele11 =document.getElementById(noteId);
    ele11.innerHTML="Loading Data";
    }
  }
}

/**
 * 
 * @param data:表示服务器响应给客户端的结果，就是req.responseText;
 * @param eleid:表示表单Form2的名称
 */
Pub.handleResponse= function handleResponse(data,eleid){
	  //获取表单form2的名称的对象
      var ele =document.getElementById(eleid);
      //将服务器返回的结果，放置到表单Form2中（替换表单Form2中的内容）
      ele.innerHTML = data;
    
}


Pub.submitAction=function(domId,action){

  var req = Pub.newXMLHttpRequest();
  
  var handlerFunction = Pub.getReadyStateHandler(req, domId,Pub.handleResponse);
  req.onreadystatechange = handlerFunction;
  req.open("POST", action, false);
  req.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");

   
  req.send(null);

}


Pub.submitActionWithNote=function(domId,action,noteId){


  var req = Pub.newXMLHttpRequest();
  
  var handlerFunction = Pub.getStatisticReadyStateHandler(req, domId,Pub.handleResponse,noteId);
  req.onreadystatechange = handlerFunction;
  req.open("POST", action, true);
  req.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");

  req.send(null);

}



/** 组织form1中元素的对象，作为传递给服务器的参数
 * sForm：表单Form1的名称
 * 返回值：strDiv的数据格式是，a=8&b=9
 */
Pub.getParams2Str=function getParams2Str(sForm){
	 var strDiv="";
	 
	 try {
	    var objForm=document.forms[sForm];
		if (!objForm)
		    return strDiv;
		
		var elt,sName,sValue;
		
		for (var fld = 0; fld < objForm.elements.length; fld++) {
	       elt = objForm.elements[fld];    
	       sName=elt.name;
	       sValue=""+elt.value;
	       
	       if(fld==objForm.elements.length-1)
	          strDiv=strDiv + sName+"="+sValue+"";
	       else   
	          strDiv=strDiv + sName+"="+sValue+"&";
	    }
	  }catch (ex) {
		   return strDiv;
	       }
   return strDiv;
}

function doContents(){
    if (xmlhttp.readyState < 4){
        alert("a");
    }
    else if (xmlhttp.readyState == 4 && xmlhttp.status == 200){
        alert("b");
    }
}

/**
 * domId:表单Form2的名称
 * action:URL地址
 * sForm:表单Form1的名称
 */
Pub.submitActionWithForm=function(domId,action,sForm){

  /** dom对象ajax 第一步：创建ajax引擎，即XMLHttpRequest */
  var req = Pub.newXMLHttpRequest();
  /**
   * 第二步：使用ajax引擎调用onreadystatechange，表示事件处理函数，
   * 用来监听服务器端（Action）与客户端（浏览器）之间的连接状态
   */
  var handlerFunction = Pub.getReadyStateHandler(req, domId,Pub.handleResponse);
  req.onreadystatechange = handlerFunction;
  /**第三步：使用ajax引擎调用open方法，打开一个连接（此时与服务器连接）*/
  req.open("POST", action, true);
  /**如果请求的方式是POST的话，需要添加请求的头，此时可以使用req.send(str);方式发送数据，
   * 如果不添加此行代码send方法将无法传递参数到服务器
   */
  req.setRequestHeader("Content-Type", "application/x-www-form-urlencoded"); 
  /**第四步：使用ajax引擎调用send方法，向服务器发送数据(发送表单Form1中所有的元素组织参数进行传递)*/
  var str = Pub.getParams2Str(sForm); 
  req.send(str);//传递的数据格式a=8&b=9
}

Pub.submitActionWithFormGet=function(domId,action,sForm){
	  var req = Pub.newXMLHttpRequest();
	  var handlerFunction = Pub.getReadyStateHandler(req, domId,Pub.handleResponse);
	  req.onreadystatechange = handlerFunction;
	  
	  req.open("Get", action, true);
	  req.send(null);
}

