package elec.dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

import elec.dao.IElecSystemDDLDao;
import elec.domain.ElecSystemDDL;


@Repository(IElecSystemDDLDao.SERVICE_NAME)
public class ElecSystemDDLDaoImpl extends CommonDaoImpl<ElecSystemDDL> implements IElecSystemDDLDao {

	/**  
	* @Name: findDistinctKeywod
	* @Description: 获取去掉重复值的数据类型
	* @Return: List<ElecSystemDDL>：封装数据类型的集合
	*/
	@SuppressWarnings("unchecked")
	public List<ElecSystemDDL> findDistinctKeywod() {
		
		List<ElecSystemDDL> sysList = new ArrayList<ElecSystemDDL>();
		/**
		 *  投影查询1 直接类名查询（hql/sql）
		 *    查询部分字段，返回List<Object>
		 *    查询多个字段，返回List<Object[]>  
		*/  
		//   这是普通方案
//		String hql = "SELECT DISTINCT o.keyword FROM ElecSystemDDL o"; // DISTINCT 关键词用于返回列中唯一的值
//		List<Object> list = this.getHibernateTemplate().find(hql);
//		if(list!=null && list.size()>0){
//			for(Object o:list){
//				ElecSystemDDL elecSystemDDL = new ElecSystemDDL();
//				elecSystemDDL.setKeyword(o.toString());
//				sysList.add(elecSystemDDL);
//			}
//		}

		//投影查询2返回实体类（hql），直接将查出的结果封装进javabean中，但JavaBean需要接收值的构造方法
		String hql = "SELECT DISTINCT new elec.domain.ElecSystemDDL(o.keyword) FROM ElecSystemDDL o";
		sysList = this.getHibernateTemplate().find(hql);
		return sysList;
	}

	/**  
	* @Name: findDdlNameByKeywordAndDdlCode
	* @Description: 使用数据类型和数据项的编号获取数据项的值
	* @Parameters: String keyword：数据类型
	*              String ddlCode：数据项的编号
	* @Return: String数据项的值
	*/

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public String findDdlNameByKeywordAndDdlCode(final String keyword, final String ddlCode) {
		final String hql = "select o.ddlName from ElecSystemDDL o where o.keyword = ? and o.ddlCode = ?";
		List<Object> list = (List<Object>) this.getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				Query query = session.createQuery(hql);
				query.setParameter(0, keyword);
				query.setParameter(1, Integer.parseInt(ddlCode));
				//开启二级缓存
				query.setCacheable(true);
				return query.list();
			}			
		});
		//数据项的值
		String ddlName = "";
		if(list!=null && list.size()>0){
			Object o = list.get(0);
			ddlName = o.toString();
		}
		return ddlName;
	}

	/**  
	* @Name: findDdlCodeByKeywordAndDdlName
	* @Description: 使用数据类型和数据项的值获取数据项的编号
	* @Parameters: String keyword：数据类型
	*              String ddlName：数据项的值
	* @Return: String数据项的编号
	*/
	public String findDdlCodeByKeywordAndDdlName(final String keyword, final String ddlName) {
		final String hql = "select o.ddlCode from ElecSystemDDL o where o.keyword = ? and o.ddlName = ?";
		@SuppressWarnings({ "unchecked", "rawtypes" })
		List<Object> list = (List<Object>) this.getHibernateTemplate().execute(new HibernateCallback() {

			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				Query query = session.createQuery(hql);
				query.setParameter(0, keyword);
				query.setParameter(1, ddlName);
				query.setCacheable(true);//开启二级缓存
				return query.list();
			}
			
		});
		//数据项的值
		String ddlCode = "";
		if(list!=null && list.size()>0){
			Object o = list.get(0);
			ddlCode = o.toString();
		}
		return ddlCode;
	}
}
