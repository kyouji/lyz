package com.ynyes.lyz.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.ynyes.lyz.entity.TdPriceList;
import com.ynyes.lyz.repository.TdPriceListRepo;

@Service
@Transactional
public class TdPriceListService {

	@Autowired
	private TdPriceListRepo repository;

	public TdPriceList save(TdPriceList e) {
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

	public TdPriceList findOne(Long id) {
		if (null == id) {
			return null;
		}
		return repository.findOne(id);
	}

	public List<TdPriceList> findAll() {
		return (List<TdPriceList>) repository.findAll();
	}

	/**
	 * 根据价目表编号和首页推荐查找价目表项（不分页）
	 * 
	 * @author dengxiao
	 */
	public List<TdPriceList> findByPriceListNumberAndIsCommendIndexTrueOrderBySortId(String priceListNumber) {
		if (null == priceListNumber) {
			return null;
		}
		return repository.findByPriceListNumberAndIsCommendIndexTrueOrderBySortId(priceListNumber);
	}

	/**
	 * 根据价目表编号和首页推荐查找价目表项（分页）
	 * 
	 * @author dengxiao
	 */
	public Page<TdPriceList> findByPriceListNumberAndIsCommendIndexTrueOrderBySortId(String priceListNumber, int size,
			int page) {
		if (null == priceListNumber) {
			return null;
		}
		PageRequest pageRequest = new PageRequest(page, size);
		return repository.findByPriceListNumberAndIsCommendIndexTrueOrderBySortId(priceListNumber, pageRequest);
	}

}