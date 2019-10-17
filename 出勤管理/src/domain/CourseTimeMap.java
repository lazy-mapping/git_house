package domain;

public class CourseTimeMap {
	/**
	 * 课程号
	 */
    private String courseId; 
    /**
         * 上课时间关系序列值
     */
    private String timemap;

	public String getCourseId() {
		return courseId;
	}

	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}

	public String getTimemap() {
		return timemap;
	}

	public void setTimemap(String timemap) {
		this.timemap = timemap;
	}
}