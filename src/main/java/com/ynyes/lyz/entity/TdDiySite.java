package com.ynyes.lyz.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * 门店
 * 
 * @author Sharon
 *
 */

@Entity
public class TdDiySite {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	// 门店名称
	@Column
	private String title;

	// 门店地址
	@Column
	private String address;

	// 付款方式
	@Column
	private String payType;

	// 营业时间
	@Column
	private String openTimeSpan;

	// 客服电话
	@Column
	private String serviceTele;

	// 投诉电话
	@Column
	private String complainTele;

	// 门店省份
	@Column
	private String province;

	// 门店城市
	@Column
	private String city;

	// 门店城市地区
	@Column
	private String disctrict;

	// 门店区域Id
	@Column
	private Long regionId;

	// 是否启用
	@Column
	private Boolean isEnable;

	// 排序数字
	@Column
	private Long sortId;

	// 经度
	@Column
	private Double longitude;

	// 纬度
	@Column
	private Double latitude;

	// 描述说明
	@Column
	private String info;

	// 图片地址
	@Column
	private String imageUri;

	// 轮播展示图片，多张图片以,隔开
	@Column
	private String showPictures;

	// 客服qq
	@Column
	private String qq;

	// 是否为旗舰店
	@Column
	private Boolean isFlagShip;

	// 门店所使用的价目表编号
	@Column
	private String priceListNumber;

	public Long getRegionId() {
		return regionId;
	}

	public void setRegionId(Long regionId) {
		this.regionId = regionId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Boolean getIsFlagShip() {
		return isFlagShip;
	}

	public void setIsFlagShip(Boolean isFlagShip) {
		this.isFlagShip = isFlagShip;
	}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public String getPayType() {
		return payType;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}

	public String getOpenTimeSpan() {
		return openTimeSpan;
	}

	public void setOpenTimeSpan(String openTimeSpan) {
		this.openTimeSpan = openTimeSpan;
	}

	public String getServiceTele() {
		return serviceTele;
	}

	public void setServiceTele(String serviceTele) {
		this.serviceTele = serviceTele;
	}

	public String getComplainTele() {
		return complainTele;
	}

	public void setComplainTele(String complainTele) {
		this.complainTele = complainTele;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getDisctrict() {
		return disctrict;
	}

	public void setDisctrict(String disctrict) {
		this.disctrict = disctrict;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public String getImageUri() {
		return imageUri;
	}

	public void setImageUri(String imageUri) {
		this.imageUri = imageUri;
	}

	public String getShowPictures() {
		return showPictures;
	}

	public void setShowPictures(String showPictures) {
		this.showPictures = showPictures;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Boolean getIsEnable() {
		return isEnable;
	}

	public void setIsEnable(Boolean isEnable) {
		this.isEnable = isEnable;
	}

	public Long getSortId() {
		return sortId;
	}

	public void setSortId(Long sortId) {
		this.sortId = sortId;
	}
	
	public String getPriceListNumber() {
		return priceListNumber;
	}

	public void setPriceListNumber(String priceListNumber) {
		this.priceListNumber = priceListNumber;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

}
