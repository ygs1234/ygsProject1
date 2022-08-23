package com.acorn.dadockProject.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.acorn.dadockProject.dto.Book;
import com.acorn.dadockProject.dto.Library;

@Mapper
public interface LibraryMapper {
	
	List<Library> selectAll();
	List<Library> selectByIdAll(String user_id);
	Library selectOne(int libraryNo);
	Library selectOneByIsbn(String  isbn);
	int insertBook(Book book);
	int insertOne(Library library);
	int updateOne(Library library);
	int deleteOne(int libraryNo);
}
