package com.ibaixiong.bbs.dao;


import org.apache.ibatis.annotations.Param;

import com.ibaixiong.entity.SignHistory;

public interface SignHistoryDao {
	Long deleteByPrimaryKey(Long id);

	Long insertSelective(SignHistory record);

    SignHistory selectByPrimaryKey(Long id);

    Long updateByPrimaryKeySelective(SignHistory record);
    
    SignHistory checkSign(@Param("userId") Long userId,@Param("year") Integer year,@Param("month") Integer month ,@Param("day") Integer day);
}