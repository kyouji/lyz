package com.ynyes.lyz.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ynyes.lyz.entity.TdUserSuggestionCategory;
import com.ynyes.lyz.repository.TdUserSuggestionCategoryRepo;

@Service
@Transactional
public class TdUserSuggestionCategoryService {

	@Autowired
	private TdUserSuggestionCategoryRepo repository;

	public TdUserSuggestionCategory save(TdUserSuggestionCategory e) {
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

	public TdUserSuggestionCategory findOne(Long id) {
		if (null == id) {
			return null;
		}
		return repository.findOne(id);
	}

	public List<TdUserSuggestionCategory> findAll() {
		return (List<TdUserSuggestionCategory>) repository.findAll();
	}

	/**
	 * 查找所有能用的咨询分类并根据sortId正序排序
	 * 
	 * @author dengxiao
	 */
	public List<TdUserSuggestionCategory> findByIsEnableTrueOrderBySortIdAsc() {
		return repository.findByIsEnableTrueOrderBySortIdAsc();
	}
}
