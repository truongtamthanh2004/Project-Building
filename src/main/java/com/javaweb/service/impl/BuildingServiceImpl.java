package com.javaweb.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaweb.DTO.BuildingDTO;
import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.DistrictRepository;
import com.javaweb.repository.RentAreaRepository;
import com.javaweb.repository.entity.BuildingEntity;
import com.javaweb.repository.entity.DistrictEntity;
import com.javaweb.service.BuildingService;

@Service
public class BuildingServiceImpl implements BuildingService{
	@Autowired
	private BuildingRepository buildingRepository;
	@Autowired
	private DistrictRepository districtRepository;
	@Autowired
	private RentAreaRepository rentAreaRepository;
	
	@Override
	public List<BuildingDTO> findBuildingDTO(Map<String, Object> params, List<String> typeCode) {
		// TODO Auto-generated method stub
		List<BuildingEntity> buildingEntities = buildingRepository.findAll(params, typeCode);
		List<BuildingDTO> buildingDTOs = new ArrayList<BuildingDTO>();
		for (BuildingEntity x : buildingEntities) {
			// New building DTO
			BuildingDTO buildingDTO = new BuildingDTO();
			
			// Name
			buildingDTO.setName(x.getName());
			
			// Address
			DistrictEntity districtEntity = districtRepository.findDistrictNameById(x.getDistrictId());
			buildingDTO.setAddress(x.getStreet() + ", " + x.getWard() + ", " + districtEntity.getName());
			
			// Number of basement
			buildingDTO.setNumberOfBasement(x.getNumberOfBasement());
			
			// Manager's name
			buildingDTO.setManagerName(x.getManagerName());
			
			// Manager's phone number
			buildingDTO.setManagerPhoneNumber(x.getManagerPhoneNumber());
			
			// Floor area
			buildingDTO.setFloorArea(x.getFloorArea());
			
			// Free area
			buildingDTO.setFreeArea("0");
			
			// Rent area
			String area = rentAreaRepository.findAreaByBuildingId(x.getId());
			buildingDTO.setRentArea(area);
			
			// Rent price
			buildingDTO.setRentFee(x.getRentPrice());
			
			// Brokerage fee
			buildingDTO.setAgencyFee(x.getBrokerageFee());
			
			// Service fee
			buildingDTO.setServiceFee(x.getServiceFee());
			
			buildingDTOs.add(buildingDTO);
		}
		
		return buildingDTOs;
	}

}
