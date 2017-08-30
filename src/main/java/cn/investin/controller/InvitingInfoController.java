package cn.investin.controller;

import java.util.HashMap;
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

import cn.investin.model.Account;
import cn.investin.model.AdminUser;
import cn.investin.model.ContactInfo;
import cn.investin.model.Country;
import cn.investin.model.Inquiry;
import cn.investin.model.InquiryAccount;
import cn.investin.model.InvitingInfo;
import cn.investin.model.ReciveEmail;
import cn.investin.service.AdminUserService;
import cn.investin.service.InvitingInfoService;
import cn.investin.service.impl.EmailServer;
import cn.investin.util.pageUtil;

/**
 * 
 * @author duxitao@126.com
 *
 */
@Controller
public class InvitingInfoController {
	private Logger logger = Logger.getLogger(AdminLoginController.class);
	@Autowired
	InvitingInfoService service;
	@Autowired
	EmailServer emailServer;

	@Autowired
	AdminUserService adminService;

	/**
	 * 已上传信息
	 * 
	 * @return
	 */
	@RequestMapping("uploaded")
	public String uploaded(Model model, HttpServletRequest request, String inquiryStatus, String querytext) {
		logger.info("enter uploaded……");
		try {
			logger.debug(String.format("inquiryStatus=%s,querytext=%s", inquiryStatus, querytext));
			// 查询总记录数
			int totalRecord = service.getCountForInvitingInfo("1", inquiryStatus, null, querytext);
			int pageNum = pageUtil.AddPagE(model, request, totalRecord);
			// 查询数据
			List<InvitingInfo> list = service.getInvitingInfo("1", inquiryStatus, null, querytext, pageNum);
			model.addAttribute("list", list);
			if (inquiryStatus != null)
				model.addAttribute("inquiryStatus", inquiryStatus);
			if (querytext != null)
				model.addAttribute("querytext", querytext);

			return "uploaded";
		} catch (Exception e) {
			logger.error("enter uploaded error:" + e);
			return "exception";
		}

	}

	/**
	 * 未上传信息
	 * 
	 * @param model
	 * @param request
	 * @param payStatus
	 * @param querytext
	 * @return
	 */
	@RequestMapping("notupload")
	public String notuploaded(Model model, HttpServletRequest request, String payStatus, String querytext) {
		logger.info("enter notupload……");
		try {
			logger.debug(String.format("payStatus=%s,querytext=%s", payStatus, querytext));
			// 查询总记录数
			int totalRecord = service.getCountForInvitingInfo("0", null, payStatus, querytext);
			int pageNum = pageUtil.AddPagE(model, request, totalRecord);
			// 查询数据
			List<InvitingInfo> list = service.getInvitingInfo("0", null, payStatus, querytext, pageNum);
			model.addAttribute("list", list);
			if (payStatus != null)
				model.addAttribute("payStatus", payStatus);
			if (querytext != null)
				model.addAttribute("querytext", querytext);

			return "notupload";
		} catch (Exception e) {
			logger.error("enter notupload error:" + e);
			return "exception";
		}
	}

	@RequestMapping("translate")
	public String translate(Model model, HttpServletRequest request, InvitingInfo info) {
		String id = request.getParameter("id");
		try {
			logger.info("enter translate,id=" + id);
			model.addAttribute("info", service.getInvitingInfoById(id));
			return "translate";
		} catch (Exception e) {
			logger.error("enter translate error,id=" + id + ":" + e);
			return "exception";
		}
	}

	@RequestMapping("inquiryView")
	public String inquiryView(Model model, HttpServletRequest request) {
		String id = request.getParameter("id");
		try {
			logger.info("enter inquiryView,id=" + id);
			model.addAttribute("info", service.viewInquiry(id));
			return "inquiryView";
		} catch (Exception e) {
			logger.error("enter inquiryView error,id=" + id + ":" + e);
			return "exception";
		}
	}

	@RequestMapping("inquiryTranslate")
	public String inquiryTranslate(Model model, HttpServletRequest request) {
		String id = request.getParameter("id");
		try {

			String email_group_num = "";
			List<HashMap<String, String>> list = adminService.getSetting();
			for (HashMap<String, String> map : list) {
				if ("email_group_num".equals(map.get("_key"))) {
					email_group_num = map.get("_value");
				}
			}
			if ("0".equals(email_group_num))
				email_group_num = "全部";
			model.addAttribute("email_group_num", email_group_num);
			logger.info("enter inquiryTranslate,id=" + id);
			model.addAttribute("info", service.getInquiryById(id));
			return "inquiryTranslate";
		} catch (Exception e) {
			logger.error("enter inquiryTranslate error,id=" + id + ":" + e);
			return "exception";
		}
	}

	@RequestMapping(value = "translateSubmit", method = { RequestMethod.POST })
	@ResponseBody
	public String translateSubmit(Model model, HttpServletRequest request, InvitingInfo info) {
		String id = request.getParameter("id");
		String status = request.getParameter("status");
		AdminUser user = (AdminUser) request.getSession().getAttribute("user");
		// 登录超时，请重新登录
		if (null == user)
			return "001";
		// 参数不合法
		if ("".equals(info.getTitle_cn().trim()) || "".equals(info.getDescription_cn().trim()))
			return "002";
		try {
			logger.info("enter translate,userId=" + user.getId());
			if ("0".equals(status) || "3".equals(status))
				info.setStatus("1");
			if ("1".equals(status))
				info.setStatus("2");
			if ("2".equals(status))
				info.setStatus("3");

			info.setUpdateBy(user.getId());
			service.updateInvitingInfo(info);
			return "000";
		} catch (Exception e) {
			logger.error("enter notupload error,id=" + id + ":" + e);
			return "003";
		}
	}

	/**
	 * 询问审核
	 * 
	 * @return
	 */
	@RequestMapping("inquiry")
	public String inquiry(Model model, HttpServletRequest request) {
		logger.info("enter inquiry……");
		try {
			int totalRecord = service.getCountForInquiry();
			int pageNum = pageUtil.AddPagE(model, request, totalRecord);
			// 查询数据
			List<Inquiry> list = service.getInquiry(pageNum);
			model.addAttribute("list", list);
			return "inquiry";
		} catch (Exception e) {
			logger.error("enter inquiry error:" + e);
			return "exception";
		}
	}

	@RequestMapping("inquiry_large")
	public String inquiry_large(Model model, HttpServletRequest request) {
		logger.info("enter inquiry_large……");
		try {
			int totalRecord = service.getCountForlargeInquiry();
			int pageNum = pageUtil.AddPagE(model, request, totalRecord);
			// 查询数据
			List<Inquiry> list = service.getLargeInquiry(pageNum);
			model.addAttribute("list", list);
			return "inquiry_large";
		} catch (Exception e) {
			logger.error("enter inquiry_large error:" + e);
			return "exception";
		}
	}

	@RequestMapping(value = "inquiryTranslateSubmit", method = { RequestMethod.POST })
	@ResponseBody
	public String inquiryTranslateSubmit(Model model, HttpServletRequest request, Inquiry info) {
		String status = request.getParameter("status");
		/*
		 * String id = request.getParameter("id"); String status =
		 * request.getParameter("status"); AdminUser user = (AdminUser)
		 * request.getSession().getAttribute("user"); // 登录超时，请重新登录 if (null ==
		 * user) return "001";
		 */
		// 参数不合法
		if ("".equals(info.getContent_en()))
			return "002";
		try {
			// logger.info("enter inquiryTranslateSubmit,userId=" +
			// user.getId());
			if ("0".equals(status) || "3".equals(status))
				info.setStatus("1");
			if ("1".equals(status))
				info.setStatus("2");
			if ("2".equals(status))
				info.setStatus("3");
			service.updateInquiry(info);
			return "000";
		} catch (Exception e) {
			logger.error("enter inquiryTranslateSubmit error：" + e);
			return "003";
		}
	}

	/**
	 * 查询询问用户
	 * 
	 * @return
	 */
	@RequestMapping("inquiryAccount")
	public String inquiryAccount(Model model, HttpServletRequest request, String emailtext, String tel) {
		logger.info("enter inquiryAccount……");
		try {
			int totalRecord = service.getCountForInquiryAccount(emailtext, tel);
			int pageNum = pageUtil.AddPagE(model, request, totalRecord);
			// 查询数据
			List<InquiryAccount> list = service.getInquiryAccount(emailtext, tel, pageNum);
			model.addAttribute("list", list);
			if (emailtext != null)
				model.addAttribute("emailtext", emailtext);
			if (tel != null)
				model.addAttribute("tel", tel);
			return "inquiryAccount";
		} catch (Exception e) {
			logger.error("enter inquiryAccount error:" + e);
			return "exception";
		}

	}

	/**
	 * 国外用户
	 * 
	 * @param model
	 * @param request
	 * @param emailtext
	 * @param tel
	 * @return
	 */
	@RequestMapping("account")
	public String account(Model model, HttpServletRequest request, String userName, String tel, String isPay,
			String countryId, String dateFlag, String month, String year) {
		logger.info("enter account……");
		try {
			int totalRecord = service.getCountForAccount(userName, tel, isPay, countryId, dateFlag, year + month);
			int pageNum = pageUtil.AddPagE(model, request, totalRecord);
			// 查询数据
			List<Account> list = service.getAccount(userName, tel, isPay, countryId, dateFlag, year + month, pageNum);
			List<Country> countryList = service.getAllCountry();

			model.addAttribute("list", list);
			model.addAttribute("countryList", countryList);

			if (userName != null)
				model.addAttribute("userName", userName);
			if (tel != null)
				model.addAttribute("tel", tel);
			if (isPay != null)
				model.addAttribute("isPay", isPay);
			if (countryId != null)
				model.addAttribute("countryId", countryId);
			if (dateFlag != null)
				model.addAttribute("dateFlag", dateFlag);
			if (month != null)
				model.addAttribute("month", month);
			if (year != null)
				model.addAttribute("year", year);
			return "account";
		} catch (Exception e) {
			logger.error("enter account error:" + e);
			return "exception";
		}

	}

	@RequestMapping(value = "updatePayStatus", method = { RequestMethod.POST })
	@ResponseBody
	public String updatePayStatsu(Model model, String id) {
		try {

			service.updatePayStatus(id);
			// 将credit code设为失效，并将对应的信息条
			return "000";
		} catch (Exception e) {
			logger.error("enter updatePayStatsu error：" + e);
			return "001";
		}
	}

	@RequestMapping("requestCall")
	public String requestCall(Model model, HttpServletRequest request, String countryId, String newFlag) {
		logger.info("enter requestCall……");
		try {
			int totalRecord = service.getCountForAccountOfRequest(countryId, newFlag);
			int pageNum = pageUtil.AddPagE(model, request, totalRecord);
			// 查询数据
			List<Account> list = service.getAccountOfRequest(countryId, newFlag, pageNum);
			List<Country> countryList = service.getAllCountry();

			model.addAttribute("list", list);
			model.addAttribute("countryList", countryList);

			if (countryId != null)
				model.addAttribute("countryId", countryId);
			if (newFlag != null)
				model.addAttribute("newFlag", newFlag);

			return "requestCall";
		} catch (Exception e) {
			logger.error("enter requestCall error:" + e);
			return "exception";
		}
	}

	@RequestMapping(value = "updateRequestStatus", method = { RequestMethod.POST })
	@ResponseBody
	public String updateRequestStatus(Model model, String id) {
		try {
			service.updateRequestStatus(id);
			return "000";
		} catch (Exception e) {
			logger.error("enter updateRequestStatus error：" + e);
			return "001";
		}
	}

	@RequestMapping("setting")
	public String setting(Model model) {
		logger.info("enter setting……");
		try {
			String update_frequency = "";
			String email_group_num = "";
			List<HashMap<String, String>> list = adminService.getSetting();
			// {value=2, key=email_group_num, desc=邮件群发数设置}
			for (HashMap<String, String> map : list) {
				if ("email_group_num".equals(map.get("_key"))) {
					email_group_num = map.get("_value");
				}
				if ("update_frequency".equals(map.get("_key"))) {
					update_frequency = map.get("_value");
				}
			}
			model.addAttribute("update_frequency", update_frequency);
			model.addAttribute("email_group_num", email_group_num);

			model.addAttribute("listField", service.getAllField());
			model.addAttribute("listScale", service.getAllScale());
			model.addAttribute("listCountry", service.getAllCountry());
			return "setting";
		} catch (Exception e) {
			logger.error("enter setting error:" + e);
			return "exception";
		}
	}

	@RequestMapping("createTestInfo")
	@ResponseBody
	public String createTestInfo(Model model, String title_en, String description_en, String scaleId, String fieldId,
			String countryId, String is_large) {
		logger.info("enter createTestInfo……");
		try {
			service.createTestInfo(title_en, description_en, scaleId, fieldId, countryId, is_large);
			return "000";
		} catch (Exception e) {
			logger.error("enter createTestInfo error:" + e);
			return "001";
		}
	}

	@RequestMapping("inquiry_all")
	public String inquiry_all(Model model) {
		return "inquiry_all";
	}

	@RequestMapping(value = "delInfoAndAccount", method = { RequestMethod.POST })
	@ResponseBody
	public String delInfoAndAccount(Model model, String email, String infoId) {
		try {
			service.delInfoAndAccount(email, infoId);
			return "000";
		} catch (Exception e) {
			logger.error("delInfoAndAccount error：" + e);
			return "001";
		}
	}

	@RequestMapping("contactInfo")
	public String contactInfo(Model model, HttpServletRequest request, String countryId) {
		logger.info("enter contactInfo……");
		try {
			int totalRecord = service.getCountForContactInfo(countryId);
			int pageNum = pageUtil.AddPagE(model, request, totalRecord);
			// 查询数据
			List<ContactInfo> list = service.getContactInfo(countryId, pageNum);
			List<Country> countryList = service.getAllCountry();

			model.addAttribute("list", list);
			model.addAttribute("countryList", countryList);

			if (countryId != null)
				model.addAttribute("countryId", countryId);
			return "contactInfo";
		} catch (Exception e) {
			logger.error("enter contactInfo error:" + e);
			return "exception";
		}
	}

	@RequestMapping("viewContactInfo")
	public String viewContactInfo(Model model, HttpServletRequest request, String id) {
		logger.info("enter viewContactInfo……");
		try {
			List<ContactInfo> list = service.getContactInfoByIdList(id);
			if (list.size() > 0)
				model.addAttribute("info", list.get(0));
			return "viewContactInfo";
		} catch (Exception e) {
			logger.error("enter viewContactInfo error:" + e);
			return "exception";
		}
	}

	@RequestMapping("exportContactInfo")
	public String exportContactInfo(Model model, HttpServletRequest request, String IdList) {
		logger.info("enter exportContactInfo……");
		try {
			List<ContactInfo> list = service.getContactInfoByIdList(IdList);
			if (list.size() > 0)
				model.addAttribute("list", list);
			return "exportContactInfo";
		} catch (Exception e) {
			logger.error("enter exportContactInfo error:" + e);
			return "exception";
		}
	}

	@RequestMapping("setReceiveEmail")
	public String newAccount(Model model, HttpSession session) {
		logger.info("enter setReceiveEmail……");
		try {
			List<Country> countryList = service.getAllCountry();
			model.addAttribute("countryList", countryList);
			return "setReceiveEmail";
		} catch (Exception e) {
			logger.error("enter setReceiveEmail error:" + e);
			return "exception";
		}
	}

	@RequestMapping("getReceiveEmail")
	@ResponseBody
	public String getReceiveEmail(Model model, String countryId) {
		logger.info("enter getReceiveEmail……");
		try {
			ReciveEmail reciveEmail = service.getReceiveEmail(countryId);
			if (reciveEmail == null) {
				reciveEmail = new ReciveEmail();
				reciveEmail.setStatus("2");
				reciveEmail.setReceive_email("-1");
			}
			return reciveEmail.getStatus() + "|" + reciveEmail.getReceive_email();
		} catch (Exception e) {
			logger.error("enter getReceiveEmail error:" + e);
			return "-1";
		}
	}

	@RequestMapping("addReceiveEmail")
	@ResponseBody
	public String addReceiveEmail(Model model, String countryId, String receive_email, String status) {
		logger.info("enter addReceiveEmail……");
		try {
			service.addReceiveEmail(countryId, receive_email, status);
			return "000";
		} catch (Exception e) {
			logger.error("enter addReceiveEmail error:" + e);
			return "-1";
		}
	}

	@RequestMapping("updateReceiveEmail")
	@ResponseBody
	public String updateReceiveEmail(Model model, String countryId, String receive_email, String status) {
		logger.info("enter updateReceiveEmail……");
		try {
			service.updateReceiveEmail(countryId, receive_email, status);
			return "000";
		} catch (Exception e) {
			logger.error("enter updateReceiveEmail error:" + e);
			return "-1";
		}
	}

	@RequestMapping("updateReceiveEmailStatus")
	@ResponseBody
	public String updateReceiveEmailStatus(Model model, String countryId, String status) {
		logger.info("enter updateReceiveEmailStatus……");
		try {
			service.updateReceiveEmailStatus(countryId, status);
			return "000";
		} catch (Exception e) {
			logger.error("enter updateReceiveEmailStatus error:" + e);
			return "-1";
		}
	}

}
