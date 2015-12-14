package com.ynyes.lyz.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.ynyes.lyz.entity.TdColorPackagePriceListItem;

public interface TdColorPackagePriceListItemRepo extends PagingAndSortingRepository<TdColorPackagePriceListItem, Long>,
		JpaSpecificationExecutor<TdColorPackagePriceListItem> {

	/**
	 * 根据价目表编号和调色包id查找调色包
	 * 
	 * @author dengxiao
	 */
	TdColorPackagePriceListItem findByColorPackagePriceListIdAndColorPackageId(Long colorPackagePriceListId,
			Long colorPackageId);
}
