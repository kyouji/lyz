package com.ynyes.lyz.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ynyes.lyz.entity.TdMessageType;
import com.ynyes.lyz.repository.TdMessageTypeRepo;

@Service
@Transactional
public class TdMessageTypeService {

	@Autowired
	private TdMessageTypeRepo repository;

	public TdMessageType save(TdMessageType e) {
		if (null == e) {
			return null;
		}
		return repository.save(e);
	};
	
	public void delete(Long id){
		if(null != id){
			repository.delete(id);
		}
	}
	
	public TdMessageType findOne(Long id){
		if(null == id){
			return null;
		}
		return repository.findOne(id);
	}
	
	public List<TdMessageType> findAll(){
		return (List<TdMessageType>) repository.findAll();
	}
	
	/**
	 * 查找所有能够使用的消息类型
	 * @author dengxiao
	 */
	public List<TdMessageType> findByIsEnableTrueOrderBySortIdAsc(){
		return repository.findByIsEnableTrueOrderBySortIdAsc();
	}
}
