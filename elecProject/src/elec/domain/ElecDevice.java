package elec.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * 设备表单
 * @author 王少彬。。。。
 *
 */
@SuppressWarnings("serial")
public class ElecDevice implements java.io.Serializable{  
	
	private String DevID;     //编号，值唯一
	private String DevName;  //设备名称
	private String jctID;		 //所属单位code
	private String jctUnitID;	//所属单位的单位名称（联动）
	private Date   UseDate;      //使用日期
	private String DevType;    //规格型号
	private String DevState;     //设备状态
	private String ProduceHome;  //制造厂
    private String voltage;      //额定电压
    private String power;        //额定功率
    private String Remrk;        //备注
    private String ProduceArea;     //产地
    private String Uses;		//用途
    private String AdjustPeriod; //检修周期 
    
    private Set<ElecOverhaulRecord> record = new HashSet<ElecOverhaulRecord>(); //检修记录集合	
    
	public String getVoltage() {
		return voltage;
	}
	public void setVoltage(String voltage) {
		this.voltage = voltage;
	}
	public String getPower() {
		return power;
	}
	public void setPower(String power) {
		this.power = power;
	}
	public String getDevID() {
		return DevID;
	}
	public void setDevID(String devID) {
		DevID = devID;
	}
	public String getDevName() {
		return DevName;
	}
	public void setDevName(String devName) {
		DevName = devName;
	}
	public String getDevType() {
		return DevType;
	}
	public void setDevType(String devType) {
		DevType = devType;
	}
	public String getProduceHome() {
		return ProduceHome;
	}
	public void setProduceHome(String produceHome) {
		ProduceHome = produceHome;
	}
	public String getRemrk() {
		return Remrk;
	}
	public void setRemrk(String remrk) {
		Remrk = remrk;
	}
	public Date getUseDate() {
		return UseDate;
	}
	public void setUseDate(Date useDate) {
		UseDate = useDate;
	}

	public String getJctID() {
		return jctID;
	}
	public void setJctID(String jctID) {
		this.jctID = jctID;
	}
	public String getJctUnitID() {
		return jctUnitID;
	}
	public void setJctUnitID(String jctUnitID) {
		this.jctUnitID = jctUnitID;
	}
	public String getAdjustPeriod() {
		return AdjustPeriod;
	}
	public void setAdjustPeriod(String adjustPeriod) {
		AdjustPeriod = adjustPeriod;
	}
	public String getDevState() {
		return DevState;
	}
	public void setDevState(String devState) {
		DevState = devState;
	}
	public String getProduceArea() {
		return ProduceArea;
	}
	public void setProduceArea(String produceArea) {
		ProduceArea = produceArea;
	}

	public String getUses() {
		return Uses;
	}
	public void setUses(String uses) {
		Uses = uses;
	}

	/***************非持久化javabean属性*****************/
	//使用开始时间
	private Date useDateBegin;
	//使用结束时间
	private Date useDateEnd;

	public Date getUseDateBegin() {
		return useDateBegin;
	}
	public void setUseDateBegin(Date useDateBegin) {
		this.useDateBegin = useDateBegin;
	}

	public Date getUseDateEnd() {
		return useDateEnd;
	}
	public void setUseDateEnd(Date useDateEnd) {
		this.useDateEnd = useDateEnd;
	}
	
	/**
	 * 判断是跳转    菜单->设备             页面，此时标识的值null
	 * 还是跳转     F1-> 查询->设备      页面，此时标识的值1
	 */
	private String viewflag;

	public String getViewflag() {
		return viewflag;
	}
	public void setViewflag(String viewflag) {
		this.viewflag = viewflag;
	}
	/**
	 * 用来判断是否系统管理员身份，点击用户管理，是（查看列表），不是（跳转的是编辑页面）
	 *  roleflag==null：表示跳转到编辑页面的时候，【关闭】按钮要显示
	 *  roleflag==1：表示跳转到编辑页面的时候，【关闭】按钮要隐藏???????????
	 */
	private String roleflag;
	public String getRoleflag() {
		return roleflag;
	}
	public void setRoleflag(String roleflag) {
		this.roleflag = roleflag;
	}
	public Set<ElecOverhaulRecord> getRecord() {
		return record;
	}
	public void setRecord(Set<ElecOverhaulRecord> record) {
		this.record = record;
	}
	
}							