package com.ynyes.lyz.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.ynyes.lyz.entity.TdDiySite;

/**
 * TdDiySite 实体数据库操作接口
 * 
 * @author Sharon
 *
 */

public interface TdDiySiteRepo
		extends PagingAndSortingRepository<TdDiySite, Long>, JpaSpecificationExecutor<TdDiySite> {
	Page<TdDiySite> findByTitleContainingOrderBySortIdAsc(String keywords, Pageable page);

	List<TdDiySite> findByIsEnableTrue();

	/**
	 * 通过行政区划id查找其下属的所有门店
	 * 
	 * @author dengxiao
	 */
	List<TdDiySite> findByDisctrictIdOrderBySortIdAsc(Long districtId);
}
