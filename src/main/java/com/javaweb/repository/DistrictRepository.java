package com.javaweb.repository;

import com.javaweb.repository.entity.DistrictEntity;

public interface DistrictRepository {
	public DistrictEntity findDistrictNameById(String id);
}
