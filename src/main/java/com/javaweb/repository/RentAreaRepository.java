package com.javaweb.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.javaweb.repository.entity.BuildingEntity;
import com.javaweb.repository.entity.RentAreaEntity;

public interface RentAreaRepository extends JpaRepository<RentAreaEntity, Long>{
//	public String findAreaByBuildingId(Long buildingId);
	void deleteAllByBuilding(BuildingEntity buildingEntity);
}
