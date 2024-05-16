package com.javaweb.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaweb.DTO.BuildingDTO;
import com.javaweb.DTO.BuildingRequestDTO;
import com.javaweb.builder.BuildingSearchBuilder;
import com.javaweb.converter.BuildingConverter;
import com.javaweb.converter.BuildingSearchBuilderConverter;
import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.DistrictRepository;
import com.javaweb.repository.RentAreaRepository;
import com.javaweb.repository.entity.BuildingEntity;
import com.javaweb.repository.entity.DistrictEntity;
import com.javaweb.repository.entity.RentAreaEntity;
import com.javaweb.service.BuildingService;

@Service
public class BuildingServiceImpl implements BuildingService{
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private BuildingRepository buildingRepository;
	
	@Autowired
	private BuildingConverter buildingConverter;
	
	@Autowired
	private RentAreaRepository rentAreaRepository;
	
	@Autowired
	private BuildingSearchBuilderConverter buildingSearchBuilderConverter;
	
	@Override
	public List<BuildingDTO> findBuildingDTO(Map<String, Object> params, List<String> typeCode) {
		// TODO Auto-generated method stub
		BuildingSearchBuilder buildingSearchBuilder = buildingSearchBuilderConverter.toBuildingSearchBuilder(params, typeCode);
		
		List<BuildingEntity> buildingEntities = buildingRepository.findAll(buildingSearchBuilder);
//		List<BuildingEntity> buildingEntities = buildingRepository.findByNameContainingAndWardContaining(buildingSearchBuilder.getName(), "phường 6");
//		BuildingEntity buildingEntity = buildingRepository.findById(1L).get();
		List<BuildingDTO> buildingDTOs = new ArrayList<BuildingDTO>();
		for (BuildingEntity x : buildingEntities) {
			// New building DTO
			BuildingDTO buildingDTO = buildingConverter.toBuildingDTO(x);
			
			buildingDTOs.add(buildingDTO);
		}
		
		return buildingDTOs;
	}

	@Override
	public void createBuilding(BuildingRequestDTO buildingDTO) {
		// TODO Auto-generated method stub
		BuildingEntity buildingEntity = modelMapper.map(buildingDTO, BuildingEntity.class);
		
		buildingRepository.save(buildingEntity);
		
//		List<RentAreaEntity> rentAreaEntities = new ArrayList<RentAreaEntity>();
		RentAreaEntity rentArea1 = new RentAreaEntity();
		rentArea1.setBuilding(buildingEntity);
		rentArea1.setValue("200");
		RentAreaEntity rentArea2 = new RentAreaEntity();
		rentArea2.setBuilding(buildingEntity);
		rentArea2.setValue("300");
//		rentAreaEntities.add(rentArea1);
//		rentAreaEntities.add(rentArea2);
		rentAreaRepository.save(rentArea1);
		rentAreaRepository.save(rentArea2);
		
//		buildingRepository.save(buildingEntity);    // If have Id, update. If don't, create new
	}

	@Override
	public void delete(Long[] ids) {
		// TODO Auto-generated method stub
//		rentAreaRepository.deleteById(12L);
//		rentAreaRepository.deleteById(13L);
		BuildingEntity buildingEntity = buildingRepository.findById(ids[0]).get();
		rentAreaRepository.deleteAllByBuilding(buildingEntity);
		buildingRepository.deleteByIdIn(ids);
	}
	
}
