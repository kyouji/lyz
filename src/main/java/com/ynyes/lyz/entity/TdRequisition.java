package com.ynyes.lyz.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import org.springframework.format.annotation.DateTimeFormat;


//要货单

@Entity
public class TdRequisition {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
    // 单号
    @Column(unique=true)
    private String requisitionNumber;
    
    // 订单商品
    @OneToMany
    @JoinColumn(name="TdRequisitionId")
    private List<TdOrderGoods> requisiteGoodsList;
    
    // 门店id
    @Column
    private Long diySiteId;
    
    // 门店名称
    @Column
    private String diySiteTitle;
    
    // 门店地址
    @Column
    private String diySiteAddress;
    
    // 门店电话
    @Column
    private String diySiteTel;
    
    // 商户备注
    @Column
    private String remarkInfo;
    
    // 后台备注
    @Column
    private String managerRemarkInfo;
    
    // 下单时间
    @Column
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date orderTime;
    
    // 确认时间
    @Column
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date checkTime;
    
    // 取消时间
    @Column
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date cancelTime;
    
    // 要货单状态  1:待审核 2:已完成 3:已取消  
    @Column
    private Long statusId;

    // 要货单类型 1： 要货单  2：要货单退单 3： 要货单订单
    @Column
    private Long typeId;
    
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRequisitionNumber() {
		return requisitionNumber;
	}

	public void setRequisitionNumber(String requisitionNumber) {
		this.requisitionNumber = requisitionNumber;
	}

	public List<TdOrderGoods> getRequisiteGoodsList() {
		return requisiteGoodsList;
	}

	public void setRequisiteGoodsList(List<TdOrderGoods> requisiteGoodsList) {
		this.requisiteGoodsList = requisiteGoodsList;
	}

	public Long getDiySiteId() {
		return diySiteId;
	}

	public void setDiySiteId(Long diySiteId) {
		this.diySiteId = diySiteId;
	}

	public String getDiySiteTitle() {
		return diySiteTitle;
	}

	public void setDiySiteTitle(String diySiteTitle) {
		this.diySiteTitle = diySiteTitle;
	}

	public String getRemarkInfo() {
		return remarkInfo;
	}

	public void setRemarkInfo(String remarkInfo) {
		this.remarkInfo = remarkInfo;
	}

	public String getManagerRemarkInfo() {
		return managerRemarkInfo;
	}

	public void setManagerRemarkInfo(String managerRemarkInfo) {
		this.managerRemarkInfo = managerRemarkInfo;
	}

	public Date getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(Date orderTime) {
		this.orderTime = orderTime;
	}

	public Date getCheckTime() {
		return checkTime;
	}

	public void setCheckTime(Date checkTime) {
		this.checkTime = checkTime;
	}

	public Long getStatusId() {
		return statusId;
	}

	public void setStatusId(Long statusId) {
		this.statusId = statusId;
	}

	public Date getCancelTime() {
		return cancelTime;
	}

	public void setCancelTime(Date cancelTime) {
		this.cancelTime = cancelTime;
	}

	public String getDiySiteAddress() {
		return diySiteAddress;
	}

	public void setDiySiteAddress(String diySiteAddress) {
		this.diySiteAddress = diySiteAddress;
	}

	public String getDiySiteTel() {
		return diySiteTel;
	}

	public void setDiySiteTel(String diySiteTel) {
		this.diySiteTel = diySiteTel;
	}

	public Long getTypeId() {
		return typeId;
	}

	public void setTypeId(Long typeId) {
		this.typeId = typeId;
	}
   
    
}
