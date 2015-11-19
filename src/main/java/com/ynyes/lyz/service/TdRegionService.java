package com.ynyes.lyz.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ynyes.lyz.entity.TdRegion;
import com.ynyes.lyz.repository.TdRegionRepo;

@Service
@Transactional
public class TdRegionService {

	@Autowired
	private TdRegionRepo repository;
	
	public TdRegion save(TdRegion entity){
		if(null == entity){
			return null;
		}
		return repository.save(entity);
	}
	
	public void delete(Long id){
		if(null != id){
			repository.delete(id);
		}
	}
	
	public TdRegion findOne(Long id){
		if(null == id){
			return null;
		}
		return repository.findOne(id);
	}
	
	public List<TdRegion> findAll(){
		return (List<TdRegion>) repository.findAll();
	}
	
	/**
	 * 根据城市名称查询到地区实体
	 * @author dengxiao
	 */
	public TdRegion findByCityName(String cityName){
		if(null == cityName){
			return null;
		}
		return repository.findByCityName(cityName);
	}
}
