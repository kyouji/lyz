package com.ynyes.lyz.controller.front;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ynyes.lyz.entity.TdActivity;
import com.ynyes.lyz.entity.TdAd;
import com.ynyes.lyz.entity.TdAdType;
import com.ynyes.lyz.entity.TdArticle;
import com.ynyes.lyz.entity.TdArticleCategory;
import com.ynyes.lyz.entity.TdDiySite;
import com.ynyes.lyz.entity.TdGoods;
import com.ynyes.lyz.entity.TdNaviBarItem;
import com.ynyes.lyz.entity.TdPriceListItem;
import com.ynyes.lyz.entity.TdUser;
import com.ynyes.lyz.service.TdAdService;
import com.ynyes.lyz.service.TdAdTypeService;
import com.ynyes.lyz.service.TdArticleCategoryService;
import com.ynyes.lyz.service.TdArticleService;
import com.ynyes.lyz.service.TdCommonService;
import com.ynyes.lyz.service.TdDiySiteService;
import com.ynyes.lyz.service.TdGoodsService;
import com.ynyes.lyz.service.TdNaviBarItemService;
import com.ynyes.lyz.service.TdPriceListItemService;
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
	private TdPriceListItemService tdPriceListService;

	@Autowired
	private TdCommonService tdCommonService;

	@Autowired
	private TdNaviBarItemService tdNaviBarItemService;

	@Autowired
	private TdGoodsService tdGoodsservice;

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
		if (null != diySite) {
			Page<TdPriceListItem> commend_page = tdPriceListService
					.findByPriceListNumberAndIsCommendIndexTrueOrderBySortIdAsc(diySite.getPriceListNumber(),
							ClientConstant.pageSize, 0);
			map.addAttribute("commend_page", commend_page);

		}

		// 查找所有参与促销的价目表项
		if (null != diySite) {
			List<TdPriceListItem> promotion_list_temp = tdPriceListService
					.findByPriceListNumberAndIsPromotionTrueOrderBySortIdAsc(diySite.getPriceListNumber());
			// 所有的促销商品价目表项
			List<TdPriceListItem> promotion_list = new ArrayList<>();
			// 首页特别推荐促销商品的价目表项
			for (TdPriceListItem tdPriceList : promotion_list_temp) {
				// 如果指定价目表项的活动列表不为null，且数据量大于0，则遍历活动列表，判断参加的活动是否过期
				if (null != tdPriceList && null != tdPriceList.getActivities()
						&& tdPriceList.getActivities().size() > 0) {
					// 设置一个基础布尔值，其值为false，表示活动已经过期
					Boolean outDate = false;
					// 遍历活动，判断活动是否过期
					for (TdActivity activity : tdPriceList.getActivities()) {
						if (null != activity.getBeginDate() && null != activity.getFinishDate()) {
							// 获取当前时间的毫秒数
							Long now = new Date().getTime();
							if (now > activity.getBeginDate().getTime() && now < activity.getFinishDate().getTime()) {
								outDate = true;
							}
						}
					}
					// 如果活动没有过期，则将价目表项添加到之前准备的集合中
					if (outDate) {
						if (promotion_list.size() < 15) {
							promotion_list.add(tdPriceList);
						}
					} else {
						tdPriceList.setIsPromotion(outDate);
						tdPriceListService.save(tdPriceList);
					}
				}
			}
			// 获取价目表项的商品信息
			map.addAttribute("promotion_list", promotion_list);
			for (int i = 0; i < promotion_list.size(); i++) {
				Long goodsId = promotion_list.get(i).getGoodsId();
				if (null != goodsId) {
					TdGoods goods = tdGoodsservice.findOne(goodsId);
					map.addAttribute("goods" + i, goods);
				}
			}
		}

		return "/client/index";
	}
}
