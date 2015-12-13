package com.ynyes.lyz.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.ynyes.lyz.entity.TdPriceList;
import com.ynyes.lyz.entity.TdPriceListItem;
import com.ynyes.lyz.repository.TdPriceListItemRepo;

@Service
@Transactional
public class TdPriceListItemService {

	@Autowired
	private TdPriceListItemRepo repository;

	public TdPriceListItem save(TdPriceListItem e) {
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

	public TdPriceListItem findOne(Long id) {
		if (null == id) {
			return null;
		}
		return repository.findOne(id);
	}

	public List<TdPriceListItem> findAll() {
		return (List<TdPriceListItem>) repository.findAll();
	}

	/**
	 * 根据价目表编号和首页推荐查找价目表项（分页）
	 * 
	 * @author dengxiao
	 */
	public Page<TdPriceListItem> findByPriceListNumberAndIsCommendIndexTrueOrderBySortIdAsc(String priceListNumber,
			int size, int page) {
		if (null == priceListNumber) {
			return null;
		}
		PageRequest pageRequest = new PageRequest(page, size);
		return repository.findByPriceListNumberAndIsCommendIndexTrueOrderBySortIdAsc(priceListNumber, pageRequest);
	}

	/**
	 * 查找所有参与促销的商品（不分页）
	 * 
	 * @author dengxiao
	 */
	public List<TdPriceListItem> findByPriceListNumberAndIsPromotionTrueOrderBySortIdAsc(String priceListNumber){
		if(null == priceListNumber){
			return null;
		}
		return repository.findByPriceListNumberAndIsPromotionTrueOrderBySortIdAsc(priceListNumber);
	};
	
	public Page<TdPriceList> searchAll(String keywords, int page, int size){		
		if (null == keywords) {
			return null;
		}
		PageRequest pageRequest = new PageRequest(page, size, new Sort(Direction.ASC, "sortId"));
		return repository.findBypriceListNumberContainingOrpriceListNameContainingOrcityNameContainingOrCompanyNameContaining(keywords, keywords, keywords, keywords, pageRequest);
	}
}
