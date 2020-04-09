package elec.web.action;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import elec.domain.ElecPopedom;
import elec.domain.ElecRole;
import elec.domain.ElecUser;
import elec.service.IElecRoleService;
import elec.utils.AnnotationLimit;

@SuppressWarnings("serial")
@Controller("elecRoleAction")
@Scope(value="prototype")
public class ElecRoleAction extends BaseAction<ElecPopedom> {

	private ElecPopedom elecPopedom = this.getModel();
	
	@Resource(name=IElecRoleService.SERVICE_NAME)
	private IElecRoleService elecRoleService;
	
	/**  
	* @Name: home
	* @Description: 角色管理的首页显示
	* @Return: 跳转到system/roleIndex.jsp
	*/
	@AnnotationLimit(mid = "ao",pid = "am")
	public String home(){
		//1：从Elec_Role表查询所有的角色，返回List<ElecRole>，遍历页面的下拉菜单
		List<ElecRole> roleList = elecRoleService.findRoleList();
		request.setAttribute("roleList", roleList);
		//2：从Elec_Popedom表查询所有的权限，返回List<ElecPopedom>，遍历在页面的权限分配上
		List<ElecPopedom> popedomList = elecRoleService.findPopedomList();
		request.setAttribute("popedomList", popedomList);
		return "home";
	}
	
	/**  
	* @Name: edit
	* @Description: 跳转到编辑页面（ajax）pub.js封装的代码
	* @Return: 跳转到system/roleEdit.jsp
	*/
	@AnnotationLimit(mid = "gb",pid = "ga")
	public String edit(){
		String roleID = elecPopedom.getRoleID();
		/**一：使用角色ID，获取角色和权限的关联*/
		List<ElecPopedom> popedomList = elecRoleService.findPopedomListByRoleID(roleID);
		request.setAttribute("popedomList", popedomList);
		/**二：使用角色ID，获取用户和角色的关联*/
		List<ElecUser> userList = elecRoleService.findUserListByRoleID(roleID);
		request.setAttribute("userList", userList);
		return "edit";
	}
	
	/**  
	* @Name: save
	* @Description: 保存角色和权限，保存用户和角色
	* @Return: 重定向到system/roleIndex.jsp
	*/
	@AnnotationLimit(mid = "gc",pid = "ga")
	public String save(){
		elecRoleService.saveRole(elecPopedom);
		return "save";
	}
	
}
