package com.ynyes.lyz.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ynyes.lyz.entity.TdMessage;
import com.ynyes.lyz.repository.TdMessageRepo;

@Service
@Transactional
public class TdMessageService {

	@Autowired
	private TdMessageRepo repository;

	public TdMessage save(TdMessage e) {
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

	public TdMessage findOne(Long id) {
		if (null == id) {
			return null;
		}
		return repository.findOne(id);
	}

	public List<TdMessage> findAll() {
		return (List<TdMessage>) repository.findAll();
	}

	/**
	 * 根据消息类型和用户id查找消息
	 * 
	 * @author dengxiao
	 */
	public List<TdMessage> findByTypeIdAndUserIdOrderByCreateTimeDesc(Long typeId, Long userId) {
		if (null == typeId || null == userId) {
			return null;
		}
		return repository.findByTypeIdAndUserIdOrderByCreateTimeDesc(typeId, userId);
	}

	/**
	 * 根据消息类型和用户id查找未读的消息
	 * 
	 * @author dengxiao
	 */
	public List<TdMessage> findByTypeIdAndUserIdAndIsReadFalseOrderByCreateTimeDesc(Long typeId, Long userId) {
		if (null == typeId || null == userId) {
			return null;
		}
		return repository.findByTypeIdAndUserIdAndIsReadFalseOrderByCreateTimeDesc(typeId, userId);
	}

}
