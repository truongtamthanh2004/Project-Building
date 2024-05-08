package com.javaweb.repository;

import com.javaweb.repository.entity.RentAreaEntity;

public interface RentAreaRepository {
	public String findAreaByBuildingId(Long buildingId);
}
