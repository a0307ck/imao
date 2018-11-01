package com.imao.code;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;


/**
 * 基于通用MyBatis Mapper插件的Service接口的实现
 */
public abstract class AbstractService<T> implements Service<T> {

    @Autowired
    protected DiyMapper<T> mapper;

    public void save(T model) {
        mapper.insert(model);
    }
    
    public void deleteById(Long id) {
        mapper.deleteByPrimaryKey(id);
    }


    public void update(T model) {
        mapper.updateByPrimaryKey(model);
    }

    public T findById(Long id) {
        return mapper.selectByPrimaryKey(id);
    }
    
    public List<T> findAll() {
        return mapper.selectAll();
    }
    public List<T> findByEntity(T model){
    	return mapper.selectByEntity(model);
    }
}
