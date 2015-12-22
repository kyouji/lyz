package com.ynyes.lyz.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ynyes.lyz.entity.TdCoupon;
import com.ynyes.lyz.repository.TdCouponRepo;

@Service
@Transactional
public class TdCouponService {

	@Autowired
	private TdCouponRepo repository;
	
	public TdCoupon save(TdCoupon e){
		if(null == e){
			return null;
		}
		return repository.save(e);
	}
	
	public void delete(Long id){
		if(null != id){
			repository.delete(id);
		}
	}
	
	public TdCoupon findOne(Long id){
		if(null == id){
			return null;
		}
		return repository.findOne(id);
	}
	
	public List<TdCoupon> findAll(){
		return (List<TdCoupon>) repository.findAll();
	}
	
	/**
	 * 查找指定用户所有的优惠券（按照获取时间倒序排序）
	 * 
	 * @author dengxiao
	 */
	public List<TdCoupon> findByUserIdOrderByGetTimeDesc(Long userId){
		if(null == userId){
			return null;
		}
		return repository.findByUserIdOrderByGetTimeDesc(userId);
	}
	
	/**
	 * 查找指定用户已经使用的优惠券（按照使用时间倒序排序）
	 * 
	 * @author dengxiao
	 */
	public List<TdCoupon> findByUserIdAndIsUsedTrueOrderByUseTimeDesc(Long userId){
		if(null == userId){
			return null;
		}
		return repository.findByUserIdAndIsUsedTrueOrderByUseTimeDesc(userId);
	}
	
}
