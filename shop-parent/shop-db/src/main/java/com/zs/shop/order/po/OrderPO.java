package com.zs.shop.order.po;

import com.zs.shop.common.annotation.table;
import com.zs.shop.common.base.AbstractItem;

@table(name="tb_order")
public class OrderPO {
	
    /**
	 * 
	 */
	//private static final long serialVersionUID = 1L;

	/**
     * 主键标识
     */
    private long id;
    
    private String name;
    
    private int status;
	
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
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
