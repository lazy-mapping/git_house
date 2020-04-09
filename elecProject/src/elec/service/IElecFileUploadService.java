package elec.service;

import java.util.List;

import elec.domain.ElecFileUpload;



public interface IElecFileUploadService {
	public static final String SERVICE_NAME = "elec.service.impl.ElecFileUploadServiceImpl";

	List<ElecFileUpload> findFileUploadList(ElecFileUpload elecFileUpload);

	void save(ElecFileUpload elecFileUpload);

	ElecFileUpload findFileUploadByID(ElecFileUpload elecFileUpload);

	void deleteFileUploadByID(ElecFileUpload elecFileUpload);

	List<ElecFileUpload> findFileUploadListByCondition(
			ElecFileUpload elecFileUpload);

}
