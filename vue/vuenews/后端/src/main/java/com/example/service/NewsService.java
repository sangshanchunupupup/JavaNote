package com.example.service;

import com.example.entity.News;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author sangshanchun
 * @brief
 * @date 2020/10/31 9:58
 */
public interface NewsService {
	int deleteByPrimaryKey(Integer id);

	int insert(News record);

	int insertSelective(News record);

	News selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(News record);

	int updateByPrimaryKey(News record);

	List<News> selectByCondition(News news, Integer currentPage, Integer pageSize);

	List<News> selectByPage(Integer currentPage, Integer pageSize);
}
