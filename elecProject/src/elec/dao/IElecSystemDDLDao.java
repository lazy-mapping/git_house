package elec.dao;

import java.util.List;
import elec.domain.ElecSystemDDL;

public interface IElecSystemDDLDao extends ICommonDao<ElecSystemDDL> {

	public static final String SERVICE_NAME = "elec.dao.impl.ElecSystemDDLDaoImpl";

	List<ElecSystemDDL> findDistinctKeywod();
	
	String findDdlNameByKeywordAndDdlCode(String keyword, String ddlCode);
	
	String findDdlCodeByKeywordAndDdlName(String keyword, String ddlName);
}
