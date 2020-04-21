package elec.dao;

import elec.domain.ElecDevice;

public interface IElecDeviceDao extends ICommonDao<ElecDevice> {

	public static final String SERVICE_NAME = "elec.dao.impl.ElecDeviceDaoImpl";
}
