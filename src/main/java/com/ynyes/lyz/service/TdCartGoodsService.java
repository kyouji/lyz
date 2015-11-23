package com.ynyes.lyz.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ynyes.lyz.entity.TdCartGoods;
import com.ynyes.lyz.repository.TdCartGoodsRepo;

@Service
@Transactional
public class TdCartGoodsService {

	@Autowired
	private TdCartGoodsRepo repository;

	public TdCartGoods save(TdCartGoods entity) {
		if (null == entity) {
			return null;
		}
		return repository.save(entity);
	}

	public void delete(Long id) {
		if (null != id) {
			repository.delete(id);
		}
	}

	public TdCartGoods findOne(Long id) {
		if (null == id) {
			return null;
		}
		return repository.findOne(id);
	}

	public List<TdCartGoods> findAll() {
		return (List<TdCartGoods>) repository.findAll();
	}
	
	/**
	 * 根据用户名查找购物车
	 * @author dengxiao
	 */
	public List<TdCartGoods> findByUsername(String username){
		if(null == username){
			return null;
		}
		return repository.findByUsername(username);
	}

}
