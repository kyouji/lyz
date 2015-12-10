package com.ynyes.lyz.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.ynyes.lyz.entity.TdPriceList;

public interface TdPriceListRepo
		extends PagingAndSortingRepository<TdPriceList, Long>, JpaSpecificationExecutor<TdPriceList> {


	/**
	 * 根据价目表编号和首页推荐查找价目表项（分页）
	 * 
	 * @author dengxiao
	 */
	Page<TdPriceList> findByPriceListNumberAndIsCommendIndexTrueOrderBySortIdAsc(String priceListNumber, Pageable page);
}
