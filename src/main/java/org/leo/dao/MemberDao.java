/*
 * Copyright 2005-2015 jshop.com. All rights reserved.
 * File Head

 */
package org.leo.dao;

import java.util.Date;
import java.util.List;

import org.leo.Page;
import org.leo.Pageable;
import org.leo.entity.Member;
//import org.leo.entity.MemberAttribute;

/**
 * Dao - 会员
 * 
 * @author JSHOP Team
 \* @version 3.X
 */
public interface MemberDao extends BaseDao<Member, Long> {

	/**
	 * 判断用户名是否存在
	 * 
	 * @param username
	 *            用户名(忽略大小写)
	 * @return 用户名是否存在
	 */
	boolean usernameExists(String username);

	/**
	 * 判断E-mail是否存在
	 * 
	 * @param email
	 *            E-mail(忽略大小写)
	 * @return E-mail是否存在
	 */
	boolean emailExists(String email);
	
	/**
	 * 判断电话号码是否存在
	 * 
	 * @param mobile
	 * 			电话号码
	 * @return 电话号码是否存在
	 */
	boolean mobileExists(String mobile);
	
	/**
	 * 查找会员
	 * 
	 * @param openId
	 *            openID
	 * @return 会员，若不存在则返回null
	 */
	Member findByOpenId(String openId);
	Member findByUnionId(String unionId);

	/**
	 * 根据用户名查找会员
	 * 
	 * @param username
	 *            用户名(忽略大小写)
	 * @return 会员，若不存在则返回null
	 */
	Member findByUsername(String username);

	/**
	 * 根据E-mail查找会员
	 * 
	 * @param email
	 *            E-mail(忽略大小写)
	 * @return 会员，若不存在则返回null
	 */
	List<Member> findListByEmail(String email);

	/**
	 * 根据phone查找会员
	 * 
	 * @param phone
	 * 			phone
	 * @return 会员，若不存在则返回null
	 */
	List<Member> findListByMobile(String Mobile);
	
	/**
	 * 查找会员分页
	 * 
	 * @param rankingType
	 *            排名类型
	 * @param pageable
	 *            分页信息
	 * @return 会员分页
	 */
	Page<Member> findPage(Pageable pageable);

	/**
	 * 查询会员注册数
	 * 
	 * @param beginDate
	 *            起始日期
	 * @param endDate
	 *            结束日期
	 * @return 会员注册数
	 */
	Long registerMemberCount(Date beginDate, Date endDate);


}