package com.juaracoding.main.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class DepartmentRowMapper implements RowMapper<InfoDepartment> {
	
	@Override
	public InfoDepartment mapRow(ResultSet rs, int rowNum)throws SQLException{
		return new InfoDepartment(rs.getString("department"), rs.getInt("result"));
	}

}
