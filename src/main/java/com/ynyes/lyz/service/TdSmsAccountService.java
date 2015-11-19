package com.ynyes.lyz.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ynyes.lyz.entity.TdSmsAccount;
import com.ynyes.lyz.repository.TdSmsAccountRepo;

@Service
@Transactional
public class TdSmsAccountService {

	@Autowired
	private TdSmsAccountRepo repository;
	
	public TdSmsAccount save(TdSmsAccount entity){
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
	
	public TdSmsAccount findOne(Long id){
		if(null == id){
			return null;
		}
		return repository.findOne(id);
	}
	
	public List<TdSmsAccount> findAll(){
		return (List<TdSmsAccount>) repository.findAll();
	}
}
