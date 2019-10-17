package domain;

public class StudentCourse {
	/**
	 * 学号
	 */
    private String studentId;
    /**
     * 课程号
     */
    private String courseId;
	public String getStudentId() {
		return studentId;
	}
	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}
	public String getCourseId() {
		return courseId;
	}
	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}
}