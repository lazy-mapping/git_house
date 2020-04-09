package elec.dao.impl;

import org.springframework.stereotype.Repository;

import elec.dao.IElecExportFieldsDao;
import elec.domain.ElecExportFields;


@Repository(IElecExportFieldsDao.SERVICE_NAME)
public class ElecExportFieldsDaoImpl extends CommonDaoImpl<ElecExportFields> implements IElecExportFieldsDao {
	
}
