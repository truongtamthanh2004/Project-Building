package com.javaweb.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.javaweb.DTO.BuildingDTO;
import com.javaweb.repository.DistrictRepository;
import com.javaweb.repository.RentAreaRepository;
import com.javaweb.repository.entity.BuildingEntity;
import com.javaweb.repository.entity.DistrictEntity;

@Component
public class BuildingConverter {
	@Autowired
	private DistrictRepository districtRepository;
	@Autowired
	private RentAreaRepository rentAreaRepository;
	
	public BuildingDTO toBuildingDTO(BuildingEntity x) {
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
		
		return buildingDTO;
	}
}
