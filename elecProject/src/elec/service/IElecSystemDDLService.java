package elec.service;

import java.util.List;
import elec.domain.ElecSystemDDL;

public interface IElecSystemDDLService {

	public static final String SERVICE_NAME = "elec.service.impl.ElecSystemDDLServiceImpl";
	
	List<ElecSystemDDL> findDistinctKeywod();

	List<ElecSystemDDL> findElecSystemDDLListByKeyword(String keyword);

	void saveElecSystemDDL(ElecSystemDDL elecSystemDDL);

	String findDdlNameByKeywordAndDdlCode(String keyword, String ddlCode);

	String findDdlCodeByKeywordAndDdlName(String keyword, String ddlName);

}
