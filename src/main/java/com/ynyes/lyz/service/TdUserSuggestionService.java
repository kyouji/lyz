package com.ynyes.lyz.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ynyes.lyz.entity.TdUserSuggestion;
import com.ynyes.lyz.repository.TdUserSuggestionRepo;

@Service
@Transactional
public class TdUserSuggestionService {

	@Autowired
	private TdUserSuggestionRepo repository;
	
	public TdUserSuggestion save(TdUserSuggestion e){
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
	
	public TdUserSuggestion findOne(Long id){
		if(null == id){
			return null;
		}
		return repository.findOne(id);
	}
	
	public List<TdUserSuggestion> findAll(){
		return (List<TdUserSuggestion>) repository.findAll();
	}
}
