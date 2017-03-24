package com.ibaixiong.bbs.dao;

import java.util.List;

import com.ibaixiong.entity.Grade;

public interface GradeDao {
    Long deleteByPrimaryKey(Long id);

    Long insertSelective(Grade record);

    Grade selectByPrimaryKey(Long id);

    Long updateByPrimaryKeySelective(Grade record);

    List<Grade> getAll();
}