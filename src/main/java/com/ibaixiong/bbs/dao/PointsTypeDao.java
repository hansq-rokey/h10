package com.ibaixiong.bbs.dao;

import java.util.List;

import com.ibaixiong.entity.PointsType;

public interface PointsTypeDao {
	Long deleteByPrimaryKey(Long id);

    Long insertSelective(PointsType record);

    PointsType selectByPrimaryKey(Long id);

    Long updateByPrimaryKeySelective(PointsType record);

    List<PointsType> getAll();
    
    PointsType getPointsTypeByTagName(String tagName);
}