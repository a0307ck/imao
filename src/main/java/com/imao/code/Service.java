package com.imao.code;

import java.util.List;

/**
 * Service 层 基础接口，其他Service 接口 请继承该接口
 */
public interface Service<T> {
	/**
	 * 保存
	* @author chenkang
	* @date 2018年10月30日  
	* @version 1.0
	 */
    void save(T model);
	/**
	 * 物理删除（不建议使用，软删比较适合）
	* @author chenkang
	* @date 2018年10月30日  
	* @version 1.0
	 */
    void deleteById(Long id);
	/**
	 * 更新
	* @author chenkang
	* @date 2018年10月30日  
	* @version 1.0
	 */
    void update(T model);
	/**
	 * 根据主键查询信息
	* @author chenkang
	* @date 2018年10月30日  
	* @version 1.0
	 */
    T findById(Long id);
	/**
	 * 查询所有
	* @author chenkang
	* @date 2018年10月30日  
	* @version 1.0
	 */
    List<T> findAll();
	/**
	 * 根据查询条件查询对应的信息
	* @author chenkang
	* @date 2018年10月30日  
	* @version 1.0
	 */
    List<T> findByEntity(T model);
}
