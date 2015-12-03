package com.ynyes.lyz.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import com.ynyes.lyz.entity.TdGoods;
import com.ynyes.lyz.entity.TdProductCategory;
import com.ynyes.lyz.util.StringUtils;

@Service
public class TdCommonService {

	@Autowired
	private TdUserService tdUserService;

	@Autowired
	private TdProductCategoryService tdProductCategoryService;

	@Autowired
	private TdGoodsService tdGoodsService;

	public void setHeader(HttpServletRequest req, ModelMap map) {
		String username = (String) req.getSession().getAttribute("username");
		if (null != username) {
			map.addAttribute("username", username);
			map.addAttribute("user", tdUserService.findByUsernameAndIsEnableTrue(username));
		}

	}

	// 查找三级分类的方法并查找指定三级分类下的所有商品
	public void getCategory(HttpServletRequest req, ModelMap map) {
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
			}
		}
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
