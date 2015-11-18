package com.ynyes.lyz.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.ynyes.lyz.entity.TdRegion;

public interface TdRegionRepo extends PagingAndSortingRepository<TdRegion, Long>, JpaSpecificationExecutor<TdRegion> {

	// 根据下属城市的名称查找到开通了配送业务的省会城市
	TdRegion findBySubCityNameAndCitySendTrue(String subCityName);
}
