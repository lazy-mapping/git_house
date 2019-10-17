package domain;
/**
 *用户表
 * @author 王少彬。。。。
 *
 */
public class User {
	private String Id;      // 主键
	private String Name;    // 名称
	private String Password;// 密码
	private String Sex;     // 性别
	private String Age;     // 年龄
	private String Phone;   // 电话
	private String Email;   // 邮箱
	private String Qq;      // QQ
	private String Type;    // 身份
	private String Pwd;        // 旧密码
	private String NewPassword;// 新密码
	private String EnsurePassword;// 确认密码
	public String getId() {
		return Id;
	}
	public void setId(String id) {
		Id = id;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getPassword() {
		return Password;
	}
	public void setPassword(String password) {
		Password = password;
	}
	public String getSex() {
		return Sex;
	}
	public void setSex(String sex) {
		Sex = sex;
	}
	public String getAge() {
		return Age;
	}
	public void setAge(String age) {
		Age = age;
	}
	public String getPhone() {
		return Phone;
	}
	public void setPhone(String phone) {
		Phone = phone;
	}
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}
	public String getQq() {
		return Qq;
	}
	public void setQq(String qq) {
		Qq = qq;
	}
	public String getType() {
		return Type;
	}
	public void setType(String type) {
		Type = type;
	}
	public String getNewPassword() {
		return NewPassword;
	}
	public void setNewPassword(String newPassword) {
		NewPassword = newPassword;
	}
	public String getEnsurePassword() {
		return EnsurePassword;
	}
	public void setEnsurePassword(String ensurePassword) {
		EnsurePassword = ensurePassword;
	}
	@Override
	public String toString() {
		return "User [Id=" + Id + ", Name=" + Name + ", Password=" + Password + ", Sex=" + Sex + ", Age=" + Age
				+ ", Phone=" + Phone + ", Email=" + Email + ", Qq=" + Qq + ", Type=" + Type + ", Pwd=" + Pwd
				+ ", NewPassword=" + NewPassword + ", EnsurePassword=" + EnsurePassword + "]";
	}
	public String getPwd() {
		return Pwd;
	}
	public void setPwd(String pwd) {
		Pwd = pwd;
	}
}
