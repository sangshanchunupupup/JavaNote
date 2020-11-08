package com.example.controller;

import com.example.entity.News;
import com.example.entity.User;
import com.example.service.NewsService;
import com.example.service.NewsTypeService;
import com.example.service.UserService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * @author sangshanchun
 * @brief
 * @date 2020/10/31 10:06
 */
@Controller
@RequestMapping("/news")
public class NewsController {
	@Autowired
	private UserService userService;
	@Autowired
	private NewsService newsService;
	@Autowired
	private NewsTypeService newsTypeService;

	@ResponseBody
	@PostMapping("/login")
	public Integer login(@RequestBody User user) {
		User user1 = userService.selectWithLogin(user);
		if (null != user1) {
			return user1.getId();
		} else {
			return -1;
		}
	}

	@ResponseBody
	@PostMapping("/selectByCondition")
	public PageInfo<News> selectByCondition(@RequestBody News news,
											@RequestParam(value = "currentPage", defaultValue = "1", required = false) Integer currentPage,
											@RequestParam(value = "pageSize", defaultValue = "5", required = false) Integer pageSize) {
		List<News> newsList = newsService.selectByCondition(news, currentPage, pageSize);
		return new PageInfo<>(newsList);
	}

	@ResponseBody
	@PostMapping("/selectByPage/{pageNum}")
	public PageInfo<News> selectByPage(@PathVariable(value = "pageNum", required = false) Integer currentPage,
									   @RequestParam(value = "pageSize", defaultValue = "5", required = false) Integer pageSize) {
		List<News> newsList = newsService.selectByPage(currentPage, pageSize);
		return new PageInfo<>(newsList);


	}

	@ResponseBody
	@PostMapping("/del/{id}")
	public Integer del(@PathVariable("id") Integer id) {
		return newsService.deleteByPrimaryKey(id);
	}

	@ResponseBody
	@PostMapping("/check/{uid}/{id}")
	public Integer check(@PathVariable("uid") Integer uid, @PathVariable("id") Integer id) {
		News news = newsService.selectByPrimaryKey(id);
		news.setAddTime(new Date());
		// 2表示已审核
		news.setStatus(2);
		news.setReviewUser(uid);
		return newsService.updateByPrimaryKeySelective(news);
	}
}
