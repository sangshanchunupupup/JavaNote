package com.example.entity;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class News {
    private Integer id;

    private String title;

    private String context;
    /**
     * 关联news_type表id
     */
    private Integer type;

    private String typeName;

    private String img;

    private Date addTime;
    /**
     * status
     * 1 未审核
     * 2 已审核
     */
    private Integer status;

    private Date reviewTime;

    private Integer reviewUser;

}