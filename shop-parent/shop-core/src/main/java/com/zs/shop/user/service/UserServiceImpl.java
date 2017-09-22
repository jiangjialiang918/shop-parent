package com.zs.shop.user.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zs.shop.common.util.Page;
import com.zs.shop.common.util.PageResult;
import com.zs.shop.user.dto.UserDTO;
import com.zs.shop.user.po.UserPO;
import com.zs.shop.user.storage.IUserDBStorage;

@Service
public class UserServiceImpl implements IUserService{

	@Autowired
	private IUserDBStorage userDBStorage;
	
	@Override
	public List<UserDTO> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserDTO getById(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean add(UserDTO userDTO) {
		if(userDTO != null){
			UserPO userPO = new UserPO();
			BeanUtils.copyProperties(userDTO, userPO);
			userDBStorage.add(userPO);
		}
		return false;
	}

	@Override
	public PageResult<UserDTO> findPage(Page page) {
		PageResult<UserPO> userPOResult = userDBStorage.getUserPage(page);
		PageResult<UserDTO> result = null ;
		if(userPOResult != null){
			List<UserPO> userPOList = userPOResult.getResult();
			if(userPOList != null && userPOList.size() > 0 ){
				UserDTO userDTO = null ;
				List<UserDTO> userDTOList = new ArrayList<UserDTO>();
				for(UserPO userPO : userPOList){
					userDTO = new UserDTO();
					BeanUtils.copyProperties(userPO, userDTO);
					userDTOList.add(userDTO);
				}
				result = new PageResult<UserDTO>(page, userDTOList);
			}
		}
		return result;
	}

	@Override
	public boolean update(UserDTO userDTO) {
		// TODO Auto-generated method stub
		return false;
	}

}
