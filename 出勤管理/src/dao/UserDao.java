package dao;

import java.util.ArrayList;
import java.util.HashMap;

import domain.Course;
import domain.Leave;
import domain.Record;
import domain.User;
import domain.Word;

// UserDao接口

public interface UserDao {
	/**
	 * 上课时间关系序列值，二维课表：位置，id键值对
	 * @return
	 */
	HashMap<String ,String > getCourseTimemap();
	/**
	 *未实现
	 * @param teacher_id
	 * @return
	 */
	ArrayList<Course> selectKeBiaoByTeacherId(int teacher_id);
	/**
	  *  工号查出勤表多行记录
	 * @param record
	 * @return
	 */
	ArrayList<Record> selectRecordByTId(Record record);
	/**
	 *  出勤-->学号查出勤表多行记录
	 * @param student_id
	 * @return
	 */
	ArrayList<Record> selectRecordBySId(Record record);
	//String selectRecordBysId(Record record);
	/**
	 * 添加请假记录
	 * @param form
	 */
	public void addLeave(Leave form);
	/**
	 * 添加用户
	 * @param form
	 */
	public void addUser(User form);
    /**
     * 通过id查用户
     * @param form
     * @return
     */
	public User findByUsername(User form);
	/**
	 * 更新密码
	 * @param form
	 * @return
	 */
	public User updatePwd(User form);
	/**
	 * 发口令
	 * @param word
	 */
	public Word putWord(Word word);
	/**
	 * 签口令
	 * @param user
	 */
	public Word getWord(Word word);
}
