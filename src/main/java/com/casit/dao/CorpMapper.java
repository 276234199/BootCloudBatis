package com.casit.dao;

import org.apache.ibatis.annotations.Param;

import com.casit.entity.PO.Corp;

/**
* 类说明:
* @author zhouhai
* @version 创建时间：2018年9月5日 上午10:07:42
*/
public interface CorpMapper {

	Corp getCorpByID(@Param("id")Integer id);
}
