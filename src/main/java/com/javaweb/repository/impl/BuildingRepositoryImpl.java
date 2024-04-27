package com.javaweb.repository.impl;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.javaweb.builder.BuildingSearchBuilder;
import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.entity.BuildingEntity;
import com.javaweb.utils.ConnectionUtil;
import com.javaweb.utils.StringUtil;

@Repository
public class BuildingRepositoryImpl implements BuildingRepository{
	public String join(BuildingSearchBuilder builder) {
		String sql = "";
		
		String areaFrom = builder.getAreaFrom();
		String areaTo = builder.getAreaTo();
		String staffId = builder.getStaffId();
		
		if (StringUtil.checkString(areaFrom) || StringUtil.checkString(areaTo)) {
			sql += " JOIN rentarea on rentarea.buildingid = building.id ";
		}
		if (StringUtil.checkString(staffId)) {
			sql += " JOIN assignmentbuilding on assignmentbuilding.buildingid = building.id ";
		}
		
		List<String> typeCode = builder.getTypeCode();
		if (typeCode != null && typeCode.size() > 0) {
			sql += " JOIN buildingrenttype on buildingrenttype.buildingid = building.id ";
			sql += " JOIN renttype on buildingrenttype.renttypeid = renttype.id ";
		}
		
		return sql;
	}
	
	public String whereNormal(BuildingSearchBuilder builder) {
		String sql = "";
		
		try {
			Field[] fields = BuildingSearchBuilder.class.getDeclaredFields();
			for (Field item : fields) {
				item.setAccessible(true);
				String fieldName = item.getName();
				if (!fieldName.equals("staffId") && !fieldName.startsWith("area") && !fieldName.startsWith("rentPrice") && !fieldName.equals("typeCode")) {
					if (item.get(builder) != null) {
						String value = item.get(builder).toString();
						if (StringUtil.checkString(value)) {
							if (value.matches("\\d+")) {
								sql += (" AND building." + fieldName + " = " + value);
							}
							else if (item.getType().getName().equals("java.lang.Integer")) {
								sql += (" AND building." + fieldName + " = " + value);
							}
							else if (item.getType().getName().equals("java.lang.String")) {
								sql += (" AND building." + fieldName + " LIKE '%" + value + "%'");
							}
						}
					}
				}
			}
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		
		return sql;
	}
	
	public String whereSpecial(BuildingSearchBuilder builder) {
		String sql = "";
		
		String staffId = builder.getStaffId();
		if (StringUtil.checkString(staffId)) {
			sql += (" AND assignmentbuilding.staffid = " + staffId);
		}
		
		String areaFrom = builder.getAreaFrom();
		String areaTo = builder.getAreaTo();
		if (StringUtil.checkString(areaFrom)) {
            sql += (" AND rentarea.value >= " + areaFrom);
        }
        if (StringUtil.checkString(areaTo)) {
            sql += (" AND rentarea.value <= " + areaTo);
        }
        
        String rentPriceFrom = builder.getRentPriceFrom();
		String rentPriceTo = builder.getRentPriceTo();
        if (StringUtil.checkString(rentPriceFrom)) {
            sql += (" AND building.rentprice >= " + rentPriceFrom);
        }
        if (StringUtil.checkString(rentPriceTo)) {
            sql += (" AND building.rentprice <= " + rentPriceTo);
        }
        
        List<String> typeCode = builder.getTypeCode();
	    if (typeCode != null && !typeCode.isEmpty()) {
	    	sql += " AND (";
	    	String sqlJoin = typeCode.stream().map(item -> "renttype.code LIKE '%" + item + "%'").collect(Collectors.joining(" OR "));
	    	sql += (sqlJoin + ") ");
	    }
		
		return sql;
	}

	@Override
	public List<BuildingEntity> findAll(BuildingSearchBuilder builder) {
		// TODO Auto-generated method stub
		String sql = "SELECT distinct building.* FROM building ";
		sql += join(builder);
		String where = " WHERE 1=1 ";
		where += whereNormal(builder);
		where += whereSpecial(builder);
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
