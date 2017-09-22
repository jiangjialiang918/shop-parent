package com.zs.shop.common.util;

/**
 * id生成工厂类
 */
public interface KeyFactory {
	
	/**
	 * 根据类型名称生成主键
	 * @param typeName 类型名称
	 * @see 主键类型参见 com.comall.cybershop.common.constants.PrimaryKey
	 * @return
	 */
	public int generate(String typeName);

	/**
	 * 根据PO生成主键
	 * @param obj
	 * @return
	 */
	public int generate(Object obj);
	
	/**
	 * 根据类型名称生成主键
	 * @param typeName 类型名称
	 * @see 主键类型参见 com.comall.cybershop.common.constants.PrimaryKey
	 * @return
	 */
	public Long generateForLong(String typeName);

}
