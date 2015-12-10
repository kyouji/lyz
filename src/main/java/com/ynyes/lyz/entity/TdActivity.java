package com.ynyes.lyz.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * 促销活动实体类
 * 
 * @author
 */

@Entity
public class TdActivity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	//活动名称
	@Column(unique = true)
	private String name;
	
	//是否属于首页推荐活动
	@Column
	private Boolean isCommendIndex;
	
	//活动开始时间
	@Column
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@Temporal(TemporalType.TIMESTAMP)
	private Date beginDate;
	
	//活动结束时间
	@Column
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@Temporal(TemporalType.TIMESTAMP)
	private Date finishDate;
	
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

	public Boolean getIsCommendIndex() {
		return isCommendIndex;
	}

	public void setIsCommendIndex(Boolean isCommendIndex) {
		this.isCommendIndex = isCommendIndex;
	}

	public Date getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}

	public Date getFinishDate() {
		return finishDate;
	}

	public void setFinishDate(Date finishDate) {
		this.finishDate = finishDate;
	}

	public Long getSortId() {
		return sortId;
	}

	public void setSortId(Long sortId) {
		this.sortId = sortId;
	}

	@Override
	public String toString() {
		return "TdActivity [id=" + id + ", name=" + name + ", isCommendIndex=" + isCommendIndex + ", beginDate="
				+ beginDate + ", finishDate=" + finishDate + ", sortId=" + sortId + "]";
	}
	
}
