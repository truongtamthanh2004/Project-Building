package com.javaweb.repository.custom.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.springframework.stereotype.Repository;

import com.javaweb.repository.RentAreaRepository;
import com.javaweb.utils.ConnectionUtil;
import com.javaweb.utils.StringUtil;
//implements RentAreaRepository
//@Repository
public class RentAreaRepositoryImpl {
	//@Override
	public String findAreaByBuildingId(Long buildingId) {
		// TODO Auto-generated method stub
		if (buildingId != null) {
			String sql = "SELECT * FROM rentarea where buildingid = " + buildingId;
			String area = "";
			
			try (
					Connection conn = ConnectionUtil.getConnection(); 
					Statement stm = conn.createStatement(); 
					ResultSet rs = stm.executeQuery(sql)
				) {
				while (rs.next()) {
					area += (rs.getString("value") + ",");
				}
				area = area.substring(0, area.length() - 1);
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			return area;
		}
		
		return null;
	}
}
