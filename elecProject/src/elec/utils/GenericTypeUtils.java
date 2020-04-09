package elec.utils;

import java.lang.reflect.ParameterizedType;

public class GenericTypeUtils {

	/**
	  *   泛型转换类的公用方法
	  *   将泛型转换为真实类型
	 * @param entity
	 * @return
	 */
	public static Class<?> getActualType(Class<?> entity) {
		ParameterizedType parameterizedType = (ParameterizedType) entity.getGenericSuperclass();
		Class<?> entityClass = (Class<?>) parameterizedType.getActualTypeArguments()[0];
		return entityClass;
	}
	
}
