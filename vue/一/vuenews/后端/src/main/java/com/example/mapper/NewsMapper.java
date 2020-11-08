package com.example.mapper;

import com.example.entity.News;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface NewsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(News record);

    int insertSelective(News record);

    News selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(News record);

    int updateByPrimaryKey(News record);

    List<News> selectByCondition(@Param("news1") News news1,
                                 @Param("currentPage") Integer currentPage,
                                 @Param("pageSize") Integer pageSize);

    List<News> selectByPage
            (@Param("currentPage") Integer currentPage, @Param("pageSize") Integer pageSize);
}