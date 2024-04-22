package com.javaweb.service;

import java.util.List;
import java.util.Map;

import com.javaweb.DTO.BuildingDTO;

public interface BuildingService {
	List<BuildingDTO> findBuildingDTO(Map<String, Object> params, List<String> typeCode);
}
