package com.ynyes.lyz.controller.front;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ynyes.lyz.entity.TdAd;
import com.ynyes.lyz.entity.TdAdType;
import com.ynyes.lyz.entity.TdArticle;
import com.ynyes.lyz.entity.TdArticleCategory;
import com.ynyes.lyz.entity.TdDiySite;
import com.ynyes.lyz.entity.TdNaviBarItem;
import com.ynyes.lyz.entity.TdPriceList;
import com.ynyes.lyz.entity.TdUser;
import com.ynyes.lyz.service.TdAdService;
import com.ynyes.lyz.service.TdAdTypeService;
import com.ynyes.lyz.service.TdArticleCategoryService;
import com.ynyes.lyz.service.TdArticleService;
import com.ynyes.lyz.service.TdCommonService;
import com.ynyes.lyz.service.TdDiySiteService;
import com.ynyes.lyz.service.TdNaviBarItemService;
import com.ynyes.lyz.service.TdPriceListService;
import com.ynyes.lyz.service.TdUserService;
import com.ynyes.lyz.util.ClientConstant;

@Controller
@RequestMapping(value = "/")
public class TdIndexController {

	@Autowired
	private TdAdTypeService tdAdTypeService;

	@Autowired
	private TdAdService tdAdService;

	@Autowired
	private TdUserService tdUserService;

	@Autowired
	private TdArticleCategoryService tdArticleCategoryService;

	@Autowired
	private TdArticleService tdArticleService;

	@Autowired
	private TdDiySiteService tdDiySiteService;

	@Autowired
	private TdPriceListService tdPriceListService;

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

		TdUser user = tdUserService.findByUsernameAndIsEnableTrue(username);
		// 查找指定用户所属的门店
		TdDiySite diySite = tdDiySiteService.findOne(user.getUpperDiySiteId());

		// 查找首页轮播广告
		TdAdType adType = tdAdTypeService.findByTitle("首页轮播广告");
		if (null != adType) {
			List<TdAd> circle_ad_list = tdAdService.findByTypeId(adType.getId());
			map.addAttribute("circle_ad_list", circle_ad_list);
		}

		// 查找首页中部广告
		TdAdType index_center_adType = tdAdTypeService.findByTitle("首页中部广告");
		if (null != adType) {
			List<TdAd> index_center_list = tdAdService.findByTypeId(index_center_adType.getId());
			map.addAttribute("index_center", index_center_list.get(0));
		}

		// 查找头条信息
		TdArticleCategory articleCategory = tdArticleCategoryService.findByTitle("头条消息");
		if (null != articleCategory) {
			List<TdArticle> headline_list = tdArticleService.findByCategoryId(articleCategory.getId());
			map.addAttribute("headline_list", headline_list);
		}

		// 查找导航栏
		List<TdNaviBarItem> navi_bar_list = tdNaviBarItemService.findByIsEnableTrueOrderBySortIdAsc();
		map.addAttribute("navi_bar_list", navi_bar_list);

		// 查找首页推荐商品
		Page<TdPriceList> commend_page = tdPriceListService.findByPriceListNumberAndIsCommendIndexTrueOrderBySortIdAsc(
				diySite.getPriceListNumber(), ClientConstant.pageSize, 0);
		map.addAttribute("commend_page", commend_page);

		return "/client/index";
	}
}
