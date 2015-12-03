package com.ynyes.lyz.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.ynyes.lyz.entity.TdPriceList;

public interface TdPriceListRepo
		extends PagingAndSortingRepository<TdPriceList, Long>, JpaSpecificationExecutor<TdPriceList> {

	/**
	 * 根据价目表编号和首页推荐查找价目表项（不分页）
	 * 
	 * @author dengxiao
	 */
	List<TdPriceList> findByPriceListNumberAndIsCommendIndexTrueOrderBySortId(String priceListNumber);

	/**
	 * 根据价目表编号和首页推荐查找价目表项（分页）
	 * 
	 * @author dengxiao
	 */
	Page<TdPriceList> findByPriceListNumberAndIsCommendIndexTrueOrderBySortId(String priceListNumber, Pageable page);
}
