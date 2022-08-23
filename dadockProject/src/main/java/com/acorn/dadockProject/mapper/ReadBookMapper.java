package com.acorn.dadockProject.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.acorn.dadockProject.dto.Library;
import com.acorn.dadockProject.dto.ReadBook;

@Mapper
public interface ReadBookMapper {
	   public List<ReadBook> selectByUserRecommendAll();
	   public List<ReadBook> selectByAppAll();
	   public ReadBook selectOne(@Param(value="readbook") int readbook);
	   public int updateOne(@Param(value ="readBook") ReadBook readBook);
	   public int insertOne(ReadBook readBook);
	   public Library selectStar(String isbn);
	   
	}
