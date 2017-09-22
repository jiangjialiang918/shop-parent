package com.zs.shop.user.storage;

import com.zs.shop.common.util.Page;
import com.zs.shop.common.util.PageResult;
import com.zs.shop.user.po.UserPO;

public interface IUserDBStorage {

	public PageResult<UserPO> getUserPage(Page page);
	
	public boolean add(UserPO userPO);

	
}
