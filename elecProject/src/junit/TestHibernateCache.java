package junit;


import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

import elec.domain.ElecSystemDDL;

public class TestHibernateCache {

	/**测试类级别的二级缓存*/
	@SuppressWarnings("unused")
	@Test
	public void testClassCache(){
		Configuration configuration = new Configuration();
		//默认加载类路径下的hibernate.cfg.xml，同时加载映射文件
		configuration.configure();
		SessionFactory sf = configuration.buildSessionFactory();
		
		Session s = sf.openSession();
		Transaction tr = s.beginTransaction();
		
		ElecSystemDDL ddl1 = (ElecSystemDDL)s.get(ElecSystemDDL.class, 15);//存在
		
		ElecSystemDDL ddl2 = (ElecSystemDDL)s.get(ElecSystemDDL.class, 15);//没有，从Session的一级缓存中
		
		System.out.println("ddl1,ddl2这两个对象的id一样");
		
		tr.commit();
		s.close();
		/*******************************************/
		s = sf.openSession();
		tr = s.beginTransaction();
		
		ElecSystemDDL ddl3 = (ElecSystemDDL)s.get(ElecSystemDDL.class, 15);//没有，从SessionFactory的二级缓存中，存放的散装数据

		System.out.println("ddl1,ddl3这两个对象的id不一样");
		
		tr.commit();
		s.close();
		
	}
	
	/**测试查询级别的二级缓存*/
	@Test
	public void testQueryCache(){
		Configuration configuration = new Configuration();
		//默认加载类路径下的hibernate.cfg.xml，同时加载映射文件
		configuration.configure();
		SessionFactory sf = configuration.buildSessionFactory();
		
		Session s = sf.openSession();
		Transaction tr = s.beginTransaction();
		
		Query query1 = s.createQuery("from ElecSystemDDL o where o.keyword = '性别'");
		//开启查询缓存
		query1.setCacheable(true);
		query1.list();//产生select语句
		
		tr.commit();
		s.close();
		/*******************************************/
		s = sf.openSession();
		tr = s.beginTransaction();
		
		Query query2 = s.createQuery("from ElecSystemDDL o where o.keyword = '性别'");
		//开启查询缓存
		query2.setCacheable(true);
		query2.list();//产生select语句
		
		tr.commit();
		s.close();
		
	}
}
