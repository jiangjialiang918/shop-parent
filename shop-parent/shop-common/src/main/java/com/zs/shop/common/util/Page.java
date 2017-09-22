package com.zs.shop.common.util;

import java.util.Map;

public class Page {

	/*
	 * 当前页
	 */
	private int currentPage;
	
	/*
	 * 总页数
	 */
	private int totalPage;
	
	
	/*
	 * 总条数
	 */
	private int totalNum;
	
	/*
	 * 每页显示条数
	 */
	private int currentNum;
	
	/*
	 *查询条件 
	 */
	private Map<String, Object> query;
	
	private int type;
	
	
	
	public int getType() {
		return type;
	}


	public void setType(int type) {
		this.type = type;
	}


	public Map<String, Object> getQuery() {
		return query;
	}


	public void setQuery(Map<String, Object> query) {
		this.query = query;
	}


	public int getCurrentNum() {
		return currentNum;
	}


	public void setCurrentNum(int currentNum) {
		this.currentNum = currentNum;
	}


	public int getCurrentPage() {
		return currentPage;
	}

	public int startNum() {
		int startNum = 0;
		if(currentPage == 0){
			startNum = 0;
		}else{
			startNum = currentPage * currentNum;
		}
		return startNum;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}


	public int getTotalPage() {
		if(totalNum <= currentNum ){
			totalPage = 1;
		}else if(totalNum%currentNum > 0){
			totalPage = totalNum/currentNum +1;
		}else{
			totalPage = totalNum/currentNum;
		}
		return totalPage;
	}


	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}


	public int getTotalNum() {
		return totalNum;
	}


	public void setTotalNum(int totalNum) {
		this.totalNum = totalNum;
	}
	
	/*public Page(int totalNum, int totalPage){
		this.totalNum = totalNum;
		this.totalPage = totalPage;		
	}*/
	

	public Page(int currentNum , int currentPage){
		this.currentNum = currentNum;
		this.currentPage = currentPage;
	}
	
	/*public Page(){
	}*/
	
	
}
