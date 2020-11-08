package com.example.service.ServiceImpl;

import com.example.entity.NewsType;
import com.example.mapper.NewsTypeMapper;
import com.example.service.NewsTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author sangshanchun
 * @brief
 * @date 2020/10/31 10:00
 */
@Service
@Transactional
public class NewsTypeServiceImpl implements NewsTypeService {
	@Autowired
	private NewsTypeMapper newsTypeMapper;

	@Override
	public int deleteByPrimaryKey(Integer id) {
		return newsTypeMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(NewsType record) {
		return newsTypeMapper.insert(record);
	}

	@Override
	public int insertSelective(NewsType record) {
		return newsTypeMapper.insertSelective(record);
	}

	@Override
	public NewsType selectByPrimaryKey(Integer id) {
		return newsTypeMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(NewsType record) {
		return newsTypeMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(NewsType record) {
		return newsTypeMapper.updateByPrimaryKey(record);
	}
}
