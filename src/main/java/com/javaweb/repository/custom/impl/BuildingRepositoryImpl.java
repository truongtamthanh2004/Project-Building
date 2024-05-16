package com.javaweb.repository.custom.impl;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import com.javaweb.builder.BuildingSearchBuilder;
import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.custom.BuildingRepositoryCustom;
import com.javaweb.repository.entity.BuildingEntity;
import com.javaweb.utils.ConnectionUtil;
import com.javaweb.utils.StringUtil;

@Repository
public class BuildingRepositoryImpl implements BuildingRepositoryCustom{
	@PersistenceContext
	private EntityManager entityManager;
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
		
		Query query = entityManager.createNativeQuery(sql, BuildingEntity.class);
		
		return query.getResultList();
	}
}
