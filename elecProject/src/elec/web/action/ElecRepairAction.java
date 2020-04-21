package elec.web.action;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import elec.domain.ElecDevice;
import elec.domain.ElecOverhaulRecord;
import elec.domain.ElecSystemDDL;
import elec.service.IElecSystemDDLService;
import elec.service.IElecDeviceService;
import elec.service.IElecRepairService;
import elec.utils.AnnotationLimit;
import elec.utils.ValueStackUtils;
import javassist.expr.NewArray;

@SuppressWarnings("serial")
@Controller("elecRepairAction")
@Scope(value = "prototype")
public class ElecRepairAction extends BaseAction<ElecOverhaulRecord> {
	
	  private ElecOverhaulRecord elecRecord = this.getModel();
	 
	  
	  @Resource(name=IElecRepairService.SERVICE_NAME)
	  private IElecRepairService elecRepairService;
	  
	  @Resource(name = IElecDeviceService.SERVICE_NAME)  
	  IElecDeviceService elecDeviceService;
	  
      @Resource(name=IElecSystemDDLService.SERVICE_NAME)
	  private IElecSystemDDLService elecSystemDDLService;
	  
	/**
	 * @Name: home
	 * @Description:检修记录的首页显示
	 * @Return: 跳转到equapment/repairIndex.jsp
	 */
	 @AnnotationLimit(mid = "ac",pid = "aa")
	public String home() {
		//1：从数据字典表中，查询所属单位,检修状态的列表，返回List<ElecSystemDDL>
		List<ElecSystemDDL> jctList = elecSystemDDLService.findElecSystemDDLListByKeyword("所属单位");
		request.setAttribute("jctList", jctList);
		List<ElecSystemDDL> jctUnitList = elecSystemDDLService.findElecSystemDDLListByKeyword("使用单位");
		request.setAttribute("jctUnitList", jctUnitList);
		List<ElecSystemDDL> repairStateList = elecSystemDDLService.findElecSystemDDLListByKeyword("检修状态");
		request.setAttribute("repairStateList", repairStateList);
		//2:指定查询条件，查询记录集合
		List<ElecOverhaulRecord> recordList = elecRepairService.findRepairListByCondition(elecRecord);		
	    request.setAttribute("recordList", recordList);		
		//3.添加分页
		//用来判断跳转页面的标识
	    String initpage = request.getParameter("initpage");
		if(initpage!=null && initpage.equals("1")){
			return "list";
		}
		return "home";
	}

	 /**  
		* @Name: edit
		* @Description: 跳转到编辑页面
		* @Return: 跳转到equapment/repairEdit.jsp
		*/
		@AnnotationLimit(mid = "ac2",pid = "ac")
		public String edit(){
			//1：使用userID，查询对应用户的详细信息，返回ElecUser对象，放置到栈顶，用于struts2支持的表单回显
			ElecOverhaulRecord recordOne = elecRepairService.findElecRepairByID(elecRecord);
			//将新对象压入到栈顶之前，从新设置栈顶的值
			
			//数据项的编号ddlCode=2和keyword="所属单位"，获取数据项的值
			//recordOne.getElecDevice().setJctID(StringUtils.isNotBlank(recordOne.getElecDevice().getJctID())?elecSystemDDLService.findDdlNameByKeywordAndDdlCode("所属单位",recordOne.getElecDevice().getJctID()):"");
	     	recordOne.setIsHaving(StringUtils.isNotBlank(recordOne.getIsHaving())?elecSystemDDLService.findDdlNameByKeywordAndDdlCode("检修记录",recordOne.getIsHaving()):"");
	   
			ValueStackUtils.setValueStack(recordOne);
			this.initSystemDDL();
			
			String ddlName1 = elecSystemDDLService.findDdlNameByKeywordAndDdlCode("所属单位",recordOne.getElecDevice().getJctID());
			List<ElecSystemDDL> jctList= elecSystemDDLService.findElecSystemDDLListByKeyword(ddlName1);
			request.setAttribute("jctList", jctList);
		
			
			request.setAttribute("recordOne", recordOne);
			return "edit";
		}		
		
		/**查询所属单位，检修状态的下拉菜单*/
		private void initSystemDDL() {
			List<ElecSystemDDL> jctList = elecSystemDDLService.findElecSystemDDLListByKeyword("所属单位");
			request.setAttribute("jctList", jctList);
			List<ElecSystemDDL> repairStateList = elecSystemDDLService.findElecSystemDDLListByKeyword("检修状态");
			request.setAttribute("repairStateList", repairStateList);
		}
		
		
		/**  
		* @Name: save
		* @Description: 保存设备信息
		* @Return: 
		*/
		@AnnotationLimit(mid = "ac4",pid = "ac")
		public String save(){			
			elecRepairService.updateRepair(elecRecord);			
			return "save";
		}
		
		/**  
		* @Name: delete
		* @Description: 删除页面选择的设备
		* @Return: 重定向到
		*/
		@AnnotationLimit(mid = "ac3",pid = "ac")
		public String delete(){
			elecRepairService.deleteRepairByIds(elecRecord);
			/**重定向到某一页*/
			String pageNO = request.getParameter("pageNO");
			request.setAttribute("pageNO", pageNO);
			return "delete";
		}
		
		/**  
		* @Name: moreAad
		* @Description: 跳转到批量新增页面
		* @Return: 跳转repairMoreAdd.jsp
		*/
		@AnnotationLimit(mid="ac5",pid="ac")
		public String moreAdd(){
			this.initSystemDDL();
			ElecDevice elecDevice = new ElecDevice();
			List<ElecDevice> deviceList = elecDeviceService.findDeviceListByCondition(elecDevice);
			request.setAttribute("deviceList", deviceList);	
			return "moreAdd";
		}
		
		/**  
		* @Name: moreAad
		* @Description: 跳转到批量新增页面
		* @Return: 跳转repairMoreAddList.jsp
		*/
		@AnnotationLimit(mid="ac5",pid="ac")
		public String moreAddList(){
			this.initSystemDDL();
			ElecDevice elecDevice = new ElecDevice();
			List<ElecDevice> deviceList = elecDeviceService.findDeviceListByCondition(elecDevice);
			request.setAttribute("deviceList", deviceList);	
			//用来判断跳转页面的标识
			String initpage = request.getParameter("initpage");
			if(initpage!=null && initpage.equals("1")){
				return "list";
			}
			return "moreAdd";
		}
		


}