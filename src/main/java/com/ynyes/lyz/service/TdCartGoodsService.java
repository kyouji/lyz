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
	 * 删除用户所有的已选
	 * 
	 * @author dengxiao
	 */
	public void deleteAll(List<TdCartGoods> all){
		if(null != all){
			repository.delete(all);
		}
	}

	/**
	 * 根据用户名查找购物车
	 * 
	 * @author dengxiao
	 */
	public List<TdCartGoods> findByUsername(String username) {
		if (null == username) {
			return null;
		}
		return repository.findByUsername(username);
	}

	/**
	 * 根据用户名和商品id查找购物车项
	 * 
	 * @author dengxiao
	 */
	public TdCartGoods findByUsernameAndGoodsId(String username, Long goodsId) {
		if (null == username || null == goodsId) {
			return null;
		}
		return repository.findByUsernameAndGoodsId(username, goodsId);
	}

}
