package com.ynyes.lyz.controller.front;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ynyes.lyz.entity.TdBalanceLog;
import com.ynyes.lyz.entity.TdCartColorPackage;
import com.ynyes.lyz.entity.TdCartGoods;
import com.ynyes.lyz.entity.TdCity;
import com.ynyes.lyz.entity.TdDistrict;
import com.ynyes.lyz.entity.TdDiySite;
import com.ynyes.lyz.entity.TdOrder;
import com.ynyes.lyz.entity.TdPriceListItem;
import com.ynyes.lyz.entity.TdShippingAddress;
import com.ynyes.lyz.entity.TdSubdistrict;
import com.ynyes.lyz.entity.TdUser;
import com.ynyes.lyz.entity.TdUserCollect;
import com.ynyes.lyz.entity.TdUserLevel;
import com.ynyes.lyz.entity.TdUserRecentVisit;
import com.ynyes.lyz.entity.TdUserSuggestion;
import com.ynyes.lyz.entity.TdUserSuggestionCategory;
import com.ynyes.lyz.service.TdBalanceLogService;
import com.ynyes.lyz.service.TdCityService;
import com.ynyes.lyz.service.TdCommonService;
import com.ynyes.lyz.service.TdDistrictService;
import com.ynyes.lyz.service.TdDiySiteService;
import com.ynyes.lyz.service.TdOrderService;
import com.ynyes.lyz.service.TdPriceListItemService;
import com.ynyes.lyz.service.TdShippingAddressService;
import com.ynyes.lyz.service.TdSubdistrictService;
import com.ynyes.lyz.service.TdUserCollectService;
import com.ynyes.lyz.service.TdUserLevelService;
import com.ynyes.lyz.service.TdUserRecentVisitService;
import com.ynyes.lyz.service.TdUserService;
import com.ynyes.lyz.service.TdUserSuggestionCategoryService;
import com.ynyes.lyz.service.TdUserSuggestionService;
import com.ynyes.lyz.util.ClientConstant;
import com.ynyes.lyz.util.MD5;

import ch.qos.logback.core.net.server.Client;

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
	private TdUserLevelService tdUserLevelService;

	@Autowired
	private TdOrderService tdOrderService;

	@Autowired
	private TdCommonService tdCommonService;

	@Autowired
	private TdUserSuggestionCategoryService tdUserSuggestionCategoryService;

	@Autowired
	private TdPriceListItemService tdPriceListItemService;

	@Autowired
	private TdUserSuggestionService tdUserSuggestionService;

	@Autowired
	private TdDistrictService tdDistrictService;

	@Autowired
	private TdSubdistrictService tdSubdistrictService;

	@Autowired
	private TdCityService tdCityService;

	@Autowired
	private TdShippingAddressService tdShippingAddressService;

	@Autowired
	private TdDiySiteService tdDiySiteService;

	@Autowired
	private TdBalanceLogService tdBalanceLogService;

	/**
	 * 跳转到个人中心的方法（后期会进行修改，根据不同的角色，跳转的页面不同）
	 * 
	 * @author dengxiao
	 */
	@RequestMapping
	public String userCenter(HttpServletRequest req, ModelMap map) {
		String username = (String) req.getSession().getAttribute("username");
		TdUser user = tdUserService.findByUsernameAndIsEnableTrue(username);
		if (null == user) {
			return "redirect:/login";
		}
		map.addAttribute("user", user);

		// 获取浏览记录
		List<TdUserRecentVisit> recent_list = tdUserRecentVisitService.findByUsername(username);
		map.addAttribute("recent_list", recent_list);

		// 获取收藏记录
		List<TdUserCollect> collect_list = tdUserCollectService.findByUsername(username);
		map.addAttribute("collect_list", collect_list);

		// 获取已选
		Long number = tdCommonService.getSelectedNumber(req);
		map.addAttribute("number", number);

		// 获取用户的等级
		TdUserLevel level = tdUserLevelService.findOne(user.getUserLevelId());
		map.addAttribute("level", level);

		return "/client/user_center";
	}

	/**
	 * 查看我的订单的方法
	 * 
	 * @author dengxiao
	 */
	@RequestMapping(value = "/order/{typeId}")
	public String orderList(HttpServletRequest req, ModelMap map, @PathVariable Long typeId) {
		String username = (String) req.getSession().getAttribute("username");
		TdUser user = tdUserService.findByUsernameAndIsEnableTrue(username);
		if (null == user) {
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

	/**
	 * 跳转到我的收藏页面的方法
	 * 
	 * @author dengxiao
	 */
	@RequestMapping(value = "/collect")
	public String userCollect(HttpServletRequest req, ModelMap map) {
		String username = (String) req.getSession().getAttribute("username");
		TdUser user = tdUserService.findByUsernameAndIsEnableTrue(username);
		if (null == user) {
			return "redirect:/login";
		}

		List<TdUserCollect> collect_list = tdUserCollectService.findByUsernameOrderByCollectTimeDesc(username);
		map.addAttribute("collect_list", collect_list);
		TdDiySite diySite = tdCommonService.getDiySite(req);
		// 查找收藏的商品当前的价格
		if (null != collect_list && null != diySite) {
			for (int i = 0; i < collect_list.size(); i++) {
				TdUserCollect userCollect = collect_list.get(i);
				if (null != userCollect) {
					TdPriceListItem priceListItem = tdPriceListItemService
							.findByPriceListIdAndGoodsId(diySite.getPriceListId(), userCollect.getGoodsId());
					map.addAttribute("priceListItem" + i, priceListItem);
				}
			}
		}
		return "/client/user_collect_list";
	}

	/**
	 * 删除收藏商品的方法
	 * 
	 * @author dengxiao
	 */
	@RequestMapping(value = "/delete/collect")
	@ResponseBody
	public Map<String, Object> deleteCollect(HttpServletRequest req, Long id) {
		Map<String, Object> res = new HashMap<>();
		res.put("status", -1);
		// 查找到指定id的用户收藏
		TdUserCollect collect = tdUserCollectService.findOne(id);
		if (null == collect) {
			res.put("messag", "为查找到指定id的收藏");
			return res;
		}
		tdUserCollectService.delete(collect);
		res.put("status", 0);
		return res;
	}

	/**
	 * 跳转到浏览记录的方法
	 * 
	 * @author dengxiao
	 */
	@RequestMapping(value = "/recent")
	// param为【排序字段】-【规则1】-【规则2】-【规则3】
	public String userRecent(HttpServletRequest req, ModelMap map, String param) {
		String username = (String) req.getSession().getAttribute("username");
		TdUser user = tdUserService.findByUsernameAndIsEnableTrue(username);
		if (null == user) {
			return "redirect:/login";
		}

		if (null == param) {
			param = "0-0-0-0";
		}

		// 拆分排序字段
		String[] strs = param.split("-");
		String sortFiled = strs[0];
		String rule1 = strs[1];
		String rule2 = strs[2];
		String rule3 = strs[3];

		// 新建一个集合用于接收用户的浏览记录
		List<TdUserRecentVisit> recent_list = new ArrayList<>();

		// 获取用户归属门店的信息
		TdDiySite diySite = tdCommonService.getDiySite(req);

		if ("0".equals(sortFiled)) {
			if ("0".equals(rule1)) {
				recent_list = tdUserRecentVisitService.findByUsernameOrderByVisitTimeDesc(username);
				rule1 = "1";
			} else if ("1".equals(rule1)) {
				recent_list = tdUserRecentVisitService.findByUsernameOrderByVisitTimeAsc(username);
				rule1 = "0";
			}
		} else if ("1".equals(sortFiled)) {
			if ("0".equals(rule2)) {
				recent_list = tdUserRecentVisitService.findByUsernameOrderBySalePriceDesc(username,
						diySite.getPriceListId());
				rule2 = "1";
			} else if ("1".equals(rule2)) {
				recent_list = tdUserRecentVisitService.findByUsernameOrderBySalePriceAsc(username,
						diySite.getPriceListId());
				rule2 = "0";
			}
		} else if ("2".equals(sortFiled)) {
			if ("0".equals(rule3)) {
				recent_list = tdUserRecentVisitService.findByUsernameOrderBySoldNumberDesc(username);
				rule3 = "1";
			} else if ("1".equals(rule3)) {
				recent_list = tdUserRecentVisitService.findByUsernameOrderBySoldNumberAsc(username);
				rule3 = "0";
			}
		}

		map.addAttribute("selected_rule", Long.parseLong(sortFiled));
		map.addAttribute("rule1", rule1);
		map.addAttribute("rule2", rule2);
		map.addAttribute("rule3", rule3);

		// 遍历所有的浏览记录，获取所有浏览记录的价格
		for (int i = 0; i < recent_list.size(); i++) {
			TdUserRecentVisit recentVisit = recent_list.get(i);
			if (null != recentVisit) {
				// 获取指定商品的价目表项
				TdPriceListItem priceListItem = tdPriceListItemService
						.findByPriceListIdAndGoodsId(diySite.getPriceListId(), recentVisit.getGoodsId());
				map.addAttribute("priceListItem" + i, priceListItem);
			}
		}
		map.addAttribute("recent_list", recent_list);
		return "/client/user_recent_list";
	}

	/**
	 * 删除我的浏览记录的方法
	 * 
	 * @author dengxiao
	 */
	@RequestMapping(value = "/delete/recent")
	@ResponseBody
	public Map<String, Object> deleteRecent(HttpServletRequest req, Long id) {
		Map<String, Object> res = new HashMap<>();
		res.put("status", -1);
		// 查找到指定id的浏览记录
		TdUserRecentVisit recentVisit = tdUserRecentVisitService.findOne(id);
		if (null == recentVisit) {
			res.put("messag", "为查找到指定id的收藏");
			return res;
		}
		tdUserRecentVisitService.delete(recentVisit);
		res.put("status", 0);
		return res;
	}

	/**
	 * 跳转到我的已选页面的方法
	 * 
	 * @author dengxiao
	 */
	@RequestMapping(value = "/selected")
	public String mySelected(HttpServletRequest req, ModelMap map) {
		String username = (String) req.getSession().getAttribute("username");
		TdUser user = tdUserService.findByUsernameAndIsEnableTrue(username);
		if (null == user) {
			return "redirect:/login";
		}

		// 获取所有已选的商品
		List<TdCartGoods> all_selected = tdCommonService.getSelectedGoods(req);
		// 获取所有的调色包
		List<TdCartColorPackage> selected_colors = tdCommonService.getSelectedColorPackage(req);

		// 遍历所有的已选商品，获取以下数据：1. 与之对应的已选调色包
		for (int i = 0; i < all_selected.size(); i++) {
			TdCartGoods cartGoods = all_selected.get(i);
			if (null != cartGoods) {
				// 创建一个集合用于存储与之对应的已选调色包
				List<TdCartColorPackage> goods_colors = new ArrayList<>();

				// 完成第一个目的——遍历所有已选的调色包，找到与指定已选商品所对应的
				for (int j = 0; j < selected_colors.size(); j++) {
					TdCartColorPackage colorPackage = selected_colors.get(j);
					if (null != colorPackage && null != colorPackage.getGoodsId()
							&& colorPackage.getGoodsId() == cartGoods.getGoodsId()) {
						goods_colors.add(colorPackage);
					}
				}
				map.addAttribute("goods_colors" + i, goods_colors);
			}
		}
		map.addAttribute("all_selected", all_selected);
		return "/client/user_selected";
	}

	/**
	 * 更改已选数量的方法
	 * 
	 * @author dengxiao
	 */
	@RequestMapping(value = "/selected/change/quantity")
	@ResponseBody
	public Map<String, Object> selectedChangeQuantity(HttpServletRequest req, Long operation, Long type, Long goodsId,
			Long colorId) {
		Map<String, Object> res = new HashMap<>();
		res.put("status", -1);

		// 判断参数是否接收成功
		if (null == operation) {
			res.put("message", "operation参数接收失败！");
			res.put("status", -2);
			return res;
		}
		if (null == type) {
			res.put("message", "type参数接收失败！");
			res.put("status", -2);
			return res;
		}
		if (null == goodsId) {
			res.put("message", "goodsId参数接收失败！");
			res.put("status", -2);
			return res;
		}
		if (1L == type && null == colorId) {
			res.put("message", "colorId参数接收失败！");
			res.put("status", -2);
			return res;
		}

		// 操作已选商品的情况
		if (0 == type) {
			List<TdCartGoods> selected_goods = tdCommonService.getSelectedGoods(req);

		}

		// 操作已选调色包的情况
		if (1 == type) {

		}
		res.put("status", 0);
		return res;
	}

	/**
	 * 跳转到咨询投诉页面的方法
	 * 
	 * @auhor dengxiao
	 */
	@RequestMapping(value = "/suggestion")
	public String userSuggestion(HttpServletRequest req, ModelMap map) {
		String username = (String) req.getSession().getAttribute("username");
		TdUser user = tdUserService.findByUsernameAndIsEnableTrue(username);
		if (null == user) {
			return "redirect:/login";
		}

		// 查询得到所有能用的咨询分类
		List<TdUserSuggestionCategory> category_list = tdUserSuggestionCategoryService.findAll();

		map.addAttribute("category_list", category_list);
		map.addAttribute("username", username);
		return "/client/user_suggestion";
	}

	/**
	 * 保存咨询投诉的方法
	 * 
	 * @author dengxiao
	 */
	@RequestMapping(value = "/suggestion/save")
	@ResponseBody
	public Map<String, Object> userSuggestionSave(HttpServletRequest req, String phone, String suggestion,
			Long categoryId) {
		Map<String, Object> res = new HashMap<>();
		res.put("status", -1);

		// 判断用户是否登陆
		String username = (String) req.getSession().getAttribute("username");
		// 获取登陆用户的信息
		TdUser user = tdUserService.findByUsernameAndIsEnableTrue(username);
		if (null == user) {
			res.put("status", -2);
			return res;
		}

		// 创建一个实体用于存储咨询/投诉的信息
		TdUserSuggestion userSuggestion = new TdUserSuggestion();
		userSuggestion.setCategoryId(categoryId);
		userSuggestion.setContent(suggestion);
		userSuggestion.setUserId(user.getId());
		userSuggestion.setIsAnswered(false);
		userSuggestion.setCreateTime(new Date());
		tdUserSuggestionService.save(userSuggestion);

		res.put("status", 0);
		return res;
	}

	/**
	 * 跳转到用户信息界面的方法
	 * 
	 * @author dengxiao
	 */
	@RequestMapping(value = "/info")
	public String userInfo(HttpServletRequest req, ModelMap map) {
		String username = (String) req.getSession().getAttribute("username");
		TdUser user = tdUserService.findByUsernameAndIsEnableTrue(username);
		if (null == user) {
			return "redirect:/login";
		}
		map.addAttribute("user", user);
		return "/client/user_info";
	}

	/**
	 * 修改登陆用户生日或性别的方法
	 * 
	 * @author dengxiao
	 */
	@RequestMapping(value = "/change")
	@ResponseBody
	public Map<String, Object> userChangeInfo(HttpServletRequest req, Long type, String param) {
		Map<String, Object> res = new HashMap<>();
		res.put("status", -1);

		// 判断用户是否登陆
		String username = (String) req.getSession().getAttribute("username");
		TdUser user = tdUserService.findByUsernameAndIsEnableTrue(username);
		if (null == user) {
			res.put("status", -2);
			return res;
		}

		// 如果是修改性别
		if (0L == type) {
			user.setSex(param);
		}

		// 如果是修改生日
		if (1L == type) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date birthday = null;
			try {
				birthday = sdf.parse(param + " 00:00:00");
			} catch (ParseException e) {
				e.printStackTrace();
				return res;
			}
			user.setBirthday(birthday);
		}

		tdUserService.save(user);
		res.put("status", 0);
		return res;
	}

	/**
	 * 跳转到修改密码界面的方法
	 * 
	 * @author dengxiao
	 */
	@RequestMapping(value = "/edit/password")
	public String userEditPassword(HttpServletRequest req, ModelMap map) {
		String username = (String) req.getSession().getAttribute("username");
		TdUser user = tdUserService.findByUsernameAndIsEnableTrue(username);
		if (null == user) {
			return "redirect:/login";
		}
		return "/client/edit_password";
	}

	/**
	 * 验证并保存修改密码的方法
	 * 
	 * @author dengxiao
	 */
	@RequestMapping(value = "/edit/password/save")
	@ResponseBody
	public Map<String, Object> userEditPasswordSave(HttpServletRequest req, String oldPassword, String newPassword) {
		Map<String, Object> res = new HashMap<>();
		res.put("status", -1);

		// 判断是否登陆
		String username = (String) req.getSession().getAttribute("username");
		TdUser user = tdUserService.findByUsernameAndIsEnableTrue(username);
		if (null == user) {
			res.put("status", -2);
			return res;
		}

		if (null == oldPassword || !MD5.md5(oldPassword, 32).equals(user.getPassword())) {
			res.put("message", "亲，您输入的原密码不正确");
			return res;
		}

		if (MD5.md5(oldPassword, 32).equals(user.getPassword())) {
			user.setPassword(MD5.md5(newPassword, 32));
			tdUserService.save(user);
			res.put("status", 0);
		}

		return res;
	}

	/**
	 * 跳转到收货页面的方法
	 * 
	 * @author dengxiao
	 */
	@RequestMapping(value = "/address")
	public String userAddress(HttpServletRequest req, ModelMap map) {
		String username = (String) req.getSession().getAttribute("username");
		TdUser user = tdUserService.findByUsernameAndIsEnableTrue(username);
		if (null == user) {
			return "redirect:/login";
		}
		List<TdShippingAddress> address_list = user.getShippingAddressList();
		// 遍历集合获取默认收货地址
		if (null != address_list) {
			for (TdShippingAddress shippingAddress : address_list) {
				if (null != shippingAddress && null != shippingAddress.getIsDefaultAddress()
						&& shippingAddress.getIsDefaultAddress()) {
					map.addAttribute("default", shippingAddress);
				}
			}
		}
		map.addAttribute("address_list", address_list);
		return "/client/user_address";
	}

	/**
	 * 跳转到新增收货页面的方法
	 * 
	 * @author dengxiao
	 */
	@RequestMapping(value = "/address/add/{type}")
	public String userAddressAdd(HttpServletRequest req, ModelMap map, @PathVariable Long type, Long id,
			String receiver, String receiverMobile, String detailAddress) {
		// 判断用户是否登陆
		String username = (String) req.getSession().getAttribute("username");
		TdUser user = tdUserService.findByUsernameAndIsEnableTrue(username);
		if (null == user) {
			return "redirect:/login";
		}

		// 创建一系列变量用于存储收货地址信息
		String receiverName = (String) req.getSession().getAttribute("new_addresss_receiverName");
		String mobile = (String) req.getSession().getAttribute("new_addresss_mobile");
		String city = (String) req.getSession().getAttribute("new_address_city");
		String district = (String) req.getSession().getAttribute("new_address_district");
		String subdistrict = (String) req.getSession().getAttribute("new_address_subdistrict");
		String detail = (String) req.getSession().getAttribute("new_address_detail");

		// 如果目前没有填写收件人电话号码，则默认为当前登陆用户的电话号码
		if (null == mobile) {
			mobile = username;
		}

		// 城市永远为用户所在城市，不可更改
		if (null == city) {
			city = user.getCityName();
			req.getSession().setAttribute("new_address_city", city);
		}

		// 如果有填写的收件人姓名，则将其放入session中
		if (null != receiver) {
			req.getSession().setAttribute("new_addresss_receiverName", receiver);
		}

		// 如果有填写的电话号码，则将其放入到session中
		if (null != receiverMobile) {
			req.getSession().setAttribute("new_addresss_mobile", receiverMobile);
		}

		// 如果有填写的详细地址，则将其放入到session中
		if (null != detailAddress) {
			req.getSession().setAttribute("new_address_detail", detailAddress);
		}

		map.addAttribute("receiverName", receiverName);
		map.addAttribute("mobile", mobile);
		map.addAttribute("city", city);
		map.addAttribute("district", district);
		map.addAttribute("subdistrict", subdistrict);
		map.addAttribute("detail", detail);

		// type代表的目前进行的选择：0. 代表基础信息
		if (0 == type) {
			map.addAttribute("type", 1);
			return "/client/add_address_base";
		}
		// type代表的目前进行的选择：1. 代表行政区划选择
		else if (1 == type) {
			map.addAttribute("type", 2);
			List<TdDistrict> region_list = tdDistrictService.findByCityIdOrderBySortIdAsc(user.getCityId());
			map.addAttribute("region_list", region_list);
			return "/client/add_address_detail";
		}
		// type代表的目前进行的选择：1. 代表行政区划选择
		else if (2 == type) {
			map.addAttribute("type", 3);
			// 根据指定的id获取所选择的行政区划
			TdDistrict tdDistrict = tdDistrictService.findOne(id);
			req.getSession().setAttribute("new_address_district", tdDistrict.getName());
			// 根据指定的行政区划，获取其下属所有的行政街道
			List<TdSubdistrict> region_list = tdSubdistrictService.findByDistrictIdOrderBySortIdAsc(id);
			map.addAttribute("region_list", region_list);
			return "/client/add_address_detail";
		}
		// 其他情况只有一种：3. 代表选择完毕，返回添加地址基础页面
		else {
			map.addAttribute("type", 0);
			// 根据指定的id获取所选择的行政街道
			TdSubdistrict tdSubdistrict = tdSubdistrictService.findOne(id);
			req.getSession().setAttribute("new_address_subdistrict", tdSubdistrict.getName());
			map.addAttribute("subdistrict", subdistrict);
			return "/client/add_address_base";
		}
	}

	/**
	 * 保存新增的收货地址的方法
	 * 
	 * @author dengxiao
	 */
	@RequestMapping(value = "/address/add/save")
	@ResponseBody
	public Map<String, Object> userAddressAddSave(HttpServletRequest req, String receiver, String receiverMobile,
			String detailAddress) {
		Map<String, Object> res = new HashMap<>();
		res.put("status", -1);

		// 判断用户是否登陆
		String username = (String) req.getSession().getAttribute("username");
		TdUser user = tdUserService.findByUsernameAndIsEnableTrue(username);
		if (null == user) {
			res.put("status", -2);
			return res;
		}

		String city = (String) req.getSession().getAttribute("new_address_city");
		String district = (String) req.getSession().getAttribute("new_address_district");
		String subdistrict = (String) req.getSession().getAttribute("new_address_subdistrict");

		if (null == receiver) {
			res.put("message", "亲，请添加收货人姓名");
			return res;
		}

		if (null == receiverMobile) {
			res.put("message", "亲，请添加收件人联系电话");
			return res;
		}

		if (null == district) {
			res.put("message", "亲，请选择收件地址的行政区划");
			return res;
		}

		if (null == subdistrict) {
			res.put("message", "亲，请选择收件地址的行政街道");
			return res;
		}

		if (null == detailAddress) {
			res.put("message", "亲，请添加详细地址");
			return res;
		}

		TdCity tdCity = tdCityService.findByCityName(city);
		TdShippingAddress address = new TdShippingAddress();
		address.setProvince(tdCity.getProvince());
		address.setCity(city);
		address.setDisctrict(district);
		address.setSubdistrict(subdistrict);
		address.setDetailAddress(detailAddress);
		address.setReceiverName(receiver);
		address.setReceiverMobile(receiverMobile);

		// 获取登陆用户的收货地址
		List<TdShippingAddress> address_list = user.getShippingAddressList();
		if (null == address_list || 0 == address_list.size()) {
			address_list = new ArrayList<>();
			address.setIsDefaultAddress(true);
		}

		address = tdShippingAddressService.save(address);
		address_list.add(address);
		user.setShippingAddressList(address_list);
		tdUserService.save(user);

		res.put("status", 0);
		return res;
	}

	/**
	 * 操作用户收货地址删除/设为默认的方法
	 * 
	 * @author dengxiao
	 */
	@RequestMapping(value = "/address/operate")
	@ResponseBody
	public Map<String, Object> userAddressOperate(HttpServletRequest req, Long id, Long type) {
		Map<String, Object> res = new HashMap<>();
		res.put("status", -1);

		// 判断用户是否登陆
		String username = (String) req.getSession().getAttribute("username");
		TdUser user = tdUserService.findByUsernameAndIsEnableTrue(username);
		if (null == user) {
			res.put("status", -2);
			return res;
		}

		// type的值为1的时候执行删除操作
		if (1L == type) {
			// 直接进行删除操作
			tdShippingAddressService.delete(id);
		}

		// type的值为2的时候执行设为默认操作
		if (2L == type) {
			// 获取用户所有的收货地址
			List<TdShippingAddress> address_list = user.getShippingAddressList();
			if (null != address_list) {
				for (int i = 0; i < address_list.size(); i++) {
					if (null != address_list.get(i) && null != address_list.get(i).getIsDefaultAddress()
							&& address_list.get(i).getIsDefaultAddress()) {
						address_list.get(i).setIsDefaultAddress(false);
					}
					if (null != address_list.get(i) && null != address_list.get(i).getId()
							&& id == address_list.get(i).getId()) {
						address_list.get(i).setIsDefaultAddress(true);
					}
				}
			}
			user.setShippingAddressList(address_list);
			tdUserService.save(user);
		}

		res.put("status", 0);
		return res;
	}

	/**
	 * 跳转到用户门店归属的页面
	 * 
	 * @author dengxiao
	 */
	@RequestMapping(value = "/diysite")
	public String userDiysite(HttpServletRequest req, ModelMap map) {
		String username = (String) req.getSession().getAttribute("username");
		TdUser user = tdUserService.findByUsernameAndIsEnableTrue(username);
		if (null == user) {
			return "redirect:/login";
		}
		map.addAttribute("user", user);

		// 获取用户所在城市的所有行政区划
		List<TdDistrict> district_list = tdDistrictService.findByCityIdOrderBySortIdAsc(user.getCityId());
		// 遍历集合，获取第一项行政区划下的所有行政街道
		if (null != district_list) {
			for (int i = 0; i < district_list.size(); i++) {
				if (0 == i) {
					TdDistrict district = district_list.get(i);
					// 获取指定行政区划下的所有门店
					if (null != district) {
						List<TdDiySite> all_site = tdDiySiteService.findByDisctrictIdOrderBySortIdAsc(district.getId());
						map.addAttribute("all_site", all_site);
					}
				}
			}
		}
		map.addAttribute("district_list", district_list);
		return "/client/user_diy_site";
	}

	/**
	 * 根据选择的行政区划获取其下属的所有门店信息
	 * 
	 * @author dengxiao
	 */
	@RequestMapping(value = "/diysite/get")
	public String getDiySite(ModelMap map, Long districtId) {
		// 根据行政区划的id获取指定行政区划下的所有门店
		List<TdDiySite> all_site = tdDiySiteService.findByDisctrictIdOrderBySortIdAsc(districtId);
		map.addAttribute("all_site", all_site);
		return "/client/site_in_district";
	}

	/**
	 * 用户选择门店的方法
	 * 
	 * @author dengxiao
	 */
	@RequestMapping(value = "/select/diysite")
	@ResponseBody
	public Map<String, Object> selectDiySite(HttpServletRequest req, Long id) {
		Map<String, Object> res = new HashMap<>();
		res.put("status", -1);

		// 判断用户是否登陆
		String username = (String) req.getSession().getAttribute("username");
		TdUser user = tdUserService.findByUsernameAndIsEnableTrue(username);
		if (null == user) {
			res.put("status", -2);
			return res;
		}

		// 获取指定id的门店信息
		TdDiySite diySite = tdDiySiteService.findOne(id);
		// 更改用户的归属门店
		user.setUpperDiySiteId(diySite.getId());
		user.setDiyName(diySite.getTitle());
		tdUserService.save(user);

		res.put("status", 0);
		return res;
	}

	/**
	 * 跳转到我的财富的方法
	 * 
	 * @author dengxiao
	 */
	@RequestMapping(value = "/fortune")
	public String userFortune(HttpServletRequest req, ModelMap map) {
		String username = (String) req.getSession().getAttribute("username");
		TdUser user = tdUserService.findByUsernameAndIsEnableTrue(username);
		if (null == user) {
			return "redirect:/login";
		}

		// 此处应该获取用户所有的优惠券（包括产品券和现金券）

		map.addAttribute("user", user);

		return "/client/user_fortune";
	}

	/**
	 * 跳转到我的钱包的方法
	 * 
	 * @author dengxiao
	 */
	@RequestMapping(value = "/wallet")
	public String userWallet(HttpServletRequest req, ModelMap map) {
		String username = (String) req.getSession().getAttribute("username");
		TdUser user = tdUserService.findByUsernameAndIsEnableTrue(username);
		if (null == user) {
			return "redirect:/login";
		}

		// 查询到登陆用户所有的钱包操作记录
		Page<TdBalanceLog> balance_log_page = tdBalanceLogService
				.findByUserIdAndIsSuccessTrueOrderByCreateTimeDesc(user.getId(), 0, ClientConstant.MAXRECENTNUM);
		map.addAttribute("balance_log_page", balance_log_page);
		map.addAttribute("user", user);
		return "/client/user_wallet";
	}

	/**
	 * 跳转到充值页面的方法
	 * 
	 * @author dengxiao
	 */
	@RequestMapping(value = "/recharge")
	public String userRecharge(HttpServletRequest req){
		String username = (String) req.getSession().getAttribute("username");
		TdUser user = tdUserService.findByUsernameAndIsEnableTrue(username);
		if(null == user){
			return "redirect:/login";
		}
		return "/client/user_recharge";
	}
}
