package elec.dao;

import java.util.List;
import java.util.Map;

import elec.domain.ElecOverhaulRecord;
import elec.utils.PageInfo;

public interface IElecRepairDao extends ICommonDao<ElecOverhaulRecord> {

	public static final String SERVICE_NAME = "elec.dao.impl.ElecRepairDaoImpl";

	public List<ElecOverhaulRecord> findRepairCollectionByConditionWithPage(String condition, Object[] params,
			Map<String, String> orderby,PageInfo pageInfo);

	public ElecOverhaulRecord findrecordByID(String seqID);

	//public void updateRepair(ElecOverhaulRecord record);


}
