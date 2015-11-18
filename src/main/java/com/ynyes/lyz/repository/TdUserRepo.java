package com.ynyes.lyz.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.ynyes.lyz.entity.TdUser;

public interface TdUserRepo extends PagingAndSortingRepository<TdUser, Long>, JpaSpecificationExecutor<TdUser> {

	TdUser findByUsernameAndPasswordAndIsEnableTrue(String username, String password);
	
	TdUser findByUsernameAndIsEnableTrue(String username);
	
	TdUser findByUsername(String username);
	
	TdUser findByUsernameAndCityNameAndIsEnableTrue(String username,String cityName);
	
}
