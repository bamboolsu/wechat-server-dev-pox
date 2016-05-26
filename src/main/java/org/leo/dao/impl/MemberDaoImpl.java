/*
 * Copyright 2005-2015 jshop.com. All rights reserved.
 * File Head

 */
package org.leo.dao.impl;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import org.leo.Page;
import org.leo.Pageable;
import org.leo.dao.MemberDao;
import org.leo.entity.Member;
//import org.leo.entity.MemberAttribute;

/**
 * Dao - 会员
 * 
 * @author JSHOP Team
 \* @version 3.X
 */
@Repository("memberDaoImpl")
public class MemberDaoImpl extends BaseDaoImpl<Member, Long> implements MemberDao {

	public boolean usernameExists(String username) {
		if (StringUtils.isEmpty(username)) {
			return false;
		}
		String jpql = "select count(*) from Member members where lower(members.username) = lower(:username)";
		Long count = entityManager.createQuery(jpql, Long.class).setParameter("username", username).getSingleResult();
		return count > 0;
	}
	
	public boolean mobileExists(String mobile){
		if(StringUtils.isEmpty(mobile)){
			return false;
		}
		String jpql = "select count(*) from Member members where members.mobile =:mobile";
		Long count = entityManager.createQuery(jpql,Long.class).setParameter("mobile", mobile).getSingleResult();
		return count > 0;
	}
	
	public boolean emailExists(String email) {
		if (StringUtils.isEmpty(email)) {
			return false;
		}
		String jpql = "select count(*) from Member members where lower(members.email) = lower(:email)";
		Long count = entityManager.createQuery(jpql, Long.class).setParameter("email", email).getSingleResult();
		return count > 0;
	}

	public Member findByOpenId(String openId) {
		if (StringUtils.isEmpty(openId)) {
			return null;
		}
		try {
			String jpql = "select members from Member members where members.openId = :openId";
			return entityManager.createQuery(jpql, Member.class).setParameter("openId", openId).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	public Member findByUnionId(String unionId) {
		if (StringUtils.isEmpty(unionId)) {
			return null;
		}
		try {
			String jpql = "select members from Member members where members.unionId = :unionId";
			return entityManager.createQuery(jpql, Member.class).setParameter("unionId", unionId).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}
	
	public Member findByUsername(String username) {
		if (StringUtils.isEmpty(username)) {
			return null;
		}
		try {
			String jpql = "select members from Member members where lower(members.username) = lower(:username)";
			return entityManager.createQuery(jpql, Member.class).setParameter("username", username).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	public List<Member> findListByEmail(String email) {
		if (StringUtils.isEmpty(email)) {
			return Collections.emptyList();
		}
		String jpql = "select members from Member members where lower(members.email) = lower(:email)";
		return entityManager.createQuery(jpql, Member.class).setParameter("email", email).getResultList();
	}
	
	public List<Member> findListByMobile(String mobile) {
		if(StringUtils.isEmpty(mobile)){
			return Collections.emptyList();
		}
		String jpql = "select members from Member members where members.mobile =:mobile";
		return entityManager.createQuery(jpql, Member.class).setParameter("mobile", mobile).getResultList();
	}
	
	public Page<Member> findPage(Pageable pageable) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Member> criteriaQuery = criteriaBuilder.createQuery(Member.class);
		Root<Member> root = criteriaQuery.from(Member.class);
		criteriaQuery.select(root);

		return super.findPage(criteriaQuery, pageable);
	}

	public Long registerMemberCount(Date beginDate, Date endDate) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Member> criteriaQuery = criteriaBuilder.createQuery(Member.class);
		Root<Member> root = criteriaQuery.from(Member.class);
		criteriaQuery.select(root);
		Predicate restrictions = criteriaBuilder.conjunction();
		if (beginDate != null) {
			restrictions = criteriaBuilder.and(restrictions, criteriaBuilder.greaterThanOrEqualTo(root.<Date> get("createDate"), beginDate));
		}
		if (endDate != null) {
			restrictions = criteriaBuilder.and(restrictions, criteriaBuilder.lessThanOrEqualTo(root.<Date> get("createDate"), endDate));
		}
		criteriaQuery.where(restrictions);
		return super.count(criteriaQuery, null);
	}



}