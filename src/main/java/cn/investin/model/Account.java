package cn.investin.model;

import java.util.Date;

public class Account {

	int id;
	String email;
	int contryId;
	String userName;
	String companyName;
	String tel;
	String pay_status;
	int pay_amount;
	Date pay_time;
	Date pay_expiry;
	Date createTime;
	String credit_code;
	int inquiryNum;
	String request_status;
	String request_tel;
	String payment_mode;
	String countryDesc_cn;
	String countryDesc_en;
	
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getContryId() {
		return contryId;
	}

	public void setContryId(int contryId) {
		this.contryId = contryId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getPay_status() {
		return pay_status;
	}

	public void setPay_status(String pay_status) {
		this.pay_status = pay_status;
	}

	public int getPay_amount() {
		return pay_amount;
	}

	public void setPay_amount(int pay_amount) {
		this.pay_amount = pay_amount;
	}

	public Date getPay_time() {
		return pay_time;
	}

	public void setPay_time(Date pay_time) {
		this.pay_time = pay_time;
	}

	public Date getPay_expiry() {
		return pay_expiry;
	}

	public void setPay_expiry(Date pay_expiry) {
		this.pay_expiry = pay_expiry;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getCredit_code() {
		return credit_code;
	}

	public void setCredit_code(String credit_code) {
		this.credit_code = credit_code;
	}



	public int getInquiryNum() {
		return inquiryNum;
	}

	public void setInquiryNum(int inquiryNum) {
		this.inquiryNum = inquiryNum;
	}

	public String getRequest_status() {
		return request_status;
	}

	public void setRequest_status(String request_status) {
		this.request_status = request_status;
	}

	public String getRequest_tel() {
		return request_tel;
	}

	public void setRequest_tel(String request_tel) {
		this.request_tel = request_tel;
	}

	public String getPayment_mode() {
		return payment_mode;
	}

	public void setPayment_mode(String payment_mode) {
		this.payment_mode = payment_mode;
	}

	public String getCountryDesc_cn() {
		return countryDesc_cn;
	}

	public void setCountryDesc_cn(String countryDesc_cn) {
		this.countryDesc_cn = countryDesc_cn;
	}

	public String getCountryDesc_en() {
		return countryDesc_en;
	}

	public void setCountryDesc_en(String countryDesc_en) {
		this.countryDesc_en = countryDesc_en;
	}

}
