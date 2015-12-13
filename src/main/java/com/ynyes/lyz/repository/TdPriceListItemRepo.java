package com.ynyes.lyz.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.ynyes.lyz.entity.TdPriceList;
import com.ynyes.lyz.entity.TdPriceListItem;

public interface TdPriceListItemRepo
		extends PagingAndSortingRepository<TdPriceListItem, Long>, JpaSpecificationExecutor<TdPriceListItem> {

	/**
	 * 根据价目表编号和首页推荐查找价目表项（分页）
	 * 
	 * @author dengxiao
	 */
	Page<TdPriceListItem> findByPriceListNumberAndIsCommendIndexTrueOrderBySortIdAsc(String priceListNumber, Pageable page);

	/**
	 * 根据价目表编号查找所有参加促销的商品（不分页）
	 * 
	 * @author dengxiao
	 */
	List<TdPriceListItem> findByPriceListNumberAndIsPromotionTrueOrderBySortIdAsc(String priceListNumber);
	
	/**
	 * @author lc
	 * @注释：搜索
	 */
	Page<TdPriceList> findBypriceListNumberContainingOrpriceListNameContainingOrcityNameContainingOrCompanyNameContaining(String keyword, String keyword1, String keyword2, String keyword3, Pageable page);
}
