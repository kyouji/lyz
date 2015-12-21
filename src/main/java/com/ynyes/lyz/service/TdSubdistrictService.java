package com.ynyes.lyz.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ynyes.lyz.entity.TdSubdistrict;
import com.ynyes.lyz.repository.TdSubdistrictRepo;

@Service
@Transactional
public class TdSubdistrictService {

	@Autowired
	private TdSubdistrictRepo repository;

	public TdSubdistrict save(TdSubdistrict e) {
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

	public TdSubdistrict findOne(Long id) {
		if (null == id) {
			return null;
		}
		return repository.findOne(id);
	}

	public List<TdSubdistrict> findAll() {
		return (List<TdSubdistrict>) repository.findAll();
	}

	/**
	 * 根据所属区划id查找街道，并按照排序号正序排序
	 * 
	 * @author dengxiao
	 */
	public List<TdSubdistrict> findByDistrictIdOrderBySortIdAsc(Long districtId) {
		if (null == districtId) {
			return null;
		}
		return repository.findByDistrictIdOrderBySortIdAsc(districtId);
	}
}
