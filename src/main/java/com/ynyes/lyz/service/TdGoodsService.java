package com.ynyes.lyz.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;

import com.ynyes.lyz.entity.TdGoods;
import com.ynyes.lyz.entity.TdGoodsCombination;
import com.ynyes.lyz.entity.TdGoodsGift;
import com.ynyes.lyz.entity.TdPriceChangeLog;
import com.ynyes.lyz.entity.TdProductCategory;
import com.ynyes.lyz.repository.TdGoodsRepo;
import com.ynyes.lyz.util.SiteMagConstant;

/**
 * TdGoods 服务类
 * 
 * @author lc
 * 
 */

@Service
@Transactional
public class TdGoodsService {

	@Autowired
	TdGoodsRepo repository;

	@Autowired
	TdProductCategoryService tdProductCategoryService;

	// @Autowired
	// TdBrandService tdBrandService;

	@Autowired
	TdArticleService tdArticleService;

	// @Autowired
	// TdParameterService tdParameterService;

	// @Autowired
	// TdGoodsParameterService tdGoodsParameterService;
	//
	// @Autowired
	// TdWarehouseService tdWarehouseService;

	@Autowired
	TdGoodsGiftService tdGoodsGiftService;

	@Autowired
	TdGoodsCombinationService tdGoodsCombinationService;

	@Autowired
	TdPriceChangeLogService tdPriceChangeLogService;

	// @Autowired
	// private TdUserRecentVisitService tdUserRecentVisitService;
	//
	// @Autowired
	// private TdUserService tdUserService;
	//
	// @Autowired
	// private TdUserConsultService tdUserConsultService;
	//
	// @Autowired
	// private TdUserCommentService tdUserCommentService;
	//
	// @Autowired
	// private TdUserCollectService tdUserCollectService;

	@Autowired
	private TdProductService tdProductService;

	/******** 功能部分 ***********/

	/**
	 * @author lc @注释：商品推荐、
	 */
	// 无类别限制
	public Boolean getrecommend(ModelMap map) {

		// 分类推荐
		map.addAttribute("type_recommend_page",
				this.findByIsRecommendTypeTrueAndIsOnSaleTrueOrderByIdDesc(0, SiteMagConstant.pageSize));

		// 销量排行
		map.addAttribute("soldNumber_recommend_page",
				this.findByIsOnSaleTrueOrderBySoldNumberDesc(0, SiteMagConstant.pageSize));

		// 新品推荐
		map.addAttribute("new_recommend_page",
				this.findByIsNewTrueAndIsOnSaleTrueOrderByIdDesc(0, SiteMagConstant.pageSize));

		// 首页推荐商品
		map.addAttribute("index_recommend_page",
				this.findByIsRecommendIndexTrueAndIsOnSaleTrueOrderByIdDesc(0, SiteMagConstant.pageSize));

		// 特价推荐商品
		map.addAttribute("specialPrice_recommend_page",
				this.findByIsSpecialPriceTrueAndIsOnSaleTrueOrderByIdDesc(0, SiteMagConstant.pageSize));

		// 热卖推荐
		map.addAttribute("hot_recommend_page",
				this.findByIsHotTrueAndIsOnSaleTrueOrderByIdDesc(0, SiteMagConstant.pageSize));

		return true;
	}

	// 有类别限制
	public Boolean getrecommendByCategory(Long catId, ModelMap map) {

		// 特定类型的分类推荐
		map.addAttribute("type_recommend_page", this.findByCategoryIdAndIsRecommendTypeTrueAndIsOnSaleTrueOrderByIdDesc(
				catId, 0, SiteMagConstant.pageSize));

		// 特定类型的销量排行
		map.addAttribute("soldNumber_recommend_page",
				this.findByCategoryIdAndIsOnSaleTrueOrderBySoldNumberDesc(catId, 0, SiteMagConstant.pageSize));

		// 特定类型的新品推荐
		map.addAttribute("new_recommend_page",
				this.findByCategoryIdAndisNewTrueAndIsOnSaleTrueOrderByIdDesc(catId, 0, SiteMagConstant.pageSize));

		// 特定类型的首页推荐商品
		map.addAttribute("index_recommend_page",
				this.findByCategoryIdAndIsRecommendIndexTrueAndIsOnSaleTrueOrderByIdDesc(catId, 0,
						SiteMagConstant.pageSize));

		// 制定类别的特价推荐商品
		map.addAttribute("specialPrice_recommend_page", this
				.findByCategoryIdAndisSpecialPriceTrueAndIsOnSaleTrueOrderByIdDesc(catId, 0, SiteMagConstant.pageSize));

		// 特定类型的热卖推荐
		map.addAttribute("hot_recommend_page",
				this.findByCategoryIdAndisHotTrueAndIsOnSaleTrueOrderByIdDesc(catId, 0, SiteMagConstant.pageSize));

		return true;
	}

	/******** 功能部分 ***********/

	/******** 商品筛选查找部分 ***********/
	/**
	 * @author lc
	 * @注释：按一定条件查找商品
	 */
	// 查找所有商品
	public Page<TdGoods> findAll(int page, int size) {
		PageRequest pageRequest = new PageRequest(page, size);

		return repository.findAll(pageRequest);
	}

	// 查找所有商品按序号排序
	public Page<TdGoods> findAllOrderBySortIdAsc(int page, int size) {
		PageRequest pageRequest = new PageRequest(page, size, new Sort(Direction.ASC, "sortId"));

		return repository.findAll(pageRequest);
	}

	// 查找所有上架商品按序号排序
	public Page<TdGoods> findByIsOnSaleTrueOrderBySortIdAsc(int page, int size) {
		PageRequest pageRequest = new PageRequest(page, size, new Sort(Direction.ASC, "sortId"));

		return repository.findByIsOnSaleTrue(pageRequest);
	}

	// 查找所有上架商品
	public Page<TdGoods> findAllAndIsOnSaleTrue(int page, int size) {
		PageRequest pageRequest = new PageRequest(page, size);

		return repository.findByIsOnSaleTrue(pageRequest);
	}

	// 查找前十个id的商品并按销量排序
	public List<TdGoods> findTop10ByIsOnSaleTrueOrderBySoldNumberDesc() {
		return repository.findTop10ByIsOnSaleTrueOrderBySoldNumberDesc();
	}

	//
	public List<TdGoods> findByIdAndIsOnSaleTrue(Iterable<Long> ids) {
		return repository.findByIdAndIsOnSaleTrue(ids);
	}
	
	// 查找返现金额不为零的上架商品
	public Page<TdGoods> findByReturnPriceNotZeroAndIsOnSaleTrue(int page, int size) {
		PageRequest pageRequest = new PageRequest(page, size);

		return repository.findByReturnPriceNotAndIsOnSaleTrue(0.0, pageRequest);
	}

	// 搜索返现金额不为零的上架商品
	public Page<TdGoods> findByReturnPriceNotZeroAndSearchAndIsOnSaleTrue(int page, int size, String keywords) {
		if (null == keywords) {
			return null;
		}

		PageRequest pageRequest = new PageRequest(page, size);

		return repository.findByReturnPriceNotAndTitleContainingAndIsOnSaleTrue(0.0, keywords, pageRequest);
	}

	// 按上架时间排序
	public Page<TdGoods> findByIsOnSaleTrueOrderByOnSaleTimeDesc(int page, int size) {
		PageRequest pageRequest = new PageRequest(page, size);
		return repository.findByIsOnSaleTrueOrderByOnSaleTimeDesc(pageRequest);
	}

	// 按销量排序
	public Page<TdGoods> findByIsOnSaleTrueOrderBySoldNumberDesc(int page, int size) {
		PageRequest pageRequest = new PageRequest(page, size, new Sort(Direction.DESC, "soldNumber"));

		return repository.findByIsOnSaleTrue(pageRequest);
	}

	// 根据产品id查找上架商品
	public List<TdGoods> findByProductIdAndIsOnSaleTrue(Long productId) {
		if (null == productId) {
			return null;
		}

		return repository.findByProductIdAndIsOnSaleTrue(productId);
	}

	/**
	 * @author lc
	 * @注释：搜索商品
	 */
	// 痛殴关键字搜索所有商品并按序号排序
	public Page<TdGoods> searchAndOrderBySortIdAsc(String keywords, int page, int size) {
		PageRequest pageRequest = new PageRequest(page, size);

		return repository.findByTitleContainingOrSubTitleContainingOrDetailContainingOrderBySortIdAsc(keywords,
				keywords, keywords, pageRequest);
	}

	// 痛殴关键字搜索上架商品并按序号排序
	public Page<TdGoods> searchAndIsOnSaleTrueOrderBySortIdAsc(String keywords, int page, int size) {
		PageRequest pageRequest = new PageRequest(page, size);

		return repository.findByTitleContainingOrSubTitleContainingOrDetailContainingAndIsOnSaleTrueOrderBySortIdAsc(
				keywords, keywords, keywords, pageRequest);
	}

	// 搜索特定类别的所有商品并按序号排序
	public Page<TdGoods> searchAndFindByCategoryIdOrderBySortIdAsc(String keywords, Long categoryId, int page,
			int size) {
		PageRequest pageRequest = new PageRequest(page, size);

		String catIdStr = "[" + categoryId + "]";

		return repository
				.findByCategoryIdTreeContainingAndTitleContainingOrCategoryIdTreeContainingAndSubTitleContainingOrCategoryIdTreeContainingAndDetailContainingOrderBySortIdAsc(
						catIdStr, keywords, catIdStr, keywords, catIdStr, keywords, pageRequest);
	}

	// 搜索特定类别的上架商品并按序号排序
	public Page<TdGoods> searchAndFindByCategoryIdAndIsOnSaleTrueOrderBySortIdAsc(String keywords, Long categoryId,
			int page, int size) {
		PageRequest pageRequest = new PageRequest(page, size);

		String catIdStr = "[" + categoryId + "]";

		return repository
				.findByCategoryIdTreeContainingAndTitleContainingAndIsOnSaleTrueOrCategoryIdTreeContainingAndSubTitleContainingAndIsOnSaleTrueOrCategoryIdTreeContainingAndDetailContainingAndIsOnSaleTrueOrderBySortIdAsc(
						catIdStr, keywords, catIdStr, keywords, catIdStr, keywords, pageRequest);
	}

	public Page<TdGoods> searchGoods(String keywords, int page, int size) {
		if (null == keywords) {
			return null;
		}

		PageRequest pageRequest = new PageRequest(page, size, new Sort(Direction.DESC, "id"));

		return repository
				.findByTitleContainingAndIsOnSaleTrueOrSubTitleContainingAndIsOnSaleTrueOrParamValueCollectContainingAndIsOnSaleTrueOrDetailContainingAndIsOnSaleTrue(
						keywords, keywords, keywords, keywords, pageRequest);
	}

	/**
	 * @author lc @注释：特定类型的商品推荐(包含热卖、新品、首页推荐、分类推荐、特价)
	 */
	// 特定类型的分类推荐
	public Page<TdGoods> findByCategoryIdAndIsRecommendTypeTrueAndIsOnSaleTrueOrderByIdDesc(Long catId, int page,
			int size) {
		if (null == catId) {
			return null;
		}

		PageRequest pageRequest = new PageRequest(page, size);

		String catStr = "[" + catId + "]";

		return repository.findByCategoryIdTreeContainingAndIsRecommendTypeTrueAndIsOnSaleTrueOrderByIdDesc(catStr,
				pageRequest);
	}

	// 特定类型的销量排行
	public Page<TdGoods> findByCategoryIdAndIsOnSaleTrueOrderBySoldNumberDesc(Long catId, int page, int size) {
		if (null == catId) {
			return null;
		}

		PageRequest pageRequest = new PageRequest(page, size);

		String catStr = "[" + catId + "]";

		return repository.findByCategoryIdTreeContainingAndIsOnSaleTrueOrderBySoldNumberDesc(catStr, pageRequest);
	}

	// 特定类型的新品推荐
	public Page<TdGoods> findByCategoryIdAndIsOnSaleTrueOrderByOnSaleTimeDesc(Long catId, int page, int size) {
		if (null == catId) {
			return null;
		}

		PageRequest pageRequest = new PageRequest(page, size);

		String catStr = "[" + catId + "]";

		return repository.findByCategoryIdTreeContainingAndIsOnSaleTrueOrderByOnSaleTimeDesc(catStr, pageRequest);
	}

	// 特定类型的首页推荐商品
	public Page<TdGoods> findByCategoryIdAndIsRecommendIndexTrueAndIsOnSaleTrueOrderByIdDesc(Long catId, int page,
			int size) {
		if (null == catId) {
			return null;
		}

		PageRequest pageRequest = new PageRequest(page, size);

		String catStr = "[" + catId + "]";

		return repository.findByCategoryIdTreeContainingAndIsRecommendIndexTrueAndIsOnSaleTrueOrderByIdDesc(catStr,
				pageRequest);
	}

	// 特定类型的新品推荐商品
	public Page<TdGoods> findByCategoryIdAndisNewTrueAndIsOnSaleTrueOrderByIdDesc(Long catId, int page, int size) {
		if (null == catId) {
			return null;
		}

		PageRequest pageRequest = new PageRequest(page, size);

		String catStr = "[" + catId + "]";

		return repository.findByCategoryIdTreeContainingAndIsNewTrueAndIsOnSaleTrueOrderByIdDesc(catStr, pageRequest);
	}

	// 特定类型的特价推荐商品
	public Page<TdGoods> findByCategoryIdAndisSpecialPriceTrueAndIsOnSaleTrueOrderByIdDesc(Long catId, int page,
			int size) {
		if (null == catId) {
			return null;
		}

		PageRequest pageRequest = new PageRequest(page, size);

		String catStr = "[" + catId + "]";

		return repository.findByCategoryIdTreeContainingAndIsSpecialPriceTrueAndIsOnSaleTrueOrderByIdDesc(catStr,
				pageRequest);
	}

	// 特定类型的热卖推荐商品
	public Page<TdGoods> findByCategoryIdAndisHotTrueAndIsOnSaleTrueOrderByIdDesc(Long catId, int page, int size) {
		if (null == catId) {
			return null;
		}

		PageRequest pageRequest = new PageRequest(page, size);

		String catStr = "[" + catId + "]";

		return repository.findByCategoryIdTreeContainingAndIsHotTrueAndIsOnSaleTrueOrderByIdDesc(catStr, pageRequest);
	}

	/**
	 * @author lc @注释：商品推荐(包含热卖、新品、首页推荐、分类推荐、特价)
	 */
	// 查找分类推荐上架商品按id排序
	public Page<TdGoods> findByIsRecommendTypeTrueAndIsOnSaleTrueOrderByIdDesc(int page, int size) {
		PageRequest pageRequest = new PageRequest(page, size);

		return repository.findByIsRecommendTypeTrueAndIsOnSaleTrueOrderByIdDesc(pageRequest);
	}

	// 查找新品推荐上架商品按id排序
	public Page<TdGoods> findByIsNewTrueAndIsOnSaleTrueOrderByIdDesc(int page, int size) {
		PageRequest pageRequest = new PageRequest(page, size);

		return repository.findByIsNewTrueAndIsOnSaleTrueOrderByIdDesc(pageRequest);
	}

	// 查找首页推荐上架商品按id排序
	public Page<TdGoods> findByIsRecommendIndexTrueAndIsOnSaleTrueOrderByIdDesc(int page, int size) {
		PageRequest pageRequest = new PageRequest(page, size);

		return repository.findByIsRecommendIndexTrueAndIsOnSaleTrueOrderByIdDesc(pageRequest);
	}

	// 查找特价推荐上架商品按id排序
	public Page<TdGoods> findByIsSpecialPriceTrueAndIsOnSaleTrueOrderByIdDesc(int page, int size) {
		PageRequest pageRequest = new PageRequest(page, size);

		return repository.findByIsSpecialPriceTrueAndIsOnSaleTrueOrderByIdDesc(pageRequest);
	}

	// 查找特价推荐上架商品按id排序
	public Page<TdGoods> findByIsHotTrueAndIsOnSaleTrueOrderByIdDesc(int page, int size) {
		PageRequest pageRequest = new PageRequest(page, size);

		return repository.findByIsHotTrueAndIsOnSaleTrueOrderByIdDesc(pageRequest);
	}

	// 查找制定类别的所有商品
	public Page<TdGoods> findByCategoryIdAndIsOnSaleTrue(Long catId, int page, int size) {
		if (null == catId) {
			return null;
		}

		PageRequest pageRequest = new PageRequest(page, size);

		String catStr = "[" + catId + "]";

		return repository.findByCategoryIdTreeContainingAndIsOnSaleTrue(catStr, pageRequest);
	}

	// 查找制定类别的所有商品并按序号排序
	public Page<TdGoods> findByCategoryIdTreeContainingOrderBySortIdAsc(Long catId, int page, int size) {
		PageRequest pageRequest = new PageRequest(page, size);

		String catIdStr = "[" + catId + "]";

		return repository.findByCategoryIdTreeContainingOrderBySortIdAsc(catIdStr, pageRequest);
	}

	// 查找制定类别的上架商品并按序号排序
	public Page<TdGoods> findByCategoryIdTreeContainingAndIsOnSaleTrueOrderBySortIdAsc(Long catId, int page, int size) {
		PageRequest pageRequest = new PageRequest(page, size);

		String catIdStr = "[" + catId + "]";

		return repository.findByCategoryIdTreeContainingAndIsOnSaleTrueOrderBySortIdAsc(catIdStr, pageRequest);
	}

	/**
	 * 搜索商品
	 * 
	 * @param keywords
	 *            关键字
	 * @param page
	 *            页号
	 * @param size
	 *            页大小
	 * @param sortName
	 *            排序字段名
	 * @param sd
	 *            排序方向
	 * @return
	 */
	public Page<TdGoods> searchGoods(String keywords, int page, int size, String sortName, Direction dir) {
		if (null == keywords || null == sortName) {
			return null;
		}

		PageRequest pageRequest = new PageRequest(page, size, new Sort(dir, sortName));

		return repository
				.findByTitleContainingIgnoreCaseAndIsOnSaleTrueOrSubTitleContainingAndIsOnSaleTrueOrParamValueCollectContainingAndIsOnSaleTrueOrDetailContainingAndIsOnSaleTrue(
						keywords, keywords, keywords, keywords, pageRequest);
	}

	/**
	 * @author lc
	 * @注释：列表页商品筛选
	 */
	public Page<TdGoods> findByCategoryIdAndLeftNumberGreaterThanZeroAndSalePriceBetweenAndParamsLikeAndIsOnSaleTrue(
			long catId, double priceLow, double priceHigh, List<String> paramValueList, Pageable pageRequest) {

		String paramStr = "%";

		for (int i = 0; i < paramValueList.size(); i++) {
			String value = paramValueList.get(i);
			if (!"".equals(value)) {
				paramStr += value;
				paramStr += "%";
			}
		}

		return repository
				.findByCategoryIdTreeContainingAndLeftNumberGreaterThanAndSalePriceBetweenAndParamValueCollectLikeAndIsOnSaleTrue(
						"[" + catId + "]", 0L, priceLow, priceHigh, paramStr, pageRequest);
	}

	public Page<TdGoods> findByCategoryIdAndBrandIdAndLeftNumberGreaterThanZeroAndSalePriceBetweenAndParamsLikeAndIsOnSaleTrue(
			long catId, long brandId, double priceLow, double priceHigh, List<String> paramValueList,
			Pageable pageRequest) {
		String paramStr = "%";

		for (int i = 0; i < paramValueList.size(); i++) {
			String value = paramValueList.get(i);
			if (!"".equals(value)) {
				paramStr += value;
				paramStr += "%";
			}
		}

		return repository
				.findByCategoryIdTreeContainingAndBrandIdAndLeftNumberGreaterThanAndSalePriceBetweenAndParamValueCollectLikeAndIsOnSaleTrue(
						"[" + catId + "]", brandId, 0L, priceLow, priceHigh, paramStr, pageRequest);
	}

	public Page<TdGoods> findByCategoryIdAndLeftNumberGreaterThanZeroAndParamsLikeAndIsOnSaleTrue(long catId,
			List<String> paramValueList, Pageable pageRequest) {
		String paramStr = "%";

		for (int i = 0; i < paramValueList.size(); i++) {
			String value = paramValueList.get(i);
			if (!"".equals(value)) {
				paramStr += value;
				paramStr += "%";
			}
		}

		return repository.findByCategoryIdTreeContainingAndLeftNumberGreaterThanAndParamValueCollectLikeAndIsOnSaleTrue(
				"[" + catId + "]", 0L, paramStr, pageRequest);
	}

	public Page<TdGoods> findByCategoryIdAndBrandIdAndLeftNumberGreaterThanZeroAndParamsLikeAndIsOnSaleTrue(long catId,
			long brandId, List<String> paramValueList, Pageable pageRequest) {
		String paramStr = "%";

		for (int i = 0; i < paramValueList.size(); i++) {
			String value = paramValueList.get(i);
			if (!"".equals(value)) {
				paramStr += value;
				paramStr += "%";
			}
		}

		return repository
				.findByCategoryIdTreeContainingAndBrandIdAndLeftNumberGreaterThanAndParamValueCollectLikeAndIsOnSaleTrue(
						"[" + catId + "]", brandId, 0L, paramStr, pageRequest);
	}

	public Page<TdGoods> findByCategoryIdAndSalePriceBetweenAndParamsLikeAndIsOnSaleTrue(long catId, double priceLow,
			double priceHigh, List<String> paramValueList, Pageable pageRequest) {
		String paramStr = "%";

		for (int i = 0; i < paramValueList.size(); i++) {
			String value = paramValueList.get(i);
			if (!"".equals(value)) {
				paramStr += value;
				paramStr += "%";
			}
		}

		return repository.findByCategoryIdTreeContainingAndSalePriceBetweenAndParamValueCollectLikeAndIsOnSaleTrue(
				"[" + catId + "]", priceLow, priceHigh, paramStr, pageRequest);
	}

	public Page<TdGoods> findByCategoryIdAndBrandIdAndSalePriceBetweenAndParamsLikeAndIsOnSaleTrue(long catId,
			long brandId, double priceLow, double priceHigh, List<String> paramValueList, Pageable pageRequest) {
		String paramStr = "%";

		for (int i = 0; i < paramValueList.size(); i++) {
			String value = paramValueList.get(i);
			if (!"".equals(value)) {
				paramStr += value;
				paramStr += "%";
			}
		}

		return repository
				.findByCategoryIdTreeContainingAndBrandIdAndSalePriceBetweenAndParamValueCollectLikeAndIsOnSaleTrue(
						"[" + catId + "]", brandId, priceLow, priceHigh, paramStr, pageRequest);
	}

	public Page<TdGoods> findByCategoryIdAndParamsLikeAndIsOnSaleTrue(long catId, List<String> paramValueList,
			Pageable pageRequest) {
		String paramStr = "%";

		if (null != paramValueList) {
			for (int i = 0; i < paramValueList.size(); i++) {
				String value = paramValueList.get(i);
				if (!"".equals(value)) {
					paramStr += value;
					paramStr += "%";
				}
			}
		}

		return repository.findByCategoryIdTreeContainingAndParamValueCollectLikeAndIsOnSaleTrue("[" + catId + "]",
				paramStr, pageRequest);
	}

	public Page<TdGoods> findByCategoryIdAndBrandIdAndParamsLikeAndIsOnSaleTrue(long catId, long brandId,
			List<String> paramValueList, Pageable pageRequest) {
		String paramStr = "%";

		for (int i = 0; i < paramValueList.size(); i++) {
			String value = paramValueList.get(i);
			if (!"".equals(value)) {
				paramStr += value;
				paramStr += "%";
			}
		}

		return repository.findByCategoryIdTreeContainingAndBrandIdAndParamValueCollectLikeAndIsOnSaleTrue(
				"[" + catId + "]", brandId, paramStr, pageRequest);
	}
	/**** 列表页商品筛选 ******/

	/**
	 * @author lc
	 * @注释：后台商品删选
	 */
	public Page<TdGoods> findByIsOnSaleTrueAndFlashSaleTrueOrderBySortIdAsc(int page, int size) {
		PageRequest pageRequest = new PageRequest(page, size,
				new Sort(Direction.ASC, "sortId").and(new Sort(Direction.DESC, "id")));

		return repository.findByIsOnSaleTrueAndIsFlashSaleTrue(pageRequest);
	}

	public Page<TdGoods> searchAndIsOnSaleTrueAndIsFlashSaleTrueOrderBySortIdAsc(String keywords, int page, int size) {
		PageRequest pageRequest = new PageRequest(page, size,
				new Sort(Direction.ASC, "sortId").and(new Sort(Direction.DESC, "id")));

		return repository
				.findByTitleContainingIgnoreCaseAndIsOnSaleTrueAndIsFlashSaleTrueOrSubTitleContainingIgnoreCaseAndIsOnSaleTrueAndIsFlashSaleTrueOrDetailContainingIgnoreCaseAndIsOnSaleTrueAndIsFlashSaleTrue(
						keywords, keywords, keywords, pageRequest);
	}

	public Page<TdGoods> findByIsOnSaleFalseAndIsFlashSaleTrueOrderBySortIdAsc(int page, int size) {
		PageRequest pageRequest = new PageRequest(page, size,
				new Sort(Direction.ASC, "sortId").and(new Sort(Direction.DESC, "id")));

		return repository.findByIsOnSaleFalseAndIsFlashSaleTrue(pageRequest);
	}

	public Page<TdGoods> searchAndIsOnSaleFalseAndIsFlashSaleTrueOrderBySortIdAsc(String keywords, int page, int size) {
		PageRequest pageRequest = new PageRequest(page, size,
				new Sort(Direction.ASC, "sortId").and(new Sort(Direction.DESC, "id")));

		return repository
				.findByTitleContainingIgnoreCaseAndIsOnSaleFalseAndIsFlashSaleTrueOrSubTitleContainingIgnoreCaseAndIsOnSaleFalseAndIsFlashSaleTrueOrDetailContainingIgnoreCaseAndIsOnSaleFalseAndIsFlashSaleTrue(
						keywords, keywords, keywords, pageRequest);
	}

	public Page<TdGoods> findByIsOnSaleFalseOrderBySortIdAsc(int page, int size) {
		PageRequest pageRequest = new PageRequest(page, size,
				new Sort(Direction.ASC, "sortId").and(new Sort(Direction.DESC, "id")));

		return repository.findByIsOnSaleFalse(pageRequest);
	}

	public Page<TdGoods> searchAndIsOnSaleFalseOrderBySortIdAsc(String keywords, int page, int size) {
		PageRequest pageRequest = new PageRequest(page, size,
				new Sort(Direction.ASC, "sortId").and(new Sort(Direction.DESC, "id")));

		return repository
				.findByTitleContainingIgnoreCaseAndIsOnSaleFalseOrSubTitleContainingIgnoreCaseAndIsOnSaleFalseOrDetailContainingIgnoreCaseAndIsOnSaleFalse(
						keywords, keywords, keywords, pageRequest);
	}

	public Page<TdGoods> findByIsFlashSaleTrueOrderBySortIdAsc(int page, int size) {
		PageRequest pageRequest = new PageRequest(page, size,
				new Sort(Direction.ASC, "sortId").and(new Sort(Direction.DESC, "id")));

		return repository.findByIsFlashSaleTrue(pageRequest);
	}

	public Page<TdGoods> searchAndIsFlashSaleTrueOrderBySortIdAsc(String keywords, int page, int size) {
		PageRequest pageRequest = new PageRequest(page, size,
				new Sort(Direction.ASC, "sortId").and(new Sort(Direction.DESC, "id")));

		return repository
				.findByTitleContainingIgnoreCaseAndIsFlashSaleTrueOrSubTitleContainingIgnoreCaseAndIsFlashSaleTrueOrDetailContainingIgnoreCaseAndIsFlashSaleTrue(
						keywords, keywords, keywords, pageRequest);
	}

	public Page<TdGoods> findByCategoryIdTreeContainingAndIsOnSaleTrueAndIsFlashSaleTrueOrderBySortIdAsc(Long catId,
			int page, int size) {
		PageRequest pageRequest = new PageRequest(page, size,
				new Sort(Direction.ASC, "sortId").and(new Sort(Direction.DESC, "id")));

		String catIdStr = "[" + catId + "]";

		return repository.findByCategoryIdTreeContainingAndIsOnSaleTrueAndIsFlashSaleTrue(catIdStr, pageRequest);
	}

	public Page<TdGoods> searchAndFindByCategoryIdAndIsOnSaleTrueAndIsFlashSaleTrueOrderBySortIdAsc(String keywords,
			Long categoryId, int page, int size) {
		PageRequest pageRequest = new PageRequest(page, size,
				new Sort(Direction.ASC, "sortId").and(new Sort(Direction.DESC, "id")));

		String catIdStr = "[" + categoryId + "]";

		return repository
				.findByCategoryIdTreeContainingAndTitleContainingIgnoreCaseAndIsOnSaleTrueAndIsFlashSaleTrueOrCategoryIdTreeContainingAndSubTitleContainingIgnoreCaseAndIsOnSaleTrueAndIsFlashSaleTrueOrCategoryIdTreeContainingAndDetailContainingIgnoreCaseAndIsOnSaleTrueAndIsFlashSaleTrue(
						catIdStr, keywords, catIdStr, keywords, catIdStr, keywords, pageRequest);
	}

	public Page<TdGoods> findByCategoryIdTreeContainingAndIsOnSaleFalseAndIsFlashSaleTrueOrderBySortIdAsc(Long catId,
			int page, int size) {
		PageRequest pageRequest = new PageRequest(page, size,
				new Sort(Direction.ASC, "sortId").and(new Sort(Direction.DESC, "id")));

		String catIdStr = "[" + catId + "]";

		return repository.findByCategoryIdTreeContainingAndIsOnSaleFalseAndIsFlashSaleTrue(catIdStr, pageRequest);
	}

	public Page<TdGoods> searchAndFindByCategoryIdAndIsOnSaleFalseAndIsFlashSaleTrueOrderBySortIdAsc(String keywords,
			Long categoryId, int page, int size) {
		PageRequest pageRequest = new PageRequest(page, size,
				new Sort(Direction.ASC, "sortId").and(new Sort(Direction.DESC, "id")));

		String catIdStr = "[" + categoryId + "]";

		return repository
				.findByCategoryIdTreeContainingAndTitleContainingIgnoreCaseAndIsOnSaleFalseAndIsFlashSaleTrueOrCategoryIdTreeContainingAndSubTitleContainingIgnoreCaseAndIsOnSaleTrueAndIsFlashSaleTrueOrCategoryIdTreeContainingAndDetailContainingIgnoreCaseAndIsOnSaleTrueAndIsFlashSaleTrue(
						catIdStr, keywords, catIdStr, keywords, catIdStr, keywords, pageRequest);
	}

	public Page<TdGoods> findByCategoryIdTreeContainingAndIsOnSaleFalseOrderBySortIdAsc(Long catId, int page,
			int size) {
		PageRequest pageRequest = new PageRequest(page, size,
				new Sort(Direction.ASC, "sortId").and(new Sort(Direction.DESC, "id")));

		String catIdStr = "[" + catId + "]";

		return repository.findByCategoryIdTreeContainingAndIsOnSaleFalse(catIdStr, pageRequest);
	}

	public Page<TdGoods> searchAndFindByCategoryIdAndIsOnSaleFalseOrderBySortIdAsc(String keywords, Long categoryId,
			int page, int size) {
		PageRequest pageRequest = new PageRequest(page, size,
				new Sort(Direction.ASC, "sortId").and(new Sort(Direction.DESC, "id")));

		String catIdStr = "[" + categoryId + "]";

		return repository
				.findByCategoryIdTreeContainingAndTitleContainingIgnoreCaseAndIsOnSaleFalseOrCategoryIdTreeContainingAndSubTitleContainingIgnoreCaseAndIsOnSaleTrueOrCategoryIdTreeContainingAndDetailContainingIgnoreCaseAndIsOnSaleTrue(
						catIdStr, keywords, catIdStr, keywords, catIdStr, keywords, pageRequest);
	}

	public Page<TdGoods> findByCategoryIdTreeContainingAndIsFlashSaleTrueOrderBySortIdAsc(Long catId, int page,
			int size) {
		PageRequest pageRequest = new PageRequest(page, size,
				new Sort(Direction.ASC, "sortId").and(new Sort(Direction.DESC, "id")));

		String catIdStr = "[" + catId + "]";

		return repository.findByCategoryIdTreeContainingAndIsFlashSaleTrue(catIdStr, pageRequest);
	}

	public Page<TdGoods> searchAndFindByCategoryIdAndIsFlashSaleTrueOrderBySortIdAsc(String keywords, Long categoryId,
			int page, int size) {
		PageRequest pageRequest = new PageRequest(page, size,
				new Sort(Direction.ASC, "sortId").and(new Sort(Direction.DESC, "id")));

		String catIdStr = "[" + categoryId + "]";

		return repository
				.findByCategoryIdTreeContainingAndTitleContainingIgnoreCaseAndIsFlashSaleTrueOrCategoryIdTreeContainingAndSubTitleContainingIgnoreCaseAndIsFlashSaleTrueOrCategoryIdTreeContainingAndDetailContainingIgnoreCaseAndIsFlashSaleTrue(
						catIdStr, keywords, catIdStr, keywords, catIdStr, keywords, pageRequest);
	}

	public Page<TdGoods> findByIsOnSaleTrueAndGroupSaleTrueOrderBySortIdAsc(int page, int size) {
		PageRequest pageRequest = new PageRequest(page, size,
				new Sort(Direction.ASC, "sortId").and(new Sort(Direction.DESC, "id")));

		return repository.findByIsOnSaleTrueAndIsGroupSaleTrue(pageRequest);
	}

	public Page<TdGoods> searchAndIsOnSaleTrueAndIsGroupSaleTrueOrderBySortIdAsc(String keywords, int page, int size) {
		PageRequest pageRequest = new PageRequest(page, size,
				new Sort(Direction.ASC, "sortId").and(new Sort(Direction.DESC, "id")));

		return repository
				.findByTitleContainingIgnoreCaseAndIsOnSaleTrueAndIsGroupSaleTrueOrSubTitleContainingIgnoreCaseAndIsOnSaleTrueAndIsGroupSaleTrueOrDetailContainingIgnoreCaseAndIsOnSaleTrueAndIsGroupSaleTrue(
						keywords, keywords, keywords, pageRequest);
	}

	public Page<TdGoods> findByIsOnSaleFalseAndIsGroupSaleTrueOrderBySortIdAsc(int page, int size) {
		PageRequest pageRequest = new PageRequest(page, size,
				new Sort(Direction.ASC, "sortId").and(new Sort(Direction.DESC, "id")));

		return repository.findByIsOnSaleFalseAndIsGroupSaleTrue(pageRequest);
	}

	public Page<TdGoods> searchAndIsOnSaleFalseAndIsGroupSaleTrueOrderBySortIdAsc(String keywords, int page, int size) {
		PageRequest pageRequest = new PageRequest(page, size,
				new Sort(Direction.ASC, "sortId").and(new Sort(Direction.DESC, "id")));

		return repository
				.findByTitleContainingIgnoreCaseAndIsOnSaleFalseAndIsGroupSaleTrueOrSubTitleContainingIgnoreCaseAndIsOnSaleFalseAndIsGroupSaleTrueOrDetailContainingIgnoreCaseAndIsOnSaleFalseAndIsGroupSaleTrue(
						keywords, keywords, keywords, pageRequest);
	}

	public Page<TdGoods> findByIsGroupSaleTrueOrderBySortIdAsc(int page, int size) {
		PageRequest pageRequest = new PageRequest(page, size,
				new Sort(Direction.ASC, "sortId").and(new Sort(Direction.DESC, "id")));

		return repository.findByIsGroupSaleTrue(pageRequest);
	}

	public Page<TdGoods> searchAndIsGroupSaleTrueOrderBySortIdAsc(String keywords, int page, int size) {
		PageRequest pageRequest = new PageRequest(page, size,
				new Sort(Direction.ASC, "sortId").and(new Sort(Direction.DESC, "id")));

		return repository
				.findByTitleContainingIgnoreCaseAndIsGroupSaleTrueOrSubTitleContainingIgnoreCaseAndIsGroupSaleTrueOrDetailContainingIgnoreCaseAndIsGroupSaleTrue(
						keywords, keywords, keywords, pageRequest);
	}

	public Page<TdGoods> findByCategoryIdTreeContainingAndIsOnSaleTrueAndIsGroupSaleTrueOrderBySortIdAsc(Long catId,
			int page, int size) {
		PageRequest pageRequest = new PageRequest(page, size,
				new Sort(Direction.ASC, "sortId").and(new Sort(Direction.DESC, "id")));

		String catIdStr = "[" + catId + "]";

		return repository.findByCategoryIdTreeContainingAndIsOnSaleTrueAndIsGroupSaleTrue(catIdStr, pageRequest);
	}

	public Page<TdGoods> searchAndFindByCategoryIdAndIsOnSaleTrueAndIsGroupSaleTrueOrderBySortIdAsc(String keywords,
			Long categoryId, int page, int size) {
		PageRequest pageRequest = new PageRequest(page, size,
				new Sort(Direction.ASC, "sortId").and(new Sort(Direction.DESC, "id")));

		String catIdStr = "[" + categoryId + "]";

		return repository
				.findByCategoryIdTreeContainingAndTitleContainingIgnoreCaseAndIsOnSaleTrueAndIsGroupSaleTrueOrCategoryIdTreeContainingAndSubTitleContainingIgnoreCaseAndIsOnSaleTrueAndIsGroupSaleTrueOrCategoryIdTreeContainingAndDetailContainingIgnoreCaseAndIsOnSaleTrueAndIsGroupSaleTrue(
						catIdStr, keywords, catIdStr, keywords, catIdStr, keywords, pageRequest);
	}

	public Page<TdGoods> findByCategoryIdTreeContainingAndIsOnSaleFalseAndIsGroupSaleTrueOrderBySortIdAsc(Long catId,
			int page, int size) {
		PageRequest pageRequest = new PageRequest(page, size,
				new Sort(Direction.ASC, "sortId").and(new Sort(Direction.DESC, "id")));

		String catIdStr = "[" + catId + "]";

		return repository.findByCategoryIdTreeContainingAndIsOnSaleFalseAndIsGroupSaleTrue(catIdStr, pageRequest);
	}

	public Page<TdGoods> searchAndFindByCategoryIdAndIsOnSaleFalseAndIsGroupSaleTrueOrderBySortIdAsc(String keywords,
			Long categoryId, int page, int size) {
		PageRequest pageRequest = new PageRequest(page, size,
				new Sort(Direction.ASC, "sortId").and(new Sort(Direction.DESC, "id")));

		String catIdStr = "[" + categoryId + "]";

		return repository
				.findByCategoryIdTreeContainingAndTitleContainingIgnoreCaseAndIsOnSaleFalseAndIsGroupSaleTrueOrCategoryIdTreeContainingAndSubTitleContainingIgnoreCaseAndIsOnSaleTrueAndIsGroupSaleTrueOrCategoryIdTreeContainingAndDetailContainingIgnoreCaseAndIsOnSaleTrueAndIsGroupSaleTrue(
						catIdStr, keywords, catIdStr, keywords, catIdStr, keywords, pageRequest);
	}

	public Page<TdGoods> findByCategoryIdTreeContainingAndIsGroupSaleTrueOrderBySortIdAsc(Long catId, int page,
			int size) {
		PageRequest pageRequest = new PageRequest(page, size,
				new Sort(Direction.ASC, "sortId").and(new Sort(Direction.DESC, "id")));

		String catIdStr = "[" + catId + "]";

		return repository.findByCategoryIdTreeContainingAndIsGroupSaleTrue(catIdStr, pageRequest);
	}

	public Page<TdGoods> searchAndFindByCategoryIdAndIsGroupSaleTrueOrderBySortIdAsc(String keywords, Long categoryId,
			int page, int size) {
		PageRequest pageRequest = new PageRequest(page, size,
				new Sort(Direction.ASC, "sortId").and(new Sort(Direction.DESC, "id")));

		String catIdStr = "[" + categoryId + "]";

		return repository
				.findByCategoryIdTreeContainingAndTitleContainingIgnoreCaseAndIsGroupSaleTrueOrCategoryIdTreeContainingAndSubTitleContainingIgnoreCaseAndIsGroupSaleTrueOrCategoryIdTreeContainingAndDetailContainingIgnoreCaseAndIsGroupSaleTrue(
						catIdStr, keywords, catIdStr, keywords, catIdStr, keywords, pageRequest);
	}
	/**** 后台商品删选 ****/

	/**** 团购和秒杀 ****/
	/**
	 * @author lc
	 * @注释：所有秒杀商品
	 */
	public Page<TdGoods> findByFlashSaleAllOrderByFlashSaleStartTimeAsc(int page, int size) {
		PageRequest pageRequest = new PageRequest(page, size);

		return repository.findByIsFlashSaleTrueAndIsOnSaleTrueOrderByFlashSaleStartTimeAsc(pageRequest);
	}

	/**
	 * 所有团购
	 * 
	 * @param page
	 * @param size
	 * @return
	 */
	public Page<TdGoods> findByGroupSaleAllOrderByGroupSaleStartTimeAsc(int page, int size) {
		PageRequest pageRequest = new PageRequest(page, size,
				new Sort(Direction.ASC, "sortId").and(new Sort(Direction.DESC, "id")));

		return repository.findByIsGroupSaleTrueAndIsOnSaleTrueOrderByGroupSaleStartTimeAsc(pageRequest);
	}

	/**
	 * 正在团购商品
	 * 
	 * @param page
	 * @param size
	 * @return
	 */
	public Page<TdGoods> findByGroupSalingOrderByGroupSaleStartTimeAsc(int page, int size) {
		PageRequest pageRequest = new PageRequest(page, size,
				new Sort(Direction.ASC, "sortId").and(new Sort(Direction.DESC, "id")));

		return repository
				.findByIsGroupSaleTrueAndIsOnSaleTrueAndGroupSaleStopTimeAfterAndGroupSaleStartTimeBeforeOrderByGroupSaleStartTimeAsc(
						new Date(), new Date(), pageRequest);
	}

	/**
	 * 正在秒杀商品
	 * 
	 * @param page
	 * @param size
	 * @return
	 */
	public Page<TdGoods> findByFlashSalingOrderByFlashSaleStartTimeAsc(int page, int size) {
		PageRequest pageRequest = new PageRequest(page, size,
				new Sort(Direction.ASC, "sortId").and(new Sort(Direction.DESC, "id")));

		return repository
				.findByIsFlashSaleTrueAndIsOnSaleTrueAndFlashSaleStopTimeAfterAndFlashSaleStartTimeBeforeOrderByFlashSaleStartTimeAsc(
						new Date(), new Date(), pageRequest);
	}

	/**
	 * 已结束团购
	 * 
	 * @param page
	 * @param size
	 * @return
	 */
	public Page<TdGoods> findByGroupSaleEndedOrderByGroupSaleStartTimeAsc(int page, int size) {
		PageRequest pageRequest = new PageRequest(page, size,
				new Sort(Direction.ASC, "sortId").and(new Sort(Direction.DESC, "id")));

		return repository.findByIsGroupSaleTrueAndIsOnSaleTrueAndGroupSaleStopTimeBeforeOrderByGroupSaleStartTimeAsc(
				new Date(), pageRequest);
	}

	/**
	 * 已结束秒杀
	 * 
	 * @param page
	 * @param size
	 * @return
	 */
	public Page<TdGoods> findByFlashSaleEndedOrderByFlashSaleStartTimeAsc(int page, int size) {
		PageRequest pageRequest = new PageRequest(page, size,
				new Sort(Direction.ASC, "sortId").and(new Sort(Direction.DESC, "id")));

		return repository.findByIsFlashSaleTrueAndIsOnSaleTrueAndFlashSaleStopTimeBeforeOrderByFlashSaleStartTimeAsc(
				new Date(), pageRequest);
	}

	/**
	 * 即将开始团购
	 * 
	 * @param page
	 * @param size
	 * @return
	 */
	public Page<TdGoods> findByGroupSaleGoingToStartOrderByGroupSaleStartTimeAsc(int page, int size) {
		PageRequest pageRequest = new PageRequest(page, size,
				new Sort(Direction.ASC, "sortId").and(new Sort(Direction.DESC, "id")));

		return repository.findByIsGroupSaleTrueAndIsOnSaleTrueAndGroupSaleStartTimeAfterOrderByGroupSaleStartTimeAsc(
				new Date(), pageRequest);
	}

	/**
	 * 即将开始秒杀
	 * 
	 * @param page
	 * @param size
	 * @return
	 */
	public Page<TdGoods> findByFlashSaleGoingToStartOrderByFlashSaleStartTimeAsc(int page, int size) {
		PageRequest pageRequest = new PageRequest(page, size,
				new Sort(Direction.ASC, "sortId").and(new Sort(Direction.DESC, "id")));

		return repository.findByIsFlashSaleTrueAndIsOnSaleTrueAndFlashSaleStartTimeAfterOrderByFlashSaleStartTimeAsc(
				new Date(), pageRequest);
	}

	/******** 商品信息查找部分 ***********/

	/**
	 * 判断该商品是否正在进行秒杀
	 * 
	 * @param tdGoods
	 * @return
	 */
	public boolean isFlashSaleTrue(TdGoods tdGoods) {
		if (null == tdGoods) {
			return false;
		}

		Date curr = new Date();

		if (null != tdGoods.getIsFlashSale() && tdGoods.getIsFlashSale() && null != tdGoods.getFlashSaleStartTime()
				&& tdGoods.getFlashSaleStartTime().before(curr) && null != tdGoods.getFlashSaleStopTime()
				&& tdGoods.getFlashSaleStopTime().after(curr) && null != tdGoods.getFlashSaleLeftNumber()
				&& tdGoods.getFlashSaleLeftNumber().compareTo(0L) > 0) {
			return true;
		}

		return false;
	}

	/**
	 * 计算实时秒杀价
	 * 
	 * @param goods
	 * @return
	 */
	public Double getFlashPrice(TdGoods goods) {
		if (null == goods) {
			return null;
		}

		Double flashPrice = null;
		Date curr = new Date();

		if (null != goods.getIsFlashSale() && null != goods.getFlashSaleStartTime()
				&& null != goods.getFlashSaleStopTime() && null != goods.getFlashSalePrice() && goods.getIsFlashSale()
				&& goods.getFlashSaleStopTime().after(curr) && goods.getFlashSaleStartTime().before(curr)) {
			// 剩余毫秒数
			long ts = goods.getFlashSaleStopTime().getTime() - curr.getTime();
			// 总共毫秒数
			long allts = goods.getFlashSaleStopTime().getTime() - goods.getFlashSaleStartTime().getTime();

			flashPrice = goods.getFlashSalePrice() * ts / allts;

			BigDecimal b = new BigDecimal(flashPrice);

			flashPrice = b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
		}

		return flashPrice;
	}

	/**
	 * 判断该商品是否正在进行团购
	 * 
	 * @param tdGoods
	 * @return
	 */
	public boolean isGroupSaleTrue(TdGoods tdGoods) {
		if (null == tdGoods) {
			return false;
		}

		Date curr = new Date();

		if (null != tdGoods.getIsGroupSale() && tdGoods.getIsGroupSale() && null != tdGoods.getGroupSaleStartTime()
				&& tdGoods.getGroupSaleStartTime().before(curr) && null != tdGoods.getGroupSaleStopTime()
				&& tdGoods.getGroupSaleStopTime().after(curr) && null != tdGoods.getGroupSaleLeftNumber()
				&& tdGoods.getGroupSaleLeftNumber().compareTo(0L) > 0) {
			return true;
		}

		return false;
	}
	/**** 团购和秒杀 ****/

	/**
	 * 删除
	 * 
	 * @param id
	 *            菜单项ID
	 */
	public void delete(Long id) {
		if (null != id) {
			repository.delete(id);
		}
	}

	/**
	 * 删除
	 * 
	 * @param e
	 *            菜单项
	 */
	public void delete(TdGoods e) {
		if (null != e) {
			repository.delete(e);
		}
	}

	/**
	 * 查找
	 * 
	 * @param id
	 *            ID
	 * @return
	 */
	public TdGoods findOne(Long id) {
		if (null == id) {
			return null;
		}

		return repository.findOne(id);
	}

	/**
	 * 保存类型
	 * 
	 * @param e
	 * @return
	 */
	public TdGoods save(TdGoods e, String manager) {
		if (null == e) {
			return null;
		}

		// 参数类型ID
		Long paramCategoryId = null;

		// 保存分类名称
		if (null != e.getCategoryId()) {
			TdProductCategory cat = tdProductCategoryService.findOne(e.getCategoryId());
			e.setCategoryTitle(cat.getTitle());
			e.setCategoryIdTree(cat.getParentTree());

			paramCategoryId = cat.getParamCategoryId();
		}

		// // 保存品牌名称
		// if (null != e.getCategoryId()) {
		// TdBrand brand = tdBrandService.findOne(e.getBrandId());
		//
		// if (null != brand) {
		// e.setBrandTitle(brand.getTitle());
		// }
		// }

		// 保存销售价
		if (null == e.getReturnPrice()) {
			e.setReturnPrice(0.0);
		}

		if (null == e.getOutFactoryPrice()) {
			e.setOutFactoryPrice(0.0);
		}

		e.setSalePrice(e.getReturnPrice() + e.getOutFactoryPrice());

		// 创建时间
		if (null == e.getCreateTime()) {
			e.setCreateTime(new Date());
		}

		// 上架时间
		if (null == e.getOnSaleTime() && e.getIsOnSale()) {
			e.setOnSaleTime(new Date());
		}

		// 仓库名
		// if (null != e.getWarehouseId()) {
		// TdWarehouse w = tdWarehouseService.findOne(e.getWarehouseId());
		//
		// if (null != w) {
		// e.setWarehouseTitle(w.getTitle());
		// }
		// }

		// 当修改时，若切换过类型，参数数量由多变少，需要删除多余的参数
		// if (null != e.getId() && null != paramCategoryId) {
		// int count = tdParameterService
		// .countByCategoryTreeContaining(paramCategoryId);
		// int size = e.getParamList().size();
		//
		// if (size > count) {
		// List<TdGoodsParameter> subList = e.getParamList().subList(
		// count, size);
		// tdGoodsParameterService.delete(subList);
		// e.getParamList().removeAll(subList);
		// }
		// }

		// 当修改时，赠品数量减少时，需删除多余的赠品
		if (null != e.getId() && null != e.getGiftList() && null != e.getTotalGift()) {
			int count = e.getTotalGift();
			int size = e.getGiftList().size();

			if (size > count) {
				List<TdGoodsGift> subList = e.getGiftList().subList(count, size);
				tdGoodsGiftService.delete(subList);
				e.getGiftList().removeAll(subList);
			}
		}

		// 当修改时，组合商品数量减少时，需删除多余的组合商品
		if (null != e.getId() && null != e.getCombList() && null != e.getTotalComb()) {
			int count = e.getTotalComb();
			int size = e.getCombList().size();

			if (size > count) {
				List<TdGoodsCombination> subList = e.getCombList().subList(count, size);
				tdGoodsCombinationService.delete(subList);
				e.getCombList().removeAll(subList);
			}
		}

		// if (null != e.getParamList() && e.getParamList().size() > 0) {
		// String valueCollect = "";
		// for (TdGoodsParameter gp : e.getParamList()) {
		// valueCollect += gp.getValue();
		// valueCollect += ",";
		// }
		// e.setParamValueCollect(valueCollect);
		//
		// // 保存参数
		// tdGoodsParameterService.save(e.getParamList());
		// } else {
		// e.setParamValueCollect("");
		// }

		if (null == e.getId()) {
			if (null != e.getGiftList() && e.getGiftList().size() > 0) {
				e.setTotalGift(e.getGiftList().size());
			}

			if (null != e.getCombList() && e.getCombList().size() > 0) {
				e.setTotalComb(e.getCombList().size());
			}
		}

		// 保存赠品
		tdGoodsGiftService.save(e.getGiftList());

		// 保存组合
		tdGoodsCombinationService.save(e.getCombList());

		e = repository.save(e);

		// 添加改价记录
		TdPriceChangeLog priceLog = tdPriceChangeLogService.findTopByGoodsIdOrderByIdDesc(e.getId());

		// 没有改过价，或改价后的记录与当前销售价不相等
		if (null == priceLog || !priceLog.getPrice().equals(e.getSalePrice())) {
			TdPriceChangeLog newPriceLog = new TdPriceChangeLog();

			newPriceLog.setCreateTime(new Date());
			newPriceLog.setGoodsId(e.getId());
			newPriceLog.setGoodsTitle(e.getTitle() + (null == e.getSelectOneValue() ? "" : " " + e.getSelectOneValue())
					+ (null == e.getSelectTwoValue() ? "" : " " + e.getSelectTwoValue())
					+ (null == e.getSelectThreeValue() ? "" : " " + e.getSelectThreeValue()));
			newPriceLog.setOperator(manager);

			if (null != priceLog) {
				newPriceLog.setOriginPrice(priceLog.getPrice());
			}

			newPriceLog.setPrice(e.getSalePrice());
			newPriceLog.setSortId(99.00);

			tdPriceChangeLogService.save(newPriceLog);
		}

		return e;
	}

	/**
	 * 查找所有首页推荐的商品
	 * 
	 * @author dengxiao
	 */
	public List<TdGoods> findByIsRecommendIndexTrueAndIsOnSaleTrueOrderBySortIdAsc() {
		return repository.findByIsRecommendIndexTrueAndIsOnSaleTrueOrderBySortIdAsc();
	}

	/**
	 * 根据分类id查找其下所有的商品（不分页）
	 * 
	 * @author dengxiao
	 */
	public List<TdGoods> findByCategoryIdAndIsOnSaleTrueOrderBySortIdAsc(Long categoryId) {
		if (null == categoryId) {
			return null;
		}
		return repository.findByCategoryIdAndIsOnSaleTrueOrderBySortIdAsc(categoryId);
	}

}
