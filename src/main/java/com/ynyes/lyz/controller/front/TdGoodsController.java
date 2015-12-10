package com.ynyes.lyz.controller.front;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ynyes.lyz.entity.TdColorPackage;
import com.ynyes.lyz.entity.TdGoods;
import com.ynyes.lyz.entity.TdUser;
import com.ynyes.lyz.entity.TdUserComment;
import com.ynyes.lyz.entity.TdUserRecentVisit;
import com.ynyes.lyz.service.TdColorPackageService;
import com.ynyes.lyz.service.TdCommonService;
import com.ynyes.lyz.service.TdGoodsService;
import com.ynyes.lyz.service.TdUserCommentService;
import com.ynyes.lyz.service.TdUserRecentVisitService;
import com.ynyes.lyz.service.TdUserService;
import com.ynyes.lyz.util.ClientConstant;

@Controller
@RequestMapping(value = "/goods")
public class TdGoodsController {

	@Autowired
	private TdCommonService tdCommonService;

	@Autowired
	private TdGoodsService tdGoodsService;

	@Autowired
	private TdUserService tdUserService;

	@Autowired
	private TdColorPackageService tdColorPackageService;

	@Autowired
	private TdUserCommentService tdUserCommentService;

	@Autowired
	private TdUserRecentVisitService tdUserRecentVisitService;

	/*
	 *********************************** 普通下单模式的控制器和方法*****************************************************
	 */

	@RequestMapping(value = "/normal/list")
	public String goodsListNormal(HttpServletRequest req, ModelMap map, Long selected_goods_number) {
		String username = (String) req.getSession().getAttribute("username");
		if (null == username) {
			return "redirect:/login";
		}

		tdCommonService.setHeader(req, map);
		tdCommonService.getCategory(req, map);

		if (null == selected_goods_number) {
			selected_goods_number = 0L;
		}

		map.addAttribute("selected_goods_number", selected_goods_number);

		return "/client/goods_list_normal";
	}

	// 获取调色包的方法
	@RequestMapping(value = "/get/color")
	public String getColor(HttpServletRequest req, Long goodsId, Long quantity, ModelMap map) {
		String username = (String) req.getSession().getAttribute("username");
		TdUser user = tdUserService.findByUsernameAndIsEnableTrue(username);
		List<TdColorPackage> color_package_list = tdColorPackageService
				.findByGoodsIdAndRegionIdOrderBySortIdAsc(goodsId, user.getCityId());
		if (null != color_package_list && color_package_list.size() > 0) {
			if (null != color_package_list.get(0).getPrice()) {
				map.addAttribute("unit_price", color_package_list.get(0).getPrice());
			}
		}

		// 获取所有已经选择的调色包
		@SuppressWarnings("unchecked")
		Map<String, List<TdColorPackage>> all_color = (Map<String, List<TdColorPackage>>) req.getSession()
				.getAttribute("all_color");
		if (null == all_color) {
			all_color = new HashMap<>();
		}
		List<TdColorPackage> colors = all_color.get("goods" + goodsId);
		map.addAttribute("select_colors", colors);

		map.addAttribute("goodsId", goodsId);
		map.addAttribute("color_package_list", color_package_list);
		return "/client/color_package";
	}

	// 添加已选调色包的方法
	@RequestMapping(value = "/color/add")
	public String colorAdd(String colorName, Long goodsId, Long quantity, Long max, ModelMap map,
			HttpServletRequest req) {
		colorName = colorName.trim();
		TdColorPackage colorPackage = tdColorPackageService.findByNumber(colorName);
		colorPackage.setQuantity(quantity);
		colorPackage.setTotalPrice(colorPackage.getPrice() * quantity);
		// 获取所有已经选择的调色包
		@SuppressWarnings("unchecked")
		Map<String, List<TdColorPackage>> all_color = (Map<String, List<TdColorPackage>>) req.getSession()
				.getAttribute("all_color");
		if (null == all_color) {
			all_color = new HashMap<>();
		}
		List<TdColorPackage> colors = all_color.get("goods" + goodsId);

		if (null == colors) {
			colors = new ArrayList<>();
		}
		Boolean isHave = false;
		for (int i = 0; i < colors.size(); i++) {
			if (colorName.equals(colors.get(i).getNumber())) {
				isHave = true;
				colors.get(i).setQuantity(colors.get(i).getQuantity() + quantity);
				colors.get(i).setTotalPrice(colors.get(i).getPrice() * colors.get(i).getQuantity());
			}
		}
		if (!isHave) {
			colors.add(colorPackage);
		}

		all_color.put("goods" + goodsId, colors);
		req.getSession().setAttribute("all_color", all_color);

		map.addAttribute("unit_price", colorPackage.getPrice());
		map.addAttribute("select_colors", colors);
		map.addAttribute("goodsId", goodsId);
		return "/client/selected_color_package";
	}

	// 删除已选调色包的方法
	@RequestMapping(value = "/delete/color")
	@ResponseBody
	public Map<String, Object> deleteColor(HttpServletRequest req, Long colorPackageId, Long goodsId) {
		Map<String, Object> res = new HashMap<>();
		res.put("status", -1);

		// 获取所有已经选择的调色包
		@SuppressWarnings("unchecked")
		Map<String, List<TdColorPackage>> all_color = (Map<String, List<TdColorPackage>>) req.getSession()
				.getAttribute("all_color");
		if (null == all_color) {
			all_color = new HashMap<>();
		}
		List<TdColorPackage> colors = all_color.get("goods" + goodsId);
		if (null != colors) {
			for (int i = 0; i < colors.size(); i++) {
				if (null != colors.get(i) && null != colors.get(i).getId() && colorPackageId == colors.get(i).getId()) {

				}
			}
		}
		res.put("status", 0);
		return res;
	}

	/*
	 *********************************** 结束***********************************************************
	 */

	/*
	 *********************************** 步骤下单模式的控制器和方法*******************************************
	 */
	@RequestMapping(value = "/step/list")
	public String goodsListStep(HttpServletRequest req, ModelMap map) {
		String username = (String) req.getSession().getAttribute("username");
		if (null == username) {
			return "redirect:/login";
		}

		tdCommonService.setHeader(req, map);
		tdCommonService.getCategory(req, map);

		return "/client/goods_list_step";
	}

	/*
	 *********************************** 结束************************************************************
	 */

	// 查看商品详情的方法
	@RequestMapping(value = "/detail/{id}")
	public String goodsDetail(HttpServletRequest req, ModelMap map, @PathVariable Long id, Long quantity) {
		String username = (String) req.getSession().getAttribute("username");
		if (null == username) {
			return "redirect:/login";
		}

		TdUser user = tdUserService.findByUsername(username);

		// 获取指定id的商品的信息
		TdGoods goods = tdGoodsService.findOne(id);
		map.addAttribute("goods", goods);

		// 添加浏览记录
		TdUserRecentVisit visit = new TdUserRecentVisit();
		visit.setUsername(username);
		visit.setUserId(user.getId());
		visit.setGoodsId(goods.getId());
		visit.setGoodsTitle(goods.getTitle());
		visit.setGoodsCoverImageUri(goods.getCoverImageUri());
		visit.setGoodsSalePrice(goods.getSalePrice());
		visit.setVisitTime(new Date());
		// 默认排序号1
		visit.setSortId(1L);

		// 查找当前用户所有的浏览记录
		List<TdUserRecentVisit> all_visit = tdUserRecentVisitService.findByUserIdOrderByVisitTimeAsc(user.getId());

		if (null != all_visit && all_visit.size() == ClientConstant.MAXRECENTNUM) {
			tdUserRecentVisitService.delete(all_visit.get(0));
		}

		// 存储新的浏览记录
		tdUserRecentVisitService.save(visit);

		// 获取评论
		List<TdUserComment> user_comment_list = tdUserCommentService.findByGoodsIdAndIsShowable(id);
		map.addAttribute("user_comment_list", user_comment_list);

		map.addAttribute("select_quantity", quantity);
		return "/client/goods_detail";
	}

}
