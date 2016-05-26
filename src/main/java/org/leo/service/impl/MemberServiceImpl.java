/*
 * Copyright 2005-2015 jshop.com. All rights reserved.
 * File Head

 */
package org.leo.service.impl;

import java.math.BigDecimal;
import java.util.List;

import javax.annotation.Resource;
import javax.persistence.LockModeType;

import org.apache.commons.lang.BooleanUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import org.leo.Page;
import org.leo.Pageable;
//import org.leo.Principal;
import org.leo.Setting;
//import org.leo.dao.DepositLogDao;
import org.leo.dao.MemberDao;
//import org.leo.dao.MemberRankDao;
//import org.leo.dao.PointLogDao;
//import org.leo.entity.Admin;
//import org.leo.entity.DepositLog;
import org.leo.entity.Member;
//import org.leo.entity.MemberRank;
//import org.leo.entity.PointLog;
//import org.leo.service.MailService;
import org.leo.service.MemberService;
//import org.leo.service.SmsService;
import org.leo.util.SystemUtils;

/**
 * Service - 会员
 * 
 * @author JSHOP Team
 \* @version 3.X
 */
@Service("memberServiceImpl")
public class MemberServiceImpl extends BaseServiceImpl<Member, Long> implements MemberService {

	@Resource(name = "memberDaoImpl")
	private MemberDao memberDao;


	@Transactional(readOnly = true)
	public boolean usernameExists(String username) {
		return memberDao.usernameExists(username);
	}

	@Transactional(readOnly = true)
	public boolean usernameDisabled(String username) {
		Assert.hasText(username);

		Setting setting = SystemUtils.getSetting();
		if (setting.getDisabledUsernames() != null) {
			for (String disabledUsername : setting.getDisabledUsernames()) {
				if (StringUtils.containsIgnoreCase(username, disabledUsername)) {
					return true;
				}
			}
		}
		return false;
	}
	
	@Transactional(readOnly = true)
	public boolean mobileExists(String mobile){
		return memberDao.mobileExists(mobile);
	}
	
	@Transactional(readOnly = true)
	public boolean emailExists(String email) {
		return memberDao.emailExists(email);
	}

	@Transactional(readOnly = true)
	public boolean emailUnique(String previousEmail, String currentEmail) {
		if (StringUtils.equalsIgnoreCase(previousEmail, currentEmail)) {
			return true;
		}
		return !memberDao.emailExists(currentEmail);
	}

	@Transactional(readOnly = true)
	public Member findByOpenId(String openId) {
		return memberDao.findByOpenId(openId);
	}
	
	@Transactional(readOnly = true)
	public Member findByUnionId(String unionId) {
		return memberDao.findByUnionId(unionId);
	}
	
	@Transactional(readOnly = true)
	public Member findByUsername(String username) {
		return memberDao.findByUsername(username);
	}

	@Transactional(readOnly = true)
	public List<Member> findListByEmail(String email) {
		return memberDao.findListByEmail(email);
	}
	
	@Transactional(readOnly = true)
	public List<Member> findListByMobile(String mobile) {
		return memberDao.findListByMobile(mobile);
	}

	@Transactional(readOnly = true)
	public Page<Member> findPage( Pageable pageable) {
		return memberDao.findPage( pageable);
	}

	@Transactional(readOnly = true)
	public boolean isAuthenticated() {
		RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();
		return requestAttributes != null && requestAttributes.getAttribute(Member.PRINCIPAL_ATTRIBUTE_NAME, RequestAttributes.SCOPE_SESSION) != null;
	}

/*	@Transactional(readOnly = true)
	public Member getCurrent() {
		return getCurrent(false);
	}*/

/*	@Transactional(readOnly = true)
	public Member getCurrent(boolean lock) {
		RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();
		Principal principal = requestAttributes != null ? (Principal) requestAttributes.getAttribute(Member.PRINCIPAL_ATTRIBUTE_NAME, RequestAttributes.SCOPE_SESSION) : null;
		Long id = principal != null ? principal.getId() : null;
		if (lock) {
			return memberDao.find(id, LockModeType.PESSIMISTIC_WRITE);
		} else {
			return memberDao.find(id);
		}
	}

	@Transactional(readOnly = true)
	public String getCurrentUsername() {
		RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();
		Principal principal = requestAttributes != null ? (Principal) requestAttributes.getAttribute(Member.PRINCIPAL_ATTRIBUTE_NAME, RequestAttributes.SCOPE_SESSION) : null;
		return principal != null ? principal.getUsername() : null;
	}*/





	@Override
	@Transactional
	public Member save(Member member) {
		Assert.notNull(member);

		Member pMember = super.save(member);

		return pMember;
	}

}