package com.zs.shop.user.storage;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.zs.shop.base.DaoSupport;
import com.zs.shop.common.util.KeyFactory;
import com.zs.shop.common.util.Page;
import com.zs.shop.common.util.PageResult;
import com.zs.shop.user.po.UserPO;
@Repository
public class UserDBStorageImpl implements IUserDBStorage {

	@Autowired
	DaoSupport daoSupport;
	
	@Autowired
	KeyFactory keyFactory;
	
	@Override
	public PageResult<UserPO> getUserPage(Page page) {
		String sql = "select * from tb_user where 1 = 1 " ;
		String totalNum = "select count(id) as totalcount from tb_user where 1 = 1 " ;
		List<Object> values = new ArrayList<Object>();
		Map<String, Object> map = page.getQuery();
		int count = daoSupport.count(totalNum, values.toArray());
		page.setTotalNum(count);
		sql = sql + " limit ?,? ";
		values.add(page.startNum());
		values.add(page.getCurrentNum());
		List<UserPO> listPO = new ArrayList<UserPO>();
		if(count > 0){
			listPO = daoSupport.queryForList(sql,UserPO.class,values.toArray());
		}
		return new PageResult<UserPO>(page, listPO);
	}

	@Override
	public boolean add(UserPO userPO) {
		boolean result = false;
		if(userPO != null){
			Long id = keyFactory.generateForLong("base"); 
			userPO.setId(id);
			userPO.setCreateTime(new Date());
			result = daoSupport.add(userPO);
		}
		return result;
	}

}
