package domain;

public class TeacherCourse {
	/**
	 * 工号
	 */
    private String teacherId;
    /**
     * 课程号
     */
    private String courseId;
	public String getTeacherId() {
		return teacherId;
	}
	public void setTeacherId(String teacherId) {
		this.teacherId = teacherId;
	}
	public String getCourseId() {
		return courseId;
	}
	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}
}