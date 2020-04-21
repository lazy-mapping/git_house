package elec.web.action;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import org.apache.commons.lang.StringUtils;
import elec.domain.ElecSystemDDL;
import elec.domain.ElecUser;
import elec.service.IElecSystemDDLService;
import elec.service.IElecUserService;
import elec.utils.AnnotationLimit;
import elec.utils.DateUtils;
import elec.utils.ExcelFileGenerator;
import elec.utils.GenerateSqlFromExcel;
import elec.utils.MD5keyBean;
import elec.utils.ValueStackUtils;


@SuppressWarnings("serial")
@Controller("elecUserAction")
@Scope(value="prototype")
public class ElecUserAction extends BaseAction<ElecUser> {

	private ElecUser elecUser = this.getModel();
	
	@Resource(name=IElecUserService.SERVICE_NAME)
	private IElecUserService elecUserService;
	
	@Resource(name=IElecSystemDDLService.SERVICE_NAME)
	private IElecSystemDDLService elecSystemDDLService;
	
	/**  
	* @Name: home
	* @Description: 用户管理的首页显示
	* @Return: 跳转到system/userIndex.jsp
	*/
	@AnnotationLimit(mid = "an",pid = "am")
	public String home(){
		//1：从数据字典表中，查询所属单位的列表，返回List<ElecSystemDDL>
		List<ElecSystemDDL> jctList = elecSystemDDLService.findElecSystemDDLListByKeyword("所属单位");
		request.setAttribute("jctList", jctList);
		//2:指定查询条件，查询用户集合
		List<ElecUser> userList = elecUserService.findUserListByCondition(elecUser);
		request.setAttribute("userList", userList);		
		
		/**添加分页begin*/
		//用来判断跳转页面的标识
		String initpage = request.getParameter("initpage");
		if(initpage!=null && initpage.equals("1")){
			return "list";
		}
		/**添加分页end*/
		
		/* 故意抛出异常，测试异常拦截器
		 * try { ElecUser user = null; user.getBirthday(); } catch (Exception e) {
		 * e.printStackTrace(); throw new RuntimeException("对不起！报错了！"); }
		 */		
		return "home";
	}
	
	/**  
	* @Name: add
	* @Description: 跳转到用户新增页面
	* @Return: 跳转到system/userAdd.jsp
	*/
	@AnnotationLimit(mid="fb",pid="fa")
	public String add(){
		//1：查询性别，职位，所属单位，是否在职的下拉菜单
		this.initSystemDDL();
		return "add";
	}
	
	/**查询性别，职位，所属单位，是否在职的下拉菜单*/
	private void initSystemDDL() {
		List<ElecSystemDDL> jctList = elecSystemDDLService.findElecSystemDDLListByKeyword("所属单位");
		request.setAttribute("jctList", jctList);
		List<ElecSystemDDL> sexList = elecSystemDDLService.findElecSystemDDLListByKeyword("性别");
		request.setAttribute("sexList", sexList);
		List<ElecSystemDDL> isDutyList = elecSystemDDLService.findElecSystemDDLListByKeyword("是否在职");
		request.setAttribute("isDutyList", isDutyList);
		List<ElecSystemDDL> postList = elecSystemDDLService.findElecSystemDDLListByKeyword("职位");
		request.setAttribute("postList", postList);
	}

	/**  
	* @Name: findJctUnit
	* @Description: 页面中选择所属单位，使用所属单位查询对应所属单位的单位名称（ajax的二级联动）
	* @Return: ajax，使用struts的ajax转发
	*/
	@AnnotationLimit(mid = "fl",pid = "fa")
	public String findJctUnit(){
		//将数据字典的值转换成json数据
		//1：使用jquery的ajax获取到所属单位的中文名称（北京）
		String keyword = elecUser.getJctID();//北京
		//2：以选择的数据类型作为条件，查询对应数据类型的集合，返回List<ElecSystemDDL>
		List<ElecSystemDDL> list = elecSystemDDLService.findElecSystemDDLListByKeyword(keyword);
		//3：将list放置到栈顶，将list使用struts2的方式转换成json数据
		ValueStackUtils.setValueStack(list);
		return "findJctUnit";
	}
	
	/**  
	* @Name: checkUser
	* @Description: 使用登录名作为条件，查询登录名在数据库中是否出现重复
	*      String message = "";
	*        message=1:表示登录名不能为空
	*        message=2:表示登录名在数据库中已经存在，此时不能保存
	*        message=3:表示登录名在数据库表不存在，此时可以保存
	* @Return: ajax，使用struts的ajax转发
	*/
	@AnnotationLimit(mid = "ff",pid = "fa")
	public String checkUser(){
		//获取登录名
		String logonName = elecUser.getLogonName();
		//以登录名作为条件，查询用户表
		String message = elecUserService.checkUser(logonName);
		//将message放置到栈顶
		elecUser.setMessage(message);
		return "checkUser";
	}
	
	/**  
	* @Name: save
	* @Description: 保存用户信息
	* @Return: 
	*/
	@AnnotationLimit(mid = "fc",pid = "fa")
	public String save(){
		elecUserService.saveUser(elecUser);
		//获取roleflag的值
		String roleflag = elecUser.getRoleflag();
		//此时保存不存在打开窗口的页面
		if(roleflag!=null && roleflag.equals("1")){
			return edit();
		}
		return "close";
	}
	
	/**  
	* @Name: edit
	* @Description: 跳转到编辑页面
	* @Return: 跳转到system/userEdit.jsp
	*/
	@AnnotationLimit(mid = "fd",pid = "fa")
	public String edit(){
		//1：使用userID，查询对应用户的详细信息，返回ElecUser对象，放置到栈顶，用于struts2支持的表单回显
		ElecUser user = elecUserService.findElecUserByID(elecUser);
		//将新对象压入到栈顶之前，从新设置栈顶的值
		user.setViewflag(elecUser.getViewflag());
		user.setRoleflag(elecUser.getRoleflag());
		ValueStackUtils.setValueStack(user);
		//2：查询性别，职位，所属单位，是否在职的下拉菜单
		this.initSystemDDL();
		//3：从栈顶对象ElecUser，获取数据项的编号（ddlCode=2和keyword="所属单位"，获取数据项的值）
		String ddlName = elecSystemDDLService.findDdlNameByKeywordAndDdlCode("所属单位",user.getJctID());
		//4：遍历单位名称的集合(依赖所属单位的名称)，返回List<ElecSystemDDL>
		List<ElecSystemDDL> jctUnitList= elecSystemDDLService.findElecSystemDDLListByKeyword(ddlName);
		request.setAttribute("jctUnitList", jctUnitList);
		return "edit";
	}
	
	/**  
	* @Name: delete
	* @Description: 删除页面选择的用户信息
	* @Return: 重定向到system/userIndex.jsp
	*/
	@AnnotationLimit(mid = "fe",pid = "fa")
	public String delete(){
		elecUserService.deleteUserByIds(elecUser);
		/**重定向到某一页*/
		String pageNO = request.getParameter("pageNO");
		request.setAttribute("pageNO", pageNO);
		return "delete";
	}
	
	/**  
	* @Name: exportExcel
	* @Description: 导出excel
	* @Return: 使用struts2的方式
	*/
	@AnnotationLimit(mid = "fg",pid = "fa")
	public String exportExcel(){
		try {
			//组织excel的数据标题
			ArrayList<String> fieldName = elecUserService.findExcelFieldName();
			//组织excel的数据内容
			ArrayList<ArrayList<String>> fieldData = elecUserService.findExcleFieldData(elecUser);
			//导出excel
			ExcelFileGenerator excelFileGenerator = new ExcelFileGenerator(fieldName, fieldData);
			ByteArrayOutputStream os = new ByteArrayOutputStream();
			
			//重置输出流（可以不加，但是一定保证response里面没有其他数据，建议加上）
			response.reset();
			String filename = "用户报表（"+new SimpleDateFormat("yyyyMMddHHmmss").format(new Date())+"）.xls";
			filename = new String(filename.getBytes("gbk"),"iso-8859-1");
			request.setAttribute("filename", filename);
			//导出excel
			excelFileGenerator.expordExcel(os);
			//在栈顶对象中存放输入流对象
			byte [] buf = os.toByteArray();
			InputStream inputStream = new ByteArrayInputStream(buf);
			elecUser.setInputStream(inputStream);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		} 
		return "success";
	}
	
	/**  
	* @Name: importPage
	* @Description: 跳转到导入页面（类似文件上传）
	* @Return: 跳转到system/userImport.jsp
	*/
	@AnnotationLimit(mid = "fh",pid = "fa")
	public String importPage(){
		return "importPage";
	}
	
	/**  
	* @Name: importData
	* @Description: 从excel中读取数据内容，批量导入到数据库中
	* @Return: 跳转到system/userImport.jsp
	*/
	@AnnotationLimit(mid = "fi",pid = "fa")
	public String importData(){
		try {
			//获取导入的excel文件
			File file = elecUser.getFile();
			//从excel中读取数据，并将数据存放到ArrayList中
			GenerateSqlFromExcel excel = new GenerateSqlFromExcel();
			
			@SuppressWarnings("static-access")
			ArrayList<String[]> arrayList = excel.generateUserSql(file);
			//定义一个错误的集合List<String>，存放错误信息，如果excel文件存在错误，就将错误的集合的在页面中显示
			List<String> errorList = new ArrayList<String>();
			//组织PO需要保存的对象ElecUser，每个值进行转换的同时，再进行校验，如果校验出现问题，向errorList中存放记录
			List<ElecUser> list = this.userPoList(arrayList,errorList);
			//此时表示excel文件中存在错误
			if(errorList!=null && errorList.size()>0){
				request.setAttribute("errorList", errorList);
			}
			else{
				elecUserService.saveUserFromExcel(list);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
		return "importPage";
	}

	/**组织PO需要保存的对象ElecUser*/
	private List<ElecUser> userPoList(ArrayList<String[]> arrayList,List<String> errorList) {
		//封装结果集数据
		List<ElecUser> list = new ArrayList<ElecUser>();
		//遍历arrayList
		if(arrayList!=null && arrayList.size()>0){
			for(int i=0;i<arrayList.size();i++){
				String [] o = arrayList.get(i);
				//实例ElecUser对象，存放每一行的数据(登录名	密码   	用户姓名   	性别	  所属单位	联系地址  	是否在职   出生日期	职位)
				ElecUser elecUser = new ElecUser();
				/**登录名*/
				if(StringUtils.isNotBlank(o[0])){
					//校验登录名
					String message = elecUserService.checkUser(o[0].toString());
					if(message!=null && message.equals("2")){
						errorList.add("第"+(i+2)+"行，第"+(0+1)+"列，登录名在数据库中已经存在！");
					}
					else{
						elecUser.setLogonName(o[0].toString());
					}
				}
				else{
					errorList.add("第"+(i+2)+"行，第"+(0+1)+"列，登录名不能为空！");
				}
				/**密码*/
				//设置默认密码
				if(StringUtils.isBlank(o[1])){
					o[1] = "123";
				}
				//md5加密
				MD5keyBean bean = new MD5keyBean();
				String md5Password = bean.getkeyBeanofStr(o[1].toString());
				elecUser.setLogonPwd(md5Password);
				/**用户姓名*/
				if(StringUtils.isNotBlank(o[2])){
					elecUser.setUserName(o[2].toString());
				}
				/**性别*/
				if(StringUtils.isNotBlank(o[3])){
					//数据字典的转换，要求根据数据类型和数据项的值，获取数据项的编号
					String ddlCode = elecSystemDDLService.findDdlCodeByKeywordAndDdlName("性别", o[3].toString());
					if(StringUtils.isBlank(ddlCode)){
						errorList.add("第"+(i+2)+"行，第"+(3+1)+"列，性别在数据字典转换中出现异常！");
					}
					else{
						elecUser.setSexID(ddlCode);
					}
				}
				else{
					errorList.add("第"+(i+2)+"行，第"+(3+1)+"列，性别不能为空！");
				}
				/**所属单位*/
				if(StringUtils.isNotBlank(o[4])){
					//数据字典的转换，要求根据数据类型和数据项的值，获取数据项的编号
					String ddlCode = elecSystemDDLService.findDdlCodeByKeywordAndDdlName("所属单位", o[4].toString());
					if(StringUtils.isBlank(ddlCode)){
						errorList.add("第"+(i+2)+"行，第"+(4+1)+"列，所属单位在数据字典转换中出现异常！");
					}
					else{
						elecUser.setJctID(ddlCode);
					}
				}
				else{
					errorList.add("第"+(i+2)+"行，第"+(4+1)+"列，所属单位不能为空！");
				}
				/**联系地址*/
				if(StringUtils.isNotBlank(o[5])){
					elecUser.setAddress(o[5].toString());
				}
				/**是否在职*/
				if(StringUtils.isNotBlank(o[6])){
					//数据字典的转换，要求根据数据类型和数据项的值，获取数据项的编号
					String ddlCode = elecSystemDDLService.findDdlCodeByKeywordAndDdlName("是否在职", o[6].toString());
					if(StringUtils.isBlank(ddlCode)){
						errorList.add("第"+(i+2)+"行，第"+(6+1)+"列，是否在职在数据字典转换中出现异常！");
					}
					else{
						elecUser.setIsDuty(ddlCode);
					}
				}
				else{
					errorList.add("第"+(i+2)+"行，第"+(6+1)+"列，是否在职不能为空！");
				}
				/**出生日期*/
				if(StringUtils.isNotBlank(o[7])){
					Date date = DateUtils.stringToDate(o[7].toString());
					elecUser.setBirthday(date);
				}
				/**职位*/
				if(StringUtils.isNotBlank(o[8])){
					//数据字典的转换，要求根据数据类型和数据项的值，获取数据项的编号
					String ddlCode = elecSystemDDLService.findDdlCodeByKeywordAndDdlName("职位", o[8].toString());
					if(StringUtils.isBlank(ddlCode)){
						errorList.add("第"+(i+2)+"行，第"+(8+1)+"列，职位在数据字典转换中出现异常！");
					}
					else{
						elecUser.setPostID(ddlCode);
					}
				}
				else{
					errorList.add("第"+(i+2)+"行，第"+(8+1)+"列，职位不能为空！");
				}
				list.add(elecUser);
			}
		}
		return list;
	}
}
