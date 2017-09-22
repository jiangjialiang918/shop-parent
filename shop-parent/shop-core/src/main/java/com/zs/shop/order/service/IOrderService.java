package com.zs.shop.order.service;

import java.util.List;

import com.zs.shop.common.util.Page;
import com.zs.shop.common.util.PageResult;
import com.zs.shop.order.dto.OrderDTO;

public interface IOrderService {
	
	public List<OrderDTO> findAll();
	
	public OrderDTO getByProductId(long productId);
	
	public boolean add(OrderDTO orderDTO);
	
	public PageResult<OrderDTO> findPage(Page page);

	public boolean update(OrderDTO orderDTO);

	public boolean updateStatus(int status, int id);
	
	public List<OrderDTO> findBystatus(int status);
	
	public boolean refreshDate(long objId);
	
}
