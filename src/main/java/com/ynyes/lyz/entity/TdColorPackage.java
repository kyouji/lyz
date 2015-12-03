package com.ynyes.lyz.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * 调色包实体类
 * 
 * @author dengxiao
 */

@Entity
public class TdColorPackage {

	// id
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	// 调色包名称
	@Column
	private String name;

	// 调色包编号
	@Column
	private String number;

	// 调色包图片
	@Column
	private String imageUri;

	// 调色包价格
	@Column(scale = 2)
	private Double price;

	// 商品的编号
	@Column
	private Long goodsId;
	
	//商品的名称
	@Column
	private String goodsName;

	// 地区编号
	@Column
	private Long regionId;

	// 地区名称
	@Column
	private String regionName;
	
	//数量
	@Column
	private Long quantity;
	
	//总价格
	@Column(scale = 2)
	private Double totalPrice;

	// 排序号
	@Column
	private Long sortId;

	public Long getQuantity() {
		return quantity;
	}

	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}

	public Double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}

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

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getImageUri() {
		return imageUri;
	}

	public void setImageUri(String imageUri) {
		this.imageUri = imageUri;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Long getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(Long goodsId) {
		this.goodsId = goodsId;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public Long getRegionId() {
		return regionId;
	}

	public void setRegionId(Long regionId) {
		this.regionId = regionId;
	}

	public String getRegionName() {
		return regionName;
	}

	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}

	public Long getSortId() {
		return sortId;
	}

	public void setSortId(Long sortId) {
		this.sortId = sortId;
	}
}
