package com.ynyes.lyz.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.ynyes.lyz.entity.TdColorPackage;

public interface TdColorPackageRepo
		extends PagingAndSortingRepository<TdColorPackage, Long>, JpaSpecificationExecutor<TdColorPackage> {

	// 根据商品id和地区id查找调色包
	List<TdColorPackage> findByGoodsIdAndRegionIdOrderBySortIdAsc(Long goodsId, Long regionId);
	
	//根据调色包名查找调色包
	TdColorPackage findByName(String name);
	
	//根据调色包编号查找调色包
	TdColorPackage findByNumber(String number);
}
