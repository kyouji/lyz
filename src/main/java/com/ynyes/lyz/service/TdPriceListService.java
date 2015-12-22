package com.ynyes.lyz.service;

import java.util.List;

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


	public Page<TdPriceList> findAll(int page, int size){
		PageRequest pageRequest = new PageRequest(page, size, new Sort(Direction.ASC, "sortId"));
		return repository.findAll(pageRequest);
	}

}
