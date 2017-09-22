package com.zs.shop.user.service;

import java.util.List;

import com.zs.shop.common.util.Page;
import com.zs.shop.common.util.PageResult;
import com.zs.shop.user.dto.UserDTO;

public interface IUserService {
	
	public List<UserDTO> findAll();
	
	public UserDTO getById(long id);
	
	public boolean add(UserDTO userDTO);
	
	public PageResult<UserDTO> findPage(Page page);

	public boolean update(UserDTO userDTO);

	
		
}
