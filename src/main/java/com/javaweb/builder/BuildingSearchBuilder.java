package com.javaweb.builder;

import java.util.ArrayList;
import java.util.List;

public class BuildingSearchBuilder {
	private String name;
	private String floorArea;
	private String districtId;
	private String ward;
	private String street;
	private String numberOfBasement;
	private String direction;
	private String level;
	private String areaFrom;
	private String areaTo;
	private String rentPriceFrom;
	private String rentPriceTo;
	private String managerName;
	private String managerPhoneNumber;
	private String staffId;
	private List<String> typeCode = new ArrayList<>();
	
	public BuildingSearchBuilder(Builder builder) {
		this.name = builder.name;
		this.floorArea = builder.floorArea;
		this.districtId = builder.districtId;
		this.ward = builder.ward;
		this.street = builder.street;
		this.numberOfBasement = builder.numberOfBasement;
		this.direction = builder.direction;
		this.level = builder.level;
		this.areaFrom = builder.areaFrom;
		this.areaTo = builder.areaTo;
		this.rentPriceFrom = builder.rentPriceFrom;
		this.rentPriceTo = builder.rentPriceTo;
		this.managerName = builder.managerName;
		this.managerPhoneNumber = builder.managerPhoneNumber;
		this.staffId = builder.staffId;
		this.typeCode = builder.typeCode;
	}
	
	public String getName() {
		return name;
	}
	public String getFloorArea() {
		return floorArea;
	}
	public String getDistrictId() {
		return districtId;
	}
	public String getWard() {
		return ward;
	}
	public String getStreet() {
		return street;
	}
	public String getNumberOfBasement() {
		return numberOfBasement;
	}
	public String getDirection() {
		return direction;
	}
	public String getLevel() {
		return level;
	}
	public String getAreaFrom() {
		return areaFrom;
	}
	public String getAreaTo() {
		return areaTo;
	}
	public String getRentPriceFrom() {
		return rentPriceFrom;
	}
	public String getRentPriceTo() {
		return rentPriceTo;
	}
	public String getManagerName() {
		return managerName;
	}
	public String getManagerPhoneNumber() {
		return managerPhoneNumber;
	}
	public String getStaffId() {
		return staffId;
	}
	public List<String> getTypeCode() {
		return typeCode;
	}
	
	public static class Builder {
		private String name;
		private String floorArea;
		private String districtId;
		private String ward;
		private String street;
		private String numberOfBasement;
		private String direction;
		private String level;
		private String areaFrom;
		private String areaTo;
		private String rentPriceFrom;
		private String rentPriceTo;
		private String managerName;
		private String managerPhoneNumber;
		private String staffId;
		private List<String> typeCode = new ArrayList<>();
		public Builder setName(String name) {
			this.name = name;
			return this;
		}
		public Builder setFloorArea(String floorArea) {
			this.floorArea = floorArea;
			return this;
		}
		public Builder setDistrictId(String districtId) {
			this.districtId = districtId;
			return this;
		}
		public Builder setWard(String ward) {
			this.ward = ward;
			return this;
		}
		public Builder setStreet(String street) {
			this.street = street;
			return this;
		}
		public Builder setNumberOfBasement(String numberOfBasement) {
			this.numberOfBasement = numberOfBasement;
			return this;
		}
		public Builder setDirection(String direction) {
			this.direction = direction;
			return this;
		}
		public Builder setLevel(String level) {
			this.level = level;
			return this;
		}
		public Builder setAreaFrom(String areaFrom) {
			this.areaFrom = areaFrom;
			return this;
		}
		public Builder setAreaTo(String areaTo) {
			this.areaTo = areaTo;
			return this;
		}
		public Builder setRentPriceFrom(String rentPriceFrom) {
			this.rentPriceFrom = rentPriceFrom;
			return this;
		}
		public Builder setRentPriceTo(String rentPriceTo) {
			this.rentPriceTo = rentPriceTo;
			return this;
		}
		public Builder setManagerName(String managerName) {
			this.managerName = managerName;
			return this;
		}
		public Builder setManagerPhoneNumber(String managerPhoneNumber) {
			this.managerPhoneNumber = managerPhoneNumber;
			return this;
		}
		public Builder setStaffId(String staffId) {
			this.staffId = staffId;
			return this;
		}
		public Builder setTypeCode(List<String> typeCode) {
			this.typeCode = typeCode;
			return this;
		}
		public BuildingSearchBuilder build () {
			return new BuildingSearchBuilder(this);
		}
	}
}
