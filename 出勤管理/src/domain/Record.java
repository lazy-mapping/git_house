package domain;
/**
 * 出勤表，包含教师，学生
 * @author 王少彬。。。。
 *
 */
public class Record {
	/**
	 * 出勤编号
	 */
	private String  kaoqin_id;
	/**
	 * 唯一标识，用户id
	 */
	private String  id;
	/**
	 * 上课时间/节次
	 */
    private String time;
    /**
     * 学号
     */
    private String s_id;
    /**
     * 教师号
     */
    private String t_id;
    /**
     * 课程号
     */
    private String course_id;
    /**
     * 考勤状态
     */
    private String stauts;
    /**
     * 周
     */
    private String week;
    /**
     * 学期
     */
    private String term;
    /**
     * 年
     */
    private String year;
    /**
     * 月
     */
    private String month;
	public String getKaoqin_id() {
		return kaoqin_id;
	}
	public void setKaoqin_id(String kaoqin_id) {
		this.kaoqin_id = kaoqin_id;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getS_id() {
		return s_id;
	}
	public void setS_id(String s_id) {
		this.s_id = s_id;
	}
	public String getT_id() {
		return t_id;
	}
	public void setT_id(String t_id) {
		this.t_id = t_id;
	}
	public String getCourse_id() {
		return course_id;
	}
	public void setCourse_id(String course_id) {
		this.course_id = course_id;
	}
	public String getStauts() {
		return stauts;
	}
	public void setStauts(String stauts) {
		this.stauts = stauts;
	}
	public String getWeek() {
		return week;
	}
	public void setWeek(String week) {
		this.week = week;
	}
	public String getTerm() {
		return term;
	}
	public void setTerm(String term) {
		this.term = term;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	@Override
	public String toString() {
		return "Record [kaoqin_id=" + kaoqin_id + ", id=" + id + ", time=" + time + ", s_id=" + s_id + ", t_id=" + t_id
				+ ", course_id=" + course_id + ", stauts=" + stauts + ", week=" + week + ", term=" + term + ", year="
				+ year + ", month=" + month + "]";
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

}
