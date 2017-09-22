package com.zs.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zs.shop.common.util.AjaxResultUtil;
import com.zs.shop.common.util.Page;
import com.zs.shop.common.util.PageResult;
import com.zs.shop.order.dto.OrderDTO;
import com.zs.shop.order.service.IOrderService;

@Controller
@RequestMapping(value = "/item",  produces = {"application/json;charset=UTF-8"})
public class ItemListController {
	
	@Autowired
	private IOrderService orderService;
	
	

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String ItemList(){
		return "/item/add";
	}
	
	
	@RequestMapping(value = "/list")
	public String ItemList2(	
			@RequestParam(value = "currentNum", defaultValue = "5", required = false) int currentNum,
			@RequestParam(value = "currentPage", defaultValue = "0", required = false) int currentPage,
			@RequestParam(value = "status", required = false) Integer status,
			Model model){
		Page page = new Page(currentNum, currentPage);
		Map<String, Object> query = new HashMap<String, Object>();
		if(status != null){
			query.put("status", status);
			model.addAttribute("status", status);
		}
		page.setQuery(query);
		/*page.setCurrentNum(currentNum);
		page.setCurrentPage(currentPage);*/
		PageResult<OrderDTO> list = orderService.findPage(page);
		model.addAttribute("orderList", list);
		return "/item/index";
	}
	
	@RequestMapping("/adds")
	@ResponseBody
	public String add(OrderDTO orderDTO){
		boolean result = orderService.add(orderDTO);
		return AjaxResultUtil.dumpResult(result);
	}
	
	@RequestMapping("/delete")
	@ResponseBody
	public String delete(){
		//boolean result = orderService.add();
		return AjaxResultUtil.dumpResult(null);
	}
	
	@RequestMapping("/update")
	@ResponseBody
	public String update(OrderDTO orderDTO){
		boolean result = orderService.update(orderDTO);
		return AjaxResultUtil.dumpResult(result);
	}
	
	@RequestMapping("/orderInfo")
	public void getOrderInfo(){
		List<OrderDTO> result = orderService.findAll();
		System.out.println(result == null);
	}
	
	@RequestMapping("/orderInfomation")
	@ResponseBody
	public boolean orderInfomation(long id){
		boolean result = orderService.refreshDate(id);
		return result;
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
