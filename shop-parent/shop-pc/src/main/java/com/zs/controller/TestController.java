package com.zs.controller;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zs.shop.common.util.AjaxResultUtil;
import com.zs.shop.order.dto.OrderDTO;
import com.zs.shop.order.service.IOrderService;

@Controller
@RequestMapping("/test")
//@RequestMapping(value = "/test",  produces = {"application/json;charset=UTF-8"})
public class TestController {
	
	@Autowired
	private IOrderService orderService;
	
	private static TestController testController; 
	
    public void setOrderService(IOrderService orderService){ 
    	this.orderService = orderService; 
    } 
    public IOrderService getOrderService(){ 
    	return	orderService; 
    } 
    
    /**
     * @PostConstruct修饰的方法会在服务器加载Servlet的时候运行，
     * 并且只会被服务器执行一次。PostConstruct在构造函数之后执行,init()方法之前运行。
     * @author ZhangShuai
     * @date 创建时间：2017年4月6日 下午2:09:44
     */
    @PostConstruct
    public void init(){ 
    	testController = this; 
    	testController.orderService = this.orderService; 
    } 

	/*
	 * 1：
	 *    @ResponseBody
	 * 	     表示该方法的返回结果直接写入HTTP response body中
	 *    一般在异步获取数据时使用，在使用@RequestMapping后，返回值通常解析为跳转路径，
	 *    加上@ResponseBody后返回结果不会被解析为跳转路径，而是直接写入HTTP response body中
	 * 2：
	 *      @ResponseBody 返回中文乱码
	 * 		引起乱码原因为spring mvc使用的默认处理字符串编码为ISO-8859-1
	 *      具体参考org.springframework.http.converter.StringHttpMessageConverter类中public static final Charset DEFAULT_CHARSET = Charset.forName("ISO-8859-1");
	 * 		第一种方法：在返回方法上加 produces = "application/json; charset=utf-8"
	 *      第二种方法：配置springmvc-servlet.xml
	 *       		<mvc:annotation-driven>  
	    				<mvc:message-converters register-defaults="true">  
	        			<bean class="org.springframework.http.converter.StringHttpMessageConverter">  
	            		<property name="supportedMediaTypes" value = "text/html;charset=UTF-8" />  
	        			</bean>  
	    				</mvc:message-converters>  
					</mvc:annotation-driven>
			第三种方法：在类请求路径添加 produces = {"application/json;charset=UTF-8"}
	 */
	@RequestMapping("/dbTest/{id}")
	//@RequestMapping(value="/dbTest", produces = "application/json; charset=utf-8")  
	@ResponseBody
	public String dbTest(@PathVariable(value="id") long id){
		OrderDTO orderDTO = orderService.getByProductId(id);
		return AjaxResultUtil.dumpResult(orderDTO);
	}
	
	/*
	 * @PathVariable 获取URL中的动态参数
	 */
	@RequestMapping("/dbTest1/{name}")
	public String jsonTest1(@PathVariable(value="name") String name){
		System.out.println(name);
		return name;
	}
	
	/*
	 * @RequestParam 默认request = true 
	 */
	@RequestMapping("/dbTest2")
	public String jsonTest2(
			@RequestParam(value = "name")  
			String name){
	
		System.out.println(name);
		return name;
	}
	
	@RequestMapping("/add")
	@ResponseBody
	public String add(){
		OrderDTO orderDTO1 = new OrderDTO();
		orderDTO1.setName("测试添加");
		boolean result1 = orderService.add(orderDTO1);
		boolean result2 = orderService.add(new OrderDTO());
		
		return AjaxResultUtil.dumpResult(result1);
	}
	
	
	@RequestMapping("/dbTest3")
	@ResponseBody
	public String dbTest3(){
		return AjaxResultUtil.dumpResult(getInfo());
	}
	
	private static OrderDTO getInfo(){
		OrderDTO orderDTO = testController.orderService.getByProductId(1);
		System.out.println(orderDTO.getName());
		return orderDTO;
	}
	/**
	 * TODO 文件上传
	 */
	
	/**
	 * TODO 导入导出EXCEL
	 */
	
	/**
	 * 
	 */
	
	
}
