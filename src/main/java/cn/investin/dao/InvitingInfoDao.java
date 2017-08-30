package cn.investin.dao;

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

public interface InvitingInfoDao {

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
	 * @param limitStart
	 *            开始位置
	 * @param pageSize
	 *            查询数量
	 * @return
	 */
	public List<InvitingInfo> getInvitingInfo(@Param("upload_status") String upload_status,
			@Param("inquiryStatus") String inquiryStatus, @Param("payStatus") String payStatus,
			@Param("querytext") String querytext, @Param("limitStart") int limitStart, @Param("pageSize") int pageSize);

	/**
	 * 根据条件查询引资记录数
	 * 
	 * @param upload_status
	 *            是否已上传，0：未上传；1：已上传。
	 * @param inquiryStatus
	 *            询问状态，0：所有；1：只显示询问为0。
	 * @param payStatus
	 *            支付状态，1：已支付；0：未支付
	 * @param querytext
	 *            查询条件
	 * @param limitStart
	 *            开始位置
	 * @param pageSize
	 *            查询数量
	 * @return
	 */
	public int getCountForInvitingInfo(@Param("upload_status") String upload_status,
			@Param("inquiryStatus") String inquiryStatus, @Param("payStatus") String payStatus,
			@Param("querytext") String querytext);

	public InvitingInfo getInvitingInfoById(@Param("id") String id);

	public List<InvitingInfo> getInvitingInfoByfieldId(@Param("fieldId") String fieldId);

	public void updateInvitingInfo(InvitingInfo info);

	public List<Inquiry> getInquiry(@Param("limitStart") int limitStart, @Param("pageSize") int pageSize);

	public int getCountForInquiry();

	public List<Inquiry> getLargeInquiry(@Param("limitStart") int limitStart, @Param("pageSize") int pageSize);

	public int getCountForlargeInquiry();

	public void updateInquiry(Inquiry info);

	public Inquiry getInquiryById(@Param("id") String id);

	public Inquiry viewInquiry(@Param("id") String id);

	public List<Inquiry> getInquiryByStatus(@Param("status") String status);

	public Account getAccountByEmail(@Param("email") String email);

	public void addSendemaiLog(EmailLog emailLog);

	public List<InquiryAccount> getInquiryAccount(@Param("email") String email, @Param("tel") String tel,
			@Param("limitStart") int limitStart, @Param("pageSize") int pageSize);

	public int getCountForInquiryAccount(@Param("email") String email, @Param("tel") String tel);

	public List<Account> getAccount(@Param("userName") String userName, @Param("tel") String tel,
			@Param("isPay") String isPay, @Param("countryId") String countryId, @Param("dateFlag") String dateFlag,
			@Param("regDate") String regDate, @Param("limitStart") int limitStart, @Param("pageSize") int pageSize);

	public int getCountForAccount(@Param("userName") String userName, @Param("tel") String tel,
			@Param("isPay") String isPay, @Param("countryId") String countryId, @Param("dateFlag") String dateFlag,
			@Param("regDate") String regDate);

	public List<Country> getAllCountry();

	public void updatePayStatus(@Param("id") String id);

	public void updateRequestStatus(@Param("id") String id);

	public List<Account> getAccountOfRequest(@Param("countryId") String countryId, @Param("newFlag") String newFlag,
			@Param("limitStart") int limitStart, @Param("pageSize") int pageSize);

	public int getCountForAccountOfRequest(@Param("countryId") String countryId, @Param("newFlag") String newFlag);

	public List<Scale> getAllScale();

	public List<Field> getAllField();

	public void createTestInfo(@Param("title_en") String title_en, @Param("description_en") String description_en,
			@Param("scaleId") String scaleId, @Param("fieldId") String fieldId, @Param("countryId") String countryId,
			@Param("is_large") String is_large);

	public void delInfo(@Param("email") String email);

	public void delInfoByID(@Param("id") String id);

	public void delAccount(@Param("email") String email);

	public List<ContactInfo> getContactInfo(@Param("countryId") String countryId, @Param("limitStart") int limitStart,
			@Param("pageSize") int pageSize);

	public int getCountForContactInfo(@Param("countryId") String countryId);

	public List<ContactInfo> getContactInfoByIdList(@Param("idList") String idList);
	
	public ReciveEmail getReceiveEmail(@Param("countryId") String countryId);
	
	public void addReceiveEmail(@Param("countryId") String countryId,@Param("receive_email") String receive_email,@Param("status") String status);
	
	public void updateReceiveEmail(@Param("countryId") String countryId,@Param("receive_email") String receive_email,@Param("status") String status);
	
	public void  updateReceiveEmailStatus(@Param("countryId") String countryId,@Param("status") String status);
	

}
