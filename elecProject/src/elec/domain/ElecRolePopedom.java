package elec.domain;

/**
  *   角色权限关联表
 * @author 王少彬。。。。
 *
 */
@SuppressWarnings("serial")
public class ElecRolePopedom implements java.io.Serializable {
	
	
	private String roleID;
	private String mid;
	private String pid;
	
	public String getRoleID() {
		return roleID;
	}
	public void setRoleID(String roleID) {
		this.roleID = roleID;
	}
	public String getMid() {
		return mid;
	}
	public void setMid(String mid) {
		this.mid = mid;
	}
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	
	
}
