package com.zs.shop.order.service;



import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zs.shop.common.util.KeyFactory;
import com.zs.shop.common.util.Page;
import com.zs.shop.common.util.PageResult;
import com.zs.shop.order.dto.OrderDTO;
import com.zs.shop.order.po.OrderPO;
import com.zs.shop.order.storage.IOrderDBStorage;

@Service
public class OrderServiceImpl implements IOrderService {

	@Autowired
	IOrderDBStorage orderDBStorage;
	
	@Autowired
	KeyFactory keyFactory;
	
	@Override
	public OrderDTO getByProductId(long productId) {
		OrderPO orderPO = orderDBStorage.getByProductId(productId);
		OrderDTO orderDTO = new OrderDTO();
		orderDTO.setId(orderPO.getId());
		orderDTO.setName(orderPO.getName());
		return orderDTO;
	}

	@Override
	public boolean add(OrderDTO orderDTO) {
		if(orderDTO != null){
			OrderPO orderPO = new OrderPO();
			Long id =keyFactory.generateForLong("base");
			orderPO.setId(id);
			orderPO.setName(orderDTO.getName());
			orderPO.setStatus(orderDTO.getStatus());
			return orderDBStorage.add(orderPO);
		}
		return false;
	}

	@Override
	public List<OrderDTO> findAll() {
		List<OrderPO> orderPOList = orderDBStorage.findAll();
		List<OrderDTO> orderDTOList = new ArrayList<OrderDTO>(); 
		/*if(orderPOList != null && orderPOList.size() > 0){*/
		if(orderPOList.size() > 0){
			for(OrderPO orderPO : orderPOList){
				OrderDTO orderDTO = new OrderDTO();
				BeanUtils.copyProperties(orderPO, orderDTO);
				orderDTOList.add(orderDTO);
			}
		}
		return orderDTOList;
	}

	@Override
	public PageResult<OrderDTO> findPage(Page page) {
		PageResult<OrderPO> pageResultPO = orderDBStorage.findPage(page);
		List<OrderDTO> orderDTOList = new ArrayList<OrderDTO>(); 
		if(pageResultPO != null && pageResultPO.getResult() != null
				&& pageResultPO.getResult().size() >0){
			List<OrderPO> orderPOList= pageResultPO.getResult();
			for(OrderPO orderPO : orderPOList){
				OrderDTO orderDTO = new OrderDTO();
				BeanUtils.copyProperties(orderPO, orderDTO);
				orderDTOList.add(orderDTO);
			}
		}
		return new PageResult<OrderDTO>(page, orderDTOList);
	}

	@Override
	public boolean update(OrderDTO orderDTO) {
		//DTO转化为PO
		OrderPO orderPO = null;
		if(orderDTO != null){
			BeanUtils.copyProperties(orderPO, orderDTO);
		}
		return orderDBStorage.update(orderPO);
	}

	@Override
	public boolean updateStatus(int status, int id) {
		return orderDBStorage.updateStatus(status, id);
	}

	@Override
	public List<OrderDTO> findBystatus(int status) {
		List<OrderPO> orderList = orderDBStorage.findByStatus(status);
		return null;
	}

	@Override
	public boolean refreshDate(long objId) {
		boolean result = true;
		List<OrderPO> orderList = orderDBStorage.findByStatus(objId);
		if(orderList.size() > 0 ){
			for(OrderPO order : orderList){
				long status = order.getStatus();
			    result = orderDBStorage.deleteByObjId(status);
			}
		}
		return result;
	}

	
	


	

}
