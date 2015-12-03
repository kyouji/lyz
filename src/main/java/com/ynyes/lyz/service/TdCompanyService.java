package com.ynyes.lyz.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ynyes.lyz.entity.TdCompany;
import com.ynyes.lyz.repository.TdCompanyRepo;

@Service
@Transactional
public class TdCompanyService {

	@Autowired
	private TdCompanyRepo repository;

	public TdCompany save(TdCompany e) {
		if (null == e) {
			return null;
		}
		return repository.save(e);
	}

	public void delete(Long id) {
		if (null != id) {
			repository.delete(id);
		}
	}

	public TdCompany findOne(Long id) {
		if (null == id) {
			return null;
		}
		return repository.findOne(id);
	}
	
	public List<TdCompany> findAll(){
		return (List<TdCompany>) repository.findAll();
	}  
}
