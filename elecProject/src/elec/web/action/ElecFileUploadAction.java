package elec.web.action;


import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import elec.domain.ElecFileUpload;
import elec.domain.ElecSystemDDL;
import elec.service.IElecFileUploadService;
import elec.service.IElecSystemDDLService;
import elec.utils.AnnotationLimit;


@SuppressWarnings("serial")
@Controller("elecFileUploadAction")
@Scope(value="prototype")
public class ElecFileUploadAction extends BaseAction<ElecFileUpload>  {
	
	
	
	private ElecFileUpload elecFileUpload = this.getModel();
	
	@Resource(name=IElecFileUploadService.SERVICE_NAME)
	private IElecFileUploadService elecFileUploadService;
	
	@Resource(name=IElecSystemDDLService.SERVICE_NAME)
	private IElecSystemDDLService elecSystemDDLService;
	
	/**  资料图纸管理的首页显示*/
	@AnnotationLimit(mid = "af",pid = "ae")
	public String home(){
		this.initSystemDDL();
		return "home";
	}
	
	/**点击查询按钮，使用lucene进行检索*/
	@AnnotationLimit(mid = "f6",pid = "af")
	public String luceneHome(){
		this.initSystemDDL();
		List<ElecFileUpload> uploadList = elecFileUploadService.findFileUploadList(elecFileUpload);
		request.setAttribute("uploadList", uploadList);	
		return "home";
	}
	
	/**跳转到新增页面*/
	@AnnotationLimit(mid = "f1",pid = "af")
	public String add(){
		this.initSystemDDL();
		return "add";
	}
	
	
	/**初始化数据字典的所属单位和图纸类别*/
	private void initSystemDDL() {
		List<ElecSystemDDL> jctList = elecSystemDDLService.findElecSystemDDLListByKeyword("所属单位");
		request.setAttribute("jctList", jctList);
		List<ElecSystemDDL> paperList = elecSystemDDLService.findElecSystemDDLListByKeyword("图纸类别");
		request.setAttribute("paperList", paperList);
	}

	/**使用ajax技术，以所属单位和图纸类别进行查询，获取对应的集合列表*/
	@AnnotationLimit(mid = "f4",pid = "af")
	public String addList(){
		List<ElecFileUpload> uploadList = elecFileUploadService.findFileUploadListByCondition(elecFileUpload);
		request.setAttribute("uploadList", uploadList);	
		return "addList";
	}
	
	/**保存资料图纸数据*/
	@AnnotationLimit(mid = "f2",pid = "af")
	public String save(){
		elecFileUploadService.save(elecFileUpload);
		return "save";
	}
	
	/**文件下载*/
	@AnnotationLimit(mid = "f5",pid = "af")
	public String download(){
		try {
			ElecFileUpload fileUpload = elecFileUploadService.findFileUploadByID(elecFileUpload);
			//将name的值放置到栈顶，用于struts.xml文件中
			String filename = fileUpload.getFileName();
			//处理中文响应的时候的乱码问题
			filename = new String(filename.getBytes("gbk"),"iso-8859-1");
			request.setAttribute("filename", filename);
			//获取路径path
			String path = fileUpload.getFileUrl();
			//使用path，转化成File类型，再转换成InputStream类型
			InputStream inputStream = new FileInputStream(new File(ServletActionContext.getServletContext().getRealPath("")+path));
			//放置到栈顶的属性中
			elecFileUpload.setInputStream(inputStream);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "success";
	}
	
	/**删除资料图纸数据*/
	@AnnotationLimit(mid = "f3",pid = "af")
	public String delete(){
		elecFileUploadService.deleteFileUploadByID(elecFileUpload);
		return "delete";
	}
}
