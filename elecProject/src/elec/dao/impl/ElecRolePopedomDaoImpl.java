package elec.dao.impl;

import org.springframework.stereotype.Repository;

import elec.dao.IElecRolePopedomDao;
import elec.domain.ElecRolePopedom;


@Repository(IElecRolePopedomDao.SERVICE_NAME)
public class ElecRolePopedomDaoImpl extends CommonDaoImpl<ElecRolePopedom> implements IElecRolePopedomDao {
	
}
