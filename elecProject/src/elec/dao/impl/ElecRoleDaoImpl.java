package elec.dao.impl;

import org.springframework.stereotype.Repository;

import elec.dao.IElecRoleDao;
import elec.domain.ElecRole;


@Repository(IElecRoleDao.SERVICE_NAME)
public class ElecRoleDaoImpl extends CommonDaoImpl<ElecRole> implements IElecRoleDao {
	
}
