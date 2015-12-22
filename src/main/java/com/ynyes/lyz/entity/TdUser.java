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

@Entity
public class TdUser {

	// 用户Id
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	// 收货地址
	@OneToMany
	@JoinColumn(name = "userId")
	private List<TdShippingAddress> shippingAddressList;

	// 用户头像
	@Column
	private String headImageUri;

	// 用户名
	@Column(nullable = false, unique = true)
	private String username;

	// 所属门店ID
	@Column
	private Long upperDiySiteId;

	// 归属门店名称
	@Column
	private String diyName;

	// 昵称
	@Column
	private String nickname;

	// 密码
	@Column(nullable = false)
	private String password;

	// 真实姓名
	@Column
	private String realName;

	// 性别
	@Column
	private String sex;

	//生日
	@Column
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date birthday;
	
	// 注册时间
	@Column
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date registerTime;

	// 最后登录时间
	@Column
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date lastLoginTime;

	// 身份证号码
	@Column
	private String identity;

	// 电子邮箱
	@Column
	private String email;

	// 用户等级
	@Column
	private Long userLevelId;

	// 用户等级名称
	@Column
	private String userLevelTitle;

	// 账户余额
	@Column
	private Double balance;

	// 用户类型(0代表会员，1代表销售顾问，2代表店长，3代表店主，4代表区域经理)
	@Column
	private Long userType;

	// 归属区域名称
	@Column
	private String cityName;

	// 归属区域Id
	@Column
	private Long cityId;

	// 归属销顾电话（推荐人电话）
	@Column
	private String referPhone;

	// 首单优惠是否可用
	@Column
	private Boolean firstOrder;

	// 总消费
	@Column
	private Double allPayed;

	// 可提现余额
	@Column(scale = 2)
	private Double cashBalance;

	// 不可提现余额
	@Column(scale = 2)
	private Double unCashBalance;

	// 是否是老会员
	@Column
	private Boolean isOld;

	// 是否启用
	@Column
	private Boolean isEnable;

	 // 排序号
    @Column
    private Double sortId;
    
	public Double getSortId() {
		return sortId;
	}

	public void setSortId(Double sortId) {
		this.sortId = sortId;
	}

	public Boolean getFirstOrder() {
		return firstOrder;
	}

	public void setFirstOrder(Boolean firstOrder) {
		this.firstOrder = firstOrder;
	}

	public Double getAllPayed() {
		return allPayed;
	}

	public void setAllPayed(Double allPayed) {
		this.allPayed = allPayed;
	}

	public Boolean getIsOld() {
		return isOld;
	}

	public void setIsOld(Boolean isOld) {
		this.isOld = isOld;
	}

	public Long getCityId() {
		return cityId;
	}

	public void setCityId(Long cityId) {
		this.cityId = cityId;
	}

	public String getDiyName() {
		return diyName;
	}

	public void setDiyName(String diyName) {
		this.diyName = diyName;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getReferPhone() {
		return referPhone;
	}

	public void setReferPhone(String referPhone) {
		this.referPhone = referPhone;
	}

	public Boolean getIsEnable() {
		return isEnable;
	}

	public void setIsEnable(Boolean isEnable) {
		this.isEnable = isEnable;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<TdShippingAddress> getShippingAddressList() {
		return shippingAddressList;
	}

	public void setShippingAddressList(List<TdShippingAddress> shippingAddressList) {
		this.shippingAddressList = shippingAddressList;
	}

	public String getHeadImageUri() {
		return headImageUri;
	}

	public void setHeadImageUri(String headImageUri) {
		this.headImageUri = headImageUri;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Long getUpperDiySiteId() {
		return upperDiySiteId;
	}

	public void setUpperDiySiteId(Long upperDiySiteId) {
		this.upperDiySiteId = upperDiySiteId;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public Date getRegisterTime() {
		return registerTime;
	}

	public void setRegisterTime(Date registerTime) {
		this.registerTime = registerTime;
	}

	public Date getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public String getIdentity() {
		return identity;
	}

	public void setIdentity(String identity) {
		this.identity = identity;
	}

	public Double getCashBalance() {
		return cashBalance;
	}

	public void setCashBalance(Double cashBalance) {
		this.cashBalance = cashBalance;
	}

	public Double getUnCashBalance() {
		return unCashBalance;
	}

	public void setUnCashBalance(Double unCashBalance) {
		this.unCashBalance = unCashBalance;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Long getUserLevelId() {
		return userLevelId;
	}

	public void setUserLevelId(Long userLevelId) {
		this.userLevelId = userLevelId;
	}

	public String getUserLevelTitle() {
		return userLevelTitle;
	}

	public void setUserLevelTitle(String userLevelTitle) {
		this.userLevelTitle = userLevelTitle;
	}

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

	public Long getUserType() {
		return userType;
	}

	public void setUserType(Long userType) {
		this.userType = userType;
	}

}
