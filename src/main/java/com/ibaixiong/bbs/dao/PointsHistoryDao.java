package com.ibaixiong.bbs.dao;

import java.util.List;
import java.util.Map;

import com.ibaixiong.entity.PointsHistory;

public interface PointsHistoryDao {
    Long deleteByPrimaryKey(Long id);

    Long insertSelective(PointsHistory record);

    PointsHistory selectByPrimaryKey(Long id);

    Long updateByPrimaryKeySelective(PointsHistory record);

    Integer getPointsHistoryCount(Map<String, Object> map);
    
    List<PointsHistory> queryPointsHistoryGroupByUserId();
}