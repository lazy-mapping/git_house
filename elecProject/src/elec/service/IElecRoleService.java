package elec.service;

import java.util.List;

import elec.domain.ElecPopedom;
import elec.domain.ElecRole;
import elec.domain.ElecUser;



public interface IElecRoleService {

	public static final String SERVICE_NAME = "elec.service.impl.ElecRoleServiceImpl";

	List<ElecRole> findRoleList();

	List<ElecPopedom> findPopedomList();

	List<ElecPopedom> findPopedomListByRoleID(String roleID);

	List<ElecUser> findUserListByRoleID(String roleID);

	void saveRole(ElecPopedom elecPopedom);

	List<ElecPopedom> findShowMenu(String popedom);

	boolean findRolePopedomByID(String roleID, String mid, String pid);
}
