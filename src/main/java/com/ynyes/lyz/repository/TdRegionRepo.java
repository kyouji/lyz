package com.ynyes.lyz.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.ynyes.lyz.entity.TdRegion;

public interface TdRegionRepo extends PagingAndSortingRepository<TdRegion, Long>, JpaSpecificationExecutor<TdRegion> {

	// 根据城市名称查询地区实体
	TdRegion findByCityName(String cityName);
}
