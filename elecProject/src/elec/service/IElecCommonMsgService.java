package elec.service;

import elec.domain.ElecCommonMsg;


public interface IElecCommonMsgService {

	public static final String SERVICE_NAME = "elec.service.impl.ElecCommonMsgServiceImpl";

	ElecCommonMsg findElecCommonMsg();

	void saveElecCommonMsg(ElecCommonMsg elecCommonMsg);
}
