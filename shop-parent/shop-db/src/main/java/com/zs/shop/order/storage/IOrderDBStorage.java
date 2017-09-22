package com.zs.shop.order.storage;

import java.util.List;

import com.zs.shop.common.util.Page;
import com.zs.shop.common.util.PageResult;
import com.zs.shop.order.po.OrderPO;

public interface IOrderDBStorage {

	public OrderPO getByProductId(long productId);

	public boolean add(OrderPO orderPO);

	public List<OrderPO> findAll();

	public PageResult<OrderPO> findPage(Page page);
	
	public boolean update(OrderPO orderPO);
	
	public boolean updateStatus(int status, int id);

	public List<OrderPO> findByStatus(long id);
	
	public boolean deleteByObjId(long objId);
}
