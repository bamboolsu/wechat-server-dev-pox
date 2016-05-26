/*
 * Copyright 2005-2015 jshop.com. All rights reserved.
 * File Head

 */
package org.leo.entity;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

//import org.leo.util.JsonUtils;

/**
 * Entity - 会员
 * 
 * @author JSHOP Team \* @version 3.X
 */
@Entity
@Table(name = "member_base")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "seq_member")
public class Member extends BaseEntity<Long> {

	private static final long serialVersionUID = 3289462945582355081L;

	/**
	 * 性别
	 */
	public enum Gender {

		/** 男 */
		male,

		/** 女 */
		female
	}


	/** "身份信息"属性名称 */
	public static final String PRINCIPAL_ATTRIBUTE_NAME = "MemberInterceptor.PRINCIPAL";// TODO delete MemberInterceptor.class.getName() +

	/** "用户名"Cookie名称 */
	public static final String USERNAME_COOKIE_NAME = "username";

	/** "昵称"Cookie名称 */
	public static final String NICKNAME_COOKIE_NAME = "nickname";

	/** "用户名"Cookie名称 */
	public static final String MOBILE_COOKIE_NAME = "mobile";

	/** 会员注册项值属性个数 */
	public static final int ATTRIBUTE_VALUE_PROPERTY_COUNT = 10;


	/** 用户名 */
	private String username;

	/** E-mail */
	private String email;

	/** 昵称 */
	private String province;

	/** 最后创建日期 */
	private Date create_date;
	
	/** 最后修改日期 */
	private Date modify_date;

	/** 姓名 */
	private String name;

	/** 性别 */
	private Member.Gender gender;

	/** 地址 */
	private String address;

	/** 邮编 */
	private String city;

	/** 手机 */
	private String mobile;

	/** openID */
	private String openId;

	/** unionID */
	private String unionId;

	/**
	 * 获取用户名
	 * 
	 * @return 用户名
	 */
	@NotEmpty(groups = Save.class)
	@Pattern(regexp = "^[0-9a-zA-Z_\\u4e00-\\u9fa5]+$")
	@Column(nullable = false, updatable = false, unique = true)
	public String getUsername() {
		return username;
	}

	/**
	 * 设置用户名
	 * 
	 * @param username
	 *            用户名
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * 获取E-mail
	 * 
	 * @return E-mail
	 */
	@NotEmpty
	@Email
	@Length(max = 200)
	public String getEmail() {
		return email;
	}

	/**
	 * 设置E-mail
	 * 
	 * @param email
	 *            E-mail
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * 获取昵称
	 * 
	 * @return 昵称
	 */
	@Length(max = 200)
	public String getProvince() {
		return province;
	}

	/**
	 * 设置昵称
	 * 
	 * @param nickname
	 *            昵称
	 */
	public void setProvince(String province) {
		this.province = province;
	}

	/**
	 * 获取最后创建日期
	 * 
	 * @return 最后创建日期
	 */
	public Date getCreateDate() {
		return create_date;
	}

	/**
	 * 设置最后创建日期
	 * 
	 * @param loginDate
	 *            最后创建日期
	 */
	public void setCreateDate(Date create_date) {
		this.create_date = create_date;
	}

	/**
	 * 获取最后修改日期
	 * 
	 * @return 最后登录日期
	 */
	public Date getModifyDate() {
		return modify_date;
	}

	/**
	 * 设置最后修改日期
	 * 
	 * @param loginDate
	 *            最后登录日期
	 */
	public void setModifyDate(Date modify_date) {
		this.modify_date = modify_date;
	}
	
	/**
	 * 获取姓名
	 * 
	 * @return 姓名
	 */
	@Length(max = 200)
	public String getName() {
		return name;
	}

	/**
	 * 设置姓名
	 * 
	 * @param name
	 *            姓名
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 获取性别
	 * 
	 * @return 性别
	 */
	public Member.Gender getGender() {
		return gender;
	}

	/**
	 * 设置性别
	 * 
	 * @param gender
	 *            性别
	 */
	public void setGender(Member.Gender gender) {
		this.gender = gender;
	}

	/**
	 * 获取地址
	 * 
	 * @return 地址
	 */
	@Length(max = 200)
	public String getAddress() {
		return address;
	}

	/**
	 * 设置地址
	 * 
	 * @param address
	 *            地址
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * 获取邮编
	 * 
	 * @return 邮编
	 */
	@Length(max = 200)
	public String getCity() {
		return city;
	}

	/**
	 * 设置邮编
	 * 
	 * @param zipCode
	 *            邮编
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * 获取手机
	 * 
	 * @return 手机
	 */
	@Length(max = 200)
	public String getMobile() {
		return mobile;
	}

	/**
	 * 设置手机
	 * 
	 * @param mobile
	 *            手机
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	/**
	 * 获取openID
	 * 
	 * @return openID
	 */
	@Column(updatable = false)
	public String getOpenId() {
		return openId;
	}

	/**
	 * 设置openID
	 * 
	 * @param openId
	 *            openID
	 */
	public void setOpenId(String openId) {
		this.openId = openId;
	}

	
	/**
	 * 移除所有会员注册项值
	 */
	@Transient
	public void removeAttributeValue() {
		setName(null);
		setGender(null);
		setCity(null);
		setProvince(null);
		setAddress(null);
		setOpenId(null);
		setUnionId(null);
		setMobile(null);
	}

	/**
	 * 持久化前处理
	 */
	@PrePersist
	public void prePersist() {
		setUsername(StringUtils.lowerCase(getUsername()));
		setEmail(StringUtils.lowerCase(getEmail()));
	}
	
	
	
	/**
	 * 更新前处理
	 */
	@PreUpdate
	public void preUpdate() {
		setEmail(StringUtils.lowerCase(getEmail()));
	}

	public String getUnionId() {
		return unionId;
	}

	public void setUnionId(String unionId) {
		this.unionId = unionId;
	}

}
