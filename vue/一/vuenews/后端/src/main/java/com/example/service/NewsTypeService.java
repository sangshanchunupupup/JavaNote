package com.example.service;

import com.example.entity.NewsType;

/**
 * @author sangshanchun
 * @brief
 * @date 2020/10/31 9:59
 */
public interface NewsTypeService {
	int deleteByPrimaryKey(Integer id);

	int insert(NewsType record);

	int insertSelective(NewsType record);

	NewsType selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(NewsType record);

	int updateByPrimaryKey(NewsType record);
}
