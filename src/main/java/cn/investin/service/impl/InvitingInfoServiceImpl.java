package cn.investin.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.investin.dao.InvitingInfoDao;
import cn.investin.model.Account;
import cn.investin.model.ContactInfo;
import cn.investin.model.Country;
import cn.investin.model.EmailLog;
import cn.investin.model.Field;
import cn.investin.model.Inquiry;
import cn.investin.model.InquiryAccount;
import cn.investin.model.InvitingInfo;
import cn.investin.model.ReciveEmail;
import cn.investin.model.Scale;
import cn.investin.service.InvitingInfoService;
import cn.investin.util.AppConfig;

@Service
public class InvitingInfoServiceImpl implements InvitingInfoService {
	@Autowired
	InvitingInfoDao dao;

	@Autowired
	AppConfig appConfig;

	public List<InvitingInfo> getInvitingInfo(String upload_status, String inquiryStatus, String payStatus,
			String querytext, int pageNum) {
		int pageSize = Integer.parseInt(appConfig.getProperties().getProperty("system.pageSize"));
		int limitStart = (pageNum - 1) * pageSize;
		return dao.getInvitingInfo(upload_status, inquiryStatus, payStatus, querytext, limitStart, pageSize);
	}

	public int getCountForInvitingInfo(String upload_status, String inquiryStatus, String payStatus, String querytext) {
		return dao.getCountForInvitingInfo(upload_status, inquiryStatus, payStatus, querytext);
	}

	public InvitingInfo getInvitingInfoById(String id) {
		return dao.getInvitingInfoById(id);
	}

	public void updateInvitingInfo(InvitingInfo info) {
		dao.updateInvitingInfo(info);
	}

	public List<Inquiry> getInquiry(int pageNum) {
		int pageSize = Integer.parseInt(appConfig.getProperties().getProperty("system.pageSize"));
		int limitStart = (pageNum - 1) * pageSize;
		return dao.getInquiry(limitStart, pageSize);
	}

	public int getCountForInquiry() {

		return dao.getCountForInquiry();
	}

	public List<Inquiry> getLargeInquiry(int pageNum) {
		int pageSize = Integer.parseInt(appConfig.getProperties().getProperty("system.pageSize"));
		int limitStart = (pageNum - 1) * pageSize;
		return dao.getLargeInquiry(limitStart, pageSize);
	}

	public int getCountForlargeInquiry() {

		return dao.getCountForInquiry();
	}

	public void updateInquiry(Inquiry info) {
		dao.updateInquiry(info);

	}

	public Inquiry getInquiryById(String id) {
		return dao.getInquiryById(id);
	}

	public List<Inquiry> getInquiryByStatus(String status) {
		return dao.getInquiryByStatus(status);
	}

	public Account getAccountByEmail(String email) {
		return dao.getAccountByEmail(email);
	}

	public List<InvitingInfo> getInvitingInfoByfieldId(String fieldId) {
		return dao.getInvitingInfoByfieldId(fieldId);
	}

	public void addSendemaiLog(EmailLog emailLog) {
		dao.addSendemaiLog(emailLog);
	}

	public List<InquiryAccount> getInquiryAccount(String email, String tel, int pageNum) {
		int pageSize = Integer.parseInt(appConfig.getProperties().getProperty("system.pageSize"));
		int limitStart = (pageNum - 1) * pageSize;
		if ("".equals(email))
			email = null;
		if ("".equals(tel))
			tel = null;
		return dao.getInquiryAccount(email, tel, limitStart, pageSize);
	}

	public int getCountForInquiryAccount(String email, String tel) {
		if ("".equals(email))
			email = null;
		if ("".equals(tel))
			tel = null;
		return dao.getCountForInquiryAccount(email, tel);
	}

	public List<Account> getAccount(String userName, String tel, String isPay, String countryId, String dateFlag,
			String regDate, int pageNum) {
		int pageSize = Integer.parseInt(appConfig.getProperties().getProperty("system.pageSize"));
		int limitStart = (pageNum - 1) * pageSize;
		if ("".equals(userName))
			userName = null;
		if ("".equals(tel))
			tel = null;
		return dao.getAccount(userName, tel, isPay, countryId, dateFlag, regDate, limitStart, pageSize);
	}

	public int getCountForAccount(String userName, String tel, String isPay, String countryId, String dateFlag,
			String regDate) {
		if ("".equals(userName))
			userName = null;
		if ("".equals(tel))
			tel = null;
		return dao.getCountForAccount(userName, tel, isPay, countryId, dateFlag, regDate);
	}

	public List<Country> getAllCountry() {
		return dao.getAllCountry();
	}

	public void updatePayStatus(String id) {
		dao.updatePayStatus(id);

	}

	public List<Account> getAccountOfRequest(String countryId, String newFlag, int pageNum) {
		int pageSize = Integer.parseInt(appConfig.getProperties().getProperty("system.pageSize"));
		int limitStart = (pageNum - 1) * pageSize;
		return dao.getAccountOfRequest(countryId, newFlag, limitStart, pageSize);
	}

	public int getCountForAccountOfRequest(String countryId, String newFlag) {
		return dao.getCountForAccountOfRequest(countryId, newFlag);
	}

	public void updateRequestStatus(String id) {
		dao.updateRequestStatus(id);
	}

	public List<Scale> getAllScale() {
		return dao.getAllScale();
	}

	public List<Field> getAllField() {
		return dao.getAllField();
	}

	public void createTestInfo(String title_en, String description_en, String scaleId, String fieldId, String countryId,
			String is_large) {
		dao.createTestInfo(title_en, description_en, scaleId, fieldId, countryId, is_large);

	}

	@Transactional
	public void delInfoAndAccount(String email, String infoId) {
		if ("-1".equals(infoId))// 通过账号页面删除，只传了email，则通过email删除（账号页面没有显示测试账号，所有测试账号安全）
			dao.delInfo(email);
		else
			dao.delInfoByID(infoId);// 通过已上传信息页面删除，传了id，则根据ID删除（根据邮件删除会导致测试账号下的所有信息被删除）

		if (!"test@test.com".equals(email))
			dao.delAccount(email);

	}

	public Inquiry viewInquiry(String id) {
		return dao.viewInquiry(id);
	}

	public List<ContactInfo> getContactInfo(String countryId, int pageNum) {
		int pageSize = Integer.parseInt(appConfig.getProperties().getProperty("system.pageSize"));
		int limitStart = (pageNum - 1) * pageSize;
		if (null == countryId)
			countryId = "-1";
		return dao.getContactInfo(countryId, limitStart, pageSize);
	}

	public int getCountForContactInfo(String countryId) {
		if (null == countryId)
			countryId = "-1";
		return dao.getCountForContactInfo(countryId);
	}

	public List<ContactInfo> getContactInfoByIdList(String idList) {
		return dao.getContactInfoByIdList(idList);
	}

	public ReciveEmail getReceiveEmail(String countryId) {
		return dao.getReceiveEmail(countryId);
	}

	public void addReceiveEmail(String countryId, String receive_email,String status){
		dao.addReceiveEmail(countryId, receive_email,status);
	}

	public void updateReceiveEmail(String countryId, String receive_email,String status){
		dao.updateReceiveEmail(countryId, receive_email,status);
	}

	public void updateReceiveEmailStatus(String countryId, String status) {
		dao.updateReceiveEmailStatus(countryId, status);
		
	}

}
