package com.example.service;

import com.example.entity.User;

/**
 * @author sangshanchun
 * @brief
 * @date 2020/10/31 9:59
 */
public interface UserService {
	int deleteByPrimaryKey(Integer id);

	int insert(User record);

	int insertSelective(User record);

	User selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(User record);

	int updateByPrimaryKey(User record);

	User selectWithLogin(User user);
}
