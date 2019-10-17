package domain;
/**
 * 签到口令
 * @author 王少彬。。。。
 *
 */
public class Word {
    private String id;
	/**
	 * 教师工号
	 */
    private String teacher_id;
	/**
	 * 学号
	 */
    private String student_id;
    /**
     * 身份
     */
    private String Type;    
    /**
         * 六位数口令
     */
    private String word;
	public String getTeacher_id() {
		return teacher_id;
	}
	public void setTeacher_id(String teacher_id) {
		this.teacher_id = teacher_id;
	}
	public String getWord() {
		return word;
	}
	public void setWord(String word) {
		this.word = word;
	}
	@Override
	public String toString() {
		return "Word [id=" + id + ", teacher_id=" + teacher_id + ", student_id=" + student_id + ", Type=" + Type
				+ ", word=" + word + "]";
	}
	public String getStudent_id() {
		return student_id;
	}
	public void setStudent_id(String student_id) {
		this.student_id = student_id;
	}
	public String getType() {
		return Type;
	}
	public void setType(String type) {
		Type = type;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
}
