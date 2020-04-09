package elec.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import elec.dao.IElecExportFieldsDao;
import elec.domain.ElecExportFields;
import elec.service.IElecExportFieldsService;

@Service(IElecExportFieldsService.SERVICE_NAME)
@Transactional(readOnly=true)
public class ElecExportFieldsServiceImpl implements IElecExportFieldsService {

	@Resource(name=IElecExportFieldsDao.SERVICE_NAME)
	private IElecExportFieldsDao elecExportFieldsDao;
	
	/**  
	* @Name: findElecExportFields
	* @Description:使用belongTo作为主键，查询导出设置表，获取ElecExportFields对象
	* @Author: 刘洋（作者）
	* @Version: V1.00 （版本号）
	* @Create Date: 2013-11-17（创建日期）
	* @Parameters: String:主键ID
	* @Return: ElecExportFields：存放对象
	*/
	public ElecExportFields findElecExportFields(String belongTo) {
		ElecExportFields elecExportFields = elecExportFieldsDao.findObjectByID(belongTo);
		return elecExportFields;
	}
	
	/**  
	* @Name: saveSetExportExcel
	* @Description:获取页面5个隐藏域的值，执行更新的操作，针对页面传递的belongTo，更新对应的导出设置字段
	* @Author: 刘洋（作者）
	* @Version: V1.00 （版本号）
	* @Create Date: 2013-11-17（创建日期）
	* @Parameters: ElecExportFields：页面传递对象
	* @Return: 无
	*/
	@Transactional(isolation=Isolation.DEFAULT,propagation=Propagation.REQUIRED,readOnly=false)
	public void saveSetExportExcel(ElecExportFields elecExportFields) {
		elecExportFieldsDao.update(elecExportFields);
	}
	
}
