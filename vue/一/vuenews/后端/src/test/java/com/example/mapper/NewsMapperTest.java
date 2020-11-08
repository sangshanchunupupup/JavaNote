package com.example.mapper;

import com.example.entity.News;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class NewsMapperTest {
	@Autowired
	private NewsMapper newsMapper;
	@Test
	public void testSelectByPage() {
		List<News> newsList = newsMapper.selectByPage(1, 2);
		newsList.forEach(e -> {
			System.out.println(e);
		});
	}
}