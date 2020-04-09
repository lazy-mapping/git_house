package elec.web.action;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;

import elec.domain.ElecText;
import elec.service.IElecTextService;

@SuppressWarnings("serial")
@Controller("elecTextAction")
public class ElecTextAction extends BaseAction<ElecText> {
	
	private ElecText elecText = this.getModel();
	
	/**注入Service*/
	@Resource(name=IElecTextService.SERVICE_NAME)
	private IElecTextService elecTextService;
	
	/**  
	* @Name: save
	* @Return: String：跳转到textAdd.jsp
	*/
	public String save(){
		elecTextService.save(elecText);
		System.out.println(request.getParameter("textDate")+"测试成功");
		return "save";
	}
}
