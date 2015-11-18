package com.ynyes.lyz.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * 区域实体类
 * @author Administrator
 */

@Entity
public class TdRegion {

	//区域Id
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	//省份
	@Column
	private String province;
	
	//城市名
	@Column
	private String cityName;
	
	//省会城市是否开通配送服务
	@Column
	private Boolean citySend;
	
	//下属城市
	@Column
	private String subCityName;
	
	//下属城市是否开通配送业务
	@Column
	private Boolean subCitySend;
	
	//价目表Id
	@Column
	private Long priceListId;
	
	//排序号
	@Column
	private Long sortId;

	public Long getSortId() {
		return sortId;
	}

	public void setSortId(Long sortId) {
		this.sortId = sortId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public Boolean getCitySend() {
		return citySend;
	}

	public void setCitySend(Boolean citySend) {
		this.citySend = citySend;
	}

	public String getSubCityName() {
		return subCityName;
	}

	public void setSubCityName(String subCityName) {
		this.subCityName = subCityName;
	}

	public Boolean getSubCitySend() {
		return subCitySend;
	}

	public void setSubCitySend(Boolean subCitySend) {
		this.subCitySend = subCitySend;
	}

	public Long getPriceListId() {
		return priceListId;
	}

	public void setPriceListId(Long priceListId) {
		this.priceListId = priceListId;
	}
	
	
}
