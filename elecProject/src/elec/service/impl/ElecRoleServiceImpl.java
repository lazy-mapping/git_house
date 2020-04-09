package elec.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import elec.dao.IElecPopedomDao;
import elec.dao.IElecRoleDao;
import elec.dao.IElecRolePopedomDao;
import elec.dao.IElecUserDao;
import elec.domain.ElecPopedom;
import elec.domain.ElecRole;
import elec.domain.ElecRolePopedom;
import elec.domain.ElecUser;
import elec.service.IElecRoleService;

@Service(IElecRoleService.SERVICE_NAME)
@Transactional(readOnly=true)
public class ElecRoleServiceImpl implements IElecRoleService {

	/**角色*/
	@Resource(name=IElecRoleDao.SERVICE_NAME)
	private IElecRoleDao elecRoleDao;
	
	/**权限*/
	@Resource(name=IElecPopedomDao.SERVICE_NAME)
	private IElecPopedomDao elecPopedomDao;
	
	/**角色权限*/
	@Resource(name=IElecRolePopedomDao.SERVICE_NAME)
	private IElecRolePopedomDao elecRolePopedomDao;
	
	/**用户*/
	@Resource(name=IElecUserDao.SERVICE_NAME)
	private IElecUserDao elecUserDao;
	
	/**  
	* @Name: findRoleList
	* @Description: 查询所有的角色信息集合
	* @Return: List<ElecRole>：角色列表
	*/
	public List<ElecRole> findRoleList() {
		Map<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("o.roleID", "asc");//这是按ID升序排序
		List<ElecRole> list = elecRoleDao.findCollectionByConditionNoPage("", null, orderby);
		return list;
	}
	
	/**  
	* @Name: findPopedomList
	* @Description: 查询所有的权限信息集合
	* @Return: List<ElecPopedom>：权限列表
	*/
	public List<ElecPopedom> findPopedomList() {
		Map<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("o.mid", "asc");//这是按ID升序排序
		List<ElecPopedom> list = elecPopedomDao.findCollectionByConditionNoPage("", null, orderby);
		return list;
	}
	
	/**  
	* @Name: findPopedomListByRoleID
	* @Description: 使用角色ID查询所有的功能权限，并进行匹配（当前角色具有哪些权限要被选中）
	* @Parameters: String roleID:角色ID
	* @Return: List<ElecPopedom>：权限集合列表
	*/
	public List<ElecPopedom> findPopedomListByRoleID(String roleID) {
		//1：遍历所有的权限，List<ElecPopedom> list 
		List<ElecPopedom> popedomList = this.findPopedomList();
		//2：获取角色id，查询角色权限关联表，获取当前角色所具有的功能权限List<ElecRolePopedom> popedomList
		String condition = " and o.roleID = ?";		
		Object [] params = {roleID};
		Map<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("o.mid", "asc");
		List<ElecRolePopedom> list = elecRolePopedomDao.findCollectionByConditionNoPage(condition, params, orderby);
		
		//使用字符串存放当前角色具有的功能权限
		//StringBuffer字符串变量，线程安全，速度比builder慢一点
		StringBuffer buffer = new StringBuffer("");
		if(list!=null && list.size()>0){
			for(ElecRolePopedom elecRolePopedom:list){
				buffer.append(elecRolePopedom.getMid()).append("@");//ab@ac@ad
			}
			buffer.deleteCharAt(buffer.length()-1);
		}
		//存放权限的字符串集合（格式：ab@ac@ad）
		String popedom = buffer.toString();
		/**
		 * 3:遍历List<ElecPopedom> list ，获取系统中每个功能权限，
        * 如果获取的每个权限和当前角色具有的权限匹配，设置flag=1
        * 如果获取的每个权限和当前角色具有的权限不匹配，设置flag=2
		 */
		if(popedomList!=null && popedomList.size()>0){
			for(ElecPopedom elecPopedom:popedomList){
				//获取的每个权限和当前角色具有的权限匹配，设置flag=1
				//contains字符串-->包含属性
				if(popedom.contains(elecPopedom.getMid())){
					elecPopedom.setFlag("1");
				}
				//获取的每个权限和当前角色具有的权限不匹配，设置flag=2
				else{
					elecPopedom.setFlag("2");
				}
			}
		}
		return popedomList;
	}
	
	/**  
	* @Name: findUserListByRoleID
	* @Description: 使用角色ID查询所有的用户，并进行匹配（当前角色具有哪些用户要被选中）
	* @Parameters: String roleID:角色ID
	* @Return: List<ElecUser>：用户集合列表
	*/
	public List<ElecUser> findUserListByRoleID(String roleID) {
		//1：遍历所有的用户，List<ElecUser> list 
		String condition = " and o.isDuty='1'";//要求人员在职
		Map<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("o.onDutyDate", "asc");
		List<ElecUser> userList = elecUserDao.findCollectionByConditionNoPage(condition, null, orderby);
		
		//2：获取角色id，查询角色表，获取角色信息ElecRole，获取当前角色下有多少用户，使用ElecRole.getElecUsers
		ElecRole elecRole = elecRoleDao.findObjectByID(roleID);
		//set集合（无序，唯一），与list（有序，不唯一）都继承Collection接口（Map与Collection并列存在）
		Set<ElecUser> set = elecRole.getElecUsers();
		//使用list<String>存放当前角色所具有的用户，list中存放userID
		List<String> list = new ArrayList<String>();
		if(set!=null && set.size()>0){
			for(ElecUser elecUser:set){
				list.add(elecUser.getUserID());
			}
		}
		/**遍历List<ElecUser> userList ，获取系统中每个用户，
		 * * 如果获取的每个用户和当前角色具有的用户匹配，设置flag=1
           * 如果获取的每个用户和当前角色具有的用户不匹配，设置flag=2
		 */
        if(userList!=null && userList.size()>0){
        	for(ElecUser elecUser:userList){
        		//获取的每个用户和当前角色具有的用户匹配，设置flag=1
        		if(list.contains(elecUser.getUserID())){
        			elecUser.setFlag("1");
        		}
        		//获取的每个用户和当前角色具有的用户不匹配，设置flag=2
        		else{
        			elecUser.setFlag("2");
        		}
        	}
        }
		return userList;
	}
	
	/**  
	* @Name: saveRole
	* @Description: 保存角色和权限，保存用户和角色
	* @Parameters: ElecPopedom：存放保存的参数
	* @Return: 无
	*/
	@Transactional(isolation=Isolation.DEFAULT,propagation=Propagation.REQUIRED,readOnly=false)
	public void saveRole(ElecPopedom elecPopedom) {
		//角色ID
		String roleID = elecPopedom.getRoleID();
		//选中的权限数组
		String [] selectoper = elecPopedom.getSelectoper();
		//选中的用户数组
		String [] selectuser = elecPopedom.getSelectuser();
		/**保存角色和权限（不用hibernate）*/
		this.saveRolePopedom(roleID,selectoper);
		/**保存用户和角色（用hibernate）*/
		this.saveUserRole(roleID,selectuser);
	}

	
     //对于保存多对多关系表，关系映射的中间表，先删除后插入
	/**保存角色和权限*/
	private void saveRolePopedom(String roleID, String[] selectoper) {
		//1：使用roleID作为条件，查询角色权限关联表，返回List<ElecRolePopedom> list，删除之前的数据
		String condition = " and o.roleID = ?";
		Object [] params = {roleID};
		List<ElecRolePopedom> list = elecRolePopedomDao.findCollectionByConditionNoPage(condition, params, null);
		//删除
		elecRolePopedomDao.deleteObjectByCollection(list);
		//2：遍历页面选择的功能权限的数组，组织PO对象，执行保存
		if(selectoper!=null && selectoper.length>0){
			for(String oper:selectoper){//分割字符aa_0   ab_aa
				String [] arrays = oper.split("_");//[0]:mid,[1]:pid
				ElecRolePopedom elecRolePopedom = new ElecRolePopedom();
				elecRolePopedom.setRoleID(roleID);
				elecRolePopedom.setMid(arrays[0]);
				elecRolePopedom.setPid(arrays[1]);
				elecRolePopedomDao.save(elecRolePopedom);
			}
		}
	}
	
	/**保存用户和角色（用hibernate）*/
	private void saveUserRole(String roleID, String[] selectuser) {
		//1：使用角色ID，查询角色表，获取ElecRole
		ElecRole elecRole = elecRoleDao.findObjectByID(roleID);
		//2：使用ElecRole获取当前角色具有哪些用户，Set<ElecUser> set = elecRole.getElecUsers();----（之前的数据）
		
		//3：遍历页面选中的selectuser，获取用户ID，将最新的用户ID放置到Set<ElecUser>----（之后的数据）
		Set<ElecUser> set = new HashSet<ElecUser>();
		if(selectuser!=null && selectuser.length>0){
			for(String userID:selectuser){
				ElecUser elecUser = new ElecUser();
				elecUser.setUserID(userID);
				set.add(elecUser);
			}
		}
		//4:重新建立ElecRole对象的集合属性elecUsers的关联关系
		elecRole.setElecUsers(set);//将新的关联关系替换旧的关联关系
		//当事务提交的时候，执行更新语句
	}

	/**  
	* @Name: findShowMenu
	* @Description: 使用当前登录人具有的权限，获取权限的集合
	* @Parameters: String：Session中存放的权限字符串（aa@ab@ac@ad@ae@bf@bg）
	* @Return: List<ElecPopedom>：权限集合
	*/
	public List<ElecPopedom> findShowMenu(String popedom) {
		//replace  字符串替换方法，用来组成sql
		String condition = " AND o.mid IN ('"+popedom.replace("@", "','")+"') AND isMenu = ?";
		Object [] params = {true};
		Map<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("o.mid", "asc");
		List<ElecPopedom> list = elecPopedomDao.findCollectionByConditionNoPage(condition, params, orderby);
		return list;
	}
	
	/**  
	* @Name: findRolePopedomByID
	* @Description: 使用角色ID，权限id，和父级权限的id作为联合主键的条件，查询角色权限关联表
	* @Parameters: String roleID：角色ID, String mid：权限ID, String pid：父级权限ID
	* @Return: List<ElecPopedom>：权限集合
	* 目的：防止你这边正在操作，管理员已经将数据库更改成最新的权限，如果不再次查询，那么会导致权限不对
	*/
	public boolean findRolePopedomByID(String roleID, String mid, String pid) {
		//组织查询条件
		String condition = "";
		List<Object> paramsList = new ArrayList<Object>();
		//角色ID
		if(StringUtils.isNotBlank(roleID)){
			condition += " and o.roleID = ?";
			paramsList.add(roleID);
		}
		//子权限名称
		if(StringUtils.isNotBlank(mid)){
			condition += " and o.mid = ?";
			paramsList.add(mid);
		}
		//父权限名称
		if(StringUtils.isNotBlank(pid)){
			condition += " and o.pid = ?";
			paramsList.add(pid);
		}
		Object [] params = paramsList.toArray();
		//查询对应的角色权限信息
		List<ElecRolePopedom> list = elecRolePopedomDao.findCollectionByConditionNoPage(condition, params, null);
		boolean flag = false;
		if(list!=null && list.size()>0){
			flag = true;
		}
		return flag;

	}
}
