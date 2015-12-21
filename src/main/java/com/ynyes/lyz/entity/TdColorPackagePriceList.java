package com.ynyes.lyz.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * 调色包价目表类
 * @author dengxiao
 */

@Entity
public class TdColorPackagePriceList {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	//调色包价目表名称
	@Column
	private String name;
	
	//所属城市id
	@Column
	private Long cityId;
	
	//创建者
	@Column
	private String username;
	
	//排序号
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

	public Long getCityId() {
		return cityId;
	}

	public void setCityId(Long cityId) {
		this.cityId = cityId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Double getSortId() {
		return sortId;
	}

	public void setSortId(Double sortId) {
		this.sortId = sortId;
	}

	@Override
	public String toString() {
		return "TdColorPackagePriceList [id=" + id + ", name=" + name + ", cityId=" + cityId + ", username=" + username
				+ ", sortId=" + sortId + "]";
	}
}
