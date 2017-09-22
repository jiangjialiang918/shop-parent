package com.zs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zs.shop.common.util.Page;
import com.zs.shop.common.util.PageResult;
import com.zs.shop.user.dto.UserDTO;
import com.zs.shop.user.service.IUserService;

@Controller
@RequestMapping(value = "/user",  produces = {"application/json;charset=UTF-8"})
public class UserController  extends BaseController{
	
	@Autowired
	IUserService userServcie;
	
	@RequestMapping("userPage")
	public String getUserPage(
			@RequestParam(value = "currentNum" , defaultValue = "5", required = false) Integer currentNum,
			@RequestParam(value = "currentPage" , defaultValue = "0", required = false) Integer currentPage,
			Model model){
	    Page page = new Page(currentNum, currentPage);
	    PageResult<UserDTO> result = userServcie.findPage(page);
		model.addAttribute("result", result);
		return "user/userList";
		
	}
	
	
	@RequestMapping(value = "add", method = RequestMethod.GET)
	public String add(){
		return "user/add";		
	}
	
	@RequestMapping(value = "add", method = RequestMethod.POST)
	public String add(UserDTO userDTO){
		 boolean result = userServcie.add(userDTO);	
		 return "redirect:/user/userPage";
	}
	
	
	@RequestMapping(value = "/login")
	@ResponseBody
	public void loginByPassword() {
		//Object object = session.setAttribute("current_userId", "1");
		//String userId = (String) session.getAttribute("current_userId");
		//Object object = session.getAttribute("current_userId");
		String userId = (String) null;
		System.out.println("userId" + userId);
		//session.removeAttribute("current_userId");
		//String userId = (String) session.getAttribute("current_userId");
		//System.out.println("userId" + userId);
	}
	
}
