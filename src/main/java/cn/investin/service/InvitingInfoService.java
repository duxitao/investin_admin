package cn.investin.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

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

public interface InvitingInfoService {

	/**
	 * 分页查询引资信息
	 * 
	 * @param upload_status
	 *            是否已上传，0：未上传；1：已上传。
	 * @param inquiryStatus
	 *            询问状态，0：所有；1：只显示询问为0。
	 * @param payStatus
	 *            支付状态，1：已支付；0：未支付
	 * @param querytext
	 *            查询条件
	 * @param pageNum
	 *            页码
	 * 
	 * @return
	 */
	public List<InvitingInfo> getInvitingInfo(String upload_status, String inquiryStatus, String payStatus,
			String querytext, int pageNum);

	/**
	 * 分页查询引资信息
	 * 
	 * @param upload_status
	 *            是否已上传，0：未上传；1：已上传。
	 * @param inquiryStatus
	 *            询问状态，0：所有；1：只显示询问为0。
	 * @param payStatus
	 *            支付状态，1：已支付；0：未支付
	 * @param querytext
	 *            查询条件
	 * 
	 * @return
	 */
	public int getCountForInvitingInfo(String upload_status, String inquiryStatus, String payStatus, String querytext);

	public InvitingInfo getInvitingInfoById(String id);

	public List<InvitingInfo> getInvitingInfoByfieldId(String fieldId);

	public void updateInvitingInfo(InvitingInfo info);

	public List<Inquiry> getInquiry(int pageNum);

	public int getCountForInquiry();

	public List<Inquiry> getLargeInquiry(int pageNum);

	public int getCountForlargeInquiry();

	public void updateInquiry(Inquiry info);

	public Inquiry getInquiryById(String id);

	public Inquiry viewInquiry(String id);

	public List<Inquiry> getInquiryByStatus(String status);

	public Account getAccountByEmail(String email);

	public void addSendemaiLog(EmailLog emailLog);

	public List<InquiryAccount> getInquiryAccount(String email, String tel, int pageNum);

	public int getCountForInquiryAccount(String email, String tel);

	public List<Account> getAccount(String userName, String tel, String isPay, String countryId, String dateFlag,
			String regDate, int pageNum);

	public int getCountForAccount(String userName, String tel, String isPay, String countryId, String dateFlag,
			String regDate);

	public List<Country> getAllCountry();

	public void updatePayStatus(String id);

	public List<Account> getAccountOfRequest(String countryId, String newFlag, int pageNum);

	public int getCountForAccountOfRequest(String countryId, String newFlag);

	public void updateRequestStatus(String id);

	public List<Scale> getAllScale();

	public List<Field> getAllField();

	public void createTestInfo(String title_en, String description_en, String scaleId, String fieldId, String countryId,
			String is_large);

	public void delInfoAndAccount(String email, String infoId);

	public List<ContactInfo> getContactInfo(String countryId, int pageNum);

	public int getCountForContactInfo(String countryId);

	public List<ContactInfo> getContactInfoByIdList(String idList);

	public ReciveEmail getReceiveEmail(String countryId);

	public void addReceiveEmail(String countryId, String receive_email,String status);

	public void updateReceiveEmail(String countryId, String receive_email,String status);

	public void updateReceiveEmailStatus(String countryId, String status);
}
