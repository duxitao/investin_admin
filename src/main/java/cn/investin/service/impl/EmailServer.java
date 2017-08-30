package cn.investin.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Flags;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Part;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sun.mail.imap.IMAPFolder;
import com.sun.mail.imap.IMAPStore;

import cn.investin.model.EmailLog;
import cn.investin.util.AppConfig;

@Service
public class EmailServer {

	@Autowired
	AppConfig appConfig;

	private Logger logger = Logger.getLogger(this.getClass());

	public boolean send(EmailLog emailLog) {
		try {
			// 配置发送邮件的环境属性
			final Properties props = appConfig.getProperties();

			// 构建授权信息，用于进行SMTP进行身份验证
			Authenticator authenticator = new Authenticator() {
				@Override
				protected PasswordAuthentication getPasswordAuthentication() {
					// 用户名、密码
					String userName = props.getProperty("mail.user");
					String password = props.getProperty("mail.password");
					return new PasswordAuthentication(userName, password);
				}
			};
			// 使用环境属性和授权信息，创建邮件会话
			Session mailSession = Session.getInstance(props, authenticator);
			// 创建邮件消息
			MimeMessage message = new MimeMessage(mailSession);
			// 设置发件人
			//InternetAddress form = new InternetAddress(props.getProperty("mail.user"));
			//message.setFrom(form);
			
			message.setFrom(new InternetAddress("loginfund"+" <"+props.getProperty("mail.user")+">"));
			

			// 设置收件人
			InternetAddress to = new InternetAddress(emailLog.getTo_address());
			message.setRecipient(RecipientType.TO, to);

			// 设置邮件标题
			message.setSubject(emailLog.getSubject());

			// 设置邮件的内容体
			message.setContent(emailLog.getContent(), "text/html;charset=UTF-8");

			// 发送邮件
			Transport.send(message);
			return true;
		} catch (Exception e) {
			logger.error(String.format("send inquiry info to %s error %s", emailLog.getTo_address(), e.getMessage()));
			logger.error(e);
			return false;
		}
	}

	public List<EmailLog> getEmail() throws MessagingException, IOException {
		logger.info("*********************************************************************************");
		List<EmailLog> list = new ArrayList<EmailLog>();
		logger.info("read unreadMessage start……");
		Properties props = appConfig.getProperties();
		Session session = Session.getInstance(props);

		IMAPStore store = (IMAPStore) session.getStore("imap"); // 使用imap会话机制，连接服务器
		store.connect(props.getProperty("mail.user"), props.getProperty("mail.password"));
		IMAPFolder folder = (IMAPFolder) store.getFolder("INBOX"); // 收件箱
		folder.open(Folder.READ_WRITE);

		logger.info("unreadMessageCount：" + folder.getUnreadMessageCount());
		Message[] messages = folder.getMessages(folder.getMessageCount() - folder.getUnreadMessageCount() + 1,
				folder.getMessageCount());

		for (Message message : messages) {
			EmailLog emailLog = new EmailLog();
			// 获取发件人
			String from = "";
			Address[] froms = message.getFrom();
			if (froms.length >= 1) {
				InternetAddress address = (InternetAddress) froms[0];
				from = address.getAddress();
			}

			// 获取邮件内容
			StringBuffer textContent = new StringBuffer();
			StringBuffer htmlContent = new StringBuffer();
			getMailTextContent(message, textContent);
			getMailHtmlContent(message, htmlContent);

			// 解析收件人
			String to = "";
			String reg = "\\(\\w+@\\w+(\\.\\w+)+\\)";
			Pattern p = Pattern.compile(reg);
			Matcher m = p.matcher(textContent);
			while (m.find()) {
				to = m.group();
				to = to.substring(1, to.length() - 1);// 去掉括号
			}
			emailLog.setSender(appConfig.getProperties().getProperty("mail.user"));
			emailLog.setFrom_address(from);
			emailLog.setTo_address(to);
			emailLog.setSubject(message.getSubject());
			emailLog.setContent(htmlContent.toString());
			emailLog.setType("1");
			message.setFlag(Flags.Flag.SEEN, true);
			list.add(emailLog);
			logger.info(emailLog);
		}
		// 释放资源
		if (folder != null)
			folder.close(true);
		if (store != null)
			store.close();
		logger.info("*********************************************************************************");
		return list;
	}

	public static void getMailTextContent(Part part, StringBuffer content) throws MessagingException, IOException {
		// 如果是文本类型的附件，通过getContent方法可以取到文本内容，但这不是我们需要的结果，所以在这里要做判断
		boolean isContainTextAttach = part.getContentType().indexOf("name") > 0;
		if (part.isMimeType("text/*") && !isContainTextAttach) {
			content.append(part.getContent().toString());
		} else if (part.isMimeType("message/rfc822")) {
			getMailTextContent((Part) part.getContent(), content);
		} else if (part.isMimeType("multipart/*")) {
			Multipart multipart = (Multipart) part.getContent();
			//int partCount = multipart.getCount();
			// i设为1只取html格式，要取文本格式的内容则i从0开始取
			for (int i = 0; i < 1; i++) {
				BodyPart bodyPart = multipart.getBodyPart(i);
				getMailTextContent(bodyPart, content);
			}
		}
	}

	public static void getMailHtmlContent(Part part, StringBuffer content) throws MessagingException, IOException {
		// 如果是文本类型的附件，通过getContent方法可以取到文本内容，但这不是我们需要的结果，所以在这里要做判断
		boolean isContainTextAttach = part.getContentType().indexOf("name") > 0;
		if (part.isMimeType("text/*") && !isContainTextAttach) {
			content.append(part.getContent().toString());
		} else if (part.isMimeType("message/rfc822")) {
			getMailTextContent((Part) part.getContent(), content);
		} else if (part.isMimeType("multipart/*")) {
			Multipart multipart = (Multipart) part.getContent();
			int partCount = multipart.getCount();
			// i设为1只取html格式，要取文本格式的内容则i从0开始取
			for (int i = 1; i < partCount; i++) {
				BodyPart bodyPart = multipart.getBodyPart(i);
				getMailTextContent(bodyPart, content);
			}
		}
	}

}
