package com.javaweb.repository.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.entity.BuildingEntity;
import com.javaweb.utils.ConnectionUtil;

@Repository
public class BuildingRepositoryImpl implements BuildingRepository{
	public String join(Map<String, Object> params, List<String> typeCode) {
		String sql = "";
		
		if (params != null && !params.isEmpty()) {
			String areaFrom = (String)params.get("areaFrom");
			String areaTo = (String)params.get("areaTo");
			String staffId = (String)params.get("staffId");
			
			if ((areaFrom != null && !areaFrom.equals("")) && (areaTo != null && !areaTo.equals(""))) {
				sql += " JOIN rentarea on rentarea.buildingid = building.id ";
			}
			if (staffId != null && !staffId.equals("")) {
				sql += " JOIN assignmentbuilding on assignmentbuilding.buildingid = building.id ";
			}
		}
		
		if (typeCode != null && typeCode.size() > 0) {
			sql += " JOIN buildingrenttype on buildingrenttype.buildingid = building.id ";
			sql += " JOIN renttype on buildingrenttype.renttypeid = renttype.id ";
		}
		
		return sql;
	}
	
	public String where(Map<String, Object> params, List<String> typeCode) {
	    String sql = "";
	    if (params != null && !params.isEmpty()) {
	        for (Map.Entry<String, Object> x : params.entrySet()) {
	            String key = x.getKey();
	            Object objValue = x.getValue();
	            if (key != null && objValue != null) {
	                String value = objValue.toString();
	                if (!key.equals("staffId") && !key.startsWith("area") && !key.startsWith("rentPrice") && !key.equals("typeCode")) {
	                    if (value.matches("\\d+")) {
	                        sql += (" AND building." + key + " = " + value);
	                    } else if (!value.equals("")) {
	                        sql += (" AND building." + key + " LIKE '%" + value + "%'");
	                    }
	                }

	                if (key.equals("staffId") && !value.equals("")) {
	                    sql += (" AND assignmentbuilding.staffid = " + value);
	                }

	                if (key.equals("areaFrom") && !value.equals("")) {
	                    sql += (" AND rentarea.value >= " + value);
	                }

	                if (key.equals("areaTo") && !value.equals("")) {
	                    sql += (" AND rentarea.value <= " + value);
	                }

	                if (key.equals("rentPriceFrom") && !value.equals("")) {
	                    sql += (" AND building.rentprice >= " + value);
	                }

	                if (key.equals("rentPriceTo") && !value.equals("")) {
	                    sql += (" AND building.rentprice <= " + value);
	                }
	            }
	        }	        
	    }
	    
	    if (typeCode != null && !typeCode.isEmpty()) {
	    	String result = "IN (\'" + String.join("\',\'", typeCode) + "\')";
            sql += (" AND renttype.code " + result);
        }
	    
	    return sql;
	}

	@Override
	public List<BuildingEntity> findAll(Map<String, Object> params, List<String> typeCode) {
		// TODO Auto-generated method stub
		String sql = "SELECT distinct building.* FROM building ";
		sql += join(params, typeCode);
		String where = " WHERE 1=1 ";
		where += where(params, typeCode);
		sql += where;
		
		System.out.println(sql);
		
		List<BuildingEntity> result = new ArrayList<BuildingEntity>();
		try (
				Connection conn = ConnectionUtil.getConnection(); 
				Statement stm = conn.createStatement(); 
				ResultSet rs = stm.executeQuery(sql)
			) {
			while (rs.next()) {
				BuildingEntity building = new BuildingEntity();
				building.setId(rs.getString("id"));
				building.setName(rs.getString("name"));
				building.setStreet(rs.getString("street"));
				building.setWard(rs.getString("ward"));
				building.setDistrictId(rs.getString("districtid"));
				building.setNumberOfBasement(rs.getString("numberofbasement"));
				building.setFloorArea(rs.getString("floorarea"));
				building.setRentPrice(rs.getString("rentprice"));
				building.setRentPriceDescription(rs.getString("rentpricedescription"));
				building.setManagerName(rs.getString("managername"));
				building.setManagerPhoneNumber(rs.getString("managerphonenumber"));
				
				result.add(building);
			}
			System.out.println("Connected database successfully...");
		} catch (SQLException e) {
			e.printStackTrace();

			System.out.println("Connected database failed...");
		}
		
		return result;
	}
}
