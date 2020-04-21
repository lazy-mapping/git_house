package elec.service;

import java.util.List;

import elec.domain.ElecOverhaulRecord;

public interface IElecRepairService {
	public static final String SERVICE_NAME = "elec.service.impl.ElecRepairServiceImpl";

	List<ElecOverhaulRecord> findRepairListByCondition(ElecOverhaulRecord record);
 
	ElecOverhaulRecord findElecRepairByID(ElecOverhaulRecord record);
	  
	  
	  void deleteRepairByIds(ElecOverhaulRecord record);
	  
	  void updateRepair(ElecOverhaulRecord record);  
	 
}
