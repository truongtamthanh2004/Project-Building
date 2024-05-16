package com.javaweb.api;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.javaweb.DTO.BuildingDTO;
import com.javaweb.DTO.BuildingRequestDTO;
import com.javaweb.repository.entity.BuildingEntity;
import com.javaweb.repository.entity.DistrictEntity;
import com.javaweb.service.BuildingService;

@RestController
@Transactional
public class NewAPI {
	@Autowired
	private BuildingService buildingService;
	
	@GetMapping(value = "/api/building")
	public List<BuildingDTO> findBuilding(@RequestParam Map<String, Object> params, @RequestParam (value="typeCode", required=false) List<String> typeCode) {
		List<BuildingDTO> buildingDTOs = buildingService.findBuildingDTO(params, typeCode);
		
		return buildingDTOs;
	}
	
	@PersistenceContext
	private EntityManager entityManager;
	
	
	
	@PostMapping(value="/api/building")
	public void createBuilding(@RequestBody BuildingRequestDTO buildingDTO) {
//		BuildingEntity buildingEntity = modelMapper.map(buildingDTO, BuildingEntity.class);
//		DistrictEntity districtEntity = entityManager.find(DistrictEntity.class, buildingDTO.getDistrictId());
//		buildingEntity.setDistrict(districtEntity);
//		entityManager.merge(buildingEntity); // Add to Database
		buildingService.createBuilding(buildingDTO);
	}
	
	@DeleteMapping(value="/api/building/{ids}")
	public void deleteBuilding(@PathVariable Long[] ids) {
//		BuildingEntity buildingEntity = entityManager.find(BuildingEntity.class, id);
//		entityManager.remove(buildingEntity);
		buildingService.delete(ids);
	}
}
