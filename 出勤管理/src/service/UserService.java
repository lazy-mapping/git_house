package service;

import java.util.ArrayList;
import java.util.HashMap;

import dao.DaoFactory;
import dao.UserDao;
import domain.CourseTimeMap;
import domain.Leave;
import domain.Record;
import domain.User;
import domain.Word;


/**
 * 业务层
 * 封装的是业务，可以理解成功能，例如本项目中有两个功能：登录和注册
 * 那么本类就有两个方法：login()、regist()
 *
 */
public class UserService {
	// 把具体的实现类的创建，隐藏到工厂中了！，类似于spring框架
	private UserDao userDao = DaoFactory.getUserDao();
	
	
	/**
	 * 查看教师个人的出勤记录，可以按月，周，年等查，后续再写
	 * @param record
	 * @return
	 * @throws UserException
	 */
	public 	ArrayList<Record> selectRecordTeacher(Record record)throws UserException{
		ArrayList<Record> list = userDao.selectRecordByTId(record);	
		System.out.println("service教师"+list.toString());
		return list;
	}
	
	
	/**
	 * 查看学生个人的出勤记录，可以按月，周，年等查，后续再写
	 * @param record
	 * @return
	 * @throws UserException
	 */
	public 	ArrayList<Record> selectRecordStudent(Record record)throws UserException{
		ArrayList<Record> list = userDao.selectRecordBySId(record);
		System.out.println("service学生数据库"+list.toString());
		return list;
	}

	/*
	 * public String selectRecordstudent(Record record)throws UserException{
	 * ArrayList<Record> string=userDao.selectRecordBySId(record);
	 * System.out.println("service学生数据库json"+string); return string; }
	 */
	/**
	 * 添加请假记录
	 * @param form
	 * @throws UserException
	 */
	public void addLeave(Leave form) throws UserException{
		if(form==null) {
			 throw new UserException("添加请假记录失败！");
		}
		userDao.addLeave(form);
	}
	
	/**
	 * 发口令
	 * @param teacher_id
	 * @throws UserException
	 */
	public Word qiandao(Word form) throws UserException{
		if(form==null) {
			return null;
		}
		/**
		 * 判断是否教师身份,执行发口令
		 */
		if(form.getType().equals("teacher")) {
			Word word=userDao.putWord(form);
			form.setWord(word.getWord());
		}
		/**
		 * 判断是否学生身份,执行签口令
		 */
		
		  if(form.getType().equals("student")) {
			  Word word=userDao.getWord(form);
			  if(form.getWord().equals(word.getWord())) {
				  System.out.println("学生签到成功！");
				  return form;
			  }else {
				  System.out.println("学生签到失败！");
				  throw new UserException("口令错误！");
			  }
		  }
		 
		return form;
	}
	
	/**
	 * 搜索课表
	 */
	public CourseTimeMap getCourse(CourseTimeMap course) throws UserException {
		
		return course;
	}
	
	/**
	 * 修改密码
	 * @param form
	 * @throws UserException
	 */
	public User updatePwd(User form)  throws UserException{
		
		/*
		 * 判断form和user密码是否相同
		 * 若不同，说明密码错误，所以抛出异常
		 */
		if(!form.getPassword().equals(form.getPwd())) throw new 
			UserException("旧密码错误！"); 
		if(!form.getEnsurePassword().equals(form.getNewPassword())) throw new 
			UserException("确认新密码是否相同！"); 
		User user =userDao.updatePwd(form);
		return user;
	}
	
	/**
	 * 注册功能
	 * @param form
	 */
	public void regist(User form) throws UserException {
		/*
		 * 1. 校验用户名
		 *   > 如果已经注册，那么抛出异常（异常信息为：用户名已被注册！）
		 * 2. 完成插入
		 */
		/*
		 * 1. 校验用户名
		 */
		User user = userDao.findByUsername(form);
		if(user != null) throw new UserException("用户名已被注册！");
		
		/*
		 * 2. 插入User对象
		 */
		userDao.addUser(form);
	}
	/**
	 * 登录功能
	 * @param form
	 * @return
	 * @throws UserException
	 */
	public User login(User form) throws UserException {
		/*
		 * 1. 使用form的userId进行校验
		 */
		User user = userDao.findByUsername(form);
		/*
		 * 判断user是否为null，若为null，说明用户名错误，所以抛出异常
		 */
		if(user == null) throw new UserException("用户名不存在！");
		/*
		 * 判断form和user密码是否相同
		 * 若不同，说明密码错误，所以抛出异常
		 */
		if(!user.getPassword().equals(form.getPassword())) {
			throw new UserException("密码错误！");
		}
		/*
		 * 若执行到这里，说明没有错误，登录成功了，返回当前user对象！
		 */
		return user;
	}


	public HashMap<String ,String > getCourseTimemap() throws UserException {
		HashMap<String ,String > hashmap=userDao.getCourseTimemap();
		if(hashmap == null) throw new UserException("访问数据库失败");
		return hashmap;
	}
}
