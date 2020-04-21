package elec.service;

import java.util.List;

import elec.domain.ElecDevice;

public interface IElecDeviceService {

	public static final String SERVICE_NAME = "elec.service.impl.ElecDeviceServiceImpl";

	List<ElecDevice> findDeviceListByCondition(ElecDevice elecDevice);

	ElecDevice findElecDeviceByID(ElecDevice elecDevice);

	void saveDevice(ElecDevice elecDevice);

	void deleteDeviceByIds(ElecDevice elecDevice);

	void updateDevice(ElecDevice elecDevice);



}
