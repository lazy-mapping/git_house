package elec.dao;

import java.util.List;
import elec.domain.ElecFileUpload;

public interface IElecFileUploadDao extends ICommonDao<ElecFileUpload> {
	
	public static final String SERVICE_NAME = "elec.dao.impl.ElecFileUploadDaoImpl";

	List<ElecFileUpload> findElecFileUploadByCondition(String condition,
			Object[] params);
	
	
}
