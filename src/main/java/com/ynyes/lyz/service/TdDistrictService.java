package com.ynyes.lyz.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ynyes.lyz.entity.TdDistrict;
import com.ynyes.lyz.repository.TdDistrictRepo;

@Service
@Transactional
public class TdDistrictService {

	@Autowired
	private TdDistrictRepo repository;

	public TdDistrict save(TdDistrict e) {
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

	public TdDistrict findOne(Long id) {
		if (null == id) {
			return null;
		}
		return repository.findOne(id);
	}

	public List<TdDistrict> findAll() {
		return (List<TdDistrict>) repository.findAll();
	}

	/**
	 * 根据所属城市id查找行政区划，并根据排序号正序排序
	 * 
	 * @author dengxiao
	 */
	public List<TdDistrict> findByCityIdOrderBySortIdAsc(Long cityId) {
		if (null == cityId) {
			return null;
		}
		return repository.findByCityIdOrderBySortIdAsc(cityId);
	}
}
