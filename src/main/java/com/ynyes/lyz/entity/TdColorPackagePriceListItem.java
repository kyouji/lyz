package com.ynyes.lyz.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * 调色包价目表项
 * 
 * @author dengxiao
 */

@Entity
public class TdColorPackagePriceListItem {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	// 调色包价目表编号
	@Column
	private Long colorPackagePriceListId;

	// 价目表名称
	@Column
	private String colorPackagePriceListName;

	// 所属区域id
	@Column
	private Long cityId;

	// 所属区域城市名称
	@Column
	private String cityName;

	// 所属分公司名称
	@Column
	private String companyName;

	// 所属分公司id
	@Column
	private Long companyId;

	// 调色包id
	@Column
	private Long colorPackageId;

	// 该件调色包的虚拟销售价
	@Column(scale = 2)
	private Double salePrice;
	
	//该件调色包的实际销售价
	@Column(scale = 2)
	private Double realSalePrice;

	// 该件调色包的虚拟进货价
	@Column(scale = 2)
	private Double stockPrice;

	//该件调色包的实际进货价
	@Column(scale = 2)
	private Double realStockPrice;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getColorPackagePriceListId() {
		return colorPackagePriceListId;
	}

	public void setColorPackagePriceListId(Long colorPackagePriceListId) {
		this.colorPackagePriceListId = colorPackagePriceListId;
	}

	public String getColorPackagePriceListName() {
		return colorPackagePriceListName;
	}

	public void setColorPackagePriceListName(String colorPackagePriceListName) {
		this.colorPackagePriceListName = colorPackagePriceListName;
	}

	public Long getCityId() {
		return cityId;
	}

	public void setCityId(Long cityId) {
		this.cityId = cityId;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public Long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

	public Long getColorPackageId() {
		return colorPackageId;
	}

	public void setColorPackageId(Long colorPackageId) {
		this.colorPackageId = colorPackageId;
	}

	public Double getSalePrice() {
		return salePrice;
	}

	public void setSalePrice(Double salePrice) {
		this.salePrice = salePrice;
	}

	public Double getStockPrice() {
		return stockPrice;
	}

	public void setStockPrice(Double stockPrice) {
		this.stockPrice = stockPrice;
	}

	public Double getRealSalePrice() {
		return realSalePrice;
	}

	public void setRealSalePrice(Double realSalePrice) {
		this.realSalePrice = realSalePrice;
	}

	public Double getRealStockPrice() {
		return realStockPrice;
	}

	public void setRealStockPrice(Double realStockPrice) {
		this.realStockPrice = realStockPrice;
	}
}
