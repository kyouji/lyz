package com.ynyes.lyz.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import com.ynyes.lyz.entity.TdColorPackage;
import com.ynyes.lyz.entity.TdDiySite;
import com.ynyes.lyz.entity.TdGoods;
import com.ynyes.lyz.entity.TdPriceListItem;
import com.ynyes.lyz.entity.TdProductCategory;
import com.ynyes.lyz.entity.TdUser;
import com.ynyes.lyz.util.StringUtils;

@Service
public class TdCommonService {

	@Autowired
	private TdUserService tdUserService;

	@Autowired
	private TdProductCategoryService tdProductCategoryService;

	@Autowired
	private TdGoodsService tdGoodsService;

	@Autowired
	private TdPriceListItemService tdPriceListItemService;

	@Autowired
	private TdDiySiteService tdDiySiteService;

	public void setHeader(HttpServletRequest req, ModelMap map) {
		String username = (String) req.getSession().getAttribute("username");
		if (null != username) {
			map.addAttribute("username", username);
			map.addAttribute("user", tdUserService.findByUsernameAndIsEnableTrue(username));
		}

	}

	// 查找三级分类的方法并查找指定三级分类下的所有商品
	public void getCategory(HttpServletRequest req, ModelMap map) {
		// 获取到登陆用户的用户名
		String username = (String) req.getSession().getAttribute("username");
		// 通过用户名查找到用户资料
		TdUser user = tdUserService.findByUsernameAndIsEnableTrue(username);
		// 获取登陆用户的门店信息
		TdDiySite diySite = tdDiySiteService.findOne(user.getUpperDiySiteId());
		// 根据门店信息获取到用户当前的价目表
		Long priceListId = null;
		if (null != diySite) {
			priceListId = diySite.getPriceListId();
		}

		// 查找到所有的一级分类
		List<TdProductCategory> level_one_categories = tdProductCategoryService.findByParentIdIsNullOrderBySortIdAsc();
		map.addAttribute("level_one_categories", level_one_categories);
		// 遍历一级分类用于查找所有的二级分类
		for (int i = 0; i < level_one_categories.size(); i++) {
			// 获取指定的一级分类
			TdProductCategory one_category = level_one_categories.get(i);
			// 根据指定的一级分类查找到该分类下所有的二级分类
			List<TdProductCategory> level_two_categories = tdProductCategoryService
					.findByParentIdOrderBySortIdAsc(one_category.getId());
			map.addAttribute("level_two_categories" + i, level_two_categories);
			// 遍历二级分类查找其下所有的商品
			for (int j = 0; j < level_two_categories.size(); j++) {
				TdProductCategory two_category = level_two_categories.get(j);
				List<TdGoods> goods_list = tdGoodsService
						.findByCategoryIdAndIsOnSaleTrueOrderBySortIdAsc(two_category.getId());
				map.addAttribute("goods_list" + i + j, goods_list);
				// 遍历所有的商品，查询在指定城市的商品的价格
				for (int k = 0; k < goods_list.size(); k++) {
					TdGoods goods = goods_list.get(k);
					if (null != goods) {
						TdPriceListItem priceListItem = tdPriceListItemService
								.findByPriceListIdAndGoodsId(priceListId, goods.getId());
						map.addAttribute("priceListItem" + i + j + k, priceListItem);
					}
				}
			}
		}
	}

	// 获取所有已选商品的方法
	public List<TdGoods> getSelectedGoods(HttpServletRequest req) {
		@SuppressWarnings("unchecked")
		List<TdGoods> all_selected = (ArrayList<TdGoods>) req.getSession().getAttribute("all_selected");
		return all_selected;
	}

	// 获取所有已选调色包的方法
	public Map<String, List<TdColorPackage>> getSelectedColorPackage(HttpServletRequest req) {
		@SuppressWarnings("unchecked")
		Map<String, List<TdColorPackage>> all_color = (HashMap<String, List<TdColorPackage>>) req.getSession()
				.getAttribute("all_color");
		return all_color;
	}

	// 获取已选数量（包括商品和调色包）的方法
	public Long getSelectedNumber(HttpServletRequest req) {
		Long selected_number = 0L;
		// 获取所有已选的商品
		List<TdGoods> all_selected = getSelectedGoods(req);
		if (null != all_selected) {
			selected_number += all_selected.size();
		}
		// 获取所有已选的调色包
		Map<String, List<TdColorPackage>> selected_color = getSelectedColorPackage(req);
		// 遍历所有已选择的调色包
		if (null != selected_color) {
			for (List<TdColorPackage> color_list : selected_color.values()) {
				if (null != color_list) {
					selected_number += color_list.size();
				}
			}
		}
		return selected_number;
	}

	public static String getIp(HttpServletRequest request) {
		if (request == null)
			return "";
		String ip = request.getHeader("X-Requested-For");
		if (StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("X-Forwarded-For");
		}
		if (StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_CLIENT_IP");
		}
		if (StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_X_FORWARDED_FOR");
		}
		if (StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}
}
