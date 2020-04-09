package elec.web.action;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import elec.domain.ElecSystemDDL;
import elec.service.IElecSystemDDLService;
import elec.utils.AnnotationLimit;


@SuppressWarnings("serial")
@Controller("elecSystemDDLAction")
@Scope(value="prototype")
public class ElecSystemDDLAction extends BaseAction<ElecSystemDDL> {

	private ElecSystemDDL elecSystemDDL = this.getModel();
	
	@Resource(name=IElecSystemDDLService.SERVICE_NAME)
	private IElecSystemDDLService elecSystemDDLService;
	
	/**  
	* @Name: home
	* @Description: 数据字典的首页显示
	* @Return: 跳转到system/dictionaryIndex.jsp
	*/
	@AnnotationLimit(mid = "aq",pid = "am")
	public String home(){
		List<ElecSystemDDL> list = elecSystemDDLService.findDistinctKeywod();
		request.setAttribute("list", list);
		return "home";
	}
	
	/**  
	* @Name: edit
	* @Description: 跳转到数据字典编辑页面
	* @Return: 跳转到system/dictionaryEdit.jsp
	*/
	@AnnotationLimit(mid = "eb",pid = "ea")
	public String edit(){
		//获取数据类型
		String keyword = elecSystemDDL.getKeyword();
		//使用数据类型作为条件，查询数据字典中对应数据类型的集合
		List<ElecSystemDDL> list = elecSystemDDLService.findElecSystemDDLListByKeyword(keyword);
		request.setAttribute("list", list);
		return "edit";
	}

	/**  
	* @Name: save
	* @Description: 保存数据字典
	* @Return:  重定向到system/dictionaryIndex.jsp
	*/
	@AnnotationLimit(mid = "ec",pid = "ea")
	public String save(){
		elecSystemDDLService.saveElecSystemDDL(elecSystemDDL);
		return "save";
	}
	
}
