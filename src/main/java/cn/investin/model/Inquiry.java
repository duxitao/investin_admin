package cn.investin.model;

import java.util.Date;

public class Inquiry {

	int id;
	int info_id;
	String field_id;
	String inquiry_email;
	String title_cn;
	String title_en;
	String content;
	String requires1;
	String requires2;
	String requires3;
	String content_en;
	String requires1_en;
	String requires2_en;
	String requires3_en;
	String status;
	String group_send;
	Date create_time;
	String description_cn;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getInfo_id() {
		return info_id;
	}
	public void setInfo_id(int info_id) {
		this.info_id = info_id;
	}
	
	public String getTitle_cn() {
		return title_cn;
	}
	public void setTitle_cn(String title_cn) {
		this.title_cn = title_cn;
	}
	public String getInquiry_email() {
		return inquiry_email;
	}
	public void setInquiry_email(String inquiry_email) {
		this.inquiry_email = inquiry_email;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getRequires1() {
		return requires1;
	}
	public void setRequires1(String requires1) {
		this.requires1 = requires1;
	}
	public String getRequires2() {
		return requires2;
	}
	public void setRequires2(String requires2) {
		this.requires2 = requires2;
	}
	public String getRequires3() {
		return requires3;
	}
	public void setRequires3(String requires3) {
		this.requires3 = requires3;
	}
	public String getContent_en() {
		return content_en;
	}
	public void setContent_en(String content_en) {
		this.content_en = content_en;
	}
	public String getRequires1_en() {
		return requires1_en;
	}
	public void setRequires1_en(String requires1_en) {
		this.requires1_en = requires1_en;
	}
	
	public String getField_id() {
		return field_id;
	}
	public void setField_id(String field_id) {
		this.field_id = field_id;
	}
	public String getRequires2_en() {
		return requires2_en;
	}
	public void setRequires2_en(String requires2_en) {
		this.requires2_en = requires2_en;
	}
	public String getRequires3_en() {
		return requires3_en;
	}
	public void setRequires3_en(String requires3_en) {
		this.requires3_en = requires3_en;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getGroup_send() {
		return group_send;
	}
	public void setGroup_send(String group_send) {
		this.group_send = group_send;
	}
	public Date getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	public String getTitle_en() {
		return title_en;
	}
	public void setTitle_en(String title_en) {
		this.title_en = title_en;
	}
	public String getDescription_cn() {
		return description_cn;
	}
	public void setDescription_cn(String description_cn) {
		this.description_cn = description_cn;
	}
	
	
}
