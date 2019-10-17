package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import domain.Course;
import domain.Leave;
import domain.Record;
import domain.User;
import domain.Word;

/**
 * 针对数据库的实现
 * 
 * @author cxf
 *
 */
public class JdbcUserDaoImpl implements UserDao {
	private String biao;

	@Override
	public User updatePwd(User form) {
		Connection con = null;
		PreparedStatement pstmt = null;
		biao = form.getType();
		try {
			/*
			 * 一、得到连接
			 */
			con = JdbcUtils.getConnection();
			/*
			 * 二、准备sql模板，得到pstmt
			 */
			String sql = "update " + biao + " set Password= ? where Id=?";
			pstmt = con.prepareStatement(sql);
			/*
			 * 三、为pstmt中的问号赋值
			 */
			pstmt.setString(1, form.getEnsurePassword());
			pstmt.setString(2, form.getId());
			/*
			 * 四、执行之
			 */
			pstmt.executeUpdate();
		} catch (Exception e) {
			throw new RuntimeException(e + form.getEnsurePassword());
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
			}
		}
		return form;
	}

	@Override
	public void addUser(User form) {
		biao = form.getType();
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			/*
			 * 一、得到连接
			 */
			con = JdbcUtils.getConnection();
			/*
			 * 二、准备sql模板，得到pstmt
			 */
			String sql = "INSERT INTO " + biao + " VALUES(?,?)";
			pstmt = con.prepareStatement(sql);
			/*
			 * 三、为pstmt中的问号赋值
			 */
			pstmt.setString(1, form.getName());
			pstmt.setString(2, form.getPassword());

			/*
			 * 四、执行之
			 */
			pstmt.executeUpdate();
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
			}
		}
	}

	@Override
	public User findByUsername(User form) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		biao = form.getType();
		try {
			/*
			 * 一、得到连接
			 */
			con = JdbcUtils.getConnection();
			/*
			 * 二、准备sql模板，得到pstmt
			 */
			String sql = "SELECT * FROM " + biao + " WHERE Id=?";
			pstmt = con.prepareStatement(sql);
			/*
			 * 三、为pstmt中的问号赋值
			 */
			pstmt.setString(1, form.getId());
			/*
			 * 四、执行之
			 */
			rs = pstmt.executeQuery();
			/*
			 * 五、把rs转换成User类型，返回！
			 */
			if (rs == null) {
				return null;
			}
			if (rs.next()) {
				// 转换成User对象，并返回
				// ORM --> 对象关系映射！ hibernate!是另一个关系管理工具
				User user = new User();
				user.setId(rs.getString("Id"));
				user.setPassword(rs.getString("Password"));
				user.setName(rs.getString("Name"));
				return user;
			} else {
				return null;
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
			}
		}
	}

	/**
	 * 发口令，，即将一条记录存入数据库表中 先删除，后存入
	 * 
	 * @param form
	 */
	@Override
	public Word putWord(Word word) {
		Connection con = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmts = null;
		try {
			/*
			 * 一、得到连接
			 */
			con = JdbcUtils.getConnection();
			/*
			 * 二、准备sql模板，得到pstmt
			 */
			String sql_a = "delete from word where id=?";
			pstmt = con.prepareStatement(sql_a);
			pstmt.setString(1, word.getId());
			pstmt.executeUpdate();
			/**
			 * 生成随机六位数
			 */
			String a = LiuWeiShu.randomWord();

			String sql = "INSERT INTO word VALUES(?,?)";
			pstmts = con.prepareStatement(sql);
			pstmts.setString(1, word.getId());
			pstmts.setString(2, a);
			pstmts.executeUpdate();
			Word w = new Word();
			w.setWord(a);
			return w;
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
					pstmts.close();
				}
				if (con != null)
					con.close();
			} catch (SQLException e) {
			}
		}
	}

	/**
	 * 学生签到，搜索数据库的一条数据 凭教师id 搜索
	 */
	@Override
	public Word getWord(Word word) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			/*
			 * 一、得到连接
			 */
			con = JdbcUtils.getConnection();
			/*
			 * 二、准备sql模板，得到pstmt
			 */
			String sql = "SELECT * FROM word WHERE id=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, "1001001001");
			rs = pstmt.executeQuery();
			if (rs == null) {
				return null;
			}
			if (rs.next()) {
				// 转换成User对象，并返回
				// ORM --> 对象关系映射！ hibernate!是另一个关系管理工具
				Word w = new Word();
				w.setStudent_id(rs.getString("Id"));
				w.setWord(rs.getString("word"));
				return w;
			} else {
				return null;
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (con != null)
					con.close();
			} catch (SQLException e) {
			}
		}
	}

	@Override
	public void addLeave(Leave form) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			/*
			 * 一、得到连接
			 */
			con = JdbcUtils.getConnection();
			/*
			 * 二、准备sql模板，得到pstmt
			 */
			String sql = "INSERT INTO qingjia VALUES(?,?,?,?,?,?,?,?)";
			pstmt = con.prepareStatement(sql);
			/*
			 * 三、为pstmt中的问号赋值
			 */
			pstmt.setString(1, form.getLeaveId());
			pstmt.setString(2, form.getEmployeeId());
			pstmt.setString(3, form.getEmployeeName());
			pstmt.setString(4, form.getReason());
			pstmt.setString(5, form.getStartTime());
			pstmt.setString(6, form.getEndTime());
			pstmt.setString(7, form.getDay());
			pstmt.setString(8, form.getApprovePerson());
			/*
			 * 四、执行之
			 */
			pstmt.executeUpdate();
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
			}
		}
	}

	/**
	 * 查学生个人出勤表
	 */
	@Override
	public ArrayList<Record> selectRecordBySId(Record record) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			/*
			 * 一、得到连接
			 */
			con = JdbcUtils.getConnection();
			/*
			 * 二、准备sql模板，得到pstmt
			 */
			String sql = "SELECT * FROM record_s WHERE SId=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, record.getId());
			rs = pstmt.executeQuery();
			if (rs == null) {
				return null;
			}
			/**
			 * 一行记录转存成 record结构化信息-->出勤 多行记录转存成 ArrayList线性表
			 */
			ArrayList<Record> list = new ArrayList<Record>();
			Record record_s = new Record();
			while (rs.next()) {
				record_s.setKaoqin_id(rs.getString("KaoQin_Id"));
				record_s.setTime(rs.getString("Time"));
				record_s.setS_id(rs.getString("SId"));
				record_s.setT_id(rs.getString("TId"));
				record_s.setCourse_id(rs.getString("CourseId"));
				record_s.setStauts(rs.getString("status_s"));
				record_s.setWeek(rs.getString("week"));
				record_s.setTerm(rs.getString("term"));
				record_s.setYear(rs.getString("year"));
				list.add(record_s);
				System.out.println(rs);
			}
			return list;
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (con != null)
					con.close();
			} catch (SQLException e) {
			}
		}
	}

	/**
	 * 查教师个人出勤表
	 */
	@Override
	public ArrayList<Record> selectRecordByTId(Record record) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			/*
			 * 一、得到连接
			 */
			con = JdbcUtils.getConnection();
			/*
			 * 二、准备sql模板，得到pstmt
			 */
			String sql = "SELECT * FROM record_t WHERE TId=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, record.getId());
			rs = pstmt.executeQuery();
			if (rs == null) {
				return null;
			}
			/**
			 * 一行记录转存成 record结构化信息-->出勤 多行记录转存成 ArrayList线性表
			 */
			ArrayList<Record> list = new ArrayList<Record>();
			while (rs.next()) {
				record.setKaoqin_id(rs.getString("Chuqin_id"));
				record.setTime(rs.getString("Time"));
				record.setT_id(rs.getString("TId"));
				// record.setCourse_id(rs.getString("CourseId"));
				record.setStauts(rs.getString("status_t"));
				record.setMonth(rs.getString("month"));
				record.setYear(rs.getString("year"));
				list.add(record);
				System.out.println("数据库" + list.toString());
			}
			return list;
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (con != null)
					con.close();
			} catch (SQLException e) {
			}
		}
	}

	/**
	 * 查看课程表，二维显示
	 */
	@Override
	public HashMap<String, String> getCourseTimemap() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		HashMap<String, String> hashmap = new HashMap<String, String>();
		try {
			con = JdbcUtils.getConnection();

			String sql = "SELECT * FROM  course_timemap";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();

			if (rs == null) {
				return null;
			}
			while (rs.next()) {
				hashmap.put(rs.getString("timemap"), rs.getString("course_id"));
			}
			return hashmap;
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
			}
		}
	}

	@Override
	public ArrayList<Course> selectKeBiaoByTeacherId(int teacher_id) {
		// TODO 自动生成的方法存根
		return null;
	}
/*
 * @Override public String selectRecordBysId(Record record) { Connection con =
 * null; PreparedStatement pstmt = null; ResultSet rs = null; try {
 * 
 * 一、得到连接
 * 
 * con = JdbcUtils.getConnection();
 * 
 * 二、准备sql模板，得到pstmt
 * 
 * String sql ="SELECT * FROM record_s WHERE SId=?"; pstmt =
 * con.prepareStatement(sql); pstmt.setString(1, record.getId()); rs =
 * pstmt.executeQuery(); if(rs == null) { return null; } String
 * string=Jsonstring.resultSetToJson(rs); System.out.println(string);
 *//**
	 * 一行记录转存成 record结构化信息-->出勤 多行记录转存成 ArrayList线性表
	 *//*
		 * return string ; } catch(Exception e) { throw new RuntimeException(e); }
		 * finally { try { if(pstmt != null) { pstmt.close(); } if(con != null)
		 * con.close(); } catch(SQLException e) {} } } 
		 */
}