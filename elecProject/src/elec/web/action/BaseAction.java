package elec.web.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import elec.utils.GenericTypeUtils;

public class BaseAction<T> extends ActionSupport implements ModelDriven<T>,ServletRequestAware,ServletResponseAware {

	private static final long serialVersionUID = 1L;

	private T entity;
	protected HttpServletRequest request;
	protected HttpServletResponse response;
	
	//T型实例化
	@SuppressWarnings("unchecked")
	public BaseAction(){
		//泛型转换为真实的对象
		Class<?> entityClass = (Class<?>) GenericTypeUtils.getActualType(this.getClass());
		try {
			entity = (T) entityClass.newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public T getModel() {
		return entity;
	}

	public void setServletResponse(HttpServletResponse res) {
		this.response = res;
	}

	public void setServletRequest(HttpServletRequest req) {
		this.request = req;
	}
}
