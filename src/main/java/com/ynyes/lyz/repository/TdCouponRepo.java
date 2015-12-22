package com.ynyes.lyz.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.ynyes.lyz.entity.TdCoupon;

public interface TdCouponRepo extends PagingAndSortingRepository<TdCoupon, Long>, JpaSpecificationExecutor<TdCoupon> {

	/**
	 * 查找指定用户所有的优惠券（按照获取时间倒序排序）
	 * 
	 * @author dengxiao
	 */
	List<TdCoupon> findByUserIdOrderByGetTimeDesc(Long userId);

	/**
	 * 查找指定用户已经使用的优惠券（按照使用时间倒序排序）
	 * 
	 * @author dengxiao
	 */
	List<TdCoupon> findByUserIdAndIsUsedTrueOrderByUseTimeDesc(Long userId);

	/**
	 * 查找指定用户已经过期的优惠券（按照过期时间倒序排序）
	 * 
	 * @author dengxiao
	 */
	List<TdCoupon> findByUserIdAndIsOutDateTrueOrderByFinishTimeDesc(Long userId);

	/**
	 * 查找指定用户未过期并未使用的优惠券（按照获取时间倒序排序）
	 * 
	 * @author dengxiao
	 */
	List<TdCoupon> findByUserIdAndIsOutDateFalseAndIsUsedFalseOrderByGetTimeDesc(Long userId);
}
