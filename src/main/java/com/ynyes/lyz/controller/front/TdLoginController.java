package com.ynyes.lyz.controller.front;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ynyes.lyz.entity.TdUser;
import com.ynyes.lyz.service.TdUserService;

@Controller
@RequestMapping(value = "/")
public class TdLoginController {

	@Autowired
	private TdUserService tdUserService;
	
	@RequestMapping
	public String index(){
		return "/client/login";
	};
	
	@RequestMapping(value = "/login/check")
	@ResponseBody
	public Map<String, Object> loginCheck(HttpServletRequest req,String username,String password){
		Map<String, Object> res = new HashMap<>();
		res.put("status", -1);
		TdUser user = tdUserService.findByUsernameAndPasswordAndIsEnableTrue(username, password);
		if(null!=user){
			res.put("status", 0);
			user.setLastLoginTime(new Date());
			req.getSession().setAttribute("username", username);
		}
		
		if(null == user){
			TdUser user_by_username_is_enable = tdUserService.findByUsernameAndIsEnableTrue(username);
			if(null == user_by_username_is_enable){
				TdUser user_by_username = tdUserService.findByUsername(username);
				if(null == user_by_username){
					res.put("message","该手机号码未注册！");
				}else{
					res.put("message", "该账号已被冻结！");
				}
			}else{
				res.put("message", "您输入的密码有误！");
			}
		}
		return res;
	}
	
}