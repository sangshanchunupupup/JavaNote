package com.example.service.ServiceImpl;

import com.example.entity.News;
import com.example.mapper.NewsMapper;
import com.example.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author sangshanchun
 * @brief
 * @date 2020/10/31 10:00
 */
@Service
@Transactional
public class NewsServiceImpl implements NewsService {
	@Autowired
	private NewsMapper newsMapper;

	@Override
	public int deleteByPrimaryKey(Integer id) {
		return newsMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(News record) {
		return newsMapper.insert(record);
	}

	@Override
	public int insertSelective(News record) {
		return newsMapper.insertSelective(record);
	}

	@Override
	public News selectByPrimaryKey(Integer id) {
		return newsMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(News record) {
		return newsMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(News record) {
		return newsMapper.updateByPrimaryKey(record);
	}

	@Override
	public List<News> selectByCondition(News news, Integer currentPage, Integer pageSize) {
		return newsMapper.selectByCondition(news, currentPage, pageSize);
	}

	@Override
	public List<News> selectByPage(Integer currentPage, Integer pageSize) {
		return newsMapper.selectByPage(currentPage, pageSize);
	}

}
