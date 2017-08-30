package cn.investin.model;

import java.util.Date;

public class AdminUser {

	int id;
	String pwd;
	String type;
	Date createTime;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Override
	public String toString() {
		return "AdminUser [id=" + id + ", pwd=" + pwd + ", type=" + type + ", createTime=" + createTime + "]";
	}

	
}
