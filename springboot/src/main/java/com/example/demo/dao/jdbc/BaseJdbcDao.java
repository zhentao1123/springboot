package com.example.demo.dao.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

public class BaseJdbcDao {
	
	@Autowired
	@Qualifier("primaryJdbcTemplate")
	private JdbcTemplate jdbcTemplateP;
	
	@Autowired
	@Qualifier("secondaryJdbcTemplate")
	private JdbcTemplate jdbcTemplateS;
	
	protected JdbcTemplate getJdbcTemplatePrimary(){
		return this.jdbcTemplateP;
	}
	
	protected JdbcTemplate getJdbcTemplateSecondary(){
		return this.jdbcTemplateS;
	}
	
	protected <T> RowMapper<T> getRowMapper(Class<T> clazz){
		return BeanPropertyRowMapper.newInstance(clazz);
	}
	
	protected String appenderStr(String... lines) throws Exception{
		StringBuilder sb = new StringBuilder();
		for(String line : lines){
			sb.append(line);
		}
		return sb.toString();
	}
}
