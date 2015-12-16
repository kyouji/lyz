package com.ynyes.lyz.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.ynyes.lyz.entity.TdPriceListItem;

public interface TdPriceListItemRepo
		extends PagingAndSortingRepository<TdPriceListItem, Long>, JpaSpecificationExecutor<TdPriceListItem> {

	/**
	 * 根据价目表编号和首页推荐查找价目表项（分页）
	 * 
	 * @author dengxiao
	 */
	Page<TdPriceListItem> findByPriceListIdAndIsCommendIndexTrueOrderBySortIdAsc(Long PriceListId,
			Pageable page);

	/**
	 * 根据价目表编号查找所有参加促销的商品（不分页）
	 * 
	 * @author dengxiao
	 */

	List<TdPriceListItem> findByPriceListIdAndIsPromotionTrueOrderBySortIdAsc(Long PriceListId);

	/**
	 * @author lc
	 * @注释：搜索
	 */
	Page<TdPriceListItem> findByPriceListIdContainingOrpriceListNameContainingOrcityNameContainingOrCompanyNameContaining(
			String keyword, String keyword1, String keyword2, String keyword3, Pageable page);

	/**
	 * 根据价目表编号和商品id查找价目表项
	 * 
	 * @author dengxiao
	 */
	TdPriceListItem findByPriceListIdAndGoodsId(Long PriceListId, Long goodsId);

}
