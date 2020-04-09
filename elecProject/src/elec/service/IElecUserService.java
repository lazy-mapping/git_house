package elec.service;

import java.util.ArrayList;
import java.util.List;

import elec.domain.ElecUser;



public interface IElecUserService {

	public static final String SERVICE_NAME = "elec.service.impl.ElecUserServiceImpl";

	List<ElecUser> findUserListByCondition(ElecUser elecUser);

	String checkUser(String logonName);

	void saveUser(ElecUser elecUser);

	ElecUser findElecUserByID(ElecUser elecUser);

	void deleteUserByIds(ElecUser elecUser);

	ElecUser findElecUserByLogonName(String name);

	String findPopedomByLogonName(String name);
	
	ArrayList<String> findExcelFieldName();

	ArrayList<ArrayList<String>> findExcleFieldData(ElecUser elecUser);

	void saveUserFromExcel(List<ElecUser> list);

}
