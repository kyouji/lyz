package com.ynyes.lyz.controller.front;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ynyes.lyz.entity.TdRegion;
import com.ynyes.lyz.entity.TdUser;
import com.ynyes.lyz.service.TdRegionService;
import com.ynyes.lyz.service.TdUserService;

@Controller
@RequestMapping(value = "/regist")
public class TdRegistController {

	@Autowired
	private TdRegionService tdRegionService;

	@Autowired
	private TdUserService tdUserService;

	@RequestMapping
	public String regist(HttpServletRequest req, ModelMap map) {
		List<TdRegion> regions = tdRegionService.findAll();
		map.addAttribute("regions", regions);
		return "/client/regist";
	}

	@RequestMapping(value = "/refer/check")
	@ResponseBody
	public Map<String, Object> referCheck(HttpServletRequest req, String referPhone, String cityInfo) {
		Map<String, Object> res = new HashMap<>();
		res.put("status", -1);
		TdUser user = tdUserService.findByUsernameAndCityNameAndIsEnableTrue(referPhone, cityInfo);
		if (null == user) {
			return res;
		}
		res.put("message", user.getDiyName());
		res.put("status", 0);
		return res;
	}

	@RequestMapping(value = "/save")
	@ResponseBody
	public Map<String, Object> save(HttpServletRequest req, String cityInfo, String phone, String code, String password,
			String repassword, String referPhone, String diySiteName) {
		Map<String, Object> res = new HashMap<>();
		res.put("status", -1);
		String smsCode = (String) req.getSession().getAttribute("smsCode");
		TdUser user = tdUserService.findByUsername(phone);
		if (null != user) {
			res.put("message", "该手机号码已注册！");
			return res;
		}
		// if (null == smsCode) {
		// res.put("message", "验证码错误！");
		// return res;
		// }
		// if (!smsCode.equals(code)) {
		// res.put("message", "验证码错误！");
		// return res;
		// }
		if (!repassword.equals(password)) {
			res.put("message", "两次输入的密码不一致！");
			return res;
		}

		TdUser new_user = new TdUser();
		new_user.setUsername(phone);
		new_user.setPassword(password);
		new_user.setReferPhone(referPhone);
		new_user.setBalance(0.00);
		new_user.setNickname(phone);
		new_user.setRegisterTime(new Date());
		new_user.setAllPayed(0.00);
		new_user.setUserType(0L);
		new_user.setCityName(cityInfo);
		new_user.setFirstOrder(true);
		new_user.setIsOld(false);
		new_user.setLastLoginTime(new Date());
		new_user.setDiyName(cityInfo + "默认门店");

		TdUser refer_user = tdUserService.findByUsernameAndCityNameAndIsEnableTrue(referPhone, cityInfo);
		if (null != refer_user) {
			new_user.setUpperDiySiteId(refer_user.getUpperDiySiteId());
			new_user.setDiyName(refer_user.getDiyName());
			new_user.setRegionId(refer_user.getRegionId());
		}

		tdUserService.save(new_user);
		req.getSession().setAttribute("username", phone);

		res.put("status", 0);
		return res;
	}
}
