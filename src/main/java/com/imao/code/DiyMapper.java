package com.imao.code;

import java.util.List;

public interface DiyMapper<T> {

	void insert(T model);

	void deleteByPrimaryKey(Long id);

	void updateByPrimaryKey(T model);

	T selectByPrimaryKey(Long id);

	List<T> selectAll();
	
	List<T> selectByEntity(T model);
}
