package com.ynyes.lyz.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.ynyes.lyz.entity.TdUserSuggestion;

public interface TdUserSuggestionRepo
		extends PagingAndSortingRepository<TdUserSuggestion, Long>, JpaSpecificationExecutor<TdUserSuggestion> {

}
