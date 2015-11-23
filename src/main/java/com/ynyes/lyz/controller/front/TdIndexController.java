package com.ynyes.lyz.controller.front;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ynyes.lyz.entity.TdAd;
import com.ynyes.lyz.entity.TdAdType;
import com.ynyes.lyz.entity.TdArticle;
import com.ynyes.lyz.entity.TdArticleCategory;
import com.ynyes.lyz.entity.TdGoods;
import com.ynyes.lyz.entity.TdNaviBarItem;
import com.ynyes.lyz.service.TdAdService;
import com.ynyes.lyz.service.TdAdTypeService;
import com.ynyes.lyz.service.TdArticleCategoryService;
import com.ynyes.lyz.service.TdArticleService;
import com.ynyes.lyz.service.TdCommonService;
import com.ynyes.lyz.service.TdGoodsService;
import com.ynyes.lyz.service.TdNaviBarItemService;

@Controller
@RequestMapping(value = "/")
public class TdIndexController {

	@Autowired
	private TdAdTypeService tdAdTypeService;

	@Autowired
	private TdAdService tdAdService;

	@Autowired
	private TdArticleCategoryService tdArticleCategoryService;

	@Autowired
	private TdArticleService tdArticleService;

	@Autowired
	private TdGoodsService tdGoodsService;

	@Autowired
	private TdCommonService tdCommonService;
	
	@Autowired
	private TdNaviBarItemService tdNaviBarItemService;

	@RequestMapping
	public String index(HttpServletRequest req, ModelMap map) {
		String username = (String) req.getSession().getAttribute("username");
		if (null == username) {
			return "redirect:/login";
		}

		tdCommonService.setHeader(req, map);

		// 查找首页轮播广告
		TdAdType adType = tdAdTypeService.findByTitle("首页轮播广告");
		if(null != adType){
			List<TdAd> circle_ad_list = tdAdService.findByTypeId(adType.getId());
			map.addAttribute("circle_ad_list", circle_ad_list);
		}
		
		//查找首页中部广告
		TdAdType index_center_adType = tdAdTypeService.findByTitle("首页中部广告");
		if(null != adType){
			List<TdAd> index_center_list = tdAdService.findByTypeId(index_center_adType.getId());
			map.addAttribute("index_center", index_center_list.get(0));
		}
		
		// 查找头条信息
		TdArticleCategory articleCategory = tdArticleCategoryService.findByTitle("头条消息");
		if(null != articleCategory){
			List<TdArticle> headline_list = tdArticleService.findByCategoryId(articleCategory.getId());
			map.addAttribute("headline_list", headline_list);
		}

		//查找导航栏
		List<TdNaviBarItem> navi_bar_list = tdNaviBarItemService.findByIsEnableTrueOrderBySortIdAsc();
		map.addAttribute("navi_bar_list", navi_bar_list);
		
		
		// 查找首页推荐商品
		List<TdGoods> index_recommend_list = tdGoodsService.findByIsRecommendIndexTrueAndIsOnSaleTrueOrderBySortIdAsc();
		map.addAttribute("index_recommend_list", index_recommend_list);

		return "/client/index";
	}
}
