package domain;

public class Course {
	/**
	 * 课程号
	 */
    private String courseid;
    /**
        * 课程名
     */
    private String name;
    /**
     * 教室
     */
    private String classroom;
    /**
     * 年
     */
    private  String year;
    /**
     * 学期
     */
    private  String term;
    /**
     * 周
     */
    private  String week;
	public String getCourseid() {
		return courseid;
	}
	public void setCourseid(String courseid) {
		this.courseid = courseid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getClassroom() {
		return classroom;
	}
	public void setClassroom(String classroom) {
		this.classroom = classroom;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getTerm() {
		return term;
	}
	public void setTerm(String term) {
		this.term = term;
	}
	public String getWeek() {
		return week;
	}
	public void setWeek(String week) {
		this.week = week;
	}
}