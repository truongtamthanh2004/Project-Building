package com.javaweb.repository.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="rentarea")
public class RentAreaEntity {
	@Id // Primary key
	@GeneratedValue(strategy = GenerationType.IDENTITY) // Auto Increment
	private Long id;
	
	@Column(name="value")
	private String value;
	
//	@Column(name="buildingid")
//	private String buildingId;
	
	@Column(name="createddate")
	private String createdDate;
	
	@Column(name="modifieddate")
	private String modifiedDate;
	
	@Column(name="createdby")
	private String createdBy;
	
	@Column(name="modifiedby")
	private String modifiedBy;
	
	@ManyToOne
	@JoinColumn(name="buildingid")
	private BuildingEntity building;
	
	
	public BuildingEntity getBuilding() {
		return building;
	}
	public void setBuilding(BuildingEntity building) {
		this.building = building;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
//	public String getBuildingId() {
//		return buildingId;
//	}
//	public void setBuildingId(String buildingId) {
//		this.buildingId = buildingId;
//	}
	public String getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}
	public String getModifiedDate() {
		return modifiedDate;
	}
	public void setModifiedDate(String modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public String getModifiedBy() {
		return modifiedBy;
	}
	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}
}
