package domain;

public class Leave {
	/**
	 * 请假编号
	 */
	private String leaveId;
	/**
	 * 请假人id
	 */
	private String employeeId;		
	/**
	 * 请加人姓名
	 */
	private String employeeName;
	/**
	 * 身份
	 */
	private String type;
	/**
	 * 开始时间
	 */
	private String startTime;	
	/**
	 * 结束时间
	 */
	private String endTime;	
	/**
	 * 请假天数
	 */
	private String day;		
	/**
	 * 请假理由
	 */
	private String reason;	
	/**
	 * 审批人
	 */
	private String approvePerson;	

	public String getLeaveId() {
		return leaveId;
	}

	public void setLeaveId(String leaveId) {
		this.leaveId = leaveId;
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getApprovePerson() {
		return approvePerson;
	}

	public void setApprovePerson(String approvePerson) {
		this.approvePerson = approvePerson;
	}

	@Override
	public String toString() {
		return "Leave [leaveId=" + leaveId + ", employeeId=" + employeeId + ", employeeName=" + employeeName + ", type="
				+ type + ", startTime=" + startTime + ", endTime=" + endTime + ", day=" + day + ", reason=" + reason
				+ ", approvePerson=" + approvePerson + "]";
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}			
	
}