package elec.service.impl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import elec.dao.IElecDeviceDao;
import elec.dao.IElecSystemDDLDao;
import elec.domain.ElecDevice;
import elec.domain.ElecUser;
import elec.service.IElecDeviceService;
import elec.utils.PageInfo;

@Service(IElecDeviceService.SERVICE_NAME)
@Transactional(readOnly = true)
public class ElecDeviceServiceImpl implements IElecDeviceService {

	@Resource(name=IElecDeviceDao.SERVICE_NAME)
	private IElecDeviceDao elecDeviceDao;
	
	@Resource(name=IElecSystemDDLDao.SERVICE_NAME)
	private IElecSystemDDLDao elecSystemDDLDao;
	
	/**  
	* @Name: findDeviceListByCondition
	* @Description: 指定条件查询设备信息
	* @Parameters: ElecDevice：传递参数
	* @Return: List<ElecDevice>：设备集合
	*/
	public List<ElecDevice> findDeviceListByCondition(ElecDevice elecDevice) {
		/**
		 * 2：获取页面传递的姓名、所属单位、入职时间判断输入的值是否为空
			  * 如果为null，查询所有
			  * 如果不为null，就指定对应的条件查询用户表
			  * 返回List<ElecUser>
		 */
		//组织查询条件
		String condition = "";
		List<Object> paramsList = new ArrayList<Object>();
		//获取设备名称		 
		if(StringUtils.isNotBlank(elecDevice.getDevName())){
			condition += " and o.DevName like ?";
			paramsList.add("%"+elecDevice.getDevName()+"%");
		}
		//所属单位
		if(StringUtils.isNotBlank(elecDevice.getJctID())){
			condition += " and o.jctID = ?";
			paramsList.add(elecDevice.getJctID());
		}
		
		//设备状态
		if(StringUtils.isNotBlank(elecDevice.getDevState())){
			condition += " and o.DevState = ?";
			paramsList.add(elecDevice.getDevState());
		}
		
		//使用时间开始时间
		if(elecDevice.getUseDateBegin()!=null){
			condition += " and o.UseDate >= ?";
			paramsList.add(elecDevice.getUseDateBegin());
		}
		//使用日期结束时间
		if(elecDevice.getUseDateEnd()!=null){
			condition += " and o.UseDate <= ?";
			paramsList.add(elecDevice.getUseDateEnd());
		}
		Object [] params = paramsList.toArray();
		//排序，按照使用时间的降序排列
		Map<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("o.UseDate", "desc");
		
		/**添加分页 begin*/
		PageInfo pageInfo = new PageInfo(ServletActionContext.getRequest());
		List<ElecDevice> list = elecDeviceDao.findCollectionByConditionWithPage(condition, params, orderby,pageInfo);
		ServletActionContext.getRequest().setAttribute("page",pageInfo.getPageBean() );
		/**添加分页 end*/
		
		/**3：涉及到数据字段字段的时候，要将数据项的编号转换成数据项值*/
		this.userPOListToVOList(list);
		return list;
	}
	
	/**涉及到数据字段字段的时候，要将数据项的编号转换成数据项值*/
	private void userPOListToVOList(List<ElecDevice> list) {
		if(list!=null && list.size()>0){
			for(ElecDevice elecDevice:list){
				//使用数据类型和数据项的编号获取数据项的值
				//所属单位
				elecDevice.setJctID(StringUtils.isNotBlank(elecDevice.getJctID())?elecSystemDDLDao.findDdlNameByKeywordAndDdlCode("所属单位",elecDevice.getJctID()):"");
				//设备状态
				elecDevice.setDevState(StringUtils.isNotBlank(elecDevice.getDevState())?elecSystemDDLDao.findDdlNameByKeywordAndDdlCode("设备状态",elecDevice.getDevState()):"");	
				//#数据字典项要定义成VARCHAR，可以用于数据字典的ddlCode和ddlName之间的转换
			}
		}
	}

	/**  
	* @Name: findElecUserByID
	* @Description: 使用主键ID，查询对应的用户信息
	* @Parameters: ElecUser：存放ID
	* @Return: ElecUser：查询用户信息
	*/
	public ElecDevice findElecDeviceByID(ElecDevice elecDevice) {

		//获取用户ID
		String devID = elecDevice.getDevID();
		ElecDevice device = elecDeviceDao.findObjectByID(devID);
		return device;
	}

	/**
	 * 保存，走原子操作
	 */
	@Transactional(isolation=Isolation.DEFAULT,propagation=Propagation.REQUIRED,readOnly=false)
	public void saveDevice(ElecDevice elecDevice) {
		elecDeviceDao.save(elecDevice);
	}
	
	/**
	 * 一次删除一个
	 */
	@Transactional(isolation=Isolation.DEFAULT,propagation=Propagation.REQUIRED,readOnly=false)
	public void deleteDeviceByIds(ElecDevice elecDevice) {
		String devID = elecDevice.getDevID();
		String [] ids = devID.split(", ");
		elecDeviceDao.deleteObjectByIds(ids);
	}

	//更新
	@Transactional(isolation=Isolation.DEFAULT,propagation=Propagation.REQUIRED,readOnly=false)
	public void updateDevice(ElecDevice elecDevice) {
		if(elecDevice.getDevID()!=null) {
			elecDeviceDao.update(elecDevice);
		}else {
			elecDeviceDao.save(elecDevice);
		}
	}

	
	
	
}
