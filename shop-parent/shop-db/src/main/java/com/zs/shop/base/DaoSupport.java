package com.zs.shop.base;



import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.zs.shop.common.util.ObjectSQLUtil;
import com.zs.shop.order.po.OrderPO;


@Component
public class DaoSupport {

	public final String DEFAULT_COUNT_COLUMN_NAME = "totalcount";

	@Autowired
	private JdbcTemplate jdbcTemplate;
	private Logger log = Logger.getLogger(getClass());

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	
	public <T> List<T> queryForList(Class<T> elementType, String querySql,
			Object... args) {
		String sql = new StringBuffer(ObjectSQLUtil.getQuery(elementType))
				.append(" where true ")
				.append(querySql != null ? querySql : "").toString();
		return queryForList(sql, elementType, args);
	}
	
	

	/**
	 * 返回Bean
	 * @author ZhangShuai
	 * @date 创建时间：2017年4月9日 上午12:09:59
	 * @param sql
	 * @param elementType
	 * @param args
	 * @return
	 */
	public <T> T queryForObject(String sql, Class<T> elementType,
			Object... args) {
		try {
			return jdbcTemplate.queryForObject(sql, args,
					new BeanPropertyRowMapper<T>(elementType));
		} catch (EmptyResultDataAccessException e) {
			return null;
		} catch (Exception e) {
			log.debug(sql);
			log.error("数据库异常" + e);
		}
		return null;
	}


	public boolean add(Object object) {
		String sql = ObjectSQLUtil.getAdd(object);
		return update(sql, ObjectSQLUtil.getAddParameters(object));
	}
	
	/**
	 * update
	 * @author ZhangShuai
	 * @date 创建时间：2017年4月9日 上午12:11:29
	 * @param sql
	 * @param args
	 * @return
	 */
	public boolean update(String sql, Object... args) {
		try {
			return jdbcTemplate.update(sql, args) > 0?true : false;
		} catch (DataAccessException e) {
			log.debug(sql);
			log.error("数据库异常" + e);
			e.printStackTrace();
		}
		return false;
	}
	
	public <T> List<T> queryForList(String sql, Class<T> elementType,
			Object... args) {
		try {
			return jdbcTemplate.query(sql, args, new BeanPropertyRowMapper<T>(
					elementType));
		} catch (Exception e) {
			log.debug(sql);
			e.printStackTrace();
			return null;
		}
	}
	public <T> List<T> queryForList2(String sql, Class<T> elementType,
			Object... args) {
		try {
			return jdbcTemplate.query(sql, args, new BeanPropertyRowMapper<T>(
					elementType));
		} catch (Exception e) {
			log.debug(sql);
			e.printStackTrace();
			return null;
		}
	}
	
	
	public <T> List<T> queryForList(String querySql, Class<T> elementType){
		return jdbcTemplate.queryForList(querySql, elementType);
	}
	
	


	public int count(String sql, Object... args) {
		return count(sql, DEFAULT_COUNT_COLUMN_NAME, args);
	}
	
	private int count(String sql, final String countColumnName, Object... args) {
		Integer key = jdbcTemplate.queryForObject(sql, args,
				new RowMapper<Integer>() {
					@Override
					public Integer mapRow(ResultSet rs, int rowNum) {
						try {
							return rs.getInt(countColumnName);
						} catch (SQLException e) {
							log.error("SQL异常" + e);
						}
						return 0;
					}
				});
		return key;
	}
	
	public boolean update(Object obj) {
		String sql = ObjectSQLUtil.getUpdate(obj);
		return update(sql, ObjectSQLUtil.getUpdateParameters(obj));
	}
	
	
}
