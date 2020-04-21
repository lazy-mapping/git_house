package elec.web.action;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import elec.domain.ElecDevice;
import elec.domain.ElecSystemDDL;
import elec.service.IElecDeviceService;
import elec.service.IElecSystemDDLService;
import elec.utils.AnnotationLimit;
import elec.utils.ValueStackUtils;

@SuppressWarnings("serial")
@Controller("elecDeviceAction")
@Scope(value = "prototype")
public class ElecDeviceAction extends BaseAction<ElecDevice> {
	
	  private ElecDevice elecDevice = this.getModel();
	  
	  @Resource(name = IElecDeviceService.SERVICE_NAME) private 
	  IElecDeviceService elecDeviceService;
	  
      @Resource(name=IElecSystemDDLService.SERVICE_NAME)
	  private IElecSystemDDLService elecSystemDDLService;
	  
	/**
	 * @Name: home
	 * @Description: 设备管理的首页显示
	 * @Return: 跳转到equapment/equapmentIndex.jsp
	 */
	 @AnnotationLimit(mid = "ab",pid = "aa")
	public String home() {
		//1：从数据字典表中，查询所属单位,设备状态的列表，返回List<ElecSystemDDL>
		List<ElecSystemDDL> jctList = elecSystemDDLService.findElecSystemDDLListByKeyword("所属单位");
		request.setAttribute("jctList", jctList);
		List<ElecSystemDDL> devStateList = elecSystemDDLService.findElecSystemDDLListByKeyword("设备状态");
		request.setAttribute("devStateList", devStateList);
		//2:指定查询条件，查询设备集合
		List<ElecDevice> deviceList = elecDeviceService.findDeviceListByCondition(elecDevice);
		request.setAttribute("deviceList", deviceList);		
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
		* @Return: 跳转到equapment/equapmentEdit.jsp
		*/
		@AnnotationLimit(mid = "ab1",pid = "ab")
		public String edit(){
			//1：使用userID，查询对应用户的详细信息，返回ElecUser对象，放置到栈顶，用于struts2支持的表单回显
			ElecDevice device = elecDeviceService.findElecDeviceByID(elecDevice);
			//将新对象压入到栈顶之前，从新设置栈顶的值
		
			ValueStackUtils.setValueStack(device);
			//2：查询所属单位，设备状态的下拉菜单
			this.initSystemDDL();
			
			//3：从栈顶对象，获取数据项的编号（ddlCode=2和keyword="所属单位"，获取数据项的值）
			String ddlName1 = elecSystemDDLService.findDdlNameByKeywordAndDdlCode("所属单位",device.getJctID());
			//4：遍历单位名称的集合(依赖所属单位的名称)，返回List<ElecSystemDDL>
			List<ElecSystemDDL> jctList= elecSystemDDLService.findElecSystemDDLListByKeyword(ddlName1);
			request.setAttribute("jctList", jctList);
			
			return "edit";
		}		
		
		/**查询所属单位，设备状态的下拉菜单*/
		private void initSystemDDL() {
			List<ElecSystemDDL> jctList = elecSystemDDLService.findElecSystemDDLListByKeyword("所属单位");
			request.setAttribute("jctList", jctList);
			List<ElecSystemDDL> devStateList = elecSystemDDLService.findElecSystemDDLListByKeyword("设备状态");
			request.setAttribute("devStateList", devStateList);
		}
		
		
		/**  
		* @Name: save
		* @Description: 保存设备信息
		* @Return: 
		*/
		@AnnotationLimit(mid = "fc",pid = "fa")
		public String save(){			
			elecDeviceService.updateDevice(elecDevice);			
			return "save";
		}
		
		/**  
		* @Name: delete
		* @Description: 删除页面选择的设备
		* @Return: 重定向到
		*/
		@AnnotationLimit(mid = "ab3",pid = "ab")
		public String delete(){
			elecDeviceService.deleteDeviceByIds(elecDevice);
			/**重定向到某一页*/
			String pageNO = request.getParameter("pageNO");
			request.setAttribute("pageNO", pageNO);
			return "delete";
		}
		
		/**  
		* @Name: add
		* @Description: 跳转到新增页面
		* @Return: 跳转到
		*/
		@AnnotationLimit(mid="ab4",pid="ab")
		public String add(){
			this.initSystemDDL();
			return "add";
		}
		
		
}
