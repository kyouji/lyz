package com.ynyes.lyz.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ynyes.lyz.entity.TdCoupon;
import com.ynyes.lyz.entity.TdCouponType;
import com.ynyes.lyz.entity.TdDiySite;
import com.ynyes.lyz.repository.TdCouponRepo;

/**
 * TdCoupon 服务类
 * 
 * @author Sharon
 *
 */

@Service
@Transactional
public class TdCouponService {
    @Autowired
    TdCouponRepo repository;
    
    @Autowired
    TdCouponTypeService tdCouponTypeService;
    
    @Autowired
    TdDiySiteService tdDiySiteService;
    
    /**
     * 删除
     * 
     * @param id 菜单项ID
     */
    public void delete(Long id)
    {
        if (null != id)
        {
            repository.delete(id);
        }
    }
    
    /**
     * 删除
     * 
     * @param e 菜单项
     */
    public void delete(TdCoupon e)
    {
        if (null != e)
        {
            repository.delete(e);
        }
    }
    
    public void delete(List<TdCoupon> entities)
    {
        if (null != entities)
        {
            repository.delete(entities);
        }
    }
    
    /**
     * 查找
     * 
     * @param id ID
     * @return
     */
    public TdCoupon findOne(Long id)
    {
        if (null == id)
        {
            return null;
        }
        
        return repository.findOne(id);
    }
    
    /**
     * 免费打蜡券
     * @author Zhangji
     */
    public TdCoupon findByDiySiteIdAndTypeTitle(Long diySiteId,String typeTitle)
    {
    	return repository.findByDiySiteIdAndTypeTitleAndIsDistributtedFalse(diySiteId,typeTitle);
    }
    
    /**
     * 查找
     * 
     * @param ids
     * @return
     */
    public List<TdCoupon> findAll(Iterable<Long> ids)
    {
        return (List<TdCoupon>) repository.findAll(ids);
    }
    
    public List<TdCoupon> findByUsernameAndIsUseable(String username)
    {
        if (null == username)
        {
            return null;
        }
        return repository.findByUsernameAndExpireTimeAfterAndIsDistributtedTrueAndIsUsedFalse(username, new Date());
    }
    
    public List<TdCoupon> findByUsernameAndIsUseable(String username, Long articleId)
    {
        if (null == username || null == articleId)
        {
            return null;
        }
        
        return repository.findByUsernameAndArticleIdAndIsUsedFalse(username, articleId);
    }
    
    public List<TdCoupon> findByMobileAndIsUseable(String mobile){
    	if (null == mobile)
        {
            return null;
        }
    	return repository.findByMobileAndExpireTimeAfterAndIsDistributtedTrueAndIsUsedFalse(mobile, new Date());
    }
    
    public List<TdCoupon> findByUsername(String username)
    {
        if (null == username)
        {
            return null;
        }
        return repository.findByUsernameAndIsDistributtedTrue(username);
    }
    
    public List<TdCoupon> findByMoblie(String mobile)
    {
        if (null == mobile)
        {
            return null;
        }
        return repository.findByMobileAndIsDistributtedTrue(mobile);
    } 
    
    public Page<TdCoupon> findAllOrderBySortIdAsc(int page, int size)
    {
        PageRequest pageRequest = new PageRequest(page, size, new Sort(Direction.ASC, "sortId"));
        
        return repository.findAll(pageRequest);
    }
    
    public List<TdCoupon> findAllOrderBySortIdAsc()
    {
        return (List<TdCoupon>) repository.findAll();
    }
    
    public List<TdCoupon> findDistinctTypeIdByIsDistributtedFalse()
    {
        return repository.findTypeIdDistinctByIsDistributtedFalse();
    }
    
    //列表查找，可领取优惠券 zhangji
    public List<TdCoupon> findByIsDistributtedFalseOrderBySortIdAsc()
    {
        return repository.findByIsDistributtedFalseOrderBySortIdAsc();
    }
    
    public List<TdCoupon> findByTypeIdAndIsDistributtedFalse(Long typeId)
    {
        return repository.findByTypeIdAndIsDistributtedFalse(typeId);
    }
    
    public List<TdCoupon> findByTypeIdAndIsDistributtedTrueOrderByIdDesc(Long typeId)
    {
        return repository.findByTypeIdAndIsDistributtedTrueOrderByIdDesc(typeId);
    }
    
    public TdCoupon findTopByTypeIdAndDiySiteIdAndIsDistributtedFalse(Long typeId, Long diySiteId)
    {
        if (null == typeId || null == diySiteId)
        {
            return null;
        }
        
        return repository.findTopByTypeIdAndDiySiteIdAndIsDistributtedFalse(typeId, diySiteId);
    }
    
    public Page<TdCoupon> findByIsDistributtedFalseOrderBySortIdAsc(int page, int size)
    {
        PageRequest pageRequest = new PageRequest(page, size);
        
        return repository.findByIsDistributtedFalseOrderBySortIdAsc(pageRequest);
    }
    
    public Page<TdCoupon> findByIsDistributtedTrueOrderByIdDesc(int page, int size)
    {
        PageRequest pageRequest = new PageRequest(page, size);
        
        return repository.findByIsDistributtedTrueOrderByIdDesc(pageRequest);
    }
    //zhangji  1
    public Page<TdCoupon> findByIsDistributtedTrueAndIsEnableTrueOrderByIdDesc(int page, int size)
    {
        PageRequest pageRequest = new PageRequest(page, size);
        
        return repository.findByIsDistributtedTrueAndIsEnableTrueOrderByIdDesc(pageRequest);
    }
    
    public Page<TdCoupon> findByTypeIdAndIsDistributtedTrueOrderByIdDesc(Long typeId,int page,int size)
    {
    	PageRequest pageRequest = new PageRequest(page, size);
    	return repository.findByTypeIdAndIsDistributtedTrueOrderByIdDesc(typeId, pageRequest);
    }
    
    //zhangji 2
    public Page<TdCoupon> findByTypeIdAndIsDistributtedTrueAndIsEnableTrueOrderByIdDesc(Long typeId,int page,int size)
    {
    	PageRequest pageRequest = new PageRequest(page, size);
    	return repository.findByTypeIdAndIsDistributtedTrueAndIsEnableTrueOrderByIdDesc(typeId, pageRequest);
    }
    
    //根据核销状态查找
    public Page<TdCoupon> findByIsDistributtedTrueAndIsUsedTrueOrderByIdDesc(int page, int size){
    	PageRequest pageRequest = new PageRequest(page, size);
    	return repository.findByIsDistributtedTrueAndIsUsedTrueOrderByIdDesc(pageRequest);
    }
    //zhangji 3
    public Page<TdCoupon> findByIsDistributtedTrueAndIsUsedTrueAndIsEnableTrueOrderByIdDesc(int page, int size){
    	PageRequest pageRequest = new PageRequest(page, size);
    	return repository.findByIsDistributtedTrueAndIsUsedTrueAndIsEnableTrueOrderByIdDesc(pageRequest);
    }
    public Page<TdCoupon> findByIsDistributtedTrueAndIsUsedFalseOrderByIdDesc(int page, int size){
    	PageRequest pageRequest = new PageRequest(page, size);
    	return repository.findByIsDistributtedTrueAndIsUsedFalseOrderByIdDesc(pageRequest);
    }
    
    //zhangji 5
    public Page<TdCoupon> findByIsDistributtedTrueAndIsUsedFalseAndIsEnableTrueOrderByIdDesc(int page, int size){
    	PageRequest pageRequest = new PageRequest(page, size);
    	return repository.findByIsDistributtedTrueAndIsUsedFalseAndIsEnableTrueOrderByIdDesc(pageRequest);
    }
    
    
    //根据同盟店id核销状态查找
    public Page<TdCoupon> findByIsDistributtedTrueAndDiySiteIdOrderByIdDesc(Long diysiteId, int page, int size)
    {
    	if (null == diysiteId) {
			return  null;
		}
        PageRequest pageRequest = new PageRequest(page, size);
        
        return repository.findByIsDistributtedTrueAndDiySiteIdOrderByIdDesc(diysiteId, pageRequest);
    }
    
    //zhangji 7
    public Page<TdCoupon> findByIsDistributtedTrueAndDiySiteIdAndIsEnableTrueOrderByIdDesc(Long diysiteId, int page, int size)
    {
    	if (null == diysiteId) {
			return  null;
		}
        PageRequest pageRequest = new PageRequest(page, size);
        
        return repository.findByIsDistributtedTrueAndDiySiteIdAndIsEnableTrueOrderByIdDesc(diysiteId, pageRequest);
    }
    
    public Page<TdCoupon> findByIsDistributtedTrueAndDiySiteIdAndIsUsedTrueOrderByIdDesc(Long diysiteId, int page, int size)
    {
    	if (null == diysiteId) {
			return  null;
		}
        PageRequest pageRequest = new PageRequest(page, size);
        
        return repository.findByIsDistributtedTrueAndDiySiteIdAndIsUsedTrueOrderByIdDesc(diysiteId, pageRequest);
    }
    
    //zhangji 8
    public Page<TdCoupon> findByIsDistributtedTrueAndDiySiteIdAndIsUsedTrueAndIsEnableTrueOrderByIdDesc(Long diysiteId, int page, int size)
    {
    	if (null == diysiteId) {
			return  null;
		}
        PageRequest pageRequest = new PageRequest(page, size);
        
        return repository.findByIsDistributtedTrueAndDiySiteIdAndIsUsedTrueAndIsEnableTrueOrderByIdDesc(diysiteId, pageRequest);
    }
    public Page<TdCoupon> findByIsDistributtedTrueAndDiySiteIdAndIsUsedFalseOrderByIdDesc(Long diysiteId, int page, int size)
    {
    	if (null == diysiteId) {
			return  null;
		}
        PageRequest pageRequest = new PageRequest(page, size);
        
        return repository.findByIsDistributtedTrueAndDiySiteIdAndIsUsedFalseOrderByIdDesc(diysiteId, pageRequest);
    }
    
    //zhangji 10
    public Page<TdCoupon> findByIsDistributtedTrueAndDiySiteIdAndIsUsedFalseAndIsEnableTrueOrderByIdDesc(Long diysiteId, int page, int size)
    {
    	if (null == diysiteId) {
			return  null;
		}
        PageRequest pageRequest = new PageRequest(page, size);
        
        return repository.findByIsDistributtedTrueAndDiySiteIdAndIsUsedFalseAndIsEnableTrueOrderByIdDesc(diysiteId, pageRequest);
    }
    
    //根据搜索关键字和同盟店id和核销状态查找
    public Page<TdCoupon> findByIsDistributtedTrueAndContainingKeywords(String keywords, int page, int size){
    	PageRequest pageRequest = new PageRequest(page, size);
    	
    	return repository.findByIsDistributtedTrueAndUsernameContainingOrIsDistributtedTrueAndMobileContainingOrIsDistributtedTrueAndCarCodeContainingOrderByIdDesc(keywords,keywords,keywords,pageRequest);
    }
    
    //zhangji 13
    public Page<TdCoupon> findByIsDistributtedTrueAndIsEnableTrueAndContainingKeywords(String keywords, int page, int size){
    	PageRequest pageRequest = new PageRequest(page, size);
    	
    	return repository.findByIsDistributtedTrueAndIsEnableTrueAndUsernameContainingOrIsDistributtedTrueAndIsEnableTrueAndMobileContainingOrIsDistributtedTrueAndIsEnableTrueAndCarCodeContainingOrderByIdDesc(keywords,keywords,keywords,pageRequest);
    }
    public Page<TdCoupon> findByIsDistributtedTrueAndIsUsedTrueAndContainingKeywords(String keywords, int page, int size){
    	PageRequest pageRequest = new PageRequest(page, size);
    	
    	return repository.findByIsDistributtedTrueAndIsUsedTrueAndUsernameContainingOrIsDistributtedTrueAndIsUsedTrueAndMobileContainingOrIsDistributtedTrueAndIsUsedTrueAndCarCodeContainingOrderByIdDesc(keywords,keywords,keywords,pageRequest);
    }
    
    //zhangji 15
    public Page<TdCoupon> findByIsDistributtedTrueAndIsEnableTrueAndIsUsedTrueAndContainingKeywords(String keywords, int page, int size){
    	PageRequest pageRequest = new PageRequest(page, size);
    	
    	return repository.findByIsDistributtedTrueAndIsEnableTrueAndIsUsedTrueAndUsernameContainingOrIsDistributtedTrueAndIsEnableTrueAndIsUsedTrueAndMobileContainingOrIsDistributtedTrueAndIsEnableTrueAndIsUsedTrueAndCarCodeContainingOrderByIdDesc(keywords,keywords,keywords,pageRequest);
    }
    
    public Page<TdCoupon> findByIsDistributtedTrueAndIsUsedFalseAndContainingKeywords(String keywords, int page, int size){
    	PageRequest pageRequest = new PageRequest(page, size);
    	
    	return repository.findByIsDistributtedTrueAndIsUsedFalseAndUsernameContainingOrIsDistributtedTrueAndIsUsedFalseAndMobileContainingOrIsDistributtedTrueAndIsUsedFalseAndCarCodeContainingOrderByIdDesc(keywords,keywords,keywords,pageRequest);
    }
    
    //zhangji 17
    public Page<TdCoupon> findByIsDistributtedTrueAndIsEnableTrueAndIsUsedFalseAndContainingKeywords(String keywords, int page, int size){
    	PageRequest pageRequest = new PageRequest(page, size);
    	
    	return repository.findByIsDistributtedTrueAndIsEnableTrueAndIsUsedFalseAndUsernameContainingOrIsDistributtedTrueAndIsEnableTrueAndIsUsedFalseAndMobileContainingOrIsDistributtedTrueAndIsEnableTrueAndIsUsedFalseAndCarCodeContainingOrderByIdDesc(keywords,keywords,keywords,pageRequest);
    }
    public Page<TdCoupon> findByIsDistributtedTrueAndDiySiteIdAndContainingKeywordsOrderBySortIdAsc(Long diysiteId, String keywords, int page, int size)
    {
    	if (null == diysiteId) {
			return  null;
		}
        PageRequest pageRequest = new PageRequest(page, size);
        
        return repository.findByIsDistributtedTrueAndDiySiteIdAndUsernameContainingOrIsDistributtedTrueAndDiySiteIdAndMobileContainingOrIsDistributtedTrueAndDiySiteIdAndCarCodeContainingOrderByIdDesc(diysiteId, keywords, diysiteId, keywords, diysiteId, keywords, pageRequest);
    }
    
    //zhangji 19
    public Page<TdCoupon> findByIsDistributtedTrueAndIsEnableTrueAndDiySiteIdAndContainingKeywordsOrderBySortIdAsc(Long diysiteId, String keywords, int page, int size)
    {
    	if (null == diysiteId) {
			return  null;
		}
        PageRequest pageRequest = new PageRequest(page, size);
        
        return repository.findByIsDistributtedTrueAndIsEnableTrueAndDiySiteIdAndUsernameContainingOrIsDistributtedTrueAndIsEnableTrueAndDiySiteIdAndMobileContainingOrIsDistributtedTrueAndIsEnableTrueAndDiySiteIdAndCarCodeContainingOrderByIdDesc(diysiteId, keywords, diysiteId, keywords, diysiteId, keywords, pageRequest);
    }
    public Page<TdCoupon> findByIsDistributtedTrueAndAndIsUsedTrueAndDiySiteIdAndContainingKeywordsOrderBySortIdAsc(Long diysiteId, String keywords, int page, int size)
    {
    	if (null == diysiteId) {
			return  null;
		}
        PageRequest pageRequest = new PageRequest(page, size);
        
        return repository.findByIsDistributtedTrueAndIsUsedTrueAndDiySiteIdAndUsernameContainingOrIsDistributtedTrueAndIsUsedTrueAndDiySiteIdAndMobileContainingOrIsDistributtedTrueAndIsUsedTrueAndDiySiteIdAndCarCodeContainingOrderByIdDesc(diysiteId, keywords, diysiteId, keywords, diysiteId, keywords, pageRequest);
    }
    
    //zhangji 21
    public Page<TdCoupon> findByIsDistributtedTrueAndIsEnableTrueAndAndIsUsedTrueAndDiySiteIdAndContainingKeywordsOrderBySortIdAsc(Long diysiteId, String keywords, int page, int size)
    {
    	if (null == diysiteId) {
			return  null;
		}
        PageRequest pageRequest = new PageRequest(page, size);
        
        return repository.findByIsDistributtedTrueAndIsEnableTrueAndIsUsedTrueAndDiySiteIdAndUsernameContainingOrIsDistributtedTrueAndIsEnableTrueAndIsUsedTrueAndDiySiteIdAndMobileContainingOrIsDistributtedTrueAndIsEnableTrueAndIsUsedTrueAndDiySiteIdAndCarCodeContainingOrderByIdDesc(diysiteId, keywords, diysiteId, keywords, diysiteId, keywords, pageRequest);
    }
    public Page<TdCoupon> findByIsDistributtedTrueAndAndIsUsedFalseAndDiySiteIdAndContainingKeywordsOrderBySortIdAsc(Long diysiteId, String keywords, int page, int size)
    {
    	if (null == diysiteId) {
			return  null;
		}
        PageRequest pageRequest = new PageRequest(page, size);
        
        return repository.findByIsDistributtedTrueAndIsUsedFalseAndDiySiteIdAndUsernameContainingOrIsDistributtedTrueAndIsUsedFalseAndDiySiteIdAndMobileContainingOrIsDistributtedTrueAndIsUsedFalseAndDiySiteIdAndCarCodeContainingOrderByIdDesc(diysiteId, keywords, diysiteId, keywords, diysiteId, keywords, pageRequest);
    }
    
    //zhangji 23
    public Page<TdCoupon> findByIsDistributtedTrueAndIsEnableTrueAndAndIsUsedFalseAndDiySiteIdAndContainingKeywordsOrderBySortIdAsc(Long diysiteId, String keywords, int page, int size)
    {
    	if (null == diysiteId) {
			return  null;
		}
        PageRequest pageRequest = new PageRequest(page, size);
        
        return repository.findByIsDistributtedTrueAndIsEnableTrueAndIsUsedFalseAndDiySiteIdAndUsernameContainingOrIsDistributtedTrueAndIsEnableTrueAndIsUsedFalseAndDiySiteIdAndMobileContainingOrIsDistributtedTrueAndIsEnableTrueAndIsUsedFalseAndDiySiteIdAndCarCodeContainingOrderByIdDesc(diysiteId, keywords, diysiteId, keywords, diysiteId, keywords, pageRequest);
    }
    
    
    
    public TdCoupon findByTypeIdAndMobileAndIsDistributtedTrue(Long typeId, String mobile)
    {
        if (null == typeId || null == mobile)
        {
            return null;
        }
        
        return repository.findTopByTypeIdAndMobileAndIsDistributtedTrue(typeId, mobile);
    }
    
    
    //zhangji
    public TdCoupon findByTypeIdAndUsernameAndIsDistributtedTrue(Long typeId, String username)
    {
        if (null == typeId || null == username)
        {
            return null;
        }
        
        return repository.findTopByTypeIdAndUsernameAndIsDistributtedTrue(typeId, username);
    }
    
    /**
	 * @author lc
	 * @注释：根据类型查找优惠券
	 */
    public TdCoupon findTopByTypeIdAndIsDistributtedFalse(Long typeId){
    	 if (null == typeId )
         {
             return null;
         }
    	 return repository.findTopByTypeIdAndIsDistributtedFalse(typeId);
    }
    
    public List<TdCoupon> findByIsDistributtedTrueOrderByIdDesc(){
    	return repository.findByIsDistributtedTrueOrderByIdDesc();
    }
    
    /**
	 * @author lc
	 * @注释：查询特定同盟店已使用的优惠券
	 */
    public List<TdCoupon> findByDiySiteIdAndIsUsedTrue(Long diySiteId){
    	if (null == diySiteId) {
			return null;
		}
    	return repository.findByDiySiteIdAndIsUsedTrue(diySiteId);
    }
    
    public List<TdCoupon> findByArticleIdAndIsDistributtedFalse(Long articleId){
        if (null == articleId) {
            return null;
        }
        return repository.findByArticleIdAndIsDistributtedFalse(articleId);
    }
    
    /**
   	 * @author lc
   	 * @注释：查询特定同盟店已使用的优惠券
   	 */
    public Page<TdCoupon> findByDiySiteIdAndIsUsedTrue(Long diySiteId, int page, int size){
       	if (null == diySiteId) {
   			return null;
   		}
       	PageRequest pageRequest = new PageRequest(page, size);
       	return repository.findByDiySiteIdAndIsUsedTrue(diySiteId, pageRequest);
     }
    
    /**
	 * @author lc
	 * @注释：包含搜索关键字
	 */
    public Page<TdCoupon> findByDiySiteIdAndIsUsedTrueContainsKeywords(Long diySiteId, String keywords, int page, int size){
    	if (null == diySiteId) {
   			return null;
   		}
    	PageRequest pageRequest = new PageRequest(page, size);
       	return repository.findByDiySiteIdAndIsUsedTrueAndMobileContainingOrDiySiteIdAndIsUsedTrueAndUsernameContainingOrDiySiteIdAndIsUsedTrueAndCarCodeContaining(diySiteId,keywords,diySiteId,keywords,diySiteId,keywords, pageRequest);
    }
    
    /**
	 * @author lc
     * @return 
	 * @注释：根据手机号和消费密码查询优惠券
	 */
    public TdCoupon findByMoblieAndConsumerPassword(String mobile, String password){
    	if (null == mobile || null == password) {
			return null;
		}
    	return repository.findByMobileAndConsumerPassword(mobile, password);
    }
    
    /**
     * 保存
     * 
     * @param e
     * @return
     */
    public TdCoupon save(TdCoupon e)
    {
        if (null == e.getIsDistributted())
        {
            e.setIsDistributted(false);
        }
        
        if (null == e.getGetNumber())
        {
            e.setGetNumber(0L);
        }
        
        if (null != e.getTypeId())
        {
            TdCouponType cType = tdCouponTypeService.findOne(e.getTypeId());
            
            if (null != cType)
            {
                e.setTypeTitle(cType.getTitle());
                e.setTypePicUri(cType.getPicUri());
                e.setTypeDescription(cType.getDescription());
                e.setTypeCategoryId(cType.getCategoryId());
            }
        }
        
        if (null != e.getDiySiteId())
        {
            TdDiySite diySite = tdDiySiteService.findOne(e.getDiySiteId());
            
            if (null != diySite)
            {
                e.setDiySiteTitle(diySite.getTitle());
            }
        }
        return repository.save(e);
    }
    
    public List<TdCoupon> save(List<TdCoupon> entities)
    {
        
        return (List<TdCoupon>) repository.save(entities);
    }
    
//    ====================================================================================
//    ↓↓↓↓↓↓↓↓↓↓↓↓↓ 分    类   型     筛    选  ↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓
//    ====================================================================================
    
    public Page<TdCoupon> findByTypeIdAndIsDistributtedTrueAndIsUsedTrueOrderByIdDesc(Long typeId,int page, int size){
    	if(null == typeId){
    		return null;
    	}
    	PageRequest pageRequest = new PageRequest(page, size);
    	return repository.findByTypeIdAndIsDistributtedTrueAndIsUsedTrueOrderByIdDesc(typeId,pageRequest);
    }
    //zhangji 4
    public Page<TdCoupon> findByTypeIdAndIsDistributtedTrueAndIsUsedTrueAndIsEnableTrueOrderByIdDesc(Long typeId,int page, int size){
    	if(null == typeId){
    		return null;
    	}
    	PageRequest pageRequest = new PageRequest(page, size);
    	return repository.findByTypeIdAndIsDistributtedTrueAndIsUsedTrueAndIsEnableTrueOrderByIdDesc(typeId,pageRequest);
    }
    public Page<TdCoupon> findByTypeIdAndIsDistributtedTrueAndIsUsedFalseOrderByIdDesc(Long typeId, int page, int size){
    	if(null == typeId){
    		return null;
    	}
    	PageRequest pageRequest = new PageRequest(page, size);
    	return repository.findByTypeIdAndIsDistributtedTrueAndIsUsedFalseOrderByIdDesc(typeId,pageRequest);
    }
    
    //zhangji 6
    public Page<TdCoupon> findByTypeIdAndIsDistributtedTrueAndIsUsedFalseAndIsEnableTrueOrderByIdDesc(Long typeId, int page, int size){
    	if(null == typeId){
    		return null;
    	}
    	PageRequest pageRequest = new PageRequest(page, size);
    	return repository.findByTypeIdAndIsDistributtedTrueAndIsUsedFalseAndIsEnableTrueOrderByIdDesc(typeId,pageRequest);
    }
    public Page<TdCoupon> findBytypeIdAndIsDistributtedTrueAndDiySiteIdOrderByIdDesc(Long typeId,Long diysiteId, int page, int size)
    {
    	if (null == diysiteId) {
			return  null;
		}
    	if(null == typeId){
    		return null;
    	}
        PageRequest pageRequest = new PageRequest(page, size);
        
        return repository.findByTypeIdAndIsDistributtedTrueAndDiySiteIdOrderByIdDesc(typeId, diysiteId, pageRequest);
    }
    
    //zhangji 7
    public Page<TdCoupon> findBytypeIdAndIsDistributtedTrueAndDiySiteIdAndIsEnableTrueOrderByIdDesc(Long typeId,Long diysiteId, int page, int size)
    {
    	if (null == diysiteId) {
			return  null;
		}
    	if(null == typeId){
    		return null;
    	}
        PageRequest pageRequest = new PageRequest(page, size);
        
        return repository.findByTypeIdAndIsDistributtedTrueAndDiySiteIdAndIsEnableTrueOrderByIdDesc(typeId, diysiteId, pageRequest);
    }
    public Page<TdCoupon> findBytypeIdAndIsDistributtedTrueAndDiySiteIdAndIsUsedTrueOrderByIdDesc(Long typeId,Long diysiteId, int page, int size)
    {
    	if (null == diysiteId) {
			return  null;
		}
    	if(null == typeId){
    		return null;
    	}
        PageRequest pageRequest = new PageRequest(page, size);
        
        return repository.findByTypeIdAndIsDistributtedTrueAndDiySiteIdAndIsUsedTrueOrderByIdDesc(typeId,diysiteId, pageRequest);
    }
    
    //zhangji9
    public Page<TdCoupon> findBytypeIdAndIsDistributtedTrueAndDiySiteIdAndIsUsedTrueAndIsEnableTrueOrderByIdDesc(Long typeId,Long diysiteId, int page, int size)
    {
    	if (null == diysiteId) {
			return  null;
		}
    	if(null == typeId){
    		return null;
    	}
        PageRequest pageRequest = new PageRequest(page, size);
        
        return repository.findByTypeIdAndIsDistributtedTrueAndDiySiteIdAndIsUsedTrueAndIsEnableTrueOrderByIdDesc(typeId,diysiteId, pageRequest);
    }
    public Page<TdCoupon> findBytypeIdAndIsDistributtedTrueAndDiySiteIdAndIsUsedFalseOrderByIdDesc(Long typeId,Long diysiteId, int page, int size)
    {
    	if (null == diysiteId) {
			return  null;
		}
        PageRequest pageRequest = new PageRequest(page, size);
        
        return repository.findByTypeIdAndIsDistributtedTrueAndDiySiteIdAndIsUsedFalseOrderByIdDesc(typeId,diysiteId, pageRequest);
    }
    
    //zhangji 11
    public Page<TdCoupon> findBytypeIdAndIsDistributtedTrueAndDiySiteIdAndIsUsedFalseAndIsEnableTrueOrderByIdDesc(Long typeId,Long diysiteId, int page, int size)
    {
    	if (null == diysiteId) {
			return  null;
		}
        PageRequest pageRequest = new PageRequest(page, size);
        
        return repository.findByTypeIdAndIsDistributtedTrueAndDiySiteIdAndIsUsedFalseAndIsEnableTrueOrderByIdDesc(typeId,diysiteId, pageRequest);
    }
    public Page<TdCoupon> findBytypeIdAndIsDistributtedTrueAndContainingKeywords(Long typeId,String keywords, int page, int size){
    	if(null == typeId){
    		return null;
    	}
    	PageRequest pageRequest = new PageRequest(page, size);
    	
    	return repository.findByTypeIdAndIsDistributtedTrueAndUsernameContainingOrTypeIdAndIsDistributtedTrueAndMobileContainingOrTypeIdAndIsDistributtedTrueAndCarCodeContainingOrderByIdDesc(typeId,keywords,typeId,keywords,typeId,keywords,pageRequest);
    }
    
    //zhangji 14
    public Page<TdCoupon> findBytypeIdAndIsDistributtedTrueAndIsEnableTrueAndContainingKeywords(Long typeId,String keywords, int page, int size){
    	if(null == typeId){
    		return null;
    	}
    	PageRequest pageRequest = new PageRequest(page, size);
    	
    	return repository.findByTypeIdAndIsDistributtedTrueAndIsEnableTrueAndUsernameContainingOrTypeIdAndIsDistributtedTrueAndIsEnableTrueAndMobileContainingOrTypeIdAndIsDistributtedTrueAndIsEnableTrueAndCarCodeContainingOrderByIdDesc(typeId,keywords,typeId,keywords,typeId,keywords,pageRequest);
    }
    
    public Page<TdCoupon> findBytypeIdAndIsDistributtedTrueAndIsUsedTrueAndContainingKeywords(Long typeId,String keywords, int page, int size){
    	if(null == typeId){
    		return null;
    	}
    	PageRequest pageRequest = new PageRequest(page, size);
    	
    	return repository.findByTypeIdAndIsDistributtedTrueAndIsUsedTrueAndUsernameContainingOrTypeIdAndIsDistributtedTrueAndIsUsedTrueAndMobileContainingOrTypeIdAndIsDistributtedTrueAndIsUsedTrueAndCarCodeContainingOrderByIdDesc(typeId,keywords,typeId,keywords,typeId,keywords,pageRequest);
    }
    
    //zhangji 16
    public Page<TdCoupon> findBytypeIdAndIsDistributtedTrueAndIsEnableTrueAndIsUsedTrueAndContainingKeywords(Long typeId,String keywords, int page, int size){
    	if(null == typeId){
    		return null;
    	}
    	PageRequest pageRequest = new PageRequest(page, size);
    	
    	return repository.findByTypeIdAndIsDistributtedTrueAndIsEnableTrueAndIsUsedTrueAndUsernameContainingOrTypeIdAndIsDistributtedTrueAndIsEnableTrueAndIsUsedTrueAndMobileContainingOrTypeIdAndIsDistributtedTrueAndIsEnableTrueAndIsUsedTrueAndCarCodeContainingOrderByIdDesc(typeId,keywords,typeId,keywords,typeId,keywords,pageRequest);
    }
    public Page<TdCoupon> findBytypeIdAndIsDistributtedTrueAndIsUsedFalseAndContainingKeywords(Long typeId,String keywords, int page, int size){
    	PageRequest pageRequest = new PageRequest(page, size);
    	
    	return repository.findByTypeIdAndIsDistributtedTrueAndIsUsedFalseAndUsernameContainingOrTypeIdAndIsDistributtedTrueAndIsUsedFalseAndMobileContainingOrTypeIdAndIsDistributtedTrueAndIsUsedFalseAndCarCodeContainingOrderByIdDesc(typeId,keywords,typeId,keywords,typeId,keywords,pageRequest);
    }
    
    //zhangji 18
    public Page<TdCoupon> findBytypeIdAndIsDistributtedTrueAndIsEnableTrueAndIsUsedFalseAndContainingKeywords(Long typeId,String keywords, int page, int size){
    	PageRequest pageRequest = new PageRequest(page, size);
    	
    	return repository.findByTypeIdAndIsDistributtedTrueAndIsEnableTrueAndIsUsedFalseAndUsernameContainingOrTypeIdAndIsDistributtedTrueAndIsEnableTrueAndIsUsedFalseAndMobileContainingOrTypeIdAndIsDistributtedTrueAndIsEnableTrueAndIsUsedFalseAndCarCodeContainingOrderByIdDesc(typeId,keywords,typeId,keywords,typeId,keywords,pageRequest);
    }
    public Page<TdCoupon> findBytypeIdAndIsDistributtedTrueAndDiySiteIdAndContainingKeywordsOrderBySortIdAsc(Long typeId,Long diysiteId, String keywords, int page, int size)
    {
    	if (null == diysiteId) {
			return  null;
		}
    	if(null == typeId){
    		return null;
    	}
        PageRequest pageRequest = new PageRequest(page, size);
        
        return repository.findByTypeIdAndIsDistributtedTrueAndDiySiteIdAndUsernameContainingOrTypeIdAndIsDistributtedTrueAndDiySiteIdAndMobileContainingOrTypeIdAndIsDistributtedTrueAndDiySiteIdAndCarCodeContainingOrderByIdDesc(typeId ,diysiteId, keywords, typeId,diysiteId, keywords, typeId,diysiteId, keywords, pageRequest);
    }
    
    //zhangji 20
    public Page<TdCoupon> findBytypeIdAndIsDistributtedTrueAndIsEnableTrueAndDiySiteIdAndContainingKeywordsOrderBySortIdAsc(Long typeId,Long diysiteId, String keywords, int page, int size)
    {
    	if (null == diysiteId) {
			return  null;
		}
    	if(null == typeId){
    		return null;
    	}
        PageRequest pageRequest = new PageRequest(page, size);
        
        return repository.findByTypeIdAndIsDistributtedTrueAndIsEnableTrueAndDiySiteIdAndUsernameContainingOrTypeIdAndIsDistributtedTrueAndIsEnableTrueAndDiySiteIdAndMobileContainingOrTypeIdAndIsDistributtedTrueAndIsEnableTrueAndDiySiteIdAndCarCodeContainingOrderByIdDesc(typeId ,diysiteId, keywords, typeId,diysiteId, keywords, typeId,diysiteId, keywords, pageRequest);
    }
    public Page<TdCoupon> findBytypeIdAndIsDistributtedTrueAndAndIsUsedTrueAndDiySiteIdAndContainingKeywordsOrderBySortIdAsc(Long typeId,Long diysiteId, String keywords, int page, int size)
    {
    	if(null == typeId){
    		return null;
    	}
    	if (null == diysiteId) {
			return  null;
		}
        PageRequest pageRequest = new PageRequest(page, size);
        
        return repository.findByTypeIdAndIsDistributtedTrueAndIsUsedTrueAndDiySiteIdAndUsernameContainingOrTypeIdAndIsDistributtedTrueAndIsUsedTrueAndDiySiteIdAndMobileContainingOrTypeIdAndIsDistributtedTrueAndIsUsedTrueAndDiySiteIdAndCarCodeContainingOrderByIdDesc(typeId,diysiteId, keywords,typeId, diysiteId, keywords, typeId,diysiteId, keywords, pageRequest);
    }
    
    //zhangji 22
    public Page<TdCoupon> findBytypeIdAndIsDistributtedTrueAndIsEnableTrueAndAndIsUsedTrueAndDiySiteIdAndContainingKeywordsOrderBySortIdAsc(Long typeId,Long diysiteId, String keywords, int page, int size)
    {
    	if(null == typeId){
    		return null;
    	}
    	if (null == diysiteId) {
			return  null;
		}
        PageRequest pageRequest = new PageRequest(page, size);
        
        return repository.findByTypeIdAndIsDistributtedTrueAndIsEnableTrueAndIsUsedTrueAndDiySiteIdAndUsernameContainingOrTypeIdAndIsDistributtedTrueAndIsEnableTrueAndIsUsedTrueAndDiySiteIdAndMobileContainingOrTypeIdAndIsDistributtedTrueAndIsEnableTrueAndIsUsedTrueAndDiySiteIdAndCarCodeContainingOrderByIdDesc(typeId,diysiteId, keywords,typeId, diysiteId, keywords, typeId,diysiteId, keywords, pageRequest);
    }
    public Page<TdCoupon> findBytypeIdAndIsDistributtedTrueAndAndIsUsedFalseAndDiySiteIdAndContainingKeywordsOrderBySortIdAsc(Long typeId,Long diysiteId, String keywords, int page, int size)
    {
    	if (null == diysiteId) {
			return  null;
		}
    	if(null == typeId){
    		return null;
    	}
        PageRequest pageRequest = new PageRequest(page, size);
        
        return repository.findByTypeIdAndIsDistributtedTrueAndIsUsedFalseAndDiySiteIdAndUsernameContainingOrTypeIdAndIsDistributtedTrueAndIsUsedFalseAndDiySiteIdAndMobileContainingOrTypeIdAndIsDistributtedTrueAndIsUsedFalseAndDiySiteIdAndCarCodeContainingOrderByIdDesc(typeId,diysiteId, keywords, typeId,diysiteId, keywords,typeId, diysiteId, keywords, pageRequest);
    }
    //zhangji 24 
    public Page<TdCoupon> findBytypeIdAndIsDistributtedTrueAndIsEnableTrueAndAndIsUsedFalseAndDiySiteIdAndContainingKeywordsOrderBySortIdAsc(Long typeId,Long diysiteId, String keywords, int page, int size)
    {
    	if (null == diysiteId) {
			return  null;
		}
    	if(null == typeId){
    		return null;
    	}
        PageRequest pageRequest = new PageRequest(page, size);
        
        return repository.findByTypeIdAndIsDistributtedTrueAndIsEnableTrueAndIsUsedFalseAndDiySiteIdAndUsernameContainingOrTypeIdAndIsDistributtedTrueAndIsEnableTrueAndIsUsedFalseAndDiySiteIdAndMobileContainingOrTypeIdAndIsDistributtedTrueAndIsEnableTrueAndIsUsedFalseAndDiySiteIdAndCarCodeContainingOrderByIdDesc(typeId,diysiteId, keywords, typeId,diysiteId, keywords,typeId, diysiteId, keywords, pageRequest);
    }
    
    
    
    
    
    
}
