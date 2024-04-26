package com.javaweb.converter;

import org.modelmapper.ModelMapper;
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
	
	@Autowired
	private ModelMapper modelMapper;
	
	public BuildingDTO toBuildingDTO(BuildingEntity x) {
		BuildingDTO buildingDTO = modelMapper.map(x, BuildingDTO.class);
		
		// Address
		DistrictEntity districtEntity = districtRepository.findDistrictNameById(x.getDistrictId());
		buildingDTO.setAddress(x.getStreet() + ", " + x.getWard() + ", " + districtEntity.getName());
		
		// Free area
		buildingDTO.setFreeArea("0");
		
		// Rent area
		String area = rentAreaRepository.findAreaByBuildingId(x.getId());
		buildingDTO.setRentArea(area);
		
		return buildingDTO;
	}
}
