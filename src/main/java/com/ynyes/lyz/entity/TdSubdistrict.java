package com.ynyes.lyz.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * 行政街道实体类
 * 
 * @author dengxiao
 */

@Entity
public class TdSubdistrict {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	// 行政街道名称
	@Column
	private String name;

	// 所属区域id
	@Column
	private Long districtId;

	// 排序号
	@Column
	private Double sortId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getDistrictId() {
		return districtId;
	}

	public void setDistrictId(Long districtId) {
		this.districtId = districtId;
	}

	public Double getSortId() {
		return sortId;
	}

	public void setSortId(Double sortId) {
		this.sortId = sortId;
	}

	@Override
	public String toString() {
		return "TdSubDistrict [id=" + id + ", name=" + name + ", districtId=" + districtId + ", sortId=" + sortId + "]";
	}
	
}
