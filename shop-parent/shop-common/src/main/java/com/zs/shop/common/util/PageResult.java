package com.zs.shop.common.util;

import java.util.List;

public class PageResult<T> {

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
	
	private List<T> result;
	
	
	
	public List<T> getResult() {
		return result;
	}


	public void setResult(List<T> result) {
		this.result = result;
	}


	public PageResult(int currentPage, int currentNum, List<T> result){
		this.currentPage = currentPage;
		this.currentNum = currentNum;
		this.result = result;
		//calculatePage();
	}
	
	public PageResult(int currentPage, int currentNum, int totalNum, List<T> result){
		this.currentPage = currentPage;
		this.currentNum = currentNum;
		this.totalNum = totalNum;
		this.result = result;
		//calculatePage();
	}
	
	
	public PageResult(Page page, List<T> result) {
		this.currentPage = page.getCurrentPage();
		this.currentNum = page.getCurrentNum();
		this.totalNum = page.getTotalNum();
		this.totalPage = page.getTotalPage();
		this.result = result;	
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


	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}


	public int getTotalPage() {
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
	 
	
	
	
}
