package elec.dao.impl;

import org.springframework.stereotype.Repository;

import elec.dao.IElecDeviceDao;
import elec.domain.ElecDevice;


@Repository(IElecDeviceDao.SERVICE_NAME)
public class ElecDeviceDaoImpl extends CommonDaoImpl<ElecDevice> implements IElecDeviceDao {
	
}
