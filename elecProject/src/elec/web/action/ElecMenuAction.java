package elec.web.action;

import java.util.Hashtable;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import elec.domain.ElecCommonMsg;
import elec.domain.ElecPopedom;
import elec.domain.ElecRole;
import elec.domain.ElecUser;
import elec.service.IElecCommonMsgService;
import elec.service.IElecRoleService;
import elec.service.IElecUserService;
import elec.utils.LogonUtils;
import elec.utils.MD5keyBean;
import elec.utils.ValueStackUtils;
import elec.web.form.MenuForm;

@SuppressWarnings("serial")
@Controller("elecMenuAction")
@Scope(value="prototype")
public class ElecMenuAction extends BaseAction<MenuForm> {
	
	//注入运行监控的Service
	@Resource(name=IElecCommonMsgService.SERVICE_NAME)
	private IElecCommonMsgService elecCommonMsgService;

	private MenuForm menuForm = this.getModel();
	
	//注入用户的Service
	@Resource(name=IElecUserService.SERVICE_NAME)
	private IElecUserService elecUserService;
	
	//注入角色的Service
	@Resource(name=IElecRoleService.SERVICE_NAME)
	private IElecRoleService elecRoleService;
	
	//加载左侧树型菜单的集合
	private List<ElecPopedom> modelList;
	
	public List<ElecPopedom> getModelList() {
		return modelList;
	}
	public void setModelList(List<ElecPopedom> modelList) {
		this.modelList = modelList;
	}
	
	/**  
	* @Name: menuHome
	* @Description: 系统登录
	* @Return: 跳转到menu/home.jsp
	*/
	public String menuHome(){
		/**添加验证码*/
		//flag=true:表示验证码输入正确，flag=false：表示验证码输入有误
		boolean flag = LogonUtils.checkNumber(request);
		if(!flag){
			this.addFieldError("error", "验证码有误！");
			return "error";
		}
		/**一：验证登录名和密码是否正确*/
		//1：页面获取登录名和密码
		String name = menuForm.getName();
		String password = menuForm.getPassword();
		//2：使用登录名作为查询条件，查询对应的用户信息（ElecUser）
		ElecUser elecUser = elecUserService.findElecUserByLogonName(name);
		if(elecUser==null){
			//存放错误信息，Hibernate封装好的显示错误信息方法
			this.addFieldError("error", "登录名有误！");
			return "error";
		}
		if(StringUtils.isBlank(password)){
			this.addFieldError("error", "密码不能为空！");
			return "error";
		}
		else{
			//页面获取的密码和ElecUser对象中获取的密码比对，如果不一致，提示【密码错误】
			//对页面的密码进行加密
			MD5keyBean md5keyBean = new MD5keyBean();
			String md5Password = md5keyBean.getkeyBeanofStr(password);
			if(!md5Password.equals(elecUser.getLogonPwd())){
				this.addFieldError("error", "密码有误！");
				return "error";
			}
		}
		/**二：当前登录名具有角色才能登陆系统*/
		//获取当前用户具有的角色
		Set<ElecRole> elecRoles = elecUser.getElecRoles();
		//组织hashtable对象（线程安全的）
		Hashtable<String, String> ht = new Hashtable<String, String>();
		if(elecRoles!=null && elecRoles.size()>0){
			for(ElecRole elecRole:elecRoles){
				ht.put(elecRole.getRoleID(), elecRole.getRoleName());
			}
		}
		else{
			this.addFieldError("error", "登录名没有分配角色！");
			return "error";
		}
		/**三：当前登录名具有角色，但是角色没有分配权限，此时也不能登录系统*/
		//查询当前登录名具有的权限集合，返回字符串，将权限放置到String popedom类型的字符串中，存放的值的形式（aa@ab@ac@ad@ae）
		String popedom = elecUserService.findPopedomByLogonName(name);
		if(StringUtils.isBlank(popedom)){
			this.addFieldError("error", "登录名具有的角色没有分配权限！");
			return "error";
		}
		/**添加【记住我】，将登录信息放入cookie中*/
		LogonUtils.rememberMe(request,response,name,password);
		request.getSession().setAttribute("globle_user", elecUser);
		request.getSession().setAttribute("globle_role", ht);
		request.getSession().setAttribute("globle_popedom", popedom);
		
		return "menuHome";
	}

	/**系统首页标题*/
	public String title(){
		return "title";
	}
	
	/**系统首页左侧菜单*/
	public String left(){
		return "left";
	}
	
	/**系统首页显示框架改变*/
	public String change(){
		return "change";
	}
	
	/**系统首页功能区域显示*/
	public String loading(){
		return "loading";
	}
	
	/**重新登录*/
	public String logout(){
	 //指定某个Session清空是不够的    
		//request.getSession().removeAttribute(arg0);
		//清空所有的Session
		request.getSession().invalidate();
		return "logout";
	}
	
	/**  
	* @Name: alermStation
	* @Description: 显示站点运行情况
	* @Parameters: 无
	* @Return: 跳转到menu/alermStation.jsp
	*/
	public String alermStation(){
		//1：查询运行监控表，获取运行监控表中的数据，返回ElecCommonMsg对象
		ElecCommonMsg commonMsg = elecCommonMsgService.findElecCommonMsg();
		//2：将ElecCommonMsg对象压入到栈顶，用于表单回显，将所有数据显示到文本框中
		ValueStackUtils.setValueStack(commonMsg);
		return "alermStation";
	}
	
	/**  
	* @Name: alermDevice
	* @Description: 显示设备运行情况
	* @Parameters: 无
	* @Return: 跳转到menu/alermDevice.jsp
	*/
	public String alermDevice(){
		//1：查询运行监控表，获取运行监控表中的数据，返回ElecCommonMsg对象
		ElecCommonMsg commonMsg = elecCommonMsgService.findElecCommonMsg();
		//2：将ElecCommonMsg对象压入到栈顶，用于表单回显，将所有数据显示到文本框中
		ValueStackUtils.setValueStack(commonMsg);
		return "alermDevice";
	}
	
	/**  
	* @Name: showMenu
	* @Description: 查询左侧树型菜单的显示（ajax）
	* @Return: struts2的ajax返回json数据
	*/
	@SuppressWarnings("unchecked")
	public String showMenu(){
		//获取当前登录名所具有的功能权限（aa@ab@ac@ad@ae@bf@bg）
		String popedom = (String) request.getSession().getAttribute("globle_popedom");
		//获取当前登录名所具有的角色
		Hashtable<String, String> ht = (Hashtable<String, String>)request.getSession().getAttribute("globle_role");
		//获取当前登录名所具有的用户
		ElecUser elecUser = (ElecUser)request.getSession().getAttribute("globle_user");
		modelList = elecRoleService.findShowMenu(popedom);
		/**
		 * 遍历modelList，
		 * 点击用户管理：如果是系统管理员，可以查看用户列表
                *                        如果不是系统管理员，此时只能编辑自己的信息
		 */
		//改变用户管理的URL
		if(!ht.containsKey("1")){
			if(modelList!=null && modelList.size()>0){
				for(ElecPopedom elecPopedom:modelList){
					//用户管理
					if(elecPopedom.getMid().equals("an")){
						elecPopedom.setUrl("../system/elecUserAction_edit.do?userID="+elecUser.getUserID()+"&roleflag=1");
					}
				}
			}
		}
		/**
		 * 若不将list压入栈顶，可在struts.xml中加入注解<param name="root">action</param>
		 */
		//ValueStackUtils.setValueStack(list);
		return "showMenu";
	}
}
