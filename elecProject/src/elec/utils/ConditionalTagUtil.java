package elec.utils;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import org.apache.struts2.ServletActionContext;

public class ConditionalTagUtil extends SimpleTagSupport {
	private String pattern;
	
	/** 自定义标签
	 * 标签中要处理的内容*/
	@Override
	public void doTag() throws JspException, IOException {
		String popedom = (String) ServletActionContext.getRequest().getSession().getAttribute("globle_popedom");
		// <u:if pattern="aa">
		if(popedom.contains(pattern)){
			this.getJspBody().invoke(null);
		}
	}
	public void setPattern(String pattern) {
		this.pattern = pattern;
	}
}
