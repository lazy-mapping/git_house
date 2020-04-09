package elec.service;

import java.util.List;

import elec.domain.ElecText;

public interface IElecTextService {
	public static final String SERVICE_NAME ="elec.service.impl.ElecTextServiceImpl";
	
	public void save(ElecText elecText);
	
	public List<ElecText> findCollectionByConditionNoPage(ElecText elecText);
}
