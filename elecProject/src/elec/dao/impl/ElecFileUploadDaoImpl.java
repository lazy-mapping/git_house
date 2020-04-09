package elec.dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

import elec.dao.IElecFileUploadDao;
import elec.domain.ElecFileUpload;


@Repository(IElecFileUploadDao.SERVICE_NAME)
public class ElecFileUploadDaoImpl extends CommonDaoImpl<ElecFileUpload> implements IElecFileUploadDao {

	/**指定查询条件，获取资料图纸的信息，sql语句进行查询*/
	public List<ElecFileUpload> findElecFileUploadByCondition(String condition,
			final Object[] params) {
		List<ElecFileUpload> uploadList = new ArrayList<ElecFileUpload>();
		
		final String sql = "SELECT a.seqID as seqid,b.ddlName as jctname,c.ddlName as picname,a.fileName as filename,a.fileURL as fileurl,a.comment as comment " +
					 " FROM Elec_FileUpload a " +
					 " JOIN  Elec_SystemDDL b  ON b.DdlCode=a.ProjID  AND b.keyword='所属单位'" +
					 " JOIN  Elec_SystemDDL c  ON c.ddlCode=a.BelongTo  AND c.keyword='图纸类别' " +
					 " WHERE 1=1" + condition;
		@SuppressWarnings({ "unchecked", "rawtypes" })
		List<Object[]> list = (List<Object[]>) this.getHibernateTemplate().execute(new HibernateCallback(){

			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				//使用hibernate支持的sql语句的标量查询，将查询字段的结果转换成对应的值，并放置在Object数组中
				Query query = session.createSQLQuery(sql)
					.addScalar("seqid",Hibernate.INTEGER)//参数一：查询字段的别名；参数二：指定封装类型
					.addScalar("jctname",Hibernate.STRING)
					.addScalar("picname",Hibernate.STRING)
					.addScalar("filename",Hibernate.STRING)
					.addScalar("fileurl",Hibernate.STRING)
					.addScalar("comment", Hibernate.STRING);
				if(params!=null && params.length>0){
					for(int i=0;i<params.length;i++){
						query.setParameter(i, params[i]);
					}
				}
				
				return query.list();
			}
			
		
		});
		//转换成对应的PO对象
		if(list!=null && list.size()>0){
			for(Object[] o:list){
				ElecFileUpload elecFileUpload = new ElecFileUpload();
				elecFileUpload.setSeqId(Integer.parseInt(o[0].toString()));
				elecFileUpload.setProjId(o[1].toString());
				elecFileUpload.setBelongTo(o[2].toString());
				elecFileUpload.setFileName(o[3].toString());
				elecFileUpload.setFileUrl(o[4].toString());
				elecFileUpload.setComment(o[5].toString());
				uploadList.add(elecFileUpload);
			}
		}
		return uploadList;
	}
}
