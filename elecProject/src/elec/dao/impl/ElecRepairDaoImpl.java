package elec.dao.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

import elec.dao.IElecRepairDao;
import elec.domain.ElecOverhaulRecord;
import elec.utils.PageInfo;


@Repository(IElecRepairDao.SERVICE_NAME)
public class ElecRepairDaoImpl extends CommonDaoImpl<ElecOverhaulRecord> implements IElecRepairDao{

   final String hql = "SELECT a.SeqID as SeqID, a.DevID as DevID, a.repairState as repairState, a.StartDate as StartDate, a.Record as Record, a.IsHaving as IsHaving, " + 
			"b.DevName as DevName, b.JctID as jctID, b.UseDate as UseDate, b.Remrk as Remrk, b.AdjustPeriod as AdjustPeriod, b.JctUnitID as jctUnitID " + 
			"FROM Elec_Overhaul_Record  a " + 
			"join Elec_Device  b "+
			"WHERE b.DevID = a.DevID ";
	
	/**
	 * 设备表，检修记录表----联合查询----检修记录List
	 * hql 不支持on,替换成 where， 
	 */
	public List<ElecOverhaulRecord> findRepairCollectionByConditionWithPage(String condition,Object[] params,
			Map<String, String> orderby, PageInfo pageInfo) {
			
			//解析map集合，获取排序的语句
			String orderbyhql = this.orderby(orderby);
			String finalHql = hql + condition + orderbyhql ;
			@SuppressWarnings({ "unchecked", "rawtypes" })
			List<ElecOverhaulRecord> list = (List<ElecOverhaulRecord>) this.getHibernateTemplate().execute(new HibernateCallback() {

				public Object doInHibernate(Session session)
						throws HibernateException, SQLException {
					//Query query = session.createQuery(finalHql);
					Query query = session.createSQLQuery(finalHql).addEntity(ElecOverhaulRecord.class);//关键步骤
					if(params!=null && params.length>0){
						for(int i=0;i<params.length;i++){
							query.setParameter(i, params[i]);
						}
					}
					/**添加分页 begin*/
					pageInfo.setTotalResult(query.list().size());//先获取检索数据的总记录数
					query.setFirstResult(pageInfo.getBeginResult());//当前页从第几条开始检索，0表示第1条记录（默认值）
					query.setMaxResults(pageInfo.getPageSize());//当前页最多显示多少条记录
					/**添加分页 end*/
					return query.list();
				}
			});
			return list;
	}

	/**
	 * SeqID(包含 DevID)----联合查询----检修记录
	 */
	public ElecOverhaulRecord findrecordByID(String seqID) {
		
		String finalHql = hql + " AND a.SeqID="+seqID;
		@SuppressWarnings({ "unchecked", "rawtypes" })
		ElecOverhaulRecord record = (ElecOverhaulRecord) 
					this.getHibernateTemplate().execute(new HibernateCallback() {
						
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				Query query = session.createSQLQuery(finalHql).addEntity(ElecOverhaulRecord.class);//关键步骤
				//return query.list();
				return query.uniqueResult();//只有一个对象时，使用
			}
		});
		return record;
	}


	
}
