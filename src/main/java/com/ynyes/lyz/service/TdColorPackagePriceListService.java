package com.ynyes.lyz.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ynyes.lyz.entity.TdColorPackagePriceList;
import com.ynyes.lyz.repository.TdColorPackagePriceListRepo;

@Service
@Transactional
public class TdColorPackagePriceListService {

	@Autowired
	private TdColorPackagePriceListRepo repository;
	
	public TdColorPackagePriceList save(TdColorPackagePriceList e){
		if(null == e){
			return null;
		}
		return repository.save(e);
	}
	
	public void delete(Long id){
		if(null != id){
			repository.delete(id);
		}
	}
	
	public TdColorPackagePriceList findOne(Long id){
		if(null == id){
			return null;
		}
		return repository.findOne(id);
	}
	
	public List<TdColorPackagePriceList> findAll(){
		return (List<TdColorPackagePriceList>) repository.findAll();
	}
}
