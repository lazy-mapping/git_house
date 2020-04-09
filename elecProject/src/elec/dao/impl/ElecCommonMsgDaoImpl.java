package elec.dao.impl;

import org.springframework.stereotype.Repository;

import elec.dao.IElecCommonMsgDao;
import elec.domain.ElecCommonMsg;


@Repository(IElecCommonMsgDao.SERVICE_NAME)
public class ElecCommonMsgDaoImpl extends CommonDaoImpl<ElecCommonMsg> implements IElecCommonMsgDao {
	
}
