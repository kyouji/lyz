package com.ynyes.lyz.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ynyes.lyz.entity.TdColorPackagePriceListItem;
import com.ynyes.lyz.repository.TdColorPackagePriceListItemRepo;

@Service
@Transactional
public class TdColorPackagePriceListItemService {

	@Autowired
	private TdColorPackagePriceListItemRepo repository;

	public TdColorPackagePriceListItem save(TdColorPackagePriceListItem e) {
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

	public TdColorPackagePriceListItem findOne(Long id) {
		if (null == id) {
			return null;
		}
		return repository.findOne(id);
	}

	public List<TdColorPackagePriceListItem> findAll() {
		return (List<TdColorPackagePriceListItem>) repository.findAll();
	}

	/**
	 * 根据价目表id和调色包id查找调色包价目表项
	 * 
	 * @author dengxiao
	 */
	public TdColorPackagePriceListItem findByColorPackagePriceListIdAndColorPackageId(Long colorPackagePriceListId,
			Long colorPackageId) {
		if (null == colorPackagePriceListId || null == colorPackageId) {
			return null;
		}
		return repository.findByColorPackagePriceListIdAndColorPackageId(colorPackagePriceListId, colorPackageId);
	}

}
