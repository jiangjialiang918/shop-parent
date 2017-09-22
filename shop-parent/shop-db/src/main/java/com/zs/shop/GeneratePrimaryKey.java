package com.zs.shop;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.zs.shop.common.annotation.table;
import com.zs.shop.common.util.KeyFactory;

@Component
public class GeneratePrimaryKey implements KeyFactory {



	@Autowired
	private JdbcTemplate jdbc;
	
	public int generate(String typeName) {
		StringBuffer sql = new StringBuffer("select nextval('");
		sql.append(typeName);
		sql.append("') id");
		return getKey(sql.toString());
	}

	public int generate(Object obj) {
		StringBuffer sql = new StringBuffer("select nextval('");
		sql.append(obj.getClass().getAnnotation(table.class).name());
		sql.append("') id");
		return getKey(sql.toString());
	}
	
	private int getKey(String sql){
		Integer key = jdbc.queryForObject(sql, new RowMapper<Integer>(){
			@Override
			public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
				return rs.getInt("id");
			}
		});
		return key;
	}
	
	private long getLongKey(String sql){
		Long key = jdbc.queryForObject(sql, new RowMapper<Long>(){
			@Override
			public Long mapRow(ResultSet rs, int rowNum) throws SQLException {
				return rs.getLong("id");
			}
		});
		return key;
	}

	@Override
	public Long generateForLong(String typeName) {
		StringBuffer sql = new StringBuffer("select nextval('");
		sql.append(typeName);
		sql.append("') id");
		return getLongKey(sql.toString());
	}
}
