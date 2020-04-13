package elec.service.impl;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import elec.utils.PageInfo;
import elec.dao.IElecExportFieldsDao;
import elec.dao.IElecSystemDDLDao;
import elec.dao.IElecUserDao;
import elec.domain.ElecExportFields;
import elec.domain.ElecUser;
import elec.service.IElecUserService;
import elec.utils.MD5keyBean;
import elec.utils.StringToListUtils;

@Service(IElecUserService.SERVICE_NAME)
@Transactional(readOnly=true)
public class ElecUserServiceImpl implements IElecUserService {

	@Resource(name=IElecUserDao.SERVICE_NAME)
	private IElecUserDao elecUserDao;
	
	@Resource(name=IElecSystemDDLDao.SERVICE_NAME)
	private IElecSystemDDLDao elecSystemDDLDao;
	
	@Resource(name=IElecExportFieldsDao.SERVICE_NAME)
	private IElecExportFieldsDao elecExportFieldsDao;
	
	/**  
	* @Name: findUserListByCondition
	* @Description: 指定条件查询用户信息
	* @Parameters: ElecUser：传递参数
	* @Return: List<ElecUser>：用户集合
	*/
	public List<ElecUser> findUserListByCondition(ElecUser elecUser) {
		/**
		 * 2：获取页面传递的姓名、所属单位、入职时间判断输入的值是否为空
			  * 如果为null，查询所有
			  * 如果不为null，就指定对应的条件查询用户表
			  * 返回List<ElecUser>
		 */
		//组织查询条件
		String condition = "";
		List<Object> paramsList = new ArrayList<Object>();
		//获取用户姓名
		String userName = elecUser.getUserName();
		if(StringUtils.isNotBlank(userName)){
			condition += " and o.userName like ?";
			paramsList.add("%"+userName+"%");
		}
		//所属单位
		if(StringUtils.isNotBlank(elecUser.getJctID())){
			condition += " and o.jctID = ?";
			paramsList.add(elecUser.getJctID());
		}
		//入职日期开始时间
		if(elecUser.getOnDutyDateBegin()!=null){
			condition += " and o.onDutyDate >= ?";
			paramsList.add(elecUser.getOnDutyDateBegin());
		}
		//入职日期结束时间
		if(elecUser.getOnDutyDateEnd()!=null){
			condition += " and o.onDutyDate <= ?";
			paramsList.add(elecUser.getOnDutyDateEnd());
		}
		Object [] params = paramsList.toArray();
		//排序，按照入职时间的降序排列
		Map<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("o.onDutyDate", "desc");
		
		/**添加分页 begin*/
		PageInfo pageInfo = new PageInfo(ServletActionContext.getRequest());
		List<ElecUser> list = elecUserDao.findCollectionByConditionWithPage(condition, params, orderby,pageInfo);
		ServletActionContext.getRequest().setAttribute("page",pageInfo.getPageBean() );
		/**添加分页 end*/
		
		/**3：涉及到数据字段字段的时候，要将数据项的编号转换成数据项值*/
		this.userPOListToVOList(list);
		return list;
	}

	/**涉及到数据字段字段的时候，要将数据项的编号转换成数据项值*/
	private void userPOListToVOList(List<ElecUser> list) {
		if(list!=null && list.size()>0){
			for(ElecUser elecUser:list){
				//使用数据类型和数据项的编号获取数据项的值
				//性别
				elecUser.setSexID(StringUtils.isNotBlank(elecUser.getSexID())?elecSystemDDLDao.findDdlNameByKeywordAndDdlCode("性别",elecUser.getSexID()):"");
				//所属单位
				elecUser.setJctID(StringUtils.isNotBlank(elecUser.getJctID())?elecSystemDDLDao.findDdlNameByKeywordAndDdlCode("所属单位",elecUser.getJctID()):"");
				//是否在职
				elecUser.setIsDuty(StringUtils.isNotBlank(elecUser.getIsDuty())?elecSystemDDLDao.findDdlNameByKeywordAndDdlCode("是否在职",elecUser.getIsDuty()):"");
				//职位
				elecUser.setPostID(StringUtils.isNotBlank(elecUser.getPostID())?elecSystemDDLDao.findDdlNameByKeywordAndDdlCode("职位",elecUser.getPostID()):"");
				//#数据字典项要定义成VARCHAR，可以用于数据字典的ddlCode和ddlName之间的转换
			}
		}
	}
	
	/**  
	* @Name: checkUser
	* @Description: 使用登录名作为条件，查询登录名在数据库中是否出现重复
	* @Return: String message = "";
	*        message=1:表示登录名不能为空
	*        message=2:表示登录名在数据库中已经存在，此时不能保存
	*        message=3:表示登录名在数据库表不存在，此时可以保存
	*/
	public String checkUser(String logonName) {
		String message = "";
		if(StringUtils.isNotBlank(logonName)){
			String condition = " and o.logonName = ?";
			Object [] params = {logonName};
			/**
			 * 2：以登录名作为条件，查询用户表，返回List<ElecUser>
			 *   如果登录名为空，返回message=1
				  如果list不为空，说明数据库中已经存在值，返回message=2
				  如果list为空，说明数据库中没有值，返回message=3
			 */
			List<ElecUser> list = elecUserDao.findCollectionByConditionNoPage(condition, params, null);
			if(list!=null && list.size()>0){
				message = "2";
			}
			else{
				message = "3";
			}
		}
		else{
			message = "1";
		}
		return message;
	}
	
	/**  
	* @Name: saveUser
	* @Description: 保存用户
	* @Parameters: ElecUser：存放保存对象
	* @Return: 无
	*/
	@Transactional(isolation=Isolation.DEFAULT,propagation=Propagation.REQUIRED,readOnly=false)
	public void saveUser(ElecUser elecUser) {
		//获取用户ID
		String userID = elecUser.getUserID();
		//添加md5的密码加密
		this.md5Password(elecUser);
		//1：如果userID==null，直接获取保存的PO对象，执行save()
		if(StringUtils.isBlank(userID)){
			elecUserDao.save(elecUser);
		}
		//2：如果userID!=null，获取更新的PO对象，执行update()
		else{
			elecUserDao.update(elecUser);
		}
	}
	
	/**添加md5的密码加密，对登录名的密码进行安全的控制*/
	private void md5Password(ElecUser elecUser) {
		//获取页面输入的密码
		String logonPwd = elecUser.getLogonPwd();
		//加密后的密码
		String md5LogonPwd = "";
		//如果密码没有填写，给出初始密码123
		if(StringUtils.isBlank(logonPwd)){
			logonPwd = "123";
		}
		//判断是否对密码进行了修改，获取password
		String password = elecUser.getPassword();
		//表示没有修改密码，此时不需要进行加密
		if(password!=null && password.equals(logonPwd)){
			md5LogonPwd = logonPwd;
		}
		else{
			//md5密码加密
			MD5keyBean md5keyBean = new MD5keyBean();
			md5LogonPwd = md5keyBean.getkeyBeanofStr(logonPwd);
		}
		//最后讲加密后的密码放置到ElecUser中
		elecUser.setLogonPwd(md5LogonPwd);
	}

	/**  
	* @Name: findElecUserByID
	* @Description: 使用主键ID，查询对应的用户信息
	* @Parameters: ElecUser：存放ID
	* @Return: ElecUser：查询用户信息
	*/
	public ElecUser findElecUserByID(ElecUser elecUser) {
		//获取用户ID
		String userID = elecUser.getUserID();
		ElecUser user = elecUserDao.findObjectByID(userID);
		return user;
	}
	
	/**  
	* @Name: deleteUserByIds
	* @Description: 页面选择用户ID执行删除（1和或者多个）
	* @Parameters: ElecUser：存放ID的字符串，如果多个值，中间使用”, “
	* @Return: 无
	*/
	@Transactional(isolation=Isolation.DEFAULT,propagation=Propagation.REQUIRED,readOnly=false)
	public void deleteUserByIds(ElecUser elecUser) {
		//获取ID
		String userID = elecUser.getUserID();
		String [] ids = userID.split(", ");
		elecUserDao.deleteObjectByIds(ids);
	}
	
	/**  
	* @Name: findElecUserByLogonName
	* @Description: 使用登录名作为条件，查询当前登录名所对应的用户
	* @Parameters: String：登录名
	* @Return: ElecUser：存放用户信息
	*/
	@Transactional(isolation=Isolation.DEFAULT,propagation=Propagation.REQUIRED,readOnly=false)
	public ElecUser findElecUserByLogonName(String name) {
		//组织查询条件
		String condition = "";
		List<Object> paramsList = new ArrayList<Object>();
		if(StringUtils.isNotBlank(name)){
			condition += " and o.logonName=?";
			paramsList.add(name);
		}
		Object [] params = paramsList.toArray();
		//查询
		List<ElecUser> list = elecUserDao.findCollectionByConditionNoPage(condition, params, null);
		ElecUser elecUser = null;
		if(list!=null && list.size()>0){
			elecUser = list.get(0);
			//方案一：
//			elecUser.getElecRoles().size();
			//方法二：
			Hibernate.initialize(elecUser.getElecRoles());
		}
		return elecUser;
	}
	
	/**  
	* @Name: findPopedomByLogonName
	* @Description: 使用登录名作为条件，查询当前登录名具有的权限
	* @Parameters: String：登录名
	* @Return: String：权限的字符串集合（@分割）
	*/
	public String findPopedomByLogonName(String name) {
		List<Object> list = elecUserDao.findPopedomByLogonName(name);
		StringBuffer buffer = new StringBuffer();
		if(list!=null && list.size()>0){
			for(Object o:list){
				buffer.append(o.toString()).append("@");
			}
			//删除最后一个@
			buffer.deleteCharAt(buffer.length()-1);
		}
		return buffer.toString();
	}
	
	/**  
	* @Name: saveUserFromExcel
	* @Description: 从excel中读取数据，批量保存到数据库表汇总
	* @Parameters: List<ElecUser>：PO对象的集合
	*/
	@Transactional(isolation=Isolation.DEFAULT,propagation=Propagation.REQUIRED,readOnly=false)
	public void saveUserFromExcel(List<ElecUser> list) {
		if(list!=null && list.size()>0){
			for(ElecUser elecUser:list){
				elecUserDao.save(elecUser);
			}
		}
		
	}
	
	/**  
	* @Name: findExcelFieldName
	* @Description: 组织动态导出excel的标题数据
	* @Return: ArrayList<String>：存放excel的数据标题
	* 		ArrayList<String> fieldName：
	*/
	public ArrayList<String> findExcelFieldName() {
		//查询导出设置表，获取导出设置中存放的导出excel的字段的中文名称
		ElecExportFields elecExportFields = elecExportFieldsDao.findObjectByID("5-1");
		ArrayList<String> zName = (ArrayList<String>)StringToListUtils.stringToList(elecExportFields.getExpNameList(), "#");
		return zName;
	}

	/**  
	* @Name: findExcleFieldData
	* @Description: 组织动态导出excel的数据内容
	* @Return: ArrayList<ArrayList<String>>：存放excel的数据内容：
	* 		ArrayList<ArrayList<String>> fieldData//存放所有行的数据
	*/
	public ArrayList<ArrayList<String>> findExcleFieldData(ElecUser elecUser) {
		//最终的结果集
		ArrayList<ArrayList<String>> fieldData = new ArrayList<ArrayList<String>>();
		
		//查询导出设置表，获取用户5-1标识对应的导出字段的英文名
		ElecExportFields elecExportFields = elecExportFieldsDao.findObjectByID("5-1");
		String eName = elecExportFields.getExpFieldName();//logonName#userName
		//将#号替换成逗号
		String selectCondition = eName.replace("#", ",");
		
		//获取导出设置的中文字段，并转换成List集合
		String zName = elecExportFields.getExpNameList();
		List<String> zNameList = StringToListUtils.stringToList(zName, "#");
		
		//组织查询条件
		String condition = "";
		List<Object> paramsList = new ArrayList<Object>();
		//获取用户姓名
		String userName = elecUser.getUserName();
		try {
			userName = URLDecoder.decode(userName, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		if(StringUtils.isNotBlank(userName)){
			condition += " and o.userName like ?";
			paramsList.add("%"+userName+"%");
		}
		//所属单位
		if(StringUtils.isNotBlank(elecUser.getJctID())){
			condition += " and o.jctID = ?";
			paramsList.add(elecUser.getJctID());
		}
		//入职日期开始时间
		if(elecUser.getOnDutyDateBegin()!=null){
			condition += " and o.onDutyDate >= ?";
			paramsList.add(elecUser.getOnDutyDateBegin());
		}
		//入职日期结束时间
		if(elecUser.getOnDutyDateEnd()!=null){
			condition += " and o.onDutyDate <= ?";
			paramsList.add(elecUser.getOnDutyDateEnd());
		}
		Object [] params = paramsList.toArray();
		//排序，按照入职时间的降序排列
		Map<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("o.onDutyDate", "desc");
		
		//查询对应的结果(返回所有的结果)
		List<?> list = elecUserDao.findCollectionByConditionNoPageWithSelectCondition(condition, params, orderby,selectCondition);
		//遍历所有的结果
		if(list!=null && list.size()>0){
			for(int i=0;i<list.size();i++){
				//存放的就数据每1行的记录
				Object [] arrays = null;
				//判断返回结果是Object数组还是Object对象呢？
				//此时返回Object数组对象
				if(selectCondition.contains(",")){
					arrays = (Object[]) list.get(i);
				}
				//否则就返回Object对象
				else{
					arrays = new Object[1];
					arrays[0] = list.get(i);
				}
				//遍历每一行的记录，将每一行的记录放置到ArrayList<String>
				ArrayList<String> data = new ArrayList<String>();
				if(arrays!=null && arrays.length>0){
					for(int j=0;j<arrays.length;j++){
						Object o = arrays[j];
						//遇到读取的数据字典的值需要转换
						if(zNameList!=null && zNameList.get(j).equals("性别") || zNameList.get(j).equals("所属单位") || zNameList.get(j).equals("职位") || zNameList.get(j).equals("是否在职")){
							//要求使用数据类型和数据项的编号，获取数据项的值
							data.add(o!=null?elecSystemDDLDao.findDdlNameByKeywordAndDdlCode(zNameList.get(j), o.toString()):"");
						}
						else{
							//存放每1行的数据
							data.add(o!=null?o.toString():"");
						}
					}
				}
				//存放所有行的数据
				fieldData.add(data);
			}
		}
		return fieldData;
	}
}
