package cn.investin.model;

import java.util.Date;

public class InvitingInfo {

	int id;
	String email;
	String userName;
	String tel;
	String title_en;
	String title_cn;
	String status;
	String upload_status;
	String description_cn;
	String description_en;
	Date dateConfirmed;
	int inquiryNum;
	String scaleDesc_en;
	String fieldDesc_en;
	String additional1;
	String additional2;
	String additional3;
	Date updateTime;
	int updateBy;
	String is_test;
	String is_large;

	
	public String getIs_large() {
		return is_large;
	}

	public void setIs_large(String is_large) {
		this.is_large = is_large;
	}

	public String getIs_test() {
		return is_test;
	}

	public void setIs_test(String is_test) {
		this.is_test = is_test;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getTitle_en() {
		return title_en;
	}

	public void setTitle_en(String title_en) {
		this.title_en = title_en;
	}

	public String getTitle_cn() {
		return title_cn;
	}

	public void setTitle_cn(String title_cn) {
		this.title_cn = title_cn;
	}

	public String getDescription_cn() {
		return description_cn;
	}

	public void setDescription_cn(String description_cn) {
		this.description_cn = description_cn;
	}

	public String getDescription_en() {
		return description_en;
	}

	public void setDescription_en(String description_en) {
		this.description_en = description_en;
	}

	public Date getDateConfirmed() {
		return dateConfirmed;
	}

	public void setDateConfirmed(Date dateConfirmed) {
		this.dateConfirmed = dateConfirmed;
	}

	public int getInquiryNum() {
		return inquiryNum;
	}

	public void setInquiryNum(int inquiryNum) {
		this.inquiryNum = inquiryNum;
	}

	public String getScaleDesc_en() {
		return scaleDesc_en;
	}

	public void setScaleDesc_en(String scaleDesc_en) {
		this.scaleDesc_en = scaleDesc_en;
	}

	public String getFieldDesc_en() {
		return fieldDesc_en;
	}

	public void setFieldDesc_en(String fieldDesc_en) {
		this.fieldDesc_en = fieldDesc_en;
	}

	public String getAdditional1() {
		return additional1;
	}

	public void setAdditional1(String additional1) {
		this.additional1 = additional1;
	}

	public String getAdditional2() {
		return additional2;
	}

	public void setAdditional2(String additional2) {
		this.additional2 = additional2;
	}

	public String getAdditional3() {
		return additional3;
	}

	public void setAdditional3(String additional3) {
		this.additional3 = additional3;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public int getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(int updateBy) {
		this.updateBy = updateBy;
	}

	public String getUpload_status() {
		return upload_status;
	}

	public void setUpload_status(String upload_status) {
		this.upload_status = upload_status;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "InvitingInfo [id=" + id + ", email=" + email + ", userName=" + userName + ", tel=" + tel + ", title_en="
				+ title_en + ", title_cn=" + title_cn + ", status=" + status + ", upload_status=" + upload_status
				+ ", description_cn=" + description_cn + ", description_en=" + description_en + ", dateConfirmed="
				+ dateConfirmed + ", inquiryNum=" + inquiryNum + ", scaleDesc_en=" + scaleDesc_en + ", fieldDesc_en="
				+ fieldDesc_en + ", additional1=" + additional1 + ", additional2=" + additional2 + ", additional3="
				+ additional3 + ", updateTime=" + updateTime + ", updateBy=" + updateBy + ", is_test=" + is_test
				+ ", is_large=" + is_large + "]";
	}



}
