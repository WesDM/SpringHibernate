package com.wesdm.springhibernate.dao;

import java.util.List;

public interface BasicDao<T> {
	void save(T obj);

	List<T> findAll();
	
	void delete(Long id);
}
