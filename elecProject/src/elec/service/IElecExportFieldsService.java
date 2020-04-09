package elec.service;

import elec.domain.ElecExportFields;



public interface IElecExportFieldsService {

	public static final String SERVICE_NAME = "elec.service.impl.ElecExportFieldsServiceImpl";

	ElecExportFields findElecExportFields(String belongTo);

	void saveSetExportExcel(ElecExportFields elecExportFields);

}
