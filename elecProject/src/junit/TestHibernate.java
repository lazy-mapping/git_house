package junit;

import org.junit.Test;

import elec.domain.ElecText;

import org.hibernate.Transaction;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class TestHibernate{
	
	/**测试保存*/
	@Test
	public void save() {
		Configuration configuration = new Configuration();
		configuration.configure();//加载classpath下的hibernate.cfg.xml的文件
		SessionFactory sf = configuration.buildSessionFactory();
		Session s = sf.openSession();
		Transaction tr = s.beginTransaction();
		
		ElecText elecText = new ElecText();
		elecText.setTextName("测试hibernate的名称");
		elecText.setTextDate(new Date());
		elecText.setTextRemark("测试hibernate备注");
		s.save(elecText);
		
		tr.commit();//手动提交
		s.close();
	}
	
}