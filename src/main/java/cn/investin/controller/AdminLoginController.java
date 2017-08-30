package cn.investin.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.investin.model.AdminUser;
import cn.investin.model.BaseData;
import cn.investin.model.Country;
import cn.investin.service.AdminUserService;
import cn.investin.util.pageUtil;

@Controller
public class AdminLoginController {
	private Logger logger = Logger.getLogger(AdminLoginController.class);
	@Autowired
	AdminUserService service;

	@RequestMapping("login")
	public String getUserList(Model model) {
		List<AdminUser> userList = service.getAllUser();
		model.addAttribute("userList", userList);
		return "login";
	}

	@RequestMapping("exit")
	public String exit(Model model, HttpSession session) {
		session.setAttribute("user", null);
		List<AdminUser> userList = service.getAllUser();
		model.addAttribute("userList", userList);
		return "login";
	}

	@RequestMapping("addUser")
	public String addUser(Model model) {
		service.addUser();
		List<AdminUser> userList = service.getAllUser();
		model.addAttribute("userList", userList);
		return "password";
	}

	@RequestMapping(value = "updatePwd", method = { RequestMethod.POST })
	@ResponseBody
	public String updatePwd(Model model, HttpSession session, String old_pwd, String new_pwd) {
		try {
			AdminUser user = (AdminUser) session.getAttribute("user");
			if (null == user)
				return "001";// 未登录
			user.setPwd(old_pwd);
			if (service.login(user)) {
				user.setPwd(new_pwd);
				service.updatePwd(user);
				return "000";
			} else
				return "002";// 旧密码不正确

		} catch (Exception e) {
			logger.info("updatePwd error……" + e.getMessage());
			return "002";
		}
	}

	@RequestMapping(value = "loginSubmit", method = { RequestMethod.POST })
	@ResponseBody
	public String login(AdminUser user, HttpSession session) {
		logger.info("loginSubmit……" + user);
		try {
			if (service.login(user)) {
				session.setAttribute("user", user);
				return "000";
			} else
				return "001";
		} catch (Exception e) {
			logger.info("loginSubmit error……" + e.getMessage());
			return "002";
		}

	}

	@RequestMapping("getBaseData")
	public String getBaseData(Model model, HttpServletRequest request) {
		try {
			int totalRecord = service.getCountForBaseData();
			int pageNum = pageUtil.AddPagE(model, request, totalRecord);

			List<BaseData> baseDataList = service.getBaseData(pageNum);
			model.addAttribute("baseDataList", baseDataList);
			return "baseData";
		} catch (Exception e) {
			logger.info("登录异常……" + e.getMessage());
			return "exception";
		}
	}

	@RequestMapping("addOrEditBaseData")
	public String addBaseData(Model model, String id) {
		model.addAttribute("id", id);
		if (!"-1".equals(id))
			model.addAttribute("baseData", service.getBaseDataById(id));
		return "addOrEditBaseData";
	}

	@RequestMapping(value = "submitAddOrEditBaseData", method = { RequestMethod.POST })
	@ResponseBody
	public String submitAddOrEditBaseData(Model model, BaseData baseData) {
		try {
			if (baseData.getId() == -1)
				service.addBaseData(baseData);
			else
				service.updateBaseData(baseData);
			return "000";
		} catch (Exception e) {
			logger.info("submitAddOrEditBaseData error:" + e.getMessage());
			return "001";
		}

	}

	@RequestMapping("updateSetting")
	@ResponseBody
	public String updateSendGroupNum(Model model, String key, String value) {
		logger.info("enter updateSetting……");
		try {
			service.updateSetting(key, value);
			return "000";
		} catch (Exception e) {
			logger.error("enter updateSetting error:" + e);
			return "001";
		}
	}

	@RequestMapping("password")
	public String password(Model model, HttpSession session) {
		AdminUser user = (AdminUser) session.getAttribute("user");
		if (null == user)
			return getUserList(model);// 转到登录页面
		session.setAttribute("user", user);// 重新放入session，防止登录过期
		List<AdminUser> userList = service.getAllUser();
		model.addAttribute("userList", userList);

		model.addAttribute("user", user);
		return "password";
	}


}
