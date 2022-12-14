package com.acorn.dadockProject.dto;

import lombok.Getter;
import lombok.ToString;

@Getter@ToString
public class Paging {

	private int page; 
	private int rowCount; 
	private String url;
	private int row=10; 

	private int startRow; 
	
	private int start; 
	private int end; 
	private int firstPage=1;
	private int lastPage;
	private int previousPage;
	private int nextPage;
	
	private boolean isFirst;
	private boolean isLast;
	private boolean isPrevious;
	private boolean isNext;
	public Paging(int page, int rowCount, String url) {
		this.page=page;
		this.rowCount=rowCount;
		this.url=url;
		this.setAll();
	}
	public Paging(int page, int rowCount, String url, int row) {
		this.page=page;
		this.rowCount=rowCount;
		this.url=url;	
		this.row=row;
		this.setAll();
	}
	public void setAll() {
		this.startRow=(page-1)*this.row;
		this.lastPage=rowCount/row+((rowCount%row>0)?1:0);
		this.previousPage=page-1;
		this.nextPage=page+1;
		
		this.isFirst=(page>firstPage)?true:false;
		this.isPrevious=(page>firstPage)?true:false;

		this.isLast=(page<lastPage)?true:false;
		this.isNext=(page<lastPage)?true:false;

		
		this.start=this.page-2;
		this.end=this.page+2;   
		
		if(this.start<firstPage) {
			end=end-start+1;
			if(end<lastPage) {start=lastPage;}
			start=firstPage;
		}
		if(end>lastPage) {
			start=start-end+lastPage;
			if(start<firstPage) {start=firstPage;}
			end=lastPage;
		}	
	}
	
}








