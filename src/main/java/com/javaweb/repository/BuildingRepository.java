package com.javaweb.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;

import com.javaweb.builder.BuildingSearchBuilder;
import com.javaweb.repository.custom.BuildingRepositoryCustom;
import com.javaweb.repository.entity.BuildingEntity;

public interface BuildingRepository extends JpaRepository<BuildingEntity, Long>, BuildingRepositoryCustom {
//	List<BuildingEntity> findAll(BuildingSearchBuilder builder);
	List<BuildingEntity> findByNameContaining(String name); // like '%%'
	List<BuildingEntity> findByNameContainingAndWardContaining(String name, String ward);
	void deleteByIdIn(Long[] Ids);
}
