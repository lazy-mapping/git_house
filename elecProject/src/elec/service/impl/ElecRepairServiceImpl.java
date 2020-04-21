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
import elec.dao.IElecRepairDao;
import elec.dao.IElecSystemDDLDao;
import elec.domain.ElecDevice;
import elec.domain.ElecOverhaulRecord;
import elec.service.IElecRepairService;
import elec.utils.PageInfo;

@Service(IElecRepairService.SERVICE_NAME)
@Transactional(readOnly = true)
public class ElecRepairServiceImpl implements IElecRepairService {

	@Resource(name=IElecRepairDao.SERVICE_NAME)
	private IElecRepairDao elecRepairDao;
	
	@Resource(name=IElecDeviceDao.SERVICE_NAME)
	private IElecDeviceDao elecDeviceDao;
	
	@Resource(name=IElecSystemDDLDao.SERVICE_NAME)
	private IElecSystemDDLDao elecSystemDDLDao;
	
	/**  
	* @Name: findRepairListByCondition
	* @Description: 指定条件查询检修记录信息
	* @Parameters: ElecOverhaulRecord：传递参数
	* @Return: List<ElecOverhaulRecord>：记录集合
	*/
	public List<ElecOverhaulRecord> findRepairListByCondition(ElecOverhaulRecord record) {
		/**
		 * 2：获取页面传递的设备名称、所属单位、检修时间、检修状态判断输入的值是否为空
			  * 如果为null，查询所有
			  * 如果不为null，就指定对应的条件查询记录表
			  * 返回List<ElecOverhaulRecord>
		 */
		//组织查询条件
		String condition = "";
		List<Object> paramsList = new ArrayList<Object>();
		//获取设备名称		 
		if(StringUtils.isNotBlank(record.getDevName())){
			condition += " and b.DevName like ?";
			paramsList.add("%"+record.getDevName()+"%");
		}
		//所属单位
		if(StringUtils.isNotBlank(record.getJctID())){
			condition += " and b.JctID = ?";
			paramsList.add(record.getJctID());
		}
		
		//检修状态
		if(StringUtils.isNotBlank(record.getRepairState())){
			condition += " and a.RepairState = ?";
			paramsList.add(record.getRepairState());
		}
		
		//检修开始时间
		if(record.getStartDateBegin()!=null){
			condition += " and a.StartDate >= ?";
			paramsList.add(record.getStartDateBegin());
		}
		//检修结束时间
		if(record.getStartDateEnd()!=null){
			condition += " and a.StartDate <= ?";
			paramsList.add(record.getStartDateEnd());
		}
		Object [] params = paramsList.toArray();
		//排序，按照使用时间的降序排列
		Map<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("a.StartDate", "desc");
		/**添加分页 begin*/
		PageInfo pageInfo = new PageInfo(ServletActionContext.getRequest());
		List<ElecOverhaulRecord> list = elecRepairDao.findRepairCollectionByConditionWithPage(condition, params, orderby,pageInfo);
		ServletActionContext.getRequest().setAttribute("page",pageInfo.getPageBean() );
		/**添加分页 end*/
		
		/**3：涉及到数据字段字段的时候，要将数据项的编号转换成数据项值*/
		this.POListToVOList(list);
		return list;
	}
	
	/**涉及到数据字段字段的时候，要将数据项的编号转换成数据项值*/
	private void POListToVOList(List<ElecOverhaulRecord> list) {
		if(list!=null && list.size()>0){
			for(ElecOverhaulRecord record:list){
				//使用数据类型和数据项的编号获取数据项的值
				//所属单位
				record.setJctID(StringUtils.isNotBlank(record.getJctID())?elecSystemDDLDao.findDdlNameByKeywordAndDdlCode("所属单位",record.getJctID()):"");
				
				//使用单位
				record.setJctUnitID(StringUtils.isNotBlank(record.getJctUnitID())?elecSystemDDLDao.findDdlNameByKeywordAndDdlCode(record.getJctID(), record.getJctUnitID()):"");
				
				//检修状态
				record.setRepairState(StringUtils.isNotBlank(record.getRepairState())?elecSystemDDLDao.findDdlNameByKeywordAndDdlCode("检修状态",record.getRepairState()):"");	
				
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
	public ElecOverhaulRecord findElecRepairByID(ElecOverhaulRecord record) {
		//获取ID
		String seqID = record.getSeqID();
		String devID = record.getDevID();
		//ElecOverhaulRecord records = elecRepairDao.findrecordByID(seqID);
		ElecOverhaulRecord records = elecRepairDao.findObjectByID(seqID);
		records.setElecDevice(elecDeviceDao.findObjectByID(devID));
		
		return records;
	}

	
	/**
	 * 一次删除一个
	 */
	@Transactional(isolation=Isolation.DEFAULT,propagation=Propagation.REQUIRED,readOnly=false)
	public void deleteRepairByIds(ElecOverhaulRecord record) {
		String seqID = record.getSeqID();
		String [] ids = seqID.split(", ");
		
		elecRepairDao.deleteObjectByIds(ids);
	}

	//更新
	@Transactional(isolation=Isolation.DEFAULT,propagation=Propagation.REQUIRED,readOnly=false)
	public void updateRepair(ElecOverhaulRecord record) {
		if(record.getSeqID()!=null) {
			//elecRepairDao.updateRepair(record);
			elecRepairDao.update(record);
			elecDeviceDao.update(record.getElecDevice());
		}else {
			elecRepairDao.save(record);
			elecDeviceDao.save(record.getElecDevice());
		}
	}





	
	
	
}
