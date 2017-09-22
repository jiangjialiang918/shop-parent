package com.zs.shop.order.dto;

public class ResourcesDTO {
	
	/**
	 * 主键标识
	 */
	private long id;
	private String name;
	private int parentId;
	private String resURL;
	
	
	public int getParentId() {
		return parentId;
	}
	public void setParentId(int parentId) {
		this.parentId = parentId;
	}
	public String getResURL() {
		return resURL;
	}
	public void setResURL(String resURL) {
		this.resURL = resURL;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
	
}
