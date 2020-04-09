package elec.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import elec.dao.IElecCommonMsgDao;
import elec.domain.ElecCommonMsg;
import elec.service.IElecCommonMsgService;

@Service(IElecCommonMsgService.SERVICE_NAME)
@Transactional(readOnly=true)
public class ElecCommonMsgServiceImpl implements IElecCommonMsgService {

	@Resource(name=IElecCommonMsgDao.SERVICE_NAME)
	private IElecCommonMsgDao elecCommonMsgDao;
	
	/**  
	* @Name: findElecCommonMsg
	* @Description: 获取运行监控中的数据
	* @Return: ElecCommonMsg：封装数据对象
	*/
	public ElecCommonMsg findElecCommonMsg() {
		List<ElecCommonMsg> list = elecCommonMsgDao.findCollectionByConditionNoPage("", null, null);
		ElecCommonMsg commonMsg = null;
		if(list!=null && list.size()>0){
			commonMsg = list.get(0);
		}
		return commonMsg;
	}
	
	/**  
	* @Name: saveElecCommonMsg
	* @Description: 保存运行监控
	* @Parameters: ElecCommonMsg：封装保存的参数
	* @Return: 无
	* 事务：隔离级别默认，传播行为required，只读flase
	*/
	@Transactional(isolation=Isolation.DEFAULT,propagation=Propagation.REQUIRED,readOnly=false)
	public void saveElecCommonMsg(ElecCommonMsg elecCommonMsg) {
		//1：查询运行监控表，获取运行监控表的数据，返回List<ElecCommonMsg> list，判断数据库中是否存在数据
		List<ElecCommonMsg> list = elecCommonMsgDao.findCollectionByConditionNoPage("", null, null);
		//如果list!=null:数据表表中存在数据，获取页面传递的2个参数，组织PO对象，执行更新（update）
		if(list!=null && list.size()>0){
			ElecCommonMsg commonMsg = list.get(0);
			commonMsg.setStationRun(elecCommonMsg.getStationRun());
			commonMsg.setDevRun(elecCommonMsg.getDevRun());
			commonMsg.setCreateDate(new Date());
			//一个Session中不能存放2个相同的OID的对象

		}
		//如果list==null:数据表表中不存在数据，获取页面传递的2个参数，组织PO对象，执行新增（save）
		else{
			elecCommonMsg.setCreateDate(new Date());
			elecCommonMsgDao.save(elecCommonMsg);
		}
	}
	
	
}
