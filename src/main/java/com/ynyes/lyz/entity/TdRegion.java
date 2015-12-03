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
	
	//所属分公司id
	@Column
	private Long companyId;
	
	//城市名
	@Column
	private String cityName;
	
	//是否开通配送服务
	@Column
	private Boolean citySend;
	
	//上级城市
	@Column
	private String parentCityName;
	
	//上级城市是否开通配送业务
	@Column
	private Boolean parentCitySend;
	
	//价目表Id
	@Column
	private Long priceListId;
	
	//短信息账户Id
	@Column
	private Long smsAccountId;
	
	//排序号
	@Column
	private Long sortId;

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

	public Long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
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

	public String getParentCityName() {
		return parentCityName;
	}

	public void setParentCityName(String parentCityName) {
		this.parentCityName = parentCityName;
	}

	public Boolean getParentCitySend() {
		return parentCitySend;
	}

	public void setParentCitySend(Boolean parentCitySend) {
		this.parentCitySend = parentCitySend;
	}

	public Long getPriceListId() {
		return priceListId;
	}

	public void setPriceListId(Long priceListId) {
		this.priceListId = priceListId;
	}

	public Long getSmsAccountId() {
		return smsAccountId;
	}

	public void setSmsAccountId(Long smsAccountId) {
		this.smsAccountId = smsAccountId;
	}

	public Long getSortId() {
		return sortId;
	}

	public void setSortId(Long sortId) {
		this.sortId = sortId;
	}

	@Override
	public String toString() {
		return "TdRegion [id=" + id + ", province=" + province + ", companyId=" + companyId + ", cityName=" + cityName
				+ ", citySend=" + citySend + ", parentCityName=" + parentCityName + ", parentCitySend=" + parentCitySend
				+ ", priceListId=" + priceListId + ", smsAccountId=" + smsAccountId + ", sortId=" + sortId + "]";
	}
}
