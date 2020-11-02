package com.example.mapper;

import com.example.entity.NewsType;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface NewsTypeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(NewsType record);

    int insertSelective(NewsType record);

    NewsType selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(NewsType record);

    int updateByPrimaryKey(NewsType record);

    String selectTypeNameById(Integer id);
}