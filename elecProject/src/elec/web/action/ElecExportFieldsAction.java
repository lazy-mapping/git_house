package elec.web.action;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import elec.domain.ElecExportFields;
import elec.service.IElecExportFieldsService;
import elec.utils.AnnotationLimit;
import elec.utils.StringToListUtils;

@SuppressWarnings("serial")
@Controller("elecExportFieldsAction")
@Scope(value="prototype")
public class ElecExportFieldsAction extends BaseAction<ElecExportFields> {

	private ElecExportFields elecExportFields = this.getModel();
	
	@Resource(name=IElecExportFieldsService.SERVICE_NAME)
	private IElecExportFieldsService elecExportFieldsService;
	
	/**  
	* @Name: setExportExcel
	* @Description: 跳转导出设置的页面（左移右移）
	* @Return: 跳转到system/exportExcel.jsp
	*/
	@AnnotationLimit(mid = "db",pid = "da")
	public String setExportExcel(){
		//获取所属模块（主键ID）
		String belongTo = elecExportFields.getBelongTo();
		//1：使用belongTo作为主键，查询导出设置表，获取ElecExportFields对象
		ElecExportFields exportFields = elecExportFieldsService.findElecExportFields(belongTo);
		/**
		 * 2:使用ElecExportFields对象获取4个字段的值
			 未导出的中文字段       未导出的英文字段           导出的中文字段           导出的英文字段
			   同时将4个字段的值，使用#号分割，转换成4个集合List<String> list
		 */
		List<String> noZList = StringToListUtils.stringToList(exportFields.getNoExpNameList(),"#");
		List<String> noEList = StringToListUtils.stringToList(exportFields.getNoExpFieldName(),"#");
		List<String> zList = StringToListUtils.stringToList(exportFields.getExpNameList(),"#");
		List<String> eList = StringToListUtils.stringToList(exportFields.getExpFieldName(),"#");
		/**
		 * 3：由于特点
			  【未导出的中文字段和未导出的英文字段，长度要一一对应
			     导出的中文字段和导出的英文字段，长度要一一对应】
		   4：使用2个map集合，存放未导出字段的集合，存放导出字段的集合
			  Map<String,String> map ，key存放英文信息，map集合的value存放中文信息
		 */
		//存放未导出的字段
		Map<String, String> noMap = new LinkedHashMap<String, String>();
		//存放导出的字段
		Map<String, String> map = new LinkedHashMap<String, String>();
		//noZList和noEList长度一一对应
		if(noZList!=null && noZList.size()>0){
			for(int i=0;i<noZList.size();i++){
				noMap.put(noEList.get(i), noZList.get(i));
			}
		}
		//zList和eList长度一一对应
		if(zList!=null && zList.size()>0){
			for(int i=0;i<zList.size();i++){
				map.put(eList.get(i), zList.get(i));
			}
		}
		request.setAttribute("noMap", noMap);
		request.setAttribute("map", map);
		
		return "setExportExcel";
	}
	
	/**  
	* @Name: saveSetExportExcel
	* @Description: 保存导出设置
	* @Return: 跳转到close.jsp(关闭页面)
	*/
	@AnnotationLimit(mid = "dc",pid = "da")
	public String saveSetExportExcel(){
		elecExportFieldsService.saveSetExportExcel(elecExportFields);
		return "close";
	}
	
}
