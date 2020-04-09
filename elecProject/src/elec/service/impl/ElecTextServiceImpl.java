package elec.service.impl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import elec.dao.IElecTextDao;
import elec.domain.ElecText;
import elec.service.IElecTextService;

@Transactional(readOnly = true)//类级别,数据库只读，为了安全
@Service(IElecTextService.SERVICE_NAME)
public class ElecTextServiceImpl implements IElecTextService{
	
	@Resource(name = IElecTextDao.SERVICE_NAME)
	private IElecTextDao elecTextDao;
	
	@Transactional(readOnly = false)//方法级别可写入，增删改
	@Override
	public void save(ElecText elecText) {
		elecTextDao.save(elecText);
	}

	/**指定查询条件，查询列表*/
	/**
	 * SELECT * FROM elec_text o WHERE 1=1     #Dao层
		AND o.textName LIKE '%张%'   #Service层
		AND o.textRemark LIKE '%张%'   #Service层
		ORDER BY o.textDate ASC,o.textName DESC  #Service层
	 */
	@Override
	public List<ElecText> findCollectionByConditionNoPage(ElecText elecText) {
		//查询条件
				String condition = "";
				//查询条件对应的参数
				List<Object> paramsList = new ArrayList<Object>();
				if(StringUtils.isNotBlank(elecText.getTextName())){
					condition += " and o.textName like ?";
					paramsList.add("%"+elecText.getTextName()+"%");
				}
				if(StringUtils.isNotBlank(elecText.getTextRemark())){
					condition += " and o.textRemark like ?";
					paramsList.add("%"+elecText.getTextRemark()+"%");
				}
				//传递可变参数
				Object [] params = paramsList.toArray();
				//排序
				Map<String, String> orderby = new LinkedHashMap<String, String>();//有序
				orderby.put("o.textDate", "asc");
				orderby.put("o.textName", "desc");
				//查询
				List<ElecText> list = elecTextDao.findCollectionByConditionNoPage(condition,params,orderby);
				return list;
	}

}
