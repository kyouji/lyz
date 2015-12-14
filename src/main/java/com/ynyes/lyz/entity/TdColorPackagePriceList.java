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
	
	//排序号
	@Column
	private Long sortId;

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

	public Long getSortId() {
		return sortId;
	}

	public void setSortId(Long sortId) {
		this.sortId = sortId;
	}

	@Override
	public String toString() {
		return "TdColorPackagePriceList [id=" + id + ", name=" + name + ", sortId=" + sortId + "]";
	}
}
