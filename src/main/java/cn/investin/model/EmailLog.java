package cn.investin.model;

import java.util.Date;

public class EmailLog {

	int id;
	int inquiry_id;
	String sender;
	String from_address;
	String to_address;
	String subject;
	String content;
	String type;
	Date send_time;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getInquiry_id() {
		return inquiry_id;
	}

	public void setInquiry_id(int inquiry_id) {
		this.inquiry_id = inquiry_id;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getFrom_address() {
		return from_address;
	}

	public void setFrom_address(String from_address) {
		this.from_address = from_address;
	}

	public String getTo_address() {
		return to_address;
	}

	public void setTo_address(String to_address) {
		this.to_address = to_address;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Date getSend_time() {
		return send_time;
	}

	public void setSend_time(Date send_time) {
		this.send_time = send_time;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	@Override
	public String toString() {
		return "EmailLog [id=" + id + ", inquiry_id=" + inquiry_id + ", sender=" + sender + ", from_address="
				+ from_address + ", to_address=" + to_address + ", subject=" + subject + ", content=" + content
				+ ", type=" + type + ", send_time=" + send_time + "]";
	}

}
