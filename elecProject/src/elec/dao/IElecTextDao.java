package elec.dao;

import elec.domain.ElecText;

public interface IElecTextDao extends ICommonDao<ElecText >{
	public static final String SERVICE_NAME ="elec.dao.impl.ElecTextDaoImpl";
}