package elec.web.action;

import java.io.PrintWriter;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import elec.domain.ElecCommonMsg;
import elec.service.IElecCommonMsgService;
import elec.utils.AnnotationLimit;
import elec.utils.ValueStackUtils;

@SuppressWarnings("serial")
@Controller("elecCommonMsgAction")
@Scope(value = "prototype")
public class ElecCommonMsgAction extends BaseAction<ElecCommonMsg> {

	private ElecCommonMsg elecCommonMsg = this.getModel();

	@Resource(name = IElecCommonMsgService.SERVICE_NAME)
	private IElecCommonMsgService elecCommonMsgService;

	/**
	 * @Name: home
	 * @Description: 运行监控的首页显示
	 * @Return: 跳转到system/actingIndex.jsp
	 */
	@AnnotationLimit(mid = "ap",pid = "am")
	public String home() {
		// 1：查询运行监控表，获取运行监控表中的数据，返回ElecCommonMsg对象
		ElecCommonMsg commonMsg = elecCommonMsgService.findElecCommonMsg();
		// 2：将ElecCommonMsg对象压入到栈顶，用于表单回显，将所有数据显示到文本框中
		ValueStackUtils.setValueStack(commonMsg);
		return "home";
	}

	/**
	 * @Name: save
	 * @Description: 保存运行监控
	 * @Return: 重定向到system/actingIndex.jsp
	 */
	@AnnotationLimit(mid = "cb",pid = "ca")
	public String save() {
		//elecCommonMsgService.saveElecCommonMsg(elecCommonMsg);
		//测试百分比的进度条
		//模拟：循环遍历150条数据，观察百分比的变化情况
		for(int i=1;i<=50;i++){
			elecCommonMsgService.saveElecCommonMsg(elecCommonMsg);
			request.getSession().setAttribute("percent", (double)i/150*100);//存放计算的百分比
		}
		//线程结束时，清空当前session
		request.getSession().removeAttribute("percent");
		return "save";
	}

	/**
	 * @Name: progressBar
	 * @Description: 使用ajax计算返回页面的百分比数量
	 * @Return: null
	 */
	@AnnotationLimit(mid = "cb",pid = "ca")
	public String progressBar() throws Exception {
		// 从session中获取操作方法中计算的百分比
		Double percent = (Double) request.getSession().getAttribute("percent");
		String res = "";
		// 此时说明操作的业务方法仍然继续在执行
		if (percent != null) {
			// 计算的小数，四舍五入取整
			int percentInt = (int) Math.rint(percent);
			res = "<percent>" + percentInt + "</percent>";
		}
		// 此时说明操作的业务方法已经执行完毕，session中的值已经被清空
		else {
			// 存放百分比
			res = "<percent>" + 100 + "</percent>";
		}
		// 定义ajax的返回结果是XML的形式
		PrintWriter out = response.getWriter();
		response.setContentType("text/xml");
		response.setHeader("Cache-Control", "no-cache");
		/*
		 * 存放结果数据，例如： <response> <percent>88</percent> </response>
		 */
		out.println("<response>");
		out.println(res);
		out.println("</response>");
		out.close();
		return null;
	}

}
