package com.ynyes.lyz.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.ynyes.lyz.entity.TdUserRecentVisit;

/**
 * TdUserRecentVisit 实体数据库操作接口
 * 
 * @author Sharon
 *
 */

public interface TdUserRecentVisitRepo extends
		PagingAndSortingRepository<TdUserRecentVisit, Long>,
		JpaSpecificationExecutor<TdUserRecentVisit> 
{
    Page<TdUserRecentVisit> findByUsernameOrderByVisitTimeDesc(String username, Pageable page);
    
    Page<TdUserRecentVisit> findByUsernameAndGoodsTitleContainingOrderByVisitTimeDesc(String username, String keywords, Pageable page);
    
    List<TdUserRecentVisit> findByUsername(String username);
    
    TdUserRecentVisit findByUsernameAndGoodsId(String username, Long goodsId);
    
    //根据用户id查找所有的浏览记录，按浏览时间倒序排序
    List<TdUserRecentVisit> findByUserIdOrderByVisitTimeDesc(Long userId);
    
    //查找指定用户最早的一个浏览记录
    TdUserRecentVisit findTopByUserIdOrderByVisitTimeAsc(Long userId);
    
    //根据用户id查找所有的浏览记录，按照浏览时间正序排序
    List<TdUserRecentVisit> findByUserIdOrderByVisitTimeAsc(Long userId);
}
