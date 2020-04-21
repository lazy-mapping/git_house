package elec.domain;

import java.util.Date;
import java.util.Set;

/**
 * 检修记录
 * @author 王少彬。。。。
 *
 */
@SuppressWarnings("serial")
public class ElecOverhaulRecord implements java.io.Serializable{  
	
	private String SeqID;    //记录编号，唯一
	private  String  DevID;     //设备编号
	private Date StartDate;  //检修开始时间
    private String IsHaving; //记录文件
    private String Record;   //检修记录
    private String repairState;  //检修状态状态
    private ElecDevice elecDevice; //设备
    
	public String getSeqID() {
		return SeqID;
	}
	public void setSeqID(String seqID) {
		SeqID = seqID;
	}
	public String getDevID() {
		return DevID;
	}
	public void setDevID(String devID) {
		DevID = devID;
	}
	public String getIsHaving() {
		return IsHaving;
	}
	public void setIsHaving(String isHaving) {
		IsHaving = isHaving;
	}
	public String getRecord() {
		return Record;
	}
	public void setRecord(String record) {
		Record = record;
	}

	public Date getStartDate() {
		return StartDate;
	}
	public void setStartDate(Date startDate) {
		StartDate = startDate;
	}
	public String getRepairState() {
		return repairState;
	}
	public void setRepairState(String repairState) {
		this.repairState = repairState;
	}
	/***************非持久化javabean属性*****************/
	
	
	private Date StartDateBegin;   //检修开始时间	
	private Date StartDateEnd;     //检修结束时间
	private String DevName;  //设备名称
	private String jctID;		 //所属单位code
	private String jctUnitID;	//使用单位的单位名称（联动）
	private Date   UseDate;      //使用日期	
    private String AdjustPeriod; //检修周期 
    private String Remrk;        //备注
	public Date getStartDateBegin() {
		return StartDateBegin;
	}
	public void setStartDateBegin(Date startDateBegin) {
		StartDateBegin = startDateBegin;
	}
	public Date getStartDateEnd() {
		return StartDateEnd;
	}
	public void setStartDateEnd(Date startDateEnd) {
		StartDateEnd = startDateEnd;
	}
	public ElecDevice getElecDevice() {
		return elecDevice;
	}
	public void setElecDevice(ElecDevice elecDevice) {
		this.elecDevice = elecDevice;
	}
	public String getDevName() {
		return DevName;
	}
	public void setDevName(String devName) {
		DevName = devName;
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
	public Date getUseDate() {
		return UseDate;
	}
	public void setUseDate(Date useDate) {
		UseDate = useDate;
	}
	public String getAdjustPeriod() {
		return AdjustPeriod;
	}
	public void setAdjustPeriod(String adjustPeriod) {
		AdjustPeriod = adjustPeriod;
	}
	public String getRemrk() {
		return Remrk;
	}
	public void setRemrk(String remrk) {
		Remrk = remrk;
	}

}							