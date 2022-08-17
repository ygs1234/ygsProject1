package com.acorn.dadockProject.controller;

import java.net.URLEncoder;
import java.util.List;


import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.acorn.dadockProject.dto.Book;
import com.acorn.dadockProject.dto.Paging;
import com.acorn.dadockProject.dto.ReadBook;
import com.acorn.dadockProject.mapper.ReadBookMapper;
import com.acorn.dadockProject.service.BookApiCallService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
@Controller
@RequestMapping("/book")
public class BookController {
	@Autowired
	private ReadBookMapper readBookMapper;
	@Autowired
	BookApiCallService bookApiCallService;
	@Autowired
	ObjectMapper objectMapper;

	
	@GetMapping("/register")		
	public void register() {
		
	}
	
//	@GetMapping("/detail/{isbn}")
//	public String detail(@PathVariable String isbn) {
//		return "/book/detail";
//	}
	@PostMapping("/detail")
	public String detail(Book book, Model model) {
		String isbn=book.getIsbn();
		String title=book.getTitle();
		String author=book.getAuthor();
		String image=book.getImage();
		String publisher=book.getPublisher();
		String pubdate=book.getPubdate();
		String description=book.getDescription();
		String link=book.getLink();
		
		System.out.println(isbn);		
		System.out.println(title);		
		System.out.println(author);		
		System.out.println(image);		
		System.out.println(publisher);		
		System.out.println(pubdate);		
		System.out.println(description);		
		System.out.println(link);		

		model.addAttribute(isbn);
		model.addAttribute(title);
		model.addAttribute(author);
		model.addAttribute(image);
		model.addAttribute(publisher);
		model.addAttribute(pubdate);
		model.addAttribute(description);
		model.addAttribute(link);
		
		return "/book/detail";
	}
	
	@CrossOrigin
	@GetMapping("/recommend")
	public String list(Model model) {
		List<ReadBook> recommendList=readBookMapper.selectAll();
		model.addAttribute("recommendList",recommendList);
		System.out.println(recommendList);
		return "/book/recommend";
	}
	
	@GetMapping("/search")
	public void search() {
		
	}
	
	@GetMapping("/searchList/{page}")
	public String searchList(@PathVariable int page, @RequestParam String text, Model model) throws Exception {
		JSONArray naver_result_arr=new JSONArray();

		int display=30;
		int start=((page-1)*display)+1;
		int row=display;
		
		String query=URLEncoder.encode(text, "UTF-8");
		
		JSONObject naver_result = bookApiCallService.get("https://openapi.naver.com/"
				+ "v1/search/book.json?start="+start+"&display="+display+"&query="+query);
		naver_result_arr=(JSONArray) naver_result.get("items");
		
		
		// JSONArray 파싱
		String jsonBookArray=naver_result.get("items").toString();
		
		
		List<Book> bookList=objectMapper.readValue(jsonBookArray, new TypeReference<List<Book>>(){});
		
		Long total_long=(Long)naver_result.get("total");
		int total=Math.toIntExact(total_long);
		int rowCount=total;
		Paging paging = new Paging(page, rowCount, "/book/searchList/", row); //질문
		
		model.addAttribute("paging", paging);
		model.addAttribute("rowCount", rowCount);
		model.addAttribute("page",page);
		model.addAttribute("text", text);
		model.addAttribute("total", total);
		model.addAttribute("bookList", bookList);
		
		return "/book/searchList";
		
	}
}
