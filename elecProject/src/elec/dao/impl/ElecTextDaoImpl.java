package elec.dao.impl;

import org.springframework.stereotype.Repository;

import elec.dao.IElecTextDao;
import elec.domain.ElecText;

@Repository(IElecTextDao.SERVICE_NAME)
public class ElecTextDaoImpl extends CommonDaoImpl<ElecText> implements IElecTextDao{

}
