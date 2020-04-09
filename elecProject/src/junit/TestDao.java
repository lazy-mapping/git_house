package junit;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import elec.dao.IElecTextDao;
import elec.domain.ElecText;

public class TestDao {

	//@Test
	public void save() {
		//加载spring容器
		ApplicationContext ac = new ClassPathXmlApplicationContext("beans.xml");
		IElecTextDao elecTextDao = (IElecTextDao) ac.getBean(IElecTextDao.SERVICE_NAME);
		
		ElecText elecText = new ElecText();
		elecText.setTextName("测试Dao名称");
		elecText.setTextDate(new Date());
		elecText.setTextRemark("测试Dao备注");
		elecTextDao.save(elecText);
		((AbstractApplicationContext) ac).close();
		/**
		 * 没有手动提交事务，在hibrenate.cfg.xml中设置自动提交事务
		 */
	}
	/**更新*/
	//@Test
	public void update(){
		//加载spring容器
		ApplicationContext ac = new ClassPathXmlApplicationContext("beans.xml");
		IElecTextDao elecTextDao = (IElecTextDao) ac.getBean(IElecTextDao.SERVICE_NAME);
		
		ElecText elecText = new ElecText();
		elecText.setTextID("402809816ed080fb016ed08108b50005");
		elecText.setTextName("王五");
		elecText.setTextDate(new Date());
		elecText.setTextRemark("王小五");
		elecTextDao.update(elecText);
		((AbstractApplicationContext) ac).close();
	}
	
	/**使用主键ID查询对象的对象*/
	@Test
	public void findObjectById(){
		//加载spring容器
		ApplicationContext ac = new ClassPathXmlApplicationContext("beans.xml");
		IElecTextDao elecTextDao = (IElecTextDao) ac.getBean(IElecTextDao.SERVICE_NAME);
		Serializable id = "402809816ed0823d016ed0823fe50001";
		ElecText elecText = elecTextDao.findObjectByID(id);
		System.out.println(elecText.getTextName()+"    "+elecText.getTextRemark());
		((AbstractApplicationContext) ac).close();
	}
	
	/**删除（使用主键ID删除）*/
	//@Test
	public void deleteObjectByIds(){
		//加载spring容器
		ApplicationContext ac = new ClassPathXmlApplicationContext("beans.xml");
		IElecTextDao elecTextDao = (IElecTextDao) ac.getBean(IElecTextDao.SERVICE_NAME);
		//Serializable [] ids = {"402895ef49f42c000149f42c0faf0001","402895ef49f42db20149f42db7d10001"};
		Serializable ids = "402809816ed080fb016ed08108b50005";
		elecTextDao.deleteObjectByIds(ids);
		((AbstractApplicationContext) ac).close();
	}
	
	/**删除（使用集合List进行删除）*/
	//@Test
	public void deleteObjectByCollection(){
		//加载spring容器
		ApplicationContext ac = new ClassPathXmlApplicationContext("beans.xml");
		IElecTextDao elecTextDao = (IElecTextDao) ac.getBean(IElecTextDao.SERVICE_NAME);
		List<ElecText> list = new ArrayList<ElecText>();
		ElecText elecText1 = new ElecText();
		elecText1.setTextID("40283f816ea76245016ea76247b40001");
		
		ElecText elecText2 = new ElecText();
		elecText2.setTextID("40283f816ea76a79016ea76a7b0f0001");
		
		ElecText elecText3 = new ElecText();
		elecText3.setTextID("40283f816ea76af1016ea76af36b0001");
		
		list.add(elecText1);
		list.add(elecText2);
		list.add(elecText3);
		
		elecTextDao.deleteObjectByCollection(list);
		((AbstractApplicationContext) ac).close();
	}
}
