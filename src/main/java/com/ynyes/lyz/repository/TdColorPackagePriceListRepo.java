package com.ynyes.lyz.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.ynyes.lyz.entity.TdColorPackagePriceList;

public interface TdColorPackagePriceListRepo extends PagingAndSortingRepository<TdColorPackagePriceList, Long>,
		JpaSpecificationExecutor<TdColorPackagePriceList> {

}
