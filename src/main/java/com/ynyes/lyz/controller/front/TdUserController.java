package com.ynyes.lyz.controller.front;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ynyes.lyz.entity.TdCartGoods;
import com.ynyes.lyz.entity.TdOrder;
import com.ynyes.lyz.entity.TdUser;
import com.ynyes.lyz.entity.TdUserCollect;
import com.ynyes.lyz.entity.TdUserLevel;
import com.ynyes.lyz.entity.TdUserRecentVisit;
import com.ynyes.lyz.service.TdCartGoodsService;
import com.ynyes.lyz.service.TdOrderService;
import com.ynyes.lyz.service.TdUserCollectService;
import com.ynyes.lyz.service.TdUserLevelService;
import com.ynyes.lyz.service.TdUserRecentVisitService;
import com.ynyes.lyz.service.TdUserService;

@Controller
@RequestMapping(value = "/user")
public class TdUserController {

	@Autowired
	private TdUserService tdUserService;

	@Autowired
	private TdUserRecentVisitService tdUserRecentVisitService;

	@Autowired
	private TdUserCollectService tdUserCollectService;

	@Autowired
	private TdCartGoodsService tdCartGoodsService;

	@Autowired
	private TdUserLevelService tdUserLevelService;

	@Autowired
	private TdOrderService tdOrderService;

	@RequestMapping
	public String userCenter(HttpServletRequest req, ModelMap map) {
		String username = (String) req.getSession().getAttribute("username");
		if (null == username) {
			return "redirect:/login";
		}
		TdUser tdUser = tdUserService.findByUsernameAndIsEnableTrue(username);
		map.addAttribute("user", tdUser);

		// 获取浏览记录
		List<TdUserRecentVisit> recent_list = tdUserRecentVisitService.findByUsername(username);
		map.addAttribute("recent_list", recent_list);

		// 获取收藏记录
		List<TdUserCollect> collect_list = tdUserCollectService.findByUsername(username);
		map.addAttribute("collect_list", collect_list);

		// 获取已选（从购物车中获取已选）
		List<TdCartGoods> cart_list = tdCartGoodsService.findByUsername(username);
		map.addAttribute("cart_list", cart_list);

		// 获取用户的等级
		TdUserLevel level = tdUserLevelService.findOne(tdUser.getUserLevelId());
		map.addAttribute("level", level);

		return "/client/user_center";
	}

	@RequestMapping(value = "/order/{typeId}")
	public String orderList(HttpServletRequest req, ModelMap map,@PathVariable Long typeId) {
		String username = (String) req.getSession().getAttribute("username");
		if (null == username) {
			return "redirect:/login";
		}

		// 查找所有待支付的订单
		List<TdOrder> all_order_list = tdOrderService.findByUsername(username);
		map.addAttribute("all_order_list", all_order_list);

		// 查找所有待支付的订单
		List<TdOrder> unpayed_order_list = tdOrderService.findByUsernameAndStatusId(username, 2L);
		map.addAttribute("unpayed_order_list", unpayed_order_list);

		// 查找所有待发货的订单
		// List<TdOrder> undeliver_order_list =
		// tdOrderService.findByUsernameAndStatusId(username, 3L);
		// map.addAttribute("undeliver_order_list", undeliver_order_list);

		// 查找所有待收货的订单
		List<TdOrder> unsignin_order_list = tdOrderService.findByUsernameAndStatusId(username, 4L);
		map.addAttribute("unsignin_order_list", unsignin_order_list);

		// 查找所有待评价的订单
		List<TdOrder> uncomment_order_list = tdOrderService.findByUsernameAndStatusId(username, 5L);
		map.addAttribute("uncomment_order_list", uncomment_order_list);

		map.addAttribute("typeId", typeId);
		return "/client/user_order_list";
	}
}
