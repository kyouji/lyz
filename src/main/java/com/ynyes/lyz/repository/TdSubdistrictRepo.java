package com.ynyes.lyz.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.ynyes.lyz.entity.TdSubdistrict;

public interface TdSubdistrictRepo
		extends PagingAndSortingRepository<TdSubdistrict, Long>, JpaSpecificationExecutor<TdSubdistrict> {

	/**
	 * 根据所属区划id查找街道，并按照排序号正序排序
	 * 
	 * @author dengxiao
	 */
	List<TdSubdistrict> findByDistrictIdOrderBySortIdAsc(Long districtId);
}
