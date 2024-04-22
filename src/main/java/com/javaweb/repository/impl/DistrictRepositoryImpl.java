package com.javaweb.repository.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.springframework.stereotype.Repository;

import com.javaweb.repository.DistrictRepository;
import com.javaweb.repository.entity.DistrictEntity;
import com.javaweb.utils.ConnectionUtil;

@Repository
public class DistrictRepositoryImpl implements DistrictRepository{

	@Override
	public DistrictEntity findDistrictNameById(String id) {
		// TODO Auto-generated method stub
		
		if (id != null && !id.equals("")) {
			String sql = "SELECT * FROM district where id = " + id;
			DistrictEntity districtEntity = new DistrictEntity();
			try (
					Connection conn = ConnectionUtil.getConnection(); 
					Statement stm = conn.createStatement(); 
					ResultSet rs = stm.executeQuery(sql)
				) {
				while (rs.next()) {
					districtEntity.setName(rs.getString("name"));
					districtEntity.setCode(rs.getString("code"));
					districtEntity.setId(rs.getString("id"));
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			return districtEntity;
		}
		
		return null;
		
	}

}
