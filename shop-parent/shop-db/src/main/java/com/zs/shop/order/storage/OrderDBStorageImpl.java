package com.zs.shop.order.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.zs.shop.base.DaoSupport;
import com.zs.shop.common.util.Page;
import com.zs.shop.common.util.PageResult;
import com.zs.shop.order.po.OrderPO;
@Repository
public class OrderDBStorageImpl implements IOrderDBStorage {

	@Autowired
	private DaoSupport dao;
	  
	@Override
	public OrderPO getByProductId(long productId) {
		String querySQL = "select * from tb_order  where id = ?";
		return dao.queryForObject(querySQL, OrderPO.class, productId);
	}

	@Override
	public boolean add(OrderPO orderPO) {
		return dao.add(orderPO);
	}

	@Override	
	public List<OrderPO> findAll() {
		String sql = "select * from tb_order order by id desc ";
		return dao.queryForList2(sql, OrderPO.class);
	}

	@Override
	public PageResult<OrderPO> findPage(Page page) {
		StringBuffer querySql = new StringBuffer("select * from tb_order where true  ");
		StringBuffer countSql = new StringBuffer("select count(id) as totalcount from tb_order where true ");
		List<Object> values = new ArrayList<Object>();
		List<OrderPO> list = null;
		int totalcount = 0;
		Map<String, Object> query = page.getQuery();
		if(query.containsKey("status")){
			querySql.append(" and status = ?");
			countSql.append(" and status = ?");
			values.add(query.get("status"));
		}
		querySql.append(" ORDER BY id desc  limit ?,? ");
		totalcount = dao.count(countSql.toString(), values.toArray());
		page.setTotalNum(totalcount);
		//new Page(totalcount, page.getTotalPage());
		values.add(page.startNum());
		values.add(page.getCurrentNum());
        if(totalcount > 0){
        	list = dao.queryForList2(querySql.toString(), OrderPO.class, values.toArray());
        }else{
        	list = new ArrayList<OrderPO>();
        }
		return new PageResult<OrderPO>(page, list);
	}

	@Override
	public boolean update(OrderPO t) {
		return dao.update(t);
	}

	@Override
	public boolean updateStatus(int status, int id) {
		String sql = "update uni_order set status = ? where id = ? ";
		return dao.update(sql, status, id);
	}

	@Override
	public List<OrderPO> findByStatus(long id) {
		String sql = " and id = ? ";
		return dao.queryForList(OrderPO.class, sql,  id);
	}

	@Override
	public boolean deleteByObjId(long objId) {
		String sql = "delete from tb_order where status = ? ";
		return dao.update(sql, objId);
	}
    
	
	


}
