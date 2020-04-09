package elec.dao.impl;

import org.springframework.stereotype.Repository;

import elec.dao.IElecPopedomDao;
import elec.domain.ElecPopedom;


@Repository(IElecPopedomDao.SERVICE_NAME)
public class ElecPopedomDaoImpl extends CommonDaoImpl<ElecPopedom> implements IElecPopedomDao {
	
}
