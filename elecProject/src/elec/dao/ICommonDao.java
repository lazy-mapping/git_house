package elec.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public interface ICommonDao <T>{
	/**
	 * 底层封装的方法
	 */
	void save(T entityT);
	void update(T entity);
	T findObjectByID(Serializable id);
	void deleteObjectByIds(Serializable... ids);
	void deleteObjectByCollection(List<T> list);
	
	List<T> findCollectionByConditionNoPage(String condition,Object[] params, Map<String, String> orderby);
	List<T> findCollectionByConditionNoPageCache(String condition, Object[] params,Map<String, String> orderby);
	List findCollectionByConditionNoPageWithSelectCondition(String condition,
			Object[] params, Map<String, String> orderby, String selectCondition);
}
