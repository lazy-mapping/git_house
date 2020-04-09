package elec.dao;

import java.util.List;
import elec.domain.ElecUser;

public interface IElecUserDao extends ICommonDao<ElecUser> {

	public static final String SERVICE_NAME = "elec.dao.impl.ElecUserDaoImpl";

	public List<Object> findPopedomByLogonName(String name);
	
}
