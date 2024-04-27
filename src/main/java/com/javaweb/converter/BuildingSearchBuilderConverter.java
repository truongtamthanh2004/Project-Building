package com.javaweb.converter;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.javaweb.builder.BuildingSearchBuilder;
import com.javaweb.utils.MapUtil;

@Component
public class BuildingSearchBuilderConverter {
	public BuildingSearchBuilder toBuildingSearchBuilder(Map<String, Object> params, List<String> typeCode) {
		BuildingSearchBuilder builder = new BuildingSearchBuilder.Builder()
																	.setName(MapUtil.getObject(params, "name", String.class))
																	.setFloorArea(MapUtil.getObject(params, "floorArea", String.class))
																	.setDistrictId(MapUtil.getObject(params, "districtId", String.class))
																	.setWard(MapUtil.getObject(params, "ward", String.class))
																	.setStreet(MapUtil.getObject(params, "street", String.class))
																	.setNumberOfBasement(MapUtil.getObject(params, "numberOfBasement", String.class))
																	.setDirection(MapUtil.getObject(params, "direction", String.class))
																	.setLevel(MapUtil.getObject(params, "level", String.class))
																	.setAreaFrom(MapUtil.getObject(params, "areaFrom", String.class))
																	.setAreaTo(MapUtil.getObject(params, "areaTo", String.class))
																	.setRentPriceFrom(MapUtil.getObject(params, "rentPriceFrom", String.class))
																	.setRentPriceTo(MapUtil.getObject(params, "rentPriceTo", String.class))
																	.setManagerName(MapUtil.getObject(params, "managerName", String.class))
																	.setManagerPhoneNumber(MapUtil.getObject(params, "managerPhoneNumber", String.class))
																	.setStaffId(MapUtil.getObject(params, "staffId", String.class))
																	.setTypeCode(typeCode)
																	.build();
		return builder;
	}
}
