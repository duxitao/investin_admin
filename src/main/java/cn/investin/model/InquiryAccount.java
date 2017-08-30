package cn.investin.model;

import java.util.Date;

public class InquiryAccount {

	int id;
	String email;
	String tel;
	String name;
	Date reg_time;
	String status;

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

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getReg_time() {
		return reg_time;
	}

	public void setReg_time(Date reg_time) {
		this.reg_time = reg_time;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
