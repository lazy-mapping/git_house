package com.mybatis.mapper;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import com.mybatis.util.MyBatisUtil;
import com.mybatis.vo.Vehicle;

public class VehicleMapperTest {
	
		/**
		 * 鉴别器  动态调用时虚拟机执行子类的方法，汽车
		 * 根据表中某个字段区别数据，将查询到的数据自动封装成不同类型的对象
		 */
	   @Test
	   public void testSelectOPost() {
	   SqlSession session = MyBatisUtil.getSqlSession();
					
		VehicleMapper vehicleMapper= session.getMapper(VehicleMapper.class);
		
		Vehicle vehicle1=vehicleMapper.selectVehicleById(1);
		Vehicle vehicle3=vehicleMapper.selectVehicleById(3);		
		
		session.close();
		System.out.print(vehicle1+"\n");
		System.out.print(vehicle3);
		}	

}








