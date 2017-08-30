package cn.investin.controller;

import java.util.HashMap;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import cn.investin.model.Account;
import cn.investin.model.EmailLog;
import cn.investin.model.Inquiry;
import cn.investin.model.InvitingInfo;
import cn.investin.service.AdminUserService;
import cn.investin.service.InvitingInfoService;
import cn.investin.service.impl.EmailServer;
import cn.investin.util.AppConfig;

@Component("taskJob")
public class ScheduledController {

	@Autowired
	AppConfig appConfig;

	@Autowired
	InvitingInfoService service;

	@Autowired
	AdminUserService adminService;
	@Autowired
	EmailServer emailServer;

	private Logger logger = Logger.getLogger(AdminLoginController.class);

	/**
	 * 发送邮件任务
	 */
	@Scheduled(cron = "0 0/1 * * * ?")
	public void sendEmail() {
		// 查询群发设置
		int groupNum = 0;

		// 查询询问内容
		List<Inquiry> inquiryList = service.getInquiryByStatus("3");

		for (Inquiry InquiryInfo : inquiryList) {
			List<HashMap<String, String>> list = adminService.getSetting();
			// {value=2, key=email_group_num, desc=邮件群发数设置}
			for (HashMap<String, String> map : list) {
				if ("email_group_num".equals(map.get("_key"))) {
					groupNum = Integer.parseInt(map.get("_value"));
					break;
				}
			}
			logger.info(String.format("开始执行邮件任务，询问编码：%s，是否群发：%s（1-群发；0-不群发），群发数量：%s（0为全部发）", InquiryInfo.getId(),
					InquiryInfo.getGroup_send(), groupNum));

			// 查询邮件发送对象
			if ("1".equals(InquiryInfo.getGroup_send())) {
				List<InvitingInfo> ListInfo = null;
				// 查询所有领域
				if ("ALL".equals(appConfig.getProperties().getProperty("system.field")))
					ListInfo = service.getInvitingInfoByfieldId("-1");
				else
					ListInfo = service.getInvitingInfoByfieldId(InquiryInfo.getField_id());
				if (groupNum == 0) {
					// 群发所有
					for (InvitingInfo info : ListInfo) {
						try {
							Account account = service.getAccountByEmail(info.getEmail());
							EmailLog emailLog = createEmailLog(InquiryInfo, info, account);
							if (!"1".equals(info.getIs_test())) {
								emailServer.send(emailLog);
								service.addSendemaiLog(emailLog);
							}
							Thread.sleep(60*1000);
						} catch (Exception e) {
							logger.error("send email error:" + e);
						}
					}
				} else {
					// 根据群发设置数量查询要发送的对象
					int count = 0;
					for (InvitingInfo info : ListInfo) {
						try {
							// 被询问的信息条不包括在内
							if (InquiryInfo.getInfo_id() != info.getId()) {
								Account account = service.getAccountByEmail(info.getEmail());
								EmailLog emailLog = createEmailLog(InquiryInfo, info, account);
								if (!"1".equals(info.getIs_test())) {
									emailServer.send(emailLog);
									service.addSendemaiLog(emailLog);
									count++;
								}
							}
							Thread.sleep(60*1000);
							if (count >= groupNum)
								break;
						} catch (Exception e) {
							logger.error("send email error:" + e);
						}
					}
					// 被询问的信息条单独发送
					InvitingInfo info = service.getInvitingInfoById(InquiryInfo.getInfo_id() + "");
					Account account = service.getAccountByEmail(info.getEmail());
					EmailLog emailLog = createEmailLog(InquiryInfo, info, account);
					if (!"1".equals(info.getIs_test())) {
						emailServer.send(emailLog);
						try {
							service.addSendemaiLog(emailLog);
						} catch (Exception e) {
							logger.error("addSendemaiLog error:" + e);
						}
					}
				}

			} else {
				InvitingInfo info = service.getInvitingInfoById(InquiryInfo.getInfo_id() + "");
				Account account = service.getAccountByEmail(info.getEmail());
				EmailLog emailLog = createEmailLog(InquiryInfo, info, account);
				if (!"1".equals(info.getIs_test())) {
					emailServer.send(emailLog);
					try {
						service.addSendemaiLog(emailLog);
					} catch (Exception e) {
						logger.error("addSendemaiLog error:" + e);
					}
				}
			}
			// 更新询问信息的status状态为已发邮件
			InquiryInfo.setStatus("4");
			service.updateInquiry(InquiryInfo);
		}
	}

	private EmailLog createEmailLog(Inquiry InquiryInfo, InvitingInfo info, Account account) {
		EmailLog emailLog = new EmailLog();
		if ("1".equals(account.getPay_status()) || "2".equals(account.getPay_status())) {

			emailLog.setInquiry_id(InquiryInfo.getId());
			emailLog.setSender(appConfig.getProperties().getProperty("mail.user"));
			emailLog.setFrom_address(InquiryInfo.getInquiry_email());
			emailLog.setTo_address(info.getEmail());
			emailLog.setSubject("You Received an Investor's Message");
			StringBuffer Sb = new StringBuffer();
			Sb.append("<p style='text-indent: 1em;'><strong>Dear " + account.getUserName() + ",</strong></p>");
			Sb.append(
					"<p style='text-indent: 1em;'>loginfund.com inform you that you have just received a new message from the investor<span>("
							+ InquiryInfo.getInquiry_email() + ")</span>as below:</p>");

			if (!"".equals(InquiryInfo.getRequires1_en().trim()) || !"".equals(InquiryInfo.getRequires2_en().trim())
					|| !"".equals(InquiryInfo.getRequires3_en().trim()))
				Sb.append("<p style='text-indent: 3em;'><strong>The investor requires:</strong></p>");

			if (InquiryInfo.getRequires1_en() != null && !"".equals(InquiryInfo.getRequires1_en()))
				Sb.append("<p style='text-indent: 5em;'>" + InquiryInfo.getRequires1_en() + "</p>");
			if (InquiryInfo.getRequires2_en() != null && !"".equals(InquiryInfo.getRequires2_en()))
				Sb.append("<p style='text-indent: 5em;'>" + InquiryInfo.getRequires2_en() + "</p>");
			if (InquiryInfo.getRequires3_en() != null && !"".equals(InquiryInfo.getRequires3_en()))
				Sb.append("<p style='text-indent: 5em;'>" + InquiryInfo.getRequires3_en() + "</p>");

			Sb.append("<p style='text-indent: 3em;'><strong>The investor leaves you a note:</strong></p>");
			Sb.append("<p style='text-indent: 5em;'>" + InquiryInfo.getContent_en() + "</p><br>");
			Sb.append(
					"<p style='text-indent: 3em;color: rgb(255, 102, 0);'><strong>You can directly contact the investor by his email address!</strong></p>");
			emailLog.setContent(Sb.toString());
			emailLog.setType("0");
		} else {
			emailLog.setInquiry_id(InquiryInfo.getId());
			emailLog.setSender(appConfig.getProperties().getProperty("mail.user"));
			emailLog.setFrom_address(InquiryInfo.getInquiry_email());
			emailLog.setTo_address(info.getEmail());
			emailLog.setSubject("You Received an Investor’s Message");

			// 隐藏邮箱地址
			String email = InquiryInfo.getInquiry_email();

			email = "******" + email.substring(email.indexOf('@'), email.length());

			StringBuffer Sb = new StringBuffer();
			Sb.append("<p style='text-indent: 1em;'><strong>Dear " + account.getUserName() + ",</strong></p>");
			Sb.append(
					"<p style='text-indent: 1em;'>loginfund.com inform you that you have just received a new message from the investor<span>("
							+ email + ")</span>as below:</p>");
			if (!"".equals(InquiryInfo.getRequires1_en().trim()) || !"".equals(InquiryInfo.getRequires2_en().trim())
					|| !"".equals(InquiryInfo.getRequires3_en().trim()))
				Sb.append("<p style='text-indent: 3em;'><strong>The investor requires:</strong></p>");
			if (InquiryInfo.getRequires1_en() != null && !"".equals(InquiryInfo.getRequires1_en()))
				Sb.append("<p style='text-indent: 5em;'>" + InquiryInfo.getRequires1_en() + "</p>");
			if (InquiryInfo.getRequires2_en() != null && !"".equals(InquiryInfo.getRequires2_en()))
				Sb.append("<p style='text-indent: 5em;'>" + InquiryInfo.getRequires2_en() + "</p>");
			if (InquiryInfo.getRequires3_en() != null && !"".equals(InquiryInfo.getRequires3_en()))
				Sb.append("<p style='text-indent: 5em;'>" + InquiryInfo.getRequires3_en() + "</p>");
			Sb.append("<p style='text-indent: 3em;'><strong>The investor leaves you a note:</strong></p>");
			Sb.append("<p style='text-indent: 5em;'>" + InquiryInfo.getContent_en() + "</p><br>");
			Sb.append(
					"<p style='text-indent: 3em;color:blue;'>For accessing the investor’s email,please log in at www.loginfund.com to pay for a formal membership.</p>");
			emailLog.setContent(Sb.toString());
			emailLog.setType("0");
		}
		return emailLog;
	}

	/**
	 * 转发邮件
	 */
	@Scheduled(cron = "0/30 * * * * ? ")
	public void relayEmail() {
		try {
			List<EmailLog> list = emailServer.getEmail();

			for (EmailLog emailLog : list) {
				emailServer.send(emailLog);
				service.addSendemaiLog(emailLog);
			}

		} catch (Exception e) {
			logger.error("relayEmail error:" + e);
			e.printStackTrace();
		}
	}

	@Scheduled(cron = "0 0/1 * * * ?")
	public void infoSort() {
		logger.error("infoSort……");
	}

	public static void main(String agrs[]) {
		String email = "duxitao@126.com";
		email = "******" + email.substring(email.indexOf('@'), email.length());
		System.out.println(email);
	}
}
