package com.javaweb.api;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.javaweb.DTO.BuildingDTO;
import com.javaweb.service.BuildingService;

@RestController
public class NewAPI {
	@Autowired
	private BuildingService buildingService;
	
	@GetMapping(value = "/api/building")
	public List<BuildingDTO> findBuilding(@RequestParam Map<String, Object> params, @RequestParam (value="typeCode", required=false) List<String> typeCode) {
		List<BuildingDTO> buildingDTOs = buildingService.findBuildingDTO(params, typeCode);
		
		return buildingDTOs;
	}
}
