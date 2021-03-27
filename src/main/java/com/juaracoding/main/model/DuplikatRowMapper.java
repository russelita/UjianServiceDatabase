package com.juaracoding.main.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class DuplikatRowMapper implements RowMapper<CariDuplikat> {
	
	@Override
	public CariDuplikat mapRow(ResultSet rs, int rowNum)throws SQLException{
		
		return new CariDuplikat(rs.getString("first_name"), rs.getString("last_name"), rs.getInt("salary"), rs.getInt("jumlah"));
	}

}
